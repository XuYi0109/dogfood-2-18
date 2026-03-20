package com.charging.station.common;

public enum ResultCode {

    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    PARAM_ERROR(400, "参数错误"),
    NOT_FOUND(404, "资源不存在"),
    REGION_NOT_FOUND(1001, "区域不存在"),
    STATION_NOT_FOUND(1002, "充电站不存在"),
    ROUTE_NOT_FOUND(1003, "路线信息不存在");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
