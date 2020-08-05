/*
Created: 2020-08-03
Modified: 2020-08-03
Model: MySQL 8.0
Database: MySQL 8.0
*/

-- Create tables section -------------------------------------------------

-- Table MEMBER

create user 'boardgame'@'localhost' identified by 'boardgame';

create DATABASE boardgameproject CHARACTER SET = utf8;

grant all PRIVILEGES ON boardgameproject.* to 'boardgame'@'localhost';

Use boardgameproject;
create table boardgameproject.MEMBER
(
  member_number Int NOT NULL AUTO_INCREMENT,
  member_email Varchar(100) NOT NULL,
  member_password Varchar(100) NOT NULL,
  member_nickname Varchar(100) NOT NULL,
  member_gamelog Int NOT NULL,
  PRIMARY KEY (member_number)
)
;

ALTER TABLE MEMBER ADD UNIQUE member_nickname (member_nickname)
;

ALTER TABLE MEMBER ADD UNIQUE member_email (member_email)
;

-- Table GAME

create table boardgameproject.GAME
(
  game_number Int NOT NULL AUTO_INCREMENT,
  game_name Varchar(20) NOT NULL,
  game_info Varchar(300),
  PRIMARY KEY (game_number),
  UNIQUE game_number (game_number)
)
;

ALTER TABLE GAME ADD UNIQUE game_name (game_name)
;

-- Table GAMERECORD

create table boardgameproject.GAMERECORD
(
  gamerecord_number Int NOT NULL AUTO_INCREMENT,
  gamerecord_gamelog Varchar(100) NOT NULL,
  PRIMARY KEY (gamerecord_number),
  UNIQUE gamerecord_number (gamerecord_number)
)
;

CREATE TABLE boardgameproject.custom
(
   COUNT      INT(10) NOT NULL AUTO_INCREMENT,
   TITLE      VARCHAR(30)
                  CHARACTER SET utf8mb4
                  COLLATE utf8mb4_0900_ai_ci
                  NULL,
   CONTENT    VARCHAR(1000)
                  CHARACTER SET utf8mb4
                  COLLATE utf8mb4_0900_ai_ci
                  NULL,
   NAME       VARCHAR(20)
                  CHARACTER SET utf8mb4
                  COLLATE utf8mb4_0900_ai_ci
                  NULL,
   EMAIL      VARCHAR(40)
                  CHARACTER SET utf8mb4
                  COLLATE utf8mb4_0900_ai_ci
                  NULL,
   REGDATE    DATETIME(0) NULL,
   PRIMARY KEY(COUNT)
)
ENGINE INNODB
COLLATE 'utf8_general_ci'
ROW_FORMAT DEFAULT