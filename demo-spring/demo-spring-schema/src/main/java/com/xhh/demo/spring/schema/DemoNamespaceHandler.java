package com.xhh.demo.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * DateFormat Handler
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/28 上午8:24
 * @see org.springframework.beans.factory.xml.NamespaceHandlerSupport
 * @since 1.6
 */
public class DemoNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());
        registerBeanDefinitionParser("component", new ComponentBeanDefinitionParser());
        registerBeanDefinitionDecoratorForAttribute("cache-name", new JCacheInitializingBeanDefinitionDecorator());
    }
}
