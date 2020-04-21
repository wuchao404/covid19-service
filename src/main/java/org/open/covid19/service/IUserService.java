package org.open.covid19.service;

import org.open.covid19.entity.UserEntity;

public interface IUserService {
    UserEntity getUser(String username);
}
