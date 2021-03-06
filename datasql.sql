USE [QLBaiGuiXe]
GO
/****** Object:  Table [dbo].[Custemer]    Script Date: 3/3/2022 12:00:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Custemer](
	[ID_custemer] [int] IDENTITY(1,1) NOT NULL,
	[fullname] [nvarchar](500) NULL,
	[student_id] [nvarchar](1000) NULL,
	[username] [nvarchar](1000) NULL,
	[password] [nvarchar](1000) NULL,
	[email] [nvarchar](1000) NULL,
	[phone] [nvarchar](500) NULL,
	[card_id] [int] NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
 CONSTRAINT [PK_Custemer] PRIMARY KEY CLUSTERED 
(
	[ID_custemer] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 3/3/2022 12:00:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[ID_employee] [int] IDENTITY(1,1) NOT NULL,
	[fullname] [nvarchar](500) NULL,
	[identity_card] [nvarchar](1000) NULL,
	[usename] [nvarchar](500) NULL,
	[password] [nvarchar](500) NULL,
	[birday] [date] NULL,
	[gender] [bit] NULL,
	[created_at] [date] NULL,
	[updateed_ay] [date] NULL,
	[role_id] [int] NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[ID_employee] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ParkingHistory]    Script Date: 3/3/2022 12:00:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ParkingHistory](
	[ID_parking] [int] IDENTITY(1,1) NOT NULL,
	[license_plate] [nvarchar](500) NULL,
	[check_in_at] [date] NULL,
	[check_out_at] [date] NULL,
	[price] [money] NULL,
	[custemer_id] [int] NULL,
	[img] [nvarchar](500) NULL,
 CONSTRAINT [PK_ParkingHistory] PRIMARY KEY CLUSTERED 
(
	[ID_parking] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ParkingPackage]    Script Date: 3/3/2022 12:00:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ParkingPackage](
	[ID_package] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](1000) NULL,
	[description] [nvarchar](500) NULL,
	[duration] [int] NULL,
	[visible] [bit] NULL,
	[price] [money] NULL,
 CONSTRAINT [PK_ParkingPackage] PRIMARY KEY CLUSTERED 
(
	[ID_package] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payment]    Script Date: 3/3/2022 12:00:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[ID_payment] [int] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](1000) NULL,
	[paid_at] [date] NULL,
	[start_date] [date] NULL,
	[end_end] [date] NULL,
	[price] [money] NULL,
	[custemer_id] [int] NULL,
 CONSTRAINT [PK_Payment] PRIMARY KEY CLUSTERED 
(
	[ID_payment] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 3/3/2022 12:00:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[ID_role] [int] IDENTITY(1,1) NOT NULL,
	[code] [nvarchar](500) NULL,
	[description] [nvarchar](500) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[ID_role] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheXe]    Script Date: 3/3/2022 12:00:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheXe](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[barcode] [nvarchar](500) NULL,
 CONSTRAINT [PK_TheXe] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UnitPrice]    Script Date: 3/3/2022 12:00:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnitPrice](
	[ID_unit_price] [int] IDENTITY(1,1) NOT NULL,
	[day_in_week] [int] NULL,
	[start_time_in_day] [time](7) NULL,
	[end_time_in_day] [time](7) NULL,
	[start_date] [date] NULL,
	[end_date] [date] NULL,
	[price] [money] NULL,
 CONSTRAINT [PK_UnitPrice] PRIMARY KEY CLUSTERED 
(
	[ID_unit_price] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Custemer]  WITH CHECK ADD  CONSTRAINT [FK_Custemer_TheXe] FOREIGN KEY([card_id])
REFERENCES [dbo].[TheXe] ([id])
GO
ALTER TABLE [dbo].[Custemer] CHECK CONSTRAINT [FK_Custemer_TheXe]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Role] FOREIGN KEY([role_id])
REFERENCES [dbo].[Role] ([ID_role])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Role]
GO
ALTER TABLE [dbo].[ParkingHistory]  WITH CHECK ADD  CONSTRAINT [FK_ParkingHistory_Custemer] FOREIGN KEY([custemer_id])
REFERENCES [dbo].[Custemer] ([ID_custemer])
GO
ALTER TABLE [dbo].[ParkingHistory] CHECK CONSTRAINT [FK_ParkingHistory_Custemer]
GO
ALTER TABLE [dbo].[Payment]  WITH CHECK ADD  CONSTRAINT [FK_Payment_Custemer] FOREIGN KEY([custemer_id])
REFERENCES [dbo].[Custemer] ([ID_custemer])
GO
ALTER TABLE [dbo].[Payment] CHECK CONSTRAINT [FK_Payment_Custemer]
GO
