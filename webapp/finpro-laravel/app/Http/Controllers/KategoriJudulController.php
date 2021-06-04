<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\judul;
use App\Models\kategori_judul;
use Carbon\Carbon;
use Illuminate\Support\Facades\Hash;
use Intervention\Image\ImageManagerStatic as Image;
use Session;
use Excel;
use File;
use DataTables;
class KategoriJudulController extends Controller
{
    //koordiantor

    public function getPageKategoriJudulKoor(){        
        $kategoriJudul = kategori_judul::get();
        $countJudul = judul::where('judul_status', 'tersedia')->orWhere('judul_status', 'digunakan')->orWhere('judul_status', 'pending')->get();

        return view('koordinator.judul-kategori', compact('kategoriJudul', 'countJudul'));
    }

    public function postKategoriJudulKoor(Request $request){
        $this->validate($request, [
            'form_kategoriJudul' => 'required|string|max:32',
        ]);
        kategori_judul::create([
            'kategori_nama'        => $request->form_kategoriJudul, 
        ]);
        $request->session()->flash('alert-success', 'Kategori baru berhasil ditambahkan!');
        return redirect()->back();
    }

    public function getModalUbahKategoriJudul($id){
        $model = kategori_judul::findOrFail($id);
        return view('koordinator._sub-menu.kategori-judul.form-ubah', compact('model'));
    }

    public function postHapusKategoriJudulKoor($id){
        $model = kategori_judul::findOrFail($id);
        $model->delete();
    }

    public function putUbahKategoriJudulKoor(Request $request, $id){
        $this->validate($request, [
            'form_kategoriJudul' => 'required|string|max:32',        
        ]);
        kategori_judul::where('kategori_id', $id)->update([
            'kategori_nama' => $request->form_kategoriJudul,
            'kategori_status' => $request->form_kategoriStatus
            ]);        
        
    }

    //end koordinator
}
