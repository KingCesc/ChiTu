package com.linrui.chitu.tiktok.api.shop;

import cn.linrui.chitu.api.core.ChiTuRestApiException;
import cn.linrui.chitu.api.core.RestApi;
import com.linrui.chitu.tiktok.api.shop.param.GetShopWebhookParam;
import com.linrui.chitu.tiktok.api.shop.result.GetShopWebhookResult;

/**
 * TikTokShopApi
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public interface TikTokShopApi {

    @RestApi(apiCode = "getShopWebhooks", path ="/event/202309/webhooks")
    GetShopWebhookResult getShopWebhooks(GetShopWebhookParam getShopWebhookParam) throws ChiTuRestApiException;

}
