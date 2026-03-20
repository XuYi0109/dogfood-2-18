package com.charging.station.dto;

import java.io.Serializable;
import java.util.List;

public class ChargingStationOccupancyDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long stationId;
    private String stationName;
    private String stationCode;
    private String address;
    private Integer totalPorts;
    private Integer availablePorts;
    private Integer occupiedPorts;
    private Double occupancyRate;
    private List<PortOccupancyInfo> portDetails;

    public ChargingStationOccupancyDTO() {
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalPorts() {
        return totalPorts;
    }

    public void setTotalPorts(Integer totalPorts) {
        this.totalPorts = totalPorts;
    }

    public Integer getAvailablePorts() {
        return availablePorts;
    }

    public void setAvailablePorts(Integer availablePorts) {
        this.availablePorts = availablePorts;
    }

    public Integer getOccupiedPorts() {
        return occupiedPorts;
    }

    public void setOccupiedPorts(Integer occupiedPorts) {
        this.occupiedPorts = occupiedPorts;
    }

    public Double getOccupancyRate() {
        return occupancyRate;
    }

    public void setOccupancyRate(Double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    public List<PortOccupancyInfo> getPortDetails() {
        return portDetails;
    }

    public void setPortDetails(List<PortOccupancyInfo> portDetails) {
        this.portDetails = portDetails;
    }
}
