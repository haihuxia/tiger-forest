package com.xhh.demo.http.exception;

/**
 * Assert
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-17
 * @since 1.6
 */
public class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void main(String[] args) {
        String path = null;
        Assert.notNull(path, "Path must not be null");
    }

}
