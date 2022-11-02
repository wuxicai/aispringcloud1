

package time;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;


public class TimeTest1 {

    public int fib(int n) {
        if(n<2){
            return n;
        }
        int f=0;
        int s=1;
        while(n-- > 1){
            s+=f;
            f=s-f;
            System.out.println(s);
        }
        return s;
    }
    @Test
    public void wuxi(){
        System.out.println(fib(7));
    }
    @Test
    public void test1() throws InterruptedException {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(date.getTime());
        System.out.println(sdf.format(date));

        System.out.println("---------------------localdatetime-----------------------");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now);
        LocalDateTime of = LocalDateTime.of(2020, 12, 31, 22, 55, 55);
        System.out.println(of);
        System.out.println("---------------------时间运算-----------------------");
        System.out.println(now.plusDays(5));
        System.out.println(now.plusYears(-1));
        System.out.println(now.minusYears(1));
        System.out.println(now.minusHours(11));
        System.out.println("---------------------getday-----------------------");
        System.out.println(now.getDayOfWeek());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getDayOfYear());
        System.out.println("---------------------gethour-----------------------");
        System.out.println(now.getHour());
        System.out.println("---------------------getyear-----------------------");
        System.out.println(now.getYear());
        System.out.println("---------------------getmonth-----------------------");
        System.out.println(now.getMonth().getValue());
        System.out.println("---------------------instant(时间戳：Unix元年1970年1月1日到现在的毫秒值)-----------------------");
        Instant now1 = Instant.now();
        System.out.println(now1);//utc时间：世界协调时间
        OffsetDateTime offsetDateTime = now1.atOffset(ZoneOffset.ofHours(8));//8个时差
        System.out.println(offsetDateTime);
        System.out.println(now1.toEpochMilli());
        System.out.println("---------------------Duration计算两个时间的间隔-----------------------");
        LocalDateTime now2 = LocalDateTime.now();
        Thread.sleep(15);
        LocalDateTime now3 = LocalDateTime.now();
        System.out.println(Duration.between(now2, now3).toDays());
        Instant now4 = Instant.now();
        Thread.sleep(150);
        Instant now5 = Instant.now();
        System.out.println(Duration.between(now4, now5).getNano());
        System.out.println("---------------------Preiod计算两个日期的间隔-----------------------");
        LocalDate now7 = LocalDate.of(2020, 6, 12);
        LocalDate now6 = LocalDate.now();
        System.out.println(Period.between(now7, now6).getDays()+"***********"+now7+now6);
        System.out.println(Period.between(now7, now6).withDays(1));
        System.out.println("---------------------TemporalAdjuster时间矫正器-----------------------");
        System.out.println(now.withDayOfMonth(10));
        System.out.println(now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).getDayOfMonth());
        System.out.println("---------------------下一个工作日-----------------------");
        LocalDate nextworkday = LocalDate.now().with(d -> {
            LocalDate l = (LocalDate) d;
            DayOfWeek dw = ((LocalDate) l).getDayOfWeek();
            if (dw.equals(DayOfWeek.FRIDAY)) {
                return ((LocalDate) l).plusDays(3);
            }
            if (dw.equals(DayOfWeek.SATURDAY)) {
                return ((LocalDate) l).plusDays(2);
            } else return ((LocalDate) l).plusDays(1);
        });
        System.out.println(nextworkday);
        System.out.println("---------------------DateTimeFormatter格式化时间和日期-----------------------");
        DateTimeFormatter isoDate = DateTimeFormatter.ISO_DATE;
        System.out.println(isoDate.format(LocalDate.now()));
        System.out.println("---------------------DateTimeFormatter格式化时间和日期(自定义)-----------------------");
        DateTimeFormatter yy = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter yyy = DateTimeFormatter.ofPattern("yyyy年MM月dd号HH点mm分ss秒");
        System.out.println(yyy.format(LocalDateTime.now()));
        System.out.println("---------------------字符串转日期-----------------------");
        String format = yyy.format(LocalDateTime.now());
        System.out.println(LocalDateTime.now().parse(format,yyy));
        System.out.println("---------------------Zone时区相关-----------------------");
//        Set<String> ids = ZoneId.getAvailableZoneIds();
//        for (String id : ids) {
//            System.out.println(id);
//        }
        System.out.println(LocalDateTime.now(ZoneId.of("Asia/Chongqing")));
        ZonedDateTime chongqing = LocalDateTime.now().atZone(ZoneId.of("Asia/Chongqing"));
        System.out.println(chongqing);
    }
}
