package cn.linrui.chitu.api.core;

import cn.linrui.chitu.api.core.constants.ResultCodeEnum;

public class ChiTuRestApiException extends Exception {

    private static final long serialVersionUID = 3521978735006435970L;

    private String code;

    public ChiTuRestApiException(String message) {
        super(message);
    }

    public ChiTuRestApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ChiTuRestApiException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ChiTuRestApiException(ResultCodeEnum resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public ChiTuRestApiException(ResultCodeEnum resultCode, Throwable cause) {
        super(resultCode.getMessage(), cause);
        this.code = resultCode.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
