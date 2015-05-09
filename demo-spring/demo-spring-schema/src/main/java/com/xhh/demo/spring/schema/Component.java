package com.xhh.demo.spring.schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Component
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/28 上午10:35
 * @since 1.6
 */
public class Component {

    private String name;
    private List<Component> components = new ArrayList<Component>();

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
