package com.charging.station;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.charging.station.mapper")
public class ChargingStationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChargingStationApplication.class, args);
    }
}
