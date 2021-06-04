<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\notifikasi;
use App\Models\judul;
use App\Models\proyek_akhir;
use App\Models\sidang;
use App\Models\bimbingan;
use Carbon\Carbon;
use Illuminate\Support\Facades\Hash;
use Intervention\Image\ImageManagerStatic as Image;
use Illuminate\Support\Facades\Redirect;
use Session;
use Excel;
use File;
use DataTables;

class SidangController extends Controller
{
    //KOORDINATOR===========================================================================================
    public function getPageSidangKoor(){

        return view('koordinator.sidang');
    }

    public function getJsonSidangKoor(){        
        $model = proyek_akhir::whereNotNull('dsn_nip')->whereHas('tbl_judul', function($query){
            $query->where('judul_status', 'digunakan');
        })->get();
        return DataTables::of($model)
        ->addColumn('action', function ($model){
            return view('layout._action-r',[
            'model' => $model,
            'url_show' => route('koordinator.sidang.nilai.detail', $model->proyek_akhir_id),
            'data_show' => "Detail nilai Sidang ". $model->tbl_mahasiswa->mhs_nama,
            ]);
        })   
        ->addColumn('nama_mahasiswa', function ($model){

           return $model->tbl_mahasiswa->mhs_nama;         
        })  
        ->addColumn('pembimbing', function ($model){

            return $model->tbl_judul->tbl_dosen->dsn_kode;         
        })        
        ->addColumn('reviewer', function ($model){

            return $model->tbl_dosen->dsn_kode;         
        })   
        ->addColumn('nilai_total', function ($model){

            if(empty($model->tbl_sidang->nilai_total)){
                return "kosong";
            }else{
                return $model->tbl_sidang->nilai_total;
            }            
        })    
        ->addColumn('status_sidang', function ($model){
            if(empty($model->tbl_sidang->sidang_status)){
                return "<a class='btn btn-danger'>belum lulus</a>";
            }else{
                return "<a class='btn btn-success'>".$model->tbl_sidang->sidang_status."</a>";
            }    
        })    
        ->addIndexColumn()
        ->rawColumns(['action', 'status_sidang'])
        ->make(true);
    }

    public function getModalDetailNilaiSidangKoor($id){
        $detailSidang = proyek_akhir::where('proyek_akhir_id', $id)->first();
        return view('koordinator._sub-menu.sidang.show-modal-detail-nilai', compact('detailSidang'));
    }

    //END KOORDINATOR===========================================================================================

    //DOSEN===========================================================================================
    public function getPageSidangDosen(){
        
        $selectJudul = judul::where('dsn_nip',  Session::get('session_nip'))->whereHas('tbl_proyek_akhir', function ($query) {
            $query->whereNotNull('dsn_nip');
        })->get();
        return view('dosen.sidang', compact('selectJudul'));   
    }

    
    public function getPageNilaiSidangDosen(Request $request){
        $nipDosen = Session::get('session_nip');
        $waktu_sekarang = Carbon::now()->formatLocalized('%A, %d %B %Y '); //kamis, 14 Maret 2019 12:00
        $this->validate($request, [
            'form_bimbinganJudul' => 'required',
        ]);
        $idJudul = $request->form_bimbinganJudul;
        $proyekAkhir = proyek_akhir::with('tbl_bimbingan')->with('tbl_sidang')->where('judul_id', $idJudul)->whereHas('tbl_judul', function ($query) use ($nipDosen){
            $query->where('dsn_nip', $nipDosen);
        })->get();
        $tempProyekAkhir =  $proyekAkhir->first();
        $namaTim = $tempProyekAkhir->nama_tim;        
        $countBimbingan = $tempProyekAkhir->tbl_bimbingan->count();

        $cekNilaiSidang = '';
        if(empty($tempProyekAkhir->tbl_sidang)){
            $cekNilaiSidang = 'insert';
        }else{
            $cekNilaiSidang = 'update';
        }

        $bimbinganCount = bimbingan::whereHas('tbl_proyek_akhir', function($query) use ($namaTim){
            $query->where('nama_tim', $namaTim);
        })->count();

        if($bimbinganCount < 16){
            $request->session()->flash('alert-danger', 'Jumlah Bimbingan kurang dari 16 kali');            
        }
        
        return view('dosen.sidang-nilai', compact('proyekAkhir', 'namaTim', 'waktu_sekarang', 'countBimbingan', 'cekNilaiSidang'));   
    }

    public function postTambahNilaiSidangDosen(Request $request, $tim){

        $nilaiProposal = request()->input('nilaiProposal');
        $nilaiPembimbing = request()->input('nilaiPembimbing');
        $nilaiPenguji1 = request()->input('nilaiPenguji1');
        $nilaiPenguji2 = request()->input('nilaiPenguji2');
        $nilaiTotal = request()->input('nilaiTotal');
        $reviewSidang = request()->input('reviewSidang');
        $statusSidang = request()->input('statusSidang');

        $cekData = proyek_akhir::where('nama_tim', $tim)->whereHas('tbl_sidang', function($query){
            $query->whereNotNull('proyek_akhir_id');
        })->get();

        if($cekData->count() === 0 ){
            foreach ($nilaiProposal as $idProyek => $proposal) {            
                sidang::create([
                    'sidang_review'         => '-',
                    'sidang_tanggal'        => Carbon::now(),
                    'nilai_proposal'        => $proposal,
                    'sidang_status'         => '-',
                    'proyek_akhir_id'       => $idProyek,
                    ]);            
            }   

            foreach ($nilaiPembimbing as $idProyek => $pembimbing) {            
                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'nilai_pembimbing'      => $pembimbing,
                    ]);            
            }   

            foreach ($nilaiPenguji1 as $idProyek => $penguji1) {            
                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'nilai_penguji_1'      => $penguji1,
                    ]);            
            }

            foreach ($nilaiPenguji2 as $idProyek => $penguji2) {            
                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'nilai_penguji_2'      => $penguji2,
                    ]);            
            }
            
            foreach ($reviewSidang as $idProyek => $review) {      
                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'sidang_Review'      => $review,
                    ]);            
            } 

            foreach ($statusSidang as $idProyek => $status) {    
                $nilai = sidang::where('proyek_akhir_id', $idProyek)->first();
                $total = (($nilai->nilai_proposal * 10 ) / 100) + (($nilai->nilai_pembimbing * 50) / 100) + (($nilai->nilai_penguji_1 + $nilai->nilai_penguji_2)/2) * 0.4;

                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'sidang_status'         => $status,
                    'nilai_total'           => $total,
                    ]);  

                $notif = proyek_akhir::where('proyek_akhir_id', $idProyek)->first();
                notifikasi::create([  //untuk anggota TIM
                    'notifikasi_dari'          => Session::get('session_nama'), 
                    'notifikasi_untuk'         => $notif->mhs_nim,
                    'notifikasi_kategori'      => "Nilai Sidang telah Diinputkan", 
                    'notifikasi_deskripsi'     => "Nilai Sidang anda telah diinputkan oleh Dosen Pembimbing",
                    'notifikasi_tanggal'       => Carbon::now()
                ]);
            } 

            $request->session()->flash('alert-success', 'Nilai Sidang Berhasil Dimasukan');            
            return Redirect::route('dosen.sidang');

        }else{
            foreach ($nilaiProposal as $idProyek => $proposal) {            
                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'nilai_proposal'        => $proposal,
                    ]);            
            }   

            foreach ($nilaiPembimbing as $idProyek => $pembimbing) {            
                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'nilai_pembimbing'      => $pembimbing,
                    ]);            
            }   

            foreach ($nilaiPenguji1 as $idProyek => $penguji1) {            
                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'nilai_penguji_1'      => $penguji1,
                    ]);            
            }

            foreach ($nilaiPenguji2 as $idProyek => $penguji2) {            
                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'nilai_penguji_2'      => $penguji2,
                    ]);            
            }
            
            foreach ($reviewSidang as $idProyek => $review) {            
                sidang::where('proyek_akhir_id', $idProyek)->update([
                    'sidang_Review'      => $review,
                    ]);            
            } 

            foreach ($statusSidang as $idProyek => $status) {            
                $nilai = sidang::where('proyek_akhir_id', $idProyek)->first();
                //loop update nilai
                //foreach($nilaiSidang as $index => $nilai){
                    $total = (($nilai->nilai_proposal * 10 ) / 100) + (($nilai->nilai_pembimbing * 50) / 100) + (($nilai->nilai_penguji_1 + $nilai->nilai_penguji_2)/2) * 0.4;
                    
                    sidang::where('proyek_akhir_id', $idProyek)->update([
                        'sidang_status'         => $status,
                        'nilai_total'           => $total,
                        ]);  

                    notifikasi::create([
                        'notifikasi_dari'          => Session::get('session_nama'), 
                        'notifikasi_untuk'         => $nilai->tbl_proyek_akhir->mhs_nim,
                        'notifikasi_kategori'      => "Nilai Sidang telah Diubah", 
                        'notifikasi_deskripsi'     => "Nilai Sidang telah diubah oleh Dosen Pembimbing",
                        'notifikasi_tanggal'       => Carbon::now()
                        ]);
                //}
                $request->session()->flash('alert-success', 'Nilai Sidang Berhasil Ubah');
                return Redirect::route('dosen.sidang');
            }
        }        
        
    }

    public function getJsonSidangDosen(){
        $dsnNip = Session::get('session_nip');
        $model = proyek_akhir::whereNotNull('dsn_nip')->whereHas('tbl_judul', function($queryJudul) use ($dsnNip){
            $queryJudul->where('judul_status', 'digunakan')->whereHas('tbl_dosen', function($queryDosen) use ($dsnNip){
                $queryDosen->where('dsn_nip', $dsnNip);
            });
        })->get();
        return DataTables::of($model)
        ->addColumn('action', function ($model){
            return view('layout._action-r',[
            'model' => $model,
            'url_show' => route('dosen.sidang.nilai.detail', $model->proyek_akhir_id),
            'data_show' => "Detail nilai Sidang ". $model->tbl_mahasiswa->mhs_nama,
            ]);
        })   
        ->addColumn('nama_mahasiswa', function ($model){

           return $model->tbl_mahasiswa->mhs_nama;         
        })  
        ->addColumn('pembimbing', function ($model){

            return $model->tbl_judul->tbl_dosen->dsn_kode;         
        })        
        ->addColumn('nilai_total', function ($model){
            if(empty($model->tbl_sidang->nilai_total)){
                return "kosong";
            }else{
                return $model->tbl_sidang->nilai_total;
            }            
        })    
        ->addColumn('status_sidang', function ($model){
            if(empty($model->tbl_sidang->sidang_status) || $model->tbl_sidang->sidang_status === "-"){
                return "<a class='btn btn-danger'>belum lulus</a>";
            }else{
                return "<a class='btn btn-success'>".$model->tbl_sidang->sidang_status."</a>";
            }    
        })    
        ->addIndexColumn()
        ->rawColumns(['action', 'status_sidang'])
        ->make(true);
    }

    public function getModalDetailNilaiSidangDosen($id){
        $detailSidang = proyek_akhir::where('proyek_akhir_id', $id)->first();
        return view('dosen._sub-menu.sidang.show-modal-detail-nilai', compact('detailSidang'));
    }

//END DOSEN===========================================================================================

//MAHASISWA=============================================================================================    
    public function getPageSidangMhs(){
        $getNim = Session::get('session_nim');
        $nilaiSidang = sidang::whereHas('tbl_proyek_akhir', function ($query) use ($getNim){
            $query->where('mhs_nim', $getNim);
        })->first();

        return view('mahasiswa.sidang', compact('nilaiSidang'));   
    }

//END MAHASISWA=========================================================================================
}
