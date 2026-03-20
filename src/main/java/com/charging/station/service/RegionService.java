package com.charging.station.service;

import com.charging.station.dto.RegionStationInfoDTO;
import com.charging.station.dto.RouteInfoDTO;
import com.charging.station.entity.Region;

import java.util.List;

public interface RegionService {

    Region getById(Long id);

    Region getByCode(String regionCode);

    List<Region> getAllRegions();

    RegionStationInfoDTO getRegionStationInfo(Long regionId);

    RouteInfoDTO getRouteInfo(Long startRegionId, Long endRegionId);

    List<RouteInfoDTO> getRoutesFromRegion(Long startRegionId);

    List<RouteInfoDTO> getRoutesToRegion(Long endRegionId);
}
