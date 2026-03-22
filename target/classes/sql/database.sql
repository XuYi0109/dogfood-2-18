-- 创建数据库
CREATE DATABASE IF NOT EXISTS charging_station_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE charging_station_db;

-- 区域表
CREATE TABLE IF NOT EXISTS region (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '区域ID',
    region_code VARCHAR(50) NOT NULL UNIQUE COMMENT '区域编码',
    region_name VARCHAR(100) NOT NULL COMMENT '区域名称',
    parent_id BIGINT DEFAULT NULL COMMENT '父级区域ID',
    level INT DEFAULT 1 COMMENT '区域级别：1-省/直辖市，2-市，3-区/县',
    longitude DECIMAL(10, 7) COMMENT '经度',
    latitude DECIMAL(10, 7) COMMENT '纬度',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_region_code (region_code),
    INDEX idx_parent_id (parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区域信息表';

-- 充电站表
CREATE TABLE IF NOT EXISTS charging_station (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '充电站ID',
    station_code VARCHAR(50) NOT NULL UNIQUE COMMENT '充电站编码',
    station_name VARCHAR(200) NOT NULL COMMENT '充电站名称',
    region_id BIGINT NOT NULL COMMENT '所属区域ID',
    address VARCHAR(500) COMMENT '详细地址',
    longitude DECIMAL(10, 7) NOT NULL COMMENT '经度',
    latitude DECIMAL(10, 7) NOT NULL COMMENT '纬度',
    total_piles INT DEFAULT 0 COMMENT '充电桩总数',
    available_piles INT DEFAULT 0 COMMENT '可用充电桩数',
    occupied_piles INT DEFAULT 0 COMMENT '占用充电桩数',
    status TINYINT DEFAULT 1 COMMENT '状态：0-停用，1-正常',
    operator_name VARCHAR(100) COMMENT '运营商名称',
    contact_phone VARCHAR(20) COMMENT '联系电话',
    service_time VARCHAR(100) COMMENT '服务时间',
    parking_fee DECIMAL(10, 2) COMMENT '停车费(元/小时)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (region_id) REFERENCES region(id),
    INDEX idx_region_id (region_id),
    INDEX idx_station_code (station_code),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充电站信息表';

-- 充电桩表
CREATE TABLE IF NOT EXISTS charging_pile (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '充电桩ID',
    pile_code VARCHAR(50) NOT NULL UNIQUE COMMENT '充电桩编码',
    pile_name VARCHAR(100) COMMENT '充电桩名称',
    station_id BIGINT NOT NULL COMMENT '所属充电站ID',
    pile_type TINYINT NOT NULL COMMENT '桩类型：1-直流快充，2-交流慢充',
    power_kw DECIMAL(10, 2) COMMENT '功率(kW)',
    voltage VARCHAR(50) COMMENT '电压规格',
    current_type VARCHAR(50) COMMENT '电流类型',
    connector_type VARCHAR(50) COMMENT '接口类型',
    status TINYINT DEFAULT 0 COMMENT '状态：0-空闲，1-充电中，2-故障，3-离线',
    current_user_id BIGINT COMMENT '当前用户ID',
    start_time TIMESTAMP NULL COMMENT '开始充电时间',
    estimated_end_time TIMESTAMP NULL COMMENT '预计结束时间',
    current_soc INT COMMENT '当前电量百分比',
    charging_duration INT COMMENT '已充电时长(分钟)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (station_id) REFERENCES charging_station(id),
    INDEX idx_station_id (station_id),
    INDEX idx_pile_code (pile_code),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充电桩信息表';

-- 路程信息表
CREATE TABLE IF NOT EXISTS route_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '路程ID',
    from_station_id BIGINT NOT NULL COMMENT '起点充电站ID',
    to_station_id BIGINT NOT NULL COMMENT '终点充电站ID',
    distance_km DECIMAL(10, 2) NOT NULL COMMENT '距离(公里)',
    estimated_duration INT COMMENT '预计行驶时间(分钟)',
    route_type TINYINT DEFAULT 1 COMMENT '路线类型：1-驾车，2-步行',
    toll_fee DECIMAL(10, 2) COMMENT '过路费(元)',
    route_description VARCHAR(500) COMMENT '路线描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (from_station_id) REFERENCES charging_station(id),
    FOREIGN KEY (to_station_id) REFERENCES charging_station(id),
    UNIQUE KEY uk_route (from_station_id, to_station_id, route_type),
    INDEX idx_from_station (from_station_id),
    INDEX idx_to_station (to_station_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='路程信息表';
