package com.guangze.hermes.application.auth.impl;

import com.guangze.hermes.application.auth.IApiAccessService;
import com.guangze.hermes.application.auth.command.AuthorizeCommand;
import com.guangze.hermes.common.result.Result;
import com.guangze.hermes.domain.auth.JwtUtil;
import com.guangze.hermes.domain.auth.realm.JwtRealm;

import java.util.HashMap;
import java.util.Map;

public class ApiAccessServiceImpl implements IApiAccessService {
    @Override
    public Result<Map<String, String>> authorize(AuthorizeCommand command) {
        Map<String, String> res = new HashMap<>();
        // todo 账号密码验证
        // 生成token
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username", command.getUsername());
        String token = jwtUtil.encode(command.getUsername(), 5 * 60 * 1000, chaim);
        res.put("msg", "授权成功");
        res.put("token", token);
        return Result.success(res);
    }
}
