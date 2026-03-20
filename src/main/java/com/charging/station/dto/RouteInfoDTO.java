package com.charging.station.dto;

import java.io.Serializable;

public class RouteInfoDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long routeId;
    private String startRegionName;
    private String endRegionName;
    private Double distance;
    private String distanceDesc;
    private Integer estimatedTime;
    private String timeDesc;
    private String routePath;
    private String trafficStatus;

    public RouteInfoDTO() {
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getStartRegionName() {
        return startRegionName;
    }

    public void setStartRegionName(String startRegionName) {
        this.startRegionName = startRegionName;
    }

    public String getEndRegionName() {
        return endRegionName;
    }

    public void setEndRegionName(String endRegionName) {
        this.endRegionName = endRegionName;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getDistanceDesc() {
        return distanceDesc;
    }

    public void setDistanceDesc(String distanceDesc) {
        this.distanceDesc = distanceDesc;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getTimeDesc() {
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
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
