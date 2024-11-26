package com.linrui.chitu.tiktok;

import cn.linrui.chitu.api.core.ResponseHandler;
import cn.linrui.chitu.api.core.ChiTuRestApiException;
import cn.linrui.chitu.api.core.executor.model.HttpRequest;
import cn.linrui.chitu.api.core.executor.model.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * TikTokResponseHandler
 *
 * @author nick.zw
 * @since 2024/10/30
 */
public class TikTokResponseHandler implements ResponseHandler {

    private static final Logger log = LoggerFactory.getLogger(TikTokResponseHandler.class);

    @Override
    public <T> T parseResponse(HttpResponse response, HttpRequest<T> request) throws ChiTuRestApiException {
        TikTokApiResponse tikTokApiResponse = JSON.parseObject(response.getData(), TikTokApiResponse.class, JSONReader.Feature.SupportSmartMatch);
        tikTokApiResponse.setHeaders(response.getHeaders());
        tikTokApiResponse.setCost(response.getCost());
        Integer code = tikTokApiResponse.getCode();
        String message = tikTokApiResponse.getMessage();

        if (log.isInfoEnabled()) {
            log.info("{}|{}|{}|{}|{}|{}", request.getRequestConfig().getApiCode(), request.getRequestConfig().getApiPath(), response.getCost(),
                    tikTokApiResponse.getRequestId(), code, message);
            logRequest(request, response);
        }

        if (!TikTokResultCodeEnum.OK.getCode().equals(code)) {
            throw new ChiTuRestApiException(wrapCode(code), message);
        }

        return JSONObject.parseObject(tikTokApiResponse.getData(), request.getResultClass(), JSONReader.Feature.SupportSmartMatch);
    }

    private String wrapCode(Integer tikTokErrorCode) {
        return "TK-" + tikTokErrorCode;
    }

    private <T> void logRequest(HttpRequest<T> request, HttpResponse tiktokHttpResponse) {
        Map<String, String> logData = new HashMap<>();
        logData.put("uri", request.getRequestUrl());
        logData.put("requestBody", request.getRequestBody());
        logData.put("requestParams", JSON.toJSONString(request.getRequestParams()));
        logData.put("response", tiktokHttpResponse.getData());
        log.info("{}", JSON.toJSONString(logData));
    }


}
