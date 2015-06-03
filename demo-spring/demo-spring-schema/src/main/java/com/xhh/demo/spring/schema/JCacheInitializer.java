package com.xhh.demo.spring.schema;

/**
 * JCacheInitializer
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/28 上午10:52
 * @since 1.6
 */
public class JCacheInitializer {

    private String name;

    public JCacheInitializer(String name) {
        this.name = name;
    }

    public void initialize() {
        // lots of JCache API calls to initialize the named cache...
    }
}
