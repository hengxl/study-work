use work_db;

create table t_user (
	name varchar(10) primary key,
    age int);

create table t_score(
	id int primary key auto_increment,
    username varchar(10),
	score_name varchar(10),
    grade double,
    unique key idx_unique_username_scorename (username, score_name)
    );

-- 插入4个用户，name作为主键（不重复），age为整数
insert into t_user (name, age) values ('张三', 20);
insert into t_user (name, age) values ('李四', 21);
insert into t_user (name, age) values ('王五', 22);
insert into t_user (name, age) values ('赵六', 20);

-- 张三的成绩：数学、语文
insert into t_score (username, score_name, grade) values ('张三', '数学', 88.5);
insert into t_score (username, score_name, grade) values ('张三', '语文', 92.0);

-- 李四的成绩：英语、数学
insert into t_score (username, score_name, grade) values ('李四', '英语', 79.5);
insert into t_score (username, score_name, grade) values ('李四', '数学', 95.0);

-- 王五的成绩：物理、化学
insert into t_score (username, score_name, grade) values ('王五', '物理', 85.0);
insert into t_score (username, score_name, grade) values ('王五', '化学', 80.0);

-- 赵六的成绩：语文、英语
insert into t_score (username, score_name, grade) values ('赵六', '语文', 76.5);
insert into t_score (username, score_name, grade) values ('赵六', '英语', 90.0);