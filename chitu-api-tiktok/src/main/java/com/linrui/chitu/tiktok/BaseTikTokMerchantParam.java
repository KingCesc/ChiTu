package com.linrui.chitu.tiktok;

import cn.linrui.chitu.api.core.ApiParam;
import com.alibaba.fastjson2.JSON;

import java.util.Map;

/**
 * BaseTikTokMerchantParam
 *
 * @author nick.zw
 * @since 2024/10/30
 */
public class BaseTikTokMerchantParam extends ApiParam {

    private String shopCipher;

    @Override
    public String buildRequestBodyString(Map<String, Object> paramMap) {
        return JSON.toJSONString(paramMap);
    }

}
