package fastjson.enumcvt;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(serializeEnumAsJavaBean = true)
public enum FakeJavaBeanEnum {


    SUCCESS("200", "success"),
    FAIL("400", "fail"),
    ;

    private String code;

    private String msg;

    FakeJavaBeanEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "str" + this.code;
    }
    
}
