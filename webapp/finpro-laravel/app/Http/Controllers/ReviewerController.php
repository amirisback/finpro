<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\notifikasi;
use App\Models\proyek_akhir;
use App\Models\dosen;
use Carbon\Carbon;
use Illuminate\Support\Facades\Hash;
use Intervention\Image\ImageManagerStatic as Image;
use Session;
use Excel;
use File;
use DataTables;
class ReviewerController extends Controller
{
//KOORDINATOR=================================================================================================
    public function getPageUserReviewerKoor(){
        $dosenAll = dosen::All();   
        $proyekAkhirCount = proyek_akhir::distinct('judul_id')->get(['judul_id', 'dsn_nip']);        

        $dosen= array();
        $temp = array();   
        foreach($dosenAll as $dsn){
            $jmlDosenKuota = $dsn->batas_reviewer - $proyekAkhirCount->where('dsn_nip', $dsn->dsn_nip)->count();
            if($jmlDosenKuota == '0'){
                $temp[] = $dsn->dsn_nip;
            }       
        }

        $dosen = dosen::whereNotIn('dsn_nip', $temp)->get();        
        $proyek_akhir = proyek_akhir::where('dsn_nip', null)->whereHas('tbl_judul', function ($query) {
            $query->where('judul_status', 'digunakan');
        })->distinct()->get(['judul_id']);            
        $proyekAkhir_detail = proyek_akhir::whereNotNull('dsn_nip')->distinct()->get(['dsn_nip', 'judul_id']);
        return view('koordinator.user_reviewer', compact('dosen', 'proyek_akhir', 'proyekAkhir_detail', 'proyekAkhirCount'));   
    }

    public function postTambahReviewerKoor(Request $request)
    {
        $this->validate($request, [
            'nip_reviewer' => 'required',
            'id_judul' => 'required',
        ]);

        $splitReviewer  = explode('_&_', $request->nip_reviewer); //pisah karakter berdasarkan space
        $splitReviewer_nip = $splitReviewer[0];  //NIP     
        $splitReviewer_nama = $splitReviewer[1];  //nama
        $splitJudul =explode('_&_', $request->id_judul);
        $splitJudul_id = $splitJudul[0];  //id judul 
        $splitJudul_nip = $splitJudul[1];  //NIP 
        $getToken = "REV".uniqid();

        if($splitJudul_nip === $splitReviewer_nip){
            $request->session()->flash('alert-warning', 'Reviewer dan dosen pembimbing Tidak boleh sama!');
            return redirect()->back();
        }
        
        $proyekAkhir = proyek_akhir::where('dsn_nip', null)->where('judul_id', $splitJudul_id)->whereHas('tbl_judul', function ($query) use ($splitJudul_nip) {
                            $query->where('judul_status', 'digunakan')->where('dsn_nip', $splitJudul_nip);
                        })->get();

        foreach($proyekAkhir as $index => $proyek){
            proyek_akhir::where('proyek_akhir_id', $proyek->proyek_akhir_id)->update([
                'dsn_nip' => $splitReviewer_nip
            ]);

            notifikasi::create([  //untuk anggota TIM
                'notifikasi_dari'          => "[Koordinator] ".Session::get('session_nama'), 
                'notifikasi_untuk'         => $proyek->mhs_nim,
                'notifikasi_kategori'      => "Pemetaan Dosen Reviewer", 
                'notifikasi_deskripsi'     => "Dosen ".$proyek->dsn_nip." telah Menjadi Reviewer Tim anda",
                'notifikasi_tanggal'       => Carbon::now()
            ]);
        }

        $dataTim = proyek_akhir::where('judul_id', $splitJudul_id)->where('dsn_nip', $splitReviewer_nip)->first();
        $notifDeskripsiReviewer = "Anda telah dijadikan Reviewer untuk Tim $dataTim->nama_tim dengan judul ".$dataTim->tbl_judul->judul_nama.", Kategori ".$dataTim->tbl_judul->tbl_kategori_judul->kategori_nama;
        notifikasi::create([  //untuk calon Reviewer
            'notifikasi_dari'          => "[Koordinator] ".Session::get('session_nama'), 
            'notifikasi_untuk'         =>  $splitReviewer_nip,
            'notifikasi_kategori'      => "Pemetaan Dosen Reviewer", 
            'notifikasi_deskripsi'     => $notifDeskripsiReviewer,
            'notifikasi_tanggal'       => Carbon::now()
        ]);   

        $request->session()->flash('alert-success', 'Reviewer '.$splitReviewer_nama. ' berhasil ditambahkan!');
        return redirect()->back();
    }

    public function getJsonReviewerKoor(){
        $model = proyek_akhir::whereNotNull('dsn_nip')->distinct()->whereHas('tbl_judul', function($query){
            $query->where('judul_status', 'digunakan');
        })->get(['judul_id','nama_tim', 'dsn_nip']);
        return DataTables::of($model)
        ->addColumn('action', function ($model){
            $reviewer = proyek_akhir::where('nama_tim', $model->nama_tim)->whereHas('tbl_detail_monev', function($query){
                $query->whereNotNull('monev_detail_id');
            })->first();

            if($reviewer){
                return view('layout._action-ru',[
                    'model' => $model,
                    'url_show' => route('koordinator.reviewer.detail', $model->nama_tim),
                    'data_show' => 'Detail Reviewer '.$model->tbl_dosen->dsn_nama,
                    'url_edit' => route('koordinator.reviewer.tampil', $model->nama_tim),
                    'data_edit' => 'Ubah Reviewer '.$model->tbl_dosen->dsn_nama,
                ]);

            }else{
                return view('layout._action-rud',[
                    'model' => $model,
                    'url_show' => route('koordinator.reviewer.detail', $model->nama_tim),
                    'data_show' => 'Detail Reviewer '.$model->tbl_dosen->dsn_nama,
                    'url_edit' => route('koordinator.reviewer.tampil', $model->nama_tim),
                    'data_edit' => 'Ubah Reviewer '.$model->tbl_dosen->dsn_nama,
                    'url_delete' => route('koordinator.reviewer.hapus', $model->nama_tim),
                    'data_delete' => 'Hapus Reviewer '.$model->tbl_dosen->dsn_nama
                ]);
            }
        })
        ->addColumn('judul', function ($model){
            return $model->tbl_judul->judul_nama;                
        })
        ->addColumn('tim_kelompok', function ($model){
            return $model->nama_tim;                
        })
        ->addColumn('pembimbing', function ($model){
            return $model->tbl_judul->tbl_dosen->dsn_nama;                
        })
        ->addColumn('reviewer', function ($model){
            if(!is_null($model->dsn_nip))
            {
                return $model->tbl_dosen->dsn_nama;                
            }else{
                return '-';
            }  
        })
        ->addIndexColumn()
        ->rawColumns(['action'])
        ->make(true);   
    }

    public function getModalDetailReviewerKoor($tim){
        $model = proyek_akhir::where('nama_tim', $tim)->first();
        $mahasiswa = proyek_akhir::where('nama_tim', $tim)->get();
        return view('koordinator._sub-menu.reviewer.show', compact('model', 'mahasiswa'));
    }

    public function getModalUbahReviewerKoor($tim){
        $proyekAkhirCount = proyek_akhir::distinct('judul_id')->get(['judul_id', 'dsn_nip']); 
      
        $model = proyek_akhir::where('nama_tim', $tim)->first();
        $mahasiswa = proyek_akhir::where('nama_tim', $tim)->get();
        $dosen = dosen::where('dsn_nip', '!=',  $model->tbl_judul->dsn_nip)->get();
        $temp = array();
        foreach($dosen as $dsn){  //cek dosen kuota reviewer
            $tempDosen='';
            if(empty($proyekAkhirCount->where('dsn_nip', $dsn->dsn_nip))){
                $tempDosen = 0;
            }else{
                $tempDosen = $proyekAkhirCount->where('dsn_nip', $dsn->dsn_nip)->count();
            }
            $countDosen = $dsn->batas_reviewer - $tempDosen;
            if($countDosen == '0'){
                $temp[] = $dsn->dsn_nip;
            }
        }

        $reviewer = dosen::whereNotIn('dsn_nip', $temp)->get();     

        $selectReviewer= [];
        if($model->dsn_nip !== null){
            foreach($reviewer as $review){
                if(empty($proyekAkhirCount->where('dsn_nip', $review->dsn_nip))){
                    $tempDosen = 0;
                }else{
                    $tempDosen = $proyekAkhirCount->where('dsn_nip', $review->dsn_nip)->count();
                }
                $selectReviewer[$review->dsn_nip] = ($review->batas_reviewer - $tempDosen).' - '.$review->dsn_nama;
            }
        }else{
            return 'kosong';
        }
        return view('koordinator._sub-menu.reviewer.form', compact('model', 'mahasiswa', 'selectReviewer'));
    }

    public function putUbahReviewerKoor(Request $request, $tim, $id){
        $proyekAkhir = proyek_akhir::where('nama_tim', $tim)->where('judul_id', $id)->get();
        $reviewerLama = $proyekAkhir->first();
        
        notifikasi::create([  //untuk Reviewer LAMA
            'notifikasi_dari'          => "[Koordinator] ".Session::get('session_nama'), 
            'notifikasi_untuk'         => $reviewerLama->dsn_nip,
            'notifikasi_kategori'      => "Perubahan Dosen Reviewer ", 
            'notifikasi_deskripsi'     => "Reviewer untuk tim "
                                            .$reviewerLama->nama_tim." dengan judul ".$reviewerLama->tbl_judul->judul_nama.
                                            ", Kategori ".$reviewerLama->tbl_judul->tbl_kategori_judul->kategori_nama." Telah diganti dengan Dosen Lain",
            'notifikasi_tanggal'       => Carbon::now()
        ]);

        foreach($proyekAkhir as $proyek){
            proyek_akhir::where('proyek_akhir_id', $proyek->proyek_akhir_id)->update([
                'dsn_nip' => $request->form_pilihPembimbing
            ]);
            $reviewerBaru = proyek_akhir::where('dsn_nip', $request->form_pilihPembimbing)->first();
            notifikasi::create([  //untuk anggota TIM
                'notifikasi_dari'          => "[Koordinator] ".Session::get('session_nama'), 
                'notifikasi_untuk'         => $proyek->mhs_nim,
                'notifikasi_kategori'      => "Perubahan Dosen Reviewer ", 
                'notifikasi_deskripsi'     => "Reviewer Tim anda saat ini diubah menjadi ".$reviewerBaru->tbl_dosen->dsn_nama,
                'notifikasi_tanggal'       => Carbon::now()
            ]);
        }
        $reviewerPengganti = proyek_akhir::where('dsn_nip', $request->form_pilihPembimbing)->first();
        notifikasi::create([  //untuk Reviewer BARU
            'notifikasi_dari'          => "[Koordinator] ".Session::get('session_nama'), 
            'notifikasi_untuk'         => $reviewerPengganti->dsn_nip,
            'notifikasi_kategori'      => "Perubahan Dosen Reviewer ", 
            'notifikasi_deskripsi'     => "Anda telah dijadikan Reviewer Pengganti untuk Tim "
                                          .$reviewerPengganti->nama_tim." dengan judul ".$reviewerPengganti->tbl_judul->judul_nama.
                                          ", Kategori ".$reviewerPengganti->tbl_judul->tbl_kategori_judul->kategori_nama,
            'notifikasi_tanggal'       => Carbon::now()
        ]);
    }

    public function postHapusReviewerKoor($tim){
        proyek_akhir::where('nama_tim', $tim)->update(['dsn_nip' => null]);
    }
//END KOORDINATOR=============================================================================================

//DOSEN=================================================================================================
    //to do
//END DOSEN=============================================================================================

//MAHASISWA=================================================================================================
    //to do
//END MAHASISWA=============================================================================================
}


