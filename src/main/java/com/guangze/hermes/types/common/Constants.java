package com.guangze.hermes.types.common;

public class Constants {
    public static final String NULL = "NULL";

    public Constants() {
    }

    public static enum Role {
        SYSTEM("system"),
        USER("user"),
        ASSISTANT("assistant");

        private String code;

        private Role(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }
    }
}
