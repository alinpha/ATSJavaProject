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
  
INSERT INTO employees (firstName,lastName,sin,hourlyRate,isDeleted,createdAt) VALUES
('Bob','Marley','000000000',50.00,false,'2021-01-01'),
('Jay','Doe','111111111',20.00,false,'2021-02-03'),
('Mariah','Carrey','222222222',12.00,false,'2020-06-23'),
('Michael','Jackson','333333333',120.00,false,'2021-03-01');

CREATE TABLE `tasks` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` NVARCHAR(50) NOT NULL,
  `description` NVARCHAR(255) NOT NULL,
  `duration` INT NOT NULL,
  `createdAt` DATETIME NOT NULL,
  `updatedAt` DATETIME NULL);
  
INSERT INTO tasks (name,description,duration,createdAt) VALUES
('Install SQL Server','Install SQL Server on machines for department anc on floor 7.',30,'2021-01-01'),
('Replace HDD','Replace hdd for ssd in station 223',45,'2021-02-03'),
('Configure Switches','Configure new switches on floor 5 for finance department.',60,'2020-06-23');
  
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


