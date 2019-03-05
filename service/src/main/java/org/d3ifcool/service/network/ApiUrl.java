package org.d3ifcool.service.network;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 03/03/2019.
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
public class ApiUrl {

    public ApiUrl() {
    }

    public static final class FinproUrl {

        public static final String IP = "192.168.0.16";
        public static final String FOLDER_NAME = "finpro-android";
        public static final String BASE_URL = "http://"+IP+"/"+FOLDER_NAME+"/";
        // -----------------------------------------------------------------------------------------
        public static final String BASE_URL_DOSEN = "dosen/";
        public static final String URL_DOSEN_CREATE = BASE_URL_DOSEN + "createDosen.php";
        public static final String URL_DOSEN_READ = BASE_URL_DOSEN + "getDosen.php";
        public static final String URL_DOSEN_DELETE = BASE_URL_DOSEN + "deleteDosen.php";
        public static final String URL_DOSEN_UPDATE = BASE_URL_DOSEN + "updateDosen.php";
        // -----------------------------------------------------------------------------------------
        public static final String BASE_URL_MAHASISWA = "mahasiswa/";
        public static final String URL_MAHASISWA_CREATE = BASE_URL_MAHASISWA + "createMahasiswa.php";
        public static final String URL_MAHASISWA_READ = BASE_URL_MAHASISWA + "getMahasiswa.php";
        public static final String URL_MAHASISWA_DELETE = BASE_URL_MAHASISWA + "deleteMahasiswa.php";
        public static final String URL_MAHASISWA_UPDATE = BASE_URL_MAHASISWA + "updateMahasiswa.php";
        // -----------------------------------------------------------------------------------------
        public static final String BASE_URL_LOGIN = "login/";
        public static final String URL_LOGIN_CREATE = BASE_URL_LOGIN + "createLogin.php";
        public static final String URL_LOGIN_READ = BASE_URL_LOGIN + "getLogin.php";
        public static final String URL_LOGIN_DELETE = BASE_URL_LOGIN + "deleteLogin.php";
        public static final String URL_LOGIN_UPDATE = BASE_URL_LOGIN + "updateLogin.php";
        public static final String URL_LOGIN = BASE_URL_LOGIN + "login.php";
        // -----------------------------------------------------------------------------------------
        public static final String BASE_URL_INFORMASI = "informasi/";
        public static final String URL_INFORMASI_CREATE = BASE_URL_INFORMASI + "createInformasi.php";
        public static final String URL_INFORMASI_READ = BASE_URL_INFORMASI + "getInformasi.php";
        public static final String URL_INFORMASI_DELETE = BASE_URL_INFORMASI + "deleteInformasi.php";
        public static final String URL_INFORMASI_UPDATE = BASE_URL_INFORMASI + "updateInformasi.php";
        // -----------------------------------------------------------------------------------------
        public static final String BASE_URL_JUDUL_PA = "judulpa/";
        public static final String URL_JUDUL_PA_CREATE = BASE_URL_JUDUL_PA + "createJudul.php";
        public static final String URL_JUDUL_PA_READ = BASE_URL_JUDUL_PA + "getJudul.php";
        public static final String URL_JUDUL_PA_DELETE = BASE_URL_JUDUL_PA + "deleteJudul.php";
        public static final String URL_JUDUL_PA_UPDATE = BASE_URL_JUDUL_PA + "updateJudul.php";
        public static final String URL_KATEGORI_JUDUL_PA_READ = BASE_URL_JUDUL_PA + "getKategoriJudul.php";

    }
}
