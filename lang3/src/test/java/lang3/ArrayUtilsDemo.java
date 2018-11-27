package lang3;

import lang3.util.Log;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Map;

import static org.apache.commons.lang3.ArrayUtils.*;

/**
 * ArrayUtilsDemo
 * <p>
 *
 * @author Feathers
 * @date 2018-05-19 0:57
 */
public class ArrayUtilsDemo {

    private char[] nullArr = null;
    private char[] emptyArr = new char[]{};

    /**
     * 数组判断工具
     * 判断是否为空数组
     * 判断数组长度是否相同
     * 判断数组是否已经排序
     */
    @Test
    public void testIs() {
        Log.i("判断数组是否为空", isEmpty(emptyArr));
        Log.i("判断数组是否不为空", isNotEmpty(emptyArr));
        Log.i("判断数组是否为空,如果为null，同样代表数组为空", isEmpty(nullArr));

        Log.i("判断数组长度是否一致,提供了Object[]与Object[]的比较，可以比较不同的类型", isSameLength(new char[]{'a', 'b'}, new char[]{'1', '2'}));
        Log.i("判断数组长度是否一致,如果两个数组都为null，则代表有相同的length", isSameLength(nullArr, nullArr));

        Log.i("判断数组是否排序，使用compare函数进行判断，对象类型需要实现Comparable", isSorted(new char[]{'a', 'b', 'c'}));
    }

    /**
     * 打印数组
     */
    @Test
    public void testToString() {
        Log.i(ArrayUtils.toString(new int[]{1, 2, 3, 4}));
    }

    /**
     * 基本类型数组 转换 对象类型数组
     */
    @Test
    public void testToObject() {
        Integer[] integers = toObject(new int[]{1, 2, 3, 4});
        Log.i(integers);
    }

    /**
     * 对象类型数组 转换基本类型数组 ，如果出现null会出现空指针异常
     */
    @Test
    public void testToPrimitive() {
        boolean[] booleans = toPrimitive(new Boolean[]{true, false, true, null});
        Log.i(booleans);
    }

    @Test
    public void testToArray() {
        Integer[] integers = null;
        Log.i("nullToEmpty, 如果数组对象为null，则返回一个空的数组", nullToEmpty(integers));
        Log.i("ToArray", toArray("a", "b", "b"));
        Log.i("ToStringArray", toStringArray(new String[]{null, null}, "null~"));
    }

    /**
     * 将二维数组转换为map
     */
    @Test
    public void testToMap() {
        String[][] arr = new String[][]{{"a", "b"}, {"aa", "bb"}};
        Map<Object, Object> map = toMap(arr);
        Log.i("转换为Map", map);
    }

    /**
     * 元素位置查找
     */
    @Test
    public void testIndex() {
        char[] as = new char[]{'a', 'b', 'a'};
        Log.i("从前往后查找数组元素a", indexOf(as, 'a'));
        Log.i("从前往后查找数组元素a, 指定开始查找位置为1", indexOf(as, 'a', 1));

        Log.i("从后往前查找数组元素a", lastIndexOf(as, 'a'));
        Log.i("从后往前查找数组元素a, 指定开始查找位置为1", lastIndexOf(as, 'a', 1));
    }

    /**
     * 判断是否包含
     */
    @Test
    public void testContains() {
        char[] as = new char[]{'a', 'b', 'a'};
        Log.i("判断元素是否被包含", contains(as, 'a'));
    }

    /**
     * 元素尾部增添
     */
    @Test
    public void testAdd() {
        Log.i("将单个元素添加到数组中", add(new char[]{}, 'a'));
        Log.i("将多个元素添加到另一个数组中", addAll(new char[]{}, 'a', 'b'));
    }

    /**
     * 元素插入 插入到指定的位置
     */
    @Test
    public void testInsert() {
        String[] strs = new String[]{"a", "b", "c"};
        Log.i("将元素插入到数组的指定位置", insert(1, strs, "c", "c"));
    }

    /**
     * 元素删除
     */
    @Test
    public void testRemove() {
        Log.i("从数组中删除指定下标的元素", remove(new char[]{'a', 'b'}, 1));
        Log.i("从数组中删除指定的多个下标的元素", removeAll(new char[]{'a', 'b'}, 0, 1));
        Log.i("从数组中删除指定的元素, 只会删除第一个", removeElement(new char[]{'a', 'b', 'a'}, 'a'));
        Log.i("从数组中删除多个指定的元素, 只会删除第一个", removeElements(new char[]{'a', 'b', 'a'}, 'a', 'b'));
        Log.i("从数组中删除所有的指定的元素", removeAllOccurences(new char[]{'a', 'b', 'a'}, 'a'));
    }

    @Test
    public void testOpr() {
        int[] ints = new int[]{1, 2, 4, 5};
        reverse(ints);
        Log.i("反转数组", ints);

        int[] ints1 = new int[]{1, 2, 4, 5};
        shift(ints1, 1);
        Log.i("移动数组，数组后移1位", ints1);
        shift(ints1, -1);
        Log.i("移动数组，数组前移1位", ints1);

        int[] ints2 = new int[]{1, 2, 4, 5};
        shuffle(ints2);
        Log.i("随机打乱数组", ints2);

        int[] ints3 = new int[]{1, 2, 4, 5};
        swap(ints3, 1, 2);
        Log.i("数组交换", ints3);
        swap(ints3, 1, 2,1);
        Log.i("数组交换，指定偏移量", ints3);

        Log.i("截取数组", subarray(new int[]{1, 2, 3, 4, 5}, 1, 3));
    }
}
