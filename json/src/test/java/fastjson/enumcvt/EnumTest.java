package fastjson.enumcvt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import fastjson.EnumUtil;
import org.junit.Test;

import javax.xml.transform.Result;

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
     * <p>
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
     * 【重点】假设需要使用 code 作为枚举 序列化 和 反序列化的值
     * 如果每个枚举都重写 toString 方法，过于繁琐，
     * 可以自定义fastjson 序列化器和反序列化器
     * 并使用 @JsonField 的 serializeUsing 和 deserializeUsing 属性指定序列化反序列化器实现类
     * 
     * 类似的做法，也可以在枚举类型使用 
     * @ JSONType(serializer = ResultEnumSerializer.class, deserializer = ResultEnumSerializer.class)
     * 指定序列化器和反序列化器
     * 同样不好用，每个枚举都要添加
     */
    @Test
    public void enumToJsonFiledCode() {
        TestJavaBean testJavaBean = new TestJavaBean();
        testJavaBean.setName("张三");
        testJavaBean.setAge(100);
        testJavaBean.setResultEnum(ResultEnum.FAIL);

        String json = JSON.toJSONString(testJavaBean);
        System.out.println(json);

        TestJavaBean newTestJavaBean = JSON.parseObject(json, TestJavaBean.class);
        System.out.println(newTestJavaBean);
    }

    /**
     * 解决上面每个enum都要打注解，很影响代码整洁度
     * 但是每有一个注解需要处理，就要加入config，很不方便
     */
    @Deprecated
    @Test
    public void enumToJsonFieldCode2() {
        SerializeConfig serializeConfig = new SerializeConfig();
        serializeConfig.put(BaseEnum.class, new ResultEnumSerializer());
        
        // 注意，这里写BaseEnum 无效
        String s = JSON.toJSONString(ResultEnum.FAIL, serializeConfig);
        System.out.println(s);
    }

    /**
     * 【重点】 解决方案, 只有序列化有效 
     * 使用JsonFilter 对值进行修改
     */
    @Test
    public void enumToJsonFieldCode3() {
        ValueFilter valueFilter = (object, name, value) -> {
            if (value instanceof Enum && value instanceof BaseEnum) {
                return ((BaseEnum) value).getCode();
            }
            return value;
        };
        TestJavaBeanNoAnno testJavaBean = new TestJavaBeanNoAnno();
        testJavaBean.setName("里昂");
        testJavaBean.setAge(50);
        testJavaBean.setResultEnum(ResultEnum.SUCCESS);
        String s = JSON.toJSONString(testJavaBean, valueFilter);
        System.out.println(s);

        TestJavaBeanNoAnno testJavaBeanNoAnno = JSON.parseObject(s, TestJavaBeanNoAnno.class);
        System.out.println(testJavaBeanNoAnno);
    }

    /**
     * 【重点】 终极解决方案  配合 enumToJsonFieldCode3 使用
     */
    @Test
    public void enumToJsonFieldCode4() {
        String json = "{\"age\":10,\"name\":\"里斯\",\"resultEnum\":\"200\"}";
        TestJavaBeanNoAnno o = JSON.parseObject(json, TestJavaBeanNoAnno.class, new MyParseConfig());
        System.out.println(o);
        
        // 全局配置parseConfig  spring boot 环境
        //FastJsonConfig.setParserConfig(new MyParseConfig());
        
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
     * <p>
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
