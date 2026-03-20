package com.example.chargingstation.mapper;

import com.example.chargingstation.entity.ChargingStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ChargingStationMapper {
    List<ChargingStation> selectByRegionCodes(@Param("regionCodes") List<String> regionCodes,
                                              @Param("status") Integer status,
                                              @Param("sortBy") String sortBy);
    ChargingStation selectById(@Param("id") Long id);
    void updateAvailablePorts(@Param("stationId") Long stationId);
}
