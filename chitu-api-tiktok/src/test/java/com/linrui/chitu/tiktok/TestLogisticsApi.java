package com.linrui.chitu.tiktok;

import cn.linrui.chitu.api.core.ChiTuRestApiException;
import cn.linrui.chitu.api.core.RestApiBeanBuilder;
import com.linrui.chitu.tiktok.api.logistics.TikTokLogisticsApi;
import com.linrui.chitu.tiktok.api.logistics.param.GetWarehouseListParam;
import com.linrui.chitu.tiktok.api.logistics.result.GetGlobalWarehouseListResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TestLogisticsApi
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class TestLogisticsApi extends TestBase {

    public static final String ACCESS_TOKEN = "ROW_x_MG8wAAAACE-Lgwth0JPgdW4vi4KG2gY3dEC5vfLI3WJ3Am0WvnjYhA1xL0UaASkoINlMT3ZHPVruGm8EPuBO_67a6NgvwEGXfvnxbXjD6oSO-0FjjogCvHkouZlFdkHAoM6UbRW_ZaUE_HOqqP_in_hR1ub_D-duuPF9kDH1ZrqZUgO3i7cGDuhKdfyxgLMUARlIf-s1CVUsw8Ba18VGE6ORLwTE6ZvunPBYqvE0i2gdKXvV3rKQ";

    private TikTokLogisticsApi logisticsApi;

    @Before
    public void init() {
        initExecutor();
        RestApiBeanBuilder<TikTokLogisticsApi> apiBeanBuilder = new RestApiBeanBuilder<>();
        apiBeanBuilder.setTiktokApiExecutor(apiExecutor);
        apiBeanBuilder.setInterfaceClass(TikTokLogisticsApi.class);
        logisticsApi = apiBeanBuilder.get();
    }

    @Test
    public void testGetGlobalWarehouse() throws ChiTuRestApiException {
        Assert.assertNotNull(logisticsApi);

        GetWarehouseListParam getWarehouseListParam = new GetWarehouseListParam();
        getWarehouseListParam.setAccessToken(ACCESS_TOKEN);
        GetGlobalWarehouseListResult getGlobalWarehouseListResult = logisticsApi.getGlobalWarehouseListDTO(getWarehouseListParam);
        Assert.assertEquals(13, getGlobalWarehouseListResult.getGlobalWarehouses().size());
    }

}
