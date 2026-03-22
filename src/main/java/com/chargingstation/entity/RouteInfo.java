package com.chargingstation.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class RouteInfo {

    private Long id;
    private Long fromStationId;
    private Long toStationId;
    private BigDecimal distanceKm;
    private Integer estimatedDuration;
    private Integer routeType;
    private BigDecimal tollFee;
    private String routeDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RouteInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromStationId() {
        return fromStationId;
    }

    public void setFromStationId(Long fromStationId) {
        this.fromStationId = fromStationId;
    }

    public Long getToStationId() {
        return toStationId;
    }

    public void setToStationId(Long toStationId) {
        this.toStationId = toStationId;
    }

    public BigDecimal getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(BigDecimal distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Integer getRouteType() {
        return routeType;
    }

    public void setRouteType(Integer routeType) {
        this.routeType = routeType;
    }

    public BigDecimal getTollFee() {
        return tollFee;
    }

    public void setTollFee(BigDecimal tollFee) {
        this.tollFee = tollFee;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    public void setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
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
        return "RouteInfo{" +
                "id=" + id +
                ", fromStationId=" + fromStationId +
                ", toStationId=" + toStationId +
                ", distanceKm=" + distanceKm +
                ", estimatedDuration=" + estimatedDuration +
                '}';
    }
}
