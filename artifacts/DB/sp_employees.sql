USE ats2021foxtrot;

-- EMPLOYEE STORED PROCEDURES

DELIMITER //
DROP PROCEDURE IF EXISTS select_employees_in_team;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE select_employees_in_team (
IN team_id_param int
)
BEGIN
	SELECT * FROM employees where id in (select employeeId from teammembers where teamId = team_id_param);
END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS selectemployees_sin;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE selectemployees_sin (
IN sin_param varchar(11)
)
BEGIN
	SELECT * FROM employees where sin like sin_param
    ORDER BY createdAt;
END//
DELIMITER ;
call selectemployees_sin('%111111111%')
DELIMITER //
DROP PROCEDURE IF EXISTS selectemployees_lastname;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE selectemployees_lastname (
IN l_param varchar(25)
)
BEGIN
	SELECT * FROM employees where lastName like l_param
    ORDER BY createdAt;
END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS add_employee_skill;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE add_employee_skill(
IN emp_id_param int,
IN tsk_id_param int,
OUT id_out INT
)
BEGIN

	INSERT INTO employeetasks(employeeId,taskId)
    VALUES(emp_id_param,tsk_id_param);
    
    SET id_out = LAST_INSERT_ID();

END//
DELIMITER ;
    
DELIMITER //
DROP PROCEDURE IF EXISTS insertemployee;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE insertemployee(
IN firstName_param NVARCHAR(25),
IN lastName_param NVARCHAR(25),
IN sin_param NVARCHAR(9),
IN hourlyRate_param DECIMAL(13,2),
OUT id_out INT
)
BEGIN

	INSERT INTO employees(firstName,lastName,sin,hourlyRate,isDeleted,createdAt)
    VALUES(firstName_param,lastName_param,sin_param,hourlyRate_param,false,now());
    
    SET id_out = LAST_INSERT_ID();

END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS deleteemployee
// DELIMITER;

DELIMITER //
CREATE PROCEDURE deleteemployee (
IN id_param INT
)
BEGIN
	SET @count = (select count(*) from teammembers where employeeId = id_param);
	IF (@count > 0)
    THEN 
    UPDATE employees 
    SET deletedAt = now(), isDeleted = true
    WHERE id = id_param;
    else
    DELETE FROM employees WHERE id = id_param;
    END IF;
    
END//
DELIMITER;

DELIMITER //
DROP PROCEDURE IF EXISTS updateemployee;
// DELIMITER ;

DELIMITER //
CREATE PROCEDURE updateemployee(
IN id_param INT,
IN firstName_param NVARCHAR(25),
IN lastName_param NVARCHAR(25),
IN sin_param NVARCHAR(9),
IN hourlyRate_param DECIMAL(13,2)
)
BEGIN

	UPDATE employees 
    SET
    firstName = IF(firstName_param IS NULL,firstName,firatName_param),
    lastName = IF(lastName_param IS NULL,lastName,lastName_param),
    sin = IF(sin_param IS NULL,sin,sin_param),
    hourlyRate = IF(hourlyRate_param IS NULL,hourlyRate,hourlyRate_param),
    updatedAt = now()
    WHERE id = id_param;

END;//
DELIMITER;

DELIMITER //
DROP PROCEDURE IF EXISTS selectemployees;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE selectemployees (
IN id_param INT
)
BEGIN
	SELECT * FROM employees
    WHERE
    (id_param IS NULL OR id = id_param)
    ORDER BY createdAt;
END//
DELIMITER ;


DELIMITER //
DROP PROCEDURE IF EXISTS select_employee_teams;
// DELIMITER;

DELIMITER //

CREATE PROCEDURE select_employee_teams(
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
DROP PROCEDURE IF EXISTS select_employee_teams;
// DELIMITER;

DELIMITER //

CREATE  PROCEDURE select_employee_tasks(
IN emp_id_param INT
)
BEGIN
	SELECT * FROM tasks
    WHERE
    id in (select taskId from employeetasks where employeeId = emp_id_param)
    ORDER BY createdAt;
END//
DELIMITER ;