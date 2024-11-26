package cn.linrui.chitu.api.core.configuration;

import cn.linrui.chitu.api.core.constants.HttpConstants;

import java.util.Map;

public class GlobalConfig {

    private String endpoint;

    private String appKey;

    private String appSecret;

    private boolean useProxy = false;

    private String proxyHostName;

    private Integer proxyPort;

    private String proxyUserName;

    private String proxyPassword;

    private Integer retryTimes = 0;

    /**
     * http链接超时时间，单位秒
     */
    private Integer connectTimeout;

    /**
     * http数据读取超时时间，单位秒
     */
    private Integer readTimeout;

    /**
     * http数据写入超时时间，单位秒，目前没有使用
     */
    private Integer writeTimeout = HttpConstants.WRITE_TIMEOUT_SECONDS;

    /**
     * http链接池大小
     */
    private Integer maxTotal = HttpConstants.CONNECT_POOL_SIZE;


    /**
     * 特殊的扩展参数
     */
    private Map<String, String> extendParams;

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

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public boolean isUseProxy() {
        return useProxy;
    }

    public void setUseProxy(boolean useProxy) {
        this.useProxy = useProxy;
    }

    public String getProxyHostName() {
        return proxyHostName;
    }

    public void setProxyHostName(String proxyHostName) {
        this.proxyHostName = proxyHostName;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUserName() {
        return proxyUserName;
    }

    public void setProxyUserName(String proxyUserName) {
        this.proxyUserName = proxyUserName;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
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

    public Integer getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Integer writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Map<String, String> getExtendParams() {
        return extendParams;
    }

    public void setExtendParams(Map<String, String> extendParams) {
        this.extendParams = extendParams;
    }
}
