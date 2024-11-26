package cn.linrui.chitu.api.core.constants;


/**
 * HttpMethodEnum
 *
 * @author nick.zw
 * @since 2024/6/20
 */
public enum HttpMethodEnum {

    GET("GET"),

    POST("POST"),

    PUT("PUT"),

    DELETE("DELETE"),

    HEAD("HEAD"),

    OPTION("OPTION"),

    TRACE("TRACE"),
    ;

    HttpMethodEnum(String method) {
        this.method = method;
    }

    private final String method;

    public String getMethod() {
        return method;
    }
}
