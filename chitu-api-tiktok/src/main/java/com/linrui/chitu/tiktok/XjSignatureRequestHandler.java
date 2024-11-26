package com.linrui.chitu.tiktok;

import cn.linrui.chitu.api.core.RequestHandler;
import cn.linrui.chitu.api.core.executor.model.HttpRequest;
import cn.linrui.chitu.api.core.executor.model.HttpRequestConfig;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

/**
 * SignatureHandler
 *
 * @author nick.zw
 * @since 2024/6/19
 */
public class XjSignatureRequestHandler implements RequestHandler {

    @Override
    public <T> void handler(HttpRequest<T> httpRequest) {
        Map<String, String> requestParams = httpRequest.getRequestParams();

        HttpRequestConfig requestConfig = httpRequest.getRequestConfig();
        String transKey = requestConfig.getImgTransKey();
        requestParams.put("ImgTransKey", transKey);

        String commitTime = requestParams.get("CommitTime");
        String userKey = requestConfig.getAppKey();
        String sign = DigestUtils.md5Hex(commitTime + "_" + userKey + "_" + transKey);
        requestParams.put("Sign", sign);
    }

}
