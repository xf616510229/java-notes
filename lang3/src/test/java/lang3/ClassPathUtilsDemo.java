package lang3;

import lang3.util.Log;
import org.apache.commons.lang3.ClassPathUtils;
import org.junit.Test;

/**
 * ClassPathUtilsDemo
 * 路径相关操作
 * <p>
 *
 * @author Feathers
 * @date 2018-05-22 10:13
 */
public class ClassPathUtilsDemo {

    @Test
    public void test() {
        String s = ClassPathUtils.toFullyQualifiedName(ClassPathUtilsDemo.class, "test.properties");
        Log.i("获取与ClassPathUtils本类相同路径下的静态文件的全限定名", s);

        String s1 = ClassPathUtils.toFullyQualifiedName(ClassPathUtils.class.getPackage(), "test.properties");
        Log.i("获取与ClassPathUtils本类相同包下的静态文件的全限定名", s1);


        String s2 = ClassPathUtils.toFullyQualifiedPath(ClassPathUtilsDemo.class, "test.properties");
        Log.i("获取与ClassPathUtils本类相同路径下的静态文件的路径", s2);

        String s3 = ClassPathUtils.toFullyQualifiedPath(ClassPathUtils.class.getPackage(), "test.properties");
        Log.i("获取与ClassPathUtils本类相同包下的静态文件的路径", s3);
    }

}
