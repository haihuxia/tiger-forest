package com.xhh.demo.spring.boot.hystrix;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;

/**
 * PsersonService
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/7/13 下午4:26
 */
public class PsersonService {

    public static String sayHello(final String name) {
        return new PersonCommand(name).execute();
    }

    public static Future<String> sayHelloAsync(final String name) {
        return new PersonCommand(name).queue();
    }

    public static Observable<String> sayHelloObservable(final String name) {
        return new PersonCommand(name).observe();
    }

    public static void main(String[] args) {
        String s = PsersonService.sayHello("world");
        System.out.println(s);

        Observable<String> egg = PsersonService.sayHelloObservable("egg");
        Observable<String> milk = PsersonService.sayHelloObservable("milk");
        System.out.println();
        System.out.println(egg.toBlocking().single());
        System.out.println(milk.toBlocking().single());

        egg.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });

        milk.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("onNext: " + s);
            }
        });
    }
}
