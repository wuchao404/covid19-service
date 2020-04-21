package org.open.covid19.service.impl;

import org.open.covid19.entity.UserEntity;
import org.open.covid19.mapper.UserMapper;
import org.open.covid19.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    public UserMapper userMapper;
    @Override
    public UserEntity getUser(String username) {
        return userMapper.getUser(username);
    }
}
