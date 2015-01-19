package com.xhh.demo.http.practice.aop;

/**
 * AOPFactory
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-9
 * @since 1.6
 */
public class AOPFactory {

    private static Object getClassInstance(String clzName) {
        Object obj = null;
        try {
            Class cls = Class.forName(clzName);
            obj = cls.newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ClassNotFoundException:" + cnfe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Object getAOPProxyedObject(String clzName) {
        Object proxy = null;
        MyHandler handler = new MyHandler();
        Object obj = getClassInstance(clzName);
        if (obj != null) {
            proxy = handler.bind(obj);
        } else {
            System.out.println("Can't get the proxyobj");
            //throw
        }
        return proxy;
    }
}
