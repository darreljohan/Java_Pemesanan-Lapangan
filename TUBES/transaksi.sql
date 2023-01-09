-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2023 at 11:13 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbotubes`
--

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(30) NOT NULL,
  `user_id` varchar(60) NOT NULL,
  `lapangan` varchar(60) NOT NULL,
  `jam_mulai` datetime NOT NULL,
  `jam_selesai` datetime NOT NULL,
  `total_harga` int(10) NOT NULL,
  `media_pembayaran` varchar(50) NOT NULL,
  `status_pembayaran` tinyint(1) NOT NULL COMMENT 'TRUE = LUNAS\r\nFALSE = Pembayarnan muka'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `user_id`, `lapangan`, `jam_mulai`, `jam_selesai`, `total_harga`, `media_pembayaran`, `status_pembayaran`) VALUES
(1, 'akunbaru1', 'lap01', '2023-01-07 10:00:00', '2023-01-07 11:00:00', 150000, 'QRIS', 1),
(2, 'akunbaru1', 'lap01', '2023-01-07 13:00:00', '2023-01-07 15:00:00', 300000, 'QRIS', 1),
(3, 'akunbaru1', 'lap01', '2023-01-07 18:00:00', '2023-01-07 20:00:00', 300000, 'QRIS', 1),
(4, 'akuncoba1', 'lap01', '2023-01-08 12:00:00', '2023-01-08 15:00:00', 450000, 'BCA', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
