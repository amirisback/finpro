<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\notifikasi;
use App\Models\informasi;
use Carbon\Carbon;
use Illuminate\Support\Facades\Hash;
use Intervention\Image\ImageManagerStatic as Image;
use Session;
use Excel;
use File;
use DataTables;
class InformasiController extends Controller
{

//KOORDINATOR=======================================================
    public function getPageInformasiKoor(){
        $informasiPagination = informasi::orderBy('informasi_waktu', 'desc')->paginate(3);
        return view('koordinator.informasi_lihat', compact('informasiPagination'));   
    }

  
    public function postTambahInformasiKoor(Request $request){
        $this->validate($request,[
            'form_informasiJudul'       => 'required|max:255',
            'form_informasiDeskripsi'   => 'required',
        ]);
        informasi::create([
            'informasi_judul'   =>$request->form_informasiJudul,
            'informasi_isi'     =>$request->form_informasiDeskripsi,
            'penerbit'          =>Session::get('session_nama'),
            'informasi_waktu'   =>Carbon::now()
        ]);

        notifikasi::create([  //untuk Semua User
            'notifikasi_dari'          => "[koordinator] ".Session::get('session_nama'), 
            'notifikasi_untuk'         => "semua",
            'notifikasi_kategori'      => "[koordinator] ".Session::get('session_nama')." Mengirim Informasi baru", 
            'notifikasi_deskripsi'     => $request->form_informasiJudul,
            'notifikasi_tanggal'       => Carbon::now(),
            'notifikasi_baca'          => '1'
        ]); 
        $request->session()->flash('alert-success', 'Informasi baru  berhasil ditambahkan!');
        return redirect()->back();
    }

    public function getPageUbahInformasiKoor(){
        $informasi = informasi::all();
        return view('koordinator.informasi_ubah', compact('informasi'));   
    }

    public function putUbahInformasiKoor(Request $request, $id){
        $this->validate($request, [
            'informasi_judul' => 'required|string|min:5|max:255',
            'informasi_isi' => 'required|string|min:5|max:255',
        ]);
        $model = informasi::findOrFail($id);
        $model->update($request->all());
    }

    public function getPageEditInformasiKoor($id)
    {
        $model = informasi::findOrFail($id);
        return view('koordinator._sub-menu.informasi.form', compact('model'));
    }

    public function postHapusInformasiKoor($id)
    {
        $model = informasi::findOrFail($id);
        $model->delete();
        return redirect()->back();
    }

//END KOORDINATOR=======================================================


//DOSEN=================================================================

    public function getPageInformasiLihatDosen(){
        $informasi = informasi::orderBy('informasi_waktu', 'desc')->paginate(4);
        return view('dosen.informasi', compact('informasi'));   
    }
    
    public function getPageInformasiUbahDosen(){
        //$waktu_sekarang = Carbon::now()->formatLocalized('%A, %d %B %Y %H:%M'); //kamis, 14 Maret 2019 12:00
        $informasi = informasi::where('penerbit',Session::get('session_nama'))->orderBy('informasi_waktu', 'asc')->get();
        return view('dosen.informasi-ubah', compact('informasi'));   
    }

    public function getModalDetailInformasiDosen($id){
        $model = informasi::findOrFail($id);
        return view('dosen._sub-menu.informasi._modal-ubah', compact('model'));
    }

    public function putUbahInformasiDosen(Request $request, $id){
        $this->validate($request, [
            'informasi_judul' => 'required|string|min:5|max:255',
            'informasi_isi' => 'required|string|min:5|max:255',
        ]);
        $model = informasi::findOrFail($id);
        $model->update($request->all());
    }

    public function postHapusDetailInformasiDosen($id){
        informasi::findOrFail($id)->delete();
    }


    public function postTambahInformasiDosen(Request $request){
        $this->validate($request,[
            'form_informasiJudul'       => 'required|max:255',
            'form_informasiDeskripsi'   => 'required',
        ]);
        $publisher = Session::get('session_nama');
        informasi::create([
            'informasi_judul'   => $request->form_informasiJudul,
            'informasi_isi'     => $request->form_informasiDeskripsi,
            'penerbit'          => $publisher,
            'informasi_waktu'   => Carbon::now()
        ]);

        notifikasi::create([  //untuk Semua User
            'notifikasi_dari'          => Session::get('session_nama'), 
            'notifikasi_untuk'         => "semua",
            'notifikasi_kategori'      => Session::get('session_nama')." Mengirim Informasi baru", 
            'notifikasi_deskripsi'     => $request->form_informasiJudul,
            'notifikasi_tanggal'       => Carbon::now(),
            'notifikasi_baca'          => '1'
        ]);     
        $request->session()->flash('alert-success', 'Informasi Baru berhasil Ditambahkan!');
        return redirect()->back();
    }   
//END DOSEN==============================================================

//MAHASISWA=================================================================
    //di MahasiswaController
//END MAHASISWA=============================================================

}
