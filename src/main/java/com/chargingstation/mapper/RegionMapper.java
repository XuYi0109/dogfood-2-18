package com.chargingstation.mapper;

import com.chargingstation.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegionMapper {

    @Select("SELECT * FROM region WHERE id = #{id}")
    Region findById(@Param("id") Long id);

    @Select("SELECT * FROM region WHERE region_code = #{regionCode}")
    Region findByCode(@Param("regionCode") String regionCode);

    @Select("SELECT * FROM region WHERE level = #{level}")
    List<Region> findByLevel(@Param("level") Integer level);

    @Select("SELECT * FROM region WHERE parent_id = #{parentId}")
    List<Region> findByParentId(@Param("parentId") Long parentId);

    @Select("SELECT * FROM region")
    List<Region> findAll();
}
