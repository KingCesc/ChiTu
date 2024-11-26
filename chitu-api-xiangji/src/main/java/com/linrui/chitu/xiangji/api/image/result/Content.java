package com.linrui.chitu.xiangji.api.image.result;


import com.alibaba.fastjson2.annotation.JSONField;

public class Content {

    @JSONField(name = "Code")
    private Integer code;

    @JSONField(name = "Message")
    private String message;

    @JSONField(name = "RequestId")
    private String requestId;

    @JSONField(name = "OriginUrl")
    private String originUrl;

    @JSONField(name = "Url")
    private String url;

    @JSONField(name = "SslUrl")
    private String sslUrl;

    @JSONField(name = "SourceLanguage")
    private String sourceLanguage;

    @JSONField(name = "TargetLanguage")
    private String targetLanguage;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSslUrl() {
        return sslUrl;
    }

    public void setSslUrl(String sslUrl) {
        this.sslUrl = sslUrl;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }
}
