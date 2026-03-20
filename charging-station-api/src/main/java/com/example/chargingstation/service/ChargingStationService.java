package com.example.chargingstation.service;

import com.example.chargingstation.dto.StationQueryDTO;
import com.example.chargingstation.dto.StationDetailVO;
import java.util.List;

public interface ChargingStationService {
    List<StationDetailVO> getStationsByRegion(StationQueryDTO queryDTO);
    StationDetailVO getStationDetail(Long stationId, StationQueryDTO queryDTO);
    void updateStationAvailablePorts(Long stationId);
}
