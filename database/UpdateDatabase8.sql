use EasyTravel
go

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
