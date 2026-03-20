package com.charging.station.controller;

import com.charging.station.common.Result;
import com.charging.station.common.ResultCode;
import com.charging.station.dto.RegionStationInfoDTO;
import com.charging.station.dto.RouteInfoDTO;
import com.charging.station.entity.Region;
import com.charging.station.service.RegionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/list")
    public Result<List<Region>> getAllRegions() {
        List<Region> regions = regionService.getAllRegions();
        return Result.success(regions);
    }

    @GetMapping("/{id}")
    public Result<Region> getRegionById(@PathVariable("id") Long id) {
        Region region = regionService.getById(id);
        if (region == null) {
            return Result.error(ResultCode.REGION_NOT_FOUND);
        }
        return Result.success(region);
    }

    @GetMapping("/code/{code}")
    public Result<Region> getRegionByCode(@PathVariable("code") String code) {
        Region region = regionService.getByCode(code);
        if (region == null) {
            return Result.error(ResultCode.REGION_NOT_FOUND);
        }
        return Result.success(region);
    }

    @GetMapping("/{regionId}/stations")
    public Result<RegionStationInfoDTO> getRegionStationInfo(@PathVariable("regionId") Long regionId) {
        RegionStationInfoDTO dto = regionService.getRegionStationInfo(regionId);
        if (dto == null) {
            return Result.error(ResultCode.REGION_NOT_FOUND);
        }
        return Result.success(dto);
    }

    @GetMapping("/route")
    public Result<RouteInfoDTO> getRouteInfo(
            @RequestParam("startRegionId") Long startRegionId,
            @RequestParam("endRegionId") Long endRegionId) {
        RouteInfoDTO dto = regionService.getRouteInfo(startRegionId, endRegionId);
        if (dto == null) {
            return Result.error(ResultCode.ROUTE_NOT_FOUND);
        }
        return Result.success(dto);
    }

    @GetMapping("/route/from/{startRegionId}")
    public Result<List<RouteInfoDTO>> getRoutesFromRegion(@PathVariable("startRegionId") Long startRegionId) {
        List<RouteInfoDTO> routes = regionService.getRoutesFromRegion(startRegionId);
        return Result.success(routes);
    }

    @GetMapping("/route/to/{endRegionId}")
    public Result<List<RouteInfoDTO>> getRoutesToRegion(@PathVariable("endRegionId") Long endRegionId) {
        List<RouteInfoDTO> routes = regionService.getRoutesToRegion(endRegionId);
        return Result.success(routes);
    }
}
