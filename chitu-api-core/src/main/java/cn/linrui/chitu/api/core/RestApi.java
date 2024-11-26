package cn.linrui.chitu.api.core;


import cn.linrui.chitu.api.core.constants.HttpMethodEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestApi {

    String apiCode();

    String path();

    HttpMethodEnum method() default HttpMethodEnum.GET;

    boolean needSignature() default true;

}
