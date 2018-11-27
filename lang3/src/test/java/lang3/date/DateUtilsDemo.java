package lang3.date;

import lang3.util.Log;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.apache.commons.lang3.time.DateUtils.*;

/**
 * DateUtilsDemo
 * 常用日期操作工具类
 * <p>
 *
 * @author Feathers
 * @date 2018-05-20 9:32
 */
public class DateUtilsDemo {

    @Test
    public void testConstant() {
        // 毫秒数
        Object[] MILLIS = new Object[]{
                MILLIS_PER_SECOND, // 每秒的毫秒数
                MILLIS_PER_MINUTE, // 每分钟的毫秒数
                MILLIS_PER_HOUR, // 每小时的毫秒数
                MILLIS_PER_DAY // 每天的毫秒数
        };
    }

    /**
     * 判断方法
     */
    @Test
    public void testIs() {
        Log.i("是否是同一天", isSameDay(new Date(), new Date()));
        Log.i("判断即时时间，即毫秒值是否相同", isSameInstant(new Date(), new Date()));
        Log.i("判断两个日历本地时间是否相同", isSameLocalTime(Calendar.getInstance(), Calendar.getInstance()));
    }

    @Test
    public void testParseDate() {
        try {
            Date date = parseDate("2018-01-03", "yyyy-MM-dd", "yyyyMMdd");
            Log.i("将字符串使用相应的一个或者多个pattern解析为对象", date);

            Date date1 = parseDate("1995年09月02日", Locale.CHINA, "yyyy年MM月dd日");
            Log.i("将字符串使用相应的一个或者多个pattern解析为date对象，并且指定Locale，如果locale为null，则使用系统默认的locale", date1);

            Date date2 = parseDateStrictly("2018-01-03", "yyyy-MM-dd", "yyyyMMdd");
            Log.i("使用严格模式解析,解析器解析严格不允许的日期， 如：\"February 942, 1996\" ", date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 日期修改 add
     */
    @Test
    public void testAdd() {
        Log.i("获取去年今天", addYears(new Date(), -1));
        Log.i("获取明年今天", addYears(new Date(), 1));

        Log.i("获取上个月今天", addMonths(new Date(), -1));
        Log.i("获取下个月今天", addMonths(new Date(), 1));

        Log.i("获取上周今天", addWeeks(new Date(), -1));
        Log.i("获取下周今天", addWeeks(new Date(), 1));

        Log.i("获取昨天", addDays(new Date(), -1));
        Log.i("获取明天", addDays(new Date(), 1));

        Log.i("获取一小时前", addHours(new Date(), -1));
        Log.i("获取一小时后", addHours(new Date(), 1));

        Log.i("获取一分钟前", addMinutes(new Date(), -1));
        Log.i("获取一分钟后", addMinutes(new Date(), 1));

        Log.i("获取一秒钟前", addSeconds(new Date(), -1));
        Log.i("获取一秒钟后", addSeconds(new Date(), 1));

        Log.i("获取一毫秒前", addMilliseconds(new Date(), -1));
        Log.i("获取一毫秒后", addMilliseconds(new Date(), 1));

    }

    /**
     * 根据阈值向上舍入 ceiling
     */
    @Test
    public void testCeiling() {
        Log.i("舍入到年", ceiling(new Date(), Calendar.YEAR));
        Log.i("舍入到月", ceiling(new Date(), Calendar.MONTH));
    }

    /**
     * 根据阈值四舍五入
     */
    @Test
    public void testRound() {
        Log.i("四舍五入到年", round(new Date(), Calendar.YEAR));
        Log.i("四舍五入到月", round(new Date(), Calendar.MONTH));
    }

    /**
     * 截取时间
     */
    @Test
    public void testTruncate() {
        Log.i("截取到年", truncate(new Date(), Calendar.YEAR));
        Log.i("截取到月", truncate(new Date(), Calendar.MONTH));
    }

    /**
     * 比较
     */
    @Test
    public void testEquals() {
        Log.i("判断两个日期的月是否相同", truncatedEquals(new Date(), new Date(), Calendar.MONTH));
        Log.i("比较两个日期的月份的大小，相同返回0", truncatedCompareTo(new Date(), new Date(), Calendar.MONTH));
    }

    /**
     * 获取方法
     * 返回指定范围内的xx数，比如指定年的月数
     */
    @Test
    public void testGet() {
        Log.i("获取一年中的天数", getFragmentInDays(new Date(), Calendar.YEAR));
    }
}
