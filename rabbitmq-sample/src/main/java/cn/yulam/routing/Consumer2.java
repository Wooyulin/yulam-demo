package cn.yulam.routing;

import cn.yulam.utils.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {

    public static void main(String[] args) throws IOException {
        // 通过工具类获取连接
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare("order_direct", "direct");
        // 获取临时队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定临时队列和交换机
        channel.queueBind(queue, "order_direct", "error");
        // 消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：消费-" + new String(body));
            }
        });
    }
}
