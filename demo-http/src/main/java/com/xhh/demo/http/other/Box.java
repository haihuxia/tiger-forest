package com.xhh.demo.http.other;

import lombok.Getter;

/**
 * Box builder
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/5/6 下午3:44
 * @since 1.6
 */
public class Box {

    String color;
    String name;

    public Box(Builder builder) {
        this.color = builder.getColor();
        this.name = builder.getName();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Builder() {}

        public Box build() {
            return new Box(this);
        }

        @Getter
        private String color;

        @Getter
        private String name;

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

    }
}
