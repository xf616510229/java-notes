package fastjson.valuefilter;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.lang.reflect.Field;

public class DesensitizationValueFilter implements ValueFilter {
    
    @Override
    public Object process(Object o, String propertyName, Object propertyValue) {
        try {
            Field field = o.getClass().getDeclaredField(propertyName);
            Desensitization filedAnnotation = field.getAnnotation(Desensitization.class);
            if (filedAnnotation != null) {
                if (propertyValue instanceof String && String.valueOf(propertyValue).trim().length() > 0) {
                    return doDesensitization(filedAnnotation.type(), (String) propertyValue);
                }
            }
        } catch (NoSuchFieldException ignored) {
        }
        return propertyValue;
    }

    private String doDesensitization(Desensitization.DesensitizationType type, String value) {
        switch (type) {
            case EMAIL:
                break;
            case PHONE:
                return value.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
            case USERNAME:
                break;
        }
        return null;
    }
}
