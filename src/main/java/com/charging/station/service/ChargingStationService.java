package com.charging.station.service;

import com.charging.station.dto.ChargingStationOccupancyDTO;
import com.charging.station.entity.ChargingStation;

import java.util.List;

public interface ChargingStationService {

    ChargingStation getById(Long id);

    List<ChargingStation> getByRegionId(Long regionId);

    List<ChargingStation> getAllStations();

    ChargingStationOccupancyDTO getStationOccupancy(Long stationId);

    List<ChargingStationOccupancyDTO> getStationsOccupancyByRegion(Long regionId);
}
