-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: pql
-- ------------------------------------------------------
-- Server version	5.6.16-log

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `jbpt_labels_before_del_tr` BEFORE DELETE ON `jbpt_labels`
  FOR EACH ROW
BEGIN
  DELETE FROM pql_tasks_sim WHERE pql_tasks_sim.label_id = OLD.id;
  DELETE FROM pql_tasks WHERE pql_tasks.label_id=OLD.id;
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
  `pnml_content` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`(20)),
  UNIQUE KEY `external_id` (`external_id`(20))
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `jbpt_petri_nets_before_del_tr` BEFORE DELETE ON `jbpt_petri_nets`
  FOR EACH ROW
BEGIN
  DELETE FROM pql_index_status WHERE pql_index_status.net_id=OLD.id;

  DELETE FROM jbpt_petri_nodes WHERE jbpt_petri_nodes.net_id=OLD.id;
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
  KEY `net_id` (`net_id`),
  KEY `label_id` (`label_id`),
  CONSTRAINT `jbpt_nodes_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`),
  CONSTRAINT `jbpt_petri_nodes_fk` FOREIGN KEY (`label_id`) REFERENCES `jbpt_labels` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=282 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
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
-- Temporary table structure for view `jbpt_unused_labels`
--

DROP TABLE IF EXISTS `jbpt_unused_labels`;
/*!50001 DROP VIEW IF EXISTS `jbpt_unused_labels`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `jbpt_unused_labels` (
  `label_id` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `pql_always_occurs`
--

DROP TABLE IF EXISTS `pql_always_occurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_always_occurs` (
  `net_id` int(11) unsigned NOT NULL,
  `task_id` int(10) unsigned NOT NULL,
  `value` tinyint(1) NOT NULL,
  PRIMARY KEY (`net_id`,`task_id`),
  KEY `net_id` (`net_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `pql_always_occurs_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`),
  CONSTRAINT `pql_always_occurs_fk1` FOREIGN KEY (`task_id`) REFERENCES `pql_tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `value` tinyint(1) NOT NULL,
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
-- Table structure for table `pql_can_cooccur`
--

DROP TABLE IF EXISTS `pql_can_cooccur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_can_cooccur` (
  `net_id` int(11) unsigned NOT NULL,
  `taskA_id` int(10) unsigned NOT NULL,
  `taskB_id` int(10) unsigned NOT NULL,
  `value` tinyint(1) NOT NULL,
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
-- Table structure for table `pql_can_occur`
--

DROP TABLE IF EXISTS `pql_can_occur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_can_occur` (
  `net_id` int(11) unsigned NOT NULL,
  `task_id` int(10) unsigned NOT NULL,
  `value` tinyint(1) NOT NULL,
  PRIMARY KEY (`net_id`,`task_id`),
  KEY `net_id` (`net_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `pql_can_occurs_fk` FOREIGN KEY (`net_id`) REFERENCES `jbpt_petri_nets` (`id`),
  CONSTRAINT `pql_can_occur_fk` FOREIGN KEY (`task_id`) REFERENCES `pql_tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Temporary table structure for view `pql_index_queue`
--

DROP TABLE IF EXISTS `pql_index_queue`;
/*!50001 DROP VIEW IF EXISTS `pql_index_queue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `pql_index_queue` (
  `id` tinyint NOT NULL
) ENGINE=MyISAM */;
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
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pql_index_status_before_del_tr` BEFORE DELETE ON `pql_index_status`
  FOR EACH ROW
BEGIN
  DELETE FROM pql_can_occur WHERE pql_can_occur.net_id=OLD.net_id;
  DELETE FROM pql_always_occurs WHERE pql_always_occurs.net_id=OLD.net_id;
  DELETE FROM pql_can_conflict WHERE pql_can_conflict.net_id=OLD.net_id;
  DELETE FROM pql_can_cooccur WHERE pql_can_cooccur.net_id=OLD.net_id;
  DELETE FROM pql_total_causal WHERE pql_total_causal.net_id=OLD.net_id;
  DELETE FROM pql_total_concur WHERE pql_total_concur.net_id=OLD.net_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary table structure for view `pql_indexed_ids`
--

DROP TABLE IF EXISTS `pql_indexed_ids`;
/*!50001 DROP VIEW IF EXISTS `pql_indexed_ids`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `pql_indexed_ids` (
  `net_id` tinyint NOT NULL,
  `external_id` tinyint NOT NULL
) ENGINE=MyISAM */;
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pql_tasks_before_del_tr` BEFORE DELETE ON `pql_tasks`
  FOR EACH ROW
BEGIN
  DELETE FROM pql_tasks_sim WHERE pql_tasks_sim.task_id=OLD.id;
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
-- Table structure for table `pql_total_causal`
--

DROP TABLE IF EXISTS `pql_total_causal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_total_causal` (
  `net_id` int(11) unsigned NOT NULL,
  `taskA_id` int(10) unsigned NOT NULL,
  `taskB_id` int(10) unsigned NOT NULL,
  `value` tinyint(1) NOT NULL,
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
-- Table structure for table `pql_total_concur`
--

DROP TABLE IF EXISTS `pql_total_concur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pql_total_concur` (
  `net_id` int(11) unsigned NOT NULL,
  `taskA_id` int(10) unsigned NOT NULL,
  `taskB_id` int(10) unsigned NOT NULL,
  `value` tinyint(1) NOT NULL,
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
-- Final view structure for view `jbpt_unused_labels`
--

/*!50001 DROP TABLE IF EXISTS `jbpt_unused_labels`*/;
/*!50001 DROP VIEW IF EXISTS `jbpt_unused_labels`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `jbpt_unused_labels` AS select `jbpt_labels`.`id` AS `label_id` from `jbpt_labels` where (not(`jbpt_labels`.`id` in (select distinct `jbpt_petri_nodes`.`label_id` from `jbpt_petri_nodes` where (`jbpt_petri_nodes`.`label_id` is not null)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `pql_index_queue`
--

/*!50001 DROP TABLE IF EXISTS `pql_index_queue`*/;
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

/*!50001 DROP TABLE IF EXISTS `pql_indexed_ids`*/;
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

-- Dump completed on 2015-09-26 10:42:28
