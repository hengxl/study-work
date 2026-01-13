-- 创建数据库（不存在则创建）
CREATE DATABASE IF NOT EXISTS multi_db;

-- 切换至目标数据库
USE multi_db;

-- 删除2026年1月用户消费分月表（存在则删除）
DROP TABLE IF EXISTS t_deduction_2026_1;

-- 删除基础用户消费模板表（存在则删除）
DROP TABLE IF EXISTS t_deduction;

-- 删除日历基础表（存在则删除）
DROP TABLE IF EXISTS t_calendar;

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

-- 创建2026年1月用户消费表
CREATE TABLE t_deduction_2026_1 (
    id INT AUTO_INCREMENT COMMENT '主键id',
    start_time DATETIME DEFAULT NULL COMMENT '开始时间',
    end_time DATETIME DEFAULT NULL COMMENT '结束时间',
    data_size DECIMAL(20,6) DEFAULT NULL COMMENT '消费数据量',
    PRIMARY KEY (id),
    KEY idx_end_time (end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '基础用户消费模板表（2026年1月）';

-- 批量插入t_deduction_2026_1模拟消费数据（移除 username，匹配表结构）
INSERT INTO t_deduction_2026_1 (start_time, end_time, data_size)
VALUES
    -- 2026-01-01 消费记录（1条，验证单条数据统计）
    ('2026-01-01 08:30:00', '2026-01-01 18:45:00', 100.500000),
    -- 2026-01-03 消费记录（1条，大额单条数据）
    ('2026-01-03 09:15:00', '2026-01-03 20:30:00', 200.800000),

    -- 2026-01-02 消费记录（2条，验证 sum 求和逻辑，总金额 50.200000+78.900000=129.100000）
    ('2026-01-02 10:00:00', '2026-01-02 16:20:00', 50.200000),
    ('2026-01-02 17:00:00', '2026-01-02 22:50:00', 78.900000),

    -- 2026-01-05 消费记录（1条，超大额数据，验证高精度小数统计）
    ('2026-01-05 07:40:00', '2026-01-05 19:10:00', 500.123456),

    -- 2026-01-08 消费记录（2条，验证小额数据求和，总金额 10.000000+25.678901=35.678901）
    ('2026-01-08 11:20:00', '2026-01-08 14:30:00', 10.000000),
    ('2026-01-08 15:00:00', '2026-01-08 18:00:00', 25.678901);

-- 批量插入t_calendar表2026年1月（1日-31日）完整日历数据
INSERT INTO t_calendar (date_list)
VALUES
    ('2026-01-01'), ('2026-01-02'), ('2026-01-03'), ('2026-01-04'), ('2026-01-05'),
    ('2026-01-06'), ('2026-01-07'), ('2026-01-08'), ('2026-01-09'), ('2026-01-10'),
    ('2026-01-11'), ('2026-01-12'), ('2026-01-13'), ('2026-01-14'), ('2026-01-15'),
    ('2026-01-16'), ('2026-01-17'), ('2026-01-18'), ('2026-01-19'), ('2026-01-20'),
    ('2026-01-21'), ('2026-01-22'), ('2026-01-23'), ('2026-01-24'), ('2026-01-25'),
    ('2026-01-26'), ('2026-01-27'), ('2026-01-28'), ('2026-01-29'), ('2026-01-30'),
    ('2026-01-31');