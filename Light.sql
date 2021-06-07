-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Gegenereerd op: 07 jun 2021 om 17:25
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
-- Tabelstructuur voor tabel `Action`
--

CREATE TABLE `Action` (
  `ID` int(11) NOT NULL,
  `Created` text NOT NULL,
  `Process` text NOT NULL,
  `Type` text NOT NULL,
  `Par` text NOT NULL,
  `Done` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `Action`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Current`
--

CREATE TABLE `Current` (
  `SeqNr` int(11) NOT NULL,
  `ID` text NOT NULL,
  `Sunset` text NOT NULL,
  `StartLightOn` text NOT NULL,
  `LightOff` text NOT NULL,
  `UpdateTime` text NOT NULL,
  `Fase` int(11) NOT NULL,
  `LightReading` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `Current`
--

INSERT INTO `Current` (`SeqNr`, `ID`, `Sunset`, `StartLightOn`, `LightOff`, `UpdateTime`, `Fase`, `LightReading`) VALUES
(1, 'Light', '2021-06-07T21:57:57+02:00[Europe/Amsterdam]', '2021-06-07T20:57:57+02:00[Europe/Amsterdam]', '2021-06-07T23:52:00+02:00[Europe/Amsterdam]', '2021-06-08T10:00:00+02:00[Europe/Amsterdam]', 1, 0);

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
-- Gegevens worden geëxporteerd voor tabel `Log`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Setting`
--

CREATE TABLE `Setting` (
  `SeqNr` int(11) NOT NULL,
  `ID` text NOT NULL,
  `Longitude` double NOT NULL,
  `Lattitude` double NOT NULL,
  `LightOffHour` int(11) NOT NULL,
  `LightOffMin` int(11) NOT NULL,
  `OffDuration` int(11) NOT NULL,
  `SensorLimit` int(11) NOT NULL,
  `PeriodDark` int(11) NOT NULL,
  `PeriodSec` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `Setting`
--

INSERT INTO `Setting` (`SeqNr`, `ID`, `Longitude`, `Lattitude`, `LightOffHour`, `LightOffMin`, `OffDuration`, `SensorLimit`, `PeriodDark`, `PeriodSec`) VALUES
(1, 'Light', 4.664475, 52.136223, 23, 30, 45, 5000, 3, 60);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Switch`
--

CREATE TABLE `Switch` (
  `SeqNr` int(11) NOT NULL,
  `SeqNumber` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Active` int(11) NOT NULL,
  `Pause` int(11) NOT NULL,
  `IP` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `Switch`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Version`
--

CREATE TABLE `Version` (
  `ID` text CHARACTER SET ascii COLLATE ascii_bin NOT NULL DEFAULT '',
  `Version` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `Version`
--

INSERT INTO `Version` (`ID`, `Version`) VALUES
('\'Light\'', 200);

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `Action`
--
ALTER TABLE `Action`
  ADD PRIMARY KEY (`ID`);

--
-- Indexen voor tabel `Current`
--
ALTER TABLE `Current`
  ADD PRIMARY KEY (`SeqNr`);

--
-- Indexen voor tabel `Log`
--
ALTER TABLE `Log`
  ADD PRIMARY KEY (`SeqNr`);

--
-- Indexen voor tabel `Setting`
--
ALTER TABLE `Setting`
  ADD PRIMARY KEY (`SeqNr`);

--
-- Indexen voor tabel `Switch`
--
ALTER TABLE `Switch`
  ADD PRIMARY KEY (`SeqNr`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `Action`
--
ALTER TABLE `Action`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT voor een tabel `Current`
--
ALTER TABLE `Current`
  MODIFY `SeqNr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `Log`
--
ALTER TABLE `Log`
  MODIFY `SeqNr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=384;
--
-- AUTO_INCREMENT voor een tabel `Setting`
--
ALTER TABLE `Setting`
  MODIFY `SeqNr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `Switch`
--
ALTER TABLE `Switch`
  MODIFY `SeqNr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
