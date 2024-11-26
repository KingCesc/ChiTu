package com.linrui.chitu.xiangji;

import cn.linrui.chitu.api.core.ApiParam;
import cn.linrui.chitu.api.core.constants.ContentTypeEnum;
import cn.linrui.chitu.api.core.utils.HttpUtils;
import com.google.common.base.CaseFormat;
import org.apache.commons.collections4.MapUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * XjApiParam
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class XjBaseApiParam extends ApiParam {

    /**
     * 需要设定charset, 中文如何处理？
     * @return
     */
    @Override
    public ContentTypeEnum getContentType() {
        return ContentTypeEnum.APPLICATION_FORM_URLENCODED;
    }

    @Override
    protected String getFieldKey(Field field) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field.getName());
    }

    /**
     * url超长，走request body
     *  content-type: application/x-www-form-urlencoded
     *
     * @param paramMap
     * @return
     */
    @Override
    public String buildRequestBodyString(Map<String, Object> paramMap) {
        if (MapUtils.isEmpty(paramMap)) {
            return null;
        }

        try {
            return HttpUtils.encodeObjectParams(paramMap);
        } catch (Exception e) {

        }

        return null;
    }
}
