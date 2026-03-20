-- 创建数据库
CREATE DATABASE IF NOT EXISTS charging_station CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE charging_station;

-- 创建区域表
CREATE TABLE IF NOT EXISTS `region` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '区域ID',
    `name` VARCHAR(100) NOT NULL COMMENT '区域名称',
    `code` VARCHAR(50) NOT NULL UNIQUE COMMENT '区域编码',
    `parent_code` VARCHAR(50) COMMENT '父区域编码',
    `level` INT NOT NULL COMMENT '区域级别(1:省, 2:市, 3:区/县)',
    `longitude` DECIMAL(10, 7) COMMENT '区域中心经度',
    `latitude` DECIMAL(10, 7) COMMENT '区域中心纬度',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_code (`code`),
    INDEX idx_parent_code (`parent_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区域表';

-- 创建充电桩表
CREATE TABLE IF NOT EXISTS `charging_station` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '充电桩ID',
    `station_code` VARCHAR(50) NOT NULL UNIQUE COMMENT '充电桩编号',
    `name` VARCHAR(100) NOT NULL COMMENT '充电桩名称',
    `region_code` VARCHAR(50) NOT NULL COMMENT '所属区域编码',
    `address` VARCHAR(200) NOT NULL COMMENT '详细地址',
    `longitude` DECIMAL(10, 7) NOT NULL COMMENT '经度',
    `latitude` DECIMAL(10, 7) NOT NULL COMMENT '纬度',
    `total_ports` INT NOT NULL DEFAULT 0 COMMENT '总充电口数量',
    `available_ports` INT NOT NULL DEFAULT 0 COMMENT '可用充电口数量',
    `power` DECIMAL(5, 2) COMMENT '充电功率(kW)',
    `price_per_kwh` DECIMAL(8, 4) COMMENT '每度电价格(元)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0:离线, 1:在线, 2:维护中)',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_region_code (`region_code`),
    INDEX idx_status (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充电桩表';

-- 创建充电口占用信息表
CREATE TABLE IF NOT EXISTS `charging_port` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '充电口ID',
    `port_code` VARCHAR(50) NOT NULL UNIQUE COMMENT '充电口编号',
    `station_id` BIGINT NOT NULL COMMENT '所属充电桩ID',
    `port_type` VARCHAR(20) COMMENT '充电口类型(快充/慢充)',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0:空闲, 1:占用, 2:故障)',
    `current_order_no` VARCHAR(50) COMMENT '当前充电订单号',
    `start_charging_time` TIMESTAMP NULL COMMENT '开始充电时间',
    `expected_end_time` TIMESTAMP NULL COMMENT '预计结束时间',
    `charged_kwh` DECIMAL(8, 2) DEFAULT 0 COMMENT '已充电量(kWh)',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_station_id (`station_id`),
    INDEX idx_status (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充电口表';

-- 插入区域测试数据
INSERT IGNORE INTO `region` (`name`, `code`, `parent_code`, `level`, `longitude`, `latitude`) VALUES
('北京市', '110000', NULL, 1, 116.403874, 39.914885),
('上海市', '310000', NULL, 1, 121.473701, 31.230416),
('广州市', '440100', '440000', 2, 113.264434, 23.129162),
('深圳市', '440300', '440000', 2, 114.066112, 22.548515),
('杭州市', '330100', '330000', 2, 120.155070, 30.274084),
('朝阳区', '110105', '110000', 3, 116.443108, 39.921470),
('海淀区', '110108', '110000', 3, 116.310316, 39.956074),
('浦东新区', '310115', '310000', 3, 121.544379, 31.231706),
('天河区', '440106', '440100', 3, 113.330930, 23.125599),
('南山区', '440305', '440300', 3, 113.930303, 22.534794);

-- 插入充电桩测试数据
INSERT IGNORE INTO `charging_station` 
(`station_code`, `name`, `region_code`, `address`, `longitude`, `latitude`, `total_ports`, `available_ports`, `power`, `price_per_kwh`, `status`) VALUES
('BJ-CY-001', '朝阳公园充电站', '110105', '北京市朝阳区朝阳公园南门停车场', 116.478901, 39.933452, 10, 6, 120.00, 1.5000, 1),
('BJ-CY-002', '国贸充电站', '110105', '北京市朝阳区国贸地下停车场B3层', 116.460948, 39.908761, 20, 12, 180.00, 1.8000, 1),
('BJ-HD-001', '中关村软件园充电站', '110108', '北京市海淀区中关村软件园一期地下停车场', 116.302145, 39.987634, 15, 8, 150.00, 1.6000, 1),
('SH-PD-001', '陆家嘴金融中心充电站', '310115', '上海市浦东新区陆家嘴环路1000号', 121.501234, 31.245678, 25, 18, 200.00, 2.0000, 1),
('SH-PD-002', '张江高科技园充电站', '310115', '上海市浦东新区张江高科技园区博云路2号', 121.589012, 31.207890, 12, 5, 120.00, 1.7000, 1),
('GZ-TH-001', '天河城购物中心充电站', '440106', '广州市天河区天河路208号天河城停车场', 113.329012, 23.135678, 18, 10, 150.00, 1.6000, 1),
('SZ-NS-001', '科技园充电站', '440305', '深圳市南山区科技园南区深南大道9996号', 113.951234, 22.543210, 30, 22, 180.00, 1.9000, 1),
('HZ-XH-001', '西湖文化广场充电站', '330100', '杭州市西湖区西湖文化广场地下停车场', 120.156789, 30.278901, 16, 9, 120.00, 1.5500, 1);

-- 插入充电口测试数据
INSERT IGNORE INTO `charging_port` (`port_code`, `station_id`, `port_type`, `status`, `current_order_no`, `start_charging_time`, `expected_end_time`, `charged_kwh`) VALUES
('BJ-CY-001-01', 1, '快充', 1, 'ORD202401150001', '2024-01-15 09:30:00', '2024-01-15 11:30:00', 45.50),
('BJ-CY-001-02', 1, '快充', 0, NULL, NULL, NULL, 0),
('BJ-CY-001-03', 1, '快充', 1, 'ORD202401150002', '2024-01-15 10:15:00', '2024-01-15 12:15:00', 28.30),
('BJ-CY-001-04', 1, '慢充', 0, NULL, NULL, NULL, 0),
('BJ-CY-001-05', 1, '慢充', 2, NULL, NULL, NULL, 0),
('BJ-CY-001-06', 1, '慢充', 1, 'ORD202401150003', '2024-01-15 08:00:00', '2024-01-15 16:00:00', 35.20),
('BJ-CY-001-07', 1, '快充', 0, NULL, NULL, NULL, 0),
('BJ-CY-001-08', 1, '快充', 1, 'ORD202401150004', '2024-01-15 11:00:00', '2024-01-15 13:00:00', 15.80),
('BJ-CY-001-09', 1, '慢充', 0, NULL, NULL, NULL, 0),
('BJ-CY-001-10', 1, '慢充', 0, NULL, NULL, NULL, 0),
('BJ-CY-002-01', 2, '快充', 1, 'ORD202401150005', '2024-01-15 09:00:00', '2024-01-15 10:30:00', 60.00),
('BJ-CY-002-02', 2, '快充', 1, 'ORD202401150006', '2024-01-15 09:15:00', '2024-01-15 11:00:00', 52.30),
('BJ-CY-002-03', 2, '快充', 0, NULL, NULL, NULL, 0),
('BJ-HD-001-01', 3, '快充', 1, 'ORD202401150007', '2024-01-15 08:30:00', '2024-01-15 10:00:00', 38.50),
('SH-PD-001-01', 4, '快充', 1, 'ORD202401150008', '2024-01-15 10:00:00', '2024-01-15 12:00:00', 42.00),
('SH-PD-002-01', 5, '慢充', 1, 'ORD202401150009', '2024-01-15 07:00:00', '2024-01-15 15:00:00', 55.60),
('GZ-TH-001-01', 6, '快充', 0, NULL, NULL, NULL, 0),
('SZ-NS-001-01', 7, '快充', 1, 'ORD202401150010', '2024-01-15 10:30:00', '2024-01-15 12:30:00', 33.20),
('HZ-XH-001-01', 8, '慢充', 0, NULL, NULL, NULL, 0);
