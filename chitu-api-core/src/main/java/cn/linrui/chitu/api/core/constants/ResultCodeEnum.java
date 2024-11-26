package cn.linrui.chitu.api.core.constants;

public enum ResultCodeEnum {

    UNKNOWN("CT-10001", "Unexpected error, please contact admin"),

    REQUEST_TIMEOUT("CT-10002", "Request timeout"),

    CONTENT_TYPE_NOT_SUPPORT("CT-10003", "Content Type is not supported");

    private final String code;

    private final String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
