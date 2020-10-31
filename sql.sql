IF OBJECT_ID('dbo.[bookups]', 'U') IS NOT NULL
	DROP TABLE dbo.[bookups]
 GO
IF OBJECT_ID('dbo.[trips]', 'U') IS NOT NULL
	DROP TABLE dbo.trips
 GO
IF OBJECT_ID('dbo.[passenger_information]', 'U') IS NOT NULL
	DROP TABLE dbo.passenger_information
 GO
IF OBJECT_ID('dbo.[destinations]', 'U') IS NOT NULL
	DROP TABLE dbo.destinations
 GO
IF OBJECT_ID('dbo.[airplanes]', 'U') IS NOT NULL
	DROP TABLE dbo.[airplanes]
 GO
IF OBJECT_ID('dbo.[airplane_type]', 'U') IS NOT NULL
	DROP TABLE dbo.[airplane_type]
 GO


CREATE TABLE [airplane_type] (
	id integer NOT NULL IDENTITY PRIMARY KEY,
	airplane_type varchar(50) NOT NULL
)
GO
CREATE TABLE [airplanes] (
	id integer NOT NULL IDENTITY PRIMARY KEY,
	name varchar(50),
	airplane_type_id integer NOT NULL,
	capacity integer NOT NULL
)
GO
CREATE TABLE [destinations] (
	id integer NOT NULL IDENTITY PRIMARY KEY,
	country varchar(50) NOT NULL
)
GO
CREATE TABLE [trips] (
	id integer NOT NULL IDENTITY PRIMARY KEY,
	airplane_id integer NOT NULL,
	destination_id integer NOT NULL,
	arrival_id integer NOT NULL,
	date datetime NOT NULL DEFAULT GETDATE()
)
GO
CREATE TABLE [passenger_information] (
	id integer NOT NULL IDENTITY PRIMARY KEY,
	name varchar(50) NOT NULL,
	phone varchar(50) NOT NULL UNIQUE CHECK(phone LIKE '+375[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
)
GO
CREATE TABLE [bookups] (
	trip_id integer NOT NULL,
	passenger_id integer NOT NULL,
	date datetime NOT NULL DEFAULT GETDATE()
)

ALTER TABLE bookups ADD CONSTRAINT bookups_pk PRIMARY KEY (trip_id, passenger_id, date);

ALTER TABLE airplanes ADD CONSTRAINT airplanes_fk_airplane_type FOREIGN KEY ([airplane_type_id]) REFERENCES [airplane_type]([id]);

ALTER TABLE trips ADD CONSTRAINT trips_fk_airplane FOREIGN KEY (airplane_id) REFERENCES [airplanes]([id]);
ALTER TABLE trips ADD CONSTRAINT trips_fk_destination FOREIGN KEY (destination_id) REFERENCES [destinations]([id]);
ALTER TABLE trips ADD CONSTRAINT trips_fk_arrival FOREIGN KEY (arrival_id) REFERENCES [destinations]([id]);

ALTER TABLE bookups ADD CONSTRAINT bookups_fk_trip FOREIGN KEY (trip_id) REFERENCES [trips]([id]);
ALTER TABLE bookups ADD CONSTRAINT bookups_fk_passenger FOREIGN KEY (passenger_id) REFERENCES [passenger_information]([id]);

GO
CREATE NONCLUSTERED INDEX trips_date
ON trips(date);
GO

GO
CREATE NONCLUSTERED INDEX passenger_information
ON passenger_information(name);
GO