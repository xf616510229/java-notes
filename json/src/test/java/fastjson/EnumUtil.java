package fastjson;

import fastjson.enumcvt.BaseEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnumUtil {

    /**
     * 根据key和泛型类型查询指定的泛型实例
     */
    @SuppressWarnings("all")
    public static <T extends Enum<T> & BaseEnum> T getEnumByKey(String code, Class<T> enumClass) {
        if (code == null || enumClass == null) {
            throw new IllegalArgumentException();
        }
        List<T> enumList = new ArrayList(Arrays.asList(enumClass.getEnumConstants()));
        for (T e : enumList) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
