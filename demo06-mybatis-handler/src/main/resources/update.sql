USE my_db;

ALTER TABLE `t_user_function`
MODIFY COLUMN `meeting` varchar(100) DEFAULT NULL COMMENT '1通讯录呼叫,2即时开会,3预约会议';

-- 只更新需要更改的行
UPDATE t_user_function
SET meeting =
    CASE
        WHEN meeting = '3' THEN '1,2'
        WHEN meeting = '5' THEN '1,4'
        WHEN meeting = '6' THEN '2,4'
        WHEN meeting = '7' THEN '1,2,4'
    END
WHERE meeting IN ('3','5','6','7');