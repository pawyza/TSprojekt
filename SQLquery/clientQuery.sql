CREATE TABLE `client` (
  `idclient` int(11) NOT NULL AUTO_INCREMENT,
  `forename` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `pesel` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idclient`),
  UNIQUE KEY `pesel_UNIQUE` (`pesel`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
