package cn.linrui.chitu.api.core.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * ContentTypeEnum
 *
 * @author nick.zw
 * @since 2024/6/20
 */
public enum ContentTypeEnum {

    APPLICATION_JSON("application/json", StandardCharsets.UTF_8),

    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded", StandardCharsets.UTF_8)
    ;

    ContentTypeEnum(String mimeType, Charset charset) {
        this.mimeType = mimeType;
        this.charset = charset;
    }

    private final String mimeType;

    private final Charset charset;

    public String getMimeType() {
        return mimeType;
    }

    public Charset getCharset() {
        return charset;
    }
}
