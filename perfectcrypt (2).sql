-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2017 at 10:13 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perfectcrypt`
--

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `branch_id` int(20) NOT NULL,
  `branch_name` varchar(255) NOT NULL,
  `tel_no` varchar(30) NOT NULL,
  `address` text NOT NULL,
  `status` varchar(30) NOT NULL,
  `create_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`branch_id`, `branch_name`, `tel_no`, `address`, `status`, `create_by`) VALUES
(2, 'Fakulti Teknologi Maklumat Komunikasi (FTMK)', '0655520000', 'Hang Tuah Jaya, 76100 Durian Tunggal, Melaka, Malaysia', 'Active', 'budie46'),
(3, 'Fakulti Teknologi Kejuruteraan (FTK), ', '60 6062346528', 'Taman Tasik Utama, 75450 Ayer Keroh, Malacca, Malaysia', 'Active', 'budie46');

-- --------------------------------------------------------

--
-- Table structure for table `data_info`
--

CREATE TABLE `data_info` (
  `idData` int(30) NOT NULL,
  `method` varchar(30) NOT NULL,
  `path` varchar(255) NOT NULL,
  `user_id` int(30) NOT NULL,
  `digital_signature` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data_info`
--

INSERT INTO `data_info` (`idData`, `method`, `path`, `user_id`, `digital_signature`) VALUES
(1, 'AES-128', 'file/budie46/2017-04-28 16-08-59/Type of Losses in Fiber Optic.jpg', 20, ''),
(2, 'AES-128', 'file/budie46/2017-04-28 16-19-39/B031520015 - MUHAMAD BUDIE BIN BASRI.docx', 20, ''),
(3, 'AES-128', 'file/budie46/2017-04-28 16-19-54/Simple_Resume_Vol1.doc', 20, ''),
(4, 'AES-128', 'file/shai46/2017-04-28 16-26-26/8Lojk3c.jpg', 19, ''),
(5, 'AES-128', 'file/shai46/2017-04-28 16-27-12/Q&A.docx', 19, ''),
(6, 'AES-128', 'file/shai46/2017-05-01 17-58-26/Naruto.mp4', 19, ''),
(7, 'AES-256', 'file/budie46/2017-05-02 04-58-20/Nightcore - DelightRei Yasuda-gjYj3qBQ5bA.mp4', 20, ''),
(8, 'AES-256', 'file/budie46/2017-05-02 05-04-56/sa.pdf', 20, ''),
(9, 'AES-256', 'file/shai46/2017-05-02 05-25-26/Nightcore - Story Kana Nishino-ZvsOqcHoQL8.mp4', 19, ''),
(10, 'AES-256', 'file/shai46/2017-05-02 05-34-33/WORKSHOP-2-Group-14-Final-Report.docx', 19, ''),
(11, 'AES-256', 'file/shai46/2017-05-02 05-39-11/Nightcore - Story Kana Nishino-ZvsOqcHoQL8.mp4', 19, ''),
(12, 'AES-256', 'file/shai46/2017-05-02 05-41-03/1.PNG', 19, ''),
(13, 'AES-256', 'file/shai46/2017-05-02 05-45-22/File Test.txt', 19, ''),
(14, 'AES-256', 'file/budie46/2017-05-02 13-37-00/2222.PNG', 20, ''),
(15, 'AES-128', 'file/budie46/2017-05-02 13-38-01/Fruit.java', 20, ''),
(16, 'AES-256', 'file/shai46/2017-05-02 13-48-49/WORKSHOP-2-Group-14-Final-Report.docx', 19, ''),
(17, 'AES-128', 'file/shai46/2017-05-02 13-49-49/Resume.pdf', 19, ''),
(18, 'AES-128', 'file/shai46/2017-05-02 13-51-04/islam_wallpaper_by_themuslimelement-d37bqm8.jpg', 19, ''),
(19, 'AES-128', 'file/shai46/2017-05-02 13-56-07/CIS000000.jsp', 19, ''),
(20, 'AES-256', 'file/shai46/2017-05-02 13-56-37/CIS020000.jsp', 19, ''),
(21, 'AES-128', 'file/shai46/2017-05-02 14-07-59/awesome gundam wallpaper by the durrrrian (1).jpg', 19, ''),
(22, 'AES-256', 'file/shai46/2017-05-02 14-16-40/Conn.java', 19, ''),
(23, 'AES-256', 'file/budie46/2017-05-02 17-07-26/Nightcore - I&U Rei Yasuda-DN9BFxhbJP8.mp4', 20, ''),
(24, 'AES-192', 'file/budie46/2017-05-03 04-26-10/41695560.png', 20, ''),
(25, 'DES', 'file/budie46/2017-05-03 06-05-03/miyamoto-musashi-saber-fate-grand-order-katana-blonde-smiling-anime-12612.jpg', 20, ''),
(26, 'DES', 'file/budie46/2017-05-03 16-43-28/CIS000000.jsp', 20, ''),
(27, 'DES', 'file/budie46/2017-05-03 16-50-06/index.html', 20, ''),
(28, 'DES', 'file/budie46/2017-05-03 17-01-26/hgignore_global.txt', 20, ''),
(29, 'DES', 'file/shai46/2017-05-03 17-47-25/gitignore_global.txt', 19, ''),
(30, 'Triple DES', 'file/shai46/2017-05-03 23-24-55/SET A AS.docx', 19, ''),
(31, 'Blowfish', 'file/budie46/2017-05-04 03-54-22/WORKSHOP-2-Group-14-Final-Report.pdf', 20, ''),
(32, 'AES-256', 'file/shai46/2017-05-05 14-20-47/Final Resume.pdf', 19, ''),
(33, 'AES-128', 'file/budie46/2017-05-05 16-01-03/muz.mp4', 20, ''),
(34, 'Triple DES', 'file/shai46/2017-05-24 04-55-47/m_23.eps', 19, ''),
(35, 'AES-256', 'file/frzfbl/2017-05-24 05-00-01/Sakura.Saber.full.2072348.jpg', 23, ''),
(36, 'AES-256', 'file/frzfbl/2017-05-24 05-00-01/Sakura.Saber.full.2072348.jpg', 23, ''),
(37, 'AES-192', 'file/frzfbl/2017-05-24 05-01-02/thumb-1920-688218.jpg', 23, ''),
(38, 'AES-128', 'file/frzfbl/2017-05-24 05-02-36/WP_Kylo_Ren-2560x1440_00330.jpg', 23, ''),
(39, 'Blowfish', 'file/frzfbl/2017-05-24 05-05-44/07 Viva la Vida.m4a', 23, ''),
(40, 'Triple DES', 'file/frzfbl/2017-05-24 05-06-55/07 Viva la Vida.m4a', 23, ''),
(41, 'AES-256', 'file/frzfbl/2017-05-24 05-09-35/07 Viva la Vida.m4a', 23, ''),
(42, 'AES-256', 'file/daen21/2017-05-24 09-05-34/CIS000000.jsp', 25, ''),
(43, 'AES-256', 'file/shai46/2017-05-24 12-15-09/07 Viva la Vida.m4a', 19, ''),
(44, 'AES-256', 'file/neva21/2017-05-29 07-50-42/5.PNG', 2, ''),
(45, 'AES-256', 'file/neva21/2017-05-29 07-50-42/5.PNG', 2, ''),
(46, 'AES-128', 'file/muz21/2017-05-29 13-57-46/thumb-1920-688218.jpg', 3, '[B@7daaea02'),
(47, 'AES-256', 'file/neva21/2017-05-29 14-02-29/thumb-1920-688218.jpg', 2, '[B@1c9c8636');

-- --------------------------------------------------------

--
-- Table structure for table `pc_adm_users`
--

CREATE TABLE `pc_adm_users` (
  `user_id` int(10) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` text NOT NULL,
  `full_name` varchar(200) NOT NULL,
  `ic_new` varchar(30) NOT NULL,
  `phone_no` varchar(30) NOT NULL,
  `email` varchar(200) NOT NULL,
  `address` text NOT NULL,
  `public_key` text NOT NULL,
  `e_private_key` text NOT NULL,
  `certificate` text,
  `type` varchar(30) NOT NULL,
  `branch_id` int(30) NOT NULL,
  `status` varchar(30) NOT NULL DEFAULT 'offline'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pc_adm_users`
--

INSERT INTO `pc_adm_users` (`user_id`, `username`, `password`, `full_name`, `ic_new`, `phone_no`, `email`, `address`, `public_key`, `e_private_key`, `certificate`, `type`, `branch_id`, `status`) VALUES
(1, 'budie', '1000:5b42403531363339633232:1780c9fb04a308330966b34aedad2b061bbca2f5ae9fe125af4845b41281b24b8fee37bac8e7a171e34294ba84237ff6a28563a12a46275c78217750b13c6e7e', 'MUHAMAD BUDIE BIN BASRI', '950405025185', '0175757018', 'budiebasri@gmail.com', 'ALAMAT RUMAH SEWA\r\nNo 94, Jalan TU3, Taman Tasik Utama, Melaka-Ayer Keroh-75450', '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', NULL, 'STAFF', 0, 'offline'),
(2, 'neva21', '1000:5b42403662356334383166:c41e234a7de19c25aaba5304462e6c0d29d06952c70cdf2bc9d4b777f64ac42f906d73d177819b08d15e4864c265426f96e755ef4c35fa9ab8f4aa51853cd68c', 'Rebecca L. Martin', '7401241242134', '267-607-5611', 'NevaACullins@armyspy.com', '4903 Spring Avenue\r\nNorristown, PA 19403', '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', NULL, 'STAFF', 0, 'offline'),
(3, 'muz21', '1000:5b42403466323465613930:67775dc79336f6a9b1659228c7e0a3751e1c4fc05230842eb8fae804ed7eda952d622b76f1a408a8606114e3762b1c48ee722e115dcbb7bae8617955ccbce179', 'MUZZAQIR AFFAN BIN ZULKIFLI', '93010486572305', '0964852176', 'muz21@gmail.com', 'fasdfasfgqwwaerfqw', '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', NULL, 'STAFF', 0, 'offline');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `ic` varchar(30) NOT NULL,
  `phone_no` varchar(30) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `public_key` text,
  `status` varchar(30) DEFAULT '0',
  `type` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `full_name`, `ic`, `phone_no`, `email`, `address`, `public_key`, `status`, `type`) VALUES
(19, 'shai46', '1000:5b42403466396266313133:ff9c17a4d32283d4ab8382cf99c2d1ed293770c18851df9a23467f8bf7a20aed26e2fa8eacd7c6c167e4dcc31e6bff0519310832d6ecd3978956e8fec7d9b3e5', 'Shai Ton', '987654312214', '0147854621', 'shay@ton.com', 'Bilik 666, Apartment N64,,Neraka Tingkat 7,66666 Neraka Jahanam', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDQI1xxG3lZLx8Nn9vwfo6lx/hiOzuiWnSamKtDjAw22+JSadCqxIvV+lI+DFLdhDq7WCmoU0G8te0/J38FEBTiUjUAjQvB+39CzMwi8jPC/vTvkW3YC4Ca+dQSJ/WbmxpRELvg570DO+VE2J7gFD4rWlRxDJDlIHEVbRdySguOQIDAQAB', '0', 'STAFF'),
(20, 'budie46', '1000:5b42403766366464313463:c2f0ff5227e19352b08193eb426ed52e913a35da8817009826fb76836e299cbb69abad5f73d0f23d59ea55fa97c91c38c82d2b623e8042f551f74a529419826e', 'MUHAMAD BUDIE BIN BASRI', '950405025185', '0175757018', 'budie@gmail.com', 'No 64, Jalan TU3,,Taman Tasik Utama,75450 Ayer Keroh Melaka', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNM0SAX+YDjq7A3CWyzrBZr7jWzE7YFHKvqHqUhFPVxSnFMXn/md1756D7wu2wbWm73BUNadECAJfqdYtmQ0SshTEiU/M0IOUwTKagPGHHW9LuoRjdNLwjUtjUiJ3+hf392ZpMunHvV/Dyxd8K//JKsjc8a9mmwQywmn61k29IYQIDAQAB', '0', 'SYSTEM_ADMINISTRATOR'),
(21, 'user12', '1000:5b42403361376336656135:120fcae1a22954d0c6031160a83e16c376929f3245ea6644520d9155a25fe18588d8a3081a274f60f3713a70c83230914834b0411f045a294d15edaebfe06318', 'user 12 bin user12', '950405025188', '019521425', 'user12@gmail.com', 'kg batu 2,ayer keroh,MELAKA', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCg0fc0yTINEuFTsF1QONr69gwWZMC8nm/ZOYk9sm9qQ6oM+KnpV/Yd46zChOvjxclOk5ysIjvV9/SdqwtZfK90HBxQeDinZg6nLiJRUfhakIu42MgHGKU8EwJ5x/cg+HeOkGrnhd4kUFY0R1ttLtWNgN5bukeYRu/GeIT6mzsKTQIDAQAB', '0', 'STAFF'),
(22, 'abf123', '1000:5b42403137326462613963:51cbded8ce3ac5b506d2324bb19c18276cd4e98b2e27b0376ae9eca2aa88a7c9cd6f341646df64a87394fd61b91c138b893282f75f92d7879906b81a741f4dcb', 'asdkjasf', '123412412', '4120421', 'asdas@gmail.com', 'fsafa,qdqw,dwqdqw', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrl1kdszvdcFXNNIwzeLwiUxt/80cejR1Sy86I8NHiUY4Uj4iEBLysN5BRTBZEvXStbsjyjWrqssWq8pSQo/NNt2Pko5DpfqAxKdQBnnVDyhbEmF326CxYGGdAPJ9P9O8bLM1SY5pg2aJozPkak01NIa763ksRF06kkX0AS+ZZSQIDAQAB', '0', 'STAFF'),
(23, 'frzfbl', '1000:5b42403437303337623732:7c63075827fd2247dfacc0c87abd1412a1b12e58b26402387ad524cdac9ca11229f3efc734c34f0c9ef5995416b7c733ce03b6db4fe9db67fcfa6ce0aed0d110', 'farez', '950405108876', '0176545464', 'frzfbl@gmail.com', 'cyberjaya,63000,cyberjaya', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVy3zr0GOEqIXrWpx06fDF0gk9ya3xt4224jg2XSWgv09OjEFmrJrSJ7qmd6Pubebsf1Sn0n2v/KXXD0a410OoUiVu2P+3wMISXByY1zq2UssFtwc5VJPhu9bB9dhFCCbHF14uy6cYLhSa9bSLgLvcQDqsBwqVtfP9HqTXNJnp3QIDAQAB', '0', 'STAFF'),
(24, 'shay46', '1000:5b424034626336626230:b1a9ba806639cc4ececc2bbfe609fbbce82b6ebfcfc1d253aad8d0ec94d942b1d5ccc0c758327f3b18a5a24c3d34ea950111584c4756c097a79bce6fc1926ee5', 'MOHAMMAD SYAHIRAN BIN SYAHRIN', '94920353423', '0196472934', 'zarran.sz@gmail.com', 'Batu Pahat,038441,JDT', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCg7ni3ZY5ahjXn5Dmw1LYfw1EmwnpfDp4c2a4CDWXG/I+QImtNhAQlDArGqTeOXWuP+/zAX8S+ahYaptniBuhcEDnGL5dAEo49hWjXTdpGUp0bCctfDImCOE+ZPu1STceAN61/tUuS6hCFuFZYpLhxWHF3y+o97StwYu2OKHbLywIDAQAB', '0', 'STAFF'),
(25, 'daen21', '1000:5b42403634616432343861:61cb79c715ab57c3a65174559a807975859d94ac4b95f7a8c83afe0d1615df84a74964e08fe7ab17d4dcdec8fe2c4e1ebbff376f73512e3647b2b9a2b992ed1c', 'MUHAMMAD HAFIZUDDIN BIN SHARUL LAZI', '950421015221', '0175757392', 'daen210421@gmail.com', 'No 94, Jln TU3,Taman Tasik Utama,75400 Ayer Keroh Melaka', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdm/FdS6HtbqZkrbtIcx93wjqxRZVToH1KDuz7abzSPnU2BeswKhD5Tr0v2Zvv8XeygA+B+D3Ue2HtPbx25RAtzi+J896oK2ossOsZTgYFOjYcDZilweZAsvXjPwm+BesFOmQOs5YctWEHlG4zATwX9CKibXU5MITcJo6vmuyP/QIDAQAB', '0', 'STAFF'),
(26, 'imam21', '1000:5b42403465656364323866:e067e896a8ee2a993f9f4fd96406476c6b24aff8aa3520bf1a2f27bdeb4b01b9e7bd90167e4fe145702147b5f3587279198b40b9559c9ba87ec76cb472f313e3', 'IMMAM MUKSENIN BIN IBRAHIM', '941221042145', '017232323', 'imamcool@gmail.com', '532,Lorong Haji Taib,Taman Bukit Sakti,03113 Alor Setar, Kedah', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCT+bo76EP94uYi9r0CdPIh0ZSVs1kJjZi6PP3JrzxwYJpEnBlEIv4LTkGZ1Tr9rVEEj3l00Fpd+JzVs3Iex+JUlIaEUuUm4EBWOBi5UqPzKF1C4jpJtzPfDO/A0CWxIQYnrN5IpAxtjBpjARpmPQKuGET/rdUq6Gkq+/9qq2ITuwIDAQAB', '0', 'STAFF');

-- --------------------------------------------------------

--
-- Table structure for table `user_friends`
--

CREATE TABLE `user_friends` (
  `list_id` int(11) NOT NULL,
  `user_id` int(30) NOT NULL,
  `friend_id` int(30) NOT NULL,
  `date_friend` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_friends`
--

INSERT INTO `user_friends` (`list_id`, `user_id`, `friend_id`, `date_friend`) VALUES
(1, 16, 12, '2017-04-17'),
(2, 16, 7, '2017-04-17'),
(3, 16, 6, '2017-04-17'),
(4, 18, 16, '2017-04-25'),
(5, 16, 18, '2017-04-25'),
(6, 20, 19, '2017-04-28'),
(7, 19, 20, '2017-04-28'),
(8, 21, 20, '2017-05-05'),
(9, 23, 19, '2017-05-24'),
(10, 19, 23, '2017-05-24'),
(11, 26, 25, '2017-05-24'),
(12, 1, 2, '2017-05-29');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `idData` int(11) NOT NULL,
  `method` varchar(30) NOT NULL,
  `path` varchar(255) NOT NULL,
  `user_id` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`branch_id`);

--
-- Indexes for table `data_info`
--
ALTER TABLE `data_info`
  ADD PRIMARY KEY (`idData`);

--
-- Indexes for table `pc_adm_users`
--
ALTER TABLE `pc_adm_users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `user_friends`
--
ALTER TABLE `user_friends`
  ADD PRIMARY KEY (`list_id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`idData`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `branch_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `data_info`
--
ALTER TABLE `data_info`
  MODIFY `idData` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT for table `pc_adm_users`
--
ALTER TABLE `pc_adm_users`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `user_friends`
--
ALTER TABLE `user_friends`
  MODIFY `list_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `idData` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
