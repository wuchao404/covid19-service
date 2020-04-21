package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.open.covid19.entity.UserEntity;

@Mapper
public interface UserMapper {
    UserEntity getUser(String username);
}
