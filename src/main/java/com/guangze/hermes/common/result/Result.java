package com.guangze.hermes.common.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -12512623623L;

    private static final int SUCCESS_CODE = 200;

    private static final int DEFAULT_ERROR_CODE = 500;

    private static final String SUCCESS = "SUCCESS";

    private static final String FAILED = "FAILED";

    private static final Result<Object> EMPTY = Result.success(null);

    private int code;

    private T data;
    private String message;

    private String debug;


    public Result() {
    }

    public boolean isSuccess() {
        return code == SUCCESS_CODE;
    }

    public static <T> Result<T> empty() {
        return (Result<T>) EMPTY;
    }

    public static Result<String> success() {
        return success("OK");
    }

    public static <T> Result<T> success(T t) {
        return Result.newBuilder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .data(t)
                .build();
    }

    public static <T> Result<T> error(Result r) {
        return error(r.getCode(), r.getMessage());
    }


    public static <T> Result<T> error(int code, String message) {
        return Result.newBuilder()
                .code(code)
                .message(message)
                .build();
    }

    public static <T> Result<T> error(String message) {
        return Result.newBuilder()
                .code(DEFAULT_ERROR_CODE)
                .message(message)
                .build();
    }

    public static <T> Result<T> error(int code, String msgPattern, Object... args) {
        return error(code, String.format(msgPattern, args));
    }

    public static <T> Result<T> error(String msgPattern, Object... args) {
        return error(String.format(msgPattern, args));
    }

    public static <T> Result<T> error(String message, DebugInfo debugInfo) {
        return Result.newBuilder()
                .code(DEFAULT_ERROR_CODE)
                .message(message)
                .debug(debugInfo.toString())
                .build();
    }


    private Result(Builder<T> builder) {
        setCode(builder.code);
        setData(builder.data);
        setMessage(builder.message);
        setDebug(builder.debug);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder<T> {
        private int code;
        private T data;
        private String message;
        private String debug;

        private Builder() {
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder debug(String debug) {
            this.debug = debug;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }
}
