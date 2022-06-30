-- Data Definition 

drop table if exists shop_roles;
create table if not exists shop_roles(
id serial primary key,
role varchar(10) not null
);

drop table if exists shop_users;
create table if not exists shop_users(
id uuid primary key,
first_name varchar(20) not null,
last_name varchar(20) not null,
username varchar(20) not null,
password varchar(20) not null,
shop_role_id integer references shop_roles(id) default 1
);

drop table if exists vehicle_types;
create table if not exists vehicle_types(
id serial primary key,
v_type varchar(10) not null
);

drop table if exists evs;
create table if not exists evs(
id uuid primary key,
brand varchar(15) not null,
model varchar(20) not null,
v_range integer not null,
v_type_id integer references vehicle_types(id) not null,
shop_user_id uuid references shop_users(id) default null,
final_price decimal default null,
remaining_balance decimal default null
);

drop table if exists offers;
create table if not exists offers(
id uuid primary key,
shop_user_id uuid references shop_users(id) not null,
ev_id uuid references evs(id) not null,
offer decimal not null
);

drop table if exists payments;
create table if not exists payments(
id uuid primary key,
shop_user_id uuid references shop_users(id) not null,
ev_id uuid references evs(id) not null,
original_balance decimal not null,
payment decimal not null,
new_balance decimal not null
);


-- Data Manipulation
-- -- Creating Roles
insert into shop_roles (role) values ('customer');
insert into shop_roles (role) values ('employee');
insert into shop_roles (role) values ('manager');

-- -- Creating Users
insert into shop_users(id, first_name, last_name, username, password) values ('5bbe28df-e4c9-4aba-89f2-2d241d2ff30d', 'Ryley', 'Baddoe', 'rbaddoe0', 'q7Cqlx02QLN0');
insert into shop_users (id, first_name, last_name, username, password) values ('1e7fe41c-93bd-4cd6-83f1-ea6954922f8e', 'Salim', 'MacWhirter', 'smacwhirter1', 'CJX5MKlgy');
insert into shop_users (id, first_name, last_name, username, password) values ('1ce0f881-7f25-47a3-a363-475e00ff8325', 'Martha', 'Friend', 'mfriend2', 'ulHlS5vSFB');
insert into shop_users (id, first_name, last_name, username, password, shop_role_id) values ('f549bc9d-e214-4e78-ad86-2734b2036f9e', 'Agnes', 'Griffin', 'agriffin3', 'Ye5kNwxd0NKm', 2);
insert into shop_users (id, first_name, last_name, username, password, shop_role_id) values ('0391140f-e968-40cb-b850-3e472b42b915', 'Alikee', 'Osinin', 'aosinin4', 'OklOoZcv', 2);

-- -- Creating Vehicle_types
insert into vehicle_types (v_type) values ('car');
insert into vehicle_types (v_type) values ('truck');
insert into vehicle_types (v_type) values ('SUV');

-- -- Creating EVs
insert into evs (id, brand, model, v_range, v_type_id) values ('95f03a40-76a7-42bc-8270-00f014aa63f7', 'BMW', 'i4', 300, 1);
insert into evs (id, brand, model, v_range, v_type_id) values ('7fcef724-2e4f-4330-b64e-1a0374651470', 'BMW', 'iX', 300, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('e153811e-8760-42ac-955d-4cdb0e046746', 'Cadillac', 'Lyriq', 312, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('7f190ca2-a8a8-4f18-8082-4835e87182b9', 'Chevrolet', 'Equinox', 300, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('a62ed516-9c72-49ca-8178-0941fe7f3077', 'Chevrolet', 'Silverado-EV', 400, 2);
insert into evs (id, brand, model, v_range, v_type_id) values ('4fbf6edb-5fc3-47a0-9f73-ef228760e1d4', 'Fisker', 'Ocean', 250, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('c85bdae9-7698-415b-a2a8-3f5aec1bf2d9', 'Ford', 'F-150 Lightning', 320, 2);
insert into evs (id, brand, model, v_range, v_type_id) values ('751e132c-d615-49fb-bf4d-fc00bb8a9df7', 'Genesis', 'GV60', 235, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('1a63cac0-ebdc-4a00-933d-4f420ef217ff', 'GMC', 'Hummer-EV', 350, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('363f4f2d-f613-4087-9cd2-98f0875350ef', 'Honda', 'Prologue', 300, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('8e93d9f4-e87d-4f73-89d3-0f0b70611792', 'Indi', 'One', 230, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('b971eda8-42c0-4e24-89ba-7a636d52ac67', 'Kia', 'EV6', 232, 3);
insert into evs (id, brand, model, v_range, v_type_id, shop_user_id, final_price, remaining_balance) values ('7b24efdb-9153-41d8-9406-a1de506cb33a', 'Kia', 'Niro', 253, 3, '5bbe28df-e4c9-4aba-89f2-2d241d2ff30d', 32100.00, 30000.00);
insert into evs (id, brand, model, v_range, v_type_id) values ('96939baa-b651-4dcc-ac37-f3ac0ca92206', 'Lexus', 'RZ', 225, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('dc0a4a89-7af2-45e7-b647-7ef55c2dd33b', 'Lucid', 'Air', 400, 1);
insert into evs (id, brand, model, v_range, v_type_id) values ('98a04b6f-cc36-411c-a2b4-31cb39ce7f9e', 'Polestar', '3', 372, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('04ce8d42-9937-4ae4-91a9-99def2ef016d', 'Nissan', 'Ariya', 300, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('894f14f1-25d2-459f-a470-0816676b3265', 'Rivian', 'R1T', 400, 2);
insert into evs (id, brand, model, v_range, v_type_id) values ('7746468f-cf63-465d-8f8d-eefe60bd8191', 'Subaru', 'Solterra', 228, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('c34bb493-79b3-46f8-bab8-8d68387c2f2b', 'Toyota', 'bZ4X', 252, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('989d438c-02be-4e4a-bd68-5a17f132048e', 'VinFast', 'VF8', 260, 3);
insert into evs (id, brand, model, v_range, v_type_id) values ('532ccae3-ebee-4fc3-be0c-40cb6c26c41f', 'Volvo', 'C40-Recharge', 220, 3);

-- -- Creating Payments
insert into payments (id, shop_user_id, ev_id, original_balance, payment, new_balance) values ('d5b02d24-823d-4791-b299-e37df8dd2e6d', '5bbe28df-e4c9-4aba-89f2-2d241d2ff30d', '7b24efdb-9153-41d8-9406-a1de506cb33a', 32100.00, 2100.00, 30000.00);

-- -- Creating Offers
insert into offers (id, shop_user_id, ev_id, offer) values ('6e83f560-8c2e-4d53-86c8-41ba429c94d9', '1e7fe41c-93bd-4cd6-83f1-ea6954922f8e', '363f4f2d-f613-4087-9cd2-98f0875350ef', 4900.00); -- prologue
insert into offers (id, shop_user_id, ev_id, offer) values ('363a7e19-2b36-44a3-ab41-5265e088c0a3', '1ce0f881-7f25-47a3-a363-475e00ff8325', '363f4f2d-f613-4087-9cd2-98f0875350ef', 5800.00); -- prologue
insert into offers (id, shop_user_id, ev_id, offer) values ('8ec22c43-7125-44d7-ac01-7c16606f3659', '5bbe28df-e4c9-4aba-89f2-2d241d2ff30d', '7fcef724-2e4f-4330-b64e-1a0374651470', 8700.00); -- iX
insert into offers (id, shop_user_id, ev_id, offer) values ('dbd83194-5769-4cf3-a435-426a12ec59c4', '1ce0f881-7f25-47a3-a363-475e00ff8325', '7fcef724-2e4f-4330-b64e-1a0374651470', 6400.00); -- iX



-- -- User Commands -- ---
-- Get a user by username
select * from shop_users where username = ?;
-- Get a user by id 
select * from shop_users where id = ?;
-- Get all users
select * from shop_users;
-- Create a new user
insert into shop_users (id, first_name, last_name, username, password) values (?, ?, ?, ?, ?) returning id;
-- Update a user
update shop_users set first_name = ?, last_name = ?, username = ?, password = ? where id = ?;
-- Delete a user
delete from shop_users where id = ?;

-- -- EV Commands -- --
-- Get all EVs -- possibly need a join to see the actual vehicle type and not the id 
select * from evs;
-- Get all available EVs
select * from evs where shop_user_id is null;
-- Get all owned EVs
select * from evs where shop_user_id= ?;
-- Get EV by model 
select * from evs where model = ?;
-- Get EV by id 
select * from evs where id = ?;
-- Create a new EV
insert into evs (id, brand, model, v_range, v_type_id, shop_user_id, final_price) values (?, ?, ?, ?, ?, ?, ?) returning id;
-- Update EV
update evs set brand = ?, model = ?, v_range = ?, v_type_id = ?, shop_user_id = ?, final_price =? where id = ?;
-- Delete EV
delete from evs where id = ?;


-- -- Payment Commands -- --
-- Get all payments
select * from payments order by shop_user_id;
-- Get payment by id
select * from evs where shop_user_id = ? AND remaining_balance > 0;



-- -- Offer Commands -- --
-- Get max offer
select max(offer) as offer from offers where ev_id = '363f4f2d-f613-4087-9cd2-98f0875350ef';
-- Get all offers
select o.id, o.offer,e.id as ev_id, e.brand, e.model, s.id as shop_user_id, s.username  from offers as o join evs as e on ev_id = e.id join shop_users s on o.shop_user_id = s.id;
-- Create new offer
insert into offers (id, shop_user_id, ev_id, offer) values (?, ?, ?, ?) returning id;
-- Get offer by EV id
select * from offers where ev_id = ?;
-- Get offer by id
select * from offers where id = ?;
-- Delete an offer
delete from offers where id = ?;











