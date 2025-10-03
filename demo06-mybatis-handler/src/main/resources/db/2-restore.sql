USE my_db;

ALTER TABLE t_user_function MODIFY COLUMN `meeting` varchar(100) DEFAULT NULL COMMENT '1通讯录呼叫,2即时开会,4预约会议';

DROP PROCEDURE IF EXISTS batch_restore;

delimiter $$

create procedure batch_restore()
BEGIN
    DECLARE batch_size INT DEFAULT 2000;

    restore_loop: LOOP
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
        WHERE meeting IN ('1,2', '1,4', '2,4', '1,2,4')
        LIMIT batch_size;

        IF ROW_COUNT() < batch_size THEN
            LEAVE restore_loop;
        END IF;

    END LOOP restore_loop;
END$$

DELIMITER ;

CALL batch_restore();

DROP PROCEDURE IF EXISTS batch_restore;