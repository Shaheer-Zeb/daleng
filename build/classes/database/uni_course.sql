-- MySQL dump 10.13  Distrib 8.0.42, for Linux (x86_64)
--
-- Host: localhost    Database: uni_course
-- ------------------------------------------------------
-- Server version	8.0.42-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `academic_term`
--

DROP TABLE IF EXISTS `academic_term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academic_term` (
  `term_id` int NOT NULL AUTO_INCREMENT,
  `term_code` varchar(10) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`term_id`),
  UNIQUE KEY `term_code` (`term_code`),
  CONSTRAINT `chk_term_dates` CHECK ((`end_date` > `start_date`))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_term`
--

LOCK TABLES `academic_term` WRITE;
/*!40000 ALTER TABLE `academic_term` DISABLE KEYS */;
INSERT INTO `academic_term` VALUES (1,'FA26','2026-09-01','2027-01-31'),(2,'SP27','2027-02-01','2027-06-30');
/*!40000 ALTER TABLE `academic_term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_code` varchar(20) NOT NULL,
  `course_name` varchar(50) NOT NULL,
  `credit_hours` decimal(3,1) NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_code` (`course_code`),
  CONSTRAINT `chk_credit_hours` CHECK ((`credit_hours` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'CS201','Data Structures',3.0),(2,'CS301','Database Systems',3.0),(3,'CS302','Operating Systems',3.0),(4,'CS303','Computer Networks',3.0),(5,'CS401','Software Engineering',3.0),(6,'CSC100','BlaBla',4.0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_offering`
--

DROP TABLE IF EXISTS `course_offering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_offering` (
  `offering_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `term_id` int NOT NULL,
  `teacher_id` int DEFAULT NULL,
  `section_name` varchar(10) NOT NULL,
  PRIMARY KEY (`offering_id`),
  UNIQUE KEY `uq_course_offering` (`course_id`,`term_id`,`section_name`),
  UNIQUE KEY `uq_offering_course` (`offering_id`,`course_id`),
  KEY `fk_offering_term` (`term_id`),
  KEY `fk_offering_teacher` (`teacher_id`),
  CONSTRAINT `fk_offering_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `fk_offering_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `fk_offering_term` FOREIGN KEY (`term_id`) REFERENCES `academic_term` (`term_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_offering`
--

LOCK TABLES `course_offering` WRITE;
/*!40000 ALTER TABLE `course_offering` DISABLE KEYS */;
INSERT INTO `course_offering` VALUES (1,1,1,1,'A'),(2,1,1,2,'B'),(3,2,1,2,'A'),(4,2,1,3,'B'),(5,3,1,1,'A'),(6,3,1,3,'B'),(7,4,1,2,'A'),(8,4,1,1,'B');
/*!40000 ALTER TABLE `course_offering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_scheme`
--

DROP TABLE IF EXISTS `course_scheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_scheme` (
  `scheme_id` int NOT NULL AUTO_INCREMENT,
  `program_id` int NOT NULL,
  `term_id` int NOT NULL,
  `scheme_name` varchar(50) NOT NULL,
  PRIMARY KEY (`scheme_id`),
  UNIQUE KEY `uq_scheme` (`program_id`,`term_id`),
  KEY `fk_scheme_term` (`term_id`),
  CONSTRAINT `fk_scheme_program` FOREIGN KEY (`program_id`) REFERENCES `program` (`program_id`),
  CONSTRAINT `fk_scheme_term` FOREIGN KEY (`term_id`) REFERENCES `academic_term` (`term_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_scheme`
--

LOCK TABLES `course_scheme` WRITE;
/*!40000 ALTER TABLE `course_scheme` DISABLE KEYS */;
INSERT INTO `course_scheme` VALUES (1,1,1,'BSCS Fall 2026 Scheme');
/*!40000 ALTER TABLE `course_scheme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) NOT NULL,
  PRIMARY KEY (`department_id`),
  UNIQUE KEY `department_name` (`department_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Computer Science'),(2,'Electrical Engineering'),(3,'Humanities');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offering_schedule`
--

DROP TABLE IF EXISTS `offering_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offering_schedule` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `offering_id` int NOT NULL,
  `time_slot_id` int NOT NULL,
  `day_of_week` enum('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday') NOT NULL,
  PRIMARY KEY (`schedule_id`),
  UNIQUE KEY `uq_offering_schedule` (`offering_id`,`day_of_week`,`time_slot_id`),
  KEY `fk_schedule_time_slot` (`time_slot_id`),
  CONSTRAINT `fk_schedule_offering` FOREIGN KEY (`offering_id`) REFERENCES `course_offering` (`offering_id`),
  CONSTRAINT `fk_schedule_time_slot` FOREIGN KEY (`time_slot_id`) REFERENCES `time_slot` (`time_slot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offering_schedule`
--

LOCK TABLES `offering_schedule` WRITE;
/*!40000 ALTER TABLE `offering_schedule` DISABLE KEYS */;
INSERT INTO `offering_schedule` VALUES (1,1,1,'Monday'),(2,2,3,'Tuesday'),(3,3,2,'Monday'),(4,4,1,'Wednesday'),(5,5,1,'Monday'),(6,6,4,'Thursday'),(7,7,2,'Tuesday'),(8,8,1,'Friday');
/*!40000 ALTER TABLE `offering_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program` (
  `program_id` int NOT NULL AUTO_INCREMENT,
  `department_id` int NOT NULL,
  `program_name` varchar(50) NOT NULL,
  PRIMARY KEY (`program_id`),
  UNIQUE KEY `uq_program` (`department_id`,`program_id`),
  CONSTRAINT `fk_program_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES (1,1,'BS Computer Science'),(2,1,'BS Artifical Intelligence'),(3,2,'BS Electrical Engineering'),(4,1,'BlaBla');
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheme_course`
--

DROP TABLE IF EXISTS `scheme_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scheme_course` (
  `scheme_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`scheme_id`,`course_id`),
  KEY `fk_scheme_course_course` (`course_id`),
  CONSTRAINT `fk_scheme_course_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `fk_scheme_course_scheme` FOREIGN KEY (`scheme_id`) REFERENCES `course_scheme` (`scheme_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheme_course`
--

LOCK TABLES `scheme_course` WRITE;
/*!40000 ALTER TABLE `scheme_course` DISABLE KEYS */;
INSERT INTO `scheme_course` VALUES (1,1),(1,2),(1,3),(1,4),(1,5);
/*!40000 ALTER TABLE `scheme_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selected_course`
--

DROP TABLE IF EXISTS `selected_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selected_course` (
  `selected_course_id` int NOT NULL AUTO_INCREMENT,
  `selection_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`selected_course_id`),
  UNIQUE KEY `uq_selected_course` (`selection_id`,`course_id`),
  UNIQUE KEY `uq_selected_course_pair` (`selected_course_id`,`course_id`),
  KEY `fk_selected_course_course` (`course_id`),
  CONSTRAINT `fk_selected_course_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `fk_selected_course_selection` FOREIGN KEY (`selection_id`) REFERENCES `student_selection` (`selection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selected_course`
--

LOCK TABLES `selected_course` WRITE;
/*!40000 ALTER TABLE `selected_course` DISABLE KEYS */;
INSERT INTO `selected_course` VALUES (1,1,1),(2,1,2),(3,1,3);
/*!40000 ALTER TABLE `selected_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selected_offering`
--

DROP TABLE IF EXISTS `selected_offering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selected_offering` (
  `selected_offering_id` int NOT NULL AUTO_INCREMENT,
  `selected_course_id` int NOT NULL,
  `course_id` int NOT NULL,
  `offering_id` int NOT NULL,
  `selection_source` enum('RECOMMENDED','MANUAL') NOT NULL,
  `selected_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`selected_offering_id`),
  UNIQUE KEY `uq_one_offering_per_course` (`selected_course_id`),
  KEY `fk_selected_offering_course` (`selected_course_id`,`course_id`),
  KEY `fk_selected_offering_offering` (`offering_id`,`course_id`),
  CONSTRAINT `fk_selected_offering_offering` FOREIGN KEY (`offering_id`, `course_id`) REFERENCES `course_offering` (`offering_id`, `course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selected_offering`
--

LOCK TABLES `selected_offering` WRITE;
/*!40000 ALTER TABLE `selected_offering` DISABLE KEYS */;
INSERT INTO `selected_offering` VALUES (1,1,1,1,'RECOMMENDED','2026-09-22 15:32:02'),(2,2,2,4,'RECOMMENDED','2026-09-22 15:32:02'),(3,3,3,6,'RECOMMENDED','2026-09-22 15:32:02');
/*!40000 ALTER TABLE `selected_offering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `reg_no` varchar(30) NOT NULL,
  `student_name` varchar(50) NOT NULL,
  `program_id` int NOT NULL,
  `cohort_code` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `reg_no` (`reg_no`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_student_program` (`program_id`),
  CONSTRAINT `fk_student_program` FOREIGN KEY (`program_id`) REFERENCES `program` (`program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'FA25-BCS-093','Shaheer Zeb Khan',1,'FA25','demo@demo.com','BlaBla'),(3,'FA25-BCS-000','Demo',1,'FA25','bla@demo.com','028927a047a043d76ab161dbacb414cfd0aedcd84aab280fadda48e1ed677dc4'),(4,'FA25-BCS-339','Demo',1,'FA25','demo@blabla.com','a08c47f3f0cb9954433a581d1af91b126eb605ace3359ed5e1e055bdab46c17b');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_selection`
--

DROP TABLE IF EXISTS `student_selection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_selection` (
  `selection_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `term_id` int NOT NULL,
  PRIMARY KEY (`selection_id`),
  KEY `fk_selection_student` (`student_id`),
  KEY `fk_selection_term` (`term_id`),
  CONSTRAINT `fk_selection_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `fk_selection_term` FOREIGN KEY (`term_id`) REFERENCES `academic_term` (`term_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_selection`
--

LOCK TABLES `student_selection` WRITE;
/*!40000 ALTER TABLE `student_selection` DISABLE KEYS */;
INSERT INTO `student_selection` VALUES (1,1,1);
/*!40000 ALTER TABLE `student_selection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `teacher_id` int NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(50) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Dr. Ahmed'),(2,'Dr. Fatima'),(3,'Dr. Hassan');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_slot`
--

DROP TABLE IF EXISTS `time_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_slot` (
  `time_slot_id` int NOT NULL AUTO_INCREMENT,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`time_slot_id`),
  UNIQUE KEY `uq_time_slot` (`start_time`,`end_time`),
  CONSTRAINT `chk_time_slot` CHECK ((`end_time` > `start_time`))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_slot`
--

LOCK TABLES `time_slot` WRITE;
/*!40000 ALTER TABLE `time_slot` DISABLE KEYS */;
INSERT INTO `time_slot` VALUES (1,'09:00:00','10:30:00'),(2,'10:30:00','12:00:00'),(3,'12:00:00','13:30:00'),(4,'13:30:00','15:00:00');
/*!40000 ALTER TABLE `time_slot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-17 12:07:40
