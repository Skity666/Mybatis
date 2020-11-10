package com.Skity666.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

/**
 * @ClassName MyInterceptor
 * @Description TODO 自定义拦截器
 * @Author Skity666
 * @Date 2020/11/9 0009 21:02
 * @Version 1.0
 **/
@Intercepts({
        @Signature(type= StatementHandler.class,method="prepare",args= {Connection.class,Integer.class })
})
public class MyInterceptor implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("拦截对象：" + invocation.getTarget());
        System.out.println("拦截方法：" + invocation.getMethod());
        System.out.println("拦截参数：" + invocation.getArgs().length);
        for (Object arg : invocation.getArgs()) {
            System.out.println("参数:" + arg);
        }
        System.out.println("执行SQL前处理");
        Object result = invocation.proceed();
        System.out.println("执行SQL后处理");
        return result;
    }

    public void setProperties(Properties properties) {
        System.out.println("获取配置参数:" + properties.getProperty("pageSize"));
    }
}