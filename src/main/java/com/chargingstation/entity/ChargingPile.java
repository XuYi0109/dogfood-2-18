package com.chargingstation.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class ChargingPile {

    private Long id;
    private String pileCode;
    private String pileName;
    private Long stationId;
    private Integer pileType;
    private BigDecimal powerKw;
    private String voltage;
    private String currentType;
    private String connectorType;
    private Integer status;
    private Long currentUserId;
    private LocalDateTime startTime;
    private LocalDateTime estimatedEndTime;
    private Integer currentSoc;
    private Integer chargingDuration;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ChargingPile() {
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

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Integer getPileType() {
        return pileType;
    }

    public void setPileType(Integer pileType) {
        this.pileType = pileType;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ChargingPile{" +
                "id=" + id +
                ", pileCode='" + pileCode + '\'' +
                ", pileName='" + pileName + '\'' +
                ", status=" + status +
                '}';
    }
}
