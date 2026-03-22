package com.chargingstation.dto;

import java.util.List;

public class RegionChargingInfoDTO {

    private String regionCode;
    private String regionName;
    private Integer totalStations;
    private Integer totalPiles;
    private Integer availablePiles;
    private Integer occupiedPiles;
    private Double overallOccupancyRate;
    private List<ChargingStationDTO> stations;
    private List<RouteInfoDTO> routes;

    public RegionChargingInfoDTO() {
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getTotalStations() {
        return totalStations;
    }

    public void setTotalStations(Integer totalStations) {
        this.totalStations = totalStations;
    }

    public Integer getTotalPiles() {
        return totalPiles;
    }

    public void setTotalPiles(Integer totalPiles) {
        this.totalPiles = totalPiles;
    }

    public Integer getAvailablePiles() {
        return availablePiles;
    }

    public void setAvailablePiles(Integer availablePiles) {
        this.availablePiles = availablePiles;
    }

    public Integer getOccupiedPiles() {
        return occupiedPiles;
    }

    public void setOccupiedPiles(Integer occupiedPiles) {
        this.occupiedPiles = occupiedPiles;
    }

    public Double getOverallOccupancyRate() {
        return overallOccupancyRate;
    }

    public void setOverallOccupancyRate(Double overallOccupancyRate) {
        this.overallOccupancyRate = overallOccupancyRate;
    }

    public List<ChargingStationDTO> getStations() {
        return stations;
    }

    public void setStations(List<ChargingStationDTO> stations) {
        this.stations = stations;
    }

    public List<RouteInfoDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteInfoDTO> routes) {
        this.routes = routes;
    }
}
