create database if not exists multi_db;

use multi_db;

create table if not exists t_table (
    id BIGINT PRIMARY KEY,
    table_name char(64) unique
)