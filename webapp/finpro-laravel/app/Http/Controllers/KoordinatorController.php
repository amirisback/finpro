<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\mahasiswa;
use App\Models\judul;
use App\Models\dosen;
use App\Models\bimbingan;
use Carbon\Carbon;
use Illuminate\Support\Facades\Hash;
use Intervention\Image\ImageManagerStatic as Image;
use Session;
use Excel;
use File;
use DataTables;


class KoordinatorController extends Controller
{
//KOORDINATOR=============================================================================================
    public function getBeranda(){
        $jmlMahasiswa = mahasiswa::All()->where('status', 'aktif')->count();
        $jmlDosen = dosen::All()->count();
        $jmlJudul = judul::All()->where('judul_status', 'tersedia')->count();
        $jmlBbimbingan = bimbingan::All()->where('judul_id', null)->count();  //belum
        $jmlMahasiswaJudulKosong = mahasiswa::All()->where('judul_id', null)->where('status', 'aktif')->count();
        return view('koordinator.beranda', compact('jmlMahasiswa','jmlDosen','jmlJudul', 'jmlBbimbingan', 'jmlMahasiswaJudulKosong'));   
    }

    public function getModalMhsTanpaJudul(){
        $model = mahasiswa::whereNull('judul_id')->orwhereHas('tbl_judul', function($query){
            $query->where('judul_status', 'pending');
        })->get();
        return view('koordinator._sub-menu.beranda.show-modal-mhs-tanpa-judul', compact('model'));
    }
   
//END KOORDINATOR=========================================================================================

//DOSEN===================================================================================================
//END KOORDINATOR=========================================================================================
      
//MAHASISWA===============================================================================================
//END MAHASISWA===========================================================================================
}


