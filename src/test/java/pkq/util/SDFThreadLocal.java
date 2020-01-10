package pkq.util;


import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class SDFThreadLocal {
    public final static String DATE_PATTERN = "yyyyMMdd";

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DATE_PATTERN);
        }
    };

    /**
     * 线程安全的转换
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseSync(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }


    /**
     * 线程安全的时间工具类
     *
     * @param date
     * @return
     */
    public static String formatSync(Date date) {
        return threadLocal.get().format(date);
    }


    //jdk1.8提供新的时间处理，是线程安全的:
    public static Date parseSync8(String dateStr, String pattern) {
        if (StringUtils.isEmpty(pattern)) {

            pattern = DATE_PATTERN;
        }
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(pattern);

        java.time.LocalDate date = java.time.LocalDate.parse(dateStr, formatter);

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = date.atStartOfDay(zoneId);

        return Date.from(zdt.toInstant());
    }

    public static String formatSync8(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {

            pattern = DATE_PATTERN;
        }

        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(pattern);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime now = date.toInstant().atZone(zoneId).toLocalDateTime();

        return now.format(formatter);

    }
}
