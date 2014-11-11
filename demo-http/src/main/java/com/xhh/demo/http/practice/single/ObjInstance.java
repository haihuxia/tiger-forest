package com.xhh.demo.http.practice.single;

/**
 * 使用双重锁判定可以大幅降低锁的征用
 *
 * Created by tiger on 14-11-11.
 */
public class ObjInstance {

    //单例
    private static ObjInstance objInstance;

    private ObjInstance() {}

    public static ObjInstance getObjInstance() {
        if (null == objInstance) { // 无锁判定
            synchronized (ObjInstance.class) {
                if (null == objInstance) { // 加锁判定
                    objInstance = new ObjInstance();
                }
            }
        }
        return objInstance;
    }

}
