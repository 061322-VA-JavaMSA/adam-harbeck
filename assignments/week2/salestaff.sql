CREATE table office(
id serial primary key,
name varchar(30),
street varchar(30),
city varchar(30),
state varchar(15),
zip integer 
);

CREATE table salestaff(
employee_id serial primary key,
first_name varchar(20),
last_name varchar(20),
DOB date,
office_id integer reference office(id)
);

CREATE table customer(
id serial primary key,
first_name varchar(20),
last_name varchar(20),
salestaff_id references salestaff(id)
);
