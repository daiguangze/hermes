package com.guangze.hermes.common.exception.asserts;

import com.guangze.hermes.common.exception.ArgException;

import java.text.MessageFormat;

/**
 * @description: 参数异常断言
 * @author: daiguangze
 * @create: 2024-06-17 01:17
 **/
public interface ArgExceptionAssert extends BaseExceptionAssert {

    default ArgException newException(Object... args) {
        String message = MessageFormat.format(this.getMessage(), args);
        return new ArgException(this, message);
    }

}
