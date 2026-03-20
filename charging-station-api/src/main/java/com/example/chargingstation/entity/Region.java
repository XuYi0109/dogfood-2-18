package com.example.chargingstation.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Region {
    private Long id;
    private String name;
    private String code;
    private String parentCode;
    private Integer level;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
