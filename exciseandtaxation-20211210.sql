-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: exciseandtaxation
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `brand` (
  `IDBrand` int(11) NOT NULL AUTO_INCREMENT,
  `BrandName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`IDBrand`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Audi','Germany','0986855875','audi@gmail.com'),(2,'BMW','Germany','0986832125','bmw@gmail.com'),(3,'OPEL','Germany','0936185875','opel@gmail.com'),(4,'Daewoo','Japan','01236852875','daewoo@gmail.com'),(5,'Ford','USA','09342685875','ford@gmail.com'),(6,'Honda','Japan','0992685875','honda@gmail.com'),(7,'Huyndai','Japan','0981685832','huyndai@gmail.com'),(8,'Mercedes Benz','Germany','0921685875','mercedesbenz@gmail.com'),(9,'Toyota','Japan','01236821125','toyota@gmail.com'),(10,'Suzuki','Japan','0932685321','suzuki@gmail.com');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `car` (
  `IDCar` int(11) NOT NULL AUTO_INCREMENT,
  `IDModel` int(11) DEFAULT NULL,
  `CarName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Picture1` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Picture2` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Picture3` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Picture4` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `YearCar` int(11) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `Engine` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `EngineType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Fuel` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Tranmission` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MaxCapacity` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MaxSpeed` int(11) DEFAULT NULL,
  `Length` int(11) DEFAULT NULL,
  `Width` int(11) DEFAULT NULL,
  `Height` int(11) DEFAULT NULL,
  `Weight` int(11) DEFAULT NULL,
  `Numberseat` int(11) DEFAULT NULL,
  `Status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`IDCar`),
  KEY `IDModel` (`IDModel`),
  CONSTRAINT `car_ibfk_1` FOREIGN KEY (`IDModel`) REFERENCES `model` (`IDModel`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (5,1,'Audi Q5 SUV/Crossover','srcimagesaudiQ5.jpg','srcimagesaudiQ51.jpg','srcimagesaudiQ52.jpg','srcimagesaudiQ53.jpg',2010,600000,'3.0 (lit)','2.0 Turbo','Diesel','Manual','280 @ 6500 RPM',203,5088,1984,1737,751,5,1),(6,7,'BMW 3 Series','srcimagesmw3s.jpg','srcimagesmw3s1.jpg','srcimagesmw3s2.jpg','srcimagesmw3s3.jpg',2009,560000,'2.0 (lit)','2.0 Turbo','Petrol','Automatic','230 @ 6500 RPM',289,4587,1783,1384,700,5,1),(7,37,'XYZ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(8,34,'Owner',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(9,39,'tanzil',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `IDCus` int(11) NOT NULL AUTO_INCREMENT,
  `Fullname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Gender` bit(1) DEFAULT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `IdentityCard` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`IDCus`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Customer 1',_binary '','H9','0987546562','71501-158877412','Customer1@gmail.com'),(2,'Customer 2',_binary '','H8','0979432296','71501-199634775','Customer2@gmail.com'),(3,'Customer 3',_binary '\0','G8','0979402196','71501-199546742','Customer3@gmail.com'),(4,'Customer 4',_binary '\0','G7','0912320096','71501-199634234','Customer4@gmail.com'),(5,'Customer 5',_binary '','I9','0974321096','71501-199548785','Customer5@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `model` (
  `IDModel` int(11) NOT NULL AUTO_INCREMENT,
  `IDBrand` int(11) DEFAULT NULL,
  `ModelName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`IDModel`),
  KEY `IDBrand` (`IDBrand`),
  CONSTRAINT `model_ibfk_1` FOREIGN KEY (`IDBrand`) REFERENCES `brand` (`IDBrand`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,1,'A1'),(2,1,'A4'),(3,1,'A5'),(4,1,'A2'),(5,1,'Q5'),(6,1,'A3'),(7,2,'1 Series'),(8,2,'2 Series'),(9,2,'3 Series'),(10,2,'M3'),(11,2,'X1'),(12,2,'Z4'),(13,3,'Astra'),(14,3,'Insignia'),(15,3,'Corsa'),(16,3,'Mtiz'),(17,4,'Aranos'),(18,4,'Damas'),(19,4,'Racer'),(20,4,'Evanda'),(21,5,'Focus'),(22,5,'Ranger'),(23,6,'Civic'),(24,6,'Jazz'),(25,7,'NSX'),(26,7,'Vigor'),(27,8,'190'),(28,8,'C class'),(29,8,'M class'),(30,8,'CLS class'),(31,8,'SLR'),(32,8,'G class'),(33,9,'V8'),(34,9,'Rush'),(35,9,'LandCruiser'),(36,9,'TX'),(37,10,'Alto'),(38,10,'Carry'),(39,10,'Swift'),(40,10,'Cultis');
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paytype`
--

DROP TABLE IF EXISTS `paytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `paytype` (
  `IDPay` int(11) NOT NULL AUTO_INCREMENT,
  `PayName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`IDPay`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paytype`
--

LOCK TABLES `paytype` WRITE;
/*!40000 ALTER TABLE `paytype` DISABLE KEYS */;
INSERT INTO `paytype` VALUES (1,'Pay Cash'),(2,'Installments');
/*!40000 ALTER TABLE `paytype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiveinvoice`
--

DROP TABLE IF EXISTS `receiveinvoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `receiveinvoice` (
  `IDReceive` int(11) NOT NULL AUTO_INCREMENT,
  `IDSup` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `IDCar` int(11) NOT NULL,
  `ReceiveDate` datetime DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `InputPrice` float DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`IDReceive`),
  KEY `IDSup` (`IDSup`),
  KEY `IDUser` (`IDUser`),
  KEY `IDCar` (`IDCar`),
  CONSTRAINT `receiveinvoice_ibfk_1` FOREIGN KEY (`IDSup`) REFERENCES `supplier` (`IDSup`),
  CONSTRAINT `receiveinvoice_ibfk_2` FOREIGN KEY (`IDUser`) REFERENCES `usersystem` (`IDUser`),
  CONSTRAINT `receiveinvoice_ibfk_3` FOREIGN KEY (`IDCar`) REFERENCES `car` (`IDCar`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiveinvoice`
--

LOCK TABLES `receiveinvoice` WRITE;
/*!40000 ALTER TABLE `receiveinvoice` DISABLE KEYS */;
INSERT INTO `receiveinvoice` VALUES (15,1,1,5,'2012-12-02 00:00:00',1,6000,_binary ''),(16,3,2,6,'2020-12-31 00:00:00',1,56000,_binary ''),(17,3,2,5,'2018-11-20 00:00:00',3,100000,_binary ''),(18,2,1,6,'2015-11-20 00:00:00',2,700000,_binary '');
/*!40000 ALTER TABLE `receiveinvoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `IDRole` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDRole`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Staff'),(3,'contacts');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saleinvoice`
--

DROP TABLE IF EXISTS `saleinvoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `saleinvoice` (
  `IDSale` int(11) NOT NULL AUTO_INCREMENT,
  `IDCus` int(11) NOT NULL,
  `IDCar` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `IDPay` int(11) NOT NULL,
  `SaleDate` datetime DEFAULT NULL,
  `SellPrice` float DEFAULT NULL,
  `Installment` float DEFAULT NULL,
  `Pay1` float DEFAULT NULL,
  `Date1` datetime DEFAULT NULL,
  `Pay2` float DEFAULT NULL,
  `Date2` datetime DEFAULT NULL,
  `Pay3` float DEFAULT NULL,
  `Date3` datetime DEFAULT NULL,
  `Pay4` float DEFAULT NULL,
  `Date4` datetime DEFAULT NULL,
  `Pay5` float DEFAULT NULL,
  `Date5` datetime DEFAULT NULL,
  `Pay6` float DEFAULT NULL,
  `Date6` datetime DEFAULT NULL,
  `LastDate` datetime DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`IDSale`),
  KEY `IDCus` (`IDCus`),
  KEY `IDCar` (`IDCar`),
  KEY `IDUser` (`IDUser`),
  KEY `IDPay` (`IDPay`),
  CONSTRAINT `saleinvoice_ibfk_1` FOREIGN KEY (`IDCus`) REFERENCES `customer` (`IDCus`),
  CONSTRAINT `saleinvoice_ibfk_2` FOREIGN KEY (`IDCar`) REFERENCES `car` (`IDCar`),
  CONSTRAINT `saleinvoice_ibfk_3` FOREIGN KEY (`IDUser`) REFERENCES `usersystem` (`IDUser`),
  CONSTRAINT `saleinvoice_ibfk_4` FOREIGN KEY (`IDPay`) REFERENCES `paytype` (`IDPay`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saleinvoice`
--

LOCK TABLES `saleinvoice` WRITE;
/*!40000 ALTER TABLE `saleinvoice` DISABLE KEYS */;
INSERT INTO `saleinvoice` VALUES (7,1,5,1,1,'2012-12-02 00:00:00',69000,69000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2012-12-02 00:00:00',_binary ''),(8,4,5,1,1,'2013-01-02 00:00:00',32200,32200,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-01-02 00:00:00',_binary ''),(9,2,6,2,2,'2012-07-28 00:00:00',56350,22540,5635,'2013-01-28 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-28 00:00:00',_binary '\0');
/*!40000 ALTER TABLE `saleinvoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `supplier` (
  `IDSup` int(11) NOT NULL AUTO_INCREMENT,
  `SuppName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`IDSup`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Supplier 1','I9 Islamabad','048842689','Supplier1@gmail.com'),(2,'Supplier 2','H8 Islamabad','0451847515','Supplier2@gmail.com'),(3,'Supplier 3','F9  Islamabad','08588426','Supplier3@gmail.com');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usersystem`
--

DROP TABLE IF EXISTS `usersystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usersystem` (
  `IDUser` int(11) NOT NULL AUTO_INCREMENT,
  `IDRole` int(11) DEFAULT NULL,
  `Username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Fullname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Gender` bit(1) DEFAULT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`IDUser`),
  KEY `IDRole` (`IDRole`),
  CONSTRAINT `usersystem_ibfk_1` FOREIGN KEY (`IDRole`) REFERENCES `role` (`IDRole`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersystem`
--

LOCK TABLES `usersystem` WRITE;
/*!40000 ALTER TABLE `usersystem` DISABLE KEYS */;
INSERT INTO `usersystem` VALUES (1,1,'admin','admin','Tanzil Hussain',_binary '','Iqra Uni','03123456','tanzil.hussain@gmail.com'),(2,2,'manager','manager','Ali Hamza',_binary '','Iqra Uni','0987654321','ali.hamza@gmail.com'),(3,2,'alihamza','alihamza','Ali  Hamza',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `usersystem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-10 19:43:27
