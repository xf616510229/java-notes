package fastjson.enumcvt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

public class EnumTest {

    /**
     * 默认情况，JSON.toJSONString 使用 SerializeConfig.globalInstance 作为序列化缺省配置
     * 该配置对象中，有关枚举的 JSON.DEFAULT_GENERATE_FEATURE 缺省配置为： JSON.DEFAULT_GENERATE_FEATURE
     * 所以，枚举进行json转换时，默认输出枚举的name
     */
    @Test
    public void enumToJsonField() {
        String result = JSON.toJSONString(ResultEnum.SUCCESS);
        System.out.println(result);
    }

    /**
     * 不使用name，使用 toString 方法，重写 toString 方法， 进行灵活的指定
     * 如果想使用 ordinal， 则在toString中返回 ordinal 即可
     */
    @Test
    public void enumToJsonFiled() {
        String s = JSON.toJSONString(ResultEnum.SUCCESS, SerializerFeature.WriteEnumUsingToString);
        System.out.println(s);
    }

    /**
     * 继续使用默认配置
     * 在默认配置的基础上修改 DEFAULT_GENERATE_FEATURE 配置
     */
    @Test
    public void enumToJsonFiledUseDefault() {
        int serializerFeatures = JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteEnumUsingToString.mask;
        String text = JSON.toJSONString(ResultEnum.SUCCESS, serializerFeatures);
        System.out.println(text);
    }

    /**
     * 修改fastjson全局配置
     * 
     * |= 使用该配置
     * &= ~  不使用该配置
     */
    @Test
    public void enumToJsonFiledUpdateDefault() {
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteEnumUsingToString.mask;
        String s = JSON.toJSONString(ResultEnum.FAIL);
        System.out.println(s);
    }

    /**
     * 使用ordinal，可以重写toString，也可以使用如下方式
     */
    @Test
    public void enumToJsonFiledUseOrdinal() {
        JSON.DEFAULT_GENERATE_FEATURE &= ~SerializerFeature.WriteEnumUsingName.mask;
        String s = JSON.toJSONString(ResultEnum.FAIL);
        System.out.println(s);
    }

    /**
     * 将enum作为javaBean序列化为json
     * {"code":"200","msg":"success"}
     */
    @SuppressWarnings("all")
    @Test
    public void enumAsJavaBean1() {
        SerializeConfig config = new SerializeConfig();
        config.configEnumAsJavaBean(ResultEnum.class);
        String s = JSON.toJSONString(ResultEnum.SUCCESS, config);
        System.out.println(s);
    }

    /**
     * 方法2
     * 同上面相同，但是是将注解加到枚举上
     * 
     * 1.2.24 以后支持
     */
    @Test
    public void enumAsJavaBean2() {
        String s = JSON.toJSONString(FakeJavaBeanEnum.SUCCESS);
        System.out.println(s);
    }

    /**
     * 方法3 
     * 直接修改 全局配置，将指定枚举类进行javaBean形式的序列化
     * 好处：不用修改枚举类
     * 坏处：全局配置
     */
    @SuppressWarnings("all")
    @Test
    public void enumAsJavaBean3() {
        SerializeConfig.globalInstance.configEnumAsJavaBean(ResultEnum.class);
        String s = JSON.toJSONString(ResultEnum.SUCCESS);
        System.out.println(s);
    }
}
