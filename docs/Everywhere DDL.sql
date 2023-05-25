create database everywhere;
use everywhere;

CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `address1` varchar(20) DEFAULT NULL,
  `address2` varchar(20) DEFAULT NULL,
  `token` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `trip_plan` (
  `plan_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `title` varchar(100) NOT NULL,
  `distance` int NOT NULL,
  `cycle_time` int NOT NULL,
  `walk_time` int NOT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `plan_list` (
  `plan_id` int NOT NULL,
  `content_id` int NOT NULL,
  `sequence` int NOT NULL,
  PRIMARY KEY (`plan_id`,`sequence`),
  CONSTRAINT `plan_id_fk` FOREIGN KEY (`plan_id`) REFERENCES `trip_plan` (`plan_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `board` (
  `board_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `plan_id` int DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `board_ibfk_1` (`user_id`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `board_idfk_1` (`plan_id`),
  CONSTRAINT `board_idfk_1` FOREIGN KEY (`plan_id`) REFERENCES `trip_plan` (`plan_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `comment` (
  `board_id` int NOT NULL,
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `content` varchar(200) NOT NULL,
  `register_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `comment_id_UNIQUE` (`comment_id`),
  KEY `board_id_idx` (`board_id`),
  CONSTRAINT `board_id` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `hotplace` (
  `content_id` int NOT NULL,
  `count` int DEFAULT 1,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `hotplace_ibfk_1` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `likelist` (
  `content_id` int NOT NULL,
  `user_id` varchar(20) NOT NULL,
  KEY `content_id_fk_idx` (`content_id`),
  KEY `user_id_fk_idx` (`user_id`),
  CONSTRAINT `content_id_fk` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL,
  `hit` int DEFAULT 0,
  `register_time` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `qna` (
  `qna_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `hit` int DEFAULT 0,
  `register_time` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`qna_id`),
  KEY `user_id_pk_idx` (`user_id`),
  CONSTRAINT `user_id_pk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `qna_comment` (
  `qna_id` int NOT NULL,
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `content` varchar(100) NOT NULL,
  `register_time` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`comment_id`),
  KEY `qna_id_pk_idx` (`qna_id`),
  CONSTRAINT `qna_id_pk` FOREIGN KEY (`qna_id`) REFERENCES `qna` (`qna_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
