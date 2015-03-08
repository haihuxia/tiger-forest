package com.xhh.demo.http.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 具体主题（ConcreteSubject）角色
 *
 * <p>保存对具体观察者对象有用的内部状态；在这种内部状态改变时给其观察者发出一个通知；具体主题角色又叫作具体被观察者角色
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/6 上午9:09
 * @since 1.6
 */
public class ConcreteSubject implements Subject {

    /**
     * 增加一个观察者
     *
     * @param observer 观察者
     */
    @Override
    public void attach(Observer observer) {
        observersVector.addElement(observer);
    }

    /**
     * 删除一个观察者
     *
     * @param observer 观察者
     */
    @Override
    public void detach(Observer observer) {
        observersVector.removeElement(observer);
    }

    /**
     * 通知观察者
     */
    @Override
    public void notifyObservers() {
        Enumeration enumeration = observers();
        while (enumeration.hasMoreElements()) {
            ((Observer)enumeration.nextElement()).update();
        }
    }

    /**
     * 数据转换
     *
     * @return Enumeration
     */
    public Enumeration observers() {
        return ((Vector)observersVector.clone()).elements();
    }

    /** 存储观察者 */
    private Vector<Observer> observersVector = new Vector<Observer>();
}
