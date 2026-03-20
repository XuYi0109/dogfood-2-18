package com.charging.station.mapper;

import com.charging.station.entity.ChargingStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChargingStationMapper {

    ChargingStation selectById(@Param("id") Long id);

    List<ChargingStation> selectByRegionId(@Param("regionId") Long regionId);

    List<ChargingStation> selectAll();

    int updateAvailablePorts(@Param("id") Long id, @Param("availablePorts") Integer availablePorts);
}
