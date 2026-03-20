package com.charging.station.entity;

import java.io.Serializable;

public class RouteInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long startRegionId;
    private Long endRegionId;
    private Double distance;
    private Integer estimatedTime;
    private String routePath;
    private String trafficStatus;

    public RouteInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartRegionId() {
        return startRegionId;
    }

    public void setStartRegionId(Long startRegionId) {
        this.startRegionId = startRegionId;
    }

    public Long getEndRegionId() {
        return endRegionId;
    }

    public void setEndRegionId(Long endRegionId) {
        this.endRegionId = endRegionId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getRoutePath() {
        return routePath;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public String getTrafficStatus() {
        return trafficStatus;
    }

    public void setTrafficStatus(String trafficStatus) {
        this.trafficStatus = trafficStatus;
    }
}
