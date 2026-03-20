package com.charging.station.controller;

import com.charging.station.common.Result;
import com.charging.station.common.ResultCode;
import com.charging.station.dto.ChargingStationOccupancyDTO;
import com.charging.station.entity.ChargingStation;
import com.charging.station.service.ChargingStationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/station")
public class ChargingStationController {

    private final ChargingStationService chargingStationService;

    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @GetMapping("/list")
    public Result<List<ChargingStation>> getAllStations() {
        List<ChargingStation> stations = chargingStationService.getAllStations();
        return Result.success(stations);
    }

    @GetMapping("/{id}")
    public Result<ChargingStation> getStationById(@PathVariable("id") Long id) {
        ChargingStation station = chargingStationService.getById(id);
        if (station == null) {
            return Result.error(ResultCode.STATION_NOT_FOUND);
        }
        return Result.success(station);
    }

    @GetMapping("/region/{regionId}")
    public Result<List<ChargingStation>> getStationsByRegion(@PathVariable("regionId") Long regionId) {
        List<ChargingStation> stations = chargingStationService.getByRegionId(regionId);
        return Result.success(stations);
    }

    @GetMapping("/{id}/occupancy")
    public Result<ChargingStationOccupancyDTO> getStationOccupancy(@PathVariable("id") Long id) {
        ChargingStationOccupancyDTO dto = chargingStationService.getStationOccupancy(id);
        if (dto == null) {
            return Result.error(ResultCode.STATION_NOT_FOUND);
        }
        return Result.success(dto);
    }

    @GetMapping("/region/{regionId}/occupancy")
    public Result<List<ChargingStationOccupancyDTO>> getStationsOccupancyByRegion(@PathVariable("regionId") Long regionId) {
        List<ChargingStationOccupancyDTO> list = chargingStationService.getStationsOccupancyByRegion(regionId);
        return Result.success(list);
    }
}
