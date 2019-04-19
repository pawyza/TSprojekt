CREATE TABLE `reservation` (
  `idreservation` int(11) NOT NULL AUTO_INCREMENT,
  `car` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  `pickUpDay` datetime NOT NULL,
  `dropOffDay` datetime NOT NULL,
  `totalCost` double NOT NULL,
  PRIMARY KEY (`idreservation`),
  KEY `carfk_idx` (`car`),
  KEY `clientfx_idx` (`client`),
  CONSTRAINT `idcar` FOREIGN KEY (`car`) REFERENCES `car` (`idcar`),
  CONSTRAINT `idclient` FOREIGN KEY (`client`) REFERENCES `client` (`idclient`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
