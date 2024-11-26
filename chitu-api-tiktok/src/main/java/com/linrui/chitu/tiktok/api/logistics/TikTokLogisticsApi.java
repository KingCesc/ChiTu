package com.linrui.chitu.tiktok.api.logistics;

import cn.linrui.chitu.api.core.RestApi;
import cn.linrui.chitu.api.core.ChiTuRestApiException;
import com.linrui.chitu.tiktok.api.logistics.param.GetWarehouseListParam;
import com.linrui.chitu.tiktok.api.logistics.result.GetGlobalWarehouseListResult;

/**
 * TikTokLogisticsChiTuApi
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public interface TikTokLogisticsApi {

    @RestApi(apiCode = "getGlobalWarehouseListDTO", path ="/logistics/202309/global_warehouses")
    GetGlobalWarehouseListResult getGlobalWarehouseListDTO(GetWarehouseListParam getGlobalWarehouseListParam) throws ChiTuRestApiException;

}
