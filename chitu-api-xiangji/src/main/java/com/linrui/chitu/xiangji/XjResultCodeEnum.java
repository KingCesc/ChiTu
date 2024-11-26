package com.linrui.chitu.xiangji;

/**
 * XjResultCodeEnum
 * <p>
 * Reference: <a href="https://www.xiangjifanyi.com/doc/apiDoc/#%E8%AF%B7%E6%B1%82%E8%BF%94%E5%9B%9E%E9%94%99%E8%AF%AF%E7%A0%81>Result Error Code</a>
 * </p>
 * @author nick.zw
 * @since 2024/10/31
 */
public enum XjResultCodeEnum {

    SUCCESS(200, "success"),

    REQUEST_TIMEOUT(101, "请求超时"),

    URL_ENCODE_ERROR(103, "URL编码错误"),

    PARAM_NOT_VALID(104, "参数错误"),

    LANGUAGE_NOT_SUPPORT(105, "该语向不支持"),

    LANGUAGE_NOT_RECOGNIZE(106, "语种识别错误"),

    TRANSLATE_ERROR(107, "翻译错误"),

    SUB_ACCOUNT_HAS_NO_PERMISSION(109, "子账号没有权限"),

    SERVICE_NOT_OPEN(110, "账号没有开通服务"),

    SUB_ACCOUNT_SERVICE_ERROR(111, "子账号服务失败"),

    INVOKE_THIRD_ERROR(112, "翻译服务调用失败"),

    INSUFFICIENT_BALANCE(113, "账号服务没有开通或者欠费"),

    TASK_IS_RUNNING(114, "任务尚未处理完成 RequestId正在处理"),

    REQUEST_ID_INVALID(115, "请求ID错误"),

    DATA_DECODE_ERROR(116, "任务数据解析失败"),

    REQUEST_IS_EXPIRED(117, "任务数据已过期"),

    UNKNOWN_ERROR(199, "未知错误");


    XjResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private final Integer code;

    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
