package cn.yulam.simple;

import cn.yulam.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sending {
    private static final Logger logger = LoggerFactory.getLogger(Sending.class);
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.getConnection();
        try (Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            for (int i = 0; i < 20; i++) {
                message = message + "=" + i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
