package com.chargingstation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ChargingPileDTO {

    private Long id;
    private String pileCode;
    private String pileName;
    private Integer pileType;
    private String pileTypeName;
    private BigDecimal powerKw;
    private String voltage;
    private String currentType;
    private String connectorType;
    private Integer status;
    private String statusName;
    private Long currentUserId;
    private LocalDateTime startTime;
    private LocalDateTime estimatedEndTime;
    private Integer currentSoc;
    private Integer chargingDuration;
    private Integer remainingMinutes;

    public ChargingPileDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPileCode() {
        return pileCode;
    }

    public void setPileCode(String pileCode) {
        this.pileCode = pileCode;
    }

    public String getPileName() {
        return pileName;
    }

    public void setPileName(String pileName) {
        this.pileName = pileName;
    }

    public Integer getPileType() {
        return pileType;
    }

    public void setPileType(Integer pileType) {
        this.pileType = pileType;
    }

    public String getPileTypeName() {
        return pileTypeName;
    }

    public void setPileTypeName(String pileTypeName) {
        this.pileTypeName = pileTypeName;
    }

    public BigDecimal getPowerKw() {
        return powerKw;
    }

    public void setPowerKw(BigDecimal powerKw) {
        this.powerKw = powerKw;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public String getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(LocalDateTime estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }

    public Integer getCurrentSoc() {
        return currentSoc;
    }

    public void setCurrentSoc(Integer currentSoc) {
        this.currentSoc = currentSoc;
    }

    public Integer getChargingDuration() {
        return chargingDuration;
    }

    public void setChargingDuration(Integer chargingDuration) {
        this.chargingDuration = chargingDuration;
    }

    public Integer getRemainingMinutes() {
        return remainingMinutes;
    }

    public void setRemainingMinutes(Integer remainingMinutes) {
        this.remainingMinutes = remainingMinutes;
    }
}
