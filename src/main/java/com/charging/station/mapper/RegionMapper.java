package com.charging.station.mapper;

import com.charging.station.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegionMapper {

    Region selectById(@Param("id") Long id);

    Region selectByCode(@Param("regionCode") String regionCode);

    List<Region> selectAll();
}
