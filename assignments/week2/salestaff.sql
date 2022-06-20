CREATE table office(
Id serial primary key,
Street varchar(30),
City varchar(30),
State varchar(15),
Zip integer 
);

CREATE table customer(
Id serial primary key,
);

CREATE table salestaff(
employee_id serial primary key,
first_name varchar(20),
last_name varchar(20),
DOB date,
office_id integer reference office(id)
customer_id integer references customer(id)
);


