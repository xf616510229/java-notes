package fastjson.enumcvt;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.EnumSerializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.util.TypeUtils;
import fastjson.EnumUtil;

import java.io.IOException;
import java.lang.reflect.Type;

public class ResultEnumSerializer implements ObjectSerializer, ObjectDeserializer {

    /**
     * 序列化过程
     *
     * @param serializer 序列化器
     * @param object     序列化目标对象
     * @param fieldName  序列化目标对象所在字段的字段名
     * @param fieldType  序列化目标对象所在字段的字段类型
     * @param features   序列化Features
     */
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        // 如果不是BaseEnum类型的枚举，无需处理
        if (object instanceof Enum && object instanceof BaseEnum) {
            serializer.write(((BaseEnum) object).getCode());
        }
    }

    // 反序列化过程
    @Override
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Object value = defaultJSONParser.parse();
        return value == null ? null : (T) EnumUtil.getEnumByKey(TypeUtils.castToString(value), (Class) type);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
