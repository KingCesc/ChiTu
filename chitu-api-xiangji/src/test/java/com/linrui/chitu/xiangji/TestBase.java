package com.linrui.chitu.xiangji;

import cn.linrui.chitu.api.core.ResponseHandler;
import cn.linrui.chitu.api.core.RestApiExecutor;
import cn.linrui.chitu.api.core.configuration.GlobalConfig;
import cn.linrui.chitu.api.core.executor.HttpRequestExecutor;
import cn.linrui.chitu.api.core.executor.httpclient.HttpClientRequestExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * TestBase
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class TestBase {

    protected RestApiExecutor apiExecutor;

    protected void initExecutor() {
        GlobalConfig globalConfig = createGlobalConfig();
        HttpRequestExecutor httpRequestExecutor = createHttpRequestExecutor(globalConfig);
        apiExecutor = new XjRestApiExecutor();
        apiExecutor.setRequestExecutor(httpRequestExecutor);
        apiExecutor.setGlobalConfig(globalConfig);
        apiExecutor.setResponseHandler(createResponseHandler());
        apiExecutor.addRequestHandler(new XjSignatureRequestHandler());
    }

    private GlobalConfig createGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setEndpoint("https://api.tosoiot.com");
        globalConfig.setAppKey("");
        Map<String, String> extendMap = new HashMap<>();
        extendMap.put("imgTransKey", "");
        globalConfig.setExtendParams(extendMap);
        globalConfig.setReadTimeout(20);
        return globalConfig;
    }

    private HttpRequestExecutor createHttpRequestExecutor(GlobalConfig globalConfig) {
        HttpClientRequestExecutor httpRequestExecutor =  new HttpClientRequestExecutor(globalConfig);
        httpRequestExecutor.init();
        return httpRequestExecutor;
    }

    private ResponseHandler createResponseHandler() {
        return new XjResponseHandler();
    }


}
