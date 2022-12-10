package cn.yulam.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SbApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RabbitMqTest {
    /**
     * 注入 RabbitTemplate
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleSendMessage() {
        for (int i = 0; i < 10; i++) {

            rabbitTemplate.convertAndSend("simple_queue_aaa", "This is a message !" + i);
        }
    }

    @Test
    public void testWorkSendMessage() {
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("work_queue_aaa", "This is a message !, 序号：" + i);
        }
    }
}