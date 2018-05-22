CREATE DATABASE  IF NOT EXISTS `project` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `project`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: project
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `level` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (1,'Beginner'),(2,'Intermediate'),(3,'Advanced');
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'User');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theme` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `translation`
--

DROP TABLE IF EXISTS `translation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `translation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `translation` varchar(256) DEFAULT NULL,
  `theme_ID` int(11) DEFAULT NULL,
  `word_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKkobtf2ub1uhh53rtmi9q1h6h2` (`word_ID`),
  KEY `FKpbqrwnt1vi39x54onfpg7bquf` (`theme_ID`),
  CONSTRAINT `FKkobtf2ub1uhh53rtmi9q1h6h2` FOREIGN KEY (`word_ID`) REFERENCES `word` (`ID`),
  CONSTRAINT `FKpbqrwnt1vi39x54onfpg7bquf` FOREIGN KEY (`theme_ID`) REFERENCES `theme` (`ID`),
  CONSTRAINT `translation_theme_ID_fk` FOREIGN KEY (`theme_ID`) REFERENCES `theme` (`ID`),
  CONSTRAINT `translation_word_ID_fk` FOREIGN KEY (`word_ID`) REFERENCES `word` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `translation`
--

LOCK TABLES `translation` WRITE;
/*!40000 ALTER TABLE `translation` DISABLE KEYS */;
/*!40000 ALTER TABLE `translation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role_ID` int(11) DEFAULT '2',
  `level_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_ID`),
  KEY `FK4do7xomli51oxpc6b5j97jap0` (`level_ID`),
  CONSTRAINT `FK4do7xomli51oxpc6b5j97jap0` FOREIGN KEY (`level_ID`) REFERENCES `level` (`ID`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`role_ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `user_level_ID_fk` FOREIGN KEY (`level_ID`) REFERENCES `level` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','$2a$10$SG3asKiwi50ujrHG0Eot1eKjTLbBUfpZl0hDVAIAAKsACv7/kYvB.','test@mail.ru',NULL,NULL),(2,'peppa','1234567','alpldpdw',1,NULL),(7,'user','$2a$10$T0I2I3JP1xeA6vLjnzVdh.0rR1VeaQ6BJHhwdQFMzRonV2Gd5eyP6','user@mail.com',NULL,NULL),(8,'user1','$2a$10$WYz9hVWTDP1XO/dlj6D2rOITre0x7x9EE3.IJ/1278ijVyxBdj996','user@mail.org',2,NULL),(9,'kekis','$2a$10$cSk4pkVsRDxBFFrZvPk08uUcJacPJeqOPNg6GZF7bRP.pqz.Vg2tW','kek@pep.ru',2,NULL),(10,'ivanov','$2a$10$K4gphOgLMf6XwKL5EQsoFuGBRSunnKN.hg1VXdJeG/GnyhPS4okdu','ivan@mail.com',2,NULL),(11,'test1','$2a$10$BaqoNWiyvDM0VXXiJavWL.J6y/FBlRwIB7tPwHsiS2VC3Na9zWqYu','tets',2,NULL),(12,'genius','$2a$10$pH6.exdGau/s6vpaJGE1wO56RqWpOhg4E/t.5SpzVC/gktV1b07vO','test@test.com',2,1),(13,'anotheruser','$2a$10$gDWjmmq6GtdHCMy8/XuYeuQZiiAUcAVnfDR1N/5j0pTNTZmPKizFC','test9@mail.ru',2,NULL),(14,'proverka','$2a$10$wj8oODqu4VEJCrltF2bRsOyKHsaK0q0c/GEOYB3JzJB4666Khdbhe','proverka@yandex.ru',2,1),(15,'proverka1','$2a$10$DkxdU3xAvnFaPDfM4u301.td.po9t/vPaK9mZVgyA3O7Ber5jSHH.','proverka1@gmail.com',2,NULL),(16,'proverka2','$2a$10$vC5D1c8uuPsyV1kDs7ddAuu4XhzVBPQd0Ba.ynKVhRflAUu83aFe6','check@mail.ru',2,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word`
--

DROP TABLE IF EXISTS `word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `word` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(256) DEFAULT NULL,
  `user_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKq6vj4o70ro4p1mdi1kiuv98iv` (`user_ID`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`user_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FKq6vj4o70ro4p1mdi1kiuv98iv` FOREIGN KEY (`user_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=879 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word`
--

LOCK TABLES `word` WRITE;
/*!40000 ALTER TABLE `word` DISABLE KEYS */;
INSERT INTO `word` VALUES (1,'crickets',11),(2,'about',11),(3,'eggs',11),(4,'cricket',11),(5,'female',11),(6,'house',11),(7,'between',11),(8,'mates',11),(9,'male',11),(10,'weeks',11),(11,'have',11),(12,'temperature',11),(13,'after',11),(14,'wings',11),(15,'used',11),(16,'which',11),(17,'their',11),(18,'rate',11),(19,'with',11),(20,'habitat',11),(21,'called',11),(22,'centimeters',11),(23,'told',11),(24,'year',11),(25,'reach',11),(26,'areas',11),(27,'chirps',11),(28,'anywhere',11),(29,'supply',11),(30,'when',11),(31,'thorax',11),(32,'else',11),(33,'legs',11),(34,'usually',11),(35,'presence',11),(36,'soft',11),(37,'surface',11),(38,'maturity',11),(39,'birth',11),(40,'stirdulation',11),(41,'males',11),(42,'light',11),(43,'determined',11),(44,'twelve',11),(45,'exact',11),(46,'restaurants',11),(47,'adult',11),(48,'sexual',11),(49,'flight',11),(50,'suburbs',11),(51,'chirp',11),(52,'extension',11),(53,'habitats',11),(54,'alive',11),(55,'ducts',11),(56,'another',11),(57,'soil',11),(58,'long',11),(59,'eight',11),(60,'woodlands',11),(61,'interestingly',11),(62,'found',11),(63,'variety',11),(64,'indoors',11),(65,'noise',11),(66,'together',11),(67,'siding',11),(68,'black',11),(69,'ovipositor',11),(70,'many',11),(71,'produce',11),(72,'food',11),(73,'stay',11),(74,'warmer',11),(75,'actually',11),(76,'chirping',11),(77,'sometimes',11),(78,'tell',11),(79,'body',11),(80,'pairs',11),(81,'that',11),(82,'faster',11),(83,'find',11),(84,'only',11),(85,'conspicuous',11),(86,'from',11),(87,'those',11),(88,'including',11),(89,'months',11),(90,'markings',11),(91,'timing',11),(92,'hatch',11),(93,'this',11),(94,'brown',11),(95,'colder',11),(96,'warm',11),(97,'scientists',11),(98,'outside',11),(99,'buildings',11),(100,'attached',11),(101,'ground',11),(102,'jumping',11),(103,'abdomen',11),(104,'depositing',11),(105,'attract',11),(106,'description',11),(107,'back',11),(108,'head',11),(109,'process',11),(110,'will',11),(111,'length',11),(112,'enough',11),(113,'technically',11),(114,'pair',11),(115,'manage',11),(116,'outdoor',11),(117,'chirpings',11),(118,'urban',11),(119,'although',11),(120,'round',11),(121,'there',11),(122,'happens',11),(123,'reproduction',11),(124,'rubbing',11),(125,'referred',11),(126,'crickets',11),(127,'about',11),(128,'eggs',11),(129,'cricket',11),(130,'female',11),(131,'house',11),(132,'between',11),(133,'mates',11),(134,'male',11),(135,'weeks',11),(136,'have',11),(137,'temperature',11),(138,'after',11),(139,'wings',11),(140,'used',11),(141,'which',11),(142,'their',11),(143,'rate',11),(144,'with',11),(145,'habitat',11),(146,'called',11),(147,'centimeters',11),(148,'told',11),(149,'year',11),(150,'reach',11),(151,'areas',11),(152,'chirps',11),(153,'anywhere',11),(154,'supply',11),(155,'when',11),(156,'thorax',11),(157,'else',11),(158,'legs',11),(159,'usually',11),(160,'presence',11),(161,'soft',11),(162,'surface',11),(163,'maturity',11),(164,'birth',11),(165,'stirdulation',11),(166,'males',11),(167,'light',11),(168,'determined',11),(169,'twelve',11),(170,'exact',11),(171,'restaurants',11),(172,'adult',11),(173,'sexual',11),(174,'flight',11),(175,'suburbs',11),(176,'chirp',11),(177,'extension',11),(178,'habitats',11),(179,'alive',11),(180,'ducts',11),(181,'another',11),(182,'soil',11),(183,'long',11),(184,'eight',11),(185,'woodlands',11),(186,'interestingly',11),(187,'found',11),(188,'variety',11),(189,'indoors',11),(190,'noise',11),(191,'together',11),(192,'siding',11),(193,'black',11),(194,'ovipositor',11),(195,'many',11),(196,'produce',11),(197,'food',11),(198,'stay',11),(199,'warmer',11),(200,'actually',11),(201,'chirping',11),(202,'sometimes',11),(203,'tell',11),(204,'body',11),(205,'pairs',11),(206,'that',11),(207,'faster',11),(208,'find',11),(209,'only',11),(210,'conspicuous',11),(211,'from',11),(212,'those',11),(213,'including',11),(214,'months',11),(215,'markings',11),(216,'timing',11),(217,'hatch',11),(218,'this',11),(219,'brown',11),(220,'colder',11),(221,'warm',11),(222,'scientists',11),(223,'outside',11),(224,'buildings',11),(225,'attached',11),(226,'ground',11),(227,'jumping',11),(228,'abdomen',11),(229,'depositing',11),(230,'attract',11),(231,'description',11),(232,'back',11),(233,'head',11),(234,'process',11),(235,'will',11),(236,'length',11),(237,'enough',11),(238,'technically',11),(239,'pair',11),(240,'manage',11),(241,'outdoor',11),(242,'chirpings',11),(243,'urban',11),(244,'although',11),(245,'round',11),(246,'there',11),(247,'happens',11),(248,'reproduction',11),(249,'rubbing',11),(250,'referred',11),(251,'crickets',11),(252,'about',11),(253,'eggs',11),(254,'cricket',11),(255,'female',11),(256,'house',11),(257,'between',11),(258,'mates',11),(259,'male',11),(260,'weeks',11),(261,'have',11),(262,'temperature',11),(263,'after',11),(264,'wings',11),(265,'used',11),(266,'which',11),(267,'their',11),(268,'rate',11),(269,'with',11),(270,'habitat',11),(271,'called',11),(272,'centimeters',11),(273,'told',11),(274,'year',11),(275,'reach',11),(276,'areas',11),(277,'chirps',11),(278,'anywhere',11),(279,'supply',11),(280,'when',11),(281,'thorax',11),(282,'else',11),(283,'legs',11),(284,'usually',11),(285,'presence',11),(286,'soft',11),(287,'surface',11),(288,'maturity',11),(289,'birth',11),(290,'stirdulation',11),(291,'males',11),(292,'light',11),(293,'determined',11),(294,'twelve',11),(295,'exact',11),(296,'restaurants',11),(297,'adult',11),(298,'sexual',11),(299,'flight',11),(300,'suburbs',11),(301,'chirp',11),(302,'extension',11),(303,'habitats',11),(304,'alive',11),(305,'ducts',11),(306,'another',11),(307,'soil',11),(308,'long',11),(309,'eight',11),(310,'woodlands',11),(311,'interestingly',11),(312,'found',11),(313,'variety',11),(314,'indoors',11),(315,'noise',11),(316,'together',11),(317,'siding',11),(318,'black',11),(319,'ovipositor',11),(320,'many',11),(321,'produce',11),(322,'food',11),(323,'stay',11),(324,'warmer',11),(325,'actually',11),(326,'chirping',11),(327,'sometimes',11),(328,'tell',11),(329,'body',11),(330,'pairs',11),(331,'that',11),(332,'faster',11),(333,'find',11),(334,'only',11),(335,'conspicuous',11),(336,'from',11),(337,'those',11),(338,'including',11),(339,'months',11),(340,'markings',11),(341,'timing',11),(342,'hatch',11),(343,'this',11),(344,'brown',11),(345,'colder',11),(346,'warm',11),(347,'scientists',11),(348,'outside',11),(349,'buildings',11),(350,'attached',11),(351,'ground',11),(352,'jumping',11),(353,'abdomen',11),(354,'depositing',11),(355,'attract',11),(356,'description',11),(357,'back',11),(358,'head',11),(359,'process',11),(360,'will',11),(361,'length',11),(362,'enough',11),(363,'technically',11),(364,'pair',11),(365,'manage',11),(366,'outdoor',11),(367,'chirpings',11),(368,'urban',11),(369,'although',11),(370,'round',11),(371,'there',11),(372,'happens',11),(373,'reproduction',11),(374,'rubbing',11),(375,'referred',11),(376,'crickets',11),(377,'about',11),(378,'eggs',11),(379,'cricket',11),(380,'female',11),(381,'house',11),(382,'between',11),(383,'mates',11),(384,'male',11),(385,'weeks',11),(386,'have',11),(387,'temperature',11),(388,'after',11),(389,'wings',11),(390,'used',11),(391,'which',11),(392,'their',11),(393,'rate',11),(394,'with',11),(395,'habitat',11),(396,'called',11),(397,'centimeters',11),(398,'told',11),(399,'year',11),(400,'reach',11),(401,'areas',11),(402,'chirps',11),(403,'anywhere',11),(404,'supply',11),(405,'when',11),(406,'thorax',11),(407,'else',11),(408,'legs',11),(409,'usually',11),(410,'presence',11),(411,'soft',11),(412,'surface',11),(413,'maturity',11),(414,'birth',11),(415,'stirdulation',11),(416,'males',11),(417,'light',11),(418,'determined',11),(419,'twelve',11),(420,'exact',11),(421,'restaurants',11),(422,'adult',11),(423,'sexual',11),(424,'flight',11),(425,'suburbs',11),(426,'chirp',11),(427,'extension',11),(428,'habitats',11),(429,'alive',11),(430,'ducts',11),(431,'another',11),(432,'soil',11),(433,'long',11),(434,'eight',11),(435,'woodlands',11),(436,'interestingly',11),(437,'found',11),(438,'variety',11),(439,'indoors',11),(440,'noise',11),(441,'together',11),(442,'siding',11),(443,'black',11),(444,'ovipositor',11),(445,'many',11),(446,'produce',11),(447,'food',11),(448,'stay',11),(449,'warmer',11),(450,'actually',11),(451,'chirping',11),(452,'sometimes',11),(453,'tell',11),(454,'body',11),(455,'pairs',11),(456,'that',11),(457,'faster',11),(458,'find',11),(459,'only',11),(460,'conspicuous',11),(461,'from',11),(462,'those',11),(463,'including',11),(464,'months',11),(465,'markings',11),(466,'timing',11),(467,'hatch',11),(468,'this',11),(469,'brown',11),(470,'colder',11),(471,'warm',11),(472,'scientists',11),(473,'outside',11),(474,'buildings',11),(475,'attached',11),(476,'ground',11),(477,'jumping',11),(478,'abdomen',11),(479,'depositing',11),(480,'attract',11),(481,'description',11),(482,'back',11),(483,'head',11),(484,'process',11),(485,'will',11),(486,'length',11),(487,'enough',11),(488,'technically',11),(489,'pair',11),(490,'manage',11),(491,'outdoor',11),(492,'chirpings',11),(493,'urban',11),(494,'although',11),(495,'round',11),(496,'there',11),(497,'happens',11),(498,'reproduction',11),(499,'rubbing',11),(500,'referred',11),(501,'crickets',11),(502,'about',11),(503,'eggs',11),(504,'cricket',11),(505,'female',11),(506,'house',11),(507,'between',11),(508,'mates',11),(509,'male',11),(510,'weeks',11),(511,'have',11),(512,'temperature',11),(513,'after',11),(514,'wings',11),(515,'used',11),(516,'which',11),(517,'their',11),(518,'rate',11),(519,'with',11),(520,'habitat',11),(521,'called',11),(522,'centimeters',11),(523,'told',11),(524,'year',11),(525,'reach',11),(526,'areas',11),(527,'chirps',11),(528,'anywhere',11),(529,'supply',11),(530,'when',11),(531,'thorax',11),(532,'else',11),(533,'legs',11),(534,'usually',11),(535,'presence',11),(536,'soft',11),(537,'surface',11),(538,'maturity',11),(539,'birth',11),(540,'stirdulation',11),(541,'males',11),(542,'light',11),(543,'determined',11),(544,'twelve',11),(545,'exact',11),(546,'restaurants',11),(547,'adult',11),(548,'sexual',11),(549,'flight',11),(550,'suburbs',11),(551,'chirp',11),(552,'extension',11),(553,'habitats',11),(554,'alive',11),(555,'ducts',11),(556,'another',11),(557,'soil',11),(558,'long',11),(559,'eight',11),(560,'woodlands',11),(561,'interestingly',11),(562,'found',11),(563,'variety',11),(564,'indoors',11),(565,'noise',11),(566,'together',11),(567,'siding',11),(568,'black',11),(569,'ovipositor',11),(570,'many',11),(571,'produce',11),(572,'food',11),(573,'stay',11),(574,'warmer',11),(575,'actually',11),(576,'chirping',11),(577,'sometimes',11),(578,'tell',11),(579,'body',11),(580,'pairs',11),(581,'that',11),(582,'faster',11),(583,'find',11),(584,'only',11),(585,'conspicuous',11),(586,'from',11),(587,'those',11),(588,'including',11),(589,'months',11),(590,'markings',11),(591,'timing',11),(592,'hatch',11),(593,'this',11),(594,'brown',11),(595,'colder',11),(596,'warm',11),(597,'scientists',11),(598,'outside',11),(599,'buildings',11),(600,'attached',11),(601,'ground',11),(602,'jumping',11),(603,'abdomen',11),(604,'depositing',11),(605,'attract',11),(606,'description',11),(607,'back',11),(608,'head',11),(609,'process',11),(610,'will',11),(611,'length',11),(612,'enough',11),(613,'technically',11),(614,'pair',11),(615,'manage',11),(616,'outdoor',11),(617,'chirpings',11),(618,'urban',11),(619,'although',11),(620,'round',11),(621,'there',11),(622,'happens',11),(623,'reproduction',11),(624,'rubbing',11),(625,'referred',11),(626,'crickets',11),(627,'about',11),(628,'eggs',11),(629,'cricket',11),(630,'female',11),(631,'house',11),(632,'between',11),(633,'mates',11),(634,'male',11),(635,'weeks',11),(636,'have',11),(637,'temperature',11),(638,'after',11),(639,'wings',11),(640,'used',11),(641,'which',11),(642,'their',11),(643,'rate',11),(644,'with',11),(645,'habitat',11),(646,'called',11),(647,'centimeters',11),(648,'told',11),(649,'year',11),(650,'reach',11),(651,'areas',11),(652,'chirps',11),(653,'anywhere',11),(654,'supply',11),(655,'when',11),(656,'thorax',11),(657,'else',11),(658,'legs',11),(659,'usually',11),(660,'presence',11),(661,'soft',11),(662,'surface',11),(663,'maturity',11),(664,'birth',11),(665,'stirdulation',11),(666,'males',11),(667,'light',11),(668,'determined',11),(669,'twelve',11),(670,'exact',11),(671,'restaurants',11),(672,'adult',11),(673,'sexual',11),(674,'flight',11),(675,'suburbs',11),(676,'chirp',11),(677,'extension',11),(678,'habitats',11),(679,'alive',11),(680,'ducts',11),(681,'another',11),(682,'soil',11),(683,'long',11),(684,'eight',11),(685,'woodlands',11),(686,'interestingly',11),(687,'found',11),(688,'variety',11),(689,'indoors',11),(690,'noise',11),(691,'together',11),(692,'siding',11),(693,'black',11),(694,'ovipositor',11),(695,'many',11),(696,'produce',11),(697,'food',11),(698,'stay',11),(699,'warmer',11),(700,'actually',11),(701,'chirping',11),(702,'sometimes',11),(703,'tell',11),(704,'body',11),(705,'pairs',11),(706,'that',11),(707,'faster',11),(708,'find',11),(709,'only',11),(710,'conspicuous',11),(711,'from',11),(712,'those',11),(713,'including',11),(714,'months',11),(715,'markings',11),(716,'timing',11),(717,'hatch',11),(718,'this',11),(719,'brown',11),(720,'colder',11),(721,'warm',11),(722,'scientists',11),(723,'outside',11),(724,'buildings',11),(725,'attached',11),(726,'ground',11),(727,'jumping',11),(728,'abdomen',11),(729,'depositing',11),(730,'attract',11),(731,'description',11),(732,'back',11),(733,'head',11),(734,'process',11),(735,'will',11),(736,'length',11),(737,'enough',11),(738,'technically',11),(739,'pair',11),(740,'manage',11),(741,'outdoor',11),(742,'chirpings',11),(743,'urban',11),(744,'although',11),(745,'round',11),(746,'there',11),(747,'happens',11),(748,'reproduction',11),(749,'rubbing',11),(750,'referred',11),(751,'clock',12),(752,'past',12),(753,'early',12),(754,'half',12),(755,'usually',12),(756,'then',12),(757,'home',12),(758,'studio',12),(759,'very',12),(760,'nine',12),(761,'leave',12),(762,'because',12),(763,'starts',12),(764,'five',12),(765,'always',12),(766,'work',12),(767,'morning',12),(768,'some',12),(769,'monday',12),(770,'evening',12),(771,'weekends',12),(772,'about',12),(773,'seven',12),(774,'husband',12),(775,'three',12),(776,'good',12),(777,'that',12),(778,'four',12),(779,'from',12),(780,'asleep',12),(781,'cinema',12),(782,'housework',12),(783,'read',12),(784,'minutes',12),(785,'often',12),(786,'friends',12),(787,'twelve',12),(788,'helps',12),(789,'arrives',12),(790,'newspaper',12),(791,'shower',12),(792,'britain',12),(793,'woman',12),(794,'house',12),(795,'eight',12),(796,'have',12),(797,'working',12),(798,'friday',12),(799,'finishes',12),(800,'after',12),(801,'ironing',12),(802,'cook',12),(803,'television',12),(804,'again',12),(805,'start',12),(806,'gets',12),(807,'dinner',12),(808,'stay',12),(809,'afternoon',12),(810,'with',12),(811,'watch',12),(812,'coffee',12),(813,'until',12),(814,'quarter',12),(815,'clock',12),(816,'past',12),(817,'early',12),(818,'half',12),(819,'usually',12),(820,'then',12),(821,'home',12),(822,'studio',12),(823,'very',12),(824,'nine',12),(825,'leave',12),(826,'because',12),(827,'starts',12),(828,'five',12),(829,'always',12),(830,'work',12),(831,'morning',12),(832,'some',12),(833,'monday',12),(834,'evening',12),(835,'weekends',12),(836,'about',12),(837,'seven',12),(838,'husband',12),(839,'three',12),(840,'good',12),(841,'that',12),(842,'four',12),(843,'from',12),(844,'asleep',12),(845,'cinema',12),(846,'housework',12),(847,'read',12),(848,'minutes',12),(849,'often',12),(850,'friends',12),(851,'twelve',12),(852,'helps',12),(853,'arrives',12),(854,'newspaper',12),(855,'shower',12),(856,'britain',12),(857,'woman',12),(858,'house',12),(859,'eight',12),(860,'have',12),(861,'working',12),(862,'friday',12),(863,'finishes',12),(864,'after',12),(865,'ironing',12),(866,'cook',12),(867,'television',12),(868,'again',12),(869,'start',12),(870,'gets',12),(871,'dinner',12),(872,'stay',12),(873,'afternoon',12),(874,'with',12),(875,'watch',12),(876,'coffee',12),(877,'until',12),(878,'quarter',12);
/*!40000 ALTER TABLE `word` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-11 13:53:07
