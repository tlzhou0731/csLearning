package userfulClasses;

import org.junit.Test;

import java.time.*;
import java.util.Set;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/3/3
 * Time:15:02
 * Describe:
 */

public class JDK8APITest {

    @Test
    /**
     * Clock：使用时区提供对当前即时、日期和时间的访问的时钟。
     * 持续时间：Duration，用于计算两个“时间”间隔
     * 日期间隔：Period，用于计算两个“日期”间隔
     * TemporalAdjuster : 时间校正器。有时我们可能需要获取例如：将日期调整到“下一个工作日”等操作。
     * TemporalAdjusters : 该类通过静态方法(firstDayOfXxx()/lastDayOfXxx()/nextXxx())提供了大量的常用TemporalAdjuster 的实现。
     */
    public void test1(){
        //ZoneId:类中包含了所有的时区信息
        // ZoneId的getAvailableZoneIds():获取所有的ZoneId
        Set<String> zoneIds= ZoneId.getAvailableZoneIds();
        for(String s: zoneIds) {
            System.out.println(s);
        }
        // ZoneId的of():获取指定时区的时间
        LocalDateTime localDateTime= LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(localDateTime);

        //ZonedDateTime:带时区的日期时间
        // ZonedDateTime的now():获取本时区的ZonedDateTime对象
        ZonedDateTime zonedDateTime= ZonedDateTime.now();
        System.out.println(zonedDateTime);
        // ZonedDateTime的now(ZoneId id):获取指定时区的ZonedDateTime对象
        ZonedDateTime zonedDateTime1= ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime1);
    }

    @Test
    public void test2(){
        //Duration:用于计算两个“时间”间隔，以秒和纳秒为基准
        LocalTime localTime= LocalTime.now();
        LocalTime localTime1= LocalTime.of(15, 23, 32);
        //between():静态方法，返回Duration对象，表示两个时间的间隔
        Duration duration= Duration.between(localTime1, localTime);
        System.out.println(duration);

        System.out.println(duration.getSeconds());
        System.out.println(duration.getNano());

        LocalDateTime localDateTime= LocalDateTime.of(2016, 6, 12, 15, 23, 32);
        LocalDateTime localDateTime1= LocalDateTime.of(2017, 6, 12, 15, 23, 32);

        Duration duration1= Duration.between(localDateTime1, localDateTime);
        System.out.println(duration1.toDays());
    }

    @Test
    public void test3(){
        //Period:用于计算两个“日期”间隔，以年、月、日衡量
        LocalDate localDate= LocalDate.now();
        LocalDate localDate1= LocalDate.of(2028, 2, 18);

        Period period= Period.between(localDate, localDate1);
        System.out.println(period);
        System.out.println(period.getYears());

        System.out.println(period.getMonths());
        System.out.println(period.getDays());

        Period period1= period.withYears(2);
        System.out.println(period1);
    }

    public static void main(String[] args) {

    }
}
