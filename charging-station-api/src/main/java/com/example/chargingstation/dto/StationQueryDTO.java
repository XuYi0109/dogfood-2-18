package com.example.chargingstation.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class StationQueryDTO {
    // 区域编码
    private String regionCode;
    // 用户当前经度（可选，用于计算距离）
    private BigDecimal userLongitude;
    // 用户当前纬度（可选，用于计算距离）
    private BigDecimal userLatitude;
    // 状态筛选（可选）
    private Integer status;
    // 排序方式（可选：distance-按距离, available-按可用数量, price-按价格）
    private String sortBy;
    // 页码
    private Integer pageNum = 1;
    // 每页大小
    private Integer pageSize = 10;
}
