package com.guangze.hermes.common.exception;

/**
 * @program: hermes
 * @description: 基础异常类
 * @author: daiguangze
 * @create: 2024-06-17 01:11
 **/
public class BaseException extends RuntimeException {

    private RespInfo respInfo;

    public BaseException(RespInfo respInfo, String message) {
        super(message);
        this.respInfo = respInfo;
    }

    public BaseException(RespInfo respInfo, String message, Throwable cause) {
        super(message, cause);
        this.respInfo = respInfo;
    }

    public RespInfo getRespInfo() {
        return respInfo;
    }
}
