package lang3;

import lang3.util.Log;
import static org.apache.commons.lang3.math.NumberUtils.*;
import org.junit.Test;

/**
 * NumberUtilsDemo
 * <p>
 *
 * @author Feathers
 * @date 2018-05-19 22:44
 */
public class NumberUtilsDemo {

    /**
     * 判断方法
     * 判断字符串是否是纯数字字符串
     * 判断字符串是否可以被解析为数字，小数、负数可以被解析
     * 判断字符串是否是一个有效的Java数值，包含0x预选项、八进制、科学计数、标点符等
     */
    @Test
    public void testIs() {
        Log.i("判断字符串是否为纯数字字符串",isDigits("123"));
        Log.i("判断字符串是否为纯数字字符串，不可包含小数点",isDigits("123.1"));
        Log.i("判断字符串是否为纯数字字符串，不可包含符号",isDigits("-123"));

        Log.i("判断字符串是否可以解析为数字,小数可以被解析", isParsable("1123.1"));
        Log.i("判断字符串是否可以解析为数字，符数可以被解析", isParsable("-1123.1"));
        Log.i("判断字符串是否可以解析为数字，其他包含字母的不可以被解析", isParsable("0x1123.1"));
        Log.i("判断字符串是否可以解析为数字，尾部包含字母不可以被解析", isParsable("123a"));

        Log.i("判断字符串是否可以被解析为java数字，负数、小数", isCreatable("-1.12"));
        Log.i("判断字符串是否可以被解析为java数字，其他进制表示", isCreatable("0x23"));
        Log.i("判断字符串是否可以被解析为java数字，科学记数法表示", isCreatable("12e12"));
        Log.i("判断字符串是否可以被解析为java数字，尾部包含字母，不可以被解析", isCreatable("-1.12a"));
    }

    @Test
    public void testToByteIntLong() {
        Log.i("string -> int 负数字符串可以转换，如果无法解析返回默认值0 ", toInt("-1", 0));
        Log.i("string -> int 其他进制不可以转换 ", toInt("0x123", 0));
        Log.i("string -> int 小数字符串不可以转换", toInt("12.3", 0));
        Log.i("string -> int 尾部不可以包含字母", toInt("123a", 0));

        // toByte toShort toLong  类似
    }

    @Test
    public void testFloatDouble() {
        Log.i("string -> float，如果无法解析则会返回0.0", toFloat("123.1"));
        Log.i("string -> float", toFloat("-123.1", 0f));
        Log.i("string -> float", toFloat("-123.1a", 0f));

        // toDouble类似
    }

    @Test
    public void testCreate() {
        Log.i("string -> number", createNumber("-123.1"));
        Log.i("string -> number 支持其他进制", createNumber("0x123"));
        Log.i("string -> number 支持科学记数法", createNumber("9e1242"));

        // 强大
        Log.i("string -> Integer 如果解析失败，会抛出NumberFormatException", createInteger("-123"));

        // BigDecimal和BigInteger
        Log.i("string -> BigInteger", createBigDecimal("9e1234"));
    }

    @Test
    public void testCompare() {
        Log.i("比较", compare(1, 2));
        Log.i("最大值", max(1, 4,3.4));
        Log.i("最小值", min(1, 2,3));

        //int[] arr = null;
        //Log.i("如果出现null,则会抛出非法参数异常", max(arr));
    }


}
