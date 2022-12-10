package cn.yulam.boot;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// 注入容器
@Component
// 监听 RabbitMQ
@RabbitListener(queuesToDeclare = @Queue(value = "simple_queue", durable = "true", exclusive = "false", autoDelete = "false"))
public class SimpleConsumer {
    // 自动回调
    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("消费者：" + message);
    }
}