use EasyTravel
go

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