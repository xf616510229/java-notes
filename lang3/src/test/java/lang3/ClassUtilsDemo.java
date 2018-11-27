package lang3;

import lang3.util.Log;
import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

import static org.apache.commons.lang3.ClassUtils.*;

/**
 * ClassUtilsDemo
 * 不直接操作反射就可以处理class
 * <p>
 *
 * @author Feathers
 * @date 2018-05-22 10:27
 */
public class ClassUtilsDemo {

    /**
     * 常用常量
     */
    @Test
    public void testConstant() {
        Log.i("内部类分隔符 string类型", ClassUtils.INNER_CLASS_SEPARATOR);
        Log.i("内部类分隔符 char类型", ClassUtils.INNER_CLASS_SEPARATOR_CHAR);
        Log.i("包分隔符 string类型", ClassUtils.PACKAGE_SEPARATOR);
        Log.i("包分隔符 char类型", ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }

    /**
     * 获取方法
     */
    @Test
    public void testGet() throws Exception {

        Log.i("使用默认类加载器，获取已经初始化的class实例", ClassUtils.getClass("java.util.List"));
        Log.i("使用默认类加载器，获取未初始化的class实例", ClassUtils.getClass("java.util.List", false));
        /*
            注： commons lang getClass方法 也提供了参数classLoader对象，用来设置加载器
         */
    }
}
