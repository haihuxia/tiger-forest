package com.xhh.demo.bank.common;

import lombok.ToString;

import java.io.Serializable;

/**
 * 银行处理响应对象
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-18
 * @since 1.6
 */
@ToString
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -1766806912941498066L;

    /**
     * 表示调用是否成功
     * 如果为true,则可以调用getResult
     * 如果为false,则调用errorCode来获取出错信息
     */
    private boolean success;

    /**
     * 返回值
     */
    private T result;

    /**
     * 错误码
     */
    private String returnCode;

    /**
     * 错误描述
     */
    private String returnMsg;

    public Response() {
    }

    public Response(T result) {
        this.success = true;
        this.result = result;
    }

    public Response(String returnCode, String returnMsg) {
        this.success = false;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public Response(String returnCode, String returnMsg,T result) {
        this.success = false;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        success = true;
        this.result = result;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.success = false;
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setErrorMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

}
