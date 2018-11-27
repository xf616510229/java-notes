package lang3.date;

import lang3.util.Log;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * DateFormatUtilsDemo
 * 日期格式化工具类
 * <p>
 *
 * @author Feathers
 * @date 2018-05-21 16:55
 */
public class DateFormatUtilsDemo {

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd-HH-mm-ss-SSS";

    @Test
    public void test() {
        Log.i("Date类型格式化", DateFormatUtils.format(new Date(), DATE_FORMAT_PATTERN));
        Log.i("Calender类型格式化", DateFormatUtils.format(Calendar.getInstance(), DATE_FORMAT_PATTERN));
        Log.i("时间戳类型格式化", DateFormatUtils.format(new Date().getMinutes(), DATE_FORMAT_PATTERN));
    }

    @Test
    public void testPattern() {
        // DateFormatUtils 提供了几种时间格式
        Date date = new Date();
        System.out.println("ISO_8601_EXTENDED_DATE_FORMAT:" + DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        System.out.println("ISO_8601_EXTENDED_DATETIME_FORMAT:" + DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern()));
        System.out.println("ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT:" + DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.getPattern()));
        System.out.println("ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT:" + DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT.getPattern()));
        System.out.println("SMTP_DATETIME_FORMAT:" + DateFormatUtils.format(date, DateFormatUtils.SMTP_DATETIME_FORMAT.getPattern()));

        // 上面的代码等同于
        System.out.println(DateFormatUtils.ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT.format(date));
    }

}
