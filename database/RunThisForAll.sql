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

insert into users(account_name, full_name, password, DOB, email, phone, role, status)
values 
('tourist_demo', 'Nguyen Van C', '123456', '2003-01-01', 'ANV123@gmail.com', '0123456780', 'Tourist', 'Active'),
('agent_demo', 'Nguyen Van B', '123456', '2003-01-01', 'ANV345@gmail.com', '0123456781', 'Travel Agent', 'Active'),
('admin_demo', 'Nguyen Van A', '123456', '2003-01-01', 'ANV234@gmail.com', '0123456789', 'Admin', 'Active')

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

﻿use EasyTravel
go

insert into tours(name, type, is_enabled, destination, trip_length, available_from, available_to, max_quantity, price, description, agent_id, image )
values
(N'Du lịch biển Sầm Sơn', N'du lịch biển', 1, N'Thanh Hoá', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, ''),
(N'Du lịch biển Nha Trang', N'du lịch biển', 1, N'Khánh Hoà', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, ''),
(N'Du lịch biển Phú Quốc', N'du lịch biển', 1, N'Kiên Giang', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, ''),
(N'Du lịch sinh thái Cát Tiên', N'du lịch sinh thái', 1, N'Đồng Nai, Lâm Đồng, Bình Phước', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, ''),
(N'Du lịch nghỉ dưỡng Sa Pa', N'nghỉ dưỡng', 1, N'Lào Cai', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, '')

﻿use EasyTravel
go

insert into staff(name, DOB, phone, gender, agent_id)
values
(N'Nguyễn Văn A', '2000-01-01', '0123456789', 1, 3),
(N'Nguyễn Văn B', '2000-01-01', '0123456788', 0, 3),
(N'Nguyễn Văn C', '2000-01-01', '0123456787', 1, 3)

use EasyTravel
go

alter table hotels
add location nvarchar(50) not null

﻿USE [EasyTravel]
GO

INSERT INTO [dbo].[vehicles]
           ([type]
           ,[driver_name]
           ,[driver_phone]
           ,[max_passengers]
           ,[agent_id])
     VALUES
           (N'Xe 7 chỗ', N'Nguyễn Văn A', '0123456789', 7, 3),
			(N'Xe 5 chỗ', N'Nguyễn Văn A', '0123456780', 5, 3),
			(N'Xe 20 chỗ', N'Nguyễn Văn A', '0123456781', 20, 3)
GO


use EasyTravel
go

alter table hotels
add name nvarchar(80) not null

use EasyTravel 
go

alter table users
add [key] varchar(100)

