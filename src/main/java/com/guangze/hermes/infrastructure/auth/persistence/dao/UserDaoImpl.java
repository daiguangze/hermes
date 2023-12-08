package com.guangze.hermes.infrastructure.auth.persistence.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guangze.hermes.infrastructure.auth.persistence.UserDO;
import com.guangze.hermes.infrastructure.auth.persistence.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl extends ServiceImpl<UserMapper, UserDO> implements UserDao {
}
