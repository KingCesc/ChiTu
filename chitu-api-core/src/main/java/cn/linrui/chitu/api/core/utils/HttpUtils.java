package cn.linrui.chitu.api.core.utils;

import cn.linrui.chitu.api.core.constants.HttpMethodEnum;
import cn.linrui.chitu.api.core.executor.model.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.StringJoiner;

/**
 * HttpUtils
 *
 * @author nick.zw
 * @since 2024/6/20
 */
public class HttpUtils {

    public static <T> boolean isNotGetReq(HttpRequest<T> request) {
        return HttpMethodEnum.GET != request.getRequestMethod();
    }

    public static String encodeParams(Map<String, String> params) throws UnsupportedEncodingException {
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sj.add(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return sj.toString();
    }

    public static String encodeObjectParams(Map<String, Object> params) throws UnsupportedEncodingException {
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sj.add(entry.getKey() + "=" + URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        }
        return sj.toString();
    }
}
