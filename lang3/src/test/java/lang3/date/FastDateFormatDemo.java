package lang3.date;

import lang3.util.Log;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.util.Date;

/**
 * FastDateFormatDemo
 * <p>
 *
 * @author Feathers
 * @date 2018-05-21 17:49
 */
public class FastDateFormatDemo {

    @Test
    public void test() {
        FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd");
        String format = fdf.format(new Date());
        Log.i(format);
    }

    @Test
    public void testPattern() {
        // 使用fast date format 直接格式化日期
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(TimeConstant.TARGET_7);
        System.out.println(fastDateFormat.format(new Date()));
    }
}
