package com.xhh.demo.http.practice.aop;

/**
 * Created by 夏海虎 on 2014/4/9.
 */
public class StudentInfoServiceImpl implements StudentInfoService {

    @Override
    public void findInfo(String studentName) {
        System.out.println("你目前输入的名字是:" + studentName);
    }
}
