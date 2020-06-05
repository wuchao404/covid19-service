package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.covid19.entity.FallbackEntity;

import java.util.List;

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

    /**
     * 批量插入错误信息
     * @param entities
     * @return
     */
    int batchInsertErrorList(List<FallbackEntity> entities);
}
