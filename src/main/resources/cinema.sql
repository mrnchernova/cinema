-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3307
-- Время создания: Сен 08 2023 г., 13:46
-- Версия сервера: 5.5.62
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `cinema`
--

-- --------------------------------------------------------

--
-- Структура таблицы `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `movie`
--

INSERT INTO `movie` (`id`, `title`, `date`) VALUES
(1, 'Индиана Джонс и колесо судьбы', '2023-09-14 16:00:00'),
(2, 'Оппенгеймер', '2023-09-09 12:45:00'),
(3, 'Черепашки ниндзя: погром мутантов', '2023-09-11 15:00:00');

-- --------------------------------------------------------

--
-- Структура таблицы `person`
--

CREATE TABLE `person` (
  `id` int(4) NOT NULL,
  `username` varchar(150) COLLATE utf8_bin NOT NULL,
  `password` varchar(150) COLLATE utf8_bin NOT NULL,
  `email` varchar(250) COLLATE utf8_bin NOT NULL,
  `role` varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `person`
--

INSERT INTO `person` (`id`, `username`, `password`, `email`, `role`) VALUES
(1, 'admin', ';уW§‚tv\'ћїІ‰w•Ш', 'admin@mail.ru', 'ADMIN'),
(2, 'manager', 'ЭWфт§СBiЦ№РСзwґ', 'manager@ya.ru', 'MANAGER'),
(3, 'user', ',†’ЦQўЏ‡¬S\rШБOн', 'user@gmail.com', 'USER');

-- --------------------------------------------------------

--
-- Структура таблицы `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `person_id` int(11) DEFAULT NULL,
  `seat` int(11) NOT NULL,
  `price` double NOT NULL,
  `in_stock` tinyint(1) NOT NULL DEFAULT '1',
  `movie_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `ticket`
--

INSERT INTO `ticket` (`id`, `person_id`, `seat`, `price`, `in_stock`, `movie_id`) VALUES
(1, NULL, 1, 12, 1, 1),
(2, NULL, 2, 12, 1, 1),
(3, NULL, 3, 12, 1, 1),
(4, NULL, 4, 12, 1, 1),
(5, NULL, 5, 12, 1, 1),
(6, 3, 6, 12, 0, 1),
(7, NULL, 7, 12, 1, 1),
(8, NULL, 8, 12, 1, 1),
(9, NULL, 9, 12, 1, 1),
(10, NULL, 10, 12, 1, 1),
(11, NULL, 1, 9.5, 1, 2),
(12, NULL, 2, 9.5, 1, 2),
(13, NULL, 3, 9.5, 1, 2),
(14, 3, 4, 9.5, 0, 2),
(15, NULL, 5, 9.5, 1, 2),
(16, NULL, 6, 9.5, 1, 2),
(17, NULL, 7, 9.5, 1, 2),
(18, NULL, 8, 9.5, 1, 2),
(19, NULL, 9, 9.5, 1, 2),
(20, NULL, 10, 9.5, 1, 2),
(21, NULL, 1, 17, 1, 3),
(22, NULL, 2, 17, 1, 3),
(23, NULL, 3, 17, 1, 3),
(24, NULL, 4, 17, 1, 3),
(25, NULL, 5, 17, 1, 3),
(26, NULL, 6, 17, 1, 3),
(27, NULL, 7, 17, 1, 3),
(28, 3, 8, 17, 0, 3),
(29, NULL, 9, 17, 1, 3),
(30, NULL, 10, 17, 1, 3);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `person_id` (`person_id`),
  ADD KEY `movie_id` (`movie_id`),
  ADD KEY `movie_id_2` (`movie_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `person`
--
ALTER TABLE `person`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_movie_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ticket_person_fk` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
