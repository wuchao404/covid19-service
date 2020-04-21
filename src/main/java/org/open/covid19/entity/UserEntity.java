package org.open.covid19.entity;

import lombok.Data;

@Data
public class UserEntity {
    private String userId;
    private String username;
    private String name;
    private String createTime;
}