package com.linrui.chitu.xiangji.api.image.result;


import com.alibaba.fastjson2.annotation.JSONField;

import java.util.List;

public class BatchTranslateImageResult {

    @JSONField(name = "Content")
    private List<Content> content;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
