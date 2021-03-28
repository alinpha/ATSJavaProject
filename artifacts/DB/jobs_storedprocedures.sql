DROP PROCEDURE IF EXISTS selectjobs;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE selectjobs (
IN date_param INT
)
BEGIN
	SELECT * FROM jobs
    WHERE
    WHERE start > date_param AND end < date_param;
    ORDER BY id;
END//
DELIMITER ;

-- TASK STORED PROCEDURES

DELIMITER //