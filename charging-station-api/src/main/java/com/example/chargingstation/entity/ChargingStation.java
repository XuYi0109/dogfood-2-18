package com.example.chargingstation.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ChargingStation {
    private Long id;
    private String stationCode;
    private String name;
    private String regionCode;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer totalPorts;
    private Integer availablePorts;
    private BigDecimal power;
    private BigDecimal pricePerKwh;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    // 额外字段：距离（用于路程计算）
    private Double distance;
}
