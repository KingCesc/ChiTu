package com.linrui.chitu.xiangji;

import cn.linrui.chitu.api.core.ChiTuRestApiException;
import cn.linrui.chitu.api.core.ResponseHandler;
import cn.linrui.chitu.api.core.constants.ResultCodeEnum;
import cn.linrui.chitu.api.core.executor.model.HttpRequest;
import cn.linrui.chitu.api.core.executor.model.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * XjResponseHandler
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class XjResponseHandler implements ResponseHandler {

    private static final Logger log = LoggerFactory.getLogger(XjResponseHandler.class);

    @Override
    public <T> T parseResponse(HttpResponse response, HttpRequest<T> request) throws ChiTuRestApiException {
        if (log.isInfoEnabled()) {
            logRequest(request, response);
        }

        String data = response.getData();
        if (StringUtils.isBlank(data)) {
            throw new ChiTuRestApiException(ResultCodeEnum.UNKNOWN);
        }

        XjApiResponse apiResponse = JSON.parseObject(data, XjApiResponse.class, JSONReader.Feature.SupportSmartMatch);
        Integer code = apiResponse.getCode();
        if (XjResultCodeEnum.SUCCESS.getCode().equals(code)) {
            return JSON.parseObject(apiResponse.getData(), request.getResultClass(), JSONReader.Feature.SupportSmartMatch);
        }

        if (XjResultCodeEnum.TASK_IS_RUNNING.getCode().equals(code)) {
            return null;
        }

        throw new ChiTuRestApiException(String.valueOf(code), apiResponse.getMessage());
    }

    private <T> void logRequest(HttpRequest<T> request, HttpResponse response) {
        Map<String, String> logData = new HashMap<>();
        logData.put("uri", request.getRequestUrl());
        logData.put("requestBody", request.getRequestBody());
        logData.put("requestParams", JSON.toJSONString(request.getRequestParams()));
        logData.put("response", response.getData());
        log.info("{}", JSON.toJSONString(logData));
    }
}
