DROP SCHEMA IF EXISTS ats2021foxtrot;

CREATE SCHEMA ats2021foxtrot;

USE ats2021foxtrot;

CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `firstName` nvarchar(25) NOT NULL,
  `lastName` nvarchar(25) NOT NULL,
  `sin` nvarchar(9) NOT NULL,
  `hourlyRate` decimal(13,2) NOT NULL,
  `isDeleted` tinyint(4) NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `deletedAt` datetime DEFAULT NULL);

CREATE TABLE `tasks` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` NVARCHAR(50) NOT NULL,
  `description` NVARCHAR(255) NOT NULL,
  `duration` INT NOT NULL,
  `createdAt` DATETIME NOT NULL,
  `updatedAt` DATETIME NULL);
  
  CREATE TABLE `employeetasks` (
  `employeeId` INT NOT NULL,
  `taskId` INT NOT NULL,
  INDEX `fk_emp_id_idx` (`employeeId` ASC),
  INDEX `fk_tsk_id_idx` (`taskId` ASC),
  CONSTRAINT `fk_emp_id`
    FOREIGN KEY (`employeeId`)
    REFERENCES `employees` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tsk_id`
    FOREIGN KEY (`taskId`)
    REFERENCES `tasks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- EMPLOYEE STORED PROCEDURES
    
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
	-- DELETE FROM invoices WHERE id = id_param;
    UPDATE employees 
    SET deletedAt = now(), isDeleted = true
    WHERE id = id_param;
    
    DELETE FROM employeetasks WHERE employeeId = id_param;
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

