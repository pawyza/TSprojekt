CREATE TABLE `car` (
  `idcar` int(11) NOT NULL AUTO_INCREMENT,
  `registrationNumber` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `perDayCost` double NOT NULL,
  PRIMARY KEY (`idcar`),
  UNIQUE KEY `id_UNIQUE` (`idcar`),
  UNIQUE KEY `registrationNumber_UNIQUE` (`registrationNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
