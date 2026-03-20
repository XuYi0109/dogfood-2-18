package com.example.chargingstation.service.impl;

import com.example.chargingstation.entity.Region;
import com.example.chargingstation.mapper.RegionMapper;
import com.example.chargingstation.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> getRegionsByParentCode(String parentCode) {
        return regionMapper.selectByParentCode(parentCode);
    }

    @Override
    public Region getRegionByCode(String code) {
        return regionMapper.selectByCode(code);
    }

    @Override
    public List<String> getAllRegionCodes(String code) {
        return regionMapper.selectAllRegionCodes(code);
    }
}
