-- 创建数据库（不存在则创建）
CREATE DATABASE IF NOT EXISTS multi_db;

-- 切换至目标数据库
USE multi_db;

-- 删除日历基础表（存在则删除）
DROP TABLE IF EXISTS t_calendar;

-- 删除基础用户消费模板表（存在则删除）
DROP TABLE IF EXISTS t_deduction;

-- 删除直播间在线总人数表
DROP TABLE IF EXISTS `t_online_num`;

-- 创建日历表
CREATE TABLE t_calendar (
    date_list DATE NOT NULL COMMENT '日历日期（唯一非空）',
    PRIMARY KEY (date_list) -- 保证日期唯一，提升查询性能
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '日历基础表';

-- 创建基础用户消费模板表
CREATE TABLE t_deduction (
    id INT AUTO_INCREMENT COMMENT '主键id',
    start_time DATETIME DEFAULT NULL COMMENT '开始时间',
    end_time DATETIME DEFAULT NULL COMMENT '结束时间',
    data_size DECIMAL(20,6) DEFAULT NULL COMMENT '消费数据量',
    PRIMARY KEY (id),
    KEY idx_end_time (end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '基础用户消费模板表';

-- 创建最大在线人数表
CREATE TABLE `t_online_num` (
    `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `room_id` INT(11) DEFAULT NULL COMMENT '直播间id',
    `total_online_num` INT(11) DEFAULT NULL COMMENT '总在线人数',
    `start_time` VARCHAR(50) DEFAULT NULL COMMENT '开始时间',
    `end_time` VARCHAR(50) DEFAULT NULL COMMENT '结束时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='直播间在线总人数表';
