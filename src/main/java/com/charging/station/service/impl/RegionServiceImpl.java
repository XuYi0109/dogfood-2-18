package com.charging.station.service.impl;

import com.charging.station.common.Constants;
import com.charging.station.dto.ChargingStationOccupancyDTO;
import com.charging.station.dto.PortOccupancyInfo;
import com.charging.station.dto.RegionStationInfoDTO;
import com.charging.station.dto.RouteInfoDTO;
import com.charging.station.entity.ChargingStation;
import com.charging.station.entity.Region;
import com.charging.station.entity.RouteInfo;
import com.charging.station.entity.StationOccupancy;
import com.charging.station.mapper.ChargingStationMapper;
import com.charging.station.mapper.RegionMapper;
import com.charging.station.mapper.RouteInfoMapper;
import com.charging.station.mapper.StationOccupancyMapper;
import com.charging.station.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;
    private final ChargingStationMapper chargingStationMapper;
    private final StationOccupancyMapper stationOccupancyMapper;
    private final RouteInfoMapper routeInfoMapper;

    public RegionServiceImpl(RegionMapper regionMapper,
                            ChargingStationMapper chargingStationMapper,
                            StationOccupancyMapper stationOccupancyMapper,
                            RouteInfoMapper routeInfoMapper) {
        this.regionMapper = regionMapper;
        this.chargingStationMapper = chargingStationMapper;
        this.stationOccupancyMapper = stationOccupancyMapper;
        this.routeInfoMapper = routeInfoMapper;
    }

    @Override
    public Region getById(Long id) {
        return regionMapper.selectById(id);
    }

    @Override
    public Region getByCode(String regionCode) {
        return regionMapper.selectByCode(regionCode);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionMapper.selectAll();
    }

    @Override
    public RegionStationInfoDTO getRegionStationInfo(Long regionId) {
        Region region = regionMapper.selectById(regionId);
        if (region == null) {
            return null;
        }

        RegionStationInfoDTO dto = new RegionStationInfoDTO();
        dto.setRegionId(region.getId());
        dto.setRegionName(region.getRegionName());
        dto.setRegionCode(region.getRegionCode());

        List<ChargingStation> stations = chargingStationMapper.selectByRegionId(regionId);
        dto.setTotalStations(stations.size());

        int totalPorts = 0;
        int availablePorts = 0;
        List<ChargingStationOccupancyDTO> stationDTOs = new ArrayList<>();

        for (ChargingStation station : stations) {
            ChargingStationOccupancyDTO stationDTO = buildStationOccupancyDTO(station);
            stationDTOs.add(stationDTO);
            totalPorts += station.getTotalPorts() != null ? station.getTotalPorts() : 0;
            availablePorts += station.getAvailablePorts() != null ? station.getAvailablePorts() : 0;
        }

        dto.setTotalPorts(totalPorts);
        dto.setAvailablePorts(availablePorts);
        dto.setStations(stationDTOs);

        return dto;
    }

    @Override
    public RouteInfoDTO getRouteInfo(Long startRegionId, Long endRegionId) {
        RouteInfo routeInfo = routeInfoMapper.selectByRegions(startRegionId, endRegionId);
        if (routeInfo == null) {
            return null;
        }
        return buildRouteInfoDTO(routeInfo);
    }

    @Override
    public List<RouteInfoDTO> getRoutesFromRegion(Long startRegionId) {
        List<RouteInfo> routes = routeInfoMapper.selectByStartRegionId(startRegionId);
        return routes.stream()
                .map(this::buildRouteInfoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteInfoDTO> getRoutesToRegion(Long endRegionId) {
        List<RouteInfo> routes = routeInfoMapper.selectByEndRegionId(endRegionId);
        return routes.stream()
                .map(this::buildRouteInfoDTO)
                .collect(Collectors.toList());
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

    private RouteInfoDTO buildRouteInfoDTO(RouteInfo routeInfo) {
        RouteInfoDTO dto = new RouteInfoDTO();
        dto.setRouteId(routeInfo.getId());

        Region startRegion = regionMapper.selectById(routeInfo.getStartRegionId());
        Region endRegion = regionMapper.selectById(routeInfo.getEndRegionId());

        dto.setStartRegionName(startRegion != null ? startRegion.getRegionName() : "未知区域");
        dto.setEndRegionName(endRegion != null ? endRegion.getRegionName() : "未知区域");
        dto.setDistance(routeInfo.getDistance());

        if (routeInfo.getDistance() != null) {
            dto.setDistanceDesc(String.format("%.1f公里", routeInfo.getDistance()));
        }

        dto.setEstimatedTime(routeInfo.getEstimatedTime());
        if (routeInfo.getEstimatedTime() != null) {
            int hours = routeInfo.getEstimatedTime() / 60;
            int minutes = routeInfo.getEstimatedTime() % 60;
            if (hours > 0) {
                dto.setTimeDesc(String.format("%d小时%d分钟", hours, minutes));
            } else {
                dto.setTimeDesc(String.format("%d分钟", minutes));
            }
        }

        dto.setRoutePath(routeInfo.getRoutePath());
        dto.setTrafficStatus(routeInfo.getTrafficStatus());

        return dto;
    }
}
