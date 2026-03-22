package com.chargingstation.mapper;

import com.chargingstation.entity.ChargingPile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChargingPileMapper {

    @Select("SELECT * FROM charging_pile WHERE id = #{id}")
    ChargingPile findById(@Param("id") Long id);

    @Select("SELECT * FROM charging_pile WHERE pile_code = #{pileCode}")
    ChargingPile findByCode(@Param("pileCode") String pileCode);

    @Select("SELECT * FROM charging_pile WHERE station_id = #{stationId}")
    List<ChargingPile> findByStationId(@Param("stationId") Long stationId);

    @Select("SELECT * FROM charging_pile WHERE station_id = #{stationId} AND status = #{status}")
    List<ChargingPile> findByStationIdAndStatus(@Param("stationId") Long stationId, @Param("status") Integer status);

    @Select("SELECT COUNT(*) FROM charging_pile WHERE station_id = #{stationId} AND status = 0")
    Integer countAvailableByStationId(@Param("stationId") Long stationId);

    @Select("SELECT COUNT(*) FROM charging_pile WHERE station_id = #{stationId} AND status = 1")
    Integer countOccupiedByStationId(@Param("stationId") Long stationId);
}
