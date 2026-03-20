package com.example.chargingstation.mapper;

import com.example.chargingstation.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface RegionMapper {
    List<Region> selectByParentCode(@Param("parentCode") String parentCode);
    Region selectByCode(@Param("code") String code);
    List<String> selectAllRegionCodes(@Param("code") String code);
}
