package com.chargingstation.service.impl;

import com.chargingstation.dto.*;
import com.chargingstation.entity.*;
import com.chargingstation.mapper.*;
import com.chargingstation.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChargingStationServiceImpl implements ChargingStationService {

    private final RegionMapper regionMapper;
    private final ChargingStationMapper chargingStationMapper;
    private final ChargingPileMapper chargingPileMapper;
    private final RouteInfoMapper routeInfoMapper;

    @Autowired
    public ChargingStationServiceImpl(RegionMapper regionMapper,
                                      ChargingStationMapper chargingStationMapper,
                                      ChargingPileMapper chargingPileMapper,
                                      RouteInfoMapper routeInfoMapper) {
        this.regionMapper = regionMapper;
        this.chargingStationMapper = chargingStationMapper;
        this.chargingPileMapper = chargingPileMapper;
        this.routeInfoMapper = routeInfoMapper;
    }

    @Override
    public RegionChargingInfoDTO getRegionChargingInfo(String regionCode) {
        Region region = regionMapper.findByCode(regionCode);
        if (region == null) {
            return null;
        }

        RegionChargingInfoDTO result = new RegionChargingInfoDTO();
        result.setRegionCode(region.getRegionCode());
        result.setRegionName(region.getRegionName());

        List<ChargingStation> stations = chargingStationMapper.findByRegionCode(regionCode);
        List<ChargingStationDTO> stationDTOs = new ArrayList<>();

        int totalPiles = 0;
        int totalAvailable = 0;
        int totalOccupied = 0;

        for (ChargingStation station : stations) {
            ChargingStationDTO stationDTO = convertToStationDTO(station);

            List<ChargingPile> piles = chargingPileMapper.findByStationId(station.getId());
            List<ChargingPileDTO> pileDTOs = new ArrayList<>();

            int availableCount = 0;
            int occupiedCount = 0;

            for (ChargingPile pile : piles) {
                ChargingPileDTO pileDTO = convertToPileDTO(pile);
                pileDTOs.add(pileDTO);

                if (pile.getStatus() != null && pile.getStatus() == 0) {
                    availableCount++;
                } else if (pile.getStatus() != null && pile.getStatus() == 1) {
                    occupiedCount++;
                }
            }

            stationDTO.setPiles(pileDTOs);
            stationDTO.setAvailablePiles(availableCount);
            stationDTO.setOccupiedPiles(occupiedCount);

            if (piles.size() > 0) {
                double occupancyRate = (double) occupiedCount / piles.size() * 100;
                stationDTO.setOccupancyRate(Math.round(occupancyRate * 100.0) / 100.0);
            }

            stationDTOs.add(stationDTO);

            totalPiles += piles.size();
            totalAvailable += availableCount;
            totalOccupied += occupiedCount;
        }

        result.setStations(stationDTOs);
        result.setTotalStations(stations.size());
        result.setTotalPiles(totalPiles);
        result.setAvailablePiles(totalAvailable);
        result.setOccupiedPiles(totalOccupied);

        if (totalPiles > 0) {
            double overallRate = (double) totalOccupied / totalPiles * 100;
            result.setOverallOccupancyRate(Math.round(overallRate * 100.0) / 100.0);
        }

        List<RouteInfo> routes = routeInfoMapper.findRoutesByRegionCode(regionCode);
        List<RouteInfoDTO> routeDTOs = routes.stream()
                .map(this::convertToRouteDTO)
                .collect(Collectors.toList());
        result.setRoutes(routeDTOs);

        return result;
    }

    private ChargingStationDTO convertToStationDTO(ChargingStation station) {
        ChargingStationDTO dto = new ChargingStationDTO();
        dto.setId(station.getId());
        dto.setStationCode(station.getStationCode());
        dto.setStationName(station.getStationName());
        dto.setAddress(station.getAddress());
        dto.setLongitude(station.getLongitude());
        dto.setLatitude(station.getLatitude());
        dto.setTotalPiles(station.getTotalPiles());
        dto.setStatus(station.getStatus());
        dto.setStatusName(getStationStatusName(station.getStatus()));
        dto.setOperatorName(station.getOperatorName());
        dto.setContactPhone(station.getContactPhone());
        dto.setServiceTime(station.getServiceTime());
        dto.setParkingFee(station.getParkingFee());
        return dto;
    }

    private ChargingPileDTO convertToPileDTO(ChargingPile pile) {
        ChargingPileDTO dto = new ChargingPileDTO();
        dto.setId(pile.getId());
        dto.setPileCode(pile.getPileCode());
        dto.setPileName(pile.getPileName());
        dto.setPileType(pile.getPileType());
        dto.setPileTypeName(getPileTypeName(pile.getPileType()));
        dto.setPowerKw(pile.getPowerKw());
        dto.setVoltage(pile.getVoltage());
        dto.setCurrentType(pile.getCurrentType());
        dto.setConnectorType(pile.getConnectorType());
        dto.setStatus(pile.getStatus());
        dto.setStatusName(getPileStatusName(pile.getStatus()));
        dto.setCurrentUserId(pile.getCurrentUserId());
        dto.setStartTime(pile.getStartTime());
        dto.setEstimatedEndTime(pile.getEstimatedEndTime());
        dto.setCurrentSoc(pile.getCurrentSoc());
        dto.setChargingDuration(pile.getChargingDuration());

        if (pile.getEstimatedEndTime() != null) {
            long remaining = ChronoUnit.MINUTES.between(LocalDateTime.now(), pile.getEstimatedEndTime());
            dto.setRemainingMinutes(remaining > 0 ? (int) remaining : 0);
        }

        return dto;
    }

    private RouteInfoDTO convertToRouteDTO(RouteInfo route) {
        RouteInfoDTO dto = new RouteInfoDTO();
        dto.setId(route.getId());
        dto.setFromStationId(route.getFromStationId());
        dto.setToStationId(route.getToStationId());
        dto.setDistanceKm(route.getDistanceKm());
        dto.setEstimatedDuration(route.getEstimatedDuration());
        dto.setRouteType(route.getRouteType());
        dto.setRouteTypeName(getRouteTypeName(route.getRouteType()));
        dto.setTollFee(route.getTollFee());
        dto.setRouteDescription(route.getRouteDescription());

        ChargingStation fromStation = chargingStationMapper.findById(route.getFromStationId());
        ChargingStation toStation = chargingStationMapper.findById(route.getToStationId());

        if (fromStation != null) {
            dto.setFromStationName(fromStation.getStationName());
        }
        if (toStation != null) {
            dto.setToStationName(toStation.getStationName());
        }

        return dto;
    }

    private String getStationStatusName(Integer status) {
        if (status == null) return "未知";
        switch (status) {
            case 0: return "停用";
            case 1: return "正常";
            default: return "未知";
        }
    }

    private String getPileTypeName(Integer type) {
        if (type == null) return "未知";
        switch (type) {
            case 1: return "直流快充";
            case 2: return "交流慢充";
            default: return "未知";
        }
    }

    private String getPileStatusName(Integer status) {
        if (status == null) return "未知";
        switch (status) {
            case 0: return "空闲";
            case 1: return "充电中";
            case 2: return "故障";
            case 3: return "离线";
            default: return "未知";
        }
    }

    private String getRouteTypeName(Integer type) {
        if (type == null) return "未知";
        switch (type) {
            case 1: return "驾车";
            case 2: return "步行";
            default: return "未知";
        }
    }
}
