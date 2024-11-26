package cn.linrui.chitu.api.core;

import cn.linrui.chitu.api.core.configuration.ApiConfig;
import cn.linrui.chitu.api.core.configuration.GlobalConfig;
import cn.linrui.chitu.api.core.constants.ResultCodeEnum;
import cn.linrui.chitu.api.core.executor.HttpRequestExecutor;
import cn.linrui.chitu.api.core.executor.model.HttpRequest;
import cn.linrui.chitu.api.core.executor.model.HttpRequestConfig;
import cn.linrui.chitu.api.core.executor.model.HttpResponse;
import cn.linrui.chitu.api.core.utils.SdkConfigUtil;
import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestApiExecutor {

    private static final Logger log = LoggerFactory.getLogger(RestApiExecutor.class);

    private GlobalConfig globalConfig;

    private HttpRequestExecutor requestExecutor;

    private Map<String, ApiConfig> apiConfigMap = new HashMap<>();

    private ResponseHandler responseHandler;

    private List<RequestHandler> requestHandlers = new ArrayList<>();

    public void addRequestHandler(RequestHandler requestHandler) {
        this.requestHandlers.add(requestHandler);
    }

    public <T> T executeRequest(RestApi restApi, ApiParam apiParam, Class<T> resultClass) throws ChiTuRestApiException {
        HttpRequestConfig requestConfig = getHttpRequestConfig(restApi, apiParam);
        HttpRequest<T> httpRequest = createRequest(apiParam, requestConfig, resultClass);
        initRequest(httpRequest);
        for (RequestHandler requestHandler : requestHandlers) {
            requestHandler.handler(httpRequest);
        }
        return executeRequest(httpRequest);
    }

    protected <T> T executeRequest(HttpRequest<T> httpRequest) throws ChiTuRestApiException {
        try {
            HttpResponse response = requestExecutor.executeRequest(httpRequest);
            return responseHandler.parseResponse(response, httpRequest);
        } catch (ChiTuRestApiException e) {
             throw e;
        } catch (IOException e) {
            throw new ChiTuRestApiException(ResultCodeEnum.REQUEST_TIMEOUT, e);
        } catch (Exception e) {
            throw new ChiTuRestApiException(ResultCodeEnum.UNKNOWN, e);
        }
    }

    private <T>  HttpRequest<T> createRequest(ApiParam apiParam, HttpRequestConfig requestConfig, Class<T> resultClass) {
        HttpRequest<T> httpRequest = new HttpRequest<>();
        httpRequest.setRequestMethod(requestConfig.getMethod());
        httpRequest.setApiParam(apiParam);
        httpRequest.setRequestConfig(requestConfig);
        httpRequest.setResultClass(resultClass);
        httpRequest.setRequestBody(apiParam.getRequestBodyString());
        httpRequest.setRequestBodyParams(apiParam.getRequestBodyParams());
        httpRequest.setContentType(apiParam.getContentType());
        httpRequest.setRequestParams(apiParam.getRequestParams());
        httpRequest.setPathParams(apiParam.getPathParams());
        return httpRequest;
    }

    private HttpRequestConfig getHttpRequestConfig(RestApi restApi, ApiParam apiParam) {
        ApiConfig appApiConfig = apiConfigMap.get(restApi.apiCode());
        return mergeApiConfig(restApi, apiParam, appApiConfig, globalConfig);
    }

    private HttpRequestConfig mergeApiConfig(RestApi restApi, ApiParam apiParam, ApiConfig appApiConfig, GlobalConfig globalConfig) {
        HttpRequestConfig httpRequestConfig = new HttpRequestConfig();
        httpRequestConfig.setApiCode(restApi.apiCode());
        httpRequestConfig.setApiPath(restApi.path());
        httpRequestConfig.setNeedSignature(restApi.needSignature());
        httpRequestConfig.setMethod(restApi.method());

        httpRequestConfig.setEndpoint(SdkConfigUtil.getApiEndpoint(apiParam.getSdkParam(), appApiConfig, globalConfig));
        httpRequestConfig.setAppKey(SdkConfigUtil.getAppKey(globalConfig, apiParam.getSdkParam()));
        httpRequestConfig.setAppKeySecret(SdkConfigUtil.getAppSecret(globalConfig, apiParam.getSdkParam()));

        httpRequestConfig.setUseProxy(SdkConfigUtil.isUseProxy(apiParam.getSdkParam(), appApiConfig, globalConfig));
        httpRequestConfig.setRetryTimes(SdkConfigUtil.getRetryTimes(globalConfig, appApiConfig, apiParam.getSdkParam()));
        httpRequestConfig.setReadTimeout(SdkConfigUtil.getReadTimeout(globalConfig, appApiConfig, apiParam.getSdkParam()));
        httpRequestConfig.setConnectTimeout(SdkConfigUtil.getConnectTimeout(globalConfig, appApiConfig, apiParam.getSdkParam()));

        httpRequestConfig.setExtendParams(globalConfig.getExtendParams());
        return httpRequestConfig;
    }

    private <T> void initRequest(HttpRequest<T> httpRequest) {
        Map<String, String>  pathParams = httpRequest.getPathParams();
        StringSubstitutor sub = new StringSubstitutor(pathParams);

        HttpRequestConfig requestConfig = httpRequest.getRequestConfig();
        String fullApiPath = sub.replace(requestConfig.getApiPath());
        requestConfig.setApiPath(fullApiPath);

        String fullReqUrl = requestConfig.getEndpoint() + requestConfig.getApiPath();
        httpRequest.setRequestUrl(fullReqUrl);
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public HttpRequestExecutor getRequestExecutor() {
        return requestExecutor;
    }

    public void setRequestExecutor(HttpRequestExecutor requestExecutor) {
        this.requestExecutor = requestExecutor;
    }

    public Map<String, ApiConfig> getApiConfigMap() {
        return apiConfigMap;
    }

    public void setApiConfigMap(Map<String, ApiConfig> apiConfigMap) {
        this.apiConfigMap = apiConfigMap;
    }

    public ResponseHandler getResponseHandler() {
        return responseHandler;
    }

    public void setResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public List<RequestHandler> getRequestHandlers() {
        return requestHandlers;
    }

    public void setRequestHandlers(List<RequestHandler> requestHandlers) {
        this.requestHandlers = requestHandlers;
    }
}
