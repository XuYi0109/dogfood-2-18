package com.example.chargingstation.controller;

import com.example.chargingstation.common.Result;
import com.example.chargingstation.dto.StationQueryDTO;
import com.example.chargingstation.dto.StationDetailVO;
import com.example.chargingstation.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/station")
public class ChargingStationController {

    @Autowired
    private ChargingStationService chargingStationService;

    /**
     * 根据区域查询充电桩列表
     * @param queryDTO 查询条件
     * @return 充电桩列表
     */
    @PostMapping("/list")
    public Result<List<StationDetailVO>> getStationsByRegion(@RequestBody StationQueryDTO queryDTO) {
        if (queryDTO.getRegionCode() == null || queryDTO.getRegionCode().trim().isEmpty()) {
            return Result.error("区域编码不能为空");
        }
        List<StationDetailVO> stations = chargingStationService.getStationsByRegion(queryDTO);
        return Result.success(stations);
    }

    /**
     * 获取充电桩详情
     * @param id 充电桩ID
     * @param queryDTO 查询条件（包含用户位置信息）
     * @return 充电桩详情
     */
    @GetMapping("/detail/{id}")
    public Result<StationDetailVO> getStationDetail(
            @PathVariable("id") Long id,
            StationQueryDTO queryDTO) {
        if (id == null) {
            return Result.error("充电桩ID不能为空");
        }
        StationDetailVO stationDetail = chargingStationService.getStationDetail(id, queryDTO);
        if (stationDetail == null) {
            return Result.error("充电桩不存在");
        }
        return Result.success(stationDetail);
    }

    /**
     * 更新充电桩可用端口数量
     * @param id 充电桩ID
     * @return 操作结果
     */
    @PutMapping("/update-available/{id}")
    public Result<Void> updateAvailablePorts(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("充电桩ID不能为空");
        }
        chargingStationService.updateStationAvailablePorts(id);
        return Result.success();
    }
}
