<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\notifikasi;
use App\Models\mahasiswa;
use App\Models\bimbingan;
use App\Models\judul;
use App\Models\monev;
use App\Models\detail_monev;
use App\Models\proyek_akhir;
use DataTables;
use Session;
use Carbon\Carbon;

class MonevController extends Controller
{

//KOORDINATOR=========================================================================================
    public function getPageMonevKoor(){
        $monevKategori = monev::orderBy('jumlah_bimbingan', 'asc')->get();
        $monevDetail = detail_monev::get();
        return view('koordinator.monev', compact('monevKategori', 'monevDetail'));
    }

    public function getJsonMonevBerandaKoor(){
        $model = proyek_akhir::whereNotNull('dsn_nip')->whereHas('tbl_judul', function($query){
            $query->where('judul_status', 'digunakan');
        })->distinct()->get(['nama_tim', 'dsn_nip']);
        return DataTables::of($model)
        ->addColumn('action', function ($model){
            return view('layout._action-r',[
            'model' => $model,
            'url_show' => route('koordinator.beranda.monev.detail', $model->nama_tim),
            'data_show' => "Detail nilai Monev ". $model->nama_tim,
            ]);
        })            
        ->addColumn('nama_mahasiswa', function ($model){
            $mahasiswa = proyek_akhir::where('nama_tim', $model->nama_tim)->get();
            $arrMhs = Array();

            foreach($mahasiswa as $index => $mhs){
                $arrMhs[] = ($index+1).". ".$mhs->tbl_mahasiswa->mhs_nama.', ';
            }
           return $arrMhs;

        })
        ->addColumn('jumlah_monev', function ($model){
            $id = proyek_akhir::where('nama_tim', $model->nama_tim)->first();
            $jml = detail_monev::where('proyek_akhir_id', $id->proyek_akhir_id)->count();
            return $jml." Kali";
        })    
        ->addColumn('reviewer', function ($model){
            return $model->tbl_dosen->dsn_kode;
        })    
        ->addIndexColumn()
        ->rawColumns(['action'])
        ->make(true);   
    }

    public function getDetailMonevBerandaKoor($tim){
        $detailMonev = proyek_akhir::with('tbl_detail_monev')->where('nama_tim', $tim)->get();
        $detail = $detailMonev->first();
        return view('koordinator._sub-menu.beranda.show-modal-detail-monev', compact('detailMonev', 'detail'));
    
    }

    public function postTambahKategoriMonevKoor(Request $request){
        $this->validate($request, [
            'form_monevKategori' => 'required|min:2|max:32',
            'form_monevBimbingan' => 'required|max:2', 
        ]);

        monev::create([
            'monev_kategori'    => $request->form_monevKategori,
            'jumlah_bimbingan'  => $request->form_monevBimbingan
        ]);

        $request->session()->flash('alert-success', 'Kategori '.$request->form_monevKategori.' Berhasil Ditambah!');
        return redirect()->back();
    }

    public function getModalUbahKategoriMonevKoor($id){
        $model = monev::findOrFail($id);        
        return view('koordinator._sub-menu.monev.form-ubah', compact('model'));
    }

    public function putUbahKategoriMonevKoor(Request $request, $id){
        $this->validate($request, [
            'form_kategoriMonev' => 'required|min:4|max:32',
            'form_BimbinganMonev' => 'required|max:2', 
        ]);
        monev::where('monev_id', $id)->update([
            'monev_kategori' => $request->form_kategoriMonev,
            'jumlah_bimbingan' => $request->form_BimbinganMonev,
            'monev_status' => $request->statusMonev,
        ]);
    }

    public function hapusKategoriMonevKoor($id){
        $model= monev::findOrFail($id);
        $model->delete();
    }

    public function getJsonMonevKoor(){
        $model = proyek_akhir::whereNotNull('dsn_nip')->whereHas('tbl_judul', function($query){
            $query->where('judul_status', 'digunakan');
        })->distinct()->get(['nama_tim', 'dsn_nip']);
        return DataTables::of($model)
        ->addColumn('action', function ($model){
            return view('layout._action-r',[
            'model' => $model,
            'url_show' => route('koordinator.beranda.monev.detail', $model->nama_tim),
            'data_show' => "Detail nilai Monev ". $model->nama_tim,
            ]);
        })            
        ->addColumn('nama_mahasiswa', function ($model){
            $mahasiswa = proyek_akhir::where('nama_tim', $model->nama_tim)->Orderby('proyek_akhir_id', 'asc')->get();
            $arrMhs = Array();

            foreach($mahasiswa as $index => $mhs){
                $arrMhs[] = ($index+1).". ".$mhs->tbl_mahasiswa->mhs_nama.', ';
            }
           return $arrMhs;

        })
        ->addColumn('jumlah_monev', function ($model){
            $id = proyek_akhir::where('nama_tim', $model->nama_tim)->first();
            $jml = detail_monev::where('proyek_akhir_id', $id->proyek_akhir_id)->count();
            return $jml." Kali";
        })    
        ->addColumn('reviewer', function ($model){
            return $model->tbl_dosen->dsn_kode;
        })    
        ->addColumn('nilai', function ($model){
            $mahasiswa = proyek_akhir::where('nama_tim', $model->nama_tim)->Orderby('proyek_akhir_id', 'asc')->get();
            $arrNilai = Array();

            foreach($mahasiswa as $index => $mhs){
                if(is_null($mhs->nilai_total)){
                    $arrNilai[] = '0'.', ';    
                }else{
                    $arrNilai[] = ($index+1).". ".$mhs->nilai_total.', ';
                }
            }
           return $arrNilai;

        }) 
        ->addIndexColumn()
        ->rawColumns(['action'])
        ->make(true);   
    }

    public function getModalDetailNilaiMonevKoor($nim){
        $model = mahasiswa::findOrFail($nim);

        return view('koordinator._sub-menu.monev.show-detail-nilai', compact('model'));
    }
//END KOORDINATOR=========================================================================================

//DOSEN=========================================================================================
public function getJsonMonevBerandaDosen(){
    $model = proyek_akhir::whereNotNull('dsn_nip')->whereHas('tbl_judul', function($query){
        $query->where('judul_status', 'digunakan')->where('dsn_nip', Session::get('session_nip'));
    })->distinct()->get(['nama_tim', 'dsn_nip']);
    return DataTables::of($model)
    ->addColumn('action', function ($model){
        return view('layout._action-r',[
        'model' => $model,
        'url_show' => route('dosen.beranda.monev.detail', $model->nama_tim),
        'data_show' => "Detail nilai Monev ". $model->nama_tim,
        ]);
    })            
    ->addColumn('nama_mahasiswa', function ($model){
        $mahasiswa = proyek_akhir::where('nama_tim', $model->nama_tim)->get();
        $arrMhs = Array();

        foreach($mahasiswa as $index => $mhs){
            $arrMhs[] = ($index+1).". ".$mhs->tbl_mahasiswa->mhs_nama.', ';
        }
       return $arrMhs;

    })
    ->addColumn('jumlah_monev', function ($model){
        $id = proyek_akhir::where('nama_tim', $model->nama_tim)->first();
        $jml = detail_monev::where('proyek_akhir_id', $id->proyek_akhir_id)->count();
        return $jml." Kali";
    })    
    ->addColumn('reviewer', function ($model){
        return $model->tbl_dosen->dsn_kode;
    })    
    ->addIndexColumn()
    ->rawColumns(['action'])
    ->make(true);   
}

public function getDetailMonevBerandaDosen($tim){
    $detailMonev = proyek_akhir::with('tbl_detail_monev')->where('nama_tim', $tim)->get();
    $detail = $detailMonev->first();
    return view('koordinator._sub-menu.beranda.show-modal-detail-monev', compact('detailMonev', 'detail'));

}
//END DOSEN=========================================================================================

//MAHASISWA=========================================================================================
    public function getPageMonevMhs(){
        $dataMonev = proyek_akhir::with('tbl_detail_monev')->where('mhs_nim', Session::get('session_nim'))->whereNotNull('dsn_nip')->whereHas('tbl_detail_monev', function($query){
            $query->whereNotNull('proyek_akhir_id');
        })->get();
        
        $mhsBio= mahasiswa::where('mhs_nim', Session::get('session_nim'))->first();
        $namaTim="";
        foreach($mhsBio->tbl_proyek_akhir as $index => $proyek){
            $namaTim = $proyek->nama_tim;
        }
        $proyekAkhir = proyek_akhir::where('nama_tim', $namaTim)->get();    
        $proyekAkhirNilai = $proyekAkhir->first();

        $monevTerlaksana = detail_monev::where('proyek_akhir_id', $proyekAkhirNilai->proyek_akhir_id)->get();
        $monevAll = monev::where('monev_status', 1)->orderBy('jumlah_bimbingan', 'asc')->get();        

        $rataMonev = $monevTerlaksana->sum('monev_nilai') / $monevAll->count();
        return view('mahasiswa.monev', compact('dataMonev', 'mhsBio', 'namaTim', 'proyekAkhir', 'rataMonev', 'monevTerlaksana', 'monevAll'));   
    }


//END MAHASISWA=========================================================================================
    
    public function getPageMonevDosen() {
        $monev = monev::orderBy('jumlah_bimbingan', 'asc')->get();
        $dsnNip = Session::get('session_nip');
        $judul = judul::where('judul_status', 'digunakan')->whereHas('tbl_proyek_akhir',
                    function($query) use($dsnNip){
                        $query->where('dsn_nip', $dsnNip);
                    })->get();
        return view('dosen.monev', compact('monev', 'judul'));
    }

    public function getNilaiMonevDosen(Request $request){
        $this->validate($request, [
            'selectMonev' => 'required',
            'selectJudul' => 'required', 
        ]);        
        $judulId = $request->selectJudul;
        $cekSyaratJml = monev::where('monev_id', $request->selectMonev)->first();
        $cekJumlahBimbingan = bimbingan::whereHas('tbl_proyek_akhir', function($query) use ($judulId){
            $query->where('judul_id', $judulId);
        })->count();
        $jmlAnggota = proyek_akhir::where('judul_id', $judulId)->count();
        if($jmlAnggota >= 2){
            $jmlBimbingan = $cekJumlahBimbingan/2;
            if( $jmlBimbingan >= $cekSyaratJml->jumlah_bimbingan ){
                $request->session()->flash('alert-success', 'Jumlah Bimbingan '.$jmlBimbingan.', memenuhi Syarat '.$cekSyaratJml->monev_kategori);
            }else{
                $request->session()->flash('alert-danger', 'Jumlah Bimbingan '.$jmlBimbingan.', TIDAK memenuhi Syarat '.$cekSyaratJml->monev_kategori);
            }
        }else{
            if($cekJumlahBimbingan >= $cekSyaratJml->jumlah_bimbingan ){
                $request->session()->flash('alert-success', 'Jumlah Bimbingan '.$cekJumlahBimbingan.', memenuhi Syarat '.$cekSyaratJml->monev_kategori);
            }else{
                $request->session()->flash('alert-danger', 'Jumlah Bimbingan '.$cekJumlahBimbingan.', TIDAK memenuhi Syarat '.$cekSyaratJml->monev_kategori);
            }
        }
        return redirect()->route('dosen.monev.nilai', ['monev' =>$request->selectMonev, 'judul'=>$request->selectJudul]);
        //ke method getPageNilaiMonevDosen()
    }

    public function getPageNilaiMonevDosen(Request $request, $monev, $judul){
        $dataMonev = detail_monev::where('monev_id', $monev)->whereHas('tbl_proyek_akhir', function($query) use ($judul){
            $query->where('judul_id', $judul);
        })->get();
        $countMonev = $dataMonev->count();
        $dataTim = proyek_akhir::where('judul_id', $judul)->get();
        $namaMonev = monev::where('monev_id', $monev)->first();
        $dataTable = detail_monev::whereHas('tbl_proyek_akhir', function($query) use($judul){
            $query->where('judul_id', $judul);
        })->orderBy('monev_tanggal', 'asc')->get();
    
        $idMonev = $monev;
        $namaTim = '';
        foreach($dataTim as $i){
            $namaTim = $i->nama_tim;
        }        
        $waktu_sekarang = Carbon::now()->formatLocalized('%A, %d %B %Y '); //kamis, 14 Maret 2019 12:00
        return view('dosen.monev-nilai', compact('waktu_sekarang', 'dataMonev', 'idMonev', 'namaTim', 'namaMonev', 'dataTim', 'countMonev', 'dataTable'));
    }

    public function postNilaiMonevDosen(Request $request, $monev, $tim){

        $arrCatatanMonev = request()->input('form_monevCatatan');    
        $arrNilaiMonev = request()->input('form_monevNilai');

        $cekNilaiMonev = detail_monev::where('monev_id', $monev)->whereHas('tbl_proyek_akhir', function ($query) use ($tim){
            $query->where('nama_tim', $tim);
        })->count();

        if($cekNilaiMonev === 0){
            foreach($arrCatatanMonev as $index => $catatan){
                detail_monev::create([
                    'monev_tanggal'     => Carbon::now(),
                    'monev_ulasan'      => $catatan,
                    'monev_id'          => $monev,
                    'proyek_akhir_id'   => $index
                ]);
            }

            foreach($arrNilaiMonev as $index => $nilai){
                detail_monev::where('proyek_akhir_id', $index)->where('monev_id', $monev)
                    ->Update([
                        'monev_nilai'       => $nilai,
                    ]);
                
                $notif = proyek_akhir::where('proyek_akhir_id', $index)->first();
                notifikasi::create([  //untuk anggota TIM
                    'notifikasi_dari'          => Session::get('session_nama'), 
                    'notifikasi_untuk'         => $notif->mhs_nim,
                    'notifikasi_kategori'      => "Nilai ".$monev." telah Ditambahkan", 
                    'notifikasi_deskripsi'     => "Nilai Monev-".$monev." telah ditambahkan oleh Reviewer",
                    'notifikasi_tanggal'       => Carbon::now()
                ]);     
            }
            $request->session()->flash('alert-success', 'Nilai Monev Berhasil ditambahkan');
            return redirect()->back();
        }else{
            foreach($arrCatatanMonev as $index => $catatan){
                detail_monev::where('proyek_akhir_id', $index)->where('monev_id', $monev)
                ->Update([
                    'monev_tanggal'     => Carbon::now(),
                    'monev_ulasan'      => $catatan,
                ]);
            }

            foreach($arrNilaiMonev as $index => $nilai){
                detail_monev::where('proyek_akhir_id', $index)->where('monev_id', $monev)
                    ->Update([
                        'monev_nilai'       => $nilai,
                    ]);
                
                $notif = proyek_akhir::where('proyek_akhir_id', $index)->first();
                notifikasi::create([  //untuk anggota TIM
                    'notifikasi_dari'          => Session::get('session_nama'), 
                    'notifikasi_untuk'         => $notif->mhs_nim,
                    'notifikasi_kategori'      => "Nilai ".$monev." telah Diubah", 
                    'notifikasi_deskripsi'     => "Nilai Monev-".$monev." telah diubah oleh Reviewer",
                    'notifikasi_tanggal'       => Carbon::now()
                ]);  
            }

            $request->session()->flash('alert-success', 'Nilai Monev Berhasil diubah');
            return redirect()->back();
        }
        
    }

    public function getModalDetailNilaiMonevDosen($id){
        $model = mahasiswa::findOrFail($id);
        return view('dosen._sub-menu.monev._modal-detail-nilai', $model);
    }

    public function getJsonNilaiMonevDosen(){
        $model = detail_monev::whereHas('tbl_proyek_akhir', function($query){
            $query->where('dsn_nip', Session::get('session_nip'));
        })->get();
        return DataTables::of($model)
        ->addColumn('action', function ($model){
            $url_edit = route('dosen.monev.nilai', ['monev' => $model->monev_id, 'judul' => $model->tbl_proyek_akhir->judul_id]);
            $kategoriMonev = "Sunting ".$model->tbl_monev->monev_kategori;
            return "<a href=\" $url_edit \" class=\"btn-action btn \" title=\" $kategoriMonev \"><i class=\"icon-pencil text-inverse\"></i></a>";
        })  
        ->addColumn('nilai', function ($model){
            $nilai =  $model->monev_nilai;
            if(is_null($nilai)){
                return "kosong";
            }else{
                return $model->monev_nilai;
            }
        })
        ->addColumn('nilai_rata', function ($model){
            $monev = monev::where('monev_status', 1)->get()->count();
            return round(detail_monev::where('proyek_akhir_id', $model->proyek_akhir_id)->sum('monev_nilai') / $monev, 1);
        })
        ->addColumn('kategori_monev', function ($model){
            return $model->tbl_monev->monev_kategori;
        })
        ->addColumn('nama_mhs', function ($model){
            return $model->tbl_proyek_akhir->tbl_mahasiswa->mhs_nama;
        })
      
        ->addColumn('tim', function ($model){
            return $model->tbl_proyek_akhir->nama_tim;
        })
        ->addColumn('pembimbing', function ($model){
            return $model->tbl_proyek_akhir->tbl_judul->tbl_dosen->dsn_kode;
        })
        ->addColumn('reviewer', function ($model){
            return $model->tbl_proyek_akhir->tbl_dosen->dsn_kode;
        })
        ->addIndexColumn()
        ->rawColumns(['action'])
        ->make(true);   
    }

    public function getModalNilaiMonevDosen($tim, $idMonev){
        return view('dosen.monev-nilai');
    }

    
}
