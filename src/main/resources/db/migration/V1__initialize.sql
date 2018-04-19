-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 19, 2018 at 04:55 PM
-- Server version: 5.6.34-log
-- PHP Version: 7.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `habit-builder-v2`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(52),
(52),
(52),
(52);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `goal` int(11) NOT NULL,
  `is_accomplished` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`task_id`, `goal`, `is_accomplished`, `name`, `user_id`) VALUES
(3, 30, b'0', 'Writing', 1),
(4, 50, b'0', 'Coding', 1),
(9, 10, b'0', 'Bark', 7),
(12, 60, b'0', 'eat', 7),
(14, 10, b'0', 'Journal', 1),
(23, 30, b'0', 'Professional Development', 1),
(25, 10, b'0', 'Chat with Colleg-O-liver', 1),
(29, 30, b'0', 'Talking to Mike', 1),
(44, 10, b'0', 'refactor code', 1),
(47, 10, b'0', 'meow', 45),
(49, 10, b'0', 'Run away', 45);

-- --------------------------------------------------------

--
-- Table structure for table `task_session`
--

CREATE TABLE `task_session` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `length` bigint(20) NOT NULL,
  `length_to_string` varchar(255) DEFAULT NULL,
  `start` time DEFAULT NULL,
  `stop` time DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_session`
--

INSERT INTO `task_session` (`id`, `date`, `length`, `length_to_string`, `start`, `stop`, `task_id`) VALUES
(5, '2018-04-01', 11, '0 minutes, 11 seconds', '02:45:11', '02:45:22', 3),
(6, '2018-04-01', 4, '0 minutes, 4 seconds', '02:45:27', '02:45:31', 4),
(10, '2018-04-01', 5, '0 minutes, 5 seconds', '02:46:43', '02:46:48', 9),
(13, '2018-04-01', 2, '0 minutes, 2 seconds', '02:57:42', '02:57:44', 12),
(15, '2018-04-01', 1, '0 minutes, 1 seconds', '02:58:28', '02:58:29', 14),
(16, '2018-04-02', 9, '0 minutes, 9 seconds', '14:15:27', '14:15:36', 3),
(18, '2018-04-02', 2, '0 minutes, 2 seconds', '14:15:47', '14:15:49', 14),
(20, '2018-04-03', 45, '0 minutes, 45 seconds', '04:09:31', '04:10:16', 4),
(21, '2018-04-03', 3, '0 minutes, 3 seconds', '04:14:05', '04:14:08', 3),
(22, '2018-04-03', 3, '0 minutes, 3 seconds', '04:14:13', '04:14:16', 14),
(24, '2018-04-03', 2, '0 minutes, 2 seconds', '04:15:44', '04:15:46', 23),
(26, '2018-04-03', 5, '0 minutes, 5 seconds', '20:12:40', '20:12:45', 25),
(27, '2018-04-07', 6, '0 minutes, 6 seconds', '03:39:31', '03:39:37', 4),
(28, '2018-04-10', 6, '0 minutes, 6 seconds', '18:28:18', '18:28:24', 3),
(31, '2018-04-11', 4, '0 minutes, 4 seconds', '01:28:59', '01:29:03', 3),
(32, '2018-04-11', 3, '0 minutes, 3 seconds', '01:29:05', '01:29:08', 3),
(33, '2018-04-11', 4, '0 minutes, 4 seconds', '01:29:41', '01:29:45', 3),
(34, '2018-04-11', 11, '0 minutes, 11 seconds', '01:33:00', '01:33:11', 25),
(35, '2018-04-11', 6, '0 minutes, 6 seconds', '01:33:36', '01:33:42', 4),
(36, '2018-04-11', 2, '0 minutes, 2 seconds', '01:34:02', '01:34:04', 4),
(37, '2018-04-11', 19, '0 minutes, 19 seconds', '11:04:20', '11:04:39', 3),
(38, '2018-04-11', 2, '0 minutes, 2 seconds', '17:06:00', '17:06:02', 14),
(39, '2018-04-11', 2, '0 minutes, 2 seconds', '17:06:42', '17:06:44', 14),
(40, '2018-04-11', 5, '0 minutes, 5 seconds', '21:00:51', '21:00:56', 14),
(41, '2018-04-11', 10, '0 minutes, 10 seconds', '21:31:00', '21:31:10', 14),
(42, '2018-04-12', 11, '0 minutes, 11 seconds', '02:24:00', '02:24:11', 3),
(43, '2018-04-13', 5, '0 minutes, 5 seconds', '13:40:22', '13:40:27', 3),
(48, '2018-04-13', 4, '0 minutes, 4 seconds', '13:43:24', '13:43:28', 47),
(50, '2018-04-13', 4, '0 minutes, 4 seconds', '13:43:44', '13:43:48', 49),
(51, '2018-04-13', 7, '0 minutes, 7 seconds', '13:43:59', '13:44:06', 47);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `enabled`, `first_name`, `last_name`, `password`) VALUES
(1, 'jnroman7@gmail.com', 0, 'Jacob', 'Roman', '$2a$10$vPBF/PH79TcMzuOR68S/quzeNGXBsc/TSuNoqM1XORHZvo0vVF0uG'),
(7, 'turk@gmail.com', 0, 'Turk', 'Hayworth', '$2a$10$5nNNpizgwm7lXWlC2Kizcu6RJAGgFf6PI/9uDY6/FCeqgIIgAjyou'),
(45, 'Susan@cat.com', 0, 'Susan', 'theCat', '$2a$10$1LUFA0z.cd/RX0d6o9BfH.Erri1eEYQ5yVAupRfSYWW6YRd.G/bF2');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_role_id`, `role`) VALUES
(2, 'ROLE_USER'),
(8, 'ROLE_USER'),
(46, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `user_user_role`
--

CREATE TABLE `user_user_role` (
  `user_role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_user_role`
--

INSERT INTO `user_user_role` (`user_role_id`, `user_id`) VALUES
(2, 1),
(8, 7),
(46, 45);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `FK2hsytmxysatfvt0p1992cw449` (`user_id`);

--
-- Indexes for table `task_session`
--
ALTER TABLE `task_session`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK15i570tw1pw6h2o0p37ope1pc` (`task_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_role_id`);

--
-- Indexes for table `user_user_role`
--
ALTER TABLE `user_user_role`
  ADD PRIMARY KEY (`user_role_id`,`user_id`),
  ADD KEY `FK2c25owjk4tax6fvm6yptbakvj` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
