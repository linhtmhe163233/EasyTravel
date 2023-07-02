use EasyTravel
go

DECLARE @booking_status_check NVARCHAR(100)
SELECT @booking_status_check = CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE TABLE_NAME = 'booking'
AND CONSTRAINT_TYPE = 'check'
exec('alter table booking drop constraint '+@booking_status_check)
alter table booking 
add check(status in('Processing','Declined', 'Canceled', 'Ready', 'Done')),
reason nvarchar(300)

