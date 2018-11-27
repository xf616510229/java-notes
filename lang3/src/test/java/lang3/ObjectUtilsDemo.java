package lang3;

import lang3.util.Log;
import org.apache.commons.lang3.ObjectUtils;
import static org.apache.commons.lang3.ObjectUtils.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Comparator;

/**
 * ObjectUtilsDemo
 * <p>
 *
 * @author Feathers
 * @date 2018-05-19 23:20
 */
public class ObjectUtilsDemo {

    /**
     * 占位符null
     */
    @Test
    public void testNull() {
        // 空的占位符
        Null aNull = ObjectUtils.NULL; // NULL
        // 比如
        Log.i(ObjectUtils.NULL == null);
    }

    /**
     * 返回不可变值
     */
    @Test
    public void testConst() {
        Log.i("返回不可变值（不可变值，可以阻止编译器重新编译）", CONST(12));
        Log.i("返回不可变值 null", CONST(null));
    }

    /**
     * 获取对象内存地址
     */
    @Test
    public void testIdentityToString() {
        Log.i("获取对象内存地址", identityToString(""));
        StringBuffer buffer = new StringBuffer();
        ObjectUtils.identityToString(buffer, 12);
        Log.i("将参数object的内存地址输入到参数1中",buffer);
    }

    /**
     * 比较
     */
    @Test
    public void testCompare() {
        Log.i("比较", compare("a", "b"));
        Log.i("比较 null，默认设置null值比较小", compare("a", null));
        Log.i("比较 null，设置null值比较大", compare("a", null, true));

        Log.i("获取较大值", max('1', '2', '3', '4'));
        Log.i("获取较小值", min('1', '2', '3', '4'));
        Log.i("获取中间值", median('1', '2', '3', '4'));

        String median = median((Comparator<String>) (o1, o2) -> {
            if (StringUtils.equals(o1, o2)) {
                return 1;
            }
            return 0;
        }, "a", "b", "c");
        Log.i("设置比较器", median);
    }

    @Test
    public void testClone() {
        Log.i("克隆对象", ObjectUtils.clone("a"));
        Log.i("克隆对象 null", ObjectUtils.clone(null));
        Log.i("克隆对象，如果克隆成功返回新的对象，失败则返回原来的对象", ObjectUtils.cloneIfPossible(null));

    }

}
