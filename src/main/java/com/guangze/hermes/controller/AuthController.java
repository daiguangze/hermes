package com.guangze.hermes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/api/verify")
    public ResponseEntity<String> verify(String token){
        logger.info("token:{}",token);
        // token验证
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping("/healthz")
    public String healthz(){
        return "ok";
    }
}
