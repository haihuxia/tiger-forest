package com.xhh.demo.http.load;

/**
 * 为多次载入执行类而加入的加载器
 *
 * <p>把 defineClass 方法开放出来，只有外部显式调用的时候才会使用到 loadByte 方法
 * 由虚拟机调用时，仍然按照原有的双亲委派规则使用 loadClass 方法执行加载
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-11-1 下午4:42
 * @since 1.6
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
