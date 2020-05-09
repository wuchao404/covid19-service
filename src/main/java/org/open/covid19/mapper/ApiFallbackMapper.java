package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 接口异常信息存库
 * @author wuchao
 */
@Mapper
public interface ApiFallbackMapper {
    /**
     * 插入接口异常信息
     * @param type
     * @param message
     * @return
     */
    int insertErrorMessage(@Param("type") String type, @Param("message") String message);
}
