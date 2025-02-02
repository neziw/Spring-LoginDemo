-- Host: 127.0.0.1
-- Generation Time: Feb 02, 2025 at 07:27 PM

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `demo`
--

CREATE TABLE `demo_users` (
  `id` bigint(20) NOT NULL,
  `last_failed_login` datetime(6) DEFAULT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `demo_users` (`id`, `last_failed_login`, `last_login`, `password`, `role`, `username`) VALUES
(1, NULL, NULL, '$2a$16$Tvoo9u/ltq.OI6rBTDk64.5JkGBbL.MDt9L8/Vr3/N2dvkFO3QIRW', 'ADMIN', 'admin');

ALTER TABLE `demo_users`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `demo_users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
