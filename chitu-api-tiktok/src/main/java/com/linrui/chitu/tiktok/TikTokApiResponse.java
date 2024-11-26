package com.linrui.chitu.tiktok;

import java.util.Map;

/**
 * TikTokApiResponse
 *
 * @author nick.zw
 * @since 2024/10/30
 */
public class TikTokApiResponse {

    private Integer code;

    private String requestId;

    private String message;

    private String data;

    private Map<String, String> headers;

    private Long cost;

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

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
