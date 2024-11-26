package cn.linrui.chitu.api.core.executor.model;

import cn.linrui.chitu.api.core.ApiParam;
import cn.linrui.chitu.api.core.constants.ContentTypeEnum;
import cn.linrui.chitu.api.core.constants.HttpMethodEnum;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest<T> {

    private String requestUrl;

    private HttpMethodEnum requestMethod;

    private ContentTypeEnum contentType;

    private String requestBody;

    private Map<String, String> requestBodyParams;

    private Map<String, String> requestParams;

    private Map<String, String> pathParams;

    private HttpRequestConfig requestConfig;

    private ApiParam apiParam;

    private Class<T> resultClass;

    private Map<String, String> headerMap = new HashMap<>();

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public HttpMethodEnum getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(HttpMethodEnum requestMethod) {
        this.requestMethod = requestMethod;
    }

    public ContentTypeEnum getContentType() {
        return contentType;
    }

    public void setContentType(ContentTypeEnum contentType) {
        this.contentType = contentType;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, String> getRequestBodyParams() {
        return requestBodyParams;
    }

    public void setRequestBodyParams(Map<String, String> requestBodyParams) {
        this.requestBodyParams = requestBodyParams;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public void setPathParams(Map<String, String> pathParams) {
        this.pathParams = pathParams;
    }

    public HttpRequestConfig getRequestConfig() {
        return requestConfig;
    }

    public void setRequestConfig(HttpRequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }

    public ApiParam getApiParam() {
        return apiParam;
    }

    public void setApiParam(ApiParam apiParam) {
        this.apiParam = apiParam;
    }

    public Class<T> getResultClass() {
        return resultClass;
    }

    public void setResultClass(Class<T> resultClass) {
        this.resultClass = resultClass;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }
}
