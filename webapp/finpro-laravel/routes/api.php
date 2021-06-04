<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

// Route::middleware('auth:api')->get('/user', function (Request $request) {
//     return $request->user(); 
// });

Route::group(['prefix' => 'v1'], function() {

    Route::resource('user', 'ApiController\ApiControllerUser', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/user/signin', ['uses' => 'ApiController\ApiControllerAuth@signin']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('informasi', 'ApiController\ApiControllerInformasi', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/informasi/update/{informasi}', ['uses' => 'ApiController\ApiControllerInformasi@update']);
    Route::post('/informasi/delete/{informasi}', ['uses' => 'ApiController\ApiControllerInformasi@destroy']);
    // ----------------------------------------------------------------------------------------------------------------------------- 
    Route::resource('mahasiswa', 'ApiController\ApiControllerMahasiswa', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/mahasiswa/update/{mahasiswa}', ['uses' => 'ApiController\ApiControllerMahasiswa@update']);
    Route::post('/mahasiswa/update/judul/{mahasiswa}', ['uses' => 'ApiController\ApiControllerMahasiswa@updateJudulMahasiswa']);
    Route::post('/mahasiswa/delete/{mahasiswa}', ['uses' => 'ApiController\ApiControllerMahasiswa@destroy']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('dosen', 'ApiController\ApiControllerDosen', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/dosen/update/{dosen}', ['uses' => 'ApiController\ApiControllerDosen@update']);
    Route::post('/dosen/update/pure/{dosen}', ['uses' => 'ApiController\ApiControllerDosen@updatePure']);
    Route::post('/dosen/delete/{dosen}', ['uses' => 'ApiController\ApiControllerDosen@destroy']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('koor', 'ApiController\ApiControllerKoor', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/koor/update/{koor}', ['uses' => 'ApiController\ApiControllerKoor@update']);
    Route::post('/koor/delete/{koor}', ['uses' => 'ApiController\ApiControllerKoor@destroy']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('judul', 'ApiController\ApiControllerJudul', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/judul/update/{judul}', ['uses' => 'ApiController\ApiControllerJudul@update']);
    Route::post('/judul/delete/{judul}', ['uses' => 'ApiController\ApiControllerJudul@destroy']);
    Route::post('/judul/update/status/{judul}', ['uses' => 'ApiController\ApiControllerJudul@updateStatusJudul']);
    Route::get('/judul/search/{parameter}/{query}', ['uses' => 'ApiController\ApiControllerJudul@searchJudulBy']);
    Route::get('/judul/search/{parameter1}/{query1}/{parameter2}/{query2}', ['uses' => 'ApiController\ApiControllerJudul@searchJudulBy2']);
    Route::get('/judul/search/sum_team/{parameter1}/{query1}/{parameter2}/{query2}', ['uses' => 'ApiController\ApiControllerJudul@searchJudulTeamByTwo']);
    Route::get('/judul/search/mahasiswa/{parameter}/{query}', ['uses' => 'ApiController\ApiControllerJudul@searchJudulShowMhsBy']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('bimbingan', 'ApiController\ApiControllerBimbingan', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/bimbingan/update/{bimbingan}', ['uses' => 'ApiController\ApiControllerBimbingan@update']);
    Route::post('/bimbingan/update/status/{bimbingan}', ['uses' => 'ApiController\ApiControllerBimbingan@updateStatusBimbingan']);
    Route::post('/bimbingan/delete/{bimbingan}', ['uses' => 'ApiController\ApiControllerBimbingan@destroy']);
    Route::get('/bimbingan/search/siap_sidang/{bimbingan}', ['uses' => 'ApiController\ApiControllerBimbingan@getSiapSidang']);
    Route::get('/bimbingan/search/all/{parameter}/{query}', ['uses' => 'ApiController\ApiControllerBimbingan@getBimbinganSearchAllBy']);
    Route::get('/bimbingan/search/all/{parameter1}/{query1}/{parameter2}/{query2}', ['uses' => 'ApiController\ApiControllerBimbingan@getBimbinganSearchAllByTwo']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('monev', 'ApiController\ApiControllerMonev', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/monev/update/{monev}', ['uses' => 'ApiController\ApiControllerMonev@update']);
    Route::post('/monev/delete/{monev}', ['uses' => 'ApiController\ApiControllerMonev@destroy']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('monev_detail', 'ApiController\ApiControllerMonevDetail', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/monev_detail/update/{monev_detail}', ['uses' => 'ApiController\ApiControllerMonevDetail@update']);
    Route::post('/monev_detail/delete/{monev_detail}', ['uses' => 'ApiController\ApiControllerMonevDetail@destroy']);
    Route::get('/monev_detail/search/{parameter}/{query}/', ['uses' => 'ApiController\ApiControllerMonevDetail@searchDetailMonevBy']);
    Route::get('/monev_detail/search/{parameter1}/{query1}/{parameter2}/{query2}', ['uses' => 'ApiController\ApiControllerMonevDetail@searchDetailMonevBy2']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('proyek_akhir', 'ApiController\ApiControllerProyekAkhir', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/proyek_akhir/update/{proyek_akhir}', ['uses' => 'ApiController\ApiControllerProyekAkhir@update']);
    Route::post('/proyek_akhir/update/nilai/{proyek_akhir}', ['uses' => 'ApiController\ApiControllerProyekAkhir@updateNilaiTotal']);
    Route::post('/proyek_akhir/update/dosen/{proyek_akhir}', ['uses' => 'ApiController\ApiControllerProyekAkhir@updateDsnNip']);
    Route::post('/proyek_akhir/delete/{proyek_akhir}', ['uses' => 'ApiController\ApiControllerProyekAkhir@destroy']);
    Route::get('/proyek_akhir/search/all/{parameter}/{query}', ['uses' => 'ApiController\ApiControllerProyekAkhir@searchAllProyekAkhirBy']);
    Route::get('/proyek_akhir/search/all/{parameter1}/{query1}/{parameter2}/{query2}', ['uses' => 'ApiController\ApiControllerProyekAkhir@searchAllProyekAkhirBy2']);
    Route::get('/proyek_akhir/search/distinct', ['uses' => 'ApiController\ApiControllerProyekAkhir@getProyekAkhirDistinct']);
    Route::get('/proyek_akhir/search/distinct/{parameter}/{query}', ['uses' => 'ApiController\ApiControllerProyekAkhir@searchDistinctProyekAkhirBy']);
    Route::get('/proyek_akhir/search/distinct/{parameter1}/{query1}/{parameter2}/{query2}', ['uses' => 'ApiController\ApiControllerProyekAkhir@searchDistinctProyekAkhirBy2']);
    Route::get('/proyek_akhir/search/distinct/sum_team/{parameter1}/{query1}/{parameter2}/{query2}', ['uses' => 'ApiController\ApiControllerProyekAkhir@searchDistinctProyekAkhirTimBy2']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('notifikasi', 'ApiController\ApiControllerNotifikasi', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/notifikasi/update/{notifikasi}', ['uses' => 'ApiController\ApiControllerNotifikasi@update']);
    Route::post('/notifikasi/delete/{notifikasi}', ['uses' => 'ApiController\ApiControllerNotifikasi@destroy']);
    Route::get('/notifikasi/search/all/{parameter}/{query}', ['uses' => 'ApiController\ApiControllerNotifikasi@searchNotifikasiBy']);
    Route::get('/notifikasi/search/all/{parameter1}/{query1}/{parameter2}/{query2}', ['uses' => 'ApiController\ApiControllerNotifikasi@searchNotifikasiBy2']);
    Route::get('/notifikasi/sort/{query1}/{query2}', ['uses' => 'ApiController\ApiControllerNotifikasi@sortNotifikasi']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('sidang', 'ApiController\ApiControllerSidang', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/sidang/update/{sidang}', ['uses' => 'ApiController\ApiControllerSidang@update']);
    Route::post('/sidang/delete/{sidang}', ['uses' => 'ApiController\ApiControllerSidang@destroy']);
    Route::get('/sidang/search/all/{parameter}/{query}', ['uses' => 'ApiController\ApiControllerSidang@searchAllSidangBy']);
    Route::get('/sidang/search/all/{parameter1}/{query1}/{parameter2}/{query2}', ['uses' => 'ApiController\ApiControllerSidang@searchAllSidangByTwo']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('kategori_judul', 'ApiController\ApiControllerKategori', ['except' => ['create', 'edit', 'update', 'destroy']]);
    Route::post('/kategori_judul/update/{kategori_judul}', ['uses' => 'ApiController\ApiControllerKategori@update']);
    Route::post('/kategori_judul/delete/{kategori_judul}', ['uses' => 'ApiController\ApiControllerKategori@destroy']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('jadwal_kegiatan', 'ApiController\ApiControllerKegiatan', ['except' => ['create', 'edit', 'update', 'destroy']]);   
    Route::post('/jadwal_kegiatan/update/{jadwal_kegiatan}', ['uses' => 'ApiController\ApiControllerKegiatan@update']);
    Route::post('/jadwal_kegiatan/delete/{jadwal_kegiatan}', ['uses' => 'ApiController\ApiControllerKegiatan@destroy']);
    // -----------------------------------------------------------------------------------------------------------------------------
    Route::resource('kuota_dosen', 'ApiController\ApiControllerKuotaDosen', ['except' => ['create', 'edit', 'update', 'destroy']]);   
    Route::post('/kuota_dosen/update/{kuota_dosen}', ['uses' => 'ApiController\ApiControllerKuotaDosen@update']);
    Route::post('/kuota_dosen/delete/{kuota_dosen}', ['uses' => 'ApiController\ApiControllerKuotaDosen@destroy']);
    
});


