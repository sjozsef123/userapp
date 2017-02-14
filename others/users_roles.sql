drop schema if exists `userapp`;
create schema `userapp`;

use userapp;

CREATE TABLE `userapp`.`users` (
	`id` int(10) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(45) NOT NULL,
	`loyalty_index` INT(2),
    `password` VARCHAR(80),
PRIMARY KEY `pk_users` (`id`),
UNIQUE KEY  `uk_users` (`username`)
);
insert into users (`id`, `username`, `loyalty_index`, `password`)  values (1, 'Robi', 10, '¸Ÿô�Nô@äcµ0ÚGW›¢d>');
insert into users (`id`, `username`, `loyalty_index`, `password`)  values (2, 'Joco', 10, 'ËLêa%]œÆ‡£éæ.KÍÉžó');
insert into users (`id`, `username`, `loyalty_index`, `password`)  values (3, 'Zolti', 2, 'ËLêa%]œÆ‡£éæ.KÍÉžó');
insert into users (`id`, `username`, `loyalty_index`, `password`)  values (4, 'Csilla', 0, '¸Ÿô�Nô@äcµ0ÚGW›¢d>');

CREATE TABLE `userapp`.`roles` (
	`id` int(10) NOT NULL AUTO_INCREMENT,
	`rolename` VARCHAR(45) NOT NULL,
PRIMARY KEY `pk_roles` (`id`),
UNIQUE KEY `uk_roles` (`rolename`)
);

insert into roles (`id`, `rolename`) values('1', 'administrator');
insert into roles (`id`, `rolename`) values('2', 'user');

CREATE TABLE `userapp`.`users_roles` (
	`user_id` int(10) NOT NULL,
	`role_id` int(10) NOT NULL,
PRIMARY KEY `pk_ur` (`user_id`,`role_id`),
CONSTRAINT `fk_ur_user` FOREIGN KEY (`user_id`) REFERENCES `userapp`.`users` (`id`) ON DELETE CASCADE
       ON UPDATE CASCADE,
CONSTRAINT `fk_ur_role` FOREIGN KEY (`role_id`) REFERENCES `userapp`.`roles` (`id`) 
);

insert into users_roles (`user_id`, `role_id`) values ('1', '1');
insert into users_roles (`user_id`, `role_id`) values ('2', '2');
insert into users_roles (`user_id`, `role_id`) values ('3', '2');


CREATE TABLE `userapp`.`authors` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45) NOT NULL UNIQUE,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_UNIQUE` (`id` ASC));
  
INSERT INTO `userapp`.`authors` (`id`, `name`) VALUES ('12', 'Arany Janos');
INSERT INTO `userapp`.`authors` (`id`, `name`) VALUES ('13', 'Jokai Mor');
  

CREATE TABLE `userapp`.`publications` (
  `id`INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL UNIQUE,
  `publisher` VARCHAR(45),  
  `release_date` date,
  `nr_of_copies` INT(5),
  `copies_left` INT(5) Unsigned,
  `type` VARCHAR(50),
PRIMARY KEY (`id`),
UNIQUE INDEX `id_UNIQUE` (`id` ASC)); 
  
  
  
INSERT INTO `userapp`.`publications` (`id`, `title`, `publisher`, `release_date`, `nr_of_copies`, `copies_left`, `type`) VALUES ('5234', 'Arany Ember', 'Korvin kiado', '2010-10-5', '5', '5', 'book');
INSERT INTO `userapp`.`publications` (`id`, `title`, `publisher`, `release_date`, `nr_of_copies`, `copies_left`, `type`) VALUES ('2134', 'Napi hirek', 'Hirlap', '2000-12-12', '2', '2', 'newspaper');
INSERT INTO `userapp`.`publications` (`id`, `title`, `publisher`, `release_date`, `nr_of_copies`, `copies_left`, `type`) VALUES ('1234', 'Nok lapja', 'Nok kiadoja', '2016-01-10', '10', '10', 'magazine');


CREATE TABLE `userapp`.`articles` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `publication_id` INT(10),
  `title` VARCHAR(45) NOT NULL UNIQUE,
PRIMARY KEY (`id`),
FOREIGN KEY (publication_id) 
	REFERENCES `userapp`.`publications`(id)
    ON DELETE CASCADE);	

INSERT INTO `userapp`.`articles` (`id`,`publication_id` , `title`) VALUES ('123', '2134','elso cikk');
INSERT INTO `userapp`.`articles` (`id`,`publication_id` , `title`) VALUES ('124', '2134','masodik cikk');
INSERT INTO `userapp`.`articles` (`id`,`publication_id` , `title`) VALUES ('125', '2134','harmadik cikk');

CREATE TABLE `userapp`.`publications_authors` (
  `publication_id` INT(10) NOT NULL,
  `author_id` INT(10) NOT NULL, 
PRIMARY KEY (`publication_id`,`author_id`),
FOREIGN KEY (publication_id) 
	REFERENCES `userapp`.`publications`(id)
    ON DELETE CASCADE,
FOREIGN KEY (author_id) 
	REFERENCES `userapp`.`authors`(id)
    ON DELETE CASCADE); 
    
INSERT INTO `userapp`.`publications_authors` (`publication_id`, `author_id`) VALUES ('1234', '12');
INSERT INTO `userapp`.`publications_authors` (`publication_id`, `author_id`) VALUES ('5234', '13');


create table `userapp`.`publications_borrowings`(
	`publication_id` INT(10) NOT NULL,
	`user_id` INT(10) NOT NULL,
	`borrowing_date` DATE,
	`deadline` DATE,
PRIMARY KEY (`publication_id`,`user_id`),
FOREIGN KEY (publication_id) 
	REFERENCES `userapp`.`publications`(id)
    ON DELETE CASCADE,
FOREIGN KEY (user_id) 
	REFERENCES `userapp`.`users`(id)
    ON DELETE CASCADE); 
    
INSERT INTO `userapp`.`publications_borrowings` (`publication_id`, `user_id`, `borrowing_date`, `deadline`) VALUES ('5234', '1', '2016-01-10', '2016-02-10');
INSERT INTO `userapp`.`publications_borrowings` (`publication_id`, `user_id`, `borrowing_date`, `deadline`) VALUES ('1234', '2', '2016-02-20', '2016-02-21');

UPDATE `userapp`.`publications` SET `copies_left`='4' WHERE `id`='5234';
UPDATE `userapp`.`publications` SET `copies_left`='9' WHERE `id`='1234';

