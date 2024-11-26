package com.linrui.chitu.xiangji.api.image;


import cn.linrui.chitu.api.core.ChiTuRestApiException;
import cn.linrui.chitu.api.core.RestApi;
import cn.linrui.chitu.api.core.constants.HttpMethodEnum;
import com.linrui.chitu.xiangji.api.image.param.CreateAsyncBatchTranslateImageTaskParam;
import com.linrui.chitu.xiangji.api.image.param.GetBatchTranslateImageTaskResultParam;
import com.linrui.chitu.xiangji.api.image.param.SyncBatchTranslateImageParam;
import com.linrui.chitu.xiangji.api.image.result.AsyncBatchTranslateImageTaskResult;
import com.linrui.chitu.xiangji.api.image.result.BatchTranslateImageResult;
import com.linrui.chitu.xiangji.api.image.result.CreateAsyncBatchTranslateImageTaskResult;

/**
 *
 * 象寄图片翻译服务
 *
 * @author nick.zw
 * @since 2024/10/31
 *
 */
public interface XjImageTranslateApi {

    @RestApi(apiCode = "syncBatchTransferImage", path = "", method = HttpMethodEnum.POST)
    BatchTranslateImageResult syncBatchTranslateImage(SyncBatchTranslateImageParam param) throws ChiTuRestApiException;

    @RestApi(apiCode = "createAsyncBatchTranslateImage", path = "", method = HttpMethodEnum.POST)
    CreateAsyncBatchTranslateImageTaskResult createAsyncBatchTranslateImage(CreateAsyncBatchTranslateImageTaskParam param) throws ChiTuRestApiException;

    @RestApi(apiCode = "getBatchTranslateImageResult", path = "")
    AsyncBatchTranslateImageTaskResult getBatchTranslateImageResult(GetBatchTranslateImageTaskResultParam param) throws ChiTuRestApiException;

}
