package com.linrui.chitu.xiangji.api.image.param;


import cn.linrui.chitu.api.core.RequestBody;
import cn.linrui.chitu.api.core.RequestParam;
import com.alibaba.fastjson2.annotation.JSONField;
import com.linrui.chitu.xiangji.XjLanguageEnum;

public class SyncBatchTranslateImageParam extends XjBaseImageTransApiParam {

    @RequestParam
    private final String action = "GetImageTranslateBatch";

    /**
     * 支持 中文简体、中文繁体、英文、日文
     * @see XjLanguageEnum
     */
    @RequestParam
    private String sourceLanguage;

    /**
     * @see XjLanguageEnum
     */
    @RequestParam
    private String targetLanguage;

    @RequestBody
    private String urls;

    @RequestParam
    private String imgTransKey;

    /**
     * 秒级时间戳
     */
    @RequestParam
    private Long commitTime;

    /**
     * 1=同步返回
     * 2=异步返回
     */
    @RequestParam
    @JSONField(serialize = false)
    private final Integer sync = 1;

    public String getAction() {
        return action;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    @Override
    public String getImgTransKey() {
        return imgTransKey;
    }

    @Override
    public void setImgTransKey(String imgTransKey) {
        this.imgTransKey = imgTransKey;
    }

    public Long getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Long commitTime) {
        this.commitTime = commitTime;
    }

    public Integer getSync() {
        return sync;
    }
}
