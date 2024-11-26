package com.linrui.chitu.xiangji.api.image.result;


import com.alibaba.fastjson2.annotation.JSONField;

import java.util.List;

public class CreateAsyncBatchTranslateImageTaskResult {

    /**
     * request ids
     */
    @JSONField(name = "Content")
    private List<String> content;

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
