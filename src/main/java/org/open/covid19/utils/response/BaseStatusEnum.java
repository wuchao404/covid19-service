package org.open.covid19.utils.response;

public enum BaseStatusEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    FAILURE(400, "系统错误");
    private int status;
    private String message;

    private BaseStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
