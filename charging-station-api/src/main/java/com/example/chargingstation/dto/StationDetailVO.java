package com.example.chargingstation.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class StationDetailVO {
    private Long id;
    private String stationCode;
    private String name;
    private String regionName;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer totalPorts;
    private Integer availablePorts;
    private BigDecimal power;
    private BigDecimal pricePerKwh;
    private Integer status;
    // 距离（公里）
    private Double distance;
    // 充电口详情
    private List<PortVO> ports;

    @Data
    public static class PortVO {
        private String portCode;
        private String portType;
        private Integer status;
        private BigDecimal chargedKwh;
        // 剩余充电时间（分钟）
        private Integer remainingMinutes;
    }
}
