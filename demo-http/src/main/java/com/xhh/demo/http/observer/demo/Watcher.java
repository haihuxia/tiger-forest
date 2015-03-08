package com.xhh.demo.http.observer.demo;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/6 上午10:04
 * @since 1.6
 */
public class Watcher implements Observer {

    /**
     * 添加观察者
     *
     * @param watched 被观察者
     */
    public Watcher(Watched watched) {
        watched.addObserver(this);
    }

    /**
     * 数据刷新
     *
     * @param observable 被观察者
     * @param arg Object
     */
    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("Data has been changed to: '" + ((Watched)observable).retrieveData() + "'");
    }

}
