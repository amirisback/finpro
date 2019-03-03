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

        public static final String IP = "192.168.44.117";
        public static final String FOLDER_NAME = "finpro-android";
        public static final String BASE_URL = "http://"+IP+"/"+FOLDER_NAME+"/";
        // -----------------------------------------------------------------------------------------
        public static final String URL_DOSEN_CREATE = "";
        public static final String URL_DOSEN_READ = "";
        public static final String URL_DOSEN_DELETE = "";
        public static final String URL_DOSEN_UPDATE = "";
        // -----------------------------------------------------------------------------------------
        public static final String BASE_URL_INFORMASI = "informasi/";
        public static final String URL_INFORMASI_CREATE = BASE_URL_INFORMASI + "createInformasi.php";
        public static final String URL_INFORMASI_READ = BASE_URL_INFORMASI + "getInformasi.php";
        public static final String URL_INFORMASI_DELETE = BASE_URL_INFORMASI + "deleteInformasi.php";
        public static final String URL_INFORMASI_UPDATE = BASE_URL_INFORMASI + "updateInformasi.php";
        // -----------------------------------------------------------------------------------------

    }
}
