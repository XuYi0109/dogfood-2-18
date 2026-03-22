package com.chargingstation.mapper;

import com.chargingstation.entity.RouteInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RouteInfoMapper {

    @Select("SELECT * FROM route_info WHERE id = #{id}")
    RouteInfo findById(@Param("id") Long id);

    @Select("SELECT * FROM route_info WHERE from_station_id = #{fromStationId}")
    List<RouteInfo> findByFromStationId(@Param("fromStationId") Long fromStationId);

    @Select("SELECT * FROM route_info WHERE to_station_id = #{toStationId}")
    List<RouteInfo> findByToStationId(@Param("toStationId") Long toStationId);

    @Select("SELECT * FROM route_info WHERE from_station_id = #{fromStationId} AND to_station_id = #{toStationId}")
    List<RouteInfo> findByFromAndToStation(@Param("fromStationId") Long fromStationId, @Param("toStationId") Long toStationId);

    List<RouteInfo> findRoutesByRegionCode(@Param("regionCode") String regionCode);
}
