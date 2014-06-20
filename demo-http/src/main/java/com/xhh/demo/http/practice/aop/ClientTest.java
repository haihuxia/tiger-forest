package com.xhh.demo.http.practice.aop;

/**
 * Created by tiger on 2014/4/9.
 */
public class ClientTest {

    public static void main(String[] args) {
        StudentInfoService studentInfo = (StudentInfoService) AOPFactory.getAOPProxyedObject("com.xhh.demo.http.practice.aop.StudentInfoServiceImpl");
        studentInfo.findInfo("阿飞");
    }
}
