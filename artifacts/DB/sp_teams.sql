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

DELIMITER //
DROP PROCEDURE IF EXISTS updateteammember;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE updateteammember(
IN emp_param int,
IN team_param int
)
BEGIN

	UPDATE teammembers 
    SET
    employeeId = IF(emp_param IS NULL,employeeId,emp_param),
    teamId = IF(team_param IS NULL,teamId,team_param)
    WHERE id = id_param;

END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS updateteam;
// DELIMITER;

DELIMITER //

CREATE PROCEDURE updateteam(
IN id_param INT,
IN name_param NVARCHAR(50),
IN ioc_param tinyint(4)
)
BEGIN
SET @count = (select count(*) from teams where isOnCall = 1);
	IF (@count = 0)
    THEN 
	UPDATE teams 
    SET
    name = IF(name_param IS NULL,name,name_param),
    isOnCall = IF(ioc_param IS NULL,isOnCall,ioc_param),
    updatedAt = now()
    WHERE id = id_param;
	END IF;
END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS select_teammembers;
// DELIMITER;

DELIMITER //

CREATE PROCEDURE select_teammembers(
IN id_param INT
)
BEGIN
	SELECT teams.id, teams.name, teams.isOnCall, teammembers.employeeid, employees.firstName, employees.lastName FROM teams
		JOIN teammembers ON teams.id = teammembers.teamId
		JOIN employees ON teammembers.employeeId = employees.id
	WHERE
    (id_param IS NULL OR teams.id = id_param)
    ORDER BY teams.id;
END//
DELIMITER ;


DELIMITER //
DROP PROCEDURE IF EXISTS deleteteam;
// DELIMITER;

DELIMITER //

CREATE PROCEDURE deleteteam(
IN id_param INT
)
BEGIN
	SET @count = (select count(*) from jobs where teamId = id_param);
	IF (@count > 0)
    THEN 
    UPDATE teams 
    SET deletedAt = now(), isDeleted = true
    WHERE id = id_param;
    else
    DELETE FROM teams WHERE id = id_param;
    END IF;
    
END//
DELIMITER ;
