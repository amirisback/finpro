<?php

namespace App\Providers;
use Illuminate\Support\Facades\View;
use Illuminate\Support\ServiceProvider;
use Illuminate\Support\Facades\Schema;
use Carbon\Carbon;
use App\Models\notifikasi;
use App\Models\mahasiswa;
use Session;
//

class AppServiceProvider extends ServiceProvider
{
    /**
     * Bootstrap any application services.
     *
     * @return void
     */
    public function boot()
    {
        //
        setlocale(LC_TIME, 'IND');
        Carbon::setLocale('IND');
        Schema::defaultStringLength(100);
        View::share('token', null); //deklarasi variable global

        //====== Composer View
        View::composer('*', function($view){
            if(Session::get('session_pengguna') === 'mahasiswa') {
                $view->with('jdl_status', mahasiswa::where('mhs_nim', Session::get('session_nim'))->whereNotNull('judul_id')->first());
                $view->with('notifikasi', notifikasi::where('notifikasi_untuk', Session::get('session_nim'))->orWhere('notifikasi_untuk', 'semua')->take(5)->orderBy('notifikasi_tanggal', 'DESC')->get());
                $view->with('jml_notif', notifikasi::where('notifikasi_untuk', Session::get('session_nim'))->orWhere('notifikasi_untuk', 'semua')->count());
                $view->with('jml_notif_baca', notifikasi::where(function($query){
                    return $query->where('notifikasi_untuk', Session::get('session_nim'))->orWhere('notifikasi_untuk', 'semua');
                })->where('notifikasi_baca', '0')->count());
                
            }else if(Session::get('session_pengguna') === 'dosen' || Session::get('session_pengguna') === 'koordinator'){
                $view->with('notifikasi', notifikasi::where('notifikasi_untuk', Session::get('session_nip'))->orWhere('notifikasi_untuk', 'semua')->take(5)->orderBy('notifikasi_tanggal', 'DESC')->get());
                $view->with('jml_notif', notifikasi::where('notifikasi_untuk', Session::get('session_nip'))->orWhere('notifikasi_untuk', 'semua')->count());
                $view->with('jml_notif_baca', notifikasi::where(function($query){
                    return $query->where('notifikasi_untuk', Session::get('session_nip'))->orWhere('notifikasi_untuk', 'semua');
                })->where('notifikasi_baca', '0')->count());
            }
        });
        //======END Composer View
    }

    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        //
    }
}
