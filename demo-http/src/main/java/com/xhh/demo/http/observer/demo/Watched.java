package com.xhh.demo.http.observer.demo;

import java.util.Observable;

/**
 * 被观察者
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/6 上午10:00
 * @since 1.6
 */
public class Watched extends Observable {

    /** 数据 */
    private String data = "";

    /**
     * 接收数据
     *
     * @return String
     */
    public String retrieveData() {
        return data;
    }

    /**
     * 数据改变
     *
     * @param data 数据
     */
    public void changeData(String data) {
        if (!this.data.equals(data)) {
            this.data = data;
            setChanged();
        }
        // 通知
        notifyObservers();
    }
}
