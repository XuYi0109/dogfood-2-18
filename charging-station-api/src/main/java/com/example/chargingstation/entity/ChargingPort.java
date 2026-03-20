package com.example.chargingstation.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ChargingPort {
    private Long id;
    private String portCode;
    private Long stationId;
    private String portType;
    private Integer status;
    private String currentOrderNo;
    private LocalDateTime startChargingTime;
    private LocalDateTime expectedEndTime;
    private BigDecimal chargedKwh;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
