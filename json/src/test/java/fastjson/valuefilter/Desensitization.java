package fastjson.valuefilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段需要进行脱敏处理
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Desensitization {
    
    DesensitizationType type();

    enum DesensitizationType {
        PHONE,
        EMAIL,
        USERNAME,
        ;
    }
    
}
