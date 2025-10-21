USE fastmeeting_odm_tt;

ALTER TABLE t_user_function MODIFY COLUMN `meeting` int DEFAULT NULL COMMENT '0 无权限，1通讯录呼叫、2即时开会、4预约会议,3通讯录呼叫和即时开会，5通讯录呼叫和预约会议，6即时开会和预约会议，7三项全有';

DROP PROCEDURE IF EXISTS batch_update;

DELIMITER $$

CREATE PROCEDURE batch_update()
BEGIN
    DECLARE batch_size INT DEFAULT 2000;

    update_loop: LOOP
        UPDATE t_user_function
        SET meeting =
                CASE meeting
                    WHEN '' THEN '0'
                    WHEN '1,2' THEN '3'
                    WHEN '1,4' THEN '5'
                    WHEN '2,4' THEN '6'
                    WHEN '1,2,4' THEN '7'
                    ELSE meeting
                    END
        WHERE meeting IN ('','1,2','1,4','2,4','1,2,4')
        LIMIT batch_size;

        IF ROW_COUNT() < batch_size THEN
            LEAVE update_loop;
        END IF;

    END LOOP update_loop;
END$$

DELIMITER ;

CALL batch_update();

DROP PROCEDURE IF EXISTS batch_update;