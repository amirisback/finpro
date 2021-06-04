<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\notifikasi;
use Carbon\Carbon;
use Illuminate\Support\Facades\Hash;
use Intervention\Image\ImageManagerStatic as Image;
use Session;
use Excel;
use File;
use DataTables;

class NotifikasiController extends Controller
{

//KOORDINATOR=================================================================================================
    public function getPageNotifikasiKoor(){
        $getNip = Session::get('session_nip');
        $pemberitahuan = notifikasi::where('notifikasi_untuk', $getNip)->orWhere('notifikasi_untuk', 'semua')->orderBy('notifikasi_tanggal', 'DESC')->paginate(10);
        return view('koordinator.notifikasi', compact('pemberitahuan'));   
    }

    public function putNotifikasiBacaKoor(){
        $getNip = Session::get('session_nip');
        notifikasi::where('notifikasi_untuk', $getNip)->update([
        'notifikasi_baca' => '1'
    ]);
    }
//END KOORDINATOR=============================================================================================

//DOSEN=================================================================================================
    public function getPageNotifikasiDosen(){
        $getNip = Session::get('session_nip');
        $pemberitahuan = notifikasi::where('notifikasi_untuk', $getNip)->orWhere('notifikasi_untuk', 'semua')->orderBy('notifikasi_tanggal', 'DESC')->paginate(10);
        return view('dosen.notifikasi', compact('pemberitahuan'));
    }

    public function putNotifikasiBacaDosen(){
        $getNip = Session::get('session_nip');
        notifikasi::where('notifikasi_untuk', $getNip)->update([
        'notifikasi_baca' => '1'
    ]);
    }
  
//END DOSEN=============================================================================================

//MAHASISWA=================================================================================================
public function getPageNotifikasiMhs(){
    $getNim = Session::get('session_nim');
    $pemberitahuan = notifikasi::where('notifikasi_untuk', $getNim)->orWhere('notifikasi_untuk', 'semua')->orderBy('notifikasi_tanggal', 'DESC')->paginate(10);
    return view('mahasiswa.notifikasi', compact('pemberitahuan'));
}

public function putNotifikasiBacaMhs(){
    $getNim = Session::get('session_nim');
    notifikasi::where('notifikasi_untuk', $getNim)->update([
        'notifikasi_baca' => '1'
    ]);
}

//END MAHASISWA=============================================================================================
}
