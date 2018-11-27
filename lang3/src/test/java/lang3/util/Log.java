package lang3.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

/**
 * Log
 * <p>
 *
 * @author Feathers
 * @date 2018-05-18 14:18
 */
@SuppressWarnings("all")
public class Log {

    public static void i(Object msg) {
        if (msg != null && msg.getClass().isArray())
            System.out.println(ArrayUtils.toString(msg));
        else if (msg != null && msg.getClass() == Date.class)
            System.out.println(DateFormatUtils.format((Date) msg, "yyyy-MM-dd-HH-mm-ss-SSS"));
        else
            System.out.println(msg);
    }

    public static void e(Object msg) {
        if (msg != null && msg.getClass().isArray())
            System.err.println(ArrayUtils.toString(msg));
        else if (msg != null && msg.getClass() == Date.class)
            System.err.println(DateFormatUtils.format((Date) msg, "yyyy-MM-dd-HH-mm-ss-SSS"));
        else
            System.err.println(msg);
    }

    public static void i(String prefix, Object msg) {
        if (msg != null && msg.getClass().isArray())
            System.out.println(prefix + ": " + ArrayUtils.toString(msg));
        else if (msg != null && msg.getClass() == Date.class)
            System.out.println(prefix + ": " + DateFormatUtils.format((Date) msg, "yyyy-MM-dd-HH-mm-ss-SSS"));
        else
            System.out.println(prefix + ": " + msg);
    }

    public static void e(String prefix, Object msg) throws Exception {
        if (msg != null && msg.getClass().isArray())
            System.err.println(prefix + ": " + ArrayUtils.toString(msg));
        else if (msg != null && msg.getClass() == Date.class)
            System.err.println(prefix + ": " + DateFormatUtils.format((Date) msg, "yyyy-MM-dd-HH-mm-ss-SSS"));
        else
            System.err.println(prefix + ": " + msg);

    }
}
