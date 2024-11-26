package cn.linrui.chitu.api.core.executor.httpclient;

import cn.linrui.chitu.api.core.ChiTuRestApiException;
import cn.linrui.chitu.api.core.configuration.GlobalConfig;
import cn.linrui.chitu.api.core.constants.ContentTypeEnum;
import cn.linrui.chitu.api.core.constants.HttpConstants;
import cn.linrui.chitu.api.core.constants.ResultCodeEnum;
import cn.linrui.chitu.api.core.executor.HttpRequestExecutor;
import cn.linrui.chitu.api.core.executor.model.HttpRequest;
import cn.linrui.chitu.api.core.executor.model.HttpResponse;
import cn.linrui.chitu.api.core.utils.HttpUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.protocol.BasicHttpContext;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpClientRequestExecutor implements HttpRequestExecutor {

    private static final Logger log = LoggerFactory.getLogger(HttpClientRequestExecutor.class);

    private CloseableHttpClient httpclient;

    private final GlobalConfig globalConfig;

    public HttpClientRequestExecutor(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public void init() {
        httpclient = HttpClientFactory.createHttpClient(globalConfig);
    }

    @Override
    public <T> HttpResponse executeRequest(HttpRequest<T> request) throws IOException, UnsupportedEncodingException, ChiTuRestApiException {
        ClassicRequestBuilder classicRequestBuilder = ClassicRequestBuilder.create(request.getRequestMethod().getMethod())
                .setUri(request.getRequestUrl());

        Map<String, String>  requestParams = request.getRequestParams();
        if (Objects.nonNull(requestParams) && !requestParams.isEmpty()) {
            for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                classicRequestBuilder.addParameter(entry.getKey(), entry.getValue());
            }
        }

        if (HttpUtils.isNotGetReq(request)) {
            ContentTypeEnum contentType = request.getContentType();
            if (ContentTypeEnum.APPLICATION_FORM_URLENCODED == contentType) {
                Map<String, String> requestBodyParams = request.getRequestBodyParams();
                if (MapUtils.isNotEmpty(requestBodyParams)) {
                    String params = HttpUtils.encodeParams(requestBodyParams);
                    classicRequestBuilder.setEntity(new StringEntity(params, ContentType.APPLICATION_FORM_URLENCODED));
                }
            } else if (ContentTypeEnum.APPLICATION_JSON == contentType) {
                String requestBody = request.getRequestBody();
                if (StringUtils.isNoneBlank(requestBody)) {
                    classicRequestBuilder.setEntity(new StringEntity(requestBody, contentType.getCharset()));
                }
            } else {
                throw new ChiTuRestApiException(ResultCodeEnum.CONTENT_TYPE_NOT_SUPPORT);
            }
        }

        Map<String, String> headerMap = request.getHeaderMap();
        if (MapUtils.isNotEmpty(headerMap)) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                classicRequestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        ClassicHttpRequest classicHttpRequest = classicRequestBuilder.build();
        HttpContext httpContext = new BasicHttpContext();
        if (request.getRequestConfig().isUseProxy()) {
            httpContext.setAttribute(HttpConstants.CONTEXT_USER_PROXY, true);
        }

        return execute(classicHttpRequest, httpContext);
    }

    private HttpResponse execute(ClassicHttpRequest httpRequest, HttpContext httpContext) throws IOException {
        long start = System.currentTimeMillis();
        return httpclient.execute(httpRequest, httpContext, response -> {
            String responseStr = EntityUtils.toString(response.getEntity());
            HttpResponse apiHttpResponse = new HttpResponse();
            apiHttpResponse.setData(responseStr);

            Header[] responseHeaders = response.getHeaders();
            if (Objects.nonNull(responseHeaders)) {
                Map<String, String> headerMap = new HashMap<>(responseHeaders.length);
                for (Header responseHeader : responseHeaders) {
                    headerMap.put(responseHeader.getName(), responseHeader.getValue());
                }
                apiHttpResponse.setHeaders(headerMap);
            }
            apiHttpResponse.setCost(System.currentTimeMillis() - start);
            return apiHttpResponse;
        });
    }

}
