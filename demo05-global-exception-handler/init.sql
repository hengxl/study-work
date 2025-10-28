USE my_db;

-- 重置表
-- DROP TABLE IF EXIST t_user;
-- DROP TABLE IF EXIST t_product;
-- DROP TABLE IF EXIST t_warehouse;

-- 用户表：存储用户信息
CREATE TABLE t_user (
    user_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    balance DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '账户余额',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '用户表';

-- 商品表：存储商品信息（含库存）
CREATE TABLE t_product (
    product_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
    product_name VARCHAR(100) NOT NULL COMMENT '商品名称',
    stock INT NOT NULL DEFAULT 0 COMMENT '商品库存数量',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '商品表';

-- 仓库表：记录用户拥有的商品（购买后存入仓库）
CREATE TABLE t_warehouse (
    warehouse_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '仓库记录ID',
    user_id INT NOT NULL COMMENT '所属用户ID',
    product_id INT NOT NULL COMMENT '商品ID',
    product_name VARCHAR(100) NOT NULL COMMENT '商品名称（冗余，方便查询）',
    quantity INT NOT NULL DEFAULT 0 COMMENT '持有数量',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '存入时间'
) COMMENT '用户仓库表';

-- 初始化5个用户（余额不同，方便测试购买能力）
INSERT INTO t_user (username, balance) VALUES
('张三', 1000.00),
('李四', 500.00),
('王五', 2000.00),
('赵六', 800.00),
('孙七', 1500.00);

-- 初始化5个商品（库存和价格不同，覆盖不同场景）
INSERT INTO t_product (product_name, stock, price) VALUES
('无线鼠标', 100, 89.99),   -- 低价商品
('机械键盘', 50, 299.99),   -- 中价商品
('蓝牙耳机', 30, 499.99),   -- 高价商品
('充电宝', 200, 59.99),     -- 低价大库存
('笔记本电脑', 10, 5999.99); -- 高价低库存

-- 初始化仓库数据（初始状态用户可能持有部分商品）
INSERT INTO t_warehouse (user_id, product_id, product_name, quantity) VALUES
(1, 1, '无线鼠标', 2),   -- 张三有2个无线鼠标
(2, 3, '蓝牙耳机', 1),   -- 李四有1个蓝牙耳机
(3, 5, '笔记本电脑', 1), -- 王五有1台笔记本
(4, 4, '充电宝', 3),     -- 赵六有3个充电宝
(5, 2, '机械键盘', 1);   -- 孙七有1个机械键盘