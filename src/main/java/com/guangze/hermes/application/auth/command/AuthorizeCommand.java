package com.guangze.hermes.application.auth.command;

import lombok.Data;

@Data
public class AuthorizeCommand {

    private String username;

    private String password;

    public AuthorizeCommand(String username, String password) {

        this.username = username;
        this.password = password;
    }
}
