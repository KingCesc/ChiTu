package cn.linrui.chitu.api.core;

import cn.linrui.chitu.api.core.constants.ContentTypeEnum;
import com.google.common.base.CaseFormat;
import org.apache.commons.collections4.MapUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ApiParam {

    private SdkParam sdkParam = new SdkParam();

    protected String accessToken;

    public ContentTypeEnum getContentType() {
        return ContentTypeEnum.APPLICATION_JSON;
    }

    public abstract String buildRequestBodyString(Map<String, Object> paramMap);

    public String getRequestBodyString() {
        Map<String, Object> paramBodyMap = new HashMap<>();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        Arrays.stream(declaredFields)
                .forEach(field -> getRequestBodyParams(field, paramBodyMap));
        if (MapUtils.isEmpty(paramBodyMap)) {
            return null;
        }
        return buildRequestBodyString(paramBodyMap);
    }

    public Map<String, String> getRequestBodyParams() {
        return getParams(RequestBody.class);
    }

    public Map<String, String> getRequestParams() {
        return getParams(RequestParam.class);
    }

    public Map<String, String> getPathParams() {
        return getParams(PathVariable.class);
    }

    private <T extends Annotation> Map<String, String> getParams(Class<T> annotationClass) {
        Map<String, String>  paramMap = new HashMap<>();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        Arrays.stream(declaredFields)
                .forEach(field -> getParam(annotationClass, field, paramMap));
        return paramMap;
    }

    private <T extends Annotation> void getRequestBodyParams(Field field, Map<String, Object>  paramMap) {
        field.setAccessible(true);
        String key = getFieldKey(field);
        try {
            Optional<RequestBody> annotation = Optional.ofNullable(field.getAnnotation(RequestBody.class));
            if (!annotation.isPresent()) {
                return;
            }

            paramMap.put(key, field.get(this));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private <T extends Annotation> void getParam(Class<T> annotationClass, Field field, Map<String, String> paramMap) {
        field.setAccessible(true);
        String key = getFieldKey(field);
        String value = null;
        try {
            Optional<T> annotation = Optional.ofNullable(field.getAnnotation(annotationClass));
            if (!annotation.isPresent()) {
                return;
            }

            if (field.get(this) != null) {
                value = getObjectStringValue(field.get(this));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        paramMap.put(key, value);
    }

    protected String getFieldKey(Field field) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_CAMEL, field.getName());
    }

    protected String getObjectStringValue(Object object) {
        String value;
        if (object instanceof List) {
            List<Object> list = (List<Object>) object;
            value = list.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        } else {
            value = object.toString();
        }

        return value;
    }

    public SdkParam getSdkParam() {
        return sdkParam;
    }

    public void setSdkParam(SdkParam sdkParam) {
        this.sdkParam = sdkParam;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
