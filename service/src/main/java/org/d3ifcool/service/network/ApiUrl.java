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

        public static final String BASE_URL = "http://finpro-laravel.000webhostapp.com/"; // Link Website
        public static final String API_PATH = "api/v1/"; // Link API
        // -----------------------------------------------------------------------------------------
        public static final String BASE_URL_FOTO = BASE_URL + "image/";
        public static final String BASE_URL_LOGIN = API_PATH + "user/";
        // -----------------------------------------------------------------------------------------
        public static final String URL_FOTO_DOSEN = BASE_URL_FOTO + "dsn_img/";
        public static final String URL_FOTO_MAHASISWA = BASE_URL_FOTO + "mhs_img/";
        // -----------------------------------------------------------------------------------------
        public static final String URL_LOGIN = BASE_URL_LOGIN + "signin";
        public static final String URL_DOSEN = API_PATH + "dosen";
        public static final String URL_MAHASISWA = API_PATH + "mahasiswa";
        public static final String URL_INFORMASI = API_PATH + "informasi";
        public static final String URL_JUDUL_PA = API_PATH + "judulpa";
        // -----------------------------------------------------------------------------------------
        public static final String PARAMETER_DOSEN = "/{dosen}";
        public static final String PARAMETER_MAHASISWA = "/{mahasiswa}";
        public static final String PARAMETER_INFORMASI = "/{informasi}";
        public static final String PARAMETER_JUDUL_PA = "/{judulpa}";
        // -----------------------------------------------------------------------------------------

    }
}