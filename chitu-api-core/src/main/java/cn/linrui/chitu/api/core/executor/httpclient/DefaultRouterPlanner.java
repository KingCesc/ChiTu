package cn.linrui.chitu.api.core.executor.httpclient;

import cn.linrui.chitu.api.core.constants.HttpConstants;
import org.apache.hc.client5.http.HttpRoute;
import org.apache.hc.client5.http.impl.DefaultSchemePortResolver;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.client5.http.routing.HttpRoutePlanner;
import org.apache.hc.client5.http.routing.RoutingSupport;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.util.Objects;

public class DefaultRouterPlanner implements HttpRoutePlanner {

    private HttpHost proxy;

    private DefaultProxyRoutePlanner planner;

    public DefaultRouterPlanner(HttpHost proxy) {
        this.proxy = proxy;
        if (Objects.nonNull(proxy)) {
            this.planner = new DefaultProxyRoutePlanner(proxy);
        }
    }
    @Override
    public HttpRoute determineRoute(HttpHost target, HttpContext context) throws HttpException {
        boolean userProxy = (Boolean)context.getAttribute(HttpConstants.CONTEXT_USER_PROXY);
        if (!userProxy || Objects.isNull(planner)) {
            return new HttpRoute(RoutingSupport.normalize(target, DefaultSchemePortResolver.INSTANCE), null, true);
        }

        return planner.determineRoute(target, context);
    }

    public HttpHost getProxy() {
        return proxy;
    }

    public void setProxy(HttpHost proxy) {
        this.proxy = proxy;
    }

    public DefaultProxyRoutePlanner getPlanner() {
        return planner;
    }

    public void setPlanner(DefaultProxyRoutePlanner planner) {
        this.planner = planner;
    }
}
