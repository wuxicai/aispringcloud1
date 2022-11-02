package time;


import org.junit.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

/*
* java8time线程安全
*
* */
public class TimeTest {
    @Test
    public void testt2(){

        int a=3;
        int b=3;
        System.out.println((double) a/(double) b);
        System.out.println(b%a);

    }
    @Test
    public void testt(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //System.out.println(df.parse(date));
        System.out.println(df.format(date));
        System.out.println(date.getTime());

        String a="2020-06";
        System.out.println(a.substring(5,7));
        BigDecimal bigDecimal = new BigDecimal("3232.33");
        BigDecimal add = bigDecimal.add(new BigDecimal(100));
        BigDecimal divide = add.divide(new BigDecimal(100));
        System.out.println(divide.toString());
        int ddd=1;
        BigDecimal bigDecimal1 = new BigDecimal(11);
        int i = bigDecimal1.compareTo(new BigDecimal(12));
        System.out.println(i);

        System.out.println(String.format("%02d", 2));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,-1);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
        String format = df1.format(cal.getTime());
        System.out.println(format);
    }
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        ExecutorService pool = Executors.newFixedThreadPool(100);
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return df.parse("20200612");

            }
        };
        ArrayList<Future<Date>> rs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            rs.add(pool.submit(task));
        }
        for (Future<Date> r : rs) {
            System.out.println(r.get());
        }
    }
    @Test
    public void test2() throws ExecutionException, InterruptedException, ParseException {
        ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyyMMdd");
            }
        };
        Date date = df.get().parse("20200612");

//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");


        ExecutorService pool = Executors.newFixedThreadPool(100);
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return date;
            }
        };
        ArrayList<Future<Date>> rs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            rs.add(pool.submit(task));
        }
        for (Future<Date> r : rs) {
            System.out.println(r.get());
        }
        pool.shutdown();
    }
    @Test
    //java8
    public void test3() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20200612",df);
            }
        };
        ArrayList<Future<LocalDate>> rs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            rs.add(pool.submit(task));
        }
        for (Future<LocalDate> r : rs) {
            System.out.println(r.get());
        }
        pool.shutdown();
    }
    @Test
    public void test5() throws InterruptedException {
        LocalDateTime now2 = LocalDateTime.now();
        Thread.sleep(15000);
        LocalDateTime now3 = LocalDateTime.now();
        System.out.println(Duration.between(LocalDateTime.now(),now3).toDays());
    }
}
