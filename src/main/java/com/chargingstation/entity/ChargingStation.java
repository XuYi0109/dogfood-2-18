package com.chargingstation.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class ChargingStation {

    private Long id;
    private String stationCode;
    private String stationName;
    private Long regionId;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer totalPiles;
    private Integer availablePiles;
    private Integer occupiedPiles;
    private Integer status;
    private String operatorName;
    private String contactPhone;
    private String serviceTime;
    private BigDecimal parkingFee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ChargingStation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public BigDecimal getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
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
        return "ChargingStation{" +
                "id=" + id +
                ", stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                ", availablePiles=" + availablePiles +
                ", occupiedPiles=" + occupiedPiles +
                '}';
    }
}
