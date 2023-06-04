USE [EasyTravel]
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


