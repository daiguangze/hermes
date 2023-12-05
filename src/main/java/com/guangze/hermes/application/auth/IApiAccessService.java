package com.guangze.hermes.application.auth;

import com.guangze.hermes.application.auth.command.AuthorizeCommand;
import com.guangze.hermes.common.result.Result;

import java.util.Map;

public interface IApiAccessService {
    Result<Map<String, String>> authorize(AuthorizeCommand command);
}
