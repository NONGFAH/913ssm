package base.timer;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


public class TimerTask {
    // 每隔2秒隔行一次
    @Scheduled(cron = "0/2 * * * * ?")
    public void test2() {
        System.out.println("job2 开始执行");
    }

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask();
    }
}
