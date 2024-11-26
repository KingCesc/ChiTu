package cn.linrui.chitu.api.core.executor;



import cn.linrui.chitu.api.core.ChiTuRestApiException;
import cn.linrui.chitu.api.core.executor.model.HttpRequest;
import cn.linrui.chitu.api.core.executor.model.HttpResponse;

import java.io.IOException;

public interface HttpRequestExecutor {

    <T> HttpResponse executeRequest(HttpRequest<T> request) throws IOException, ChiTuRestApiException;

}
