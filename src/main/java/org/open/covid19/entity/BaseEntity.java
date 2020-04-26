package org.open.covid19.entity;

import lombok.Data;

@Data
public class BaseEntity {
    private String createTime;
    private String updateTime;
    private String deleteTime;
    private int isDelete;
}
