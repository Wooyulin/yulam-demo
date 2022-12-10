package cn.yulam.work;


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
        // 通道绑定消息队列
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 1; i <= 20; i++) {
            // 发布消息
            channel.basicPublish("", "work", null, (i + "号消息").getBytes());
        }
        // 通过工具关闭channel和释放连接
        RabbitMqUtil.close(channel, connection);
    }
}
