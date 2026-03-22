package com.chargingstation.service;

import com.chargingstation.dto.RegionChargingInfoDTO;

public interface ChargingStationService {

    RegionChargingInfoDTO getRegionChargingInfo(String regionCode);
}
