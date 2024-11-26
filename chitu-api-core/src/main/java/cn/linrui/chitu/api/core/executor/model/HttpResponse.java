package cn.linrui.chitu.api.core.executor.model;


import java.util.Map;

public class HttpResponse {

    private String data;

    private Map<String, String> headers;

    private long cost;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
