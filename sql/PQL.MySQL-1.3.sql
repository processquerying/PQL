CREATE DATABASE  IF NOT EXISTS `pql` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pql`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pql
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `jbpt_labels`
--

DROP TABLE IF EXISTS `jbpt_labels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jbpt_labels` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `label` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `label` (`label`(5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jbpt_labels`
--

LOCK TABLES `jbpt_labels` WRITE;
/*!40000 ALTER TABLE `jbpt_labels` DISABLE KEYS */;
/*!40000 ALTER TABLE `jbpt_labels` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `jbpt_labels_before_del_tr` BEFORE DELETE ON `jbpt_labels`
  FOR EACH ROW
BEGIN
  DELETE FROM pql_tasks_sim WHERE pql_tasks_sim.label_id = OLD.id;
  DELETE FROM pql_tasks WHERE pql_tasks.label_id = OLD.id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `jbpt_petri_flow`
--

DROP TABLE IF EXISTS `jbpt_petri_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jbpt_petri_flow` (
  `source` int(11) unsigned NOT NULL,
  `target` int(11) unsigned NOT NULL,
  `name` text,
  `description` text,
  PRIMARY KEY (`source`,`target`),
  KEY `source` (`source`),
  KEY `target` (`target`),
  CONSTRAINT `jbpt_flow_fk_source` FOREIGN KEY (`source`) REFERENCES `jbpt_petri_nodes` (`id`),
  CONSTRAINT `jbpt_flow_fk_target` FOREIGN KEY (`target`) REFERENCES `jbpt_petri_nodes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jbpt_petri_flow`
--

LOCK TABLES `jbpt_petri_flow` WRITE;
/*!40000 ALTER TABLE `jbpt_petri_flow` DISABLE KEYS */;
/*!40000 ALTER TABLE `jbpt_petri_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jbpt_petri_markings`
--

DROP TABLE IF EXISTS `jbpt_petri_markings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jbpt_petri_markings` (
  `place_id` int(11) unsigned NOT NULL,
  `tokens` int(11) unsigned NOT NULL,
  PRIMARY KEY (`place_id`),
  CONSTRAINT `jbpt_petri_markings_fk` FOREIGN KEY (`place_id`) REFERENCES `jbpt_petri_nodes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jbpt_petri_markings`
--

LOCK TABLES `jbpt_petri_markings` WRITE;
/*!40000 ALTER TABLE `jbpt_petri_markings` DISABLE KEYS */;
/*!40000 ALTER TABLE `jbpt_petri_markings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jbpt_petri_nets`
--

DROP TABLE IF EXISTS `jbpt_petri_nets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jbpt_petri_nets` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(36) NOT NULL,
  `name` text,
  `description` text,
  `external_id` text,
  `pnml_content` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`(20)),
  UNIQUE KEY `external_id` (`external_id`(20))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jbpt_petri_nets`
--

LOCK TABLES `jbpt_petri_nets` WRITE;
/*!40000 ALTER TABLE `jbpt_petri_nets` DISABLE KEYS */;
/*!40000 ALTER TABLE `jbpt_petri_nets` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `jbpt_petri_nets_before_del_tr` BEFORE DELETE ON `jbpt_petri_nets`
  FOR EACH ROW
BEGIN
  INSERT IGNORE INTO `pql_index_status`
  VALUES (OLD.id,"DELETE",2,0,NULL,NULL,NULL);
  
  UPDATE `pql_index_status` SET `pql_index_status`.`status`=2
  WHERE `pql_index_status`.`net_id` = OLD.id;

  DELETE FROM jbpt_petri_nodes  WHERE jbpt_petri_nodes.net_id=OLD.id;
  
  DELETE FROM pql_can_occur     WHERE pql_can_occur.net_id=OLD.id;
  DELETE FROM pql_always_occurs WHERE pql_always_occurs.net_id=OLD.id;
  DELETE FROM pql_can_conflict  WHERE pql_can_conflict.net_id=OLD.id;
  DELETE FROM pql_can_cooccur   WHERE pql_can_cooccur.net_id=OLD.id;
  DELETE FROM pql_total_causal  WHERE pql_total_causal.net_id=OLD.id;
  DELETE FROM pql_total_concur  WHERE pql_total_concur.net_id=OLD.id;
  
  
  


  

  
  DELETE FROM pql_index_status WHERE pql_index_status.net_id=OLD.id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `jbpt_petri_nodes`
--

DROP TABLE IF EXISTS `jbpt_petri_nodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jbpt_petri_nodes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `net_id` int(11) unsigned NOT NULL,
  `uuid` varchar(50) NOT NULL,
  `name` text,
  `description` text,
  `label_id` int(10) unsigned DEFAULT NULL,
  `is_transition` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `label_id` (`label_id`),
  KEY `net_id` (`net_id`),
  KEY `is_transition` (`is_transition`),
  KEY `triple_index` (`net_id`,`is_transition`,`label_id`),
  CONSTRAINT `jbpt_nodes_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`),
  CONSTRAINT `jbpt_petri_nodes_fk` FOREIGN KEY (`label_id`) REFERENCES `jbpt_labels` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jbpt_petri_nodes`
--

LOCK TABLES `jbpt_petri_nodes` WRITE;
/*!40000 ALTER TABLE `jbpt_petri_nodes` DISABLE KEYS */;
/*!40000 ALTER TABLE `jbpt_petri_nodes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `jbpt_petri_nodes_before_del_tr` BEFORE DELETE ON `jbpt_petri_nodes`
  FOR EACH ROW
BEGIN
  DELETE FROM jbpt_petri_markings WHERE jbpt_petri_markings.place_id=OLD.id;
  DELETE FROM jbpt_petri_flow WHERE jbpt_petri_flow.source=OLD.id OR jbpt_petri_flow.target=OLD.id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `placeholder`
--

DROP TABLE IF EXISTS `placeholder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `placeholder` (
  `child_id` int(11) NOT NULL,
  `folder_name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `placeholder`
--

LOCK TABLES `placeholder` WRITE;
/*!40000 ALTER TABLE `placeholder` DISABLE KEYS */;
INSERT INTO `placeholder` VALUES (2,'A'),(5,'D');
/*!40000 ALTER TABLE `placeholder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_always_occurs`
--

DROP TABLE IF EXISTS `pql_always_occurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_always_occurs` (
  `net_id` int(11) unsigned NOT NULL,
  `task_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`net_id`,`task_id`),
  KEY `net_id` (`net_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `pql_always_occurs_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`),
  CONSTRAINT `pql_always_occurs_fk1` FOREIGN KEY (`task_id`) REFERENCES `pql_tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_always_occurs`
--

LOCK TABLES `pql_always_occurs` WRITE;
/*!40000 ALTER TABLE `pql_always_occurs` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_always_occurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_can_conflict`
--

DROP TABLE IF EXISTS `pql_can_conflict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_can_conflict` (
  `net_id` int(11) unsigned NOT NULL,
  `taskA_id` int(10) unsigned NOT NULL,
  `taskB_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`net_id`,`taskA_id`,`taskB_id`),
  KEY `net_id` (`net_id`),
  KEY `taskA_id` (`taskA_id`),
  KEY `taskB_id` (`taskB_id`),
  CONSTRAINT `pql_can_conflict_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`),
  CONSTRAINT `pql_can_conflict_fk1` FOREIGN KEY (`taskA_id`) REFERENCES `pql_tasks` (`id`),
  CONSTRAINT `pql_can_conflict_fk2` FOREIGN KEY (`taskB_id`) REFERENCES `pql_tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_can_conflict`
--

LOCK TABLES `pql_can_conflict` WRITE;
/*!40000 ALTER TABLE `pql_can_conflict` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_can_conflict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_can_cooccur`
--

DROP TABLE IF EXISTS `pql_can_cooccur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_can_cooccur` (
  `net_id` int(11) unsigned NOT NULL,
  `taskA_id` int(10) unsigned NOT NULL,
  `taskB_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`net_id`,`taskA_id`,`taskB_id`),
  KEY `net_id` (`net_id`),
  KEY `taskA_id` (`taskA_id`),
  KEY `pql_can_cooccur_fk2` (`taskB_id`),
  CONSTRAINT `pql_can_cooccur_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`),
  CONSTRAINT `pql_can_cooccur_fk1` FOREIGN KEY (`taskA_id`) REFERENCES `pql_tasks` (`id`),
  CONSTRAINT `pql_can_cooccur_fk2` FOREIGN KEY (`taskB_id`) REFERENCES `pql_tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_can_cooccur`
--

LOCK TABLES `pql_can_cooccur` WRITE;
/*!40000 ALTER TABLE `pql_can_cooccur` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_can_cooccur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_can_occur`
--

DROP TABLE IF EXISTS `pql_can_occur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_can_occur` (
  `net_id` int(11) unsigned NOT NULL,
  `task_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`net_id`,`task_id`),
  KEY `net_id` (`net_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `pql_can_occur_fk` FOREIGN KEY (`task_id`) REFERENCES `pql_tasks` (`id`),
  CONSTRAINT `pql_can_occurs_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_can_occur`
--

LOCK TABLES `pql_can_occur` WRITE;
/*!40000 ALTER TABLE `pql_can_occur` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_can_occur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_folder_id`
--

DROP TABLE IF EXISTS `pql_folder_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_folder_id` (
  `file_name` varchar(36) NOT NULL,
  `folder_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_folder_id`
--

LOCK TABLES `pql_folder_id` WRITE;
/*!40000 ALTER TABLE `pql_folder_id` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_folder_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_folder_name`
--

DROP TABLE IF EXISTS `pql_folder_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_folder_name` (
  `folder_id` int(11) NOT NULL AUTO_INCREMENT,
  `folder_name` varchar(26) NOT NULL,
  PRIMARY KEY (`folder_id`),
  UNIQUE KEY `folder_id_UNIQUE` (`folder_id`),
  KEY `fk_name_idx` (`folder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_folder_name`
--

LOCK TABLES `pql_folder_name` WRITE;
/*!40000 ALTER TABLE `pql_folder_name` DISABLE KEYS */;
INSERT INTO `pql_folder_name` VALUES (1,'root');
/*!40000 ALTER TABLE `pql_folder_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_folder_struct`
--

DROP TABLE IF EXISTS `pql_folder_struct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_folder_struct` (
  `parent_id` int(11) DEFAULT NULL,
  `child_id` int(11) NOT NULL,
  UNIQUE KEY `child_id_UNIQUE` (`child_id`),
  KEY `parent-child_idx` (`parent_id`),
  CONSTRAINT `child-id` FOREIGN KEY (`child_id`) REFERENCES `pql_folder_name` (`folder_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `parent-child` FOREIGN KEY (`parent_id`) REFERENCES `pql_folder_struct` (`child_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_folder_struct`
--

LOCK TABLES `pql_folder_struct` WRITE;
/*!40000 ALTER TABLE `pql_folder_struct` DISABLE KEYS */;
INSERT INTO `pql_folder_struct` VALUES (NULL,1);
/*!40000 ALTER TABLE `pql_folder_struct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_index_bots`
--

DROP TABLE IF EXISTS `pql_index_bots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_index_bots` (
  `bot_name` varchar(36) NOT NULL,
  `last_alive` bigint(20) NOT NULL,
  PRIMARY KEY (`bot_name`,`last_alive`),
  UNIQUE KEY `bot_name` (`bot_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_index_bots`
--

LOCK TABLES `pql_index_bots` WRITE;
/*!40000 ALTER TABLE `pql_index_bots` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_index_bots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `pql_index_queue`
--

DROP TABLE IF EXISTS `pql_index_queue`;
/*!50001 DROP VIEW IF EXISTS `pql_index_queue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `pql_index_queue` AS SELECT 
 1 AS `id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `pql_index_status`
--

DROP TABLE IF EXISTS `pql_index_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_index_status` (
  `net_id` int(11) unsigned NOT NULL,
  `bot_name` varchar(36) NOT NULL,
  `status` tinyint(4) unsigned zerofill NOT NULL DEFAULT '0000',
  `type` tinyint(4) unsigned zerofill NOT NULL DEFAULT '0000' COMMENT 'index type:\r\n0 - store all behavioral relations',
  `claim_time` bigint(20) DEFAULT NULL,
  `start_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`net_id`),
  UNIQUE KEY `net_id_2` (`net_id`),
  KEY `net_id` (`net_id`),
  CONSTRAINT `pql_index_status_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_index_status`
--

LOCK TABLES `pql_index_status` WRITE;
/*!40000 ALTER TABLE `pql_index_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_index_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `pql_indexed_ids`
--

DROP TABLE IF EXISTS `pql_indexed_ids`;
/*!50001 DROP VIEW IF EXISTS `pql_indexed_ids`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `pql_indexed_ids` AS SELECT 
 1 AS `net_id`,
 1 AS `external_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `pql_tasks`
--

DROP TABLE IF EXISTS `pql_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_tasks` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `label_id` int(10) unsigned NOT NULL,
  `similarity` double(15,3) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `label_id_and_sim` (`label_id`,`similarity`),
  KEY `label_id` (`label_id`),
  CONSTRAINT `pql_tasks_fk` FOREIGN KEY (`label_id`) REFERENCES `jbpt_labels` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_tasks`
--

LOCK TABLES `pql_tasks` WRITE;
/*!40000 ALTER TABLE `pql_tasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_tasks` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pql_tasks_before_del_tr` BEFORE DELETE ON `pql_tasks`
  FOR EACH ROW
BEGIN
  DELETE FROM pql_tasks_sim WHERE pql_tasks_sim.task_id = OLD.id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `pql_tasks_sim`
--

DROP TABLE IF EXISTS `pql_tasks_sim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_tasks_sim` (
  `task_id` int(11) unsigned NOT NULL,
  `label_id` int(11) unsigned NOT NULL,
  UNIQUE KEY `task_and_label_ids` (`task_id`,`label_id`),
  KEY `label_id` (`label_id`),
  CONSTRAINT `pql_tasks_sim_fk_label_id` FOREIGN KEY (`label_id`) REFERENCES `jbpt_labels` (`id`),
  CONSTRAINT `pql_tasks_sim_fk_task_id` FOREIGN KEY (`task_id`) REFERENCES `pql_tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_tasks_sim`
--

LOCK TABLES `pql_tasks_sim` WRITE;
/*!40000 ALTER TABLE `pql_tasks_sim` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_tasks_sim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_total_causal`
--

DROP TABLE IF EXISTS `pql_total_causal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_total_causal` (
  `net_id` int(11) unsigned NOT NULL,
  `taskA_id` int(10) unsigned NOT NULL,
  `taskB_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`net_id`,`taskA_id`,`taskB_id`),
  KEY `net_id` (`net_id`),
  KEY `taskA_id` (`taskA_id`),
  KEY `taskB_id` (`taskB_id`),
  CONSTRAINT `pql_total_causal_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`),
  CONSTRAINT `pql_total_causal_fk1` FOREIGN KEY (`taskA_id`) REFERENCES `pql_tasks` (`id`),
  CONSTRAINT `pql_total_causal_fk2` FOREIGN KEY (`taskB_id`) REFERENCES `pql_tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_total_causal`
--

LOCK TABLES `pql_total_causal` WRITE;
/*!40000 ALTER TABLE `pql_total_causal` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_total_causal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pql_total_concur`
--

DROP TABLE IF EXISTS `pql_total_concur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_total_concur` (
  `net_id` int(11) unsigned NOT NULL,
  `taskA_id` int(10) unsigned NOT NULL,
  `taskB_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`net_id`,`taskA_id`,`taskB_id`),
  KEY `net_id` (`net_id`),
  KEY `taskA_id` (`taskA_id`),
  KEY `taskB_id` (`taskB_id`),
  CONSTRAINT `pql_total_concurrent_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`),
  CONSTRAINT `pql_total_concurrent_fk1` FOREIGN KEY (`taskA_id`) REFERENCES `pql_tasks` (`id`),
  CONSTRAINT `pql_total_concurrent_fk2` FOREIGN KEY (`taskB_id`) REFERENCES `pql_tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pql_total_concur`
--

LOCK TABLES `pql_total_concur` WRITE;
/*!40000 ALTER TABLE `pql_total_concur` DISABLE KEYS */;
/*!40000 ALTER TABLE `pql_total_concur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pql'
--

--
-- Dumping routines for database 'pql'
--
/*!50003 DROP FUNCTION IF EXISTS `fldr_id_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fldr_id_create`(file_name VARCHAR(36), folder_id INT(11)) RETURNS int(11)
BEGIN

  INSERT INTO `pql_folder_id`
  (`pql_folder_id`.`file_name`,`pql_folder_id`.`folder_id`)
  VALUES
  (file_name,folder_id);

  RETURN 0;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fldr_struct_move` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fldr_struct_move`(original VARCHAR(36), targetfolderlocation varchar(36)) RETURNS int(11)
BEGIN

UPDATE pql.`pql_folder_struct` SET `parent_id`= (Select folder_id from `pql`.`pql_folder_name` Where Folder_name = targetfolderlocation)
Where child_id = (Select folder_id from pql.`pql_folder_name` Where Folder_name = original);


RETURN 0;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `jbpt_labels_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `jbpt_labels_create`(label TEXT) RETURNS int(11)
    DETERMINISTIC
BEGIN
  DECLARE result INTEGER;
  
  SET label = TRIM(label);
  
  SELECT `jbpt_labels`.`id` INTO result
  FROM `jbpt_labels`
  WHERE `jbpt_labels`.`label`=label;
  
  IF result IS NOT NULL THEN
    RETURN result;
  END IF;

  INSERT INTO `jbpt_labels` (`jbpt_labels`.`label`)
  VALUES (label);

  SET result = LAST_INSERT_ID();

  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `jbpt_petri_flow_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `jbpt_petri_flow_create`(source INTEGER(11), target INTEGER(11), name TEXT, description TEXT) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
  INSERT INTO `jbpt_petri_flow`
  (`jbpt_petri_flow`.`source`,`jbpt_petri_flow`.`target`,`jbpt_petri_flow`.`name`,`jbpt_petri_flow`.`description`)
  VALUES
  (source,target,name,description);
  
  RETURN TRUE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `jbpt_petri_markings_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `jbpt_petri_markings_create`(place_id INTEGER(11), tokens INTEGER(11)) RETURNS tinyint(1)
BEGIN
  INSERT INTO `jbpt_petri_markings`
  (`jbpt_petri_markings`.`place_id`,`jbpt_petri_markings`.`tokens`)
  VALUES
  (place_id,tokens);

  RETURN TRUE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `jbpt_petri_nets_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `jbpt_petri_nets_create`(uuid VARCHAR(36), name TEXT, description TEXT, external_id TEXT, pnml_content LONGTEXT) RETURNS int(11)
BEGIN
  IF jbpt_petri_nets_get_internal_id(external_id) IS NOT NULL THEN
    RETURN 0;
  END IF;

  INSERT INTO `jbpt_petri_nets`
  (`jbpt_petri_nets`.`uuid`,`jbpt_petri_nets`.name,`jbpt_petri_nets`.description,`jbpt_petri_nets`.`external_id`,`jbpt_petri_nets`.`pnml_content`)
  VALUES
  (uuid,name,description,external_id,pnml_content);

  RETURN LAST_INSERT_ID();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `jbpt_petri_nets_delete` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `jbpt_petri_nets_delete`(internal_id INTEGER(11)) RETURNS int(11)
    DETERMINISTIC
BEGIN
  DECLARE delID INTEGER;
  
  SELECT id INTO delID FROM `jbpt_petri_nets` WHERE `jbpt_petri_nets`.`id` = internal_id;
  
  IF delID IS NULL THEN
    RETURN 0;
  END IF;

  DELETE FROM `jbpt_petri_nets` WHERE `jbpt_petri_nets`.`id` = delID;

  RETURN delID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `jbpt_petri_nets_get_external_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `jbpt_petri_nets_get_external_id`(internal_id INTEGER(11)) RETURNS text CHARSET utf8
BEGIN
  DECLARE result TEXT;

  SELECT `jbpt_petri_nets`.`external_id` INTO result
  FROM `jbpt_petri_nets`
  WHERE `jbpt_petri_nets`.`id`=internal_id;

  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `jbpt_petri_nets_get_internal_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `jbpt_petri_nets_get_internal_id`(external_id TEXT) RETURNS int(11)
    DETERMINISTIC
BEGIN
  DECLARE result INTEGER;

  SELECT `jbpt_petri_nets`.`id` INTO result
  FROM `jbpt_petri_nets`
  WHERE `jbpt_petri_nets`.`external_id`=external_id;
    
  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `jbpt_petri_nets_get_pnml_content` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `jbpt_petri_nets_get_pnml_content`(internal_id INTEGER(11)) RETURNS LONGTEXT CHARSET utf8
BEGIN
  DECLARE result LONGTEXT;

  SELECT `jbpt_petri_nets`.`pnml_content` INTO result
  FROM `jbpt_petri_nets`
  WHERE `jbpt_petri_nets`.`id`=internal_id;

  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `jbpt_petri_nodes_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `jbpt_petri_nodes_create`(net_id INTEGER(11), uuid VARCHAR(50), name TEXT, description TEXT, label TEXT, is_transition BOOLEAN) RETURNS int(11)
BEGIN
  DECLARE labelID INTEGER;
  
  IF label = "" THEN
    SET labelID = NULL;
  ELSE
    SET labelID = jbpt_labels_create(label);
  END IF;

  INSERT INTO `jbpt_petri_nodes`
  (`jbpt_petri_nodes`.`net_id`,`jbpt_petri_nodes`.`uuid`,`jbpt_petri_nodes`.name,`jbpt_petri_nodes`.description,`jbpt_petri_nodes`.label_id,`jbpt_petri_nodes`.is_transition)
  VALUES
  (net_id,uuid,name,description,labelID,is_transition);

  RETURN LAST_INSERT_ID();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_always_occurs` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_always_occurs`(net_id INTEGER(11), task_id INTEGER) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
  IF EXISTS(SELECT 1 FROM `pql_always_occurs` WHERE `pql_always_occurs`.`net_id`=net_id AND `pql_always_occurs`.`task_id`=task_id LIMIT 1) THEN
    RETURN true;
  END IF;

  RETURN false;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_can_conflict` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_can_conflict`(net_id INTEGER(11), taskA_id INTEGER, taskB_id INTEGER) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
  IF EXISTS(SELECT 1 FROM `pql_can_conflict` WHERE `pql_can_conflict`.`net_id`=net_id AND `pql_can_conflict`.`taskA_id`=taskA_id AND `pql_can_conflict`.`taskB_id`=taskB_id LIMIT 1) THEN
    RETURN true;
  END IF;

  RETURN false;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_can_cooccur` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_can_cooccur`(net_id INTEGER(11), taskA_id INTEGER, taskB_id INTEGER) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
  IF EXISTS(SELECT 1 FROM `pql_can_cooccur` WHERE `pql_can_cooccur`.`net_id`=net_id AND `pql_can_cooccur`.`taskA_id`=taskA_id AND `pql_can_cooccur`.`taskB_id`=taskB_id LIMIT 1) THEN
    RETURN true;
  END IF;

  RETURN false;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_can_occur` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_can_occur`(net_id INTEGER(11), task_id INTEGER) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
  IF EXISTS(SELECT 1 FROM `pql_can_occur` WHERE `pql_can_occur`.`net_id`=net_id AND `pql_can_occur`.`task_id`=task_id LIMIT 1) THEN
    RETURN true;
  END IF;

  RETURN false;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_folder_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_folder_create`(parent_id INT(11), new_name varchar(36)) RETURNS int(11)
BEGIN

Insert into pql_folder_name (folder_name)  values(new_name);
Insert into pql_folder_struct values(parent_id, (SELECT MAX(folder_id) FROM pql_folder_name));



RETURN 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_folder_delete` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_folder_delete`(given_folder INT(11)) RETURNS int(11)
BEGIN
    
DELETE FROM pql_folder_name WHERE folder_id = given_folder;
DELETE FROM pql_folder_struct WHERE child_id NOT IN (SELECT folder_id FROM pql_folder_name);
DELETE FROM pql_folder_name WHERE folder_id NOT IN (SELECT child_id FROM pql_folder_struct);
DELETE FROM pql_folder_id WHERE folder_id NOT IN (SELECT child_id FROM pql_folder_struct);
DELETE FROM jbpt_petri_nets WHERE external_id NOT IN (SELECT file_name FROM pql_folder_id);


RETURN 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_folder_move` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`pql_master`@`%` FUNCTION `pql_folder_move`(given_folder INT(11), new_parent INT(11)) RETURNS int(11)
BEGIN

UPDATE pql_folder_struct
SET parent_id = new_parent
WHERE child_id = given_folder;

RETURN 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_id_move` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`pql_master`@`%` FUNCTION `pql_id_move`(given_folder Varchar(60), new_parent INT(11)) RETURNS int(11)
BEGIN

UPDATE pql_folder_id
SET  folder_id = new_parent
WHERE file_name = given_folder;

RETURN 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_bots_is_alive` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_bots_is_alive`(bot_name VARCHAR(36)) RETURNS tinyint(4)
BEGIN
  DECLARE result bool DEFAULT FALSE;

  SELECT EXISTS(
  SELECT
    *
  FROM
    `pql_index_bots`
  WHERE
    `pql_index_bots`.`bot_name` = TRIM(bot_name)
  ) INTO result;

  IF result THEN
    UPDATE `pql_index_status` SET `pql_index_status`.`start_time` = UNIX_TIMESTAMP()
    WHERE `pql_index_status`.`net_id` = net_id AND
    `pql_index_status`.`bot_name` = bot_name;
  END IF;

  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_delete` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_delete`(internal_id INTEGER(11)) RETURNS tinyint(1)
BEGIN
  DECLARE delID INTEGER;

  SELECT net_id INTO delID FROM `pql_index_status` WHERE `pql_index_status`.`net_id` = internal_id;

  IF delID IS NULL THEN
    RETURN FALSE;
  END IF;

  DELETE FROM `pql_index_status` WHERE `pql_index_status`.`net_id` = delID;

  RETURN TRUE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_delete_indexed_relations` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_delete_indexed_relations`(internal_id INTEGER(11)) RETURNS tinyint(1)
BEGIN
  
  DELETE FROM `pql_always_occurs` WHERE `pql_always_occurs`.`net_id` = internal_id;
  DELETE FROM `pql_can_conflict` WHERE `pql_can_conflict`.`net_id` = internal_id;
  DELETE FROM `pql_can_cooccur` WHERE `pql_can_cooccur`.`net_id` = internal_id;
  DELETE FROM `pql_can_occur` WHERE `pql_can_occur`.`net_id` = internal_id;
  DELETE FROM `pql_total_causal` WHERE `pql_total_causal`.`net_id` = internal_id;
  DELETE FROM `pql_total_concur` WHERE `pql_total_concur`.`net_id` = internal_id;

  RETURN TRUE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_get_end_time` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_get_end_time`(internal_id INTEGER) RETURNS int(11)
    DETERMINISTIC
BEGIN
DECLARE indexend INTEGER;

  SELECT `pql_index_status`.`end_time` INTO indexend
  FROM `pql_index_status`
  WHERE `pql_index_status`.`net_id`=internal_id;

RETURN indexend;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_get_next_job` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_get_next_job`() RETURNS int(11)
BEGIN
  DECLARE result INTEGER;

  SELECT id INTO result FROM `pql_index_queue` LIMIT 0,1;

  IF result IS NULL THEN RETURN 0; END IF;
  
  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_get_start_time` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_get_start_time`(internal_id INTEGER) RETURNS int(11)
    DETERMINISTIC
BEGIN
DECLARE indexstart INTEGER;

  SELECT `pql_index_status`.`start_time` INTO indexstart
  FROM `pql_index_status`
  WHERE `pql_index_status`.`net_id`=internal_id;

RETURN indexstart;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_get_status` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_get_status`(internal_id INTEGER(11)) RETURNS tinyint(4)
BEGIN
  DECLARE result TINYINT(4);

  SELECT `pql_index_status`.`status` INTO result
  FROM `pql_index_status` WHERE `pql_index_status`.`net_id`=internal_id;

  IF result IS NULL THEN
    RETURN -1;
  END IF;

  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_get_type` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_get_type`(internal_id INTEGER(11)) RETURNS tinyint(4)
BEGIN
  DECLARE result TINYINT(4);

  SELECT `pql_index_status`.`type` INTO result
  FROM `pql_index_status` WHERE `pql_index_status`.`net_id`=internal_id;

  IF result IS NULL THEN
    RETURN -1;
  END IF;

  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_start_job` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_start_job`(net_id INTEGER(11), bot_name VARCHAR(36)) RETURNS tinyint(1)
BEGIN
  DECLARE result bool DEFAULT FALSE;

  SELECT EXISTS(
  SELECT
    *
  FROM
    `pql_index_status`
  WHERE
    `pql_index_status`.`net_id` = net_id AND
    `pql_index_status`.`bot_name` = bot_name AND
    `pql_index_status`.`status` = 0
  ) INTO result;

  IF result THEN
    UPDATE `pql_index_status` SET `pql_index_status`.`start_time` = UNIX_TIMESTAMP()
    WHERE `pql_index_status`.`net_id` = net_id AND
    `pql_index_status`.`bot_name` = bot_name;
  END IF;

  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_index_time` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_index_time`(internal_id INTEGER) RETURNS int(11)
    DETERMINISTIC
BEGIN
DECLARE indexstart INTEGER;
DECLARE indexend INTEGER;

  SELECT `pql_index_status`.`start_time` INTO indexstart
  FROM `pql_index_status`
  WHERE `pql_index_status`.`net_id`=internal_id;

  SELECT `pql_index_status`.`end_time` INTO indexend
  FROM `pql_index_status`
  WHERE `pql_index_status`.`net_id`=internal_id;


RETURN indexend-indexstart;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_levenshtein` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_levenshtein`(s1 TEXT, s2 TEXT) RETURNS int(11)
    DETERMINISTIC
BEGIN
    
    
    
    DECLARE s1_len, s2_len, i, j, c, c_temp, cost INT;
    DECLARE s1_char CHAR;
    DECLARE cv0, cv1 TEXT; 
    SET s1_len = CHAR_LENGTH(s1), s2_len = CHAR_LENGTH(s2), cv1 = 0x00, j = 1, i = 1, c = 0;
    IF s1 = s2 THEN
      RETURN 0;
    ELSEIF s1_len = 0 THEN
      RETURN s2_len;
    ELSEIF s2_len = 0 THEN
      RETURN s1_len;
    ELSE
      WHILE j <= s2_len DO
        SET cv1 = CONCAT(cv1, UNHEX(HEX(j))), j = j + 1;
      END WHILE;
      WHILE i <= s1_len DO
        SET s1_char = SUBSTRING(s1, i, 1), c = i, cv0 = UNHEX(HEX(i)), j = 1;
        WHILE j <= s2_len DO
          SET c = c + 1;
          IF s1_char = SUBSTRING(s2, j, 1) THEN
            SET cost = 0; ELSE SET cost = 1;
          END IF;
          SET c_temp = CONV(HEX(SUBSTRING(cv1, j, 1)), 16, 10) + cost;
          IF c > c_temp THEN SET c = c_temp; END IF;
          SET c_temp = CONV(HEX(SUBSTRING(cv1, j+1, 1)), 16, 10) + 1;
          IF c > c_temp THEN
            SET c = c_temp;
          END IF;
          SET cv0 = CONCAT(cv0, UNHEX(HEX(c))), j = j + 1;
        END WHILE;
        SET cv1 = cv0, i = i + 1;
      END WHILE;
    END IF;
    RETURN c;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_tasks_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_tasks_create`(label TEXT, similarity DOUBLE) RETURNS int(11)
    DETERMINISTIC
BEGIN
  DECLARE result INTEGER;
  DECLARE labelID INTEGER;

  SET labelID = jbpt_labels_create(label);
    
  SELECT `pql_tasks`.`id` INTO result
  FROM `pql_tasks`
  WHERE `pql_tasks`.`label_id`=labelID AND `pql_tasks`.`similarity`=similarity;

  IF result IS NOT NULL THEN
    RETURN result;
  END IF;

  INSERT INTO `pql_tasks` (`pql_tasks`.`label_id`,`pql_tasks`.`similarity`)
  VALUES (labelID,similarity);

  SET result = LAST_INSERT_ID();

  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_tasks_get` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_tasks_get`(label TEXT, sim DOUBLE, threshold DOUBLE) RETURNS int(11)
    DETERMINISTIC
BEGIN
  DECLARE labelID INTEGER;
  DECLARE result INTEGER;
  
  SET labelID = jbpt_labels_create(label);

  SELECT id INTO result
  FROM
  (SELECT id,label_id,ABS(similarity-sim) AS distance
  FROM pql_tasks
  WHERE label_id=labelID AND ABS(similarity-sim)<threshold
  ORDER BY distance ASC
  LIMIT 0,1) AS tbl;
  
  IF result IS NULL THEN
    RETURN 0;
  END IF;
  
  RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_tasks_sim_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_tasks_sim_create`(labelA TEXT, labelB TEXT, similarity DOUBLE) RETURNS int(11)
    DETERMINISTIC
BEGIN
  DECLARE labelAid INTEGER;
  DECLARE labelBid INTEGER;
  DECLARE taskID INTEGER;
  DECLARE result INTEGER;

  SET labelAid = jbpt_labels_create(labelA);
  SET labelBid = jbpt_labels_create(labelB);
  
  SET taskID = pql_tasks_create(labelA,similarity);

  SELECT `pql_tasks_sim`.`task_id` INTO result
  FROM `pql_tasks_sim`
  WHERE `pql_tasks_sim`.`task_id`=taskID AND `pql_tasks_sim`.`label_id`=labelBid;
  
  IF result IS NOT NULL THEN
    RETURN 0;
  END IF;
  
  INSERT INTO `pql_tasks_sim` (`pql_tasks_sim`.`task_id`,`pql_tasks_sim`.`label_id`)
  VALUES (taskID,labelBid);

  RETURN taskID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_total_causal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_total_causal`(net_id INTEGER(11), taskA_id INTEGER, taskB_id INTEGER) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
  IF EXISTS(SELECT 1 FROM `pql_total_causal` WHERE `pql_total_causal`.`net_id`=net_id AND `pql_total_causal`.`taskA_id`=taskA_id AND `pql_total_causal`.`taskB_id`=taskB_id LIMIT 1) THEN
    RETURN true;
  END IF;

  RETURN false;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `pql_total_concur` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pql_total_concur`(net_id INTEGER(11), taskA_id INTEGER, taskB_id INTEGER) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
  IF EXISTS(SELECT 1 FROM `pql_total_concur` WHERE `pql_total_concur`.`net_id`=net_id AND `pql_total_concur`.`taskA_id`=taskA_id AND `pql_total_concur`.`taskB_id`=taskB_id LIMIT 1) THEN
    RETURN true;
  END IF;

  RETURN false;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `double_up` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `double_up`(search_folder_name VARCHAR(100), Inside_folder_ID INT(11))
BEGIN

Select count(folder_id) from pql_folder_name where folder_name = search_folder_name AND folder_id =
(Select child_id from pql_folder_struct where parent_id = Inside_folder_ID and child_id = folder_id);


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `double_up_file` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `double_up_file`(pnml_id VARCHAR(100), Inside_folder_ID INT(11))
BEGIN
select count(*) from pql_folder_id where folder_id = Inside_folder_ID AND file_name = pnml_id;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `id_to_children` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`pql_master`@`%` PROCEDURE `id_to_children`(id int(11))
BEGIN

Select child_id from pql_folder_struct where parent_id = id;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `id_to_filename` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`pql_master`@`%` PROCEDURE `id_to_filename`(ID int(11))
BEGIN

Select file_name from pql_folder_id where folder_id = ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `id_to_foldername` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`pql_master`@`%` PROCEDURE `id_to_foldername`(ID int(11))
BEGIN

Select folder_name from pql_folder_name where folder_id = ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `jbpt_get_net_labels` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `jbpt_get_net_labels`(IN identifier TEXT)
BEGIN
  DECLARE nid INTEGER;
  
  SELECT id INTO nid FROM jbpt_petri_nets WHERE jbpt_petri_nets.`external_id`=identifier;
  
  SELECT DISTINCT `jbpt_labels`.`label`
  FROM `jbpt_labels`, `jbpt_petri_nodes`
  WHERE `jbpt_petri_nodes`.`net_id`=nid AND
        `jbpt_petri_nodes`.`label_id` IS NOT NULL AND
        `jbpt_labels`.`id` = `jbpt_petri_nodes`.`label_id`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `jbpt_get_net_labels_ext_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `jbpt_get_net_labels_ext_id`(IN identifier TEXT)
    DETERMINISTIC
BEGIN
  DECLARE nid INTEGER;
  
  SELECT id INTO nid FROM jbpt_petri_nets WHERE jbpt_petri_nets.`external_id`=identifier;
  
  SELECT `jbpt_labels`.`label`
  FROM `jbpt_labels`, `jbpt_petri_nodes`
  WHERE `jbpt_petri_nodes`.`net_id`=nid AND
        `jbpt_petri_nodes`.`label_id` IS NOT NULL AND
        `jbpt_labels`.`id` = `jbpt_petri_nodes`.`label_id`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `jbpt_get_net_labels_int_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `jbpt_get_net_labels_int_id`(IN id INTEGER(11))
    DETERMINISTIC
BEGIN
  SELECT `jbpt_labels`.`label`
  FROM `jbpt_labels`, `jbpt_petri_nodes`
  WHERE `jbpt_petri_nodes`.`net_id`=id AND
        `jbpt_petri_nodes`.`is_transition`=1 AND
        `jbpt_labels`.`id` = `jbpt_petri_nodes`.`label_id`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `jbpt_petri_nets_get_internal_ids` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `jbpt_petri_nets_get_internal_ids`()
BEGIN
  SELECT `jbpt_petri_nets`.`id`
  FROM `jbpt_petri_nets`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `locations_query` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `locations_query`(targetfolderid int(11))
BEGIN

SELECT DISTINCT jbpt_petri_nets.`external_id`
From jbpt_petri_nets, pql_folder_id
where pql_folder_id.`folder_id`= targetfolderid AND
	  pql_folder_id.`file_name` = jbpt_petri_nets.`external_id`;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_always_occurs_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_always_occurs_create`(IN net_id INTEGER(11), IN task_id INTEGER)
    DETERMINISTIC
BEGIN
  INSERT IGNORE INTO `pql_always_occurs`
  (`pql_always_occurs`.`net_id`,`pql_always_occurs`.`task_id`)
  VALUES
  (net_id,task_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_can_conflict_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_can_conflict_create`(IN net_id INTEGER(11), IN taskA_id INTEGER, IN taskB_id INTEGER)
    DETERMINISTIC
BEGIN
  INSERT IGNORE INTO `pql_can_conflict`
  (`pql_can_conflict`.`net_id`,`pql_can_conflict`.`taskA_id`,`pql_can_conflict`.`taskB_id`)
  VALUES (net_id,taskA_id,taskB_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_can_cooccur_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_can_cooccur_create`(IN net_id INTEGER(11), IN taskA_id INTEGER, IN taskB_id INTEGER)
    DETERMINISTIC
BEGIN
  INSERT IGNORE INTO `pql_can_cooccur`
  (`pql_can_cooccur`.`net_id`,`pql_can_cooccur`.`taskA_id`,`pql_can_cooccur`.`taskB_id`)
  VALUES (net_id,taskA_id,taskB_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_can_occur_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_can_occur_create`(IN net_id INTEGER(11), IN task_id INTEGER)
    DETERMINISTIC
BEGIN
  INSERT IGNORE INTO `pql_can_occur`
  (`pql_can_occur`.`net_id`,`pql_can_occur`.`task_id`)
  VALUES
  (net_id,task_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_check_binary_predicate_macro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_check_binary_predicate_macro`(IN netID int, IN op TEXT, IN q TEXT, IN set1 json, IN set2 json)
    DETERMINISTIC
BEGIN
       
  SET @size1 = JSON_LENGTH(set1);
  SET @size2 = JSON_LENGTH(set2);
  SET @ids1 = SUBSTRING(set1,2,LENGTH(set1)-2);
  SET @ids2 = SUBSTRING(set2,2,LENGTH(set2)-2);
  
  IF op='cancooccur' THEN SET @tbl='pql_can_cooccur';
  ELSEIF op='canconflict' THEN SET @tbl='pql_can_conflict'; 
  ELSEIF op='totalcausal' THEN SET @tbl='pql_total_causal'; 
  ELSE SET @tbl='pql_total_concur'; 
  END IF;
  
  IF q='any' OR q='all' THEN
  SET @query = CONCAT('select count(*) INTO @count FROM ',@tbl,' WHERE net_id=',netID,' AND taskA_id IN (',@ids1,') AND taskB_id IN (',@ids2,')');
  ELSE 
  SET @query = CONCAT('select count(distinct taskA_id) INTO @count FROM ',@tbl,' WHERE net_id=',netID,' AND taskA_id IN (',@ids1,') AND taskB_id IN (',@ids2,')');
  END IF;
 
  PREPARE stmt FROM @query;
  EXECUTE stmt;
  DEALLOCATE PREPARE stmt;
  
  IF q='any' AND @count>0 OR q='each' AND @count=@size1 OR q='all' AND @count=@size1*@size2 THEN SET @result=TRUE; 
  ELSE SET @result=FALSE; END IF;
  
  SELECT @result;
       
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_check_unary_predicate_macro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_check_unary_predicate_macro`(IN netID int, IN op TEXT, IN q TEXT, IN ids json)
    DETERMINISTIC
BEGIN
       
  SET @size = JSON_LENGTH(ids);
  SET @idsLine = SUBSTRING(ids,2,LENGTH(ids)-2);
  
  IF op='canoccur' THEN SET @tbl='pql_can_occur';
  ELSE SET @tbl='pql_always_occurs'; END IF;
  
  SET @query = CONCAT('select count(*) INTO @count FROM ',@tbl,' WHERE net_id=',netID,' AND task_id IN (',@idsLine,')');
  PREPARE stmt FROM @query;
  EXECUTE stmt;
  DEALLOCATE PREPARE stmt;
  
  IF q='any' AND @count>0 OR q='all' AND @count=@size THEN SET @result=TRUE; 
  ELSE SET @result=FALSE; END IF;
  
  SELECT @result;
       
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_get_indexed_ids` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_get_indexed_ids`()
BEGIN
  SELECT * FROM `pql_indexed_ids`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_get_task_ids` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_get_task_ids`(IN l json, s json)
BEGIN
  DECLARE max INTEGER;
  DECLARE counter INTEGER;
  DECLARE path TEXT;
    
  SET counter = 0;
  SET max = JSON_LENGTH(l)-1;
  
  SET @query = 'SELECT pql_tasks.id, pql_tasks.similarity, jbpt_labels.label FROM pql_tasks, jbpt_labels
					WHERE pql_tasks.label_id=jbpt_labels.id AND (';
					
     
  while counter < max do
	SET path = CONCAT('$[', counter, ']');
	SET @label = JSON_EXTRACT(l, path);
    SET @sim = JSON_EXTRACT(s, path);
    
    SET @query = CONCAT(@query,	'jbpt_labels.label=',@label,' AND pql_tasks.similarity=',@sim,' OR ');          	
     
    SET counter=counter+1;
  end while;
  
  SET path = CONCAT('$[', max, ']');
  SET @label = JSON_EXTRACT(l, path);
  SET @sim = JSON_EXTRACT(s, path);
    
  SET @query = CONCAT(@query,	'jbpt_labels.label=',@label,' AND pql_tasks.similarity=',@sim,')');          	
  
  PREPARE stmt FROM @query;
  EXECUTE stmt;
  DEALLOCATE PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_get_universe_ids` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`pql_master`@`%` PROCEDURE `pql_get_universe_ids`()
BEGIN

Select file_name from pql_folder_id;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_index_bots_alive` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_index_bots_alive`(IN bot_name VARCHAR(36))
BEGIN
  DECLARE bname VARCHAR(36);

  SELECT `pql_index_bots`.`bot_name` INTO bname
  FROM `pql_index_bots`
  WHERE `pql_index_bots`.`bot_name` = TRIM(bot_name);

  IF bname IS NULL THEN
    INSERT INTO `pql_index_bots` (`pql_index_bots`.`bot_name`,`pql_index_bots`.`last_alive`)
    VALUES (TRIM(bot_name),UNIX_TIMESTAMP());
  ELSE
    UPDATE `pql_index_bots`
    SET `pql_index_bots`.`last_alive`=UNIX_TIMESTAMP()
    WHERE `pql_index_bots`.`bot_name` = bname;
  END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_index_cannot` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_index_cannot`(IN net_id INTEGER(11))
BEGIN

UPDATE `pql_index_status`
  SET `pql_index_status`.`status`=2, `pql_index_status`.`end_time`=UNIX_TIMESTAMP()
WHERE `pql_index_status`.`net_id`=net_id;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_index_claim_job` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_index_claim_job`(IN net_id INTEGER(11), IN bot_name VARCHAR(36))
BEGIN

  INSERT INTO `pql_index_status` (`pql_index_status`.`net_id`, `pql_index_status`.`bot_name`, `pql_index_status`.`claim_time`)
  VALUES (net_id,bot_name,UNIX_TIMESTAMP());

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_index_cleanup` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_index_cleanup`()
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE bname VARCHAR(36);

  DECLARE cur1 CURSOR FOR
  SELECT `pql_index_bots`.`bot_name`
  FROM `pql_index_bots`
  WHERE (UNIX_TIMESTAMP()-`pql_index_bots`.`last_alive`)>(3600*5);

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN cur1;

  read_loop: LOOP

    FETCH cur1 INTO bname;
  
    IF done THEN
      LEAVE read_loop;
    END IF;

    DELETE FROM `pql_index_status`
    WHERE `pql_index_status`.`bot_name`=bname AND `pql_index_status`.`status`<1;
  
    DELETE FROM `pql_index_bots`
    WHERE `pql_index_bots`.`bot_name` = bname;
    
  END LOOP;

  CLOSE cur1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_index_finish_job` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_index_finish_job`(IN net_id INTEGER(11), IN bot_name VARCHAR(36))
BEGIN
  UPDATE `pql_index_status`
  SET
    `pql_index_status`.`end_time` = UNIX_TIMESTAMP(),
    `pql_index_status`.`status` = 1
  WHERE
    `pql_index_status`.`net_id` = net_id AND
    `pql_index_status`.`bot_name` = bot_name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_levenshtein_label_sim_search` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_levenshtein_label_sim_search`(IN label TEXT)
BEGIN
  DECLARE x TEXT;
  SET x = TRIM(LOWER(label));
  SELECT jbpt_labels.`label`, 1-`pql_levenshtein`(x,TRIM(LOWER(jbpt_labels.`label`)))/(2*GREATEST(CHAR_LENGTH(x),CHAR_LENGTH(TRIM(LOWER(jbpt_labels.`label`))))) AS sim
  FROM jbpt_labels ORDER BY sim DESC LIMIT 0,10;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_tasks_get_in_net` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_tasks_get_in_net`(IN net_id INTEGER(11))
BEGIN
 SELECT DISTINCT `pql_tasks_sim`.`task_id`
  FROM `pql_tasks_sim`
  WHERE label_id IN
  (
    SELECT label_id
    FROM `jbpt_petri_nets`
    WHERE `jbpt_petri_nets`.`id`=net_id
  );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_tasks_get_nets` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_tasks_get_nets`(IN task_id INTEGER)
BEGIN
  SELECT DISTINCT net_id
  FROM `jbpt_petri_nodes`, `pql_tasks_sim`
  WHERE `jbpt_petri_nodes`.`is_transition` IS TRUE AND
    `jbpt_petri_nodes`.`label_id`=`pql_tasks_sim`.`label_id` AND
    `pql_tasks_sim`.`task_id`=task_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_tasks_get_sim` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_tasks_get_sim`(IN task_id INTEGER(11))
BEGIN
  SELECT DISTINCT `jbpt_labels`.`label`
  FROM `pql_tasks_sim`,`jbpt_labels`
  WHERE `pql_tasks_sim`.`label_id`=`jbpt_labels`.`id` AND `pql_tasks_sim`.`task_id` = task_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_total_causal_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_total_causal_create`(IN net_id INTEGER(11), IN taskA_id INTEGER, IN taskB_id INTEGER)
    DETERMINISTIC
BEGIN
  INSERT IGNORE INTO `pql_total_causal`
  (`pql_total_causal`.`net_id`,`pql_total_causal`.`taskA_id`,`pql_total_causal`.`taskB_id`)
  VALUES (net_id,taskA_id,taskB_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pql_total_concur_create` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pql_total_concur_create`(IN net_id INTEGER(11), IN taskA_id INTEGER, IN taskB_id INTEGER)
    DETERMINISTIC
BEGIN
  INSERT IGNORE INTO `pql_total_concur`
  (`pql_total_concur`.`net_id`,`pql_total_concur`.`taskA_id`,`pql_total_concur`.`taskB_id`)
  VALUES (net_id,taskA_id,taskB_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reset` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reset`()
    DETERMINISTIC
    SQL SECURITY INVOKER
BEGIN

  /* Resest location tables */
  DELETE FROM pql_folder_id;
  DELETE FROM pql_folder_name;
  DELETE FROM pql_folder_struct;
  ALTER TABLE pql_folder_name AUTO_INCREMENT = 1;
  
  INSERT INTO pql_folder_name (folder_name, folder_id) VALUES ("root", 1);
  INSERT INTO pql_folder_struct (child_id) VALUES (1);
    
  /* Reset pretri nets */
  DELETE FROM `jbpt_petri_nets`;
  
  DELETE FROM `pql_tasks` WHERE `pql_tasks`.`label_id` NOT IN
  (SELECT `jbpt_petri_nodes`.`label_id` FROM `jbpt_petri_nodes`);
  
  DELETE FROM jbpt_labels WHERE `jbpt_labels`.`id` NOT IN
  (SELECT `jbpt_petri_nodes`.`label_id` FROM `jbpt_petri_nodes`);
  
  ALTER TABLE jbpt_petri_nets  AUTO_INCREMENT = 1;
  ALTER TABLE jbpt_petri_nodes AUTO_INCREMENT = 1;
  ALTER TABLE jbpt_labels      AUTO_INCREMENT = 1;
  ALTER TABLE pql_tasks        AUTO_INCREMENT = 1;

  DELETE FROM `pql_index_bots`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `return_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `return_id`(originalfolderid INT(11), searchfoldername VARCHAR(36))
BEGIN
	

/* finds the folder we are searching for */

	Select folder_id from pql_folder_name 
    where folder_id = (select child_id from pql_folder_struct where parent_id = originalfolderid AND 
    child_id= (Select folder_id from pql_folder_name where folder_name = searchfoldername));
   /* AND folder_name = searchfoldername;*/

	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `pql_index_queue`
--

/*!50001 DROP VIEW IF EXISTS `pql_index_queue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `pql_index_queue` AS select `jbpt_petri_nets`.`id` AS `id` from `jbpt_petri_nets` where (not(`jbpt_petri_nets`.`id` in (select `pql_index_status`.`net_id` AS `id` from `pql_index_status`))) order by `jbpt_petri_nets`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `pql_indexed_ids`
--

/*!50001 DROP VIEW IF EXISTS `pql_indexed_ids`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `pql_indexed_ids` AS select `pql_index_status`.`net_id` AS `net_id`,`jbpt_petri_nets`.`external_id` AS `external_id` from (`jbpt_petri_nets` join `pql_index_status`) where ((`pql_index_status`.`net_id` = `jbpt_petri_nets`.`id`) and (`pql_index_status`.`status` = 1)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-04 14:56:09
