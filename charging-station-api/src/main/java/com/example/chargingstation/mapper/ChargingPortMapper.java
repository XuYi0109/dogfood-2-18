package com.example.chargingstation.mapper;

import com.example.chargingstation.entity.ChargingPort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ChargingPortMapper {
    List<ChargingPort> selectByStationId(@Param("stationId") Long stationId);
    int countAvailableByStationId(@Param("stationId") Long stationId);
}
