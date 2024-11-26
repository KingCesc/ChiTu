package com.linrui.chitu.tiktok;

/**
 * TikTokResultCodeEnum
 * <p>
 * Reference: <a href="https://partner.tiktokshop.com/docv2/page/662fd3cbba28f302e05be68c#Back%20To%20Top">TikTok Common Error Codes</a>
 * </p>
 * @author nick.zw
 * @since 2024/10/30
 */
public enum TikTokResultCodeEnum {

    OK(0, "you are lucky!"),

    INTERNAL_SERVICE_ERROR(102000, "internal service error"),

    REQUEST_TIMEOUT(102001, "request time out");

    TikTokResultCodeEnum(Integer code, String message) {
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
