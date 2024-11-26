package com.linrui.chitu.xiangji;

import cn.linrui.chitu.api.core.ChiTuRestApiException;
import cn.linrui.chitu.api.core.RestApiBeanBuilder;
import com.linrui.chitu.xiangji.api.image.XjImageTranslateApi;
import com.linrui.chitu.xiangji.api.image.param.SyncBatchTranslateImageParam;
import com.linrui.chitu.xiangji.api.image.result.BatchTranslateImageResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

/**
 * TestXjImageTranslateApi
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class TestXjImageTranslateApi extends TestBase {

    private XjImageTranslateApi xjImageTranslateApi;

    @Before
    public void init() {
        initExecutor();
        RestApiBeanBuilder<XjImageTranslateApi> apiBeanBuilder = new RestApiBeanBuilder<>();
        apiBeanBuilder.setTiktokApiExecutor(apiExecutor);
        apiBeanBuilder.setInterfaceClass(XjImageTranslateApi.class);
        xjImageTranslateApi = apiBeanBuilder.get();
    }

    @Test
    public void testSyncTranslate() throws ChiTuRestApiException {
        SyncBatchTranslateImageParam batchTransferImageParam = new SyncBatchTranslateImageParam();
        batchTransferImageParam.setSourceLanguage("CHS");
        batchTransferImageParam.setTargetLanguage("ENG");
        batchTransferImageParam.setCommitTime(Instant.now().getEpochSecond());
        String url = "https://cbu01.alicdn.com/img/ibank/O1CN018whzwP2HVVHRV9QgJ_!!2216808749156-0-cib.jpg";
        batchTransferImageParam.setUrls(url);
        BatchTranslateImageResult result = xjImageTranslateApi.syncBatchTranslateImage(batchTransferImageParam);
        Assert.assertEquals(url, result.getContent().get(0).getOriginUrl());
    }

}
