package com.guangze.hermes.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiAccessController {


    private Logger logger = LoggerFactory.getLogger(ApiAccessController.class);

    @RequestMapping("/authorize")
    public ResponseEntity<Map<String, String>> authorize(String username, String password) {
        // 验证\
        return null;
    }

    @RequestMapping("/verify")
    public ResponseEntity<String> verify(String token) {
        logger.info("token:{}", token);
        // token验证
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping("/healthz")
    public String healthz() {
        return "ok";
    }
}
