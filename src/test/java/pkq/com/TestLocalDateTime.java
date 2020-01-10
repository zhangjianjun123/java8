package pkq.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TestLocalDateTime {
    /**
     * LocalTime  时间
     * LocalDate  日期
     *
     * LocalDateTime  时间日期
     *
     *   Instant 时间戳
     *
     *   Period  计算日期
     *   Duration 计算时间
     *   TemporalAdjuster  时间接口  有一个工具类TemporalAdjusters 当工具类不满足时可以自定义
     *
     */
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime localDateTime = now.withDayOfMonth(22);//这个就是设置日期为10号
        LocalDateTime localDateTime1 = now.withDayOfYear(366);//这个不能超过366
        LocalDateTime localDateTime2 = now.withYear(2018);//设置年
        LocalDateTime localDateTime3 = now.withMonth(8);//设置月

        System.out.println(localDateTime2);
        System.out.println(localDateTime3);

        System.out.println("---------------------");
        //自定义
        LocalDateTime aa = now.with((s) -> {
        LocalDateTime ldt1 = (LocalDateTime) s;
            DayOfWeek ldt = ldt1.getDayOfWeek();
            if(ldt.equals(DayOfWeek.FRIDAY)){
            return ldt1.plusDays(3);
        }else if (ldt.equals(DayOfWeek.SATURDAY)){
            return  ldt1.plusDays(2);
        }else {
            return  ldt1.plusDays(1);
        }
        });
        System.out.println(aa);
    }
    public static void main1(String[] args) {
        LocalDate now2 = LocalDate.of(2018,9,9);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        LocalDate now3 = LocalDate.now();
        System.out.println(Period.between(now2,now3).getYears());
        System.out.println(Period.between(now2,now3).getMonths());
        System.out.println(Period.between(now2,now3).getDays());

        System.out.println("-----------------------计算两个日期差--------------------------");

        Instant now = Instant.now();//  默认读取   UTC  时区  和我们的时间相差8小时
        System.out.println(now);
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//这是本地当前时间
        System.out.println(now.toEpochMilli());//毫秒值
        System.out.println(Instant.ofEpochSecond(1000));//加1000秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Instant now1 =Instant.now();
        Duration between = Duration.between(now, now1);
        //计算俩个时间之间的差
        System.out.println(between.toMillis()+"-----------------------------It");
        //计算俩个时间之间的差
        LocalTime tm = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        LocalTime tm1 = LocalTime.now();
        Duration between1 = Duration.between(tm, tm1);
        System.out.println(between1.toMillis()+"-----------------------------lt");

        System.out.println("****************************************************************");
        LocalDateTime  ldt = LocalDateTime.now();//获取当前时间
        //把时间转成时间格式
        LocalDateTime  ldt1 = LocalDateTime.of(2018,9,9,21,33,33);
        System.out.println(ldt);
        System.out.println(ldt1);
        //格式转换
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(ldt.format(sdf));
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ldt.atZone(zoneId);
        System.out.println(zoneId+"------------");
        System.out.println(zonedDateTime+"**********************");
        LocalDateTime localDateTime = ldt.plusYears(1);//加一年
        LocalDateTime localDateTime1 = ldt.plusDays(3);//加三天
        LocalDateTime localDateTime2 = ldt.plusMonths(3);//加三天
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(ldt.minusYears(3));//减去三年
        System.out.println(ldt.minusMonths(3));//减三个月
        System.out.println(ldt.minusDays(3));//减去三天

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());

    }
}


