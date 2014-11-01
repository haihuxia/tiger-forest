package com.xhh.demo.http.load;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

/**
 * 为 JavaClass 劫持 java.lang.System 提供支持
 * 除了 out 和 err 外，其余的都直接转发给 System 处理
 *
 * Created by tiger on 14/11/1.
 */
public class HackSystem {

    public static final InputStream IN = System.in;

    private static ByteArrayOutputStream BUFFER = new ByteArrayOutputStream();

    public static final PrintStream OUT = new PrintStream(BUFFER);

    public static final PrintStream ERR = OUT;

    public static String getBufferString() {
        return BUFFER.toString();
    }

    public static void clearBuffer() {
        BUFFER.reset();
    }

    public static void setSecurityManager(final SecurityManager s) {
        System.setSecurityManager(s);
    }

    public static SecurityManager getSecurityManager() {
        return System.getSecurityManager();
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public static int identityHashCode(Object o) {
        return System.identityHashCode(o);
    }

}
