package lang3;

import lang3.util.Log;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

import static org.apache.commons.lang3.BooleanUtils.*;
import static java.lang.Boolean.*;

/**
 * BooleanUtilsDemo
 * <p>
 *
 * @author Feathers
 * @date 2018-05-19 1:26
 */
public class BooleanUtilsDemo {

    /**
     * 判断方法
     */
    @Test
    public void testIs() {
        // 三个方法没有区别，主要在于使用时语义的不同
        Log.i("判断是否为true", isTrue(Boolean.TRUE));
        Log.i("判断是否不为false", isNotTrue(Boolean.FALSE));
        Log.i("判断是否为false", isFalse(Boolean.FALSE));
        Log.i("判断是否不为true", isNotFalse(Boolean.TRUE));
    }

    /**
     * 比较
     */
    @Test
    public void testCompare() {
        Log.i("比较 相同返回0", BooleanUtils.compare(false, false));
        Log.i("比较", BooleanUtils.compare(true, false));
        Log.i("比较", BooleanUtils.compare(false, true));
    }

    /**
     * 将其他类型转换为boolean类型
     */
    @Test
    public void testToBoolean() {
        Log.i("int -> boolean 1", toBoolean(1));
        Log.i("int -> boolean -1", toBoolean(-1));
        Log.i("int -> boolean 0", toBoolean(0));
        Log.i("int -> boolean 定义true值和false值，如果不匹配则会抛出IllegalArgumentException",
                toBoolean(100, 100, 200));
        Log.i("Integer -> boolean 定义true值和false值，如果不匹配则会抛出IllegalArgumentException",
                toBoolean(Integer.valueOf(100), Integer.valueOf(100), Integer.valueOf(200)));

        Log.i("string -> boolean true", toBoolean("true"));
        Log.i("string -> boolean false", toBoolean("false"));
        String str1 = null;
        Log.i("string -> boolean abc", toBoolean("abc"));
        Log.i("string -> boolean null", toBoolean(str1));
        Log.i("string -> boolean (empty string)", toBoolean(""));
        Log.i("string -> boolean (blank string)", toBoolean(" "));
        Log.i("string -> boolean false 定义true值和false值，如果不匹配则会抛出IllegalArgumentException",
                toBoolean("open", "open", "close"));

        Log.i("Boolean -> boolean true", toBoolean(Boolean.TRUE));
        Log.i("Boolean -> boolean false", toBoolean(Boolean.FALSE));

        Log.i("all -> BooleanObject", toBooleanObject(1));
    }

    /**
     * 将boolean类型转换为int类型
     * int 默认 0false 1true
     */
    @Test
    public void testToInteger() {
        Log.i("boolean -> int true", toInteger(true));
        Log.i("boolean -> int false", toInteger(false));
        Log.i("boolean -> Integer true", toIntegerObject(true));
        Log.i("Boolean -> Integer false", toIntegerObject(false));
        Log.i("boolean -> Integer 定义true和false对应的int值", toInteger(false, 100,200));
    }

    @Test
    public void testToString() {
        Boolean nb = null;
        Log.i("boolean -> string 必须指定true和false对应的string值,如果为null会抛出空指针异常",
                BooleanUtils.toString(true, "open", "close"));
        Log.i("boolean -> string 必须指定true和false对应的string值,指定null对应的值",
                BooleanUtils.toString(nb, "open", "close", "sorry"));

        // 除此之外，还提供了许多默认的返回设置，比如yes/no,on/off等
        Log.i("true/false", toStringTrueFalse(true));
        Log.i("yes/no", toStringYesNo(true));
        Log.i("on/off", toStringOnOff(true));
    }

    @Test
    public void testLogic() {
        Log.i("与 and", BooleanUtils.and(new Boolean[]{true, false}));
        Log.i("或 or", BooleanUtils.or(new Boolean[]{true, false}));
        Log.i("抑或 xor", BooleanUtils.xor(new Boolean[]{true, false}));
    }

    @Test
    public void testDefault() {
        Boolean b = null;
        Log.i("设置如果为null的默认值",toBooleanDefaultIfNull(b, true));
    }
}

