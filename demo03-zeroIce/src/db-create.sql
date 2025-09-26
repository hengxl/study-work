USE `my_db`;

CREATE TABLE IF NOT EXISTS `t_sensitive_word` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `company_id` int(11) NOT NULL COMMENT '企业ID',
  `words` varchar(1000) DEFAULT NULL COMMENT '敏感词，多个用空格分隔',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='敏感词表';