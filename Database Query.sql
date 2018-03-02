-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create Ad TABLE
-- =============================================


IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ad]') AND type in (N'U'))
ALTER TABLE [dbo].[Ad] DROP CONSTRAINT IF EXISTS [FK_Ad_Price]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ad]') AND type in (N'U'))
ALTER TABLE [dbo].[Ad] DROP CONSTRAINT IF EXISTS [FK_ad_location]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ad]') AND type in (N'U'))
ALTER TABLE [dbo].[Ad] DROP CONSTRAINT IF EXISTS [FK_ad_content]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ad]') AND type in (N'U'))
ALTER TABLE [dbo].[Ad] DROP CONSTRAINT IF EXISTS [FK_ad_client]
GO

CREATE TABLE [dbo].[Ad](
	[ad_id] [int] NOT NULL,
	[location_id] [int] NOT NULL,
	[client_id] [int] NOT NULL,
	[content_id] [int] NOT NULL,
	[author_id] [int] NOT NULL,
	[price_id] [int] NOT NULL,
	[start_date] [datetime] NOT NULL,
	[stop_date] [datetime] NOT NULL,
	[ad_size] [nvarchar](10) NOT NULL,
	[ad_number_click] [int] NOT NULL,
	[ad_number_view] [int] NOT NULL,
 CONSTRAINT [PK_ad] PRIMARY KEY CLUSTERED 
(
	[ad_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ad_client]') AND parent_object_id = OBJECT_ID(N'[dbo].[Ad]'))
ALTER TABLE [dbo].[Ad]  WITH CHECK ADD  CONSTRAINT [FK_ad_client] FOREIGN KEY([client_id])
REFERENCES [dbo].[Client] ([client_id])
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ad_client]') AND parent_object_id = OBJECT_ID(N'[dbo].[Ad]'))
ALTER TABLE [dbo].[Ad] CHECK CONSTRAINT [FK_ad_client]
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ad_content]') AND parent_object_id = OBJECT_ID(N'[dbo].[Ad]'))
ALTER TABLE [dbo].[Ad]  WITH CHECK ADD  CONSTRAINT [FK_ad_content] FOREIGN KEY([content_id])
REFERENCES [dbo].[Content] ([content_id])
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ad_content]') AND parent_object_id = OBJECT_ID(N'[dbo].[Ad]'))
ALTER TABLE [dbo].[Ad] CHECK CONSTRAINT [FK_ad_content]
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ad_location]') AND parent_object_id = OBJECT_ID(N'[dbo].[Ad]'))
ALTER TABLE [dbo].[Ad]  WITH CHECK ADD  CONSTRAINT [FK_ad_location] FOREIGN KEY([location_id])
REFERENCES [dbo].[Location] ([location_id])
ON UPDATE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ad_location]') AND parent_object_id = OBJECT_ID(N'[dbo].[Ad]'))
ALTER TABLE [dbo].[Ad] CHECK CONSTRAINT [FK_ad_location]
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Ad_Price]') AND parent_object_id = OBJECT_ID(N'[dbo].[Ad]'))
ALTER TABLE [dbo].[Ad]  WITH CHECK ADD  CONSTRAINT [FK_Ad_Price] FOREIGN KEY([price_id])
REFERENCES [dbo].[Price] ([price_id])
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Ad_Price]') AND parent_object_id = OBJECT_ID(N'[dbo].[Ad]'))
ALTER TABLE [dbo].[Ad] CHECK CONSTRAINT [FK_Ad_Price]
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create delet stored procedure
-- =============================================
DROP PROCEDURE IF EXISTS [dbo].[AdDEL]
GO

CREATE PROCEDURE [dbo].[AdDEL] 
(
	@ad_id			[INT]
)
AS
BEGIN
	SET NOCOUNT ON;

    DELETE FROM [dbo].[Ad]
		WHERE [dbo].[Ad].[ad_id] = @ad_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create insert stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[AdINS]
GO

ALTER PROCEDURE [dbo].[AdINS] 
(
	@ad_id			[INT] OUTPUT, 
	@location_id	[INT],
	@client_id		[INT],
	@content_id		[INT],
	@author_id		[INT],
	@start_date		[DATETIME],
	@stop_date		[DATETIME],
	@ad_size		[NVARCHAR](50),
	@price_id		[INT],
	@ad_number_click [int]
    ,@ad_number_view [int]
)
AS
BEGIN
	SET NOCOUNT ON;

	SELECT @ad_id = MAX([dbo].[Ad].[ad_id]) + 1
	FROM [dbo].[Ad]

	IF (@ad_id IS NULL)
	BEGIN
		SET @ad_id = 1;
	END

    INSERT INTO [dbo].[Ad]
           ([ad_id]
           ,[location_id]
           ,[client_id]
           ,[content_id]
           ,[author_id]
           ,[price_id]
           ,[start_date]
           ,[stop_date]
           ,[ad_size]
           ,[ad_number_click]
           ,[ad_number_view])
     VALUES
           (
		   @ad_id
           ,@location_id
           ,@client_id
           ,@content_id
           ,@author_id
           ,@price_id
           ,@start_date
           ,@stop_date
           ,@ad_size
           ,@ad_number_click
           ,@ad_number_view)
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 17/01/2018
-- Description:	Create select stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[AdSEL]
GO

ALTER PROCEDURE [dbo].[AdSEL] 
(
	@ad_id int
	,@client_name nvarchar(100)
	,@author_name nvarchar(100)
	,@start_date datetime
	,@stop_date datetime
)
AS
BEGIN
	SET NOCOUNT ON;

	IF(@ad_id = -1)
	BEGIN
		SET @ad_id = NULL
	END

    SELECT [ad].[ad_id]
      ,[ad].[location_id]
      ,[ad].[client_id]
      ,[ad].[content_id]
      ,[ad].[author_id]
      ,[ad].[price_id]
      ,[ad].[start_date]
      ,[ad].[stop_date]
      ,[ad].[ad_size]
      ,[ad].[ad_number_click]
      ,[ad].[ad_number_view]
	  ,[client].[name] AS client_name
	  ,[author].[first_name] + ' ' + [author].[last_name] AS author_name
  FROM [dbo].[Ad] ad

		INNER JOIN (	SELECT	[client_id]
								,[name]
						FROM	[dbo].[Client]
						WHERE	([name] LIKE '%' + @client_name + '%') OR (@client_name IS NULL)
					) AS client ON (client.client_id = ad.client_id)

		INNER JOIN (
						SELECT	[author_id]
								,[first_name]
								,[last_name]
						FROM	[dbo].[Author]
						WHERE	((([first_name] LIKE '%' + @author_name + '%') OR (@author_name IS NULL)) AND (([last_name] LIKE '%' + @author_name + '%') OR (@author_name IS NULL) ))
					) AS author ON (author.author_id = ad.author_id)

  WHERE (
			(([ad_id] = @ad_id) OR (@ad_id IS NULL))
			AND
			(([start_date] >= @start_date) OR (@start_date IS NULL))
			AND
			(([stop_date] <= @stop_date) OR (@stop_date IS NULL))
		)

	ORDER BY [ad_id] ASC
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create update stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[AdUPD]
GO

ALTER PROCEDURE [dbo].[AdUPD] 
(
	@ad_id			[INT] OUTPUT, 
	@location_id	[INT],
	@client_id		[INT],
	@content_id		[INT],
	@author_id		[INT],
	@start_date		[DATETIME],
	@stop_date		[DATETIME],
	@ad_size		[NVARCHAR](50),
	@priceid		[INT]
)
AS
BEGIN
	SET NOCOUNT ON;

    UPDATE [dbo].[Ad]
	   SET [ad_id] = @ad_id
		  ,[location_id] = @location_id
		  ,[client_id] = @client_id
		  ,[content_id] = @content_id
		  ,[author_id] = @author_id
		  ,[price_id] = @priceid
		  ,[start_date] = @start_date
		  ,[stop_date] = @stop_date
		  ,[ad_size] = @ad_size
	 WHERE [dbo].[Ad].[ad_id] = @ad_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create Author TABLE
-- =============================================

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Author]') AND type in (N'U'))
ALTER TABLE [dbo].[Author] DROP CONSTRAINT IF EXISTS [CK_author_phone]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Author]') AND type in (N'U'))
ALTER TABLE [dbo].[Author] DROP CONSTRAINT IF EXISTS [CK_author_email_address]
GO

CREATE TABLE [dbo].[Author](
	[author_id] [int] NOT NULL,
	[first_name] [nvarchar](50) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[phone] [nvarchar](15) NOT NULL,
	[email_address] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_author] PRIMARY KEY CLUSTERED 
(
	[author_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_author_email_address]') AND parent_object_id = OBJECT_ID(N'[dbo].[Author]'))
ALTER TABLE [dbo].[Author]  WITH CHECK ADD  CONSTRAINT [CK_author_email_address] CHECK  (([email_address] like '%@%.%'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_author_email_address]') AND parent_object_id = OBJECT_ID(N'[dbo].[Author]'))
ALTER TABLE [dbo].[Author] CHECK CONSTRAINT [CK_author_email_address]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_author_phone]') AND parent_object_id = OBJECT_ID(N'[dbo].[Author]'))
ALTER TABLE [dbo].[Author]  WITH CHECK ADD  CONSTRAINT [CK_author_phone] CHECK  (([phone] like '+[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]%'))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_author_phone]') AND parent_object_id = OBJECT_ID(N'[dbo].[Author]'))
ALTER TABLE [dbo].[Author] CHECK CONSTRAINT [CK_author_phone]
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 1/7/2018
-- Description:	Create delet stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[AuthorDEL]
GO

ALTER PROCEDURE [dbo].[AuthorDEL] 
(
	@author_id			[INT]
)
AS
BEGIN
	SET NOCOUNT ON;

    DELETE FROM [dbo].[Author]
		WHERE [dbo].[Author].[author_id] = @author_id
		
END
GO
 
 -- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create update stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[AuthorINS]
GO

ALTER PROCEDURE [dbo].[AuthorINS] 
(
	@author_id int OUTPUT
    ,@first_name nvarchar(50)
    ,@last_name nvarchar(50)
    ,@phone nvarchar(15)
    ,@email_address nvarchar(50)
)
AS
BEGIN
	SET NOCOUNT ON;

	SELECT @author_id = MAX([dbo].[Author].[author_id]) + 1
	FROM [dbo].[Author]

	IF (@author_id IS NULL)
	BEGIN
		SET @author_id = 1;
	END

   INSERT INTO [dbo].[Author]
           ([author_id]
           ,[first_name]
           ,[last_name]
           ,[phone]
           ,[email_address])
     VALUES
           (@author_id
			,@first_name
			,@last_name
			,@phone
			,@email_address)
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create select stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[AuthorSEL]
GO

ALTER PROCEDURE [dbo].[AuthorSEL] 
(
	@author_id		[INT]
	,@first_name	[NVARCHAR](50)
	,@last_name		[NVARCHAR](50)
)
AS
BEGIN
	SET NOCOUNT ON;
	
	IF(@author_id = -1)
	BEGIN
		SET @author_id = NULL
	END

    SELECT [author_id]
      ,[first_name]
      ,[last_name]
      ,[phone]
      ,[email_address]
	FROM [dbo].[Author]
	WHERE ((([last_name] LIKE '%' + @last_name + '%') OR (@last_name IS NULL)) AND 
			(([first_name] LIKE '%' + @first_name + '%') OR (@first_name IS NULL)) AND
			(([author_id] = @author_id) OR (@author_id IS NULL))
			)
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create update stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[AuthorUPD]
GO

ALTER PROCEDURE [dbo].[AuthorUPD] 
(
	@author_id int
    ,@first_name nvarchar(50)
    ,@last_name nvarchar(50)
    ,@phone nvarchar(15)
    ,@email_address nvarchar(50)
)
AS
BEGIN
	SET NOCOUNT ON;

   UPDATE [dbo].[Author]
   SET [first_name] = @first_name
      ,[last_name] = @last_name
      ,[phone] = @phone
      ,[email_address] = @email_address
	 WHERE [dbo].[Author].[author_id] = @author_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create City TABLE
-- =============================================

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[City]') AND type in (N'U'))
ALTER TABLE [dbo].[City] DROP CONSTRAINT IF EXISTS [FK_City_Region]
GO

CREATE TABLE [dbo].[City](
	[city_id] [int] NOT NULL,
	[city_name] [nvarchar](50) NOT NULL,
	[region_id] [int] NOT NULL,
 CONSTRAINT [PK_City] PRIMARY KEY CLUSTERED 
(
	[city_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_City_Region]') AND parent_object_id = OBJECT_ID(N'[dbo].[City]'))
ALTER TABLE [dbo].[City]  WITH CHECK ADD  CONSTRAINT [FK_City_Region] FOREIGN KEY([region_id])
REFERENCES [dbo].[Region] ([region_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_City_Region]') AND parent_object_id = OBJECT_ID(N'[dbo].[City]'))
ALTER TABLE [dbo].[City] CHECK CONSTRAINT [FK_City_Region]
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 1/7/2018
-- Description:	Create delet stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[CityDEL]
GO

ALTER PROCEDURE [dbo].[CityDEL] 
(
	@city_id			[INT]
)
AS
BEGIN
	SET NOCOUNT ON;

    DELETE FROM [dbo].[City]
		WHERE [dbo].[City].[city_id] = @city_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2017
-- Description:	Create insert stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[CityINS]
GO

ALTER PROCEDURE [dbo].[CityINS] 
(
	@city_id	int	OUTPUT
    ,@city_name  nvarchar(50)
    ,@region_id  int
)
AS
BEGIN
	SET NOCOUNT ON;

	SELECT @city_id = MAX([dbo].[City].[city_id]) + 1
	FROM [dbo].[City]

	IF (@city_id IS NULL)
	BEGIN
		SET @city_id = 1;
	END

    INSERT INTO [dbo].[City]
           ([city_id]
           ,[city_name]
           ,[region_id])
     VALUES
           (@city_id
           ,@city_name
           ,@region_id)
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create select stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[CitySEL]
GO

ALTER PROCEDURE [dbo].[CitySEL] 
(
	@city_id int
	,@city_name	nvarchar(50)
	,@region_id int
)
AS
BEGIN
	SET NOCOUNT ON;

	IF (@city_id = -1)
	BEGIN
		SET @city_id = NULL;
	END

	IF (@region_id = -1)
	BEGIN
		SET @region_id = NULL;
	END

    SELECT	[dbo].[City].[city_id]
			,[dbo].[City].[city_name]
			,[dbo].[City].[region_id]
	FROM	[dbo].[City]
			INNER JOIN [dbo].[Region] ON ([dbo].[City].[region_id] = [dbo].[Region].[region_id])
	WHERE (
			(([city_name] LIKE '%' + @city_name + '%') OR (city_name IS NULL)) AND 
			(([city_id] = @city_id ) OR (@city_id IS NULL)) AND
			(([dbo].[City].[region_id] = @region_id) OR (@region_id IS NULL))
		)
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create update stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[CityUPD]
GO

ALTER PROCEDURE [dbo].[CityUPD] 
(
	@city_id	int
    ,@city_name  nvarchar(50)
    ,@region_id  int
)
AS
BEGIN
	SET NOCOUNT ON;

   UPDATE [dbo].[City]
   SET [city_name] = @city_name
		,[region_id] = @region_id
	 WHERE [dbo].[City].[city_id] = @city_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create Client TABLE
-- =============================================

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Client]') AND type in (N'U'))
ALTER TABLE [dbo].[Client] DROP CONSTRAINT IF EXISTS [CK_client_email]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Client]') AND type in (N'U'))
ALTER TABLE [dbo].[Client] DROP CONSTRAINT IF EXISTS [FK_Client_City]
GO

CREATE TABLE [dbo].[Client](
	[client_id] [int] NOT NULL,
	[city_id] [int] NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[type] [nvarchar](50) NOT NULL,
	[street] [nvarchar](100) NOT NULL,
	[building_number] [nvarchar](50) NOT NULL,
	[cnp_cui] [nchar](15) NOT NULL,
	[url_client] [nvarchar](50) NOT NULL,
	[email_address] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_client] PRIMARY KEY CLUSTERED 
(
	[client_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Client_City]') AND parent_object_id = OBJECT_ID(N'[dbo].[Client]'))
ALTER TABLE [dbo].[Client]  WITH CHECK ADD  CONSTRAINT [FK_Client_City] FOREIGN KEY([city_id])
REFERENCES [dbo].[City] ([city_id])
ON DELETE CASCADE
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Client_City]') AND parent_object_id = OBJECT_ID(N'[dbo].[Client]'))
ALTER TABLE [dbo].[Client] CHECK CONSTRAINT [FK_Client_City]
GO

IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_client_email]') AND parent_object_id = OBJECT_ID(N'[dbo].[Client]'))
ALTER TABLE [dbo].[Client]  WITH CHECK ADD  CONSTRAINT [CK_client_email] CHECK  (([email_address] like '%@%.%' OR [email_address]=''))
GO

IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_client_email]') AND parent_object_id = OBJECT_ID(N'[dbo].[Client]'))
ALTER TABLE [dbo].[Client] CHECK CONSTRAINT [CK_client_email]
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 1/7/2018
-- Description:	Create delet stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[ClientDEL]
GO

ALTER PROCEDURE [dbo].[ClientDEL] 
(
	@client_id			[INT]
)
AS
BEGIN
	SET NOCOUNT ON;

    DELETE FROM [dbo].[Client]
		WHERE [dbo].[Client].[client_id] = @client_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2017
-- Description:	Create insert stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[ClientINS]
GO

ALTER PROCEDURE [dbo].[ClientINS] 
(
	@client_id int OUTPUT
    ,@city_id int
    ,@name nvarchar(100)
    ,@type nvarchar(50)
    ,@street nvarchar(100)
    ,@building_number nvarchar(50)
    ,@cnp_cui nchar(15)
    ,@url_client nvarchar(50)
    ,@email_address nvarchar(50)
)
AS
BEGIN
	SET NOCOUNT ON;

	SELECT @client_id = MAX([dbo].[Client].[client_id] ) + 1
	FROM [dbo].[Client]

	IF (@client_id IS NULL)
	BEGIN
		SET @client_id = 1;
	END

    INSERT INTO [dbo].[Client]
           ([client_id]
           ,[city_id]
           ,[name]
           ,[type]
           ,[street]
           ,[building_number]
           ,[cnp_cui]
           ,[url_client]
           ,[email_address])
     VALUES
           (@client_id
           ,@city_id
           ,@name
           ,@type
           ,@street
           ,@building_number
           ,@cnp_cui
           ,@url_client
           ,@email_address)
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create select stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[ClientSEL]
GO

ALTER PROCEDURE [dbo].[ClientSEL] 
(
	@client_id int
	,@name nvarchar(50)
	,@type nvarchar(50)
	,@cnp_cui	nchar(15)
	,@city_name nvarchar(50)
	,@region_name nvarchar(50)
)
AS
BEGIN
	SET NOCOUNT ON;

	IF (@name = '')
	BEGIN
		SET @name = NULL;
	END

	IF (@type = '')
	BEGIN
		SET @type = NULL;
	END

	IF (@cnp_cui = '')
	BEGIN
		SET @cnp_cui = NULL;
	END

	IF (@city_name = '')
	BEGIN
		SET @city_name = NULL;
	END
	
	IF (@region_name = '')
	BEGIN
		SET @region_name = NULL;
	END

	IF (@client_id = -1)
	BEGIN
		SET @client_id = NULL;
	END

    SELECT [dbo].[Client].[client_id]
      ,[dbo].[Client].[city_id]
      ,[dbo].[Client].[name]
      ,[dbo].[Client].[type]
      ,[dbo].[Client].[street]
      ,[dbo].[Client].[building_number]
      ,[dbo].[Client].[cnp_cui]
      ,[dbo].[Client].[url_client]
      ,[dbo].[Client].[email_address]
	  ,[dbo].[City].[city_name]
	  ,[dbo].[Region].[region_name]
	 FROM [dbo].[Client]
			INNER JOIN [dbo].[City] ON ([dbo].[Client].[city_id] = [dbo].[City].[city_id])
			INNER JOIN [dbo].[Region] ON ([dbo].[Region].[region_id] = [dbo].[City].[region_id])
	WHERE (
			(([client_id] = @client_id) OR (@client_id IS NULL)) AND
			(([name] LIKE '%' + @name + '%') OR (@name IS NULL)) AND 
			(([type] LIKE '%' + @type + '%') OR (@type IS NULL)) AND 
			(([cnp_cui] LIKE '%' + @cnp_cui + '%') OR (@cnp_cui IS NULL)) AND 
			([Client].[city_id] IN (	SELECT	[city_id]
									FROM	[dbo].[City]
									WHERE	((([city_name] LIKE '%' + @city_name + '%') OR (@city_name IS NULL) AND
											[City].[region_id] IN (	SELECT	[Region].[region_id]
																	FROM	[dbo].[Region]
																	WHERE	([region_name] LIKE '%' + @region_name + '%') OR (@region_name IS NULL)
																)
													))
									)
			))
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create update stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[ClientUPD]
GO

ALTER PROCEDURE [dbo].[ClientUPD] 
(
	@client_id int
    ,@city_id int
    ,@name nvarchar(100)
    ,@type nvarchar(50)
    ,@street nvarchar(100)
    ,@building_number nvarchar(50)
    ,@cnp_cui nchar(15)
    ,@url_client nvarchar(50)
    ,@email_address nvarchar(50)
)
AS
BEGIN
	SET NOCOUNT ON;

   UPDATE [dbo].[Client]
   SET [city_id] = @city_id
		,[name] = @name
		,[type] = @type
		,[street] = @street
		,[building_number] = @building_number
		,[cnp_cui] = @cnp_cui
		,[url_client] = @url_client
		,[email_address] = @email_address
	WHERE [dbo].[Client].[client_id] = @client_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create Content TABLE
-- =============================================

DROP TABLE IF EXISTS [dbo].[Content]
GO

CREATE TABLE [dbo].[Content](
	[content_id] [int] NOT NULL,
	[title] [nvarchar](100) NOT NULL,
	[body] [nvarchar](500) NOT NULL,
	[media] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_Content] PRIMARY KEY CLUSTERED 
(
	[content_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 1/7/2018
-- Description:	Create delet stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[ContentDEL]
GO

ALTER PROCEDURE [dbo].[ContentDEL] 
(
	@content_id			[INT]
)
AS
BEGIN
	SET NOCOUNT ON;

    DELETE FROM [dbo].[Content]
		WHERE [dbo].[Content].[content_id] = @content_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2017
-- Description:	Create insert stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[ContentINS]
GO

ALTER PROCEDURE [dbo].[ContentINS] 
(
	@content_id int OUTPUT
    ,@title nvarchar(100)
    ,@body nvarchar(500)
    ,@media nvarchar(100)
)
AS
BEGIN
	SET NOCOUNT ON;

	SELECT @content_id = MAX([dbo].[Content].[content_id] ) + 1
	FROM [dbo].[Content]

	IF (@content_id IS NULL)
	BEGIN
		SET @content_id = 1;
	END

    INSERT INTO [dbo].[Content]
           ([content_id]
           ,[title]
           ,[body]
           ,[media])
     VALUES
           (@content_id
           ,@title
           ,@body
           ,@media)
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create select stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[ContentSEL]
GO

ALTER PROCEDURE [dbo].[ContentSEL] 
(
	@content_id int
)
AS
BEGIN
	SET NOCOUNT ON;

	IF (@content_id = -1)
	BEGIN
		SET @content_id = NULL;
	END

    SELECT	[dbo].[Content].[content_id]
			,[dbo].[Content].[body]
			,[dbo].[Content].[media]
			,[dbo].[Content].title
	FROM [dbo].[Content]
	WHERE [content_id] = @content_id OR @content_id IS NULL
	ORDER BY [content_id] ASC
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create update stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[ContentUPD]
GO

ALTER PROCEDURE [dbo].[ContentUPD] 
(
	@content_id int
	,@title nvarchar(100)
    ,@body nvarchar(500)
    ,@media nvarchar(100)
)
AS
BEGIN
	SET NOCOUNT ON;

   UPDATE [dbo].[Content]
   SET [content_id] = @content_id
      ,[title] = @title
      ,[body] = @body
      ,[media] = @media
	WHERE [dbo].[Content].[content_id] = @content_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create Link_ad_author TABLE
-- =============================================

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Link_ad_author]') AND type in (N'U'))
ALTER TABLE [dbo].[Link_ad_author] DROP CONSTRAINT IF EXISTS [FK_Link_ad_author_Author]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Link_ad_author]') AND type in (N'U'))
ALTER TABLE [dbo].[Link_ad_author] DROP CONSTRAINT IF EXISTS [FK_Link_ad_author_Ad]
GO

BEGIN
CREATE TABLE [dbo].[Link_ad_author](
	[ad_id] [int] NOT NULL,
	[author_id] [int] NOT NULL,
 CONSTRAINT [PK_Link_ad_author] PRIMARY KEY CLUSTERED 
(
	[ad_id] ASC,
	[author_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create Location TABLE
-- =============================================

DROP TABLE IF EXISTS [dbo].[Location]
GO

BEGIN
CREATE TABLE [dbo].[Location](
	[location_id] [int] NOT NULL,
	[coordonate_x] [decimal](18, 2) NOT NULL,
	[coordonate_y] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_location] PRIMARY KEY CLUSTERED 
(
	[location_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 1/7/2018
-- Description:	Create delet stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[LocationDEL]
GO

ALTER PROCEDURE [dbo].[LocationDEL] 
(
	@location_id			[INT]
)
AS
BEGIN
	SET NOCOUNT ON;

    DELETE FROM [dbo].[Location]
		WHERE [dbo].[Location].[location_id] = @location_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2017
-- Description:	Create insert stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[LocationINS]
GO

ALTER PROCEDURE [dbo].[LocationINS] 
(
	@location_id int OUTPUT
    ,@coordinate_x decimal(18,2)
    ,@coordinate_y decimal(18,2)
)
AS
BEGIN
	SET NOCOUNT ON;

	SELECT @location_id = MAX([dbo].[Location].[location_id] ) + 1
	FROM [dbo].[Location]

	IF (@location_id IS NULL)
	BEGIN
		SET @location_id = 1;
	END

    INSERT INTO [dbo].[Location]
           ([location_id]
           ,[coordonate_x]
           ,[coordonate_y])
     VALUES
           (@location_id
           ,@coordinate_x
           ,@coordinate_y)
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create select stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[LocationSEL]
GO

ALTER PROCEDURE [dbo].[LocationSEL] 
(
	@location_id int
)
AS
BEGIN
	SET NOCOUNT ON;

	IF (@location_id = -1)
	BEGIN
		SET @location_id = NULL;
	END

    SELECT	[dbo].[Location].[location_id]
			,[dbo].[Location].[coordonate_x]
			,[dbo].[Location].[coordonate_y]
	FROM [dbo].[Location]
	WHERE [location_id] = @location_id OR @location_id IS NULL
	ORDER BY [location_id] ASC
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create update stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[LocationUPD]
GO

ALTER PROCEDURE [dbo].[LocationUPD] 
(
	@location_id int
	,@coordonate_x decimal(18,2)
    ,@coordonate_y decimal(18,2)
)
AS
BEGIN
	SET NOCOUNT ON;

   UPDATE [dbo].[Location]
   SET [location_id] = @location_id
      ,[coordonate_x] = @coordonate_x
      ,[coordonate_y] = @coordonate_y
	WHERE [dbo].[Location].[location_id] = @location_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create Price TABLE
-- =============================================

DROP TABLE IF EXISTS [dbo].[Price]
GO

BEGIN
CREATE TABLE [dbo].[Price](
	[price_id] [int] NOT NULL,
	[price_unit] [nvarchar](50) NOT NULL,
	[price_cost_click] [decimal](18, 2) NOT NULL,
	[price_cost_view] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_Price] PRIMARY KEY CLUSTERED 
(
	[price_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 1/7/2018
-- Description:	Create delet stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[PriceDEL]
GO

ALTER PROCEDURE [dbo].[PriceDEL] 
(
	@price_id			[INT]
)
AS
BEGIN
	SET NOCOUNT ON;

    DELETE FROM [dbo].[Price]
		WHERE [dbo].[Price].[price_id] = @price_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2017
-- Description:	Create insert stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[PriceINS]
GO

ALTER PROCEDURE [dbo].[PriceINS] 
(
	@price_id int OUTPUT
    ,@price_unit nvarchar(50)
    ,@price_cost_click decimal(18,2)
    ,@price_cost_view decimal(18,2)
)
AS
BEGIN
	SET NOCOUNT ON;

	SELECT @price_id = MAX([dbo].[Price].[price_id] ) + 1
	FROM [dbo].[Price]

	IF (@price_id IS NULL)
	BEGIN
		SET @price_id = 1;
	END

    INSERT INTO [dbo].[Price]
           ([price_id]
           ,[price_unit]
           ,[price_cost_click]
           ,[price_cost_view])
     VALUES
           (@price_id 
           ,@price_unit 
           ,@price_cost_click 
           ,@price_cost_view)
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create select stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[PriceSEL]
GO

ALTER PROCEDURE [dbo].[PriceSEL] 
(
	@price_id int
)
AS
BEGIN
	SET NOCOUNT ON;

	IF (@price_id = -1)
	BEGIN
		SET @price_id = NULL;
	END

    SELECT [price_id]
      ,[price_unit]
      ,[price_cost_click]
      ,[price_cost_view]
	FROM [dbo].[Price]
	WHERE [price_id] = @price_id OR @price_id IS NULL
	ORDER BY [price_id] ASC
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create update stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[PriceUPD]
GO

ALTER PROCEDURE [dbo].[PriceUPD] 
(
	@price_id int
    ,@price_unit nvarchar(50)
    ,@price_cost_click decimal(18,2)
    ,@price_cost_view decimal(18,2)
)
AS
BEGIN
	SET NOCOUNT ON;

   UPDATE [dbo].[Price]
   SET [price_id] = @price_id
      ,[price_unit] = @price_unit
      ,[price_cost_click] = @price_cost_click
      ,[price_cost_view] = @price_cost_view
	WHERE [dbo].[Price].[price_id] = @price_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create Region TABLE
-- =============================================

DROP TABLE IF EXISTS [dbo].[Region]
GO

BEGIN
CREATE TABLE [dbo].[Region](
	[region_id] [int] NOT NULL,
	[region_name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Region] PRIMARY KEY CLUSTERED 
(
	[region_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 1/7/2018
-- Description:	Create delet stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[RegionDEL]
GO

ALTER PROCEDURE [dbo].[RegionDEL] 
(
	@region_id			[INT]
)
AS
BEGIN
	SET NOCOUNT ON;

    DELETE FROM [dbo].[Region]
		WHERE [dbo].[Region].[region_id] = @region_id
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 7/1/2018
-- Description:	Create update stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[RegionUPD]
GO

ALTER PROCEDURE [dbo].[RegionUPD] 
(
	@region_ID		[INT] OUTPUT, 
	@region_name	[NVARCHAR] (50)
)
AS
BEGIN
	SET NOCOUNT ON;

   UPDATE [dbo].[Region]
   SET [region_id] = @region_ID
      ,[region_name] = @region_name
	WHERE [dbo].[Region].[region_id] = @region_ID
		
END
GO

-- =============================================
-- Author:		Cãtãlin-Rãzvan Barbãlatã
-- Create date: 30/10/2017
-- Description:	Create select stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[RegionSEL]
GO

ALTER PROCEDURE [dbo].[RegionSEL] 
(
	@region_id		[INT]
	,@region_name	[NVARCHAR](50)
)
AS
BEGIN
	SET NOCOUNT ON;

	IF (@region_ID = -1)
	BEGIN
		SET @region_ID = NULL;
	END

    SELECT	[dbo].[Region].[region_id]
			,[dbo].[Region].[region_name]
	FROM	[dbo].[Region]
	WHERE	(
				(([dbo].[Region].[region_id] = @region_id) OR (@region_id IS NULL)) AND 
				(([dbo].[Region].[region_name] LIKE '%' + @region_name + '%') OR (@region_name IS NULL))
			)
		
END
GO

-- =============================================
-- Author:		Cătălin-Răzvan Barbălată
-- Create date: 7/1/2018
-- Description:	Create insert stored procedure
-- =============================================

DROP PROCEDURE IF EXISTS [dbo].[RegionINS]
GO

ALTER PROCEDURE [dbo].[RegionINS] 
(
	@region_ID		[INT] OUTPUT, 
	@region_name	[NVARCHAR] (50)
)
AS
BEGIN
	SET NOCOUNT ON;

	SELECT @region_ID = MAX([dbo].[Region].[region_id]) + 1
	FROM [dbo].[Region]

	IF (@region_ID IS NULL)
	BEGIN
		SET @region_ID = 1;
	END

    INSERT INTO [dbo].[Region]
           ([region_id]
           ,[region_name])
	VALUES
	(
		@region_id,
		@region_name
	)
		
END
GO

