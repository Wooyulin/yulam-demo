package cn.yulam.quartz;


import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //获取触发器的名称及其组名称，获取作业详细信息的名称及其组名称.
        String triggerName = context.getTrigger().getKey().getName();
        String triggerGroup = context.getTrigger().getKey().getGroup();
        String jobDetailName = context.getJobDetail().getKey().getName();
        String jobDetailGroup = context.getJobDetail().getKey().getGroup();
        JobDataMap jobDataMap = context.getTrigger().getJobDataMap();
        System.out.println(jobDataMap.get("key"));

    }
}
