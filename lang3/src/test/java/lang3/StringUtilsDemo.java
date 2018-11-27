package lang3;

import lang3.util.Log;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * StringUtilsDemo
 * <p>
 * 空白字符
 * 空格 \s
 * 回车 \n
 * 换行 \r
 * 制表 \t
 * <p>
 *
 * @author Feathers
 * @date 2018-05-18 14:15
 */
public class StringUtilsDemo {

    public static final String NULL = "Null";
    public static final String EMPTY = "Empty";

    public static final String STR_NULL = null;
    public static final String STR_EMPTY = "";
    public static final String STR_BLANK = "  ";

    @Test
    public void testConstant() {
        String cr = StringUtils.CR; // \r
        String lf = StringUtils.LF; // \n
        String space = StringUtils.SPACE; // " "
        String empty = StringUtils.EMPTY; // ""
    }

    @Test
    public void testLength() {
        Log.i(length(null));
    }

    /**
     * 字符串数据类型判断
     */
    @Test
    public void testIs() {

        Log.i("判断字符串是否为Empty", isEmpty(""));
        Log.i("判断字符串是否为Blank", isBlank("\n\t "));
        Log.i("判断字符是否不是Empty", isNoneEmpty(""));
        Log.i("判断字符是否不是Blank", isNoneBlank(" "));

        Log.i("判断字符串内容是否为数字 123", isNumeric("123"));
        Log.i("判断字符串内容是否为数字 123 ", isNumeric("123 "));
        Log.i("判断字符串内容是否为数字 123.4", isNumeric("123.4"));
        Log.i("判断字符串内容是否为数字 12 3", isNumeric("12 3"));

        Log.i("判断字符串是否为纯Unicode字符组成", isAlpha("啊打发"));
        Log.i("判断字符串是否为纯Unicode字符组成 2,\\n, \\s等字符都属于ASCII", isAlpha("啊打发2"));
        Log.i("判断字符串是否为纯Unicode字符组成 空字符串/null会返回false", isAlpha(""));
        Log.i("判断字符串是否为纯Unicode字符以及空格组成", isAlphaSpace("沙发上 "));
        Log.i("判断字符串是否为纯Unicode字符以及数字组成", isAlphanumeric("沙发上123"));
        Log.i("判断字符串是否为纯Unicode字符以及数字和空格组成", isAlphanumericSpace("沙发上 123"));


        Log.i("判断字符串是否全为小写字母", isAllLowerCase("ad"));
        Log.i("判断字符串是否全为小写字母,只允许出现小写字母，如果有其他字符，比如汉字、数字，会返回false", isAllLowerCase("ads123"));
        Log.i("判断字符串是否全为大写字母", isAllUpperCase("AD"));
        Log.i("判断字符串时大小写混合", isMixedCase("ADa"));
        Log.i("判断字符串时大小写混合 可以包含其他中文、数字字符", isMixedCase("ADa 哈"));

    }

    /**
     * trim 操作只会去除字符串两侧的空白字符
     * 去空格操作 trim   if null then null
     * 去空格操作 trimToEmpty if null/"" then ""
     */
    @Test
    public void testTrim() {
        Log.i("---------trim------");
        Log.i("a_b", trim("a b"));
        Log.i(NULL, trim(STR_NULL));

        Log.i("--------trimToNull------");
        Log.i(NULL, trimToNull(STR_NULL));
        Log.i(EMPTY, trimToNull(STR_EMPTY));

        Log.i("--------trimToEmpty------");
        Log.i(NULL, trimToEmpty(STR_NULL).length());
        Log.i(EMPTY, trimToEmpty(STR_EMPTY).length());
    }

    /**
     * 移除字符串文本末尾出的\n，\r，\n\r 即回车符
     */
    @Test
    public void testChomp() {
        Log.i(chomp("a\n\r"));
    }

    /**
     * 去除指定的字符，如果不传，则默认去除空白字符
     * strip 去除两侧占位符
     * stripStart 去除字符串开始的指定字符
     * stripEnd 去除字符串结束的指定字符
     * stripAll 批量处理
     * stripAccents 去除字符串中的变音符号 #,b,x,bb,ヰ
     */
    @Test
    public void testStrip() {
        Log.i("---------strip-----------");
        String target = " a b ";
        Log.i("去除两侧的空白字符\t\t", strip(target));
        Log.i("去除两侧的指定字符\t\t", strip(target, "\\s"));
        Log.i("去除开始的的指定字符\t", stripStart(target, "\\s"));
        Log.i("去除字符串结束的指定字符\t", stripEnd(target, "\\s"));

        Log.i("---------stripToEmpty-----------");
        // 将空白字符进行strip操作，如果得到""或null 则返回 ""
        Log.i(EMPTY, stripToEmpty(STR_BLANK));

        Log.i("---------stripToNull-----------");
        // 将空白字符进行strip操作，如果得到""或null 则返回 null
        Log.i(NULL, stripToNull(STR_BLANK));

        Log.i("---------stripAll-----------");
        String[] strings1 = stripAll(" \n", " \t");
        Log.i("StripAll 去除空白字符", strings1);
        String[] strings2 = stripAll(new String[]{"ab", "acb", "adb"}, "b");
        Log.i("StripAll 去除指定字符", strings2);

        Log.i("----------stripAccents---------");
        Log.i(stripAccents("éclair"));

    }

    /**
     * 给字符串设置默认值
     * defaultString 如果字符串是 null 则设置默认字符串，默认字符串默认为""空字符串
     * defaultIfEmpty 如果字符串是空字符串，则设置默认字符串
     * defaultIfBlank 如果字符串是blank字符串，则设置默认字符串
     */
    @Test
    public void testDefault() {
        String defaultText = "我是默认文字";

        Log.i("defaultString", defaultString(null));
        Log.i("defaultString2", defaultString(null, defaultText));

        Log.i("defaultIfEmpty", defaultIfEmpty("", defaultText));
        Log.i("defaultIfBlank", defaultIfEmpty("   ", defaultText));
    }

    /**
     * 字符串比较
     */
    @Test
    public void testEquals() {
        Log.i("null eq null", StringUtils.equals(null, null));
        Log.i("忽略大小写比较", equalsIgnoreCase("a", "A"));
        Log.i("多个字符串与同一字符串比较", equalsAny("abc", "abd", "abc"));
        Log.i("多个字符串与同一字符串忽略大小写比较", equalsAnyIgnoreCase("abc", "abd", "ABC"));
    }

    /**
     * 字符串比较（大小比较,字典顺序）
     * 当前字符串1的字典顺序高于字符串2是，返回-1 否则返回 1
     */
    @Test
    public void testCompare() {
        Log.i("字符串比较", compare("abc", "abc"));
        Log.i("字符串比较", compare("abc", "abd"));
        Log.i("字符串比较", compare("abc", "abcd"));
        Log.i("字符串比较", compare("abd", "abc"));


        Log.i("字符串比较 忽略大小写", compareIgnoreCase("Abd", "abc"));
    }

    /**
     * 字符串查找
     */
    @Test
    public void testIndexOf() {

        Log.i("-------------indexOf-------------");
        Log.i("查找字符串a在字符串b中的位置", indexOf("abc", "a"));
        Log.i("如果没有查找到，返回-1", indexOf("abc", "d"));
        Log.i("如果两个字符串其中有一个未null，则返回-1", indexOf("abc", null));

        Log.i("指定位置开始查找", indexOf("abc", "b", 1));
        Log.i("查找字符", indexOf("abc", 'a'));

        Log.i("-------------indexOfIgnoreCase-------------");
        Log.i("忽略大小写查找", indexOfIgnoreCase("abc", "Ab"));

        Log.i("--------------ordinalIndexOf------------");
        Log.i("查找字符串在目标字符串中出现第ordinal次时的位置", ordinalIndexOf("abcb", "b", 2));

        Log.i("--------------indexOfDifference------------");
        Log.i("查找两个字符串开始不同的位置", indexOfDifference("abcd", "abde"));

        Log.i("---------------indexOfAny-------------------");
        Log.i("查找多个字符在目标字符串的位置", indexOfAny("abc", "a", "b"));

        Log.i("---------------indexOfAnyBut-------------------");
        Log.i("查找字符串不在目标字符串的位置", indexOfAnyBut("abc", "ab"));

        Log.i("--------------lastIndexOf-------------");
        Log.i("从后往前查找", lastIndexOf("abca", "a"));
        Log.i("从后往前忽略大小写查找", lastIndexOfIgnoreCase("abca", "A"));

    }

    /**
     * 字符串包含检查操作 contains
     */
    @Test
    public void testContains() {
        Log.i("检查字符串中是否有某个字符", contains("\na", 'a'));
        Log.i("检查字符串中是否有某个字符串", contains("\na", "a"));

        Log.i("检查字符串中是否有某些字符", containsAny("aa", 'a', 'c'));
        Log.i("检查字符串中是否有某些字符串", containsAny("aa", "c", "d"));

        Log.i("检查字符串中是否有空白字符", containsWhitespace(""));
        Log.i("检查字符串中是否有空白字符", containsWhitespace("\na"));

        Log.i("忽略大小写检查", contains("\na", "A"));

        Log.i("检查字符串中是否只包含某个字符", containsOnly("aaa", 'a'));
        Log.i("检查字符串中是否只包含某个字符串", containsOnly("aaac", "a"));

        Log.i("检查字符串中是否不包含参数二的字符", containsNone("aaac", 'd'));
        Log.i("检查字符串中是否不包含参数二的字符串", containsNone("aaac", "c"));

    }

    /**
     * 字符串开始结尾字符判断
     */
    @Test
    public void testStartEnd() {
        Log.i("判断字符串是否以某一字符串开始", startsWith("123_test", "123"));
        Log.i("判断字符串是否以任意列表中的字符串结束", startsWithAny("123_test", "123", "124"));
        Log.i("忽略大小写判断", startsWithIgnoreCase("123A_test", "123a"));

        Log.i("判断字符串是否以某一字符串结束", endsWith("123_test", "test"));
        Log.i("判断字符串是否以任意列表字符串字符串结束", endsWithAny("123_test", "test", "ha"));
        Log.i("忽略大小写判断", endsWithIgnoreCase("123_test", "Test"));
    }

    /**
     * 字符串截取
     */
    @Test
    public void testSubstring() {
        Log.i("截取[start, ends)的字符串", substring("abcd", 2, 3));
        Log.i("从指定位置截取字符", substring("abcd", 2));
        Log.i("从指定字符串位置开始截取该字符前面的字符串(从左向右查找该字符串)", substringBefore("abcd", "b"));
        Log.i("从指定字符串位置开始截取该字符前面的字符串(从右向左查找该字符串)", substringBeforeLast("abcd", "b"));

        Log.i("从指定字符串位置开始截取该字符后面的字符串(从左向右查找该字符串)", substringAfter("abcd", "b"));
        Log.i("从指定字符串位置开始截取该字符后面的字符串(从右向左查找该字符串)", substringAfterLast("abcd", "b"));

        Log.i("找到两个tag字符之间的字符并截取", substringBetween("abcda", "a"));
        Log.i("从指定指定start字符串的位置截取end字符的位置的字符串", substringBetween("abcd", "a", "d"));
        Log.i("从指定指定start字符串的位置截取end字符的位置的字符串, 将符合情况的全部输出", substringsBetween("abcdabecd", "a", "d"));

        Log.i("-------------left, right, mid----------------");
        Log.i("从左开始截取长度为len的字符串", left("abcd", 2));
        Log.i("从右开始截取长度为len的字符串", right("abcd", 2));
        Log.i("从pos位置开始截取，截取len长度的字符串", mid("abcd", 2, 2));
    }

    /**
     * 字符串截取
     */
    @Test
    public void testTruncate() {
        Log.i("从0开始截取五个字符作为一个字符串",StringUtils.truncate("abcdefghijk", 5));
        Log.i("从1开始截取五个字符作为一个字符串",StringUtils.truncate("abcdefghijk", 1,5));
    }

    /**
     * 字符串分割
     */
    @Test
    public void testSplit() {
        Log.i("使用空格将字符串分割为数组", split("a b c "));
        Log.i("使用指定字符串将字符串分割为数组", split("a,b,c,", ","));
        Log.i("使用指定字符串将字符串分割为数组", split("a,b,c,,", ","));

        Log.i("whole使用指定字符串将字符串分割为数组", splitByWholeSeparator("a,b,c,", ","));
        Log.i("whole使用指定字符串将字符串分割为数组，默认同样使用空格", splitByWholeSeparator("a b c", null));
        Log.i("whole使用指定字符串将字符串分割为数组，空字符串不会被忽略", splitByWholeSeparator("a,b,c,,", ","));
        Log.i("whole使用指定字符串将字符串分割为数组，设置数组最大长度", splitByWholeSeparator("a,b,c", ",", 2));
        Log.i("whole使用指定字符串将字符串分割为数组，设置数组最大长度 元素1为", splitByWholeSeparator("a,b,c", ",", 2)[1]);

        Log.i("按照字符串数据的不同类型进行分割", splitByCharacterType("abcABC!!!123a"));
        Log.i("按照字符串数据的不同类型进行分割，并且忽略大小写", splitByCharacterTypeCamelCase("abcABC!!!123a"));

        Log.i("splitPreserveAllTokens和whole作用相同，不忽略空白项", splitPreserveAllTokens("a b c "));
    }

    /**
     * 字符串拼接
     */
    @Test
    public void testJoin() {
        String[] as = new String[]{"a", "b", "c"};

        Log.i("将数组拼接为字符串，默认以空字符串为连接符", join(as));
        Log.i("将数组拼接为字符串，使用指定连接符", join(as, ","));
        Log.i("将数组指定位置的元素使用指定连接符拼接为字符串", join(as, ",", 2, 3));
    }

    /**
     * 字符串字符移除操作
     */
    @Test
    public void testRemove() {

        Log.i("移除字符串中的某个字符串(移除所有匹配)", remove("abcabc", "a"));
        Log.i("移除字符串中的某个字符串(移除匹配正则的所有字符串,和remove类似)", removePattern("abcabc", "a"));
        Log.i("移除字符串中的某个字符串(移除从左到右第一个匹配)", removeFirst("abcabc", "b"));

        Log.i("移除字符串中的某个字符串(如果头部有该字符串，则移除)", removeStart("abcabc", "a"));
        Log.i("移除字符串中的某个字符串(如果头部有该字符串，则移除，忽略大小写)", removeStartIgnoreCase("abcabc", "A"));
        Log.i("移除字符串中的某个字符串(如果尾部有该字符串，则移除)", removeEnd("abcabc", "c"));
        Log.i("移除字符串中的某个字符串(如果尾部有该字符串，则移除，忽略大小写)", removeEndIgnoreCase("abcabc", "c"));
    }

    /**
     * 删除方法
     */
    @Test
    public void testDelete() {
        Log.i("删除所有空白符", deleteWhitespace("a b \nd"));
    }

    /**
     * 字符串替换
     */
    @Test
    public void testReplace() {
        Log.i("替换字符串（所有匹配的都会被替换）", replace("abcabc", "a", "e"));
        Log.i("替换字符串（所有匹配的都会被替换）", replaceChars("abcabc", "a", "e"));
        Log.i("替换字符串（所有匹配的都会被替换）", replaceChars("abcabc", 'a', 'e'));
        Log.i("替换字符串（所有匹配的都会被替换, 但是最多替换max次）", replace("abcabc", "a", "e", 1));

        Log.i("替换字符串（忽略大小写）", replaceIgnoreCase("abcabc", "A", "e"));
        Log.i("替换字符串（忽略大小写，最多被替换max次）", replaceIgnoreCase("abcabc", "A", "e", 1));

        Log.i("替换字符串（只替换第一个匹配的）", replaceFirst("abcabc", "a", "e"));
        Log.i("替换字符串（只替换一次）", replaceOnce("abcabc", "a", "e"));

        Log.i("替换字符串，使用指定的正则表达式", replacePattern("abcabc", "a", "e"));

        Log.i("增强替换，只替换一次,如果两个参数数组长度不一致，会抛出非法参数异常",
                replaceEach("bcacab", new String[]{"a", "b", "c"}, new String[]{"d", "e", "f"}));
        Log.i("增强替换，替换多次，比如将a替换为b，b再替换为c,c替换为d，结果为dddddd",
                replaceEachRepeatedly("bcacab", new String[]{"a", "b", "c"}, new String[]{"b", "c", "d"}));
    }

    /**
     * 字符串覆盖
     * 如果start和end索引最小值大于当前字符串的长度，则默认覆盖字符串当前长度的位置
     * 如果start和end索引最大值小于当前字符串的长度，则默认插入到字符串的0位置
     */
    @Test
    public void testOverlay() {
        Log.i("将字符串的[start,end)位置使用ovrlay字符串覆盖", StringUtils.overlay("abcdef", "zz", 2, 4));
        Log.i("将字符串的[start,end)位置使用ovrlay字符串覆盖", StringUtils.overlay("abcdef", "zz", 4, 2));
        Log.i("特殊情况1", StringUtils.overlay("abcdef", "zz", -100, 2));
        Log.i("特殊情况2", StringUtils.overlay("abcdef", "zz", -100, -200));
        Log.i("特殊情况3", StringUtils.overlay("abcdef", "zz", 2, 100));
        Log.i("特殊情况4", StringUtils.overlay("abcdef", "zz", 50, 100));
    }

    /**
     * 填充
     */
    @Test
    public void testPad() {
        Log.i("----------左填充----------");
        Log.i("左填充，默认使用空格", leftPad("abc", 5));
        Log.i("左填充，长度满足不进行填充", leftPad("abc", 2));
        Log.i("左填充，指定填充字符", leftPad("abc", 5, "-"));

        Log.i("----------右填充----------");
        Log.i("右填充，默认使用空格", rightPad("abc", 5));
        Log.i("右填充，长度满足不进行填充", rightPad("abc", 2));
        Log.i("右填充，指定填充字符", rightPad("abc", 5, "-"));
    }

    /**
     * 居中
     */
    @Test
    public void testCenter() {
        Log.i("字符串居中，默认使用空格", center("abc", 5));
        Log.i("字符串居中，指定填充字符", center("abc", 5, "-="));
        Log.i("字符串居中，长度不对称", center("abc", 6, "-="));
    }

    /**
     * 重复
     */
    @Test
    public void testRepeat() {
        Log.i("字符重复3次", repeat('a', 3));
        Log.i("字符串重复3次", repeat("abc", 3));
        Log.i("字符串重复0次", repeat("abc", 0));
        Log.i("字符串重复-1次", repeat("abc", -1));

        Log.i("重复字符串时，加入分割符", repeat("abc", ",", 3));
    }

    /**
     * 字符串包裹
     */
    @Test
    public void testWrap() {
        Log.i("字符串包裹，无论如何都包裹", wrap("=abc=", "="));
        Log.i("字符串包裹，如果不存在才会包裹", wrapIfMissing("=abc=", "="));

        Log.i("如果没有suffixes后缀，则添加后缀suffix", appendIfMissing("abc", "~", "-", "="));
        Log.i("如果没有suffixes后缀，则添加后缀suffix", appendIfMissing("abc-", "~", "-", "="));
        Log.i("prependIfMissing", prependIfMissing("abc", "q:", "Q:", "q:"));

        Log.i("解除字符串的包裹", unwrap("==a==", "="));
    }

    /**
     * 反转
     */
    @Test
    public void testReverse() {
        Log.i("字符串反转", reverse("abc"));
        Log.i("使用分割符反转", reverseDelimited("a-a,b,c", ','));
    }

    /**
     * 大小写转换
     */
    @Test
    public void testCase() {
        Log.i("大写转换", upperCase("abc"));
        Log.i("小写转换", lowerCase("ABC"));
        Log.i("大小写反转", swapCase("Abc"));

        Log.i("指定时区，从而指定不同语言的大小写字母", upperCase("abc", Locale.CHINA));

        Log.i("首字母大写", capitalize("abc"));
        Log.i("首字母小写", uncapitalize("ABC"));
    }

    /**
     * 缩短省略
     * 比如一个长度特别长的字符串展示时要使用...的方式展示，最短的省略大小为4，否则会抛IllegalArgumentException
     */
    @Test
    public void testAbbreviate() {
        Log.i("获取字符串缩写", abbreviate("中华人民共和国", 6));
        Log.i("获取字符串缩写", abbreviate("My Home Page", 8));
        Log.i("获取字符串缩写，指定偏移量", abbreviate("我叫李四，今年20岁了，我喜欢很多很多好吃的", 5, 10));
        Log.i("获取字符串缩写，缩略中部，上面都是尾部或者头部，可以指定缩略符号", abbreviateMiddle("我叫李四，今年20岁了，我喜欢很多很多好吃的", "...", 10));
    }

    /**
     * 匹配计数
     */
    @Test
    public void testCount() {
        Log.i("匹配计数,查找a在abcabc中出现的次数", countMatches("abcabc", "a"));
        Log.i("匹配计数,查找a在abcabc中出现的次数", countMatches("abcabc", 'a'));
    }

}
