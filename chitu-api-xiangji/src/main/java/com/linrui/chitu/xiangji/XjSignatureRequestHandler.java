package com.linrui.chitu.xiangji;

import cn.linrui.chitu.api.core.ApiParam;
import cn.linrui.chitu.api.core.RequestHandler;
import cn.linrui.chitu.api.core.executor.model.HttpRequest;
import cn.linrui.chitu.api.core.executor.model.HttpRequestConfig;
import com.linrui.chitu.xiangji.api.image.param.XjBaseImageTransApiParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Optional;

/**
 * SignatureHandler
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class XjSignatureRequestHandler implements RequestHandler {

    @Override
    public <T> void handler(HttpRequest<T> httpRequest) {
        HttpRequestConfig requestConfig = httpRequest.getRequestConfig();
        Map<String, String> extendParams = requestConfig.getExtendParams();
        Map<String, String> requestParams = httpRequest.getRequestParams();

        ApiParam apiParam = httpRequest.getApiParam();
        String serviceKey = "";
        if (apiParam instanceof XjBaseImageTransApiParam) {
            XjBaseImageTransApiParam xjBaseImageTransApiParam = (XjBaseImageTransApiParam)apiParam;
            serviceKey = xjBaseImageTransApiParam.getImgTransKey();
            if (StringUtils.isBlank(serviceKey)) {
                serviceKey = Optional.ofNullable(extendParams)
                        .map(e -> e.get("imgTransKey"))
                        .orElseThrow(() -> new IllegalArgumentException("The global translation key is not configured"));
            }

            requestParams.put("ImgTransKey", serviceKey);
        }

        String commitTime = requestParams.get("CommitTime");
        String userKey = requestConfig.getAppKey();
        String sign = DigestUtils.md5Hex(commitTime + "_" + userKey + "_" + serviceKey);
        requestParams.put("Sign", sign);
    }

}
