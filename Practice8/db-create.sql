drop database p8db;
create database p8db;
use p8db;
create table users(
id int(11) NOT NULL PRIMARY KEY auto_increment,
login varchar(50)
);
create table groups(
id int(11) NOT NULL PRIMARY KEY auto_increment,
name varchar(50)
);
create table users_groups(
user_id int(11) NOT NULL,
group_id int(11)
);
insert into users values(default, 'ivanov');
insert into groups values(default, 'teamA');