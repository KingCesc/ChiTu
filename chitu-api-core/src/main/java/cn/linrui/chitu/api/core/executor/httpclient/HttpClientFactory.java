package cn.linrui.chitu.api.core.executor.httpclient;

import cn.linrui.chitu.api.core.configuration.GlobalConfig;
import cn.linrui.chitu.api.core.constants.HttpConstants;
import cn.linrui.chitu.api.core.utils.SdkConfigUtil;
import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.auth.CredentialsProviderBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.TimeValue;

import javax.net.ssl.SSLContext;
import java.util.concurrent.TimeUnit;

public class HttpClientFactory {

    public static CloseableHttpClient createHttpClient(GlobalConfig globalConfig) {
        SSLContext sslcontext = SSLContexts.createSystemDefault();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry,
                PoolConcurrencyPolicy.STRICT,
                PoolReusePolicy.LIFO,
                TimeValue.ofMinutes(5),
                null);
        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .build();
        connManager.setDefaultSocketConfig(socketConfig);
        connManager.setMaxTotal(SdkConfigUtil.getMaxTotal(globalConfig));
        connManager.setDefaultMaxPerRoute(SdkConfigUtil.getMaxTotal(globalConfig));

        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setConnectTimeout(SdkConfigUtil.getConnectTimeout(globalConfig), TimeUnit.SECONDS)
                .setValidateAfterInactivity(TimeValue.ofSeconds(HttpConstants.VALIDATE_AFTER_INACTIVITY))
                .build();
        connManager.setDefaultConnectionConfig(connectionConfig);

        RequestConfig requestConfig = RequestConfig.custom()
                .setResponseTimeout(SdkConfigUtil.getReadTimeout(globalConfig), TimeUnit.SECONDS)
                .setConnectionKeepAlive(TimeValue.ofSeconds(HttpConstants.KEEP_ALIVE_TIME))
                .setConnectionRequestTimeout(SdkConfigUtil.getConnectTimeout(globalConfig), TimeUnit.SECONDS)
                .build();

        HttpClientBuilder httpClientBuilder = HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig);

        if (globalConfig.isUseProxy()) {
            HttpHost proxy = new HttpHost(globalConfig.getProxyHostName(), globalConfig.getProxyPort());
            httpClientBuilder.setRoutePlanner(new DefaultRouterPlanner(proxy));
            CredentialsProvider credentialsProvider = CredentialsProviderBuilder.create()
                    .add(proxy, globalConfig.getProxyUserName(), globalConfig.getProxyPassword().toCharArray())
                    .build();
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        }

        return httpClientBuilder.build();
    }
}
