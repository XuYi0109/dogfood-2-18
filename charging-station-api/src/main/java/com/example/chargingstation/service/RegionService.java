package com.example.chargingstation.service;

import com.example.chargingstation.entity.Region;
import java.util.List;

public interface RegionService {
    List<Region> getRegionsByParentCode(String parentCode);
    Region getRegionByCode(String code);
    List<String> getAllRegionCodes(String code);
}
