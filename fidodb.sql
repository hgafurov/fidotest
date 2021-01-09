-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.22 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Дамп структуры базы данных fidodb
CREATE DATABASE IF NOT EXISTS `fidodb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fidodb`;

-- Дамп структуры для таблица fidodb.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы fidodb.roles: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`, `status`, `created`, `updated`) VALUES
	(1, 'ROLE_ADMIN', 'ACTIVE', '2021-08-01 21:00:00.000000', '2021-08-01 21:00:00.000000'),
	(2, 'ROLE_USER', 'ACTIVE', '2021-08-01 21:00:00.000000', '2021-08-01 21:00:00.000000');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Дамп структуры для таблица fidodb.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `familiya` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `imya` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы fidodb.users: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `login`, `password`, `familiya`, `imya`, `email`, `status`, `created`, `updated`) VALUES
	(1, 'user1', '$2a$04$P.jikYMfWrqZX01YVQv7X.R9DaupDshVBj6tnyFyNQKX9ovFct9iS', 'Aliev', 'Ali', 'ali@test.us', 'ACTIVE', '2021-08-01 21:00:00.000000', '2021-08-01 21:00:00.000000'),
	(4, 'user4', '$2a$10$wBfY1CNfWFGmpvQNRUqqauZRr1/fAnJ9/Zx6TaB2QiFV.KdB7WlCW', 'Ahmedova', 'Alisa', 'alisa@mail.uuz', 'ACTIVE', '2021-01-08 22:02:53.821000', '2021-01-08 22:02:53.821000'),
	(5, 'user5', '$2a$10$xU6ZS.cdUzHujzYa8HpJeO7SufHYNyQ1qJt5l3rPSKHj5do5hui6u', 'Inanov', 'Ivan', 'ivan@mail.uuz', 'ACTIVE', '2021-01-08 22:07:36.090000', '2021-01-08 22:07:36.090000'),
	(9, 'user6', '$2a$10$fyRc7gUE32kUzCoAJfUdweAUIksCOZeWLyAtWBqtad8u/nVSctwJq', 'Inanov', 'Ivan', 'ivan@mail.uuz', 'ACTIVE', '2021-01-09 01:45:49.436000', '2021-01-09 01:45:49.436000'),
	(10, 'user7', '$2a$10$xWgyti2BXJZ3c0t9tYifmeNvHgnK2rWIx6KE5x0p3SkANruxZdoyu', 'Inanov1', 'Ivan1', 'ivan1@mail.uuz', 'ACTIVE', '2021-01-09 03:37:49.902000', '2021-01-09 03:37:49.902000'),
	(11, 'user9', '$2a$10$NtO7iiULZw1W10zC2/HOGusWAbbiEjIdwwYCMRetZRcvNzQ61.i2q', 'Inanov12', 'Ivan12', 'ivan12@mail.uuz', 'ACTIVE', '2021-01-09 04:57:10.097000', '2021-01-09 04:57:10.097000');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Дамп структуры для таблица fidodb.users_roles
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы fidodb.users_roles: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
	(1, 1),
	(1, 2),
	(4, 2),
	(5, 2),
	(9, 2),
	(10, 2),
	(11, 2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
