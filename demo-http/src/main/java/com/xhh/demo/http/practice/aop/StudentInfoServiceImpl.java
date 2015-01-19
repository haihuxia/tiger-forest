package com.xhh.demo.http.practice.aop;

/**
 * StudentInfoServiceImpl
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-9
 * @since 1.6
 */
public class StudentInfoServiceImpl implements StudentInfoService {

    @Override
    public void findInfo(String studentName) {
        System.out.println("你目前输入的名字是:" + studentName);
    }
}
