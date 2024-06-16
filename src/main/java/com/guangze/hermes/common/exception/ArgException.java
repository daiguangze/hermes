package com.guangze.hermes.common.exception;

/**
 * @description: 参数异常
 * @author: daiguangze
 * @create: 2024-06-17 01:39
 **/
public class ArgException extends BaseException{
    public ArgException(RespInfo respInfo, String message) {
        super(respInfo, message);
    }

    public ArgException(RespInfo respInfo, String message, Throwable cause) {
        super(respInfo, message, cause);
    }

}
