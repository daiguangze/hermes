package com.guangze.hermes.application.auth.command;

import com.google.common.base.Preconditions;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class AuthorizeCommand {

    private String username;

    private String password;

    public AuthorizeCommand(String username, String password) {
        Preconditions.checkArgument(!StringUtils.isEmpty(username), "[%s] 为空", "username");
        Preconditions.checkArgument(!StringUtils.isEmpty(password), "[%s] 为空", "password");
        this.username = username;
        this.password = password;
    }
}
