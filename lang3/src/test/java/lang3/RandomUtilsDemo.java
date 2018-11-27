package lang3;

import lang3.util.Log;
import org.junit.Test;

import static org.apache.commons.lang3.RandomUtils.*;

/**
 * RandomUtilsDemo
 * 随机生成工具类
 * <p>
 *
 * @author Feathers
 * @date 2018-05-19 22:34
 */
public class RandomUtilsDemo {

    @Test
    public void testBoolean() {
        Log.i("随机生成一个boolean值", nextBoolean());
    }

    @Test
    public void testInt() {
        Log.i("随机生成一个int值", nextInt());
        Log.i("随机生成一个int值，设置范围[start, end)", nextInt(1, 10));
    }

    @Test
    public void testLong() {
        Log.i("随机生成一个long值", nextLong());
        Log.i("随机生成一个long值，设置范围[start, end)", nextLong(1, 10));
    }

    @Test
    public void testFloat() {
        Log.i("随机生成一个float值", nextFloat());
        Log.i("随机生成一个float值，设置范围[start, end)", nextFloat(10.1f, 12.3f));
    }

    @Test
    public void testDouble() {
        Log.i("随机生成一个double值", nextFloat());
        Log.i("随机生成一个double值，设置范围[start, end)", nextDouble(10.1, 12.3));
    }

    @Test
    public void testBytes() {
        Log.i("随机生成一个bytes数组，指定长度为count", nextBytes(2));
    }
}
