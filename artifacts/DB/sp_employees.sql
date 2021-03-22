USE ats2021foxtrot;

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
    
    -- DELETE FROM employeetasks WHERE employeeId = id_param;
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


