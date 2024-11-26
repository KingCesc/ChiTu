package com.linrui.chitu.tiktok;

import cn.linrui.chitu.api.core.ApiParam;
import cn.linrui.chitu.api.core.RequestHandler;
import cn.linrui.chitu.api.core.executor.model.HttpRequest;
import cn.linrui.chitu.api.core.executor.model.HttpRequestConfig;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.time.Instant;
import java.util.*;

/**
 * TikTokRequestSignatureHandler
 * <p>
 * 请求签名，并将签名参数放入Http Header中
 * </p>
 *
 * @author nick.zw
 * @since 2024/10/30
 */
public class TikTokRequestSignatureHandler implements RequestHandler {

    @Override
    public <T> void handler(HttpRequest<T> httpRequest) {
        Map<String, String> headerMap = httpRequest.getHeaderMap();
        headerMap.put("x-tts-access-token", httpRequest.getApiParam().getAccessToken());
        headerMap.put("content-type", "application/json");
        buildFullApiPath(httpRequest);
        if (httpRequest.getRequestConfig().isNeedSignature()) {
            signRequest(httpRequest);
        }
        buildFullRequestUrl(httpRequest);
    }

    private <T> void buildFullApiPath(HttpRequest<T> tiktokHttpRequest) {
        Map<String, String> pathParams = tiktokHttpRequest.getPathParams();
        StringSubstitutor sub = new StringSubstitutor(pathParams);

        HttpRequestConfig requestConfig = tiktokHttpRequest.getRequestConfig();
        String fullApiPath = sub.replace(requestConfig.getApiPath());
        requestConfig.setApiPath(fullApiPath);
    }

    private <T> void signRequest(HttpRequest<T> tiktokHttpRequest) {
        Map<String, String> requestParams = tiktokHttpRequest.getRequestParams();
        Long timestamp = Instant.now().getEpochSecond();
        String body = tiktokHttpRequest.getRequestBody();
        HttpRequestConfig requestConfig = tiktokHttpRequest.getRequestConfig();

        ApiParam apiParam = tiktokHttpRequest.getApiParam();
        requestParams.put("app_key", requestConfig.getAppKey());
        requestParams.put("timestamp", String.valueOf(timestamp));
        if (apiParam instanceof BaseTikTokShopParam) {
            requestParams.put("shop_cipher", ((BaseTikTokShopParam) apiParam).getShopCipher());
        }
        String apiPath = requestConfig.getApiPath();
        String appSecret = requestConfig.getAppKeySecret();
        String signature = generateSignature(apiPath, requestParams, appSecret, body);
        requestParams.put("sign", signature);
    }

    private <T> void buildFullRequestUrl(HttpRequest<T> tiktokHttpRequest) {
        HttpRequestConfig requestConfig = tiktokHttpRequest.getRequestConfig();
        String fullReqUrl = requestConfig.getEndpoint() + requestConfig.getApiPath();
        tiktokHttpRequest.setRequestUrl(fullReqUrl);
    }

    private String buildUrlQuery(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        Set<Map.Entry<String, String>> entries = params.entrySet();
        StringJoiner query = new StringJoiner("&");
        for (Map.Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
                query.add(name + "=" + value);
            }
        }
        return query.toString();
    }

    private String generateSignature(String apiPath, Map<String, String> iopHashMap, String appSecret, String body) {
        if (Objects.isNull(body)) {
            body = "";
        }

        String[] strings = iopHashMap.keySet().toArray(new String[0]);
        Arrays.sort(strings);

        StringBuilder query = new StringBuilder();
        for (String key : strings) {
            String value = iopHashMap.get(key);
            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                query.append(key).append(value);
            }
        }
        String content = appSecret + apiPath + query + body + appSecret;
        return encodeHacSha256(content, appSecret);
    }

    private String encodeHacSha256(String allParams, String key) {
        HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key);
        byte[] bytes = hmacUtils.hmac(allParams);
        return Hex.encodeHexString(bytes);
    }
}
