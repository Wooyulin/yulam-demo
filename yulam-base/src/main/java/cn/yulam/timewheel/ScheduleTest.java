package cn.yulam.timewheel;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {
    public static int index = 0;

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);

//        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
//            index++;
//            System.out.println("task1::" + System.currentTimeMillis() + "::" + index);
//        }, 2,1, TimeUnit.SECONDS);

        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            index++;
            System.out.println("task2::" + System.currentTimeMillis() + "::" + "bea");
        }, 3,1, TimeUnit.SECONDS);

    }

}
