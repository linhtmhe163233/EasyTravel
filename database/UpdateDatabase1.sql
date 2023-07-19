use EasyTravel
go

insert into tours(name, type, is_enabled, destination, trip_length, available_from, available_to, max_quantity, price, description, agent_id, image )
values
(N'Du lịch biển Sầm Sơn', N'du lịch biển', 1, N'Thanh Hoá', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, ''),
(N'Du lịch biển Nha Trang', N'du lịch biển', 1, N'Khánh Hoà', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, ''),
(N'Du lịch biển Phú Quốc', N'du lịch biển', 1, N'Kiên Giang', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, ''),
(N'Du lịch sinh thái Cát Tiên', N'du lịch sinh thái', 1, N'Đồng Nai, Lâm Đồng, Bình Phước', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, ''),
(N'Du lịch nghỉ dưỡng Sa Pa', N'nghỉ dưỡng', 1, N'Lào Cai', 3, '2023-01-01', '2023-12-31', 5, 5000000, N'Chill out', 3, '')
