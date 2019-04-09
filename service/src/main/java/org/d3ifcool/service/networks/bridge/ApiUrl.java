package org.d3ifcool.service.networks.bridge;

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

        public static final String BASE_URL = "https://e-denah.000webhostapp.com/"; // Link Website
        public static final String PATH_API = "api/v1"; // Link API
        // -----------------------------------------------------------------------------------------
        public static final String BASE_URL_FOTO = BASE_URL + "image/";
        public static final String BASE_URL_LOGIN = PATH_API + "/user";
        // -----------------------------------------------------------------------------------------
        public static final String URL_FOTO_DOSEN = BASE_URL_FOTO + "dsn_img/";
        public static final String URL_FOTO_MAHASISWA = BASE_URL_FOTO + "mhs_img/";
        // -----------------------------------------------------------------------------------------
        public static final String VAR_LOGIN = "signin";
        public static final String VAR_USER = "user";
        public static final String VAR_DOSEN = "dosen";
        public static final String VAR_MAHASISWA = "mahasiswa";
        public static final String VAR_KOORDINATOR_PA = "koor";
        public static final String VAR_INFORMASI = "informasi";
        public static final String VAR_JUDUL = "judul";
        public static final String VAR_KATEGORI_JUDUL = "kategori_judul";
        public static final String VAR_NOTIFIKASI = "notifikasi";
        public static final String VAR_PROYEK_AKHIR = "proyek_akhir";
        public static final String VAR_DISTINCT = "distinct";
        public static final String VAR_NILAI = "nilai";
        public static final String VAR_SIDANG = "sidang";
        public static final String VAR_BIMBINGAN = "bimbingan";
        public static final String VAR_MONEV = "monev";
        public static final String VAR_MONEV_DETAIL = "monev_detail";
        public static final String VAR_PARAMS = "parameter";
        public static final String VAR_QUERY = "query";
        // -----------------------------------------------------------------------------------------
        public static final String PATH_LOGIN = "/" + VAR_LOGIN;
        public static final String PATH_DOSEN = "/" + VAR_DOSEN;
        public static final String PATH_MAHASISWA = "/" + VAR_MAHASISWA;
        public static final String PATH_KOORDINATOR_PA = "/" + VAR_KOORDINATOR_PA;
        public static final String PATH_INFORMASI = "/" + VAR_INFORMASI;
        public static final String PATH_JUDUL = "/" + VAR_JUDUL;
        public static final String PATH_KATEGORI_JUDUL = "/" + VAR_KATEGORI_JUDUL;
        public static final String PATH_NOTIFIKASI = "/" + VAR_NOTIFIKASI;
        public static final String PATH_PROYEK_AKHIR = "/" + VAR_PROYEK_AKHIR;
        public static final String PATH_DISTINCT = "/" + VAR_DISTINCT;
        public static final String PATH_NILAI = "/" + VAR_NILAI;
        public static final String PATH_SIDANG = "/" + VAR_SIDANG;
        public static final String PATH_BIMBINGAN = "/" + VAR_BIMBINGAN;
        public static final String PATH_MONEV = "/" + VAR_MONEV;
        public static final String PATH_MONEV_DETAIL = "/" + VAR_MONEV_DETAIL;
        // -----------------------------------------------------------------------------------------
        public static final String PATH_UPDATE = "/update";
        public static final String PATH_DELETE = "/delete";
        public static final String PATH_SEARCH = "/search";
        public static final String PATH_STATUS = "/status";
        public static final String PATH_ALL = "/all";
        public static final String PATH_SORT = "/sort";
        // -----------------------------------------------------------------------------------------
        public static final String URL_LOGIN = BASE_URL_LOGIN + PATH_LOGIN;
        public static final String URL_DOSEN = PATH_API + PATH_DOSEN;
        public static final String URL_MAHASISWA = PATH_API + PATH_MAHASISWA;
        public static final String URL_KOORDINATOR_PA = PATH_API + PATH_KOORDINATOR_PA;
        public static final String URL_INFORMASI = PATH_API + PATH_INFORMASI;
        public static final String URL_JUDUL_PA = PATH_API + PATH_JUDUL;
        public static final String URL_KATEGORI_JUDUL = PATH_API + PATH_KATEGORI_JUDUL;
        public static final String URL_NOTIFIKASI = PATH_API + PATH_NOTIFIKASI;
        public static final String URL_PROYEK_AKHIR = PATH_API + PATH_PROYEK_AKHIR;
        public static final String URL_SIDANG = PATH_API + PATH_SIDANG;
        public static final String URL_BIMBINGAN = PATH_API + PATH_BIMBINGAN;
        public static final String URL_MONEV = PATH_API + PATH_MONEV;
        public static final String URL_MONEV_DETAIL = PATH_API + PATH_MONEV_DETAIL;
        // -----------------------------------------------------------------------------------------
        public static final String BASE_PARAMETER = "/{" + VAR_PARAMS + "}";
        public static final String BASE_PARAMETER_1 = "/{" + VAR_PARAMS + "1}";
        public static final String BASE_PARAMETER_2 = "/{" + VAR_PARAMS + "2}";
        public static final String PARAMETER_QUERY = "/{" + VAR_QUERY + "}";
        public static final String PARAMETER_QUERY_1 = "/{" + VAR_QUERY + "1}";
        public static final String PARAMETER_QUERY_2 = "/{" + VAR_QUERY + "2}";
        public static final String PARAMETER_USER = "/{" + VAR_USER + "}";
        public static final String PARAMETER_DOSEN = "/{" + VAR_DOSEN + "}";
        public static final String PARAMETER_MAHASISWA = "/{" + VAR_MAHASISWA + "}";
        public static final String PARAMETER_KOOR = "/{" + VAR_KOORDINATOR_PA + "}";
        public static final String PARAMETER_INFORMASI = "/{" + VAR_INFORMASI + "}";
        public static final String PARAMETER_JUDUL = "/{" + VAR_JUDUL + "}";
        public static final String PARAMETER_KATEGORI_JUDUL = "/{" + VAR_KATEGORI_JUDUL + "}";
        public static final String PARAMETER_NOTIFIKASI = "/{" + VAR_NOTIFIKASI + "}";
        public static final String PARAMETER_PROYEK_AKHIR = "/{" + VAR_PROYEK_AKHIR + "}";
        public static final String PARAMETER_SIDANG = "/{" + VAR_SIDANG + "}";
        public static final String PARAMETER_BIMBINGAN = "/{" + VAR_BIMBINGAN+ "}";
        public static final String PARAMETER_MONEV = "/{" + VAR_MONEV + "}";
        public static final String PARAMETER_MONEV_DETAIL = "/{" + VAR_MONEV_DETAIL + "}";
        // -----------------------------------------------------------------------------------------
        public static final String PARAM_DOSEN_NAMA = "dsn_nama";
        public static final String PARAM_JUDUL_STATUS = "judul.judul_status";

    }
}