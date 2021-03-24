USE ats2021foxtrot;

-- TEAM STORED PROCEDURES

DELIMITER //
DROP PROCEDURE IF EXISTS insertteam;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE insertteam(
IN name_param varchar(50),
IN ioc_param tinyint(4),
OUT id_out INT
)
BEGIN

	INSERT INTO teams(name,isOnCall,isDeleted,createdAt)
    VALUES(name_param,ioc_param,false,now());
    
    SET id_out = LAST_INSERT_ID();

END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS insert_team_member;
// DELIMITER;

DELIMITER //
DROP PROCEDURE IF EXISTS selectteams;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE selectteams (
IN id_param INT
)
BEGIN
	SELECT * FROM teams
    WHERE
    (id_param IS NULL OR id = id_param)
    ORDER BY createdAt;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE insert_team_member(
IN emp_param int,
IN team_param int
)
BEGIN

	INSERT INTO teammembers(employeeId,teamId)
    VALUES(emp_param,team_param);

END//
DELIMITER ;

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

