use EasyTravel
go

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
