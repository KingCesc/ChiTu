package com.linrui.chitu.tiktok;

import cn.linrui.chitu.api.core.ResponseHandler;
import cn.linrui.chitu.api.core.RestApiExecutor;
import cn.linrui.chitu.api.core.configuration.GlobalConfig;
import cn.linrui.chitu.api.core.executor.HttpRequestExecutor;
import cn.linrui.chitu.api.core.executor.httpclient.HttpClientRequestExecutor;

/**
 * TestBase
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class TestBase  {

    protected RestApiExecutor apiExecutor;

    protected void initExecutor() {
        GlobalConfig globalConfig = createGlobalConfig();
        HttpRequestExecutor httpRequestExecutor = createHttpRequestExecutor(globalConfig);
        apiExecutor = new TikTokRestApiExecutor();
        apiExecutor.setRequestExecutor(httpRequestExecutor);
        apiExecutor.setGlobalConfig(globalConfig);
        apiExecutor.setResponseHandler(createResponseHandler());
        apiExecutor.addRequestHandler(new TikTokRequestSignatureHandler());
    }

    private GlobalConfig createGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setEndpoint("https://open-api.tiktokglobalshop.com");
        globalConfig.setAppKey("");
        globalConfig.setAppSecret("");
        return globalConfig;
    }

    private HttpRequestExecutor createHttpRequestExecutor(GlobalConfig globalConfig) {
        HttpClientRequestExecutor httpRequestExecutor =  new HttpClientRequestExecutor(globalConfig);
        httpRequestExecutor.init();
        return httpRequestExecutor;
    }

    private ResponseHandler createResponseHandler() {
        return new TikTokResponseHandler();
    }


}
