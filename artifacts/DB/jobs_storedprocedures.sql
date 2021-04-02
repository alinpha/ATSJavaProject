USE ats2021foxtrot;

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

