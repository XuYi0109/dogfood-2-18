package com.example.chargingstation.controller;

import com.example.chargingstation.common.Result;
import com.example.chargingstation.entity.Region;
import com.example.chargingstation.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * 根据父区域编码获取子区域列表
     * @param parentCode 父区域编码（如：110000表示北京市）
     * @return 子区域列表
     */
    @GetMapping("/list")
    public Result<List<Region>> getRegionsByParentCode(
            @RequestParam(value = "parentCode", required = false, defaultValue = "null") String parentCode) {
        if ("null".equals(parentCode)) {
            parentCode = null;
        }
        List<Region> regions = regionService.getRegionsByParentCode(parentCode);
        return Result.success(regions);
    }

    /**
     * 根据区域编码获取区域详情
     * @param code 区域编码
     * @return 区域详情
     */
    @GetMapping("/detail")
    public Result<Region> getRegionByCode(@RequestParam("code") String code) {
        Region region = regionService.getRegionByCode(code);
        if (region == null) {
            return Result.error("区域不存在");
        }
        return Result.success(region);
    }
}
