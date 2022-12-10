package cn.yulam.routing;

import cn.yulam.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Producer {

    public static void main(String[] args) throws IOException {
        // 通过工具类获取连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取连接通道
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare("order_direct", "direct");
        // 指定 routingKey
        String key = "info";
        // 发布消息
        channel.basicPublish("order_direct", key, null, ("发送给指定路由" + key + "的消息").getBytes());
        // 通过工具关闭channel和释放连接
        RabbitMqUtil.close(channel, connection);
    }
}
