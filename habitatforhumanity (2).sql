-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 18, 2017 at 04:26 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `habitatforhumanity`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `productName` varchar(100) NOT NULL,
  `modelNumber` varchar(30) NOT NULL,
  `brand` varchar(30) NOT NULL,
  `color` varchar(15) NOT NULL,
  `price` varchar(10) NOT NULL,
  `itemDemensions` varchar(20) NOT NULL,
  `imageUrl` varchar(200) NOT NULL,
  `category` varchar(30) NOT NULL,
  `quanity` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`productName`, `modelNumber`, `brand`, `color`, `price`, `itemDemensions`, `imageUrl`, `category`, `quanity`) VALUES
('Blue paint', '6666', 'Glidden', 'Blue Sapphire', '35', '15x10', 'images/blue-sapphire-glidden-premium-paint-colors-hdgb53up-01sn-64_400_compressed.jpg', 'Paint', '15'),
('Red Paint', '12345678', 'Bear', 'RED', '50', '20x15x20', 'images/Paint-Can3.png', 'Paint', '10'),
('sofa', '8080', 'macys', 'brown', '1000', '100x200x50', 'images/0312397_PE429728_S5.JPG', 'Furniture', '5'),
('Table ', '5555', 'Bob\'s', 'Brown/Black', '55', '20x55', 'images/download.png', 'Furniture', '25'),
('Table 2', '6666', 'Bob\'s', 'Oak ', '100', '20x50x10', 'images/21149_PE106147_S5.JPG', 'Furniture', '2'),
('Window', '123456', 'WindowDepot', 'White', '150', '20x100x150', 'images/windowImage.jpg', 'Window', '15'),
('Window Bow', '208020', 'windowDepot', 'white', '800', '100x300x500', 'images/bay-or-bow-windows1.png', 'Window', '20'),
('Window2', '1231', 'window', 'blue', '250', '20x50x100', 'images/windowImage.jpg', 'Window', '15');

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  `userName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `streetNumber` varchar(5) NOT NULL,
  `street` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  `zipCode` varchar(5) NOT NULL,
  `userType` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`firstName`, `lastName`, `email`, `phoneNumber`, `userName`, `password`, `streetNumber`, `street`, `city`, `state`, `zipCode`, `userType`) VALUES
('Alvin', 'joseph', 'alvinjoseph48@gmail.com', '6312774255', 'a', 'a', '30', 'st', 'islip ', 'New York', '11751', 'employee'),
('Joseph ', 'jo', 'jo@gmail.com', '123456789', 'b', 'b', '40', 'st st', 'bay shore', 'Idaho', '11706', 'customer'),
('Anthony', 'Joe', 'anthony@gmial.com', '123456789', 'joe', 'joe', '30', 'joe st', 'selden', 'Alaska', '15488', 'customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`productName`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`userName`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
