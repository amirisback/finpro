-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 19, 2019 at 10:10 PM
-- Server version: 10.3.15-MariaDB-log-cll-lve
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `diffinpr_finpro_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `bimbingan`
--

CREATE TABLE `bimbingan` (
  `bimbingan_id` int(10) UNSIGNED NOT NULL,
  `bimbingan_review` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bimbingan_kehadiran` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bimbingan_tanggal` date NOT NULL,
  `bimbingan_status` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `proyek_akhir_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `detail_monev`
--

CREATE TABLE `detail_monev` (
  `monev_detail_id` int(10) UNSIGNED NOT NULL,
  `monev_nilai` tinyint(4) DEFAULT NULL,
  `monev_tanggal` date NOT NULL,
  `monev_ulasan` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `monev_id` int(10) UNSIGNED NOT NULL,
  `proyek_akhir_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `dsn_nip` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dsn_nama` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dsn_kode` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dsn_kontak` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dsn_foto` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `dsn_email` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`dsn_nip`, `dsn_nama`, `dsn_kode`, `dsn_kontak`, `dsn_foto`, `dsn_email`, `username`) VALUES
('06750056', 'Hetti Hidayati, S.Kom., MT.', 'HTT', NULL, 'default-dosen.jpg', NULL, '06750056'),
('14840024', 'Fat\'hah Noor Prawita, S.T., M.T.', 'NPR', NULL, 'default-dosen.jpg', NULL, '14840024'),
('14871568', 'Indra Azimi, S.T., M.T.', 'IZM', NULL, 'default-dosen.jpg', NULL, '14871568'),
('14880088', 'Amir Hasanudin Fauzi, S.T., M.T.', 'FAU', NULL, 'default-dosen.jpg', NULL, '14880088'),
('15780014', 'Hariandi Maulid, S.T., M.Sc', 'HMU', NULL, 'default-dosen.jpg', NULL, '15780014'),
('15880031', 'Rizza Indah Mega Mandasari, S.Kom., M.T.', 'RIM', NULL, 'default-dosen.jpg', NULL, '15880031'),
('17720068', 'Rahmadi Wijaya, S.T., M.T.', 'RWJ', NULL, 'default-dosen.jpg', NULL, '17720068'),
('94670025', 'Tri Brotoharsono, ST., MT.', 'TBH', NULL, 'default-dosen.jpg', NULL, '94670025');

-- --------------------------------------------------------

--
-- Table structure for table `informasi`
--

CREATE TABLE `informasi` (
  `informasi_id` int(10) UNSIGNED NOT NULL,
  `informasi_judul` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `informasi_isi` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `penerbit` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `informasi_waktu` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `informasi`
--

INSERT INTO `informasi` (`informasi_id`, `informasi_judul`, `informasi_isi`, `penerbit`, `informasi_waktu`) VALUES
(8, '[INFO][REVIEWER MONEV PROYEK AKHIR]', 'Assalamu\'alaikum warahmatullahi wabarakatuh\r\n\r\nSelamat Siang,\r\n\r\nBerikut adalah list reviewer monev I Pra PA.\r\n\r\nhttps://docs.google.com/…/16i6YZMTo5HFMn6RyOsXfHPVkPr…/edit…\r\n\r\nKegiatan monev akan dilakukan sesuai dengan pengumuman yang telah diberikan minggu lalu. Silahkan kontak dosen reviewer kalian masing-masing untuk memastikan kembali jadwal monev.\r\n\r\nTerima Kasih.', 'Amir Hasanudin Fauzi, S.T., M.T.', '2019-06-19 14:57:06'),
(9, '[INFO][PROYEK AKHIR][BAB 2]', 'Assalamu\'alaikum Warahmatullahi Wabarakatuh\r\n\r\nSelamat pagi, Alhamdulillah minggu lalu kita sudah melewati milestone pertama kita, monev Pra PA 1. Dimana tahapan selanjutnya adalah BAB 2 Tinjauan Pustaka.\r\n\r\nSilahkan pahami materi tentang tinjauan pustaka pada link berikut:\r\n\r\nhttps://id.oxforddictionaries.com/…/menulis-tinjauan-pustak…\r\n\r\nBerdasarkan keputusan Tim Proyek Akhir. Untuk meningkatkan kualitas laporan penilitan, khususnya BAB 2 ini. Maka TIM PA mewajibkan beberapa syarat penulisan BAB 2 berikut ini:\r\n\r\n1. Mencari Referensi Berupa Jurnal/Prosiding/Buku yang berkaitan dengan penelitian yang dilakukan oleh mahasiswa bersumber dari jurnal nasional/internasional minimal 5 (lima) sumber.\r\n2. Agar syarat pertama dapat dilakukan, mahasiswa diwajibkan untuk berkunjung ke perpustakan open lib Universitas Telkom agar dapat mengakses Jurnal/Paper tersebut (Agar tidak perlu melewati tahapan membayar).', 'Amir Hasanudin Fauzi, S.T., M.T.', '2019-06-19 14:57:21'),
(10, '[INFO][PROYEK AKHIR][BAB 2]', 'Assalamu\'alaikum Warahmatullahi Wabarakatuh\r\n\r\nSelamat pagi, Alhamdulillah minggu lalu kita sudah melewati milestone pertama kita, monev Pra PA 1. Dimana tahapan selanjutnya adalah BAB 2 Tinjauan Pustaka.\r\n\r\nSilahkan pahami materi tentang tinjauan pustaka pada link berikut:\r\n\r\nhttps://id.oxforddictionaries.com/…/menulis-tinjauan-pustak…\r\n\r\nBerdasarkan keputusan Tim Proyek Akhir. Untuk meningkatkan kualitas laporan penilitan, khususnya BAB 2 ini. Maka TIM PA mewajibkan beberapa syarat penulisan BAB 2 berikut ini:\r\n\r\n1. Mencari Referensi Berupa Jurnal/Prosiding/Buku yang berkaitan dengan penelitian yang dilakukan oleh mahasiswa bersumber dari jurnal nasional/internasional minimal 5 (lima) sumber.\r\n2. Agar syarat pertama dapat dilakukan, mahasiswa diwajibkan untuk berkunjung ke perpustakan open lib Universitas Telkom agar dapat mengakses Jurnal/Paper tersebut (Agar tidak perlu melewati tahapan membayar).', 'Amir Hasanudin Fauzi, S.T., M.T.', '2019-06-19 14:57:30'),
(11, '[INFO][PENDAFTARAN TA/PA VIA IGRACIAS]', 'Assalamu\'alaikum Wr. Wb.\r\n\r\nSelamat Siang,\r\n\r\nDiinformasikan bagi seluruh kelompok Proyek Akhir yang sudah melakukan monev 4 untuk sesegera mungkin melakukan bimbingan proyek akhir untuk mendiskusikan pendaftaran PA via igracias. Hal ini diperlukan untuk memastikan judul PA yang akan anda daftarakan pada sistem. \r\nAkhir dari proses ini akan menerbitkan SK PA setiap kelompok, yang diperlukan nanti dalam proses pendaftaran Sidang PA.\r\n\r\nSelain itu, setiap kelompok juga harus mendaftarkan keseluruhan proses bimbingan yang telah dilakukan selama ini, untuk memenuhi persyaratan daftar sidang.\r\n\r\nUntuk informasi yang lebih mendetail, silahkan diskusikan dengan pembimbing masing-masing.\r\n\r\nTerima kasih,\r\n\r\nTim PA', 'Amir Hasanudin Fauzi, S.T., M.T.', '2019-06-19 14:57:36');

-- --------------------------------------------------------

--
-- Table structure for table `judul`
--

CREATE TABLE `judul` (
  `judul_id` int(10) UNSIGNED NOT NULL,
  `judul_nama` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `judul_deskripsi` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `judul_status` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `judul_waktu` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `dsn_nip` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `kategori_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `judul`
--

INSERT INTO `judul` (`judul_id`, `judul_nama`, `judul_deskripsi`, `judul_status`, `judul_waktu`, `dsn_nip`, `kategori_id`) VALUES
(17, 'Aplikasi Pembelajaran Bahasa Sunda Berbasis Kolaborasi', 'Deskripsi aplikasi pembelajaran b.sunda berbasis android', 'tersedia', '2019-06-19 10:47:34', '14871568', 2),
(18, 'Aplikasi Permainan bergenre Third Person Action untuk melestarikan tradisional gobak sodor', 'Detail Deskripsi Aplikasi Virtual Reality Third Person melestarikan tradisional', 'tersedia', '2019-06-19 10:48:48', '14880088', 4),
(19, '\"Lindur\" : Aplikasi permainan bergenre survival: Resource Management : Simulation Game Pembelajaran Tindakan Darurat pada saat Gempa Bumi', 'Desripsi Aplikasi Permainan yang bergenre survival Resource Management', 'tersedia', '2019-06-19 10:50:17', '14880088', 5),
(20, 'Aplikasi Pemburu Beasiswa dengan Konten Dinamis Otomatis', 'deskripsi Aplikasi web Pemburu Beasiswa dengan konten dinamis otimatis', 'tersedia', '2019-06-19 10:51:19', '14871568', 1),
(21, 'Aplikasi Gamifikasi Posyandu ( Penjadwalan Imunisasi dan Pemantauan Pertumbuhan Balita )', 'Deskripsi Android Aplikasi Gamifikasi Posyandu ( Penjadwalan Imunisasi dan Pemantauan Pertumbuhan Balita )', 'tersedia', '2019-06-19 11:06:03', '15880031', 2),
(22, 'Aplikasi Intevensi Dini Gangguan Perkembangan Menggunakan Denver Test II/KPSP Test', 'Detail Aplikasi Intervensi untuk gangguan dini perkembangan Penggunaan Denver', 'tersedia', '2019-06-19 11:07:30', '15880031', 2),
(23, 'Aplikasi kontroler Lampu Otomatis', 'Detail Aplikasi Kontroller untuk lampu otomatis', 'tersedia', '2019-06-19 11:08:47', '94670025', 3),
(24, 'Aplikasi Order Makanan dan Minuman Khusus Mahasiswa Telkom University', 'Detail Aplikasi Order Makanan dan Minuman Khusus Mahasiswa Telkom', 'tersedia', '2019-06-19 11:10:01', '94670025', 2),
(25, 'Pendeteksi Disleksia Bagi Anak Berbasis Mobile Application', 'Detail Pendeteksi Disleksia Bagi Anak yang berbasis Mobile Application', 'tersedia', '2019-06-19 11:12:29', '06750056', 2),
(26, 'Pengembangan Sistem Dan Konten E-learning Pembelajaran Time Mgt pada MK MPTI', 'Detail Sistem dan Konten E-Learning pembelajaran time management', 'tersedia', '2019-06-19 11:13:04', '06750056', 1),
(27, 'FinPro A website based Final Project Management System', 'Deskripsi Aplikasi Finpro untuk website', 'tersedia', '2019-06-19 11:14:34', '15780014', 2),
(28, 'FinPro A mobile based Final Project Management System', 'Detail Aplikasi Finpro berbasis Mobile Android', 'tersedia', '2019-06-19 11:15:35', '15780014', 2),
(29, 'Aplikasi AR untuk Pengenalan Asam Basa, Ph, dan Titrasi', 'Deskripsi Aplikasi AR untuk  Asam Basa, Ph, dan Titrasi berbasis Augmented Reality', 'tersedia', '2019-06-19 11:17:59', '14840024', 4),
(30, 'Aplikasi AR untuk pencarian lokasi produk Mall', 'Deskripsi Judul aplikasi AR untuk pencarian lokasi produk Mall  berbasis Augmented Reality Android', 'tersedia', '2019-06-19 11:18:53', '14840024', 5),
(31, 'Ruang pembelajaran : Organ dalam tubuh manusia berbasis VR 360', 'Deskripsi Judul Ruang pembelajaran : Organ dalam tubuh manusia berbasis Virtual Reality 360 derajat', 'tersedia', '2019-06-19 11:21:49', '17720068', 4),
(32, 'Aplikasi Penelusuran Sistem Tata Surya Berbasis VR', 'Deskripsi Penelusuran Sistem Tata Surya Virtual Reality Android', 'tersedia', '2019-06-19 11:22:46', '17720068', 4);

-- --------------------------------------------------------

--
-- Table structure for table `kategori_judul`
--

CREATE TABLE `kategori_judul` (
  `kategori_id` int(10) UNSIGNED NOT NULL,
  `kategori_nama` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `kategori_judul`
--

INSERT INTO `kategori_judul` (`kategori_id`, `kategori_nama`) VALUES
(1, 'Website'),
(2, 'Android'),
(3, 'Internet of Things'),
(4, 'Virtual Reality'),
(5, 'Augmented Reality');

-- --------------------------------------------------------

--
-- Table structure for table `koordinator_pa`
--

CREATE TABLE `koordinator_pa` (
  `koor_nip` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `koor_nama` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `koor_kode` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `koor_kontak` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `koor_foto` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `koor_email` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `koordinator_pa`
--

INSERT INTO `koordinator_pa` (`koor_nip`, `koor_nama`, `koor_kode`, `koor_kontak`, `koor_foto`, `koor_email`, `username`) VALUES
('14880088', 'Amir Hasanudin Fauzi, S.T., M.T.', 'FAU', '1222222222', 'default-koor.jpg', NULL, 'koordinator');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `mhs_nim` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mhs_nama` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `angkatan` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mhs_kontak` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mhs_foto` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `mhs_email` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `judul_id` int(10) UNSIGNED DEFAULT NULL,
  `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`mhs_nim`, `mhs_nama`, `angkatan`, `mhs_kontak`, `mhs_foto`, `mhs_email`, `status`, `judul_id`, `username`) VALUES
('6706160014', 'M Faisal Amir', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706160014'),
('6706160038', 'Aldi Ramdani', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706160038'),
('6706160065', 'Bryan Rafsanzani', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706160065'),
('6706160074', 'Pramana Putra', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706160074'),
('6706160098', 'Ema Nur Kuswari', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706160098'),
('6706160113', 'Rivkal Sukma Sanjaya', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706160113'),
('6706162050', 'Bunga Rizkyani', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706162050'),
('6706162062', 'M Ikhsan Ramadhan', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706162062'),
('6706162134', 'Ahmad Al Ghozi Ramadhan', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706162134'),
('6706164002', 'Seto Jalu Priyono', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706164002'),
('6706164086', 'Rizky Eka Maulana', '2016', NULL, 'default-mahasiswa.jpg', NULL, 'aktif', NULL, '6706164086');

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2019_03_13_030811_create_user_table', 1),
(2, '2019_03_13_031115_create_koordinator_pa_table', 1),
(3, '2019_03_13_031146_create_dosen_table', 1),
(4, '2019_03_13_031226_create_informasi_table', 1),
(5, '2019_03_13_031303_create_monev_table', 1),
(6, '2019_03_13_031346_create_kategori_judul_table', 1),
(7, '2019_03_13_031347_create_judul_table', 1),
(8, '2019_03_13_031404_create_mahasiswa_table', 1),
(9, '2019_03_13_031435_create_proyek_akhir_table', 1),
(10, '2019_03_13_031511_create_bimbingan_table', 1),
(11, '2019_03_13_031534_create_sidang_table', 1),
(12, '2019_03_13_031646_create_detail_monev_table', 1),
(13, '2019_03_13_031808_create_notifikasi_table', 1);

-- --------------------------------------------------------

--
-- Table structure for table `monev`
--

CREATE TABLE `monev` (
  `monev_id` int(10) UNSIGNED NOT NULL,
  `monev_kategori` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `jumlah_bimbingan` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `monev`
--

INSERT INTO `monev` (`monev_id`, `monev_kategori`, `jumlah_bimbingan`) VALUES
(1, 'Pra-Monev 1', 2),
(2, 'Monev 1', 9),
(3, 'Pra-Monev 2', 4),
(4, 'Monev 2', 12),
(9, 'Pra-Monev 3', 6),
(10, 'Monev 3', 14);

-- --------------------------------------------------------

--
-- Table structure for table `notifikasi`
--

CREATE TABLE `notifikasi` (
  `notifikasi_id` int(10) UNSIGNED NOT NULL,
  `notifikasi_dari` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `notifikasi_untuk` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `notifikasi_kategori` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `notifikasi_deskripsi` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `notifikasi_baca` tinyint(1) NOT NULL DEFAULT 0,
  `notifikasi_tanggal` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notifikasi`
--

INSERT INTO `notifikasi` (`notifikasi_id`, `notifikasi_dari`, `notifikasi_untuk`, `notifikasi_kategori`, `notifikasi_deskripsi`, `notifikasi_baca`, `notifikasi_tanggal`) VALUES
(62, '[koordinator] Amir Hasanudin Fauzi', 'semua', '[koordinator] Amir Hasanudin Fauzi Mengirim Informasi baru', '[INFO][REVIEWER MONEV PROYEK AKHIR]', 1, '2019-06-19 11:24:55'),
(63, '[koordinator] Amir Hasanudin Fauzi', 'semua', '[koordinator] Amir Hasanudin Fauzi Mengirim Informasi baru', '[INFO][PROYEK AKHIR][BAB 2]', 1, '2019-06-19 11:26:39'),
(64, '[koordinator] Amir Hasanudin Fauzi', 'semua', '[koordinator] Amir Hasanudin Fauzi Mengirim Informasi baru', '[INFO][PROYEK AKHIR][BAB 2]', 1, '2019-06-19 11:27:33'),
(65, '[koordinator] Amir Hasanudin Fauzi', 'semua', '[koordinator] Amir Hasanudin Fauzi Mengirim Informasi baru', '[INFO][PENDAFTARAN TA/PA VIA IGRACIAS]', 1, '2019-06-19 11:29:59');

-- --------------------------------------------------------

--
-- Table structure for table `proyek_akhir`
--

CREATE TABLE `proyek_akhir` (
  `proyek_akhir_id` int(10) UNSIGNED NOT NULL,
  `nilai_total` tinyint(4) DEFAULT NULL,
  `nama_tim` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `judul_id` int(10) UNSIGNED DEFAULT NULL,
  `mhs_nim` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dsn_nip` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sidang`
--

CREATE TABLE `sidang` (
  `sidang_id` int(10) UNSIGNED NOT NULL,
  `sidang_review` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sidang_tanggal` date NOT NULL,
  `nilai_proposal` tinyint(4) DEFAULT NULL,
  `nilai_penguji_1` tinyint(4) DEFAULT NULL,
  `nilai_penguji_2` tinyint(4) DEFAULT NULL,
  `nilai_pembimbing` tinyint(4) DEFAULT NULL,
  `nilai_total` double DEFAULT NULL,
  `sidang_status` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `proyek_akhir_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `pengguna` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `pengguna`) VALUES
('06750056', '$2y$10$IPR9.fy03WAMpAaacoUDGuvOJPalNHvFAkjLJa9ARn6LcLnv.WH1K', 'dosen'),
('14840024', '$2y$10$q2TFNBEyazk5YdBXeMlMCumkNoHeUW5HNNi4DpZxe/tc5p7WTBlAq', 'dosen'),
('14871568', '$2y$10$igwWEvtff118P2mNcgQSGuwV8CNgWWuqiVmObmiGHCoCpfm66yrn2', 'dosen'),
('14880088', '$2y$10$ZK6szroaF/f7SoFoWegTgeQD/axmn3NE6Z/fCEIvhT1WP3uROzmqq', 'dosen'),
('15780014', '$2y$10$ZT/M3mGG3CX6plO6u1cgxOhbjoMmbJRG07VnRlqZdm9gfFFx6MtmC', 'dosen'),
('15880031', '$2y$10$3i4n9om49IcoeRSJH.atNOGS6t7BiE7xdADytXo6nsf/og4RFrFd.', 'dosen'),
('17720068', '$2y$10$VGTOxfEj7kX49ppuMEASCuvX0vSJTZu/iU0fQ.CwiWnny9TLGrvAu', 'dosen'),
('6706160014', '$2y$10$i6MxwPwBtvd8wyYEaNaqRe8Pa02fKyn/qFBBTWhl3dfvBa3uiRJfm', 'mahasiswa'),
('6706160038', '$2y$10$lSrzZdKpm90XT9R0Y.sMvO/XT53O5z6muDMff.UEjxwsbSNrMuKuy', 'mahasiswa'),
('6706160065', '$2y$10$.6Tu7HCRF.Pi3oCM.zHVZ..P0YL8v3O4RG.jLZAKDNoVZGCEcqMiu', 'mahasiswa'),
('6706160074', '$2y$10$crd3m/g2pread/kf371cf.0aCOEXZdSlNx/cNJV/evuI84npcd6zu', 'mahasiswa'),
('6706160098', '$2y$10$5w1DQpXzV2U06r8mEQsQku8eJIbSnm2PgajA1zHyFtc8Z8wNCCfEu', 'mahasiswa'),
('6706160113', '$2y$10$R5wcBj.SoPiNtggnR4UjDeWBRT0F5qDORsNqFENmMRSh7CfIA.xya', 'mahasiswa'),
('6706162050', '$2y$10$BcPsxm77HqERHaY//JYwlOrvtYSO/78PDXglaWJRxglCY1Lq68pym', 'mahasiswa'),
('6706162062', '$2y$10$KmvLkW1ollTgm5jsEZqN8.j/OLM6Nj73RM.qJOhv56F0r00jeiz36', 'mahasiswa'),
('6706162134', '$2y$10$XS5kINX./nf6McDmCZyDD.LMu3sW4nfMofI9aSNh3HrpYZFACcf5i', 'mahasiswa'),
('6706164002', '$2y$10$g6p8sjK7.B1TcyL/YmIPWejklVZS9tLPA0FTFE6k7k5YwznN3Kp4G', 'mahasiswa'),
('6706164086', '$2y$10$28VOsp4uOKzqNRgmsFlG3uwvj9d4CP2dHsCCuKAk66wd0HfkFlxd6', 'mahasiswa'),
('94670025', '$2y$10$vuKaL8AX6kWes0IIqnC/z.nTBS0ZntIQcM/dMUK1im1xqnNCri7vi', 'dosen'),
('koordinator', '$2y$10$Ix0qOvjxRNID2JZewhr0H.ytgwmi/HshJw6huI.wszvX/dRF2u2ku', 'koor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bimbingan`
--
ALTER TABLE `bimbingan`
  ADD PRIMARY KEY (`bimbingan_id`),
  ADD KEY `bimbingan_proyek_akhir_id_foreign` (`proyek_akhir_id`);

--
-- Indexes for table `detail_monev`
--
ALTER TABLE `detail_monev`
  ADD PRIMARY KEY (`monev_detail_id`),
  ADD KEY `detail_monev_proyek_akhir_id_foreign` (`proyek_akhir_id`),
  ADD KEY `detail_monev_monev_id_foreign` (`monev_id`);

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`dsn_nip`),
  ADD UNIQUE KEY `dosen_dsn_kontak_unique` (`dsn_kontak`),
  ADD UNIQUE KEY `dosen_dsn_email_unique` (`dsn_email`),
  ADD KEY `dosen_username_foreign` (`username`);

--
-- Indexes for table `informasi`
--
ALTER TABLE `informasi`
  ADD PRIMARY KEY (`informasi_id`);

--
-- Indexes for table `judul`
--
ALTER TABLE `judul`
  ADD PRIMARY KEY (`judul_id`),
  ADD KEY `judul_kategori_id_foreign` (`kategori_id`),
  ADD KEY `15` (`dsn_nip`);

--
-- Indexes for table `kategori_judul`
--
ALTER TABLE `kategori_judul`
  ADD PRIMARY KEY (`kategori_id`);

--
-- Indexes for table `koordinator_pa`
--
ALTER TABLE `koordinator_pa`
  ADD PRIMARY KEY (`koor_nip`),
  ADD UNIQUE KEY `koordinator_pa_koor_kontak_unique` (`koor_kontak`),
  ADD UNIQUE KEY `koordinator_pa_koor_email_unique` (`koor_email`),
  ADD KEY `koordinator_pa_username_foreign` (`username`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`mhs_nim`),
  ADD UNIQUE KEY `mahasiswa_mhs_kontak_unique` (`mhs_kontak`),
  ADD UNIQUE KEY `mahasiswa_mhs_email_unique` (`mhs_email`),
  ADD KEY `mahasiswa_username_foreign` (`username`),
  ADD KEY `mahasiswa_judul_id_foreign` (`judul_id`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `monev`
--
ALTER TABLE `monev`
  ADD PRIMARY KEY (`monev_id`);

--
-- Indexes for table `notifikasi`
--
ALTER TABLE `notifikasi`
  ADD PRIMARY KEY (`notifikasi_id`);

--
-- Indexes for table `proyek_akhir`
--
ALTER TABLE `proyek_akhir`
  ADD PRIMARY KEY (`proyek_akhir_id`),
  ADD KEY `proyek_akhir_judul_id_foreign` (`judul_id`),
  ADD KEY `proyek_akhir_mhs_nim_foreign` (`mhs_nim`),
  ADD KEY `proyek_akhir_dsn_nip_foreign` (`dsn_nip`);

--
-- Indexes for table `sidang`
--
ALTER TABLE `sidang`
  ADD PRIMARY KEY (`sidang_id`),
  ADD KEY `sidang_proyek_akhir_id_foreign` (`proyek_akhir_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bimbingan`
--
ALTER TABLE `bimbingan`
  MODIFY `bimbingan_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `detail_monev`
--
ALTER TABLE `detail_monev`
  MODIFY `monev_detail_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `informasi`
--
ALTER TABLE `informasi`
  MODIFY `informasi_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `judul`
--
ALTER TABLE `judul`
  MODIFY `judul_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `kategori_judul`
--
ALTER TABLE `kategori_judul`
  MODIFY `kategori_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `monev`
--
ALTER TABLE `monev`
  MODIFY `monev_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `notifikasi`
--
ALTER TABLE `notifikasi`
  MODIFY `notifikasi_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `proyek_akhir`
--
ALTER TABLE `proyek_akhir`
  MODIFY `proyek_akhir_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `sidang`
--
ALTER TABLE `sidang`
  MODIFY `sidang_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bimbingan`
--
ALTER TABLE `bimbingan`
  ADD CONSTRAINT `bimbingan_proyek_akhir_id_foreign` FOREIGN KEY (`proyek_akhir_id`) REFERENCES `proyek_akhir` (`proyek_akhir_id`);

--
-- Constraints for table `detail_monev`
--
ALTER TABLE `detail_monev`
  ADD CONSTRAINT `detail_monev_monev_id_foreign` FOREIGN KEY (`monev_id`) REFERENCES `monev` (`monev_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_monev_proyek_akhir_id_foreign` FOREIGN KEY (`proyek_akhir_id`) REFERENCES `proyek_akhir` (`proyek_akhir_id`) ON UPDATE CASCADE;

--
-- Constraints for table `dosen`
--
ALTER TABLE `dosen`
  ADD CONSTRAINT `dosen_username_foreign` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `judul`
--
ALTER TABLE `judul`
  ADD CONSTRAINT `15` FOREIGN KEY (`dsn_nip`) REFERENCES `dosen` (`dsn_nip`) ON UPDATE CASCADE,
  ADD CONSTRAINT `judul_kategori_id_foreign` FOREIGN KEY (`kategori_id`) REFERENCES `kategori_judul` (`kategori_id`) ON UPDATE CASCADE;

--
-- Constraints for table `koordinator_pa`
--
ALTER TABLE `koordinator_pa`
  ADD CONSTRAINT `koordinator_pa_username_foreign` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `mahasiswa_judul_id_foreign` FOREIGN KEY (`judul_id`) REFERENCES `judul` (`judul_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `mahasiswa_username_foreign` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `proyek_akhir`
--
ALTER TABLE `proyek_akhir`
  ADD CONSTRAINT `proyek_akhir_dsn_nip_foreign` FOREIGN KEY (`dsn_nip`) REFERENCES `dosen` (`dsn_nip`) ON UPDATE CASCADE,
  ADD CONSTRAINT `proyek_akhir_judul_id_foreign` FOREIGN KEY (`judul_id`) REFERENCES `judul` (`judul_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proyek_akhir_mhs_nim_foreign` FOREIGN KEY (`mhs_nim`) REFERENCES `mahasiswa` (`mhs_nim`) ON UPDATE CASCADE;

--
-- Constraints for table `sidang`
--
ALTER TABLE `sidang`
  ADD CONSTRAINT `sidang_proyek_akhir_id_foreign` FOREIGN KEY (`proyek_akhir_id`) REFERENCES `proyek_akhir` (`proyek_akhir_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
