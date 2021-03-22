USE ats2021foxtrot;

-- TASK STORED PROCEDURES

    DELIMITER //
DROP PROCEDURE IF EXISTS inserttask;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE inserttask(
IN name_param NVARCHAR(50),
IN desc_param NVARCHAR(255),
IN duration_param int,
OUT id_out INT
)
BEGIN

	INSERT INTO tasks(name,description,duration,createdAt)
    VALUES(name_param,desc_param,duration_param,now());
    
    SET id_out = LAST_INSERT_ID();

END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS deletetask
// DELIMITER;

DELIMITER //
CREATE PROCEDURE deletetask (
IN id_param INT
)
BEGIN
	DELETE FROM tasks WHERE id = id_param;
    DELETE FROM employeetasks WHERE taskId = id_param;
END//
DELIMITER;

DELIMITER //
DROP PROCEDURE IF EXISTS updatetask;
// DELIMITER ;

DELIMITER //
CREATE PROCEDURE updatetask(
IN id_param INT,
IN name_param NVARCHAR(50),
IN desc_param NVARCHAR(255),
IN duration_param int
)
BEGIN

	UPDATE tasks 
    SET
    name = IF(name_param IS NULL,name,name_param),
    description = IF(desc_param IS NULL,description,desc_param),
    duration = IF(duration_param IS NULL,duration,duration_param),
    updatedAt = now()
    WHERE id = id_param;

END;//
DELIMITER;

DELIMITER //
DROP PROCEDURE IF EXISTS selecttasks;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE selecttasks (
IN id_param INT
)
BEGIN
	SELECT * FROM tasks
    WHERE
    (id_param IS NULL OR id = id_param)
    ORDER BY createdAt;
END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS select_employee_tasks;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE select_employee_tasks (
IN emp_id_param INT
)
BEGIN
	SELECT * FROM tasks
    WHERE
    id in (select taskId from employeetasks where employeeId = emp_id_param)
    ORDER BY createdAt;
END//
DELIMITER ;
