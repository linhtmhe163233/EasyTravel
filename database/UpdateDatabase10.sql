use EasyTravel
go

DECLARE @booking_status_check NVARCHAR(100)
SELECT @booking_status_check = CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE TABLE_NAME = 'booking'
AND CONSTRAINT_TYPE = 'check'
exec('alter table booking drop constraint '+@booking_status_check)
alter table booking
add check(status in('Unpaid', 'Paid', 'Declined', 'Canceled', 'Ready', 'Done'))

alter table booking
add payment varchar(4) check(payment in('Cash', 'Bank'))