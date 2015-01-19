package com.xhh.demo.http.practice.aop;

/**
 * ClientTest
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-9
 * @since 1.6
 */
public class ClientTest {

    public static void main(String[] args) {
        StudentInfoService studentInfo = (StudentInfoService) AOPFactory.getAOPProxyedObject("com.xhh.demo.http.practice.aop.StudentInfoServiceImpl");
        studentInfo.findInfo("阿飞");
    }
}
