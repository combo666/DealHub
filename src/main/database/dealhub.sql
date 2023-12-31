-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 17, 2023 at 01:07 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dealhub`
--

-- --------------------------------------------------------

--
-- Table structure for table `auctionroom`
--

CREATE TABLE `auctionroom` (
  `id` int(255) NOT NULL,
  `roomname` varchar(255) NOT NULL,
  `roomimage` varchar(255) NOT NULL,
  `roomdetails` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `auctionroom`
--

INSERT INTO `auctionroom` (`id`, `roomname`, `roomimage`, `roomdetails`) VALUES
(1, 'Cloths', 'ss.png', '0'),
(2, 'Bookd', 'books.png', '0');

-- --------------------------------------------------------

--
-- Table structure for table `uploadproducts`
--

CREATE TABLE `uploadproducts` (
  `id` int(11) NOT NULL,
  `uploader_id` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `product_cost` varchar(255) NOT NULL,
  `current_bid` varchar(255) NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `end_time` time NOT NULL,
  `product_details` varchar(255) NOT NULL,
  `product_image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `uploadproducts`
--

INSERT INTO `uploadproducts` (`id`, `uploader_id`, `product_name`, `category`, `product_cost`, `current_bid`, `company_name`, `end_time`, `product_details`, `product_image`) VALUES
(3, '011203030', 'Evan', 'Evan', '20000', '0', 'Waliullah', '00:01:23', 'This is a very good product yk!!', 'Screenshot_20230417-102638_Photos.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `userdata`
--

CREATE TABLE `userdata` (
  `id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `contact_number` varchar(255) NOT NULL,
  `profileImage` varchar(50) NOT NULL,
  `newPassword` varchar(255) NOT NULL,
  `confirmPassword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userdata`
--

INSERT INTO `userdata` (`id`, `first_name`, `last_name`, `contact_number`, `profileImage`, `newPassword`, `confirmPassword`) VALUES
('011203030', 'Sahadat', 'Islam', 'Empty', 'Empty', 'asdf', 'asdf');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `auctionroom`
--
ALTER TABLE `auctionroom`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uploadproducts`
--
ALTER TABLE `uploadproducts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userdata`
--
ALTER TABLE `userdata`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `auctionroom`
--
ALTER TABLE `auctionroom`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `uploadproducts`
--
ALTER TABLE `uploadproducts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
