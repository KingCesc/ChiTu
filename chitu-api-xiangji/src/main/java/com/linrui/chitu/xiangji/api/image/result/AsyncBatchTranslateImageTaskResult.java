package com.linrui.chitu.xiangji.api.image.result;


import com.alibaba.fastjson2.annotation.JSONField;

import java.util.Map;

public class AsyncBatchTranslateImageTaskResult {

    @JSONField(name = "Content")
    private Map<String, Content> content;

    public Map<String, Content> getContent() {
        return content;
    }

    public void setContent(Map<String, Content> content) {
        this.content = content;
    }
}
