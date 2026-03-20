package com.charging.station.service.impl;

import com.charging.station.common.Constants;
import com.charging.station.dto.ChargingStationOccupancyDTO;
import com.charging.station.dto.PortOccupancyInfo;
import com.charging.station.entity.ChargingStation;
import com.charging.station.entity.StationOccupancy;
import com.charging.station.mapper.ChargingStationMapper;
import com.charging.station.mapper.StationOccupancyMapper;
import com.charging.station.service.ChargingStationService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChargingStationServiceImpl implements ChargingStationService {

    private final ChargingStationMapper chargingStationMapper;
    private final StationOccupancyMapper stationOccupancyMapper;

    public ChargingStationServiceImpl(ChargingStationMapper chargingStationMapper,
                                      StationOccupancyMapper stationOccupancyMapper) {
        this.chargingStationMapper = chargingStationMapper;
        this.stationOccupancyMapper = stationOccupancyMapper;
    }

    @Override
    public ChargingStation getById(Long id) {
        return chargingStationMapper.selectById(id);
    }

    @Override
    public List<ChargingStation> getByRegionId(Long regionId) {
        return chargingStationMapper.selectByRegionId(regionId);
    }

    @Override
    public List<ChargingStation> getAllStations() {
        return chargingStationMapper.selectAll();
    }

    @Override
    public ChargingStationOccupancyDTO getStationOccupancy(Long stationId) {
        ChargingStation station = chargingStationMapper.selectById(stationId);
        if (station == null) {
            return null;
        }
        return buildStationOccupancyDTO(station);
    }

    @Override
    public List<ChargingStationOccupancyDTO> getStationsOccupancyByRegion(Long regionId) {
        List<ChargingStation> stations = chargingStationMapper.selectByRegionId(regionId);
        List<ChargingStationOccupancyDTO> result = new ArrayList<>();

        for (ChargingStation station : stations) {
            ChargingStationOccupancyDTO dto = buildStationOccupancyDTO(station);
            result.add(dto);
        }

        return result;
    }

    private ChargingStationOccupancyDTO buildStationOccupancyDTO(ChargingStation station) {
        ChargingStationOccupancyDTO dto = new ChargingStationOccupancyDTO();
        dto.setStationId(station.getId());
        dto.setStationName(station.getStationName());
        dto.setStationCode(station.getStationCode());
        dto.setAddress(station.getAddress());
        dto.setTotalPorts(station.getTotalPorts());
        dto.setAvailablePorts(station.getAvailablePorts());

        int occupiedPorts = (station.getTotalPorts() != null ? station.getTotalPorts() : 0)
                - (station.getAvailablePorts() != null ? station.getAvailablePorts() : 0);
        dto.setOccupiedPorts(occupiedPorts);

        if (station.getTotalPorts() != null && station.getTotalPorts() > 0) {
            double rate = (double) occupiedPorts / station.getTotalPorts() * 100;
            dto.setOccupancyRate(Math.round(rate * 100.0) / 100.0);
        } else {
            dto.setOccupancyRate(0.0);
        }

        List<StationOccupancy> occupancies = stationOccupancyMapper.selectByStationId(station.getId());
        List<PortOccupancyInfo> portDetails = new ArrayList<>();

        if (!CollectionUtils.isEmpty(occupancies)) {
            for (StationOccupancy occupancy : occupancies) {
                PortOccupancyInfo portInfo = new PortOccupancyInfo();
                portInfo.setPortNumber(occupancy.getPortNumber());
                portInfo.setStatus(occupancy.getOccupancyStatus());
                portInfo.setStatusDesc(Constants.getPortStatusDesc(occupancy.getOccupancyStatus()));
                portInfo.setVehiclePlate(occupancy.getVehiclePlate());
                portInfo.setStartTime(occupancy.getStartTime());
                portInfo.setEstimatedEndTime(occupancy.getEndTime());
                portDetails.add(portInfo);
            }
        }
        dto.setPortDetails(portDetails);

        return dto;
    }
}
