create database if not exists multi_db;

use multi_db;

-- 创建模板表
create table if not exists t_user (
    user_id BIGINT PRIMARY KEY,
    username char(64) unique,
    table_name char(64) unique
);

create table if not exists t_room (
    room_id BIGINT PRIMARY KEY,
    room_name char(64) unique,
    table_name char(64) unique
);