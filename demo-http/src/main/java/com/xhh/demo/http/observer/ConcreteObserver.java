package com.xhh.demo.http.observer;

/**
 * 具体观察者（ConcreteObserver）角色
 *
 * <p>保存一个指向具体主题对象的引用；和一个与主题的状态相符的状态。
 * 具体观察者角色实现抽象观察者角色所要求的更新自己的接口，以便使本身的状态与主题的状态自恰
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/6 上午9:10
 * @since 1.6
 */
public class ConcreteObserver implements Observer {

    /**
     * 刷新
     */
    @Override
    public void update() {

    }
}
