CREATE DATABASE  IF NOT EXISTS `proyectopoo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `proyectopoo`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: proyectopoo
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL,
  `nombre_cliente` varchar(50) NOT NULL,
  `edad_cliente` int NOT NULL,
  `direccion` varchar(40) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `correo_electronico` varchar(60) NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (13,'Michael',25,'Soledad','Masculino','3502345','michael@gmail.com'),(14,'Jaime',18,'Barranquilla','Masculino','3002345678','jaime@gmail.com'),(15,'Daniel',19,'Barranquilla','Masculino','3013857464','daniel@gmail.com'),(16,'martin',19,'soledad','masculino','30324563','martinj@gmail.com');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallefactura`
--

DROP TABLE IF EXISTS `detallefactura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallefactura` (
  `idFactura` int DEFAULT NULL,
  `idproductof` int DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `stockfinal` int NOT NULL,
  KEY `idFactura` (`idFactura`),
  KEY `id_producto` (`idproductof`),
  CONSTRAINT `id_producto` FOREIGN KEY (`idproductof`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallefactura`
--

LOCK TABLES `detallefactura` WRITE;
/*!40000 ALTER TABLE `detallefactura` DISABLE KEYS */;
INSERT INTO `detallefactura` VALUES (1,1,'Mouse',2,50000,601),(1,3,'Audifonos',2,300000,794),(1,2,'Teclado',2,80000,390),(2,6,'Portatil',2,60000,103),(3,3,'Audifonos',2,300000,792);
/*!40000 ALTER TABLE `detallefactura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturasdevueltas`
--

DROP TABLE IF EXISTS `facturasdevueltas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturasdevueltas` (
  `idfactv` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `idclient` int DEFAULT NULL,
  `idproducto` int DEFAULT NULL,
  `cant` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturasdevueltas`
--

LOCK TABLES `facturasdevueltas` WRITE;
/*!40000 ALTER TABLE `facturasdevueltas` DISABLE KEYS */;
INSERT INTO `facturasdevueltas` VALUES (678,'2022-06-03',14,2,6);
/*!40000 ALTER TABLE `facturasdevueltas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturav`
--

DROP TABLE IF EXISTS `facturav`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturav` (
  `id_cliente` int DEFAULT NULL,
  `id_vendedor` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `forma_pago` varchar(50) DEFAULT NULL,
  `idfacturav` int NOT NULL,
  PRIMARY KEY (`idfacturav`),
  KEY `id_vendedor` (`id_vendedor`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `id_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`idvendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturav`
--

LOCK TABLES `facturav` WRITE;
/*!40000 ALTER TABLE `facturav` DISABLE KEYS */;
INSERT INTO `facturav` VALUES (14,600,'2022-06-09','Credito',1),(16,602,'2022-06-10','contado',2),(15,601,'2022-06-10','contado',3);
/*!40000 ALTER TABLE `facturav` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `Id_productoi` int DEFAULT NULL,
  `descripcionP` varchar(50) DEFAULT NULL,
  `existencias_iniciales` int DEFAULT NULL,
  `entradas` int DEFAULT NULL,
  `salidas` int DEFAULT NULL,
  `stock` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL,
  `descripcion_producto` varchar(70) NOT NULL,
  `categoria_producto` varchar(70) NOT NULL,
  `precio_unidad` int NOT NULL,
  `existencias_iniciales` int NOT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Mouse','Periferico',25000,45),(2,'Teclado','Periferico',40000,70),(3,'Audifonos','Periferico',150000,130),(4,'Microfono','Periferico',70000,25),(5,'Iphone x ','Celular',1250000,60),(6,'Portatil','Electronico',30000,60);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prove_producto`
--

DROP TABLE IF EXISTS `prove_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prove_producto` (
  `idproducto` int DEFAULT NULL,
  `idproveedor` int DEFAULT NULL,
  `cantidad_productos` int NOT NULL,
  `fecha_registro` date DEFAULT NULL,
  `num_entrada` int NOT NULL,
  `entradas` int NOT NULL,
  `actualizarstock` int NOT NULL,
  PRIMARY KEY (`num_entrada`),
  KEY `producto_prove_producto` (`idproducto`),
  KEY `prove_prove_producto` (`idproveedor`),
  CONSTRAINT `producto_prove_producto` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `prove_prove_producto` FOREIGN KEY (`idproveedor`) REFERENCES `proveedor` (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prove_producto`
--

LOCK TABLES `prove_producto` WRITE;
/*!40000 ALTER TABLE `prove_producto` DISABLE KEYS */;
INSERT INTO `prove_producto` VALUES (2,124,340,'2022-06-18',101,410,390),(3,123,700,'2022-05-09',102,830,792),(4,124,560,'2022-01-12',103,585,576),(5,123,890,'2022-06-07',105,950,950),(6,124,45,'2022-06-10',951,105,103);
/*!40000 ALTER TABLE `prove_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id_proveedor` int NOT NULL,
  `nombre_proveedor` varchar(40) NOT NULL,
  `pagina_web` varchar(40) NOT NULL,
  `telefono_proveedor` varchar(50) NOT NULL,
  `direccion_proveedor` varchar(40) NOT NULL,
  `correo_electronico_proveedor` varchar(40) NOT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (123,'softin','softin.com.co','Barranquilla','3405678','softin@gmail.com'),(124,'caff','caff.com.co','Alemania','3245647','caff@gmail.com');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saldospendientes`
--

DROP TABLE IF EXISTS `saldospendientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saldospendientes` (
  `idclientes` int NOT NULL,
  `nombrec` varchar(50) DEFAULT NULL,
  `saldop` int DEFAULT NULL,
  PRIMARY KEY (`idclientes`),
  CONSTRAINT `idclientessaldos` FOREIGN KEY (`idclientes`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldospendientes`
--

LOCK TABLES `saldospendientes` WRITE;
/*!40000 ALTER TABLE `saldospendientes` DISABLE KEYS */;
INSERT INTO `saldospendientes` VALUES (13,'Michael',143000),(14,'Jaime',516000);
/*!40000 ALTER TABLE `saldospendientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendedor` (
  `idvendedor` int NOT NULL,
  `nombre_vendedor` varchar(30) NOT NULL,
  `telefono_vendedor` varchar(50) NOT NULL,
  PRIMARY KEY (`idvendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (600,'Daniel ','3134356798'),(601,'jaimito el cartero','3409800'),(602,'Samuel','3452332'),(603,'Jeus','3104567890');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-23 10:24:25
