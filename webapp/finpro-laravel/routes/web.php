<?php
use App\Models\Judul;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/
//===============PUBLIC================//

Route::group(['middleware' => 'web'], function() {
    Route::get('/', function(){
        return redirect('/login');
    }); //redirect
    Route::get('/login', 'Auth_User\LoginController@login')->name('user.login'); //halaman Login Awal User
    Route::get('/lupa-password', 'Auth_User\LoginController@lupaPassword')->name('user.lupa.password');
    Route::post('/login', 'Auth_User\LoginController@postLogInUser')->name('user.login.submit');
    Route::get('/debug', 'AuthController@debbuging'); //halaman Login Awal User
    //Route::get('/login/admin', 'Auth_Admin\LoginController@login')->name('admin.login'); //halaman Login Awal Admin//Route::post('/login/admin', 'Auth_Admin\LoginController@postLogInAdmin')->name('admin.login.submit');
    //Route::post('/login/admin/register', 'AuthController@postRegisterAdmin')->name('koordinator.register.submit');
    //Route::get('/login/admin/register', 'AuthController@getRegister')->name('koordinator.register'); //halaman Login Awal Admin
  
    Route::middleware('dosen')->group(function(){
        Route::get('/', function(){
            return redirect('/dosen');
        }); //redirect
        //=============Auth DOSEN==============//
        Route::group(['prefix' => 'dosen'], function() {
            Route::get('/', function(){ return redirect('/dosen/beranda'); });
            Route::get('/beranda', 'DosenController@getBeranda')->name('dosen.beranda');
            Route::get('/beranda-json-bimbingan', 'BimbinganController@getJsonBimbinganBerandaDosen')->name('dosen.beranda.bimbingan.json');
                Route::get('/beranda/bimbingan/{tim}', 'BimbinganController@getDetailBimbinganBerandaDosen')->name('dosen.beranda.bimbingan.detail');
            Route::get('/beranda-json-monev', 'MonevController@getJsonMonevBerandaDosen')->name('dosen.beranda.monev.json');
                Route::get('/beranda/monev/{tim}', 'MonevController@getDetailMonevBerandaDosen')->name('dosen.beranda.monev.detail');
            Route::group(['prefix' => '/profile'], function() {
                Route::get('/', function(){ return redirect('/dosen/biodata'); });
                Route::get('/biodata', 'UserController@getPageBiodataDosen')->name('dosen.profil.biodata');
                Route::put('biodata/{nip}', 'UserController@putUbahBiodataDosen')->name('dosen.biodata.ubah');
                Route::get('/foto', 'UserController@getPageFotoProfileDosen')->name('dosen.profil.foto');
                Route::put('/foto/{nip}', 'UserController@putUbahFotoDosen')->name('dosen.foto.ubah');
                Route::get('/ubah-password', 'UserController@getPageUbahPasswordDosen')->name('dosen.profil.password');
                Route::put('/ubah-password/{username}', 'UserController@putUbahPasswordDosen')->name('dosen.password.ubah');
            }); 
            Route::get('/notifikasi', 'NotifikasiController@getPageNotifikasiDosen')->name('dosen.notifikasi.lihat');
            Route::PUT('/notifikasi-baca', 'NotifikasiController@putNotifikasiBacaDosen')->name('dosen.notifikasi.baca');
            Route::get('/informasi/lihat', 'InformasiController@getPageInformasiLihatDosen')->name('dosen.informasi.lihat');
            Route::post('/informasi/tambah', 'InformasiController@postTambahInformasiDosen')->name('dosen.buat.informasi');
            Route::get('/informasi/tambah-ubah', 'InformasiController@getPageInformasiUbahDosen')->name('dosen.informasi.tambah-ubah');
                Route::get('/informasi/tambah-ubah/detail/{id}', 'InformasiController@getModalDetailInformasiDosen')->name('dosen.informasi.tambah-ubah.detail'); 
                Route::PUT('/informasi/tambah-ubah/ubah/{id}', 'InformasiController@putUbahInformasiDosen')->name('dosen.informasi.tambah-ubah.ubah'); 
                Route::DELETE('/informasi/tambah-ubah/hapus/{id}', 'InformasiController@postHapusDetailInformasiDosen')->name('dosen.informasi.tambah-ubah.hapus'); 

            Route::group(['prefix' => '/judul-proyek-akhir'], function(){
                Route::get('/', function(){ return redirect('/dosen/judul-proyek-akhir/aktif'); });
                Route::group(['prefix' => '/aktif'], function(){
                    Route::get('/', 'JudulController@getPageJudulAktifDosen')->name('dosen.judul.aktif');

                    Route::post('/judul-proyek-akhir', 'JudulController@postJudulTambahDosen')->name('dosen.judul.tambah');
                    Route::get('/json-pengajuan', 'JudulController@getJsonjudulPengajuanDosen')->name('dosen.judul.pengajuan.json');
                        Route::get('/setuju/detail/{id}/{tim}', 'JudulController@getModalDetailPengajuanJudulDosen')->name('dosen.judul.detail');
                        Route::GET('/setuju/{id}/{tim}', 'JudulController@putModalDetailSetujuJudulDosen')->name('dosen.judul.setuju');
                        Route::GET('/tolak/{id}/{tim}', 'JudulController@putTolakJudulDosen')->name('dosen.judul.tolak');
                    Route::get('/json-berjalan', 'JudulController@getJsonJudulBerjalanDosen')->name('dosen.judul.berjalan.json');
                        Route::GET('/detail/{id}', 'JudulController@getModalDetailJudulBerjalanDosen')->name('dosen.judul-berjalan.detail');
                        Route::PUT('/ubah/{id}', 'JudulController@putUbahJudulBerjalanDosen')->name('dosen.judul-berjalan.ubah');
                        Route::DELETE('/hapus/{id}', 'JudulController@postHapusJudulBerjalanDosen')->name('dosen.judul-berjalan.hapus');
                });
                Route::group(['prefix' => '/arsip'], function(){
                    Route::get('/', 'JudulController@getPageJudulArsipDosen')->name('dosen.judul.arsip');
                    Route::get('/tampil/{id}', 'judulController@getDetailJsonJudulDosen')->name('dosen.judul.arsip.detail');
                    Route::get('/json', 'judulController@getJsonJudulArsipDosen')->name('dosen.judul_arsip.json');
                    

                });
            });
            Route::get('/bimbingan', 'BimbinganController@getPageBimbinganDosen')->name('dosen.bimbingan');
            Route::get('/bimbingan/json', 'BimbinganController@getJsonBimbinganDosen')->name('dosen.bimbingan.json');
            Route::PUT('/bimbingan-pengajuan-setuju-semua', 'BimbinganController@getSetujuiPengajuanBimbinganSemuaDosen')->name('dosen.bimbingan.pengajuan.setujui-semua');
            Route::get('/bimbingan/json-pengajuan', 'BimbinganController@getJsonPengajuanBimbinganDosen')->name('dosen.bimbingan.pengajuan.json');
                Route::get('/bimbingan/tampil-pengajuan-detail/{id}', 'BimbinganController@getModalPengajuanBimbinganDosen')->name('dosen.bimbingan.pengajuan.detail');
                    Route::GET('/bimbingan/tampil-pengajuan-setuju/{tim}', 'BimbinganController@getSetujuiPengajuanBimbinganDosen')->name('dosen.bimbingan.pengajuan.setuju');
                    Route::GET('/bimbingan/tampil-pengajuan-tolak/{tim}', 'BimbinganController@getTolakPengajuanBimbinganDosen')->name('dosen.bimbingan.pengajuan.tolak');
                Route::PUT('/bimbingan-pengajuan-setuju/{tim}', 'BimbinganController@getSetujuiPengajuanBimbinganDosen')->name('dosen.bimbingan.pengajuan.setujui-langsung');
            Route::get('/bimbingan/{proyek}/{id}', 'BimbinganController@getModalDetailBimbinganDosen')->name('dosen.bimbingan.detail');
            Route::get('/monev', 'MonevController@getPageMonevDosen')->name('dosen.monev');
            Route::get('/monev/detail/{id}', 'MonevController@getModalDetailNilaiMonevDosen')->name('dosen.monev.detail');            
            Route::get('/monev/json', 'MonevController@getJsonNilaiMonevDosen')->name('dosen.monev.json');            
            Route::get('/monev/nilai', 'MonevController@getNilaiMonevDosen')->name('dosen.monev.nilai.tampung');
            Route::get('/monev/nilai/{monev}/{judul}', 'MonevController@getPageNilaiMonevDosen')->name('dosen.monev.nilai');
            Route::post('/monev-nilai-tambah/{monev}/{tim}', 'MonevController@postNilaiMonevDosen')->name('dosen.monev.nilai.tambah');
            Route::get('/sidang', 'SidangController@getPageSidangDosen')->name('dosen.sidang');
            Route::get('/sidang-nilai', 'SidangController@getPageNilaiSidangDosen')->name('dosen.sidang.nilai');
            Route::get('/sidang-detail/{id}', 'SidangController@getModalDetailNilaiSidangDosen')->name('dosen.sidang.nilai.detail');
            Route::get('/sidang/json', 'SidangController@getJsonSidangDosen')->name('dosen.sidang.json');
            Route::post('/sidang-nilai-tambah/{tim}', 'SidangController@postTambahNilaiSidangDosen')->name('dosen.sidang.nilai.tambah');
            
            Route::get('logout', 'Auth_User\LoginController@logout');
        }); 
        //================CLOSE================//
    });

    Route::middleware('koordinator')->group(function(){
        Route::get('/', function(){
            return redirect('/koordinator');
        }); //redirect
        //=============Auth Koordinator=====================================================================//
        Route::group(['prefix' => 'koordinator'], function() {
            Route::get('/', function(){ return redirect('/koordinator/beranda'); });
            Route::get('/beranda', 'KoordinatorController@getBeranda')->name('koordinator.beranda');
            Route::get('/mhs-tanpa-judul/', 'KoordinatorController@getModalMhsTanpaJudul')->name('koordinator.mahasiswa.tanpa-judul');
            Route::get('/beranda-json-bimbingan', 'BimbinganController@getJsonBimbinganBerandaKoor')->name('koordinator.beranda.bimbingan.json');
                Route::get('/beranda/bimbingan/{tim}', 'BimbinganController@getDetailBimbinganBerandaKoor')->name('koordinator.beranda.bimbingan.detail');
            Route::get('/beranda-json-monev', 'MonevController@getJsonMonevBerandaKoor')->name('koordinator.beranda.monev.json');
                Route::get('/beranda/monev/{tim}', 'MonevController@getDetailMonevBerandaKoor')->name('koordinator.beranda.monev.detail');
            Route::group(['prefix' => '/profile'], function() 
            {   
                Route::get('/', function(){ return redirect('/koordinator/biodata'); });
                Route::get('/biodata', 'UserController@getPageBiodataKoor')->name('koordinator.profil.biodata');
                Route::put('biodata/{nip}', 'UserController@putUbahBiodataKoor')->name('koordinator.biodata.ubah');
                Route::get('/foto', 'UserController@getPageFotoProfileKoor')->name('koordinator.profil.foto');
                Route::put('/foto/{nip}', 'UserController@putUbahFotoKoor')->name('koordinator.foto.ubah');
                Route::get('/ubah-password', 'UserController@getPageUbahPassword')->name('koordinator.profil.password');
                Route::put('/ubah-password/{username}', 'UserController@putUbahPasswordKoor')->name('koordinator.password.ubah');
            }); 
            Route::group(['prefix' => '/informasi'], function() 
            {
                Route::get('/', function(){ return redirect('/koordinator/lihat'); });
                Route::get('/lihat', 'InformasiController@getPageInformasiKoor')->name('koordinator.informasi.lihat');
                Route::post('/lihat/tambah', 'InformasiController@postTambahInformasiKoor')->name('koordinator.informasi.buat');
                Route::get('/ubah', 'InformasiController@getPageUbahInformasiKoor')->name('koordinator.informasi.ubah');            
                Route::PUT('tampil-ubah/{id}', 'InformasiController@putUbahInformasiKoor')->name('koordinator.informasi.ubah.perbaharui');            
                    Route::get('tampil/{id}', 'InformasiController@getPageEditInformasiKoor')->name('koordinator.informasi.edit');
                    Route::DELETE('ubah-hapus/{id}', 'InformasiController@postHapusInformasiKoor')->name('koordinator.informasi.hapus');
            });
            Route::get('/notifikasi/{id}', 'NotifikasiController@getPageNotifikasiKoor')->name('koordinator.notifikasi.lihat');;
            Route::PUT('/notifikasi-baca', 'NotifikasiController@putNotifikasiBacaKoor')->name('koordinator.notifikasi.baca');
            Route::get('/monev', 'MonevController@getPageMonevKoor')->name('koordinator.monev');
            Route::get('/monev/json', 'MonevController@getJsonMonevKoor')->name('koordinator.monev.json');
                Route::get('/monev-detail/{nim}', 'MonevController@getModalDetailNilaiMonevKoor')->name('koordinator.monev.nilai.detail');
            Route::POST('/monev/tambah-kategori', 'MonevController@postTambahKategoriMonevKoor')->name('koordinator.monev.kategori.tambah');
                Route::get('/monev/ubah-kategori/{id}', 'MonevController@getModalUbahKategoriMonevKoor')->name('koordinator.monev.kategori.detail');
                Route::PUT('/monev/ubah-kategori/{id}', 'MonevController@putUbahKategoriMonevKoor')->name('koordinator.monev.kategori.ubah');
                Route::DELETE('/monev/hapus-kategori/{id}', 'MonevController@hapusKategoriMonevKoor')->name('koordinator.monev.kategori.hapus');
            Route::get('/sidang', 'SidangController@getPageSidangKoor')->name('koordinator.sidang');
                Route::get('/sidang-tampil/{id}', 'SidangController@getModalDetailNilaiSidangKoor')->name('koordinator.sidang.nilai.detail');
            Route::get('/sidang/json', 'SidangController@getJsonSidangKoor')->name('koordinator.sidang.json');

            Route::group(['prefix' => '/judul-proyek-akhir'], function() 
            {
                Route::group(['prefix' => '/aktif'], function(){
                    Route::get('/', 'JudulController@getPageJudulAktifKoor')->name('koordinator.judul.aktif');
                    Route::get('/kategori', 'KategoriJudulController@getPageKategoriJudulKoor')->name('koordinator.judul.kategori');
                    Route::post('/kategori-tambah', 'KategoriJudulController@postKategoriJudulKoor')->name('koordinator.judul.kategori.tambah');
                        Route::get('/kategori/{id}', 'KategoriJudulController@getModalUbahKategoriJudul')->name('koordinator.judul.kategori.tampil');
                        Route::put('/kategori/{id}', 'KategoriJudulController@putUbahKategoriJudulKoor')->name('koordinator.judul.kategori.ubah');
                        Route::delete('/kategori/{id}', 'KategoriJudulController@postHapusKategoriJudulKoor')->name('koordinator.judul.kategori.hapus');
                    Route::get('/json', 'JudulController@getJsonJudulAktifKoor')->name('koordinator.judul_aktif.json');
                    Route::post('/tambah', 'JudulController@postTambahJudulKoor')->name('koordinator.judul.tambah');
                        Route::get('/detail/{id}', 'JudulController@getPageDetailJudulKoor')->name('koordinator.judul.detail');                
                        Route::get('/tampil/{id}', 'JudulController@getPageUbahJudulKoor')->name('koordinator.judul.tampil');                
                        Route::put('/tampil-ubah/{id}', 'JudulController@putUbahJudulKoor')->name('koordinator.judul.ubah');
                        Route::DELETE('/tampil-hapus/{id}', 'JudulController@postHapusJudulKoor')->name('koordinator.judul.hapus');
                });
                Route::group(['prefix' => '/arsip'], function(){
                    Route::get('/', 'JudulController@getPageJudulArsipKoor')->name('koordinator.judul.arsip');
                    Route::get('/json-arsip', 'JudulController@getJsonJudulArsipKoor')->name('koordinator.judul_arsip.json');
                    Route::get('/json-arsip-tampil/{id}', 'JudulController@getDetailJsonJudulArsip')->name('koordinator.judul_arsip.json.detail');
                        Route::delete('/json-arsip-hapus/{id}', 'JudulController@postHapusJsonJudulArsipKoor')->name('koordinator.judul_arsip.json.hapus');

                });
            });
            Route::group(['prefix' => '/pengguna'], function() 
            {
                Route::group(['prefix' => '/mahasiswa'], function() 
                {
                //CRUD User Mahasiswa
                    Route::get('/', 'MahasiswaController@getpageUserMahasiswaKoor')->name('koordinator.pengguna.mahasiswa');
                    Route::get('/tampil', 'MahasiswaController@getModalImportDataMhsKoor')->name('koordinator.mahasiswa.import.tampil');
                    Route::post('/', 'MahasiswaController@postImportDataMhsKoor')->name('koordinator.mahasiswa.import');
                    Route::post('/tambah', 'MahasiswaController@postTambahMahasiswaKoor')->name('koordinator.mahasiswa.tambah');
                    Route::get('/json', 'MahasiswaController@getJsonMahasiswaKoor')->name('koordinator.mahasiswa.json');
                        Route::get('/detail/{nim}', 'MahasiswaController@getModalDetailMahasiswaKoor')->name('koordinator.mahasiswa.tampil');
                        Route::get('/tampil/{nim}', 'MahasiswaController@getModalUbahMahasiswaKoor')->name('koordinator.mahasiswa.edit');
                        Route::put('/tampil-ubah/{nim}', 'MahasiswaController@putUbahMahasiswaKoor')->name('koordinator.mahasiswa.ubah');
                        Route::DELETE('/hapus/{nim}', 'MahasiswaController@postHapusMahasiswaKoor')->name('koordinator.mahasiswa.hapus');
                });
                Route::group(['prefix' => '/pembimbing'], function() 
                {
                    //CRUD Pembimbing
                    Route::get('/', 'DosenController@getPageUserPembimbingKoor')->name('koordinator.pengguna.pembimbing');
                    Route::post('/', 'DosenController@postTambahPembimbingKoor')->name('koordinator.pembimbing.tambah');
                    Route::get('/json', 'DosenController@getJsonPembimbingKoor')->name('koordinator.pembimbing.json');
                        Route::get('/detail/{nip}', 'DosenController@getModelDetailPembimbingKoor')->name('koordinator.pembimbing.tampil');
                        Route::get('/tampil/{nip}', 'DosenController@getModelUbahPembimbingKoor')->name('koordinator.pembimbing.edit');
                        Route::put('/tampil-ubah/{nip}', 'DosenController@putUbahPembimbingKoor')->name('koordinator.pembimbing.ubah');
                        Route::DELETE('/hapus/{nip}', 'DosenController@postHapusPembimbingKoor')->name('koordinator.pembimbing.hapus');
                });
                Route::group(['prefix' => '/reviewer'], function() 
                {
                    //CRUD Reviewer
                    Route::get('/', 'ReviewerController@getPageUserReviewerKoor')->name('koordinator.pengguna.reviewer');
                    Route::post('/', 'ReviewerController@postTambahReviewerKoor')->name('koordinator.tambah.reviewer');
                    Route::get('/json', 'ReviewerController@getJsonReviewerKoor')->name('koordinator.reviewer.json');
                        Route::GET('/detail/{tim}', 'ReviewerController@getModalDetailReviewerKoor')->name('koordinator.reviewer.detail');                
                        Route::GET('/tampil/{tim}', 'ReviewerController@getModalUbahReviewerKoor')->name('koordinator.reviewer.tampil');                
                        Route::PUT('/tampil-ubah/{tim}', 'ReviewerController@putUbahReviewerKoor')->name('koordinator.reviewer.ubah');
                        Route::DELETE('/tampil-hapus/{tim}', 'ReviewerController@postHapusReviewerKoor')->name('koordinator.reviewer.hapus');
                });
                //-------------------------------
                Route::get('/penguji', 'KoordinatorController@getUserPenguji')->name('koordinator.pengguna.penguji');
            }); 
            Route::get('logout', 'Auth_User\LoginController@logout');
        });
        //===============CLOSE=================//
    });

    Route::middleware('mahasiswa')->group(function(){
        Route::get('/', function(){
            return redirect('/mahasiswa');
        }); //redirect//
        //===========Auth Mahasiswa============//
        Route::group(['prefix' => 'mahasiswa'], function() {
            Route::get('/', function(){ return redirect('/mahasiswa/beranda'); });
            Route::get('/beranda', 'MahasiswaController@getberanda')->name('mahasiswa.beranda');
            Route::group(['prefix' => '/profile'], function() {
                Route::get('/', function(){ return redirect('/mahasiswa/biodata'); });
                Route::get('/biodata', 'UserController@getPageBiodataMahasiswa')->name('mahasiswa.profil.biodata');
                Route::put('/biodata/{nim}', 'UserController@putUbahBiodataMahasiswa')->name('mahasiswa.profil.biodata.ubah');
                Route::get('/foto', 'UserController@getPageFotoProfileMahasiswa')->name('mahasiswa.profil.foto');
                Route::put('/foto/{nim}', 'UserController@putFotoProfileMahasiswa')->name('mahasiswa.profil.foto.ubah');
                Route::get('/ubah-password', 'UserController@getPageUbahPasswordMahasiswa')->name('mahasiswa.profil.password');
                Route::put('/ubah-password/{username}', 'UserController@putUbahPasswordMahasiswa')->name('mahasiswa.profil.password.ubah');
            }); 
            Route::get('/notifikasi/{id}', 'NotifikasiController@getPageNotifikasiMhs')->name('mahasiswa.notifikasi.lihat');
            Route::PUT('/notifikasi-baca', 'NotifikasiController@putNotifikasiBacaMhs')->name('mahasiswa.notifikasi.baca');
            Route::group(['prefix' => '/judul'], function() {
                Route::get('/', function(){ return redirect('/mahasiswa/lihat'); });
                Route::get('/tim', 'JudulController@getPageJudulTimMhs')->name('mahasiswa.judul.tim');
                Route::post('/tim/ubah', 'JudulController@postUbahDataJudulTimMhs')->name('mahasiswa.judul.ubah');
                Route::get('/lihat', 'JudulController@getPageLihatJudulMhs')->name('mahasiswa.judul.lihat');
                Route::get('/lihat/judul-mandiri', 'JudulController@getModalFormJudulMandiriMhs')->name('mahasiswa.judul.madiri.form');
                Route::post('/lihat/judul-mandiri', 'JudulController@postAjukanJudulMandiriMhs')->name('mahasiswa.judul.mandiri.ajukan');
                Route::get('/lihat/json', 'JudulController@getJsonJudulAktifMhs')->name('mahasiswa.judul_aktif.json');
                Route::get('/lihat/judul-tersedia/{id}', 'JudulController@getModalFormJudulTersediaMhs')->name('mahasiswa.judul.tersedia.form');
                Route::post('/lihat/judul-tersedia/{id}', 'JudulController@postAjukanJudulTersediaMhs')->name('mahasiswa.judul.tersedia.ajukan');
                Route::post('/auto', 'MahasiswaController@PostAutoCompleteMahasiswa')->name('mahasiswa.autocomplete.nim'); //post //belomm
                Route::get('/arsip', 'JudulController@getPageArsipJudulMhs')->name('mahasiswa.judul.arsip');            
                Route::get('/arsip/json', 'JudulController@getJsonJudulArsipMhs')->name('mahasiswa.judul_arsip.json');
                    Route::get('/arsip/{id}', 'JudulController@getModalDetailJudulArsip')->name('mahasiswar.judul.arsip.tampil');
            }); 

            Route::get('/bimbingan', 'BimbinganController@getPageBimbinganMhs')->name('mahasiswa.bimbingan');
            Route::POST('/bimbingan-tambah', 'BimbinganController@postTambahBimbinganMhs')->name('mahasiswa.bimbingan.tambah');
            Route::group(['prefix' => '/nilai'], function() {
                Route::get('/monev', 'MonevController@getPageMonevMhs')->name('mahasiswa.nilai.monev');
                Route::get('/sidang', 'SidangController@getPageSidangMhs')->name('mahasiswa.nilai.sidang');
            }); 
            Route::get('logout', 'Auth_User\LoginController@logout');      
        }); 
        //===============CLOSE=================//
    });
});


