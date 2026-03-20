package com.charging.station.common;

public final class Constants {

    private Constants() {
    }

    public static final Integer STATION_STATUS_NORMAL = 1;
    public static final Integer STATION_STATUS_MAINTENANCE = 0;
    public static final Integer STATION_STATUS_OFFLINE = 2;

    public static final Integer PORT_STATUS_AVAILABLE = 0;
    public static final Integer PORT_STATUS_OCCUPIED = 1;
    public static final Integer PORT_STATUS_FAULT = 2;

    public static final String TRAFFIC_SMOOTH = "畅通";
    public static final String TRAFFIC_SLOW = "缓行";
    public static final String TRAFFIC_CONGESTED = "拥堵";

    public static String getPortStatusDesc(Integer status) {
        if (status == null) {
            return "未知";
        }
        switch (status) {
            case 0:
                return "空闲";
            case 1:
                return "占用中";
            case 2:
                return "故障";
            default:
                return "未知";
        }
    }

    public static String getStationStatusDesc(Integer status) {
        if (status == null) {
            return "未知";
        }
        switch (status) {
            case 0:
                return "维护中";
            case 1:
                return "正常运营";
            case 2:
                return "离线";
            default:
                return "未知";
        }
    }
}
