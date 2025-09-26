USE my_db;

DROP TABLE IF EXISTS `t_user_function`;

CREATE TABLE `t_user_function` (
   `user_id` int NOT NULL,
   `meeting` int DEFAULT NULL COMMENT '1通讯录呼叫、2即时开会、4预约会议,3通讯录呼叫和即时开会，5通讯录呼叫和预约会议，6即时开会和预约会议，7三项全有',
   PRIMARY KEY (`user_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `t_user_function` (`user_id`, `meeting`)
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 1),
(9, 2),
(10, 3);

COMMIT;

SELECT * FROM `t_user_function`;