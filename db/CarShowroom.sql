CREATE DATABASE CarShowroom
GO

USE CarShowroom
GO

CREATE TABLE Role
(
	IDRole INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	RoleName NVARCHAR(20)
)
GO
INSERT Role VALUES	('Admin'),
					('Staff')	
GO
SELECT * FROM Role
GO
CREATE TABLE UserSystem
(
	IDUser INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	IDRole INT FOREIGN KEY REFERENCES Role(IDRole),
	Username NVARCHAR(50),
	Password NVARCHAR(50),
	Fullname NVARCHAR(50),
	Gender BIT,
	Address NVARCHAR(100),
	Phone NVARCHAR(11),
	Email NVARCHAR(100)
	
)
GO
INSERT UserSystem VALUES		(1,'admin','admin','Nguyen Ngoc Tuyen',1,'Ha Noi','0973874452','thinghost76@gmail.com'),
								(2,'nvien','nvien','Dao Quang Hoan',0,'Ha Noi','0913451122','nvien@gmail.com')
							
GO
SELECT * FROM UserSystem
GO


CREATE TABLE Supplier
(
	IDSup INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	SuppName NVARCHAR(20),
	Address NVARCHAR(100),
	Phone NVARCHAR(11),
	Email NVARCHAR(100)
)
GO
INSERT Supplier VALUES	('Nha cung cap 1','Ha Dong - Ha Noi','048842689','ncc1@gmail.com'),
						('Nha cung cap 2','Hai Phong','0451847515','ncc2@gmail.com'),
						('Nha cung cap 3','TP. HCM','08588426','ncc3@gmail.com')
GO
SELECT * FROM Supplier
GO

CREATE TABLE Customer
(
	IDCus INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	Fullname NVARCHAR(50),
	Gender BIT,
	Address NVARCHAR(100),
	Phone NVARCHAR(11),
	IdentityCard NVARCHAR(15),
	Email NVARCHAR(100)
)
GO
INSERT Customer VALUES		('Khach Hang 1',1,'Ha Noi','0987546562','158877412','kh1@gmail.com'),
							('Khach Hang 2',1,'Hoa Binh','0979432296','199634775','kh2@gmail.com'),
							('Khach Hang 3',0,'Hung Yen','0979402196','199546742','kh3@gmail.com'),
							('Khach Hang 4',0,'Hai Phong','0912320096','199634234','kh4@gmail.com'),
							('Khach Hang 5',1,'Sai Gon','0974321096','199548785','kh5@gmail.com')
GO
SELECT * FROM Customer
GO

CREATE TABLE Brand
(
	IDBrand INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	BrandName NVARCHAR(30),
	Address NVARCHAR(100),
	Phone NVARCHAR(11),
	Email NVARCHAR(50)
)
GO
INSERT Brand VALUES		('Audi','Ha Noi','0986855875','audi@gmail.com'),
						('BMW','Hai Phong','0986832125','bmw@gmail.com'),
						('Chervolet','TP. HCM','0936185875','chervolet@gmail.com'),
						('Daewoo','Thanh Hoa','01236852875','daewoo@gmail.com'),
						('Daihatsu','Quang Binh','0972285875','daihatsu@gmail.com'),
						('Ford','Da Lat','09342685875','ford@gmail.com'),
						('Honda','Ha Noi','0992685875','honda@gmail.com'),
						('Huyndai','Hue','0981685832','huyndai@gmail.com'),
						('Isuzu','Ha Giang','09783685123','isuzu@gmail.com'),
						('Mercedes Benz','Ha Noi','0921685875','mercedesbenz@gmail.com'),
						('Mitsibishi','Quang Ninh','0972685666','mitsubishi@gmail.com'),
						('Nissan','TP. HCM','01243685555','nissan@gmail.com'),
						('Toyota','Ha Noi','01236821125','toyota@gmail.com'),
						('Suzuki','Ha Noi','0932685321','suzuki@gmail.com')
GO
SELECT * FROM Brand
GO

CREATE TABLE Model
(
	IDModel INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	IDBrand INT FOREIGN KEY REFERENCES Brand(IDBrand),
	ModelName NVARCHAR(20)
)
GO
INSERT Model VALUES	(1,'A1'),(1,'A4'),
					(1,'A5'),(1,'A2'),
					(1,'Q5'),(1,'A3'),
					(2,'1 Series'),(2,'2 Series'),
					(2,'3 Series'),(2,'M3'),
					(2,'X1'),(2,'Z4'),
					(3,'Aveo'),(3,'Camaro'),
					(3,'Berreta'),(3,'Lacetti'),
					(3,'Cruze'),(3,'Mtiz'),
					(4,'Aranos'),(4,'Damas'),
					(4,'Racer'),(4,'Damas'),
					(4,'Labo'),(4,'Evanda'),
					(5,'Charade'),(5,'Citivan'),
					(5,'Hijte'),(5,'Terios'),
					(6,'Caravan'),(6,'Escape'),
					(6,'Flex'),(6,'Focus'),
					(6,'Ranger'),(6,'Taurus'),
					(7,'Acura'),(7,'CR Z'),
					(7,'Civic'),(7,'Jazz'),
					(7,'NSX'),(7,'Vigor'),
					(8,'Accent'),(8,'Atos'),
					(8,'Getz'),(8,'Avante'),
					(8,'i30'),(8,'Lantra'),
					(9,'Amigo'),(9,'Ascender'),
					(9,'NMR'),(9,'NLR'),
					(9,'QKR'),(9,'Rodeo'),
					(10,'190'),(10,'C class'),
					(10,'M class'),(10,'CLS class'),
					(10,'SLR'),(10,'SL class'),
					(11,'3000GT'),(11,'Colt'),
					(11,'Eclipse'),(11,'Gala'),
					(11,'Hover'),(11,'L200'),
					(12,'100NX'),(12,'200SX'),
					(12,'Cima'),(12,'Cedric'),
					(12,'Livina'),(12,'Liberty'),
					(13,'Avalon'),(13,'Allion'),
					(13,'Caldina'),(13,'Cami'),
					(13,'Venza'),(13,'Innova'),
					(14,'Alto'),(14,'Carry'),
					(14,'APV'),(14,'Liana'),
					(14,'Swift'),(14,'Cultis')
GO
SELECT * FROM Model
GO

CREATE TABLE Car
(
	IDCar INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	IDModel INT FOREIGN KEY REFERENCES Model(IDModel),
	CarName NVARCHAR(50),
	Picture1 NVARCHAR(200),
	Picture2 NVARCHAR(200),
	Picture3 NVARCHAR(200),
	Picture4 NVARCHAR(200),
	YearCar INT,
	Price FLOAT,
	Engine NVARCHAR(20),
	EngineType NVARCHAR(20),
	Fuel NVARCHAR(10),
	Tranmission NVARCHAR(30),
	MaxCapacity NVARCHAR(30),
	MaxSpeed INT,
	Length INT,
	Width INT,
	Height INT,
	Weight INT,
	Numberseat INT,
	Status TINYINT
	
)
GO
INSERT Car VALUES	(1,'Audi Q5 SUV/Crossover','src\images\audiQ5.jpg','src\images\audiQ51.jpg','src\images\audiQ52.jpg','src\images\audiQ53.jpg',2010,60000,'3.0 (lit)','V6','Xang','8-spd w/OD','211 hp @ 4300 rpm','203','5088','1984','1737','751',5,1),
					(7,'BMW 3 Series','src\images\bmw3s.jpg','src\images\bmw3s1.jpg','src\images\bmw3s2.jpg','src\images\bmw3s3.jpg',2009,56000,'2.0 (lit)','2.0 Turbo, 4 xilanh','Xang','Tu dong','230 @ 6500 RPM','289','4587','1783','1384','700',5,1),
					(13,'Chevrolet Cruze','src\images\chervolet.jpg','src\images\chervolet1.jpg','src\images\chervolet2.jpg','src\images\chervolet3.jpg',2010,49000,'1.6DOHC II','4 cyl.DOHC','Xang','So tay','107/ 6000@rpm','180','4597','1788','1477','743',5,1),
					(38,'Honda Jazz','src\images\honda.jpg','src\images\honda1.jpg','src\images\honda2.jpg','src\images\honda3.jpg',2007,39000,'1.6DOHC II','4 cyl.DOHC','Xang','So tu dong','107/ 6000@rpm','180','5026','1788','1852','750',4,1),
					(43,'Hyundai Getz','src\images\hyundai.jpg','src\images\hyundai1.jpg','src\images\hyundai2.jpg','src\images\hyundai3.jpg',2010,28000,'1.6DOHC II','4 cyl.DOHC','Xang','So tu dong','107/ 6000@rpm','180','4062','1500','1654','730',5,1)
GO
SELECT * FROM Car
GO

CREATE TABLE ReceiveInvoice
(
	IDReceive INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	IDSup INT NOT NULL FOREIGN KEY REFERENCES Supplier(IDSup),
	IDUser INT NOT NULL FOREIGN KEY REFERENCES UserSystem(IDUser),
	IDCar INT NOT NULL FOREIGN KEY REFERENCES Car(IDCar),
	ReceiveDate DATETIME,
	Quantity INT,
	InputPrice FLOAT,
	Status BIT
)
GO
INSERT ReceiveInvoice VALUES	(1,1,1,'2012-12-2',1,60000,1),
								(2,1,2,'2012-10-12',2,56000,1),
								(3,2,3,'2012-5-2',1,49000,1),
								(3,2,4,'2012-7-1',2,39000,1),
								(3,2,5,'2012-9-15',2,28000,1)
GO
SELECT * FROM ReceiveInvoice
GO

CREATE TABLE PayType
(
	IDPay INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	PayName NVARCHAR(100)
)
GO
INSERT PayType VALUES	('Pay All'),
						('Installment')
GO
SELECT * FROM PayType
GO

CREATE TABLE SaleInvoice
(
	IDSale INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	IDCus INT NOT NULL FOREIGN KEY REFERENCES Customer(IDCus),
	IDCar INT NOT NULL FOREIGN KEY REFERENCES Car(IDCar),
	IDUser INT NOT NULL FOREIGN KEY REFERENCES UserSystem(IDUser),
	IDPay INT NOT NULL FOREIGN KEY REFERENCES PayType(IDPay),
	SaleDate DATETIME,
	SellPrice FLOAT,
	Installment FLOAT,
	Pay1 FLOAT,
	Date1 DATETIME,
	Pay2 FLOAT,
	Date2 DATETIME,
	Pay3 FLOAT,
	Date3 DATETIME,
	Pay4 FLOAT,
	Date4 DATETIME,
	Pay5 FLOAT,
	Date5 DATETIME,
	Pay6 FLOAT,
	Date6 DATETIME,
	LastDate DATETIME,
	Status BIT
)
GO
INSERT SaleInvoice VALUES	(1,1,1,1,'2012-12-2',69000,69000,null,null,null,null,null,null,null,null,null,null,null,null,'2012-12-2',1),
							(4,5,1,1,'2013-1-2',32200,32200,null,null,null,null,null,null,null,null,null,null,null,null,'2013-1-2',1),
							(2,3,2,2,'2012-7-28',56350,22540,5635,'2013-1-28',null,null,null,null,null,null,null,null,null,null,'2015-7-28',0)		
GO
SELECT * FROM SaleInvoice
GO
