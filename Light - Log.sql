-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Gegenereerd op: 10 jun 2021 om 17:02
-- Serverversie: 10.3.27-MariaDB-0+deb10u1
-- PHP-versie: 7.3.27-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Light`
--

-- --------------------------------------------------------


--
-- Tabelstructuur voor tabel `Log`
--

CREATE TABLE `Log` (
  `SeqNr` int(11) NOT NULL,
  `Time` text NOT NULL,
  `Text` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexen voor tabel `Log`
--
ALTER TABLE `Log`
  ADD PRIMARY KEY (`SeqNr`);

--
-- AUTO_INCREMENT voor een tabel `Log`
--
ALTER TABLE `Log`
  MODIFY `SeqNr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=590;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
