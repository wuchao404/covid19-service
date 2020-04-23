package org.open.covid19.utils;

import lombok.Data;
import org.open.covid19.utils.response.BaseStatusEnum;

/**
 * 响应
 * @author wuchao
 */
@Data
public class BaseResponse {
    private int status;
    private String message;
    // 响应内容
    private Object data;

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static BaseResponse SUCCESS =
            new BaseResponse(BaseStatusEnum.SUCCESS.getStatus(),BaseStatusEnum.SUCCESS.getMessage());

    public static BaseResponse FAILURE =
            new BaseResponse(BaseStatusEnum.FAILURE.getStatus(),BaseStatusEnum.FAILURE.getMessage());

    /**
     * 成功
     * @param data Object
     * @return BaseResponse
     */
    public static BaseResponse success200(Object data) {
        return new BaseResponse(BaseStatusEnum.SUCCESS.getStatus(),BaseStatusEnum.SUCCESS.getMessage(),data);
    }
}
