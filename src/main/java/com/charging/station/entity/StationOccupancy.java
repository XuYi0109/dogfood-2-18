package com.charging.station.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StationOccupancy implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long stationId;
    private Integer portNumber;
    private Integer occupancyStatus;
    private String vehiclePlate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;

    public StationOccupancy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public Integer getOccupancyStatus() {
        return occupancyStatus;
    }

    public void setOccupancyStatus(Integer occupancyStatus) {
        this.occupancyStatus = occupancyStatus;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
