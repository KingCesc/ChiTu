package cn.linrui.chitu.api.core.executor.model;

import cn.linrui.chitu.api.core.constants.HttpMethodEnum;

import java.util.Map;

public class HttpRequestConfig {

    private String apiCode;

    private String apiPath;

    private String endpoint;

    private String appKey;

    private String appKeySecret;

    private boolean needSignature;

    private HttpMethodEnum method;

    private boolean useProxy;

    private Integer retryTimes;

    private Integer connectTimeout;

    private Integer readTimeout;

    private Map<String, String> extendParams;

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppKeySecret() {
        return appKeySecret;
    }

    public void setAppKeySecret(String appKeySecret) {
        this.appKeySecret = appKeySecret;
    }

    public boolean isNeedSignature() {
        return needSignature;
    }

    public void setNeedSignature(boolean needSignature) {
        this.needSignature = needSignature;
    }

    public HttpMethodEnum getMethod() {
        return method;
    }

    public void setMethod(HttpMethodEnum method) {
        this.method = method;
    }

    public boolean isUseProxy() {
        return useProxy;
    }

    public void setUseProxy(boolean useProxy) {
        this.useProxy = useProxy;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Map<String, String> getExtendParams() {
        return extendParams;
    }

    public void setExtendParams(Map<String, String> extendParams) {
        this.extendParams = extendParams;
    }
}
