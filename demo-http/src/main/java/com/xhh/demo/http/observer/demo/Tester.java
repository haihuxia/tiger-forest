package com.xhh.demo.http.observer.demo;

import java.util.Observer;

/**
 * 观察者模式测试
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/6 上午10:10
 * @since 1.6
 */
public class Tester {

    private static Watched watched;
    private static Observer watcher;

    public static void main(String[] args) {
        watched = new Watched();
        watcher = new Watcher(watched);
        watched.changeData("In C, we create bugs.");
        watched.changeData("In Java, we inherit bugs.");
        watched.changeData("In Java, we inherit bugs.");
        watched.changeData("In Visual Basic, we visualize bugs.");
    }
}
