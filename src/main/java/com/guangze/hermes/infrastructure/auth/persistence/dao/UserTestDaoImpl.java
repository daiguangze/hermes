package com.guangze.hermes.infrastructure.auth.persistence.dao;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guangze.hermes.infrastructure.auth.persistence.UserTestDO;
import com.guangze.hermes.infrastructure.auth.persistence.mapper.UserTestMapper;
import org.springframework.stereotype.Component;

@Component
public class UserTestDaoImpl extends ServiceImpl<UserTestMapper, UserTestDO> implements UserTestDao {

}
