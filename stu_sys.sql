-- MySQL dump 10.13  Distrib 5.6.35, for Win64 (x86_64)
--
-- Host: localhost    Database: stu_sys
-- ------------------------------------------------------
-- Server version	5.6.35-log

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `cid` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `credit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('A0001','Java程序设计',4),('B0001','大学英语',2),('C0001','高等数学',5),('A0002','游戏数学与物理',2),('C0002','线性代数',2),('D0001','军事理论',1),('A0003','三维建模基础',3);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grades` (
  `sid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `java` double DEFAULT NULL,
  `english` double DEFAULT NULL,
  `mathematics` double DEFAULT NULL,
  `game` double DEFAULT NULL,
  `algebra` double DEFAULT NULL,
  `military` double DEFAULT NULL,
  `modeling` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES ('150806101','范雄钦',97,90,88,83,96,91,85),('150806104','马睿',47,47,8,24,43,95,0),('150806105','潘雅建',84,87,64,70,76,95,89),('150806118','陈雯颖',84,73,60,67,77,84,79),('150806121','冯靖涵',90,80,65,86,81,97,81),('150806122','何佳妮',94,83,68,84,73,85,91),('150806125','康婕',92,78,62,71,86,86,74),('150806126','李锐',92,75,69,65,73,91,88),('150806130','刘芮菡',92,80,82,77,80,86,84),('150806136','吴涛',99,82,97,91,97,95,86),('150806137','许诺',97,81,87,90,92,88,86),('150806138','许晓妍',86,75,72,79,80,83,86),('150806139','俞婕',95,86,77,86,94,91,93),('150806140','岳艺芯',0,0,0,0,0,0,0),('150806141','张甜',78,82,73,60,83,73,78),('150806142','张斓',83,76,60,61,70,86,80),('150806145','周瑶瑶',94,85,73,72,79,86,93),('150806205','鹿成',81,77,63,63,73,87,87),('150806206','毛怡明',96,85,77,80,87,84,88),('150806215','安琪',89,88,71,69,86,91,91);
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `sid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('150806140','岳艺芯','女',18),('150806136','吴涛','女',18),('150806101','范雄钦','男',18),('150806137','许诺','女',18),('150806130','刘芮菡','女',18),('150806206','毛怡明','男',18),('150806139','俞婕','女',18),('150806141','张甜','女',18),('150806145','周瑶瑶','女',18),('150806138','许晓妍','女',18),('150806215','安琪','女',18),('150806126','李锐','女',18),('150806122','何佳妮','女',18),('150806121','冯靖涵','女',18),('150806105','潘雅建','男',18),('150806205','鹿成','男',18),('150806125','康婕','女',18),('150806142','张斓','女',18),('150806118','陈雯颖','女',18),('150806104','马睿','男',18);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-29 21:58:25
