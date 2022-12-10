package cn.yulam.confirm;

import cn.yulam.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.UUID;

public class ProducerIndividually {

    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = RabbitMqUtil.getConnection();
        // 获取连接通道
        Channel channel = connection.createChannel();
        channel.confirmSelect();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        // 开启发布确认
        channel.confirmSelect();
        long start = System.currentTimeMillis();
        // 批量发消息
        for (int i = 0; i < 10; i++) {
            String msg = "消息"+i;
            channel.basicPublish("", queueName, null, msg.getBytes());
            // 单个消息发布确认
            boolean flag = channel.waitForConfirms();
                        if (flag){
                            System.out.println("====消息发布成功====");
                        }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布 "+ 10 +"个单个发布消息确认耗时：" + (end-start) + "ms");
    }
}
