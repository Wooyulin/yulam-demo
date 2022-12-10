package cn.yulam.boot;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumer {
    // 监听 RabbitMQ
    @RabbitListener(queuesToDeclare = @Queue("work_queue_aaa"))
    public void receiveMessage1(String message) {
        System.out.println("消费者1：" + message);
    }
    @RabbitListener(queuesToDeclare = @Queue("MSG_CDC:edu_user:usr_user"))
    public void receiveMessage3(String message) {
        System.out.println("消费者3：" + message);
    }

    // 监听 RabbitMQ
    @RabbitListener(queuesToDeclare = @Queue("work_queue_aaa"))
    public void receiveMessage2(String message) {
        System.out.println("消费者2：" + message);
    }

    // 监听 RabbitMQ
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(), // 临时队列
                    exchange = @Exchange(name = "cdc-exchange", type = "topic"), // 交换机和类型
                    key = {"simple_queue_template"} // 通配符路由key
            )})
    public void receiveMessageCDC(String message) {
        System.out.println("消费者2：" + message);
    }
}