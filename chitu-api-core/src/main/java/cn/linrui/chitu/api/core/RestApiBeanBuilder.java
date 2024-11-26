package cn.linrui.chitu.api.core;


import java.lang.reflect.Proxy;

public class RestApiBeanBuilder<T> {

    /** 缓存引用 **/
    private transient volatile T ref;

    private Class<?> interfaceClass;

    private RestApiExecutor restApiExecutor;

    @SuppressWarnings("unchecked")
    public T get() {
        if (ref == null) {
            synchronized (this) {
                if (ref == null) {
                    RestApiInvocationHandler invocationHandler = new RestApiInvocationHandler(restApiExecutor);
                    ref = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, invocationHandler);
                }
            }
        }
        return ref;
    }

    public void setTiktokApiExecutor(RestApiExecutor restApiExecutor) {
        this.restApiExecutor = restApiExecutor;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public RestApiExecutor getRestApiExecutor() {
        return restApiExecutor;
    }

    public void setRestApiExecutor(RestApiExecutor restApiExecutor) {
        this.restApiExecutor = restApiExecutor;
    }
}
