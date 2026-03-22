package com.chargingstation.mapper;

import com.chargingstation.entity.ChargingStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChargingStationMapper {

    @Select("SELECT * FROM charging_station WHERE id = #{id}")
    ChargingStation findById(@Param("id") Long id);

    @Select("SELECT * FROM charging_station WHERE station_code = #{stationCode}")
    ChargingStation findByCode(@Param("stationCode") String stationCode);

    @Select("SELECT * FROM charging_station WHERE region_id = #{regionId} AND status = 1")
    List<ChargingStation> findByRegionId(@Param("regionId") Long regionId);

    @Select("SELECT * FROM charging_station WHERE status = 1")
    List<ChargingStation> findAllActive();

    List<ChargingStation> findByRegionCode(@Param("regionCode") String regionCode);

    List<ChargingStation> findByRegionCodeWithPiles(@Param("regionCode") String regionCode);
}
