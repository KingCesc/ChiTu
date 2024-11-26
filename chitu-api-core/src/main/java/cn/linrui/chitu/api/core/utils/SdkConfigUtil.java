package cn.linrui.chitu.api.core.utils;

import cn.linrui.chitu.api.core.SdkParam;
import cn.linrui.chitu.api.core.configuration.ApiConfig;
import cn.linrui.chitu.api.core.configuration.GlobalConfig;
import cn.linrui.chitu.api.core.constants.HttpConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class SdkConfigUtil {

    public static String getApiEndpoint(SdkParam methodParam, ApiConfig appApiConfig, GlobalConfig globalConfig) {
        String globalEndpoint = Optional.ofNullable(globalConfig)
                .map(GlobalConfig::getEndpoint)
                .orElseThrow(()-> new IllegalArgumentException("global endpoint is null"));
        String appConfigEndpoint = Optional.ofNullable(appApiConfig)
                .map(ApiConfig::getEndpoint)
                .orElse(globalEndpoint);
        return Optional.ofNullable(methodParam)
                .map(SdkParam::getEndpoint)
                .map(StringUtils::trimToNull)
                .orElse(appConfigEndpoint);
    }

    public static String getAppKey(GlobalConfig globalConfig, SdkParam methodParam) {
        String globalAppKey = Optional.ofNullable(globalConfig)
                .map(GlobalConfig::getAppKey)
                .orElseThrow(()-> new IllegalArgumentException("global app key is null"));
        return Optional.ofNullable(methodParam)
                .map(SdkParam::getAppKey)
                .map(StringUtils::trimToNull)
                .orElse(globalAppKey);
    }

    public static String getAppSecret(GlobalConfig globalConfig, SdkParam methodParam) {
        String globalAppKey = Optional.ofNullable(globalConfig)
                .map(GlobalConfig::getAppSecret)
                .orElse("");
        return Optional.ofNullable(methodParam)
                .map(SdkParam::getAppSecret)
                .map(StringUtils::trimToNull)
                .orElse(globalAppKey);
    }

    public static Integer getRetryTimes(GlobalConfig globalConfig, ApiConfig appApiConfig, SdkParam methodParam) {
        Integer globalRetryTimes = Optional.ofNullable(globalConfig)
                .map(GlobalConfig::getRetryTimes)
                .orElse(HttpConstants.RETRY_TIMES);
        Integer apiConfigRetryTimes = Optional.ofNullable(appApiConfig)
                .map(ApiConfig::getRetryTimes)
                .orElse(globalRetryTimes);
        return Optional.ofNullable(methodParam)
                .map(SdkParam::getRetryTimes)
                .orElse(apiConfigRetryTimes);
    }

    public static Integer getConnectTimeout(GlobalConfig globalConfig, ApiConfig apiConfig, SdkParam methodParam) {
        Integer globalConnectTimeout = Optional.ofNullable(globalConfig)
                .map(GlobalConfig::getConnectTimeout)
                .orElse(HttpConstants.CONNECT_TIMEOUT_SECONDS);
        Integer apiConnectTimeout = Optional.ofNullable(apiConfig)
                .map(ApiConfig::getConnectTimeout)
                .orElse(globalConnectTimeout);
        return Optional.ofNullable(methodParam)
                .map(SdkParam::getConnectTimeout)
                .orElse(apiConnectTimeout);
    }

    public static Integer getReadTimeout(GlobalConfig globalConfig, ApiConfig apiConfig, SdkParam methodParam) {
        Integer globalReadTimeout = Optional.ofNullable(globalConfig)
                .map(GlobalConfig::getReadTimeout)
                .orElse(HttpConstants.READ_TIMEOUT_SECONDS);
        return Optional.ofNullable(methodParam)
                .map(SdkParam::getReadTimeout)
                .orElseGet(
                        () -> Optional.ofNullable(apiConfig)
                        .map(ApiConfig::getReadTimeout)
                        .orElse(globalReadTimeout)
                );
    }

    public static Boolean isUseProxy(SdkParam sdkParam, ApiConfig apiConfig, GlobalConfig globalConfig) {
        Optional<Boolean> paraUserProxyOpt =  Optional.ofNullable(sdkParam)
                .map(SdkParam::getUseProxy);
        if (paraUserProxyOpt.isPresent()) {
            return paraUserProxyOpt.get();
        }

        Optional<Boolean> apiUserProxyOpt =  Optional.ofNullable(apiConfig)
                .map(ApiConfig::getUseProxy);
        return apiUserProxyOpt.orElseGet(() -> Optional.ofNullable(globalConfig)
                .map(GlobalConfig::isUseProxy)
                .orElse(false));
    }

    public static Integer getMaxTotal(GlobalConfig globalConfig) {
        return Optional.ofNullable(globalConfig)
                .map(GlobalConfig::getMaxTotal)
                .orElse(HttpConstants.CONNECT_POOL_SIZE);
    }

    public static Integer getConnectTimeout(GlobalConfig globalConfig) {
        return Optional.ofNullable(globalConfig)
                .map(GlobalConfig::getConnectTimeout)
                .orElse(HttpConstants.CONNECT_REQUEST_TIMEOUT);
    }

    public static Integer getReadTimeout(GlobalConfig globalConfig) {
        return Optional.ofNullable(globalConfig)
                .map(GlobalConfig::getReadTimeout)
                .orElse(HttpConstants.READ_TIMEOUT_SECONDS);
    }
}
