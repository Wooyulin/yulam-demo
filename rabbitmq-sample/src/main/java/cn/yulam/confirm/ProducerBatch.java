package cn.yulam.confirm;

import cn.yulam.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class ProducerBatch {
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
        long start = System.currentTimeMillis();
        // 批量发布确认的大小
        int batchSize = 10;
        // 批量发消息
        for (int i = 0; i < MESSAGE_NUM; i++) {
            String msg = "消息" + i;
            channel.basicPublish("", queueName, null, msg.getBytes());
            // 量消息发布确认 判断达到 100 条后 批量确认一次
            if (i % batchSize == 0) {
                channel.waitForConfirmsOrDie(5_000);
                System.out.println("====消息发布成功====");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布 " + MESSAGE_NUM + "个批量发布消息确认耗时：" + (end - start) + "ms");
    }
}
