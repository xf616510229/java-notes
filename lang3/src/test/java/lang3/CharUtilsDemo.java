package lang3;

import lang3.util.Log;
import org.apache.commons.lang3.CharUtils;
import static org.apache.commons.lang3.CharUtils.*;
import org.junit.Test;

/**
 * CharUtilsDemo
 * <p>
 *
 * @author Feathers
 * @date 2018-05-19 22:10
 */
public class CharUtilsDemo {

    /**
     * 常量
     */
    @Test
    public void testConstant() {
        char nul = CharUtils.NUL; // \0
        char cr = CharUtils.CR; // \r
        char lf = CharUtils.LF; // \n
    }

    /**
     * 判断字符类型
     */
    @Test
    public void testIs() {
        Log.i("判断字符是否为ASCII字符", isAscii('a'));
        Log.i("判断字符是否为ASCII字母", isAsciiAlpha('a'));
        Log.i("判断字符是否为ASCII小写字母", isAsciiAlphaLower('a'));
        Log.i("判断字符是否为ASCII大写字母", isAsciiAlphaUpper('A'));
        Log.i("判断字符是否为ASCII数字", isAsciiNumeric('1'));
        Log.i("判断字符是否为ASCII字母或者数字", isAsciiAlphanumeric('1'));
        Log.i("判断字符是否为ASCII打印字符", isAsciiPrintable('\n'));
        Log.i("判断字符是否为ASCII控制字符", isAsciiControl('\n'));
    }

    /**
     * 转换
     */
    @Test
    public void testTo() {
        Log.i("string -> one char, 如果string为null或者empty会抛出非法参数异常", toChar("abc"));
        Log.i("string -> one char, 如果string为null或者empty输出默认值", toChar("", 'a'));

        Log.i("char -> int, 如果字符不是数字，则会抛出非法参数异常", toIntValue('0'));
        Log.i("char -> int, 如果字符不是数字，返回默认值", toIntValue('a', 0));

        Log.i("char -> string", CharUtils.toString('a'));
        Character a = null;
        Log.i("char -> string, 如果参数是null返回null", CharUtils.toString(a));

    }

    /**
     * 获取unicode码
     */
    @Test
    public void testUnicode() {
        Log.i("获取字符的unicode码",CharUtils.unicodeEscaped('a'));
    }
}
