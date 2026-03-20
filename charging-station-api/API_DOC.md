# 充电桩查询API接口文档

## 项目概述
本项目是一个基于Spring Boot的充电桩信息查询后端API服务，支持根据区域查询充电桩占用信息及路程信息。

## 技术栈
- 后端框架：Spring Boot 3.2.3
- ORM框架：MyBatis 3.0.3
- 序列化：Gson
- 数据库：MySQL
- Java版本：17

## 数据库初始化

### 执行SQL脚本
请在MySQL中执行 `src/main/resources/sql/init.sql` 文件，该文件包含：
1. 数据库创建（charging_station）
2. 表结构创建（region、charging_station、charging_port）
3. 测试数据插入

### 数据库连接配置
配置文件位于 `src/main/resources/application.properties`：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/charging_station?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=xuyi2633
```

## API接口

### 1. 获取区域列表
根据父区域编码获取子区域列表

**请求方式**：GET  
**请求URL**：`/api/region/list`

**请求参数**：
| 参数名       | 类型   | 必填 | 说明               |
|--------------|--------|------|--------------------|
| parentCode   | String | 否   | 父区域编码，如null表示顶级区域 |

**响应示例**：
```json
{
    "code": 200,
    "message": "success",
    "data": [
        {
            "id": 1,
            "name": "北京市",
            "code": "110000",
            "parentCode": null,
            "level": 1,
            "longitude": 116.403874,
            "latitude": 39.914885
        }
    ]
}
```

---

### 2. 获取区域详情
根据区域编码获取区域详情

**请求方式**：GET  
**请求URL**：`/api/region/detail`

**请求参数**：
| 参数名 | 类型   | 必填 | 说明     |
|--------|--------|------|----------|
| code   | String | 是   | 区域编码 |

---

### 3. 根据区域查询充电桩列表
支持按区域、用户位置计算距离、排序等

**请求方式**：POST  
**请求URL**：`/api/station/list`

**请求参数（JSON Body）**：
| 参数名         | 类型       | 必填 | 说明                                               |
|----------------|------------|------|----------------------------------------------------|
| regionCode     | String     | 是   | 区域编码，如110105表示朝阳区                       |
| userLongitude  | BigDecimal | 否   | 用户当前经度，用于计算距离                         |
| userLatitude   | BigDecimal | 否   | 用户当前纬度，用于计算距离                         |
| status         | Integer    | 否   | 状态筛选，1:在线，0:离线，2:维护中                 |
| sortBy         | String     | 否   | 排序方式：distance(距离)、available(可用数量)、price(价格) |
| pageNum        | Integer    | 否   | 页码，默认1                                        |
| pageSize       | Integer    | 否   | 每页大小，默认10                                   |

**请求示例**：
```json
{
    "regionCode": "110105",
    "userLongitude": 116.45,
    "userLatitude": 39.92,
    "sortBy": "distance"
}
```

**响应示例**：
```json
{
    "code": 200,
    "message": "success",
    "data": [
        {
            "id": 1,
            "stationCode": "BJ-CY-001",
            "name": "朝阳公园充电站",
            "regionName": "朝阳区",
            "address": "北京市朝阳区朝阳公园南门停车场",
            "longitude": 116.478901,
            "latitude": 39.933452,
            "totalPorts": 10,
            "availablePorts": 6,
            "power": 120.00,
            "pricePerKwh": 1.5000,
            "status": 1,
            "distance": 2.35
        }
    ]
}
```

---

### 4. 获取充电桩详情
获取单个充电桩的详细信息，包括充电口状态

**请求方式**：GET  
**请求URL**：`/api/station/detail/{id}`

**请求参数**：
| 参数名         | 类型       | 必填 | 说明                       |
|----------------|------------|------|----------------------------|
| id             | Long       | 是   | 充电桩ID，路径参数         |
| userLongitude  | BigDecimal | 否   | 用户当前经度，用于计算距离 |
| userLatitude   | BigDecimal | 否   | 用户当前纬度，用于计算距离 |

**响应示例**：
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "id": 1,
        "stationCode": "BJ-CY-001",
        "name": "朝阳公园充电站",
        "regionName": "朝阳区",
        "address": "北京市朝阳区朝阳公园南门停车场",
        "totalPorts": 10,
        "availablePorts": 6,
        "distance": 2.35,
        "ports": [
            {
                "portCode": "BJ-CY-001-01",
                "portType": "快充",
                "status": 1,
                "chargedKwh": 45.50,
                "remainingMinutes": 45
            }
        ]
    }
}
```

---

### 5. 更新充电桩可用端口数量
同步更新充电桩的可用端口数量

**请求方式**：PUT  
**请求URL**：`/api/station/update-available/{id}`

**请求参数**：
| 参数名 | 类型 | 必填 | 说明       |
|--------|------|------|------------|
| id     | Long | 是   | 充电桩ID   |

---

## 数据字典

### 区域级别（level）
| 值 | 说明   |
|----|--------|
| 1  | 省/直辖市 |
| 2  | 城市   |
| 3  | 区/县  |

### 充电桩状态（status）
| 值 | 说明   |
|----|--------|
| 0  | 离线   |
| 1  | 在线   |
| 2  | 维护中 |

### 充电口状态（status）
| 值 | 说明   |
|----|--------|
| 0  | 空闲   |
| 1  | 占用   |
| 2  | 故障   |

## 启动项目

### 使用Maven启动
```bash
# 设置JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-21.0.10

# 启动项目
mvnw.cmd spring-boot:run
```

### 访问地址
项目启动后，访问地址为：`http://localhost:8080`

## 测试区域编码
- 北京市：110000
  - 朝阳区：110105
  - 海淀区：110108
- 上海市：310000
  - 浦东新区：310115
- 广州市：440100
  - 天河区：440106
- 深圳市：440300
  - 南山区：440305
