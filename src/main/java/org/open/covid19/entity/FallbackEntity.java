package org.open.covid19.entity;

import lombok.Data;
import org.open.covid19.mapper.ApiFallbackMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 错误信息
 * @author wuchao
 */
@Data
public class FallbackEntity {
    @Autowired
    ApiFallbackMapper apiFallbackMapper;

    private String type;
    private String typeDesc;
    private String message;
    /**
     * 入库
     */
    public void insertIntoDb(){
    }
}
