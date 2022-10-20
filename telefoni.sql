/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`telefoni` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `telefoni`;



DROP TABLE IF EXISTS `Prodavac`;

CREATE TABLE `Prodavac` (
  `ProdavacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ProdavacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Prodavac` VALUES 
(1,'Tamara','Djordjevic','tamara','tamara123'),
(2,'Marina','Kovacevic','marina','marina123');



DROP TABLE IF EXISTS `Kupac`;

CREATE TABLE `Kupac` (
  `KupacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `PIB` VARCHAR(10) NOT NULL,
  `NazivKupca` VARCHAR(50) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`KupacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Kupac` VALUES 
(1,'27382711', 'Telekoplus', 'telekoplus@gmail.com', '0631231234'),
(2,'18237266', 'Technomax', 'technomax@gmail.com', '0654645434'),
(3,'72812893', 'Telenor', 'telenor@gmail.com', '0641235153');



DROP TABLE IF EXISTS `Proizvodjac`;

CREATE TABLE `Proizvodjac` (
  `ProizvodjacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivProizvodjaca` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ProizvodjacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Proizvodjac` VALUES 
(1,'Samsung'),
(2,'Apple'),
(3,'Xiaomi'),
(4,'Huawei'),
(5,'HTC');


DROP TABLE IF EXISTS `Telefon`;

CREATE TABLE `Telefon` (
  `TelefonID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivTelefona` VARCHAR(50) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `Specifikacije` VARCHAR(300) NOT NULL,
  `ProizvodjacID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TelefonID`),
  CONSTRAINT `fk_proizvodjac_id` FOREIGN KEY (`ProizvodjacID`) REFERENCES `Proizvodjac` (`ProizvodjacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Telefon` VALUES 
(1,'Galaxy S22 Ultra', 1000.00, '', 1),
(2,'Galaxy S22 Plus', 900.00, '', 1),
(3,'iPhone 14 Pro Max', 1200.00, '', 2),
(4,'iPhone 14 Plus', 1000.00, '', 2),
(5,'12s Ultra', 800.00, '', 3),
(6,'Redmi Note 10 Pro', 750.00, '', 3),
(7,'P40 Pro', 800.00, '', 4),
(8,'Mate 50 Pro', 850.00, '', 4),
(9,'HTC One M8', 1000.00, '', 5),
(10,'HTC One M9', 1000.00, '', 5);





DROP TABLE IF EXISTS `Porudzbina`;

CREATE TABLE `Porudzbina` (
  `PorudzbinaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVremePorucivanja` DATETIME NOT NULL,
  `DatumDostave` DATE NOT NULL,
  `UkupnaCena` DECIMAL(10,2) NOT NULL,
  `KupacID` BIGINT(10) UNSIGNED NOT NULL,
  `ProdavacID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PorudzbinaID`),
  CONSTRAINT `fk_kupac_id` FOREIGN KEY (`KupacID`) REFERENCES `Kupac` (`KupacID`),
  CONSTRAINT `fk_prodavac_id` FOREIGN KEY (`ProdavacID`) REFERENCES `Prodavac` (`ProdavacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT  INTO `Porudzbina` VALUES 
(1,'2022-09-01 10:00:54','2022-09-04',2200.00,1,1);


DROP TABLE IF EXISTS `StavkaPorudzbine`;

CREATE TABLE `StavkaPorudzbine` (
  `PorudzbinaID` BIGINT(10) UNSIGNED NOT NULL,
  `RbStavke` INT(7) NOT NULL,
  `KolicinaStavke` INT(7) NOT NULL,
  `CenaStavke` DECIMAL(10,2) NOT NULL,
  `TelefonID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PorudzbinaID`,`RbStavke`),
  CONSTRAINT `fk_porudzbina_id` FOREIGN KEY (`PorudzbinaID`) REFERENCES `Porudzbina` (`PorudzbinaID`) ON DELETE CASCADE,
  CONSTRAINT `fk_telefon_id` FOREIGN KEY (`TelefonID`) REFERENCES `Telefon` (`TelefonID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `StavkaPorudzbine` VALUES 
(1,1,1,1000,1),
(1,2,1,1200,2);




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
