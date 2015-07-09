package com.xhh.demo.http.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * MyHandler
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-9
 * @since 1.6
 */
public class MyHandler implements InvocationHandler {

    private Object proxyObj;

    public Object bind(Object obj) {
        this.proxyObj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            //请在这里插入代码，在方法前调用("调用log日志方法" + method.getName());
            System.out.println("AOP 记录日志");
            result = method.invoke(proxyObj, args); //原方法
            System.out.println("AOP 业务完成后处理");
            //请在这里插入代码，方法后调用
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
