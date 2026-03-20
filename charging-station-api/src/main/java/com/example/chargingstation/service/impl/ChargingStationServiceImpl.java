package com.example.chargingstation.service.impl;

import com.example.chargingstation.dto.StationQueryDTO;
import com.example.chargingstation.dto.StationDetailVO;
import com.example.chargingstation.entity.ChargingPort;
import com.example.chargingstation.entity.ChargingStation;
import com.example.chargingstation.entity.Region;
import com.example.chargingstation.mapper.ChargingPortMapper;
import com.example.chargingstation.mapper.ChargingStationMapper;
import com.example.chargingstation.mapper.RegionMapper;
import com.example.chargingstation.service.ChargingStationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargingStationServiceImpl implements ChargingStationService {

    @Autowired
    private ChargingStationMapper chargingStationMapper;

    @Autowired
    private ChargingPortMapper chargingPortMapper;

    @Autowired
    private RegionMapper regionMapper;

    // 地球半径（公里）
    private static final double EARTH_RADIUS = 6378.137;

    @Override
    public List<StationDetailVO> getStationsByRegion(StationQueryDTO queryDTO) {
        // 获取该区域及其子区域的所有编码
        List<String> allRegionCodes = regionMapper.selectAllRegionCodes(queryDTO.getRegionCode());
        if (allRegionCodes == null || allRegionCodes.isEmpty()) {
            return new ArrayList<>();
        }

        // 查询充电桩列表
        List<ChargingStation> stations = chargingStationMapper.selectByRegionCodes(
                allRegionCodes, queryDTO.getStatus(), queryDTO.getSortBy());

        // 转换为VO并计算距离
        return stations.stream()
                .map(station -> convertToVO(station, queryDTO.getUserLongitude(), queryDTO.getUserLatitude()))
                .sorted(getComparator(queryDTO.getSortBy()))
                .collect(Collectors.toList());
    }

    @Override
    public StationDetailVO getStationDetail(Long stationId, StationQueryDTO queryDTO) {
        ChargingStation station = chargingStationMapper.selectById(stationId);
        if (station == null) {
            return null;
        }

        StationDetailVO vo = convertToVO(station, queryDTO.getUserLongitude(), queryDTO.getUserLatitude());

        // 查询充电口详情
        List<ChargingPort> ports = chargingPortMapper.selectByStationId(stationId);
        List<StationDetailVO.PortVO> portVOs = ports.stream()
                .map(this::convertToPortVO)
                .collect(Collectors.toList());
        vo.setPorts(portVOs);

        return vo;
    }

    @Override
    public void updateStationAvailablePorts(Long stationId) {
        chargingStationMapper.updateAvailablePorts(stationId);
    }

    private StationDetailVO convertToVO(ChargingStation station, BigDecimal userLon, BigDecimal userLat) {
        StationDetailVO vo = new StationDetailVO();
        BeanUtils.copyProperties(station, vo);

        // 设置区域名称
        Region region = regionMapper.selectByCode(station.getRegionCode());
        if (region != null) {
            vo.setRegionName(region.getName());
        }

        // 计算距离
        if (userLon != null && userLat != null) {
            double distance = calculateDistance(
                    userLat.doubleValue(), userLon.doubleValue(),
                    station.getLatitude().doubleValue(), station.getLongitude().doubleValue());
            vo.setDistance(distance);
        }

        return vo;
    }

    private StationDetailVO.PortVO convertToPortVO(ChargingPort port) {
        StationDetailVO.PortVO portVO = new StationDetailVO.PortVO();
        BeanUtils.copyProperties(port, portVO);

        // 计算剩余充电时间
        if (port.getStatus() == 1 && port.getExpectedEndTime() != null) {
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(port.getExpectedEndTime())) {
                Duration duration = Duration.between(now, port.getExpectedEndTime());
                portVO.setRemainingMinutes((int) duration.toMinutes());
            } else {
                portVO.setRemainingMinutes(0);
            }
        }

        return portVO;
    }

    /**
     * 计算两个经纬度之间的距离（Haversine公式）
     * @return 距离（公里）
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double radLon1 = Math.toRadians(lon1);
        double radLon2 = Math.toRadians(lon2);

        double a = Math.sin((radLat2 - radLat1) / 2) * Math.sin((radLat2 - radLat1) / 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.sin((radLon2 - radLon1) / 2) * Math.sin((radLon2 - radLon1) / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private Comparator<StationDetailVO> getComparator(String sortBy) {
        if ("distance".equals(sortBy)) {
            return Comparator.comparing(vo -> vo.getDistance() != null ? vo.getDistance() : Double.MAX_VALUE);
        } else if ("price".equals(sortBy)) {
            return Comparator.comparing(StationDetailVO::getPricePerKwh);
        } else if ("available".equals(sortBy)) {
            return Comparator.comparingInt(StationDetailVO::getAvailablePorts).reversed();
        }
        // 默认按ID排序
        return Comparator.comparingLong(StationDetailVO::getId);
    }
}
