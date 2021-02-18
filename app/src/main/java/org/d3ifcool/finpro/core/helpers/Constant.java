package org.d3ifcool.finpro.core.helpers;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/03/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class Constant {

    public Constant() {
    }

    public static final class ObjectConstanta {

        public static final String JUDUL_STATUS_TERSEDIA = "tersedia"; // judul yang di buat dosen
        public static final String JUDUL_STATUS_PENDING = "pending"; // judul yang di buat mahasiswa
        public static final String JUDUL_STATUS_DITOLAK = "ditolak";
        public static final String JUDUL_STATUS_DIGUNAKAN = "digunakan";
        public static final String JUDUL_STATUS_ARSIP = "arsip";
        public static final String JUDUL_STATUS_MENUNGGU = "Menunggu persetujuan";
        public static final String JUDUL_STATUS_DISETUJUI = "Judul disetujui";
        // -----------------------------------------------------------------------------------------
        public static final String PACKAGE_ROOT = "org.d3ifcool.finpro";
        public static final String PACKAGE_PATH_ACTIVITIES = PACKAGE_ROOT+".views.activities";
        public static final String PATH_MAIN_ACTIVITY = PACKAGE_PATH_ACTIVITIES+".MainActivity";
        // -----------------------------------------------------------------------------------------
        public static int PICK_PDF_REQUEST = 1;
        public static final int STORAGE_PERMISSION_CODE = 123;
        // -----------------------------------------------------------------------------------------
        public static final String PREF_NAME = "LOGIN";
        public static final String LOGIN = "IS_LOGIN";
        public static final String USERNAME = "USERNAME";
        public static final String PENGGUNA = "PENGGUNA";
        // -----------------------------------------------------------------------------------------
        public static final String DSN_NIP = "DSN_NIP";
        public static final String DSN_NIP_TEMP = "DSN_NIP_TEMP";
        public static final String DSN_NAMA = "DSN_NAMA";
        public static final String DSN_FOTO = "DSN_FOTO";
        public static final String DSN_EMAIL = "DSN_EMAIL";
        public static final String DSN_KONTAK = "DSN_KONTAK";
        public static final String DSN_KODE = "DSN_KODE";
        public static final String DSN_STATUS = "DSN_STATUS";
        public static final String DSN_BATAS_BIMBINGAN = "DSN_BATAS_BIMBINGAN";
        public static final String DSN_BATAS_REVIEWER = "DSN_BATAS_REVIEWER";
        // -----------------------------------------------------------------------------------------
        public static final String MHS_NIM = "MHS_NIM";
        public static final String MHS_NAMA = "MHS_NAMA";
        public static final String MHS_FOTO = "MHS_FOTO";
        public static final String MHS_EMAIL = "MHS_EMAIL";
        public static final String MHS_KONTAK = "MHS_KONTAK";
        public static final String MHS_STATUS = "STATUS";
        public static final String MHS_ANGKATAN = "ANGKATAN";
        public static final String MHS_ID_JUDUL = "MHS_ID_JUDUL";
        public static final String MHS_JUDUL = "MHS_JUDUL";
        public static final String MHS_JUDUL_DESKRIPSI = "MHS_JUDUL_DESKRIPSI";
        public static final String MHS_JUDUL_STATUS = "MHS_JUDUL_STATUS";
        public static final String MHS_ID_PROYEK_AKHIR = "MHS_ID_PROYEK_AKHIR";
        // -----------------------------------------------------------------------------------------
        public static final String KOOR_NIP = "KOOR_NIM";
        public static final String KOOR_NAMA = "KOOR_NAMA";
        public static final String KOOR_KODE = "KOOR_KODE";
        public static final String KOOR_EMAIL = "KOOR_EMAIL";
        public static final String KOOR_KONTAK = "KOOR_KONTAK";
        public static final String KOOR_FOTO = "KOOR_FOTO";
        public static final String KOOR_USERNAME = "USERNAME_KOOR";
        // -----------------------------------------------------------------------------------------
        public static final String BIMBINGAN_DITOLAK = "Bimbingan Ditolak!";
        public static final String BIMBINGAN_DIACC = "Bimbingan Telah Di-Acc!";
        // -----------------------------------------------------------------------------------------
        public static final String STATUS_BIMBINGAN_PENDING = "pending";
        public static final String STATUS_BIMBINGAN_DISETUJUI = "disetujui";
        // -----------------------------------------------------------------------------------------
        public static final int JUMLAH_BIMBINGAN_SIDANG = 14;
        public static final int JUMLAH_MONEV_SIDANG = 6;
        // -----------------------------------------------------------------------------------------
        public static final String STATUS_SIDANG_LULUS = "Lulus";
        public static final String STATUS_SIDANG_LULUS_BERSYARAT = "Lulus Bersyarat";
        public static final String STATUS_SIDANG_TIDAK_LULUS = "Tidak Lulus";
        // -----------------------------------------------------------------------------------------
        public static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        public static final String PHONE_PATTERN = "^(^\\+62\\s?|^0)(\\d{3,4}-?){2}\\d{3,4}$";
        // -----------------------------------------------------------------------------------------
        public static final String FILE_TYPE_DOC = "application/msword";
        public static final String FILE_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        public static final String FILE_TYPE_PPT = "application/vnd.ms-powerpoint";
        public static final String FILE_TYPE_PPTX = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
        public static final String FILE_TYPE_XLS = "application/vnd.ms-excel";
        public static final String FILE_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        public static final String FILE_TYPE_TXT = "text/plain";
        public static final String FILE_TYPE_PDF = "application/pdf";
        public static final String FILE_TYPE_ZIP = "application/zip";
        // -----------------------------------------------------------------------------------------
        public static  String[] FILE_TYPE =
                {FILE_TYPE_DOC,
                        FILE_TYPE_DOCX,
                        FILE_TYPE_PPT,
                        FILE_TYPE_PPTX,
                        FILE_TYPE_XLS,
                        FILE_TYPE_XLSX,
                        FILE_TYPE_TXT,
                        FILE_TYPE_PDF,
                        FILE_TYPE_ZIP};
        // -----------------------------------------------------------------------------------------

    }
}
