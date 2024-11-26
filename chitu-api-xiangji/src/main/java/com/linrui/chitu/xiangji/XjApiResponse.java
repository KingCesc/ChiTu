package com.linrui.chitu.xiangji;

/**
 * XjApiResponse
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class XjApiResponse {

    private Integer code;

    private String requestId;

    private String message;

    private String data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
