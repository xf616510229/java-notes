package quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.MutableTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Scheduler：核心任务调度器
 * SchedulerFactory：用于创建Scheduler
 * Job：你需要被调度器执行的任务需要实现的一个接口
 * JobDetail：每个任务的具体细节
 * JobBuilder：通常用来构建/定义 JobDetail实例
 * Trigger：一个定义了调度器上面需要有哪些任务需要被执行的组件
 * TriggerBuilder：通常用来构建/定义 Trigger实例
 */
public class QuartzTest {

    public static void startTask() throws Exception {
        // 创建任务调度器 Scheduler
        // 任务调度器负责启动、停止定时任务
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        scheduler.start();

        // JobDetail 负责配置任务
        JobDetail jobDetail = newJob(MyJob.class)
                .withIdentity("myJob", "group1")
                .withDescription("这是一个测试定时任务")
                // 传参,使用jobDataMap
                .usingJobData("name", "李四执行的")
                .usingJobData("age", "18")
                .build();
        // Trigger 触发器负责什么时候运行定时任务
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5) // 间隔5s
                        .repeatForever()) // 重复执行
                .build();
        // 使用conn表达式
        // MutableTrigger trigger2 = CronScheduleBuilder.cronSchedule("这里传入cron表达式").build();
        // scheduler.scheduleJob(jobDetail, trigger2);
        // 给Scheduler安全触发器和任务，并执行
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 定时任务需要执行的工作
     * job的定义必须包含一个空参构造， 这是因为quartz底层使用反射创建job实例
     * 如果要想向job中传递参数，可以使用jobDataMap,在创建job是可以给予参数,同样在创建trigger时,也可以向jobMap中添加值
     * 这两个jobMap可以通过context.getMergedJobDataMap() 合并 如果key重复 则后者会覆盖前者
     */
    public static class MyJob implements Job {

        // 可以定义job成员变量, 提供set和get方法, 在JobFactory实现Job实例化时,自动从jobMap中取出jobSay付给成员变量,这样就不需要使用JobMap去取出值 更为方便
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
            JobDataMap dataMap = context.getMergedJobDataMap();
            System.out.println(sdf.format(new Date()) + "---" + name + " : " + dataMap.getString("name"));
        }
    }


    // 注意，不可以使用 junit @Test执行，原因：不明
    public static void main(String[] args) throws Exception {
        startTask();
    }

}
