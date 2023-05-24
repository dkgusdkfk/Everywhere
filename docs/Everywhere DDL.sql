use enjoytrip;

CREATE TABLE `attraction_description` (
  `content_id` int NOT NULL,
  `homepage` varchar(100) DEFAULT NULL,
  `overview` varchar(10000) DEFAULT NULL,
  `telname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `attraction_detail` (
  `content_id` int NOT NULL,
  `cat1` varchar(3) DEFAULT NULL,
  `cat2` varchar(5) DEFAULT NULL,
  `cat3` varchar(9) DEFAULT NULL,
  `created_time` varchar(14) DEFAULT NULL,
  `modified_time` varchar(14) DEFAULT NULL,
  `booktour` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `attraction_info` (
  `content_id` int NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `addr1` varchar(100) DEFAULT NULL,
  `addr2` varchar(50) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `first_image` varchar(200) DEFAULT NULL,
  `first_image2` varchar(200) DEFAULT NULL,
  `readcount` int DEFAULT NULL,
  `sido_code` int DEFAULT NULL,
  `gugun_code` int DEFAULT NULL,
  `latitude` decimal(20,17) DEFAULT NULL,
  `longitude` decimal(20,17) DEFAULT NULL,
  `mlevel` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  KEY `attraction_to_content_type_id_fk_idx` (`content_type_id`),
  KEY `attraction_to_sido_code_fk_idx` (`sido_code`),
  KEY `attraction_to_gugun_code_fk_idx` (`gugun_code`),
  CONSTRAINT `attraction_to_content_type_id_fk` FOREIGN KEY (`content_type_id`) REFERENCES `content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk` FOREIGN KEY (`gugun_code`) REFERENCES `gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk` FOREIGN KEY (`sido_code`) REFERENCES `sido` (`sido_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `board` (
  `board_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`board_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comment` (
  `board_id` int NOT NULL,
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `content` varchar(200) NOT NULL,
  `register_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `comment_id_UNIQUE` (`comment_id`),
  KEY `board_id_idx` (`board_id`),
  CONSTRAINT `board_id` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `gugun` (
  `gugun_code` int NOT NULL,
  `gugun_name` varchar(30) DEFAULT NULL,
  `sido_code` int NOT NULL,
  PRIMARY KEY (`gugun_code`,`sido_code`),
  KEY `gugun_to_sido_sido_code_fk_idx` (`sido_code`),
  CONSTRAINT `gugun_to_sido_sido_code_fk` FOREIGN KEY (`sido_code`) REFERENCES `sido` (`sido_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `hotplace` (
  `content_id` int NOT NULL,
  `count` int DEFAULT '1',
  PRIMARY KEY (`content_id`),
  CONSTRAINT `hotplace_ibfk_1` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL,
  `hit` int DEFAULT '0',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sido` (
  `sido_code` int NOT NULL,
  `sido_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sido_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `address1` varchar(20) DEFAULT NULL,
  `address2` varchar(20) DEFAULT NULL,
  `token` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `qna` (
  `qna_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `hit` int DEFAULT '0',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `qnacol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`qna_id`),
  KEY `user_id_pk_idx` (`user_id`),
  CONSTRAINT `user_id_pk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `enjoytrip`.`like` (
  `content_id` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  INDEX `content_id_fk_idx` (`content_id` ASC) VISIBLE,
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE `enjoytrip`.`qna_comment` (
  `qna_id` INT NOT NULL,
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `content` VARCHAR(100) NOT NULL,
  `register_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  INDEX `qna_id_pk_idx` (`qna_id` ASC) VISIBLE,
  CONSTRAINT `qna_id_pk`
    FOREIGN KEY (`qna_id`)
    REFERENCES `enjoytrip`.`qna` (`qna_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE `trip_plan` (
  `plan_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `distance` int NOT NULL,
  `cycleTime` int NOT NULL,
  `walkTime` int NOT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `plan_list` (
  `plan_id` int NOT NULL,
  `content_id` int NOT NULL,
  `order` int NOT NULL,
  PRIMARY KEY (`plan_id`),
  CONSTRAINT `plan_id_fk` FOREIGN KEY (`plan_id`) REFERENCES `trip_plan` (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
