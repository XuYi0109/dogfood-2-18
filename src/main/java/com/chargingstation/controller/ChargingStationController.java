package com.chargingstation.controller;

import com.chargingstation.dto.ApiResponse;
import com.chargingstation.dto.RegionChargingInfoDTO;
import com.chargingstation.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/charging")
public class ChargingStationController {

    private final ChargingStationService chargingStationService;

    @Autowired
    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @GetMapping("/region/{regionCode}")
    public ApiResponse<RegionChargingInfoDTO> getRegionChargingInfo(@PathVariable String regionCode) {
        RegionChargingInfoDTO info = chargingStationService.getRegionChargingInfo(regionCode);
        if (info == null) {
            return ApiResponse.error(404, "区域不存在: " + regionCode);
        }
        return ApiResponse.success(info);
    }

    @GetMapping("/region/info")
    public ApiResponse<RegionChargingInfoDTO> getRegionChargingInfoByParam(@RequestParam String regionCode) {
        RegionChargingInfoDTO info = chargingStationService.getRegionChargingInfo(regionCode);
        if (info == null) {
            return ApiResponse.error(404, "区域不存在: " + regionCode);
        }
        return ApiResponse.success(info);
    }
}
