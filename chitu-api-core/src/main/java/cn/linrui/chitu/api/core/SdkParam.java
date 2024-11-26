package cn.linrui.chitu.api.core;


public class SdkParam {

    /**
     * api的入口地址
     */
    private String endpoint;

    /**
     * App key
     */
    private String appKey;

    /**
     * app key secret
     */
    private String appSecret;

    /**
     * just for IOException
     */
    private Integer retryTimes;

    /**
     * use proxy if needed
     */
    private Boolean useProxy;

    /**
     * http链接超时时间，单位秒
     */
    private Integer connectTimeout;

    /**
     * 数据读取超时时间，单位秒
     */
    private Integer readTimeout;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Boolean getUseProxy() {
        return useProxy;
    }

    public void setUseProxy(Boolean useProxy) {
        this.useProxy = useProxy;
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


}
