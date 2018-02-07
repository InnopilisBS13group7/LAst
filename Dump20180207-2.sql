-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: itpdb
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `active_users`
--

DROP TABLE IF EXISTS `active_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `active_users` (
  `cookieId` varchar(18) NOT NULL,
  `userId` int(11) NOT NULL,
  `ip` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `active_users`
--

LOCK TABLES `active_users` WRITE;
/*!40000 ALTER TABLE `active_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `active_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `author` varchar(200) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `amount` int(11) DEFAULT '0',
  `description` varchar(1000) DEFAULT NULL,
  `teg` varchar(200) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `documents_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (1,'ttt','aaa','sss',2,'ssssssssssssssssssssssssssssssssssssss','tyutu','book'),(2,'Title','Author','bestseller',8,'Short description','#book','book');
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `itemId` int(11) DEFAULT NULL,
  `startTime` mediumtext NOT NULL,
  `finishTime` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (11,13,2,'1518012737837','1519222337837'),(12,13,1,'1518013004491','1519741004491');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(150) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `cookieId` varchar(20) DEFAULT '0',
  `status` varchar(10) DEFAULT 'disabled',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'l.lol@innopolis.ru','$2a$10$wqBSqtAOHDeFSNmF81JlkODAYyRapR8qP1sbYbMud/u8d/2t3Fe.K','name','surname','0','disabled'),(2,'l@innopolis.ru','$2a$10$OBBMrk1d1s9GOsn9RzCgluAR33xSF1T4nEIh0r55UNkNnOEduQvyS','alia','alien','cookieId','disabled'),(3,'s@innopolis.ru','$2a$10$Vo9Fullou7Xp2QbJgSKuCuAvMOhSWNFyqOoBj8XB6iuEIYXh3DQE6','syoma','kiss','3445253590233','disabled'),(4,'q@innopolis.ru','$2a$10$Y7La7ZX6s2irxzozDfJCTuzFMY02JRruwSNYkyQKyqsYStMYxt9FW','qq','qq','2003914364682','disabled'),(5,'qq@innopolis.ru','$2a$10$hbW3nlHisDZhAq5ubKLgNuAdRZzLGxZxOx1Rph8g/MlnR7LhgsNTe','qq','qq','2255845232900','disabled'),(6,'d@innopolis.ru','$2a$10$CIiM8.S/JUfWgX1AzS/Bf.72.Xm6Lp7k7H8rH/.CgwlzLCS0tGhw2','dd','dd','0','disabled'),(7,'i.pro@innopolis.ru','$2a$10$hepDomRdnBqeGklGQJTDRO.r0Oud94bBrGFyX73GKWkL8d9cvGuVC','Ilia','Pro','0','disabled'),(8,'g@innopolis.ru','$2a$10$VhGsJh4tIiCECHNQZ.8rzu/uW7wbY/7Sj2oubi9R5h69P94AlKNLy','gg','gg','0','disabled'),(9,'n@innopolis.ru','$2a$10$FEaCcTkyYnIRnI1.746vlOU5jH840lVnhi5xgh2BJcNnG0AJ093/O','nn','nn','0','disabled'),(10,'b@innopolis.ru','$2a$10$GuyE8Ro0o8J2sFRR9YTEiuLuLE4Ot3W9ulG9yk6MRqIJ510SvglWG','bb','bb','0','disabled'),(11,'u@innopolis.ru','$2a$10$wYk.9hmZwJnNyVZXYmLNq.TLyHfS7UpHF6jgvdPvwhafckFxbEdu6','uu','uu','0','disabled'),(12,'email','$2a$10$QOjrsT4awsFipD5PuWmJfeL/3Wx0mdkPxXWMxUwmxGOc7OtRZcs5e','name','surname','0','disabled'),(13,'sk@innopolis.ru','$2a$10$/cZeb5NmoZDEKdIt.dgxLuurMZiaNteau/aqhpqDEAY18h.ncnrt2','Semyon','Kiselyov','40641013314263','disabled'),(14,'Femail','$2a$10$KOGsvinKNnXOlQ6rZhliTe.SpwFwzsF.J4N9TEIkmO4vkt/QZcEfS','Fname','Fsurname','0','disabled'),(15,'Semail','$2a$10$emdHhts0Jm.Psoc/QRQ57eahN6VxmoLP6qz.pcjg7jBCGH/zjOUMa','Sname','Ssurname','0','disabled');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-07 17:31:56
