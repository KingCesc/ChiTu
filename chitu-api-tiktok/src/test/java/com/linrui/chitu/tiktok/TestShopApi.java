package com.linrui.chitu.tiktok;

import cn.linrui.chitu.api.core.ChiTuRestApiException;
import cn.linrui.chitu.api.core.RestApiBeanBuilder;
import com.linrui.chitu.tiktok.api.shop.TikTokShopApi;
import com.linrui.chitu.tiktok.api.shop.param.GetShopWebhookParam;
import com.linrui.chitu.tiktok.api.shop.result.GetShopWebhookResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * TestShopApi
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class TestShopApi extends TestBase {

    public static final String ACCESS_TOKEN = "";

    public static final String SHOP_CIPHER = "";

    private TikTokShopApi tikTokShopApi;

    @Before
    public void init() {
        initExecutor();
        RestApiBeanBuilder<TikTokShopApi> apiBeanBuilder = new RestApiBeanBuilder<>();
        apiBeanBuilder.setTiktokApiExecutor(apiExecutor);
        apiBeanBuilder.setInterfaceClass(TikTokShopApi.class);
        tikTokShopApi = apiBeanBuilder.get();
    }

    @Test
    public void testGetWebhook() throws ChiTuRestApiException {
        Assert.assertNotNull(tikTokShopApi);

        GetShopWebhookParam getShopWebhookParam = new GetShopWebhookParam();
        getShopWebhookParam.setShopCipher(SHOP_CIPHER);
        getShopWebhookParam.setAccessToken(ACCESS_TOKEN);
        GetShopWebhookResult shopWebhooksResult = tikTokShopApi.getShopWebhooks(getShopWebhookParam);
        List<GetShopWebhookResult.ShopWebhook> webhooks = shopWebhooksResult.getWebhooks();

        Assert.assertEquals(0, webhooks.size());
    }

}
