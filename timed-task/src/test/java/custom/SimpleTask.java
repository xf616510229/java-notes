package custom;

import java.util.Date;

/**
 * 每小时执行一次
 */
public class SimpleTask extends Thread {

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            doTask();
        }

    }

    protected void doTask() {
        // 如果需要每天指定的事件执行，在这里判断当前小时数/当前分钟数是否符合，注意sleep的事件，如果睡眠时间为一个小时，就无法精确到分钟
        System.out.println("执行任务，当前时间" + new Date().getTime());
    }

    private void Idle() {
        try {
            Thread.sleep( 60 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}