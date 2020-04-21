package org.open.covid19.mapper;

import org.open.covid19.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    UserEntity getUser(String username);
}
