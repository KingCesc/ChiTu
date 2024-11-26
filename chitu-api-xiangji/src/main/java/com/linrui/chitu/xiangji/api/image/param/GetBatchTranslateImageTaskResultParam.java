package com.linrui.chitu.xiangji.api.image.param;

import cn.linrui.chitu.api.core.RequestParam;

import java.util.List;

public class GetBatchTranslateImageTaskResultParam extends XjBaseImageTransApiParam {

    @RequestParam
    private final String action = "GetImageTranslateBatchResult";

    @RequestParam
    private List<String> requestIds;

    public String getAction() {
        return action;
    }

    public List<String> getRequestIds() {
        return requestIds;
    }

    public void setRequestIds(List<String> requestIds) {
        this.requestIds = requestIds;
    }
}
