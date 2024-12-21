-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.36 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for stt_40_maiquoctruong_21005711
CREATE DATABASE IF NOT EXISTS `stt_40_maiquoctruong_21005711` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `stt_40_maiquoctruong_21005711`;

-- Dumping structure for table stt_40_maiquoctruong_21005711.flights
CREATE TABLE IF NOT EXISTS `flights` (
  `arrival_date` date NOT NULL,
  `departure_date` date NOT NULL,
  `flight_status` bit(1) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `seat_fare` double NOT NULL,
  `airplane_name` varchar(255) NOT NULL,
  `arrival_airport` varchar(255) NOT NULL,
  `departure_airport` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table stt_40_maiquoctruong_21005711.flights: ~6 rows (approximately)
INSERT INTO `flights` (`arrival_date`, `departure_date`, `flight_status`, `id`, `seat_fare`, `airplane_name`, `arrival_airport`, `departure_airport`) VALUES
	('2024-12-27', '2024-12-26', b'0', 1, 1000000, 'Airbus 0001', 'Noi Bai', 'Tan Son Nhat'),
	('2024-12-28', '2024-12-27', b'0', 2, 1500000, 'Airbus 0002', 'Can Tho', 'Cam Ranh'),
	('2024-12-28', '2024-12-27', b'1', 3, 1750000, 'Boeing 0001', 'Noi Bai', 'Tan Son Nhat'),
	('2024-12-23', '2024-12-22', b'1', 4, 1000000, 'Airbus 0003', 'Cam Ranh', 'Noi Bai'),
	('2024-12-25', '2024-12-24', b'0', 5, 1000000, 'Airbus 0003', 'Ha Noi', 'Tan Son Nhat'),
	('2024-12-24', '2024-12-23', b'1', 6, 1750000, 'Boeing 0002', 'Can Tho', 'Da Nang');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
