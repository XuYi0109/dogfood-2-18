package com.charging.station.dto;

import java.io.Serializable;
import java.util.List;

public class RegionStationInfoDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long regionId;
    private String regionName;
    private String regionCode;
    private Integer totalStations;
    private Integer totalPorts;
    private Integer availablePorts;
    private List<ChargingStationOccupancyDTO> stations;

    public RegionStationInfoDTO() {
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getTotalStations() {
        return totalStations;
    }

    public void setTotalStations(Integer totalStations) {
        this.totalStations = totalStations;
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

    public List<ChargingStationOccupancyDTO> getStations() {
        return stations;
    }

    public void setStations(List<ChargingStationOccupancyDTO> stations) {
        this.stations = stations;
    }
}
