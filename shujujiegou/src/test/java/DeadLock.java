import java.util.concurrent.TimeUnit;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        Object lock1=new Object();
        Object lock2=new Object();
        Thread thread1=new Thread(()->{
            synchronized (lock1){
                System.out.println("A获取到了锁A");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("A获取到了锁B");
                }
            }


        });
        thread1.start();
        Thread thread2=new Thread(()->{
            synchronized (lock2){
                System.out.println("B获取到了锁B");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("B获取到了锁A");
                }
            }

        });
        thread2.start();
//        thread1.join();
//        thread2.join();
    }
}
