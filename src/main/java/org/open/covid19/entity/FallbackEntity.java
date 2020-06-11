package org.open.covid19.entity;

import lombok.Data;

/**
 * 错误信息
 * @author wuchao
 */
@Data
public class FallbackEntity {
    private String type;
    private String typeDesc;
    private String message;
}
