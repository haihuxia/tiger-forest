package com.xhh.demo.spring.schema;

import org.springframework.beans.factory.FactoryBean;

import java.util.List;

/**
 * ComponentFactoryBean
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/28 上午10:36
 * @since 1.6
 */
public class ComponentFactoryBean implements FactoryBean<Component> {

    private Component parent;
    private List<Component> children;

    public void setParent(Component parent) {
        this.parent = parent;
    }

    public void setChildren(List<Component> children) {
        this.children = children;
    }

    @Override
    public Component getObject() throws Exception {
        if (this.children != null && this.children.size() > 0) {
            for (Component child : children) {
                this.parent.addComponent(child);
            }
        }
        return this.parent;
    }

    @Override
    public Class<Component> getObjectType() {
        return Component.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
