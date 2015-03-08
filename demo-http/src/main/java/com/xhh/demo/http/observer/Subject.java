package com.xhh.demo.http.observer;

/**
 * 抽象主题（Subject）角色
 *
 * <p>主题角色把所有的观察者对象的引用保存在一个列表里；每个主题都可以有任何数量的观察者。主题提供一个接口可以加上或撤销观察者对象；
 * 主题角色又叫做抽象被观察者(Observable)角色
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/6 上午9:05
 * @since 1.6
 */
public interface Subject {

    /**
     * 增加一个观察者
     *
     * @param observer 观察者
     */
    void attach(Observer observer);

    /**
     * 删除一个观察者
     *
     * @param observer 观察者
     */
    void detach(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObservers();
}
