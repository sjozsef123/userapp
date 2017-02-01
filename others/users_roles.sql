drop schema if exists `userapp`;
create schema `userapp`;

CREATE TABLE `userapp`.`users` (
`id` int(10) NOT NULL AUTO_INCREMENT,
`username` VARCHAR(45) NOT NULL,
PRIMARY KEY `pk_users` (`id`),
UNIQUE KEY  `uk_users` (`username`)
);

CREATE TABLE `userapp`.`roles` (
`id` int(10) NOT NULL AUTO_INCREMENT,
`rolename` VARCHAR(45) NOT NULL,
PRIMARY KEY `pk_roles` (`id`),
UNIQUE KEY `uk_roles` (`rolename`)
);

CREATE TABLE `userapp`.`users_roles` (
`user_id` int(10) NOT NULL,
`role_id` int(10) NOT NULL,
PRIMARY KEY `pk_ur` (`user_id`,`role_id`),
CONSTRAINT `fk_ur_user` FOREIGN KEY (`user_id`) REFERENCES `userapp`.`users` (`id`) ON DELETE CASCADE,
CONSTRAINT `fk_ur_role` FOREIGN KEY (`role_id`) REFERENCES `userapp`.`roles` (`id`) ON DELETE CASCADE
);

use userapp;

insert into users (`id`, `username`)  values (1, 'Barni');

insert into roles (`id`, `rolename`) values('1', 'administrator');
insert into roles (`id`, `rolename`) values('2', 'user');

insert into users_roles (`user_id`, `role_id`) values ('1', '2');

