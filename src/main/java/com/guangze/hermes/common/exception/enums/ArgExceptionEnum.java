package com.guangze.hermes.common.exception.enums;

import com.guangze.hermes.common.exception.asserts.ArgExceptionAssert;

/**
 * @description: 参数异常信息枚举类
 * @author: daiguangze
 * @create: 2024-06-17 01:42
 **/
public enum ArgExceptionEnum implements ArgExceptionAssert {
    NULL(10001, "参数为null！参数名：{0}"),
    ALL_NULL(10002, "参数全为null！"),
    NOT_IN(10003, "参数取值异常！参数->{0}, 期望->{1}"),
    BLANK(10004, "参数为null或空串！参数名：{0}"),
    LESS_EQUAL_ZERO(10005, "参数小于等于0！参数名：{0}"),
    LESS_ZERO(10006, "参数小于等于0！参数名：{0}"),
    OUT_OF_RANGE(10007, "参数超出范围！参数：{0}，值：{1}，期望：{2}"),
    FORMAT_ERROR(10008, "参数格式错误！参数：{0}，值：{1}，期望格式：{2}"),
    NOT_MATCH_REGEX(10009, "参数不匹配正则表达式！值：{0}，参数：{1}"),
    NOT_MATCH_REGEX_FORMAT(10010, "参数不匹配正则表达式！值：{0}，参数：{1}，格式：{2}"),
    NOT_MATCH_PREFIX(10011, "参数前缀不匹配！值：{0}，前缀：{1}"),
    NOT_MATCH_PREFIX_SUFFIX(10012, "参数前后缀不匹配！值: {0}，前缀：{1}，后缀：{2}"),
    COLLECTION_EMPTY(10013, "参数集合为空！参数->{0}"),
    ;

    private int code;

    private String message;

    ArgExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
