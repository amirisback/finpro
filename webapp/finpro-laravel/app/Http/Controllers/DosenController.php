<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\judul;
use App\Models\proyek_akhir;
use App\Models\mahasiswa;
use App\Models\user;
use App\Models\dosen;
use Intervention\Image\ImageManagerStatic as Image;
use File;
use Session;
use DataTables;
use Carbon\Carbon;
use View;
use Illuminate\Support\Facades\Hash;

class DosenController extends Controller
{
//KOORDINATOR============================================================================================

    public function getPageUserPembimbingKoor()
    {
        $dosens = dosen::all();
        return view('koordinator.user_pembimbing', compact('dosens'));   
    }

    public function postTambahPembimbingKoor(Request $request)
    {
        $this->validate($request, [ 
            'nama_dosen' => 'required|min:5|max:63',
            'nip_dosen' => 'required|min:5|max:12|unique:dosen,dsn_nip|unique:user,username',
            'kode_dosen' => 'required|min:2|max:4', 
        ]);
        user::create([
            'username' => $request->nip_dosen,
            'password' => bcrypt($request->nip_dosen),
            'pengguna' => "dosen",
        ]);

        dosen::create([
            'dsn_nip'       => $request->nip_dosen,
            'dsn_nama'      => $request->nama_dosen,
            'dsn_kode'      => strtoupper($request->kode_dosen),
            'dsn_foto'      => "default-dosen.jpg",
            'username'      => $request->nip_dosen,
        ]);
        
        $request->session()->flash('alert-success', 'Dosen '.$request->nama_dosen. ' berhasil ditambahkan!');
        return redirect()->back();
    }

    public function getJsonPembimbingKoor(){
        $model = dosen::All();
        
        return DataTables::of($model)
        ->addColumn('action', function ($model){

            $pembimbing = dosen::where('dsn_nip', $model->dsn_nip)->whereHas('tbl_judul', function ($query){
                $query->whereNotNull('dsn_nip');
            })->first();

            if($pembimbing){
                return view('layout._action-ru',[
                    'model' => $model,
                    'url_edit' => route('koordinator.pembimbing.edit', $model->dsn_nip),
                    'data_edit' => "Edit Pembimbing ".$model->dsn_nama,
                    'url_show' => route('koordinator.pembimbing.tampil', $model->dsn_nip),
                    'data_show' => "Detail Pembimbing ". $model->dsn_nama,
                ]);
            }else{
                return view('layout._action-rud',[
                    'model' => $model,
                    'url_edit' => route('koordinator.pembimbing.edit', $model->dsn_nip),
                    'data_edit' => "Edit Pembimbing ".$model->dsn_nama,
                    'url_show' => route('koordinator.pembimbing.tampil', $model->dsn_nip),
                    'data_show' => "Detail Pembimbing ". $model->dsn_nama,
                    'url_delete' => route('koordinator.pembimbing.hapus', $model->dsn_nip),
                    'data_delete' => "Hapus Pembimbing ".$model->dsn_nama
                ]);
            }
          
        })
        ->addColumn('jml_judul_bimbingan', function ($model){
            return judul::where('dsn_nip', $model->dsn_nip)->where('judul_status', 'digunakan')->count()." Judul" ;
        })
        ->addColumn('jml_judul_tersedia', function ($model){ 
            return judul::where('dsn_nip', $model->dsn_nip)->where('judul_status', 'tersedia')->count()." Judul" ;
        })
        ->addColumn('jml_bimbingan', function ($model){ 
            return $model->batas_bimbingan." Tim";
        })
        ->addColumn('jml_reviewer', function ($model){ 
            return $model->batas_reviewer." Tim";
        })
        ->addIndexColumn()
        ->rawColumns(['action'])
        ->make(true);   
    }

    public function getModelDetailPembimbingKoor($nip){
        $model = dosen::findOrFail($nip);
        return view('koordinator._sub-menu.pembimbing.show-detail', compact('model'));
    }

    public function getModelUbahPembimbingKoor($nip){
        $model = dosen::findOrFail($nip);
        return view('koordinator._sub-menu.pembimbing.form-ubah', compact('model'));
    }

    public function putUbahPembimbingKoor(Request $request, $nip){
        $model = dosen::findOrFail($nip);
        $proyekAkhirCount = proyek_akhir::distinct('judul_id')->get(['judul_id', 'dsn_nip']);   
        $minReviewer = $proyekAkhirCount->where('dsn_nip', $model->dsn_nip)->count();

        $this->validate($request, [
            'dsn_nama' => 'required|string|min:5|max:255',
            'dsn_kode' => 'required|string|min:2|max:5',            
            'batas_bimbingan' => 'required|integer|max:10',  
            'batas_reviewer' => 'required|integer|max:10|min:'.$minReviewer,  
        ]);
        $model->update($request->all());
    }

    public function postHapusPembimbingKoor($nip){
            $model = dosen::findOrFail($nip);
            $model->delete();
    }

//END KOORDINATOR=========================================================================================

//DOSEN===================================================================================================
    public function getBeranda(){   
        $jmlMahasiswa = mahasiswa::where('status', 'aktif')->whereHas('tbl_judul', function ($query) {
            $query->where('dsn_nip', Session::get('session_nip'))->where('judul_status', 'digunakan');
        })->get()->count();
        $jmlReviewer = proyek_akhir::where('dsn_nip', Session::get('session_nip'))->distinct()->count('nama_tim');
        $jmlJudul = judul::where('dsn_nip', Session::get('session_nip'))->where('judul_status', 'tersedia')->orWhere('judul_status', 'digunakan')->where('judul_status', 'pending')->count();
        return view('dosen.beranda', compact('jmlMahasiswa', 'jmlReviewer', 'jmlJudul'));   
    }

//END KOORDINATOR=========================================================================================
      
//MAHASISWA===============================================================================================
//END MAHASISWA===========================================================================================
}
        

    


