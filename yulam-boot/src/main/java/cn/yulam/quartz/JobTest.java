package cn.yulam.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public class JobTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail detail = JobBuilder
                .newJob(MyJob.class)
                .withIdentity("my", "group")
                .build();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("helloTrigger", "triggerGroup")
                .startNow()
                .usingJobData("key", "nihao")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();
        scheduler.scheduleJob(detail, cronTrigger);
        scheduler.start();

        TimeUnit.MINUTES.sleep(1);//1分钟以后停掉调度器
        scheduler.shutdown();
    }
}
