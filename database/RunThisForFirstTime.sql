create database EasyTravel
go 

use EasyTravel
go

create table users (
id int identity(1,1) primary key,
account_name varchar(50) not null unique,
password varchar(50) not null,
full_name nvarchar(80) not null,
DOB date check(datediff(year, DOB, getdate()) >= 18) not null,
email varchar(80) check(email like '%___@___%') unique not null,
phone varchar(10) check(phone like '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') not null unique,
role varchar(12) check(role in ('Admin','Tourist','Travel Agent')) not null,
status varchar(20) check(status in ('Inactive', 'Active', 'Banned')) not null
)

create table tours(
id int identity(1,1) primary key,
name nvarchar(100) not null,
type nvarchar(50),
is_enabled bit not null,
trip_length int,
available_from date,
available_to date,
max_quantity int,
price float not null,
destination nvarchar(50) not null,
description nvarchar (300),
agent_id int,
constraint tours_to_users foreign key(agent_id) references users(id)
)

alter table tours
add image varchar(150) not null

create table staff(
id int identity(1,1) primary key,
name nvarchar(80) not null,
DOB date check(datediff(year, DOB, getdate()) >= 18) not null,
phone varchar(10) 
check(phone like '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') 
not null unique,
gender bit,
agent_id int,
constraint staff_to_users foreign key(agent_id) references users(id)
)

create table vehicles(
id int identity(1,1) primary key,
type nvarchar(30) not null,
driver_name nvarchar(80),
driver_phone varchar(10) 
check(driver_phone like '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') 
not null unique,
max_passengers int,
agent_id int,
constraint vehicles_to_users foreign key(agent_id) references users(id)
)

create table restaurants(
id int identity(1,1) primary key,
type nvarchar(50) not null,
table_available int,
phone varchar(10) 
check(phone like '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') 
not null unique,
agent_id int,
constraint restaurants_to_users foreign key(agent_id) references users(id)
)

create table hotels(
id int identity(1,1) primary key,
stars int check(stars>=1 AND stars<=5),
room_available int,
phone varchar(10) 
check(phone like '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]') 
not null unique,
agent_id int,
constraint hotels_to_users foreign key(agent_id) references users(id)
)

create table feedback_threads(
id int identity(1,1) primary key,
rating int check(rating>=1 AND rating<=5) not null,
time timestamp not null,
content nvarchar(300) not null,
tourist_id int,
tour_id int,
constraint feedback_to_users foreign key(tourist_id) references users(id),
constraint feedback_to_tours foreign key(tour_id) references tours(id),
)

create table comments(
time timestamp not null unique, 
content nvarchar(300) not null,
user_id int,
thread_id int, 
constraint comments_to_threads foreign key(thread_id) 
references feedback_threads(id),
constraint comments_to_users foreign key(user_id) 
references users(id),
primary key(thread_id, user_id, time)
)

create table booking(
id int identity(1,1) primary key,
tourist_id int,
tour_id int,
book_time timestamp,
start_date date not null,
tourists_quantity int not null,
status nvarchar(30) check(status in('Processing', 'Ready', 'Done')),
note nvarchar(200),
vehicle_id int,
hotel_id int,
staff_id int,
restaurant_id int, 
constraint booking_to_tourists foreign key(tourist_id) references users(id),
constraint booking_to_tour foreign key(tour_id) references tours(id),
constraint booking_to_vehicles foreign key(vehicle_id) references vehicles(id),
constraint booking_to_hotels foreign key(hotel_id) references hotels(id),
constraint booking_to_staff foreign key(staff_id) references staff(id),
constraint booking_to_restaurants foreign key(restaurant_id) references restaurants(id),
)

alter table hotels
add location nvarchar(50) not null

alter table hotels
add name nvarchar(80) not null

alter table users
add [key] varchar(100)

alter table booking
drop column book_time 

alter table feedback_threads
drop column time 

drop table comments

create table comments(
time datetime not null unique, 
content nvarchar(300) not null,
user_id int,
thread_id int, 
constraint comments_to_threads foreign key(thread_id) 
references feedback_threads(id),
constraint comments_to_users foreign key(user_id) 
references users(id),
primary key(thread_id, user_id, time)
)

alter table booking
add book_time datetime 

alter table feedback_threads
add time datetime 

alter table booking
drop constraint booking_to_tourists, 
booking_to_tour,
booking_to_vehicles,
booking_to_hotels,
booking_to_staff,
booking_to_restaurants

alter table booking
drop column vehicle_id,
hotel_id,
staff_id,
restaurant_id

create table bookingDetails(
booking_id int primary key,
vehicle_id int,
hotel_id int,
staff_id int,
restaurant_id int, 
constraint bookingDetails_to_booking foreign key(booking_id) references booking(id),
constraint booking_to_vehicles foreign key(vehicle_id) references vehicles(id),
constraint booking_to_hotels foreign key(hotel_id) references hotels(id),
constraint booking_to_staff foreign key(staff_id) references staff(id),
constraint booking_to_restaurants foreign key(restaurant_id) references restaurants(id),
)

DECLARE @booking_status_check NVARCHAR(100)
SELECT @booking_status_check = CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE TABLE_NAME = 'booking'
AND CONSTRAINT_TYPE = 'check'
exec('alter table booking drop constraint '+@booking_status_check)
alter table booking 
add check(status in('Unpaid', 'Paid', 'Declined', 'Canceled', 'Ready', 'Done')),
reason nvarchar(300)

alter table booking
add payment varchar(4) check(payment in('Cash', 'Bank'))

create table payment(
agent_id int not null primary key,
bank varchar(40) not null,
code varchar(40) not null,
QR varchar(100),
constraint payment_to_user foreign key(agent_id) references users(id)
)

drop table payment

create table payment(
id int not null identity(1,1) primary key,
agent_id int not null,
bank varchar(40) not null,
code varchar(40) not null,
QR varchar(100),
constraint payment_to_user foreign key(agent_id) references users(id)
)

alter table tours
add payment_id int not null

insert into users(account_name, full_name, password, DOB, email, phone, role, status)
values 
('tourist_demo', 'Nguyen Van C', '123456', '2003-01-01', 'ANV123@gmail.com', '0123456780', 'Tourist', 'Active'),
('agent_demo', 'Nguyen Van B', '123456', '2003-01-01', 'ANV345@gmail.com', '0123456781', 'Travel Agent', 'Active'),
('admin_demo', 'Nguyen Van A', '123456', '2003-01-01', 'ANV234@gmail.com', '0123456789', 'Admin', 'Active')

insert into staff(name, DOB, phone, gender, agent_id)
values 
('Nguyen Van A', '2003-01-01', '0987654321', 1, 2),
('Nguyen Van B', '2003-01-01', '0987654322', 0, 2),
('Nguyen Van C', '2003-01-01', '0987654323', 1, 2)

insert into vehicles(type, driver_name, driver_phone, max_passengers, agent_id)
values 
(N'Xe con','Nguyen Van A',  '0987654321', 6, 2),
(N'Xe khách','Nguyen Van B',  '0987654322', 30, 2),
(N'Xe khách','Nguyen Van B',  '0987654323', 20, 2)

insert into restaurants(type, table_available, phone, agent_id)
values 
(N'Nhà hàng truyền thống địa phương', 50,  '0987654321', 2),
(N'Nhà hàng Nhật', 50,  '0987654322', 2),
(N'Quán nướng buffet', 50,  '0987654323', 2)

insert into hotels(location, name, phone, room_available, stars, agent_id)
values
(N'Lào Cai', N'Khách sạn A', '0123456789', 30, 4, 2),
(N'Hà Nội', N'Khách sạn B', '0123456788', 30, 4, 2),
(N'Đà Nẵng', N'Khách sạn C', '0123456780', 30, 4, 2)

insert into payment(agent_id, bank, code, QR)
values
(2, 'MB', '9704123456', 'abc.png'),
(2, 'Techcombank', '9704123457', 'abc.png'),
(2, 'TPBank', '9704123458', 'abc.png')

insert into tours(name, type, is_enabled, destination, trip_length, available_from, available_to, max_quantity, price, description, agent_id, image, payment_id)
values
(N'Du lịch biển Sầm Sơn', N'du lịch biển', 1, N'Thanh Hoá', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 2, 'sapa.png', 1),
(N'Du lịch biển Nha Trang', N'du lịch biển', 1, N'Khánh Hoà', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 2, 'HaLong.jpg', 2),
(N'Du lịch biển Phú Quốc', N'du lịch biển', 1, N'Kiên Giang', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 2, 'PhuQuoc.jpg', 3),
(N'Du lịch sinh thái Cát Tiên', N'du lịch sinh thái', 1, N'Đồng Nai, Lâm Đồng, Bình Phước', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 2, 'CanTho.jpg', 2),
(N'Du lịch nghỉ dưỡng Sa Pa', N'nghỉ dưỡng', 1, N'Lào Cai', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 2, 'CanTho	.jpg', 1)

insert into booking(book_time, note, payment, reason, start_date, status, tour_id, tourist_id, tourists_quantity)
values
('2023-07-18 21:55:23.143', 'test booking', 'Bank', null, '2023-12-01', 'Ready', 1, 1, 3),
('2023-07-18 21:55:23.143', 'test booking', 'Cash', null, '2023-12-01', 'Paid', 2, 1, 4),
('2023-07-18 21:55:23.143', 'test booking', 'Bank', null, '2023-12-01', 'Unpaid', 3, 1, 5),
('2023-07-18 21:55:23.143', 'test booking', 'Cash', 'test decline', '2023-12-01', 'Declined', 4, 1, 6),
('2023-07-18 21:55:23.143', 'test booking', 'Bank', 'test cancel', '2023-12-01', 'Canceled', 5, 1, 7),
('2023-07-10 21:55:23.143', 'test booking', 'Cash', null, '2023-07-13', 'Done', 1, 1, 3)

insert into bookingDetails(booking_id, hotel_id, restaurant_id, staff_id, vehicle_id)
values
(1, 1, 1, 1, 1),
(6, 2, 3, 2, 3)

insert into feedback_threads(content, rating, time, tour_id, tourist_id)
values
('Good tour', 5, '2023-07-18 21:55:23.143', 1, 1),
('Bad tour', 5, '2023-07-18 21:55:23.143', 1, 1),
('Good tour', 5, '2023-07-18 21:55:23.143', 1, 1),
('Bad tour', 5, '2023-07-18 21:55:23.143', 1, 1)
