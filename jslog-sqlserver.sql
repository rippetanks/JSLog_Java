USE [jslog]
GO
/****** Object:  Table [dbo].[entity]    Script Date: 02/01/2019 19.07.06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[entity](
	[id] [smallint] IDENTITY(1,1) NOT NULL,
	[entity] [varchar](64) NOT NULL,
	[log_key] [char](32) NOT NULL,
 CONSTRAINT [PK_entity] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[log]    Script Date: 02/01/2019 19.07.07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[log](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[entity] [smallint] NULL,
	[storage_date] [datetime2](7) NOT NULL,
	[record_date] [datetime2](7) NOT NULL,
	[UserAgent] [varchar](256) NULL,
	[Host] [varchar](256) NULL,
	[message] [varchar](1024) NOT NULL,
	[http_code] [smallint] NULL,
	[level] [varchar](6) NOT NULL,
 CONSTRAINT [PK_log] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[log] ADD  CONSTRAINT [DF_log_entity]  DEFAULT (NULL) FOR [entity]
GO
ALTER TABLE [dbo].[log] ADD  CONSTRAINT [DF_log_storage_date]  DEFAULT (getdate()) FOR [storage_date]
GO
ALTER TABLE [dbo].[log] ADD  CONSTRAINT [DF_log_UserAgent]  DEFAULT (NULL) FOR [UserAgent]
GO
ALTER TABLE [dbo].[log] ADD  CONSTRAINT [DF_log_Host]  DEFAULT (NULL) FOR [Host]
GO
ALTER TABLE [dbo].[log] ADD  CONSTRAINT [DF_log_http_code]  DEFAULT (NULL) FOR [http_code]
GO
ALTER TABLE [dbo].[log]  WITH CHECK ADD  CONSTRAINT [FK_log_entity] FOREIGN KEY([entity])
REFERENCES [dbo].[entity] ([id])
GO
ALTER TABLE [dbo].[log] CHECK CONSTRAINT [FK_log_entity]
GO
ALTER TABLE [dbo].[log]  WITH CHECK ADD CHECK  (([level]='ASSERT' OR [level]='TRACE' OR [level]='DEBUG' OR [level]='INFO' OR [level]='WARN' OR [level]='ERROR' OR [level]='FATAL'))
GO
