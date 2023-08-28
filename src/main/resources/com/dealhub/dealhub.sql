-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 28, 2023 at 05:53 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

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
(11, 'T-Shirts', 'T-shirts.png', 'T-shirts for all'),
(12, 'Books', 'books.png', 'Book'),
(13, 'Shoes', 'shoe.png', 'shoes');

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
  `auction_status` varchar(255) NOT NULL,
  `pending_status` varchar(255) NOT NULL,
  `product_details` varchar(255) NOT NULL,
  `product_image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `uploadproducts`
--

INSERT INTO `uploadproducts` (`id`, `uploader_id`, `product_name`, `category`, `product_cost`, `current_bid`, `company_name`, `end_time`, `auction_status`, `pending_status`, `product_details`, `product_image`) VALUES
(15, '011203030', 'Grey T-Shirt', 'T-Shirts', '150', '0', 'BurBerry', '09:40:12', 'yes', 'yes', 'This t-shirt is too good and wear to comfortable', 'Grey.png'),
(16, '011203033', 'White', 'T-Shirts', '200', '0', 'Puma', '10:02:29', 'yes', 'yes', 'Good product', 'White.png'),
(17, '011203666', 'Black', 'T-Shirts', '120', '0', 'Nick', '13:03:24', 'yes', 'yes', 'Best product', 'Black.png'),
(18, '011203666', 'Everything I Never Told You', 'Books', '150', '0', 'Jrr', '09:48:03', 'yes', 'yes', 'Best seller book', 'everything.png'),
(19, '011203033', 'The Lord Of The Rings', 'Books', '200', '0', 'jrr', '08:19:36', 'yes', 'yes', 'Best seller book', 'Lord.png'),
(20, '011203666', 'The Nature Of Middle Earth', 'Books', '300', '0', 'Jrr', '08:31:14', 'yes', 'yes', 'Best seller book', 'JRR.png'),
(21, '011203030', 'White shoe', 'Shoes', '1000', '0', 'Adidas', '08:26:40', 'yes', 'yes', 'Best seller shoe', 'White.png'),
(22, '011203033', 'Formal', 'Shoes', '1500', '0', 'Nick', '08:27:20', 'yes', 'yes', 'Best seller shoe', 'Formal.png');

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
  `confirmPassword` varchar(255) NOT NULL,
  `user_balance` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userdata`
--

INSERT INTO `userdata` (`id`, `first_name`, `last_name`, `contact_number`, `profileImage`, `newPassword`, `confirmPassword`, `user_balance`) VALUES
('011203030', 'Sahadat', 'Islam', '0102210', 'Empty', 'asdf', 'asdf', '24776'),
('011203033', 'Aurthohin', 'Shironamhin', '017***********', 'Empty', 'asdf', 'asdf', '0'),
('011203666', 'Domination', 'pentara', 'Empty', 'Empty', 'asdf', 'asdf', '0');

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
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `uploadproducts`
--
ALTER TABLE `uploadproducts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
