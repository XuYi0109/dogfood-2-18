package com.chargingstation.controller;

import com.chargingstation.dto.ApiResponse;
import com.chargingstation.entity.Region;
import com.chargingstation.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final RegionMapper regionMapper;

    @Autowired
    public RegionController(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @GetMapping("/list")
    public ApiResponse<List<Region>> getAllRegions() {
        List<Region> regions = regionMapper.findAll();
        return ApiResponse.success(regions);
    }

    @GetMapping("/{regionCode}")
    public ApiResponse<Region> getRegionByCode(@PathVariable String regionCode) {
        Region region = regionMapper.findByCode(regionCode);
        if (region == null) {
            return ApiResponse.error(404, "区域不存在: " + regionCode);
        }
        return ApiResponse.success(region);
    }

    @GetMapping("/level/{level}")
    public ApiResponse<List<Region>> getRegionsByLevel(@PathVariable Integer level) {
        List<Region> regions = regionMapper.findByLevel(level);
        return ApiResponse.success(regions);
    }

    @GetMapping("/children/{parentId}")
    public ApiResponse<List<Region>> getRegionsByParentId(@PathVariable Long parentId) {
        List<Region> regions = regionMapper.findByParentId(parentId);
        return ApiResponse.success(regions);
    }
}
