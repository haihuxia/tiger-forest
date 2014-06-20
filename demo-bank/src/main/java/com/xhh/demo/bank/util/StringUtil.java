package com.xhh.demo.bank.util;

/**
 * Created by tiger on 6/19/14.
 */
public class StringUtil {

    public static String replaceString(String oldString, String... arg) {
        for(String replaceStr:arg){
            oldString = oldString.replaceFirst("#", replaceStr);

        }
        return oldString;

    }
}
