DROP SCHEMA IF EXISTS ats2021foxtrot;

CREATE SCHEMA ats2021foxtrot;

USE ats2021foxtrot;

CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `firstNAME` nvarchar(25) NOT NULL,
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