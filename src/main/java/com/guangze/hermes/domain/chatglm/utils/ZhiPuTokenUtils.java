package com.guangze.hermes.domain.chatglm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ZhiPuTokenUtils {

    private static final long EXPIREMILLS = 30 *60 * 1000l;

    // token缓存
    public  static Cache<String,String> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(EXPIREMILLS - (60 * 1000L), TimeUnit.MILLISECONDS)
            .build();

    public static String getToken(String apiKey, String apiSecret){
        String token = cache.getIfPresent(apiKey);
        if (!StringUtils.isEmpty(token)) return token;

        Algorithm algorithm = Algorithm.HMAC256(apiSecret);
        Map<String, Object> payload = new HashMap<>();
        payload.put("api_key",apiKey);
        payload.put("exp",System.currentTimeMillis() + EXPIREMILLS);
        payload.put("timestamp", Calendar.getInstance().getTimeInMillis());

        Map<String,Object> header = new HashMap<>();
        header.put("alg","HS256");
        header.put("sign_type","SIGN");
        token = JWT.create().withPayload(payload).withHeader(header).sign(algorithm);
        cache.put(apiKey,token);
        return token;
    }
}
