package com.guangze.hermes.infrastructure.auth;


import com.guangze.hermes.infrastructure.auth.persistence.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class AuthTest {


    @Resource
    UserDao userDao;
    @Test
    public void test(){
        System.out.println(userDao.count());
    }
}
