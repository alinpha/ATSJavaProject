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
    
CREATE TABLE `teams` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` NVARCHAR(50) NOT NULL,
  `isOnCall` tinyint(4) NOT NULL,
  `isDeleted` tinyint(4) NOT NULL,
  `createdAt` DATETIME NOT NULL,
  `updatedAt` DATETIME NULL,
  `deletedAt` DATETIME NULL);
  
CREATE TABLE `teammembers` (
  `employeeId` INT NOT NULL,
  `teamId` INT NOT NULL,
  INDEX `fk_emp2_id_idx` (`employeeId` ASC),
  INDEX `fk_tem2_id_idx` (`teamId` ASC),
  CONSTRAINT `fk_emp2_id`
    FOREIGN KEY (`employeeId`)
    REFERENCES `employees` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tem2_id`
    FOREIGN KEY (`teamId`)
    REFERENCES `teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `jobs` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `teamId` INT NOT NULL,
  `description` NVARCHAR(255) NOT NULL,
  `clientName` NVARCHAR(25) NOT NULL,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `isOnSite` tinyint(4) NOT NULL,
  INDEX `fk_tem3_id_idx` (`teamId` ASC),
  CONSTRAINT `fk_tem3_id`
    FOREIGN KEY (`teamId`)
    REFERENCES `teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
   
CREATE TABLE `jobtasks` (
  `taskId` INT NOT NULL,
  `jobId` INT NOT NULL,
  `operationCost` decimal(13,2) NOT NULL,
  `operationRevenue` decimal(13,2) NOT NULL,
  INDEX `fk_tsk4_id_idx` (`taskId` ASC),
  INDEX `fk_job4_id_idx` (`jobId` ASC),
  CONSTRAINT `fk_tsk4_id`
    FOREIGN KEY (`taskId`)
    REFERENCES `tasks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job4_id`
    FOREIGN KEY (`jobId`)
    REFERENCES `jobs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


DELIMITER //
DROP PROCEDURE IF EXISTS select_today_jobs;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE select_today_jobs(
)
BEGIN

	select * from jobs where date(start) = curdate();

END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS select_jobs_for_dates;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE select_jobs_for_dates(
IN team_id_param int,
IN start_param datetime,
IN end_param datetime
)
BEGIN

	select * from jobs 
		where ((start >= start_param and start <= end_param)
			or (end >= start_param and end <= end_param))
			and (teamId = team_id_param);

END//
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS insertjob;
// DELIMITER;

DELIMITER //
CREATE PROCEDURE insertjob(
IN team_id_param int,
IN desc_param VARCHAR(255),
IN name_param VARCHAR(25),
IN start_param datetime,
IN end_param datetime,
in is_on_site_param tinyint(4),
OUT id_out INT
)
BEGIN

	INSERT INTO jobs(teamId,description,clientName,start,end,isOnSite)
    VALUES(team_id_param,desc_param,name_param,start_param,end_param,is_on_site_param);
    
    SET id_out = LAST_INSERT_ID();

END//
DELIMITER ;

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

INSERT INTO `ats2021foxtrot`.`employees`
(`id`,
`firstName`,
`lastName`,
`sin`,
`hourlyRate`,
`isDeleted`,
`createdAt`,
`updatedAt`,
`deletedAt`)
VALUES
(1, "Bob", "Marley", 444444444, 50.00, 1, "2021-01-01 09:00:00", null, "2021-04-16 09:00:00"),
(2, "Jay", "Donald", 111111111, 20.00, 0, "2021-02-01 09:00:00", "2021-04-16 09:00:00", null),
(3, "Mariah", "Carey", 222222222, 12.00, 1, "2021-03-03 09:00:00", "2021-04-15 09:00:00", "2021-04-16 09:00:00"),
(4, "Michael", "Jackson", 333333333, 120.00, 0, "2021-03-01 09:00:00", "2021-04-16 09:00:00", null),
(5, "Aline", "Vetrov", 999999999, 60.00, 0, "2021-03-01 09:00:00", "2021-04-16 09:00:00", null),
(6, "Izes", "Souto", 888888888, 45.00, 0, "2021-03-01 09:00:00", "2021-04-16 09:00:00", null);

INSERT INTO `ats2021foxtrot`.`tasks`
(`id`,
`name`,
`description`,
`duration`,
`createdAt`,
`updatedAt`)
VALUES
(1, "Install SQL Server", "Install SQL Server on machines for department anc on floor 7.", 30, "2021-01-01 00:00:00", null),
(2, "Replace HDD", "Replace hdd for ssd in station 223", 60, "2021-02-03 00:00:00", "2021-04-16 20:51:30"),
(3, "Configure Switches", "Configure new switches on floor 5 for finance department.", 45, "2020-06-23 00:00:00", "2021-04-16 00:29:44");

INSERT INTO `ats2021foxtrot`.`teams`
(`id`,
`name`,
`isOnCall`,
`isDeleted`,
`createdAt`,
`updatedAt`,
`deletedAt`)
VALUES
(1, "Boom", 0, 1, "2021-03-31 21:58:48", null, "2021-04-15 21:32:38"),
(2, "Bang", 1, 0, "2021-04-14 17:43:45", null, null),
(3, "Big Bang", 0, 0, "2021-04-16 23:30:40", null, null);

INSERT INTO `ats2021foxtrot`.`jobs`
(`id`,
`teamId`,
`description`,
`clientName`,
`start`,
`end`,
`isOnSite`)
VALUES
( 1, 1, "Job One", "Hanna Montana", "2021-02-28 00:00:00", "2021-03-01 00:00:00", 0);

INSERT INTO `ats2021foxtrot`.`employeetasks`
(`employeeId`,
`taskId`)
VALUES
(1,3),
(4,2),
(4,1),
(4,3),
(2,2),
(2,1);

INSERT INTO `ats2021foxtrot`.`teammembers`
(`employeeId`,
`teamId`)
VALUES
( 3, 1),
( 1, 1),
( 2, 2),
( 4, 2),
( 5, 3),
( 6, 3);
