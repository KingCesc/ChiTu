package cn.linrui.chitu.api.core;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

public class RestApiInvocationHandler implements InvocationHandler {

    private final RestApiExecutor delegate;

    public RestApiInvocationHandler(RestApiExecutor delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }

        if (isDefaultMethod(method)) {
            invokeDefaultMethod(proxy, method, args);
        }

        RestApi restApi = method.getAnnotation(RestApi.class);
        if (Objects.isNull(restApi)) {
            throw new UnsupportedOperationException(method.getName());
        }

        Class<?> returnType = method.getReturnType();

        ApiParam apiParam = null;
        if (!Objects.isNull(args)) {
            apiParam = (ApiParam) args[0];
        }

        return delegate.executeRequest(restApi, apiParam, returnType);
    }

    private void invokeDefaultMethod(Object proxy, Method method, Object[] args)
            throws Throwable {
        final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        constructor
                .newInstance(declaringClass,
                        MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
                .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
    }

    private boolean isDefaultMethod(Method method) {
        return (method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC
                && method.getDeclaringClass().isInterface();
    }
}
