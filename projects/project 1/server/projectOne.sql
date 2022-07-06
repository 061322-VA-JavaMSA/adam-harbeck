-- Create Tables --
--drop table if exists emp_roles;
--create table if not exists emp_roles(
--id serial primary key,
--role varchar(10)
--)

create type role as enum ('EMPLOYEE', 'MANAGER');

drop table if exists employees;
create table if not exists employees(
id UUID primary key,
username varchar(20) not null unique,
password varchar(20) not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(40) not null unique,
role role not null default 'EMPLOYEE'
)







-- Data Insertion --

insert into employees (id, username, password, first_name, last_name, email) values ('72df26b7-c4bb-48f3-8cb9-4ffcbb112f9d', 'gbenge0', 'WQGbwp', 'Grata', 'Benge', 'gbenge0@amazon.co.jp');
insert into employees (id, username, password, first_name, last_name, email) values ('5da8f813-c538-401c-9bfd-8ebd691c7449', 'hmckinstry1', 'GZwsZl', 'Hall', 'McKinstry', 'hmckinstry1@census.gov');
insert into employees (id, username, password, first_name, last_name, email, role) values ('7921acac-b183-4676-a843-ccaa5630023a', 'adharb', 'p4$$w0rd', 'Adam', 'Harbeck', 'adam@example.com', 'MANAGER');
