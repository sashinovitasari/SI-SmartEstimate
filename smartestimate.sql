-- MySQL dump 10.16  Distrib 10.1.21-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.21-MariaDB

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
-- Table structure for table `data_estimasi`
--

DROP TABLE IF EXISTS `data_estimasi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_estimasi` (
  `id_estimasi` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal_penjualan` datetime DEFAULT NULL,
  `id_cabang` varchar(10) DEFAULT NULL,
  `id_user` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_estimasi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_estimasi`
--

LOCK TABLES `data_estimasi` WRITE;
/*!40000 ALTER TABLE `data_estimasi` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_estimasi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_penjualan`
--

DROP TABLE IF EXISTS `data_penjualan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_penjualan` (
  `id_penjualan` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal_penjualan` datetime DEFAULT NULL,
  `id_cabang` varchar(10) DEFAULT NULL,
  `id_user` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_penjualan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_penjualan`
--

LOCK TABLES `data_penjualan` WRITE;
/*!40000 ALTER TABLE `data_penjualan` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_penjualan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_pesanan`
--

DROP TABLE IF EXISTS `data_pesanan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_pesanan` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal_penjualan` datetime DEFAULT NULL,
  `id_cabang` varchar(10) DEFAULT NULL,
  `id_user` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_pesanan`
--

LOCK TABLES `data_pesanan` WRITE;
/*!40000 ALTER TABLE `data_pesanan` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_pesanan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `efek_berita`
--

DROP TABLE IF EXISTS `efek_berita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `efek_berita` (
  `id_berita` int(11) DEFAULT NULL,
  `id_estimasi` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `efek_berita`
--

LOCK TABLES `efek_berita` WRITE;
/*!40000 ALTER TABLE `efek_berita` DISABLE KEYS */;
/*!40000 ALTER TABLE `efek_berita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `efek_penjualan`
--

DROP TABLE IF EXISTS `efek_penjualan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `efek_penjualan` (
  `id_penjualan` int(11) DEFAULT NULL,
  `id_estimasi` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `efek_penjualan`
--

LOCK TABLES `efek_penjualan` WRITE;
/*!40000 ALTER TABLE `efek_penjualan` DISABLE KEYS */;
/*!40000 ALTER TABLE `efek_penjualan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estimasi_produk`
--

DROP TABLE IF EXISTS `estimasi_produk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estimasi_produk` (
  `id_estimasi` int(11) DEFAULT NULL,
  `nama_produk` varchar(30) DEFAULT NULL,
  `jumlah_estimasi` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estimasi_produk`
--

LOCK TABLES `estimasi_produk` WRITE;
/*!40000 ALTER TABLE `estimasi_produk` DISABLE KEYS */;
/*!40000 ALTER TABLE `estimasi_produk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info_berita`
--

DROP TABLE IF EXISTS `info_berita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_berita` (
  `id_berita` int(11) DEFAULT NULL,
  `deskripsi` varchar(300) DEFAULT NULL,
  `kategori` varchar(30) DEFAULT NULL,
  `tanggal_berita` datetime DEFAULT NULL,
  `bobot_berita` int(11) DEFAULT NULL,
  `lokasi` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info_berita`
--

LOCK TABLES `info_berita` WRITE;
/*!40000 ALTER TABLE `info_berita` DISABLE KEYS */;
/*!40000 ALTER TABLE `info_berita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info_cuaca`
--

DROP TABLE IF EXISTS `info_cuaca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_cuaca` (
  `id_estimasi` int(11) DEFAULT NULL,
  `nama_cuaca` varchar(20) DEFAULT NULL,
  `bobot_cuaca` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info_cuaca`
--

LOCK TABLES `info_cuaca` WRITE;
/*!40000 ALTER TABLE `info_cuaca` DISABLE KEYS */;
/*!40000 ALTER TABLE `info_cuaca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penjualan_produk`
--

DROP TABLE IF EXISTS `penjualan_produk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `penjualan_produk` (
  `id_penjualan` int(11) DEFAULT NULL,
  `nama_produk` varchar(30) DEFAULT NULL,
  `jumlah_penjualan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penjualan_produk`
--

LOCK TABLES `penjualan_produk` WRITE;
/*!40000 ALTER TABLE `penjualan_produk` DISABLE KEYS */;
/*!40000 ALTER TABLE `penjualan_produk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pesanan_produk`
--

DROP TABLE IF EXISTS `pesanan_produk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pesanan_produk` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `nama_produk` varchar(30) DEFAULT NULL,
  `jumlah_produk` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pesanan_produk`
--

LOCK TABLES `pesanan_produk` WRITE;
/*!40000 ALTER TABLE `pesanan_produk` DISABLE KEYS */;
/*!40000 ALTER TABLE `pesanan_produk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID_user` varchar(10) DEFAULT NULL,
  `Nama_user` varchar(50) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `login_state` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-09 11:02:26
