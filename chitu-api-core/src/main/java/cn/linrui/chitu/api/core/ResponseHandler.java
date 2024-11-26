package cn.linrui.chitu.api.core;


import cn.linrui.chitu.api.core.executor.model.HttpRequest;
import cn.linrui.chitu.api.core.executor.model.HttpResponse;

public interface ResponseHandler {

    <T> T parseResponse(HttpResponse response, HttpRequest<T> request) throws ChiTuRestApiException;

}
