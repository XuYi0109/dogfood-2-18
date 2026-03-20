package com.charging.station.mapper;

import com.charging.station.entity.StationOccupancy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StationOccupancyMapper {

    StationOccupancy selectById(@Param("id") Long id);

    List<StationOccupancy> selectByStationId(@Param("stationId") Long stationId);

    List<StationOccupancy> selectOccupiedByStationId(@Param("stationId") Long stationId);

    int insert(StationOccupancy occupancy);

    int update(StationOccupancy occupancy);

    int deleteById(@Param("id") Long id);
}
