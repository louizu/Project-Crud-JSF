-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Mar 10, 2024 at 12:13 PM
-- Server version: 5.7.39
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jpabdd`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(10) NOT NULL,
  `nom` varchar(10) DEFAULT NULL,
  `prenom` varchar(10) DEFAULT NULL,
  `sal` int(11) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `nom`, `prenom`, `sal`, `email`) VALUES
(1, 'Doe', 'John', 11000, 'd.john@gmail.com'),
(2, 'Doe', 'Jane', 11000, 'jane.d@gmail.com'),
(3, 'Aiden', 'Jack', 5000, 'aidenJ@gmail.com'),
(49, 'Leister', 'Philip', 7000, 'lphilip@gmail.com'),
(53, 'Crisp', 'Olivia', 6700, 'olivia-crisp@gmail.com'),
(54, 'Brown', 'Georgia', 3000, 'georgia_brown@gmail.com'),
(55, 'Dallas', 'Michael', 4500, 'dallas_m@gmail.com'),
(56, 'Williams', 'Mary', 9800, 'w.j.mary@gmail.com'),
(57, 'Carter', 'Austin', 10000, 'austinC@gmail.com'),
(67, 'Leister', 'Fiona', 7000, 'fiona_l@gmail.com'),
(68, 'Quinnlan', 'Bryce', 12000, 'b.quinn@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
