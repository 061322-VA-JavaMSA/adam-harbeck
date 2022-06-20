CREATE table office(
id serial primary key,
street varchar(30),
city varchar(30),
state varchar(15),
zip integer 
);

CREATE table customer(
id serial primary key,
);

CREATE table salestaff(
employee_id serial primary key,
first_name varchar(20),
last_name varchar(20),
DOB date,
office_id integer reference office(id)
customer_id integer references customer(id)
);


