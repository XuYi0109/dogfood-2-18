package com.charging.station.mapper;

import com.charging.station.entity.RouteInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RouteInfoMapper {

    RouteInfo selectById(@Param("id") Long id);

    RouteInfo selectByRegions(@Param("startRegionId") Long startRegionId, @Param("endRegionId") Long endRegionId);

    List<RouteInfo> selectByStartRegionId(@Param("startRegionId") Long startRegionId);

    List<RouteInfo> selectByEndRegionId(@Param("endRegionId") Long endRegionId);

    List<RouteInfo> selectAll();
}
