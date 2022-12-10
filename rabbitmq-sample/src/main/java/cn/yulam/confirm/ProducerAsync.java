package cn.yulam.confirm;

import cn.yulam.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

public class ProducerAsync {
    private static int MESSAGE_NUM = 100;

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        Connection connection = RabbitMqUtil.getConnection();
        // 获取连接通道
        Channel channel = connection.createChannel();
        // 声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        // 开启发布确认
        channel.confirmSelect();
        // 记录全部消息 线程安全，适用于高并发的队列
        ConcurrentSkipListMap<Long, String> outStandingConfirm = new ConcurrentSkipListMap<>();

        long start = System.currentTimeMillis();

        // 消息确认成功 回调函数 参数1：消息的标记 参数2：是否为批量
        ConfirmCallback ackCallback = (deliveryTag, multiple)->{
            // System.out.println("确认发布的消息：" + deliveryTag);
            // 2、删除掉已经确认的消息，剩下的就是未发送的消息 一般不是使用批量确认
            if (multiple){
                ConcurrentNavigableMap<Long, String> confirms = outStandingConfirm.headMap(deliveryTag);
                confirms.clear();
            }else {
                outStandingConfirm.remove(deliveryTag);
            }
        };
        // 消息确认失败 回调函数
        ConfirmCallback nackCallback = (deliveryTag, multiple)->{
             System.out.println("未确认发布的消息：" + deliveryTag);
            // 3、打印未发送的消息有哪些
        };
        // 准备消息监听器，监听那些消息成功了，那些消息失败了
        channel.addConfirmListener(ackCallback, nackCallback);
        // 批量发消息
        for (int i = 0; i < MESSAGE_NUM; i++) {
            String msg = "消息"+i;
            channel.basicPublish("", queueName, null, msg.getBytes());
            // 1、此处记录所有要发送的消息
            // System.out.println("channel.getNextPublishSeqNo(): " + channel.getNextPublishSeqNo());
            outStandingConfirm.put(channel.getNextPublishSeqNo()-1, msg);
        }
        long end = System.currentTimeMillis();
        System.out.println("发布 "+ MESSAGE_NUM +"个异步发布消息确认耗时：" + (end-start) + "ms");
    }
}
