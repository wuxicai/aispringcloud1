import org.junit.Test;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest{

    @Test
    public void test1() throws UnsupportedEncodingException, ExecutionException, InterruptedException {


//        FutureTask<Integer> integerFutureTask1 = new FutureTask<>(new Wu1Runnable());
//        new Thread(integerFutureTask1,"1").start();
//        new Thread(integerFutureTask1,"2").start();
        WuRunnable wuRunnable = new WuRunnable();
        new Thread(wuRunnable,"3").start();
        new Thread(wuRunnable,"4").start();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i==4){
                WuRunnable wuRunnable = new WuRunnable();
                WuRunnable wuRunnable1 = new WuRunnable();
                Thread a=new Thread(wuRunnable,"高级");
                Thread b=new Thread(wuRunnable1,"低级------");
                a.setPriority(Thread.MAX_PRIORITY);
                b.setPriority(Thread.MIN_PRIORITY);
                a.start();
                b.start();
            }
        }
    }
}

class WuRunnable implements Runnable{
    private int i;
    private String wu="wu";
    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("高级")){
            System.out.println("高级进入");
        }else {
            System.out.println("低级进入_________________________________");
        }
        Thread.yield();
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"   "+i+wu);
            if (i==50){
                Thread.yield();
                System.out.println(Thread.currentThread().getName()+"让步——————————————————————————————————————————————");
            }
        }

    }

}
class Wu1Runnable implements Callable<Integer>{

    public Integer call() throws Exception {
        int i=0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"   "+i);
        }
        return 7;
    }
}