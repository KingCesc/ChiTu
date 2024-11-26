package cn.linrui.chitu.api.core;


import cn.linrui.chitu.api.core.executor.model.HttpRequest;

public interface RequestHandler {

    <T> void handler(HttpRequest<T> httpRequest);

}
