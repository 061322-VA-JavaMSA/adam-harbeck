-- Create Tables --
--drop table if exists emp_roles;
--create table if not exists emp_roles(
--id serial primary key,
--role varchar(10)
--)

create type role as enum ('EMPLOYEE', 'MANAGER');
create type status as enum ('APPROVED', 'PENDING', 'REJECTED');
create type reimb_type as enum ('LODGING', 'TRAVEL', 'FOOD', 'OTHER');

drop table if exists employees;
create table if not exists employees(
id UUID primary key,
username varchar(20) not null unique,
password varchar(20) not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(40) not null unique,
role varchar(10) not null default 'EMPLOYEE'
)

drop table if exists tickets;
create table if not exists tickets(
id UUID primary key,
amount decimal not null,
submitted date not null default current_date,
description text not null,
employee UUID not null references employees(id) ,
approved_by UUID references employees(id),
status varchar(10) not null default 'PENDING',
reimb_type varchar(10) not null
)





-- Data Insertion --

insert into employees (id, username, password, first_name, last_name, email) values ('72df26b7-c4bb-48f3-8cb9-4ffcbb112f9d', 'gbenge0', 'WQGbwp', 'Grata', 'Benge', 'gbenge0@amazon.co.jp');
insert into employees (id, username, password, first_name, last_name, email) values ('5da8f813-c538-401c-9bfd-8ebd691c7449', 'hmckinstry1', 'GZwsZl', 'Hall', 'McKinstry', 'hmckinstry1@census.gov');
insert into employees (id, username, password, first_name, last_name, email, role) values ('7921acac-b183-4676-a843-ccaa5630023a', 'adharb', 'p4$$w0rd', 'Adam', 'Harbeck', 'adam@example.com', 'MANAGER');
insert into employees (id, username, password, first_name, last_name, email, role) values ('68e6476d-4c1f-48ec-9000-a95fb63ef5c2', 'zborn', 'dorothy', 'Bea', 'Arthur', 'bArthur@example.com', 'MANAGER');
insert into employees (id, username, password, first_name, last_name, email) values ('7828c6bc-5b62-4cd8-9b01-f03cb4638137', 'yuyuH', 'hakusho', 'Yusuke', 'Urameshi', 'y.Urameshi0@amazon.co.jp');
insert into employees (id, username, password, first_name, last_name, email) values ('90bc6cfc-758a-4017-8906-d5406606a4a2', 'soulEater', 're$0n4nc3', 'Maka', 'Albarn', 'scytheWielder0@census.gov');


insert into tickets (id, amount, description, employee, reimb_type) values ('e1062a98-51b9-41f0-b44c-12443d12043b', 23.50, 'Travel reimbursement from going to other office.', '72df26b7-c4bb-48f3-8cb9-4ffcbb112f9d', 'TRAVEL');
insert into tickets (id, amount, submitted, description, employee, reimb_type) values ('660ac5c0-1f43-4714-acda-08bf65f2a4a7', 87.49, '2022-07-01', 'Food reimbursement for entertaining the excecutives from Atlanta office.', '7828c6bc-5b62-4cd8-9b01-f03cb4638137', 'FOOD' );
insert into tickets (id, amount, submitted, description, employee, reimb_type) values ('0f149930-3568-4518-9bbf-1c1e3a11f139', 161.20, '2022-06-28', 'Lodging reimbursement from my trip to the Portland office.', '5da8f813-c538-401c-9bfd-8ebd691c7449', 'LODGING');
insert into tickets (id, amount, submitted, description, employee, reimb_type) values ('90701a4d-1857-4a2e-8e5c-01f44ee9659b', 34.95, '2022-06-22', 'Reimbursement for buying supplies for the office.', '90bc6cfc-758a-4017-8906-d5406606a4a2', 'OTHER');
insert into tickets (id, amount, submitted, description, employee, approved_by, status, reimb_type) values ('70a78dd4-3c9c-4540-9693-5237f7b26d5f', 69.87, '2022-06-11', 'Lodging reimbursement form trip to Atlanta office.', '7828c6bc-5b62-4cd8-9b01-f03cb4638137', '7921acac-b183-4676-a843-ccaa5630023a', 'APPROVED', 'LODGING');
insert into tickets (id, amount, submitted, description, employee, approved_by, status, reimb_type) values ('e045fc4f-992c-4c53-b3f4-808b1631b0c7', 32.11, '2022-06-05', 'Travel reimbursement for transit.', '90bc6cfc-758a-4017-8906-d5406606a4a2', '68e6476d-4c1f-48ec-9000-a95fb63ef5c2', 'APPROVED', 'TRAVEL');
insert into tickets (id, amount, submitted, description, employee, approved_by, status, reimb_type) values ('d368e1de-4483-4631-ac35-2c92a14da672', 404.92, '2022-06-01', 'Reimbursement for vacation', '90bc6cfc-758a-4017-8906-d5406606a4a2', '68e6476d-4c1f-48ec-9000-a95fb63ef5c2', 'REJECTED', 'OTHER');





