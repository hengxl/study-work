USE fastmeeting_odm_tt;

ALTER TABLE t_user_function MODIFY COLUMN `meeting` varchar(100) DEFAULT NULL COMMENT '1通讯录呼叫,2即时开会,4预约会议';

DROP PROCEDURE IF EXISTS batch_update;

DELIMITER $$

CREATE PROCEDURE batch_update()
BEGIN
    DECLARE batch_size INT DEFAULT 2000;

    update_loop: LOOP
        UPDATE t_user_function
        SET meeting =
                CASE meeting
                    WHEN '0' THEN ''
                    WHEN '3' THEN '1,2'
                    WHEN '5' THEN '1,4'
                    WHEN '6' THEN '2,4'
                    WHEN '7' THEN '1,2,4'
                    ELSE meeting
                    END
        WHERE meeting IN ('0','3','5','6','7')
        LIMIT batch_size;

        IF ROW_COUNT() < batch_size THEN
            LEAVE update_loop;
        END IF;

    END LOOP update_loop;
END$$

DELIMITER ;

CALL batch_update();

DROP PROCEDURE IF EXISTS batch_update;