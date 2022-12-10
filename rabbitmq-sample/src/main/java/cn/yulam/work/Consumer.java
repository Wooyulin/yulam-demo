package cn.yulam.work;

import cn.yulam.utils.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws IOException {
        // 通过工具类获取连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取连接通道
        final Channel channel = connection.createChannel();
        //步骤一:一次只接受一条未确认的消息
        channel.basicQos(1);
        // 通道绑定消息队列
        channel.queueDeclare("work", true, false, false, null);
        // 消费消息
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者1号：消费-" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
