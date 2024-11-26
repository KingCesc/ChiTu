package com.linrui.chitu.xiangji.api.image.param;

import com.linrui.chitu.xiangji.XjBaseApiParam;

/**
 * XjImageTransApiParam
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class XjBaseImageTransApiParam extends XjBaseApiParam {

    protected String imgTransKey;

    public String getImgTransKey() {
        return imgTransKey;
    }

    public void setImgTransKey(String imgTransKey) {
        this.imgTransKey = imgTransKey;
    }
}
