USE ats2021foxtrot;

-- TEAM STORED PROCEDURES


DELIMITER //
DROP PROCEDURE IF EXISTS select_employee_teams;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE select_employee_teams (
IN emp_id_param INT
)
BEGIN
	SELECT * FROM teams
    WHERE
    id in (select teamId from teammembers where employeeId = emp_id_param)
    ORDER BY createdAt;
END//
DELIMITER ;

