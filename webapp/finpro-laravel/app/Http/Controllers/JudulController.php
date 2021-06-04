<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\notifikasi;
use App\Models\mahasiswa;
use App\Models\judul;
use App\Models\proyek_akhir;
use App\Models\dosen;
use App\Models\kategori_judul;
use App\Models\monev;
use App\Models\detail_monev;
use Carbon\Carbon;
use Illuminate\Support\Facades\Hash;
use Intervention\Image\ImageManagerStatic as Image;
use Session;
use Excel;
use File;
use DataTables;

class JudulController extends Controller
{

//KOORDINATOR===========================================================================================
    public function getPageJudulAktifKoor(){
        $dosenAll = dosen::All();
        $judul = judul::All();
        $tempDosen = Array();

        foreach($dosenAll as $dsn){
            $jmlJudul = '';
            if(empty($judul->where('dsn_nip', $dsn->dsn_nip)->first())){
                $jmlJudul = 0;
            }else{
                $jmlJudul = $dsn->batas_bimbingan - $judul->where('dsn_nip', $dsn->dsn_nip)->count();
            }
            if($jmlJudul == '0'){
                $tempDosen[] = $dsn->dsn_nip;
            }
        }
        $dosen = dosen::whereNotIn('dsn_nip', $tempDosen)->get();        
        $kategori_judul = kategori_judul::All();    
        return view('koordinator.judul-aktif', compact('dosen', 'kategori_judul', 'judul'));   
    }

    public function getPageJudulArsipKoor(){
        return view('koordinator.judul-arsip');   
    }


    public function postHapusJsonJudulArsipKoor($id){
        $model = judul::findOrFail($id);
        $model->delete();
    }

    public function postTambahJudulKoor(Request $request){
        $this->validate($request, [
            'form_pembimbingjudul' => 'required',
            'form_kategoriJudul' => 'required',
            'form_judulProyek' => 'required|string', 
            'form_deskripsiJudul'   => 'required|string',
        ]);

        $splitNip  = explode('_&_', $request->form_pembimbingjudul); //ambil karakter NIP dipisah dari _&_
        $splitKategori  = explode('_&_', $request->form_kategoriJudul); //ambil karakter Kategori dipisah dari _&_
        $getPembimbingNip = $splitNip[0];
        $getPembimbingNama = $splitNip[1];
        $getKategoriId = $splitKategori[0];
        $getKategoriNama = $splitKategori[1];

        judul::create([
            'judul_nama'        => $request->form_judulProyek,
            'judul_deskripsi'   => $request->form_deskripsiJudul,
            'judul_status'      => "tersedia",
            'judul_waktu'       => Carbon::now(),
            'dsn_nip'           => $getPembimbingNip,
            'kategori_id'       => $getKategoriId
        ]);

        $deskripsi = "Anda telah dijadikan Pembimbing judul proyek akhir baru dengan nama Judul ".$request->form_judulProyek.", Kategori ".$getKategoriNama;

        notifikasi::create([
            'notifikasi_dari'       => '[koordinator] '.Session::get('session_nama'),
            'notifikasi_untuk'      => $getPembimbingNip,
            'notifikasi_kategori'   => "Pemetaan Judul PA baru oleh Koordinator",
            'notifikasi_deskripsi'  => $deskripsi,
            'notifikasi_tanggal'    => Carbon::now()
        ]);
        
        $request->session()->flash('alert-success', "Judul baru dengan pembimbing ".$getPembimbingNama." berhasil dibuat!");
        return redirect()->back();
    }

    public function getPageDetailJudulKoor($id){
        $model = judul::findOrFail($id);
        $proyekAkhir = proyek_akhir::where('judul_id', $id)->first();
        return view('koordinator._sub-menu.judul.detail', compact('model', 'proyekAkhir'));
    }

    public function getPageUbahJudulKoor($id){
        $model = judul::findOrFail($id);
        $selectPembimbing = [];
        $kategoriJudul = kategori_judul::all();
        foreach($kategoriJudul as $kategori){
            $selectPembimbing[$kategori->kategori_id] = $kategori->kategori_nama;
        }
        return view('koordinator._sub-menu.judul.form-ubah', compact('model', 'selectPembimbing'));
    }

    public function putUbahJudulKoor(Request $request, $id){
        $this->validate($request, [
            'form_judulProyek' => 'required|string',
            'form_judulDeskripsi' => 'required|string',
        ]);
        $model = judul::findOrFail($id);
        $model->update([
            'judul_nama' => $request->form_judulProyek,
            'judul_deskripsi' => $request->form_judulDeskripsi,
            'kategori_id' => $request->form_judulKategori,
        ]);
    }

    public function postHapusJudulKoor($id){
        $model = judul::findOrFail($id);
        $model->delete();
    }

    public function getDetailJsonJudulArsip($id){
        $model = judul::findOrFail($id);
        return view('koordinator._sub-menu.judul.detail-arsip', compact('model'));
    }  

    public function getJsonJudulAktifKoor()
    {
        $model =judul::OrWhere('judul_status', 'pending')->Orwhere('judul_status', 'digunakan')->Orwhere('judul_status', 'tersedia')->get();
        return DataTables::of($model)
        ->addColumn('action', function ($model){

             $cekJudul = proyek_akhir::where('judul_id', $model->judul_id)->first();
            if(is_null($cekJudul) && $model->judul_status != 'pending'){
                return view('layout._action-rud',[
                    'model' => $model,
                    'url_show'      => route('koordinator.judul.detail', $model->judul_id),
                    'url_edit'      => route('koordinator.judul.tampil', $model->judul_id),
                    'url_delete'    => route('koordinator.judul.hapus', $model->judul_id),
                    'data_show'     => 'Detail '. $model->judul_nama,
                    'data_edit'     => 'Ubah '. $model->judul_nama,
                    'data_delete'   => $model->judul_nama
                ]);

            }else{
                return view('layout._action-ru',[
                    'model' => $model,
                    'url_show'      => route('koordinator.judul.detail', $model->judul_id),
                    'url_edit'      => route('koordinator.judul.tampil', $model->judul_id),
                    'data_show'     => 'Detail '. $model->judul_nama,
                    'data_edit'     => 'Ubah '. $model->judul_nama,
                ]);
            }
        })
        ->addColumn('mahasiswa', function ($model){
                $judul = judul::where('judul_id', $model->judul_id)->where('judul_status', 'tersedia')->first();
                if($judul){
                    return "-";
                }else{
                    $mhs = mahasiswa::where('judul_id', $model->judul_id)->get();
                    $temp=array();                      
                    foreach ($mhs as $index => $mahasiswa)
                    {                            
                        $temp[] = array(($index).". ".$mahasiswa->mhs_nama);
                    }                        
                    return $temp;
                }
        })
        ->addColumn('nama_tim', function ($model){
            $judul = judul::where('judul_id', $model->judul_id)->where('judul_status', 'tersedia')->OrWhere('judul_status', 'ditolak')->first();
            if($judul){
                return "-";
            }else{
                foreach($model->tbl_proyek_akhir as $proyek){
                    return $proyek->nama_tim;
                }
                
            }
        })
        ->addColumn('pembimbing', function ($model){
                return $model->tbl_dosen->dsn_kode;                
        })
        ->addColumn('reviewer', function ($model){
            foreach($model->tbl_proyek_akhir as $proyek)
            {
                if(!is_null($proyek->dsn_nip)){
                    return $proyek->tbl_dosen->dsn_kode;
                }
            }
            return "-";
        })
        ->addColumn('kategori_judul', function ($model){
            return $model->tbl_kategori_judul->kategori_nama;
        })
        ->addColumn('status', function ($model){
            return $model->judul_status;
        })
        ->addIndexColumn()
        ->rawColumns(['action'])
        ->make(true);   
    }

//END KOORDINATOR=======================================================================================

//DOSEN=================================================================================================
    public function getPageJudulAktifDosen(Request $request){
        $getNipDosen =  Session::get('session_nip');
        $kategori_judul = kategori_judul::all();
        $judulDosenAcc = proyek_akhir::with('tbl_judul')->with('tbl_mahasiswa')->whereNotNull('judul_id')->whereNotNull('nama_tim')->whereHas('tbl_judul', function ($query) {
            $query->where('dsn_nip', Session::get('session_nip'))->where('judul_status', 'tersedia')->orwhere('judul_status', 'pending');
        })->get();
        $dosen = dosen::where('dsn_nip', $getNipDosen)->first();
        $judul = judul::where('dsn_nip', $getNipDosen)->get();
        $limitBimbingan = $dosen->batas_bimbingan - $judul->count();
        if($limitBimbingan == '0'){
            $request->session()->flash('alert-warning', "Maaf, Sudah Melebihi Batas Kuota Bimbingan anda yaitu sebanyak ".$dosen->batas_bimbingan.", Harap hubungi dosen Koordinator terkait");
        }
        return view('dosen.judul-aktif', compact('kategori_judul', 'judulDosenAcc', 'namaTim', 'limitBimbingan'));   
    }

    public function getPageJudulArsipDosen(){
        $getNipDosen =  Session::get('session_nip');
        $kategori_judul = kategori_judul::all();
        $judulDosenAcc = proyek_akhir::with('tbl_judul')->with('tbl_mahasiswa')->whereNotNull('judul_id')->whereNotNull('nama_tim')->whereHas('tbl_judul', function ($query) {
            $query->where('dsn_nip', Session::get('session_nip'))->where('judul_status', 'tersedia')->orwhere('judul_status', 'pending');
        })->get();
        return view('dosen.judul-arsip', compact('kategori_judul', 'judulDosenAcc', 'namaTim'));   
    }

    public function getJsonJudulArsipDosen(){
        $getNipDosen =  Session::get('session_nip');
        $model =judul::Where('judul_status', 'arsip')->where('dsn_nip', $getNipDosen)->get();
            return DataTables::of($model)
            ->addColumn('action', function ($model){
                return view('layout._action-r',[
                'model' => $model,
                'url_show'      => route('dosen.judul.arsip.detail', $model->judul_id),
                'data_show'     => 'Detail Arsip Judul '.$model->judul_nama,                
                ]);
            })
            ->addColumn('mahasiswa', function ($model){
                $judul = proyek_akhir::where('judul_id', $model->judul_id)->get();
                $temp=array();                      
                foreach ($judul as $index => $mahasiswa)
                {                            
                    $temp[] = array(($index).". ".$mahasiswa->mhs_nama);
                }
                return $temp;
            })
            ->addColumn('nama_tim', function ($model){
                $judul = judul::where('judul_id', $model->judul_id)->where('judul_status', 'tersedia')->OrWhere('judul_status', 'ditolak')->first();
                if($judul){
                    return "-";
                }else{
                    foreach($model->tbl_proyek_akhir as $proyek){
                        return $proyek->nama_tim;
                    }
                }
            })
            ->addColumn('pembimbing', function ($model){
                    return $model->tbl_dosen->dsn_nama;                
            })
            ->addColumn('reviewer', function ($model){
                foreach($model->tbl_proyek_akhir as $proyek){
                    if($proyek->nama_tim){
                        return $proyek->tbl_dosen->dsn_nama;
                    }else{
                        return "-";
                    }
                }
            })
            ->addColumn('tahun', function ($model){
                return substr($model->judul_waktu, 0, 4);
          
            })
            ->addColumn('kategori_judul', function ($model){
                return $model->tbl_kategori_judul->kategori_nama;
            })
            ->addIndexColumn()
            ->rawColumns(['action'])
            ->make(true);   
    }

    public function getDetailJsonJudulDosen($id){
        $model = judul::where('judul_id', $id)->first();
        return view('dosen._sub-menu.judul._modal-detail-arsip', compact('model'));
    }

    public function getJsonjudulPengajuanDosen(){
        $dsnNip = Session::get('session_nip');
        $model =proyek_akhir::whereHas('tbl_judul', function ($query) use ($dsnNip) {
            $query->where('dsn_nip', $dsnNip)->where('judul_status', 'tersedia')->orwhere('judul_status', 'pending');
        })->distinct()->get(['nama_tim', 'judul_id']);
            return DataTables::of($model)
            ->addColumn('action', function ($model){
                return view('layout._action-r',[
                'model'     => $model,
                'url_show'  => route('dosen.judul.detail', ['id' => $model->judul_id, 'tim' => $model->nama_tim]),
                'data_show' => "Detail Judul". $model->tbl_judul->judul_nama,
                ]);
            })
            ->addColumn('mahasiswa', function ($model){
                $mhs = proyek_akhir::where('nama_tim', $model->nama_tim)->where('judul_id',  $model->judul_id)->get();
                $temp=array();                      
                foreach ($mhs as $index => $mahasiswa)
                {                            
                    $temp[] = array(($index+1).". ".$mahasiswa->tbl_mahasiswa->mhs_nama);
                }                        
                return $temp;
            })
            ->addColumn('judul', function ($model){
                return $model->tbl_judul->judul_nama;
            })
            ->addColumn('kategori_judul', function ($model){
                return $model->tbl_judul->tbl_kategori_judul->kategori_nama;
            })
            ->addColumn('perihal', function ($model){
                if($model->tbl_judul->judul_status === "tersedia"){
                    return "Pengajuan Judul dari dosen ";
                }else if($model->tbl_judul->judul_status === "pending"){
                    return "Pengajuan Judul Mandiri";
                }
            })
            ->addColumn('jumlah_kelompok', function ($model){
                 $countTim= proyek_akhir::where('judul_id', $model->judul_id)->whereNotNull('nama_tim')
                ->distinct()->get(['nama_tim', 'judul_id'])->count();
                return ($countTim - 1)." Tim";
            })
            ->addIndexColumn()
            ->rawColumns(['action'])
            ->make(true);   
    }

    public function getJsonJudulBerjalanDosen()
    {
        $model =judul::Where('dsn_nip', Session::get('session_nip'))->where('judul_status','!=', 'arsip')->get();
            return DataTables::of($model)
            ->addColumn('action', function ($model){
                $proyek = proyek_akhir::where('judul_id', $model->judul_id)->first();
                if(!is_null($proyek) && $proyek->tbl_judul->judul_status != 'ditolak'){
                    return view('layout._action-u',[
                        'model' => $model,
                        'url_edit' => route('dosen.judul-berjalan.detail', $model->judul_id),
                        'data_edit' => "Ubah Judul ". $model->judul_nama,
                    ]);
                }else{ 
                    return view('layout._action-ud',[
                        'model' => $model,
                        'url_edit'      => route('dosen.judul-berjalan.detail', $model->judul_id),
                        'data_edit'     => "Ubah Judul ". $model->judul_nama,
                        'url_delete'    => route('dosen.judul-berjalan.hapus', $model->judul_id),
                        'data_delete'   => "Hapus Judul ". $model->judul_nama,
                    ]);
                }
            })
            ->addColumn('mahasiswa', function ($model){
                    $judul = judul::where('dsn_nip', Session::get('session_nip'))->where('judul_id', $model->judul_id)->where('judul_status', 'tersedia')->first();
                    if($judul){
                        return "-";
                    }else{
                        $mhs = mahasiswa::where('judul_id', $model->judul_id)->whereHas('tbl_judul', function ($query) {
                            $query->where('dsn_nip', Session::get('session_nip'));
                        })->get();
                        $temp=array();                      
                        foreach ($mhs as $index => $mahasiswa)
                        {                            
                            $temp[] = array(($index+1).". ".$mahasiswa->mhs_nama);
                        }                        
                        return $temp;
                    }
            })
            ->addColumn('nama_tim', function ($model){
                $judul = judul::where('dsn_nip', Session::get('session_nip'))->where('judul_id', $model->judul_id)->where('judul_status', 'tersedia')->OrWhere('judul_status', 'ditolak')->first();
                if($judul){
                    return "-";
                }else{
                    foreach($model->tbl_proyek_akhir as $proyek){
                        return $proyek->nama_tim;
                    }
                    
                }
            })
            ->addColumn('pembimbing', function ($model){
                    return $model->tbl_dosen->dsn_nama;                
            })
            ->addColumn('reviewer', function ($model){
                foreach($model->tbl_proyek_akhir as $proyek)
                {
                    if(!is_null($proyek->dsn_nip)){
                        return $proyek->tbl_dosen->dsn_nama;
                    }
                }
                return "-";
            })
            ->addColumn('kategori_judul', function ($model){
                return $model->tbl_kategori_judul->kategori_nama;
            })
            ->addColumn('status', function ($model){
                return $model->judul_status;
            })
            ->addIndexColumn()
            ->rawColumns(['action'])
            ->make(true);   
    }

    public function getModalDetailJudulBerjalanDosen($id){
        $model = judul::where('judul_id', $id)->first();
        $kategori_judul = kategori_judul::All();
        $selectKategoriJudul = [];
        foreach($kategori_judul as $kategori){
            $selectKategoriJudul[$kategori->kategori_id] = $kategori->kategori_nama;
        }
        return view('dosen._sub-menu.judul._modal-ubah', compact('model', 'selectKategoriJudul'));
    }

    public function putUbahJudulBerjalanDosen(Request $request, $id){
          
        $this->validate($request, [
            'form_judulProyek'      => 'required',
            'form_judulKategori'    => 'required',
            'form_judulDeskripsi'   => 'required|string',
        ]);
        judul::where('judul_id', $id)->update([
            'judul_nama'            => $request->form_judulProyek,
            'judul_deskripsi'       => $request->form_judulDeskripsi,
            'kategori_id'           => $request->form_judulKategori,
        ]);
    }

    public function postHapusJudulBerjalanDosen($id){
        $model = judul::findOrFail($id);
        $model->delete();
    }

    public function postJudulTambahDosen(Request $request)  {
        $this->validate($request, [
            'form_judulProyek'      => 'required|string',
            'form_deskripsiJudul'   => 'required|string',
            'form_kategoriJudul'    => 'required', 
        ]);
        judul::create([
            'judul_nama'        => $request->form_judulProyek,
            'judul_deskripsi'   => $request->form_deskripsiJudul,
            'kategori_id'       => $request->form_kategoriJudul,
            'dsn_nip'           => Session::get('session_nip'),
            'judul_waktu'       => Carbon::now(),
            'judul_status'      => "tersedia",
        ]);
        $request->session()->flash('alert-success', 'Judul baru berhasil ditambahkan!');
        return redirect()->back();
    }

    public function getModalDetailPengajuanJudulDosen($id, $tim)
    {
        $model= proyek_akhir::where('judul_id', $id)->where('nama_tim', $tim)->get();
        $modelDistinct = proyek_akhir::where('judul_id', $id)->where('nama_tim', $tim)->distinct(['judul_id', 'nama_tim'])->get();
        $judul = judul::where('judul_id', $id)->get();
        return view('dosen._sub-menu.judul._modal-acc-detail', compact('model', 'modelDistinct','judul'));
    }

    public function putModalDetailSetujuJudulDosen(Request $request, $id, $tim){
        $dataProyekAkhir = proyek_akhir::where('nama_tim', $tim)->where('judul_id', $id)->get();

        $proyek = $dataProyekAkhir->first();
        $namaTim = $proyek->nama_tim; 
        $judul = $proyek->tbl_judul;

        $dataMahasiswa = array();
        foreach($dataProyekAkhir as $proyek){
            $dataMahasiswa[] = $proyek->mhs_nim;
        }


        if($judul->judul_status === "pending"){
            //lakukan ACC TERIMA judul mandiri
            judul::where('judul_id', $id)->update(['judul_status'=> "digunakan"]);
            for($i = 0; $i < count($dataMahasiswa); $i++){
                notifikasi::create([
                    'notifikasi_dari'          => Session::get('session_nama'), 
                    'notifikasi_untuk'         => $dataMahasiswa[$i], 
                    'notifikasi_kategori'      => "Permohonan JUDUL MANDIRI Anda Diterima", 
                    'notifikasi_deskripsi'     => 'Judul yang Tim kalian ajukan telah disetujui. Sekarang dosen '.Session::get('session_nama').' menjadi Dosbing kalian',
                    'notifikasi_tanggal'        => Carbon::now()
                ]);
            }
            $request->session()->flash('alert-success', 'Judul Mandiri Mahasiswa Berhasil disetujui!');
            return redirect()->back();

        }else if($judul->judul_status === "tersedia"){
            //lakukan ACC TERIMA judul mandiri

            proyek_akhir::where('judul_id', $id)->delete();
            mahasiswa::where('judul_id', $id)->update(['judul_id' => null]);
            judul::where('judul_id', $id)->update(['judul_status'=> "digunakan"]);

            for($i = 0; $i < count($dataMahasiswa); $i++){
                mahasiswa::where('mhs_nim', $dataMahasiswa[$i])->update(['judul_id' => $id]);
                proyek_akhir::create([
                    'judul_id' =>$id,
                    'nama_tim' =>$tim,
                    'mhs_nim'  =>$dataMahasiswa[$i],
                ]);
                notifikasi::create([
                    'notifikasi_dari'          => Session::get('session_nama'), 
                    'notifikasi_untuk'         => $dataMahasiswa[$i], 
                    'notifikasi_kategori'      => "Permohonan JUDUL DARI DOSEN Anda Diterima", 
                    'notifikasi_deskripsi'     => 'Judul yang Tim kalian ajukan telah disetujui. Sekarang dosen '.Session::get('session_nama').' menjadi Dosbing kalian',
                    'notifikasi_tanggal'        => Carbon::now()
                ]);
            }
            $request->session()->flash('alert-success', 'Judul Mahasiswa dari Dosen Berhasil disetujui!');
            return redirect()->back();
        }    
    }

    public function putTolakJudulDosen(Request $request, $id, $tim){
        
        $judul = judul::where('judul_id', $id)->whereHas('tbl_proyek_akhir', function ($query) use ($id, $tim) {
            $query->where('judul_id', $id)->where('nama_tim',  $tim);
        })->first();

        
        if($judul->judul_status === "pending"){
            //lakukan TOLAK judul mandiri
            $judul->update(['judul_status'=> "ditolak"]);
            $dataMahasiswa = mahasiswa::where('judul_id', $id)->whereHas('tbl_proyek_akhir', function ($query) use ($tim){
                    $query->where('nama_tim', $tim);
                })->get();

            foreach($dataMahasiswa as $index => $mahasiswa){
                mahasiswa::where('mhs_nim',  $mahasiswa->mhs_nim)->update([
                    'judul_id' => null,
                ]);
                notifikasi::create([
                    'notifikasi_dari'          => Session::get('session_nama'), 
                    'notifikasi_untuk'         => $mahasiswa->mhs_nim, 
                    'notifikasi_kategori'      => "Penolakan Permohonan Judul", 
                    'notifikasi_deskripsi'     => 'Judul '.$judul->judul_nama.' oleh Tim yang anda ajukan ditolak/diberikan ke Tim lain', 
                    'notifikasi_tanggal'        => Carbon::now()
                ]);
            }
        }else if($judul->judul_status === "tersedia"){
            //lakukan TOLAK judul dari dosen
            
            $dataMahasiswa = mahasiswa::where('judul_id', $id)->whereHas('tbl_proyek_akhir', function ($query) use ($tim) {
                            $query->where('nama_tim', $tim);
                            })->get();                        
            proyek_akhir::where('judul_id', $id)->where('nama_tim', $tim)->delete();
            foreach($dataMahasiswa as $index => $mahasiswa){
                mahasiswa::where('mhs_nim',  $mahasiswa->mhs_nim)->update([
                    'judul_id' => null,
                ]);
                notifikasi::create([
                    'notifikasi_dari'          => Session::get('session_nama'), 
                    'notifikasi_untuk'         => $mahasiswa->mhs_nim, 
                    'notifikasi_kategori'      => "Penolakan Permohonan Judul", 
                    'notifikasi_deskripsi'     => 'Judul '.$judul->judul_nama.' oleh Tim yang anda ajukan ditolak/diberikan ke Tim lain', 
                    'notifikasi_tanggal'        => Carbon::now()
                ]);
            }
        }   
        $request->session()->flash('alert-success', 'Judul Mahasiswa Berhasil Ditolak!');
        return redirect()->back();
    }

    public function getJsonJudulArsipKoor()
    {
        $model =judul::Where('judul_status', 'arsip')->get();
            return DataTables::of($model)
            ->addColumn('action', function ($model){
                return view('layout._action-rd',[
                'model' => $model,
                'url_show'      => route('koordinator.judul_arsip.json.detail', $model->judul_id),
                'url_delete'    => route('koordinator.judul_arsip.json.hapus', $model->judul_id),
                'data_show'     => 'Detail Arsip Judul '.$model->judul_nama,                
                'data_delete'   => 'Arsip Judul '.$model->judul_nama, 
                ]);
            })
            ->addColumn('mahasiswa', function ($model){
                $judul = proyek_akhir::where('judul_id', $model->judul_id)->get();
                $temp=array();                      
                foreach ($judul as $index => $mahasiswa)
                {                            
                    $temp[] = array(($index).". ".$mahasiswa->mhs_nama);
                }
                return $temp;
            })
            ->addColumn('nama_tim', function ($model){
                $judul = judul::where('judul_id', $model->judul_id)->where('judul_status', 'tersedia')->OrWhere('judul_status', 'ditolak')->first();
                if($judul){
                    return "-";
                }else{
                    foreach($model->tbl_proyek_akhir as $proyek){
                        return $proyek->nama_tim;
                    }
                }
            })
            ->addColumn('pembimbing', function ($model){
                    return $model->tbl_dosen->dsn_nama;                
            })
            ->addColumn('reviewer', function ($model){
                foreach($model->tbl_proyek_akhir as $proyek){
                    if($proyek->nama_tim){
                        return $proyek->tbl_dosen->dsn_nama;
                    }else{
                        return "-";
                    }
                }
            })
            ->addColumn('tahun', function ($model){
                return substr($model->judul_waktu, 0, 4);
          
            })
            ->addColumn('kategori_judul', function ($model){
                return $model->tbl_kategori_judul->kategori_nama;
            })
            ->addIndexColumn()
            ->rawColumns(['action'])
            ->make(true);   

    }

//END DOSEN=============================================================================================

//MAHASISWA=============================================================================================
    public function getPageJudulTimMhs(){
        $judulTim = proyek_akhir::where('mhs_nim', Session::get('session_nim'))->whereHas('tbl_judul', function($query){
            $query->where('judul_status', 'digunakan');
            })->distinct(['nama_tim']);
        $mhsBio= mahasiswa::where('mhs_nim', Session::get('session_nim'))->first();
        $namaTim="";
        foreach($mhsBio->tbl_proyek_akhir as $index => $proyek){
            $namaTim = $proyek->nama_tim;
        }
        $proyekAkhir = proyek_akhir::where('nama_tim', $namaTim)->whereHas('tbl_detail_monev', function($query){
            $query->whereNotNull('proyek_akhir_id')->whereHas('tbl_monev', function($monevStatus){
                $monevStatus->where('monev_status', 1);
            }); 
        })->get();        
        $proyekAkhirNilai = $proyekAkhir->first();
        $monev = monev::where('monev_status', 1)->get();
        $detailMonev = detail_monev::whereHas('tbl_monev', function($query){
            $query->where('monev_status', 1);
        })->get();
        return view('mahasiswa.judul-tim', compact('mhsBio', 'proyekAkhir', 'namaTim', 'proyekAkhirNilai', 'monev', 'detailMonev'));
    }

    public function postUbahDataJudulTimMhs(Request $request){
        $this->validate($request, [
            'fileDokumen' => 'file|mimes:pdf|max:20000',
            'fileAbstrak' => 'file|mimes:pdf|max:5000', 
        ]);
        $oldFiles = proyek_akhir::where('mhs_nim', Session::get('session_nim'))->first();

        if($request->hasFile('fileDokumen')) { //upload dokumen
        
            if(!empty($oldFiles->tbl_judul->judul_dokumen)){//cek dokumen
                File::delete(public_path('file/dokumen_pa/'.$oldFiles->tbl_judul->judul_dokumen));
            }    
            $uploadDokumen       = $request->file('fileDokumen');
            $fileNameDokumen    = 'Dokumen' . '_'.uniqid().'-'.$oldFiles->nama_tim.'.' . $uploadDokumen->getClientOriginalExtension();        
            $uploadDokumen->move('file/dokumen_pa/',$fileNameDokumen);
            judul::where('judul_id', $oldFiles->judul_id)->update([
                'judul_dokumen'     => $fileNameDokumen
            ]);
                  
        }
        if($request->hasFile('fileAbstrak')){  //upload abstrak

            if(!empty($oldFiles->tbl_judul->judul_abstrak)){//cek abstrak
                File::delete(public_path('file/dokumen_pa/'.$oldFiles->tbl_judul->judul_abstrak));
            }    
            $uploadAbstrak       = $request->file('fileAbstrak');
            $fileNameAbstrak    = 'Abstrak' . '_'.uniqid().'-'.$oldFiles->nama_tim.'.' . $uploadAbstrak->getClientOriginalExtension();                    
            $uploadAbstrak->move('file/dokumen_pa/',$fileNameAbstrak);
            judul::where('judul_id', $oldFiles->judul_id)->update([
                'judul_abstrak'     => $fileNameAbstrak
            ]);            
        }
        $request->session()->flash('alert-success', "Dokumen Berhasil Diperbaharui");
        return redirect()->back();
    }
    public function getPageLihatJudulMhs(){
        $belumPunyaJudul = mahasiswa::where('mhs_nim', Session::get('session_nim'))->where('judul_id', null)->first();
        $pembimbing = dosen::all();
        $kategori_judul = kategori_judul::All();
        $judul = judul::where('judul_status', 'tersedia')->Orwhere('judul_status', 'digunakan')->get();
        return View('mahasiswa.judul-daftar', compact('pembimbing', 'judul', 'kategori_judul', 'mahasiswa', 'belumPunyaJudul'));
    }

    public function getModalFormJudulMandiriMhs(Request $request){
        $cekPilihJudul = mahasiswa::where('mhs_nim', Session::get('session_nim'))->where('judul_id', null)->first();
        $model = new Judul();
        $judul = judul::All();
        $dosenAll = dosen::All();
        $tempDosen = Array();
        foreach($dosenAll as $dsn){
            $jmlJudul = '';
            if(empty($judul->where('dsn_nip', $dsn->dsn_nip)->first())){
                $jmlJudul = 0;
            }else{
                $jmlJudul = $dsn->batas_bimbingan - $judul->where('dsn_nip', $dsn->dsn_nip)->count();
            }
            if($jmlJudul == '0'){
                $tempDosen[] = $dsn->dsn_nip;
            }
        }
        $pembimbing = dosen::whereNotIn('dsn_nip', $tempDosen)->get();    
        $kategori_judul = kategori_judul::All();
        $selectPembimbing = [];
        foreach($pembimbing as $bimbing){
            $selectPembimbing[$bimbing->dsn_nip.'_&_'.$bimbing->dsn_nama] = $bimbing->dsn_nama.' ('.$bimbing->dsn_kode.')';
        }
        $selectKategoriJudul = [];
        foreach($kategori_judul as $kategori){
            $selectKategoriJudul[$kategori->kategori_id.'_&_'.$kategori->kategori_nama] = $kategori->kategori_nama;
        }
        $cekJmlMahasiswa = mahasiswa::whereNull('judul_id')->count();
        return view('mahasiswa._sub-menu.judul.form_mandiri', compact('model', 'selectPembimbing', 'selectKategoriJudul', 'cekPilihJudul', 'cekJmlMahasiswa'));        
    }

    public function getJsonJudulAktifMhs(){
        $model =judul::where('judul_status', 'tersedia')->get();
            return DataTables::of($model)
            ->addColumn('action', function ($model){
                $judulNama = $model->judul_nama;
                if($model->judul_status === 'digunakan'){
                    return '<a  class="btn btn-default" title="Judul "'.$judulNama.'" sudah digunakan">kosong</a>';    
                }else{
                    $url = route('mahasiswa.judul.tersedia.form', $model->judul_id);
                    $hasJudul = mahasiswa::where('mhs_nama', Session::get('session_nama'))->whereNotNull('judul_id')->first();
                    if(empty($hasJudul)){
                        return "<a href=\"$url\" class=\"btn btn-action modal-show btn-info\" title=\"Ajukan Judul dari Dosen \">Ajukan</a>";                                                                                    
                    }else{
                        return "<a href=\"$url\" class=\"btn btn-action btn-show btn-info\" title=\"Detail Judul ".$judulNama." asd\">Detail</a>";
                    }
                }
            })
            ->addColumn('pembimbing', function ($model){
                    return $model->tbl_dosen->dsn_kode;                
            })
            ->addColumn('kategori_judul', function ($model){
                return $model->tbl_kategori_judul->kategori_nama;
            })
            ->addColumn('jumlah_kelompok', function ($model){
                $countTim= proyek_akhir::where('judul_id', $model->judul_id)->whereNotNull('nama_tim')
                ->distinct()->get(['nama_tim', 'judul_id'])->count();
                if($model->judul_status === "digunakan"){
                    return "-";
                }else {
                    return ($countTim)." Tim"; 
                }
            })
            ->addIndexColumn()
            ->rawColumns(['action'])
            ->make(true);   
    }

    public function postAjukanJudulMandiriMhs(Request $request)
    {                       
       
        $this->validate($request, [
            'form_judulProyek'      => 'required|string',
            'form_kategoriJudul'    => 'required|string',
            'form_deskripsiJudul'   => 'required|min:10|max:200',
            'form_pilihPembimbing'  => 'required',
            'form_namaTim'          => 'required|min:4|max:15|unique:proyek_akhir,nama_tim',             
        ]);

        // mahasiswa 1
        $mhs1 = Session::get('session_nim').'_&_('.Session::get('session_nama').')';  
        $splitNim1 = explode('_&_', $mhs1); //ambil karakter NIM dipisah dari _&_
        $getMhs1Nim = $splitNim1[0];
        $getMhs1Nama = Session::get('session_nama');
        
        // pembimbing
        $splitNip  = explode('_&_', $request->form_pilihPembimbing); //ambil karakter NIM dipisah dari spasi
        $pembimbingNip = $splitNip[0];
        $pembimbingNama = $splitNip[1];

        //kategori
        $splitKategori = explode('_&_', $request->form_kategoriJudul); 
        $getKategoriId = $splitKategori[0];
        $getKategorinama = $splitKategori[1];
        
        //Detail Notifikasi
        $deskripsiUntukDosen = "Pengajuan Judul MANDIRI oleh ".$getMhs1Nama." dengan NAMA TIM ".$request->form_namaTim.", dan NAMA JUDUL ".$request->form_judulProyek." KATEGORI ". $getKategorinama;
        $deskripsiUntukMahasiswaPengirim = "judul MANDIRI sedang diajukan, harap tunggu Acc dari dosen terkait";
        $deskripsiUntukMahasiswa2 = $getMhs1Nama." Mengundang anda untuk 1 TIM JUDUL MANDIRI dengan NAMA JUDUL ".$request->form_judulProyek." KATEGORI ". $getKategorinama;

        //cek jumlah mahasiswa
        if(is_null($request->form_namaAnggota2)){
            judul::create([
                'judul_nama'        => $request->form_judulProyek,
                'judul_deskripsi'   => $request->form_deskripsiJudul,
                'judul_status'      => "pending",
                'judul_waktu'       => Carbon::now(),
                'dsn_nip'           => $pembimbingNip,
                'kategori_id'       => $getKategoriId,
            ]);
            $lastUser = judul::orderBy('judul_id', 'desc')->first();    
            $judul_id = $lastUser->judul_id;   

            $dataProyekAkhir = array(
                array(
                    'judul_id'  => $judul_id, 
                    'mhs_nim'   => $getMhs1Nim,
                    'nama_tim'  => $request->form_namaTim
                ),
            );
            proyek_akhir::insert($dataProyekAkhir); 

            if($judul_id!=null){
                mahasiswa::where('mhs_nim', $getMhs1Nim)->update(['judul_id' => $judul_id]);
            }
            
            $dataPemberitahuan = array(
                array( //calon pembimbing
                    'notifikasi_dari'          => $getMhs1Nama, 
                    'notifikasi_untuk'         => $pembimbingNip, 
                    'notifikasi_kategori'      => "Pengajuan Judul Mandiri", 
                    'notifikasi_deskripsi'     => $deskripsiUntukDosen, 
                    'notifikasi_tanggal'        => Carbon::now()
                ),
                array( //diri sendiri
                    'notifikasi_dari'          => $getMhs1Nama,
                    'notifikasi_untuk'         => $getMhs1Nim,
                    'notifikasi_kategori'      => "Pengajuan Judul Mandiri",
                    'notifikasi_deskripsi'     => $deskripsiUntukMahasiswaPengirim,
                    'notifikasi_tanggal'       => Carbon::now()
                ),
            );
            notifikasi::insert($dataPemberitahuan); 

        }else{
            $this->validate($request, [           
                'form_namaAnggota2'     => 'min:10|max:63',  
            ]);


            // mahasiswa 2
            $splitNim2 = explode(' ', $request->form_namaAnggota2); //ambil karakter NIM dipisah dari spasi
            $getMhs2Nim = $splitNim2[0];

            $cekInputAnggota2 = mahasiswa::where('mhs_nim', $getMhs2Nim)->first();
            if(is_null($cekInputAnggota2) || empty($cekInputAnggota2)){
                $request->session()->flash('alert-danger', 'Nim Anggota 2 tidak Valid!');
                return redirect()->back();
            }else{
                judul::create([
                    'judul_nama'        => $request->form_judulProyek,
                    'judul_deskripsi'   => $request->form_deskripsiJudul,
                    'judul_status'      => "pending",
                    'judul_waktu'       => Carbon::now(),
                    'dsn_nip'           => $pembimbingNip,
                    'kategori_id'       => $getKategoriId,
                ]);
                $lastUser = judul::orderBy('judul_id', 'desc')->first();    
                $judul_id = $lastUser->judul_id;   
        
                $dataProyekAkhir = array(
                    array(
                        'judul_id'  => $judul_id, 
                        'mhs_nim'   => $getMhs1Nim,
                        'nama_tim'  => $request->form_namaTim
                    ),
                array(
                        'judul_id'  => $judul_id, 
                        'mhs_nim'   => $getMhs2Nim,                     
                        'nama_tim'  => $request->form_namaTim
                    ),
                );
                proyek_akhir::insert($dataProyekAkhir); 
        
                if($judul_id!=null){
                    mahasiswa::where('mhs_nim', $getMhs1Nim)->update(['judul_id' => $judul_id]);
                    mahasiswa::where('mhs_nim', $getMhs2Nim)->update(['judul_id' => $judul_id]);
                }
        
                $dataPemberitahuan = array(
                    array( //calon pembimbing
                        'notifikasi_dari'       => $getMhs1Nama, 
                        'notifikasi_untuk'      => $pembimbingNip, 
                        'notifikasi_kategori'   => "Pengajuan Judul Mandiri", 
                        'notifikasi_deskripsi'  => $deskripsiUntukDosen, 
                        'notifikasi_tanggal'    => Carbon::now()
                    ),
                    array( //diri sendiri
                        'notifikasi_dari'       => $getMhs1Nama,
                        'notifikasi_untuk'      => $getMhs1Nim,
                        'notifikasi_kategori'   => "Pengajuan Judul Mandiri",
                        'notifikasi_deskripsi'  => $deskripsiUntukMahasiswaPengirim,
                        'notifikasi_tanggal'    => Carbon::now()
                    ),
                    array(//teman 1 tim
                        'notifikasi_dari'       => $getMhs1Nama,
                        'notifikasi_untuk'      => $getMhs2Nim,
                        'notifikasi_kategori'   => "Gabung TIM judul MANDIRI",
                        'notifikasi_deskripsi'  => $deskripsiUntukMahasiswa2,
                        'notifikasi_tanggal'    => Carbon::now()
                    ),
                );
                notifikasi::insert($dataPemberitahuan); 
            }

        }
    }

    public function getModalFormJudulTersediaMhs(Request $request, $id)
    {
        $model = judul::findOrFail($id);
        $kategori_judul = kategori_judul::All();
        $cekPilihJudul = mahasiswa::where('mhs_nim', Session::get('session_nim'))->where('judul_id', null)->first();
        $bimbing = dosen::where('dsn_nip', $model->dsn_nip)->first();
        $selectPembimbing = [];
        $selectPembimbing[$bimbing->dsn_nip.'_&_'.$bimbing->dsn_nama] = $bimbing->dsn_nama.' ('.$bimbing->dsn_kode.')';
        $selectKategoriJudul = [];
        foreach($kategori_judul as $kategori){
            $selectKategoriJudul[$kategori->kategori_id.'_&_'.$kategori->kategori_nama] = $kategori->kategori_nama;
        }


        $cekJmlMahasiswa = mahasiswa::whereNull('judul_id')->count();
        return view('mahasiswa._sub-menu.judul.form_tersedia', compact('model', 'selectPembimbing', 'cekPilihJudul', 'selectKategoriJudul', 'cekJmlMahasiswa'));        
    } 
    
     public function postAjukanJudulTersediaMhs(Request $request, $id)
    {
        $this->validate($request, [
            'form_judulProyek'          => 'required',
            'form_kategoriJudul'        => 'required',
            'form_deskripsiJudul'       => 'required',
            'form_pilihPembimbing'      => 'required',
            'form_namaAnggota1'         => 'required',
            //'form_namaAnggota2'         => 'required|max:64',
            'form_namaTim'              => 'required|min:4|max:15|unique:proyek_akhir,nama_tim',
        ]);

        //kategori  
        $splitKategori = explode('_&_', $request->form_kategoriJudul); 
        $getKategoriId = $splitKategori[0];
        $getKategorinama = $splitKategori[1];

        // pembimbing
        $splitNip  = explode('_&_', $request->form_pilihPembimbing); //ambil karakter NIM dipisah dari spasi
        $pembimbingNip = $splitNip[0];
        $pembimbingNama = $splitNip[1];

        //Mahasiswa
        $getMahasiswa1Nama = Session::get('session_nama');        

        $deskripsiNotifikasiPengirim = "judul sedang diajukan, harap tunggu Acc dari dosen terkait";
        $deskripsiNotifikasiMahasiswa2 =  Session::get('session_nama')." Mengundang anda untuk 1 tim dengan JUDUL dari DOSEN yaitu dengan nama ".$request->form_judulProyek." KATEGORI ".$request->form_kategoriJudul;

        if(is_null($request->form_namaAnggota2)){ //1 orang/tim
            proyek_akhir::insert([
                'judul_id'  => $id, 
                'mhs_nim'   => Session::get('session_nim'),
                'nama_tim'  => $request->form_namaTim
            ]);
            mahasiswa::where('mhs_nim', Session::get('session_nim'))->update(['judul_id' => $id]);

            $deskripsiNotifikasiPembimbing = "Pengajuan JUDUL dari DOSEN oleh ".$getMahasiswa1Nama." dengan nama TIM ". $request->form_namaTim." dan NAMA JUDUL ".$request->form_judulProyek." KATEGORI ".$getKategorinama;

            $dataPemberitahuan = array(
                array(//kirim ke calon pembimbing
                    'notifikasi_dari'       => Session::get('session_nama'),
                    'notifikasi_untuk'      => $pembimbingNip,
                    'notifikasi_kategori'   => "Pengajuan Judul Dari Dosen",
                    'notifikasi_deskripsi'  => $deskripsiNotifikasiPembimbing,
                    'notifikasi_tanggal'    => Carbon::now(),
                ),
                array(//kirim ke mahasiswa ke-1 (diri sendiri)
                    'notifikasi_dari'       => Session::get('session_nama'),
                    'notifikasi_untuk'      => Session::get('session_nim'),
                    'notifikasi_kategori'   => "Pengajuan Judul Dari Dosen",
                    'notifikasi_deskripsi'  => $deskripsiNotifikasiPengirim,
                    'notifikasi_tanggal'    => Carbon::now()
                ),
            );
            notifikasi::insert($dataPemberitahuan); 
        }else{

            $this->validate($request, [
                'form_namaAnggota2'         => 'min:10|max:64',
            ]);

            // mahasiswa 2
            $splitNim2 = explode(' ', $request->form_namaAnggota2); //ambil karakter NIM dipisah dari spasi
            $getMhs2Nim = $splitNim2[0];

            $mahasiswa2 = mahasiswa::where('mhs_nim', $getMhs2Nim)->first();
            $getMahasiswa2Nama = $mahasiswa2->mhs_nama;

            if(is_null($mahasiswa2) || empty($mahasiswa2)){
                $request->session()->flash('alert-danger', 'Nim Anggota 2 tidak Valid!');
                return redirect()->back();
            }else{
                proyek_akhir::insert(array(
                    array(
                        'judul_id'  => $id, 
                        'mhs_nim'   => Session::get('session_nim'),
                        'nama_tim'  => $request->form_namaTim
                    ),
                    array(
                        'judul_id'  => $id, 
                        'mhs_nim'   => $getMhs2Nim,                     
                        'nama_tim'  => $request->form_namaTim
                    )
                )); 
                mahasiswa::where('mhs_nim', Session::get('session_nim'))->update(['judul_id' => $id]);
                mahasiswa::where('mhs_nim', $getMhs2Nim)->update(['judul_id' => $id]);
    
                $deskripsiNotifikasiPembimbing = "Pengajuan JUDUL dari DOSEN oleh ".$getMahasiswa1Nama.", ".$getMahasiswa2Nama." dengan nama TIM ". $request->form_namaTim." dan NAMA JUDUL ".$request->form_judulProyek." KATEGORI ".$getKategorinama;
                $dataPemberitahuan = array(
                    array(//kirim ke calon pembimbing
                        'notifikasi_dari'          => Session::get('session_nama'),
                        'notifikasi_untuk'         => $pembimbingNip,
                        'notifikasi_kategori'      => "Pengajuan Judul Dari Dosen",
                        'notifikasi_deskripsi'     => $deskripsiNotifikasiPembimbing,
                        'notifikasi_tanggal'       => Carbon::now()
                    ),
                    array(//kirim ke mahasiswa ke-1 (diri sendiri)
                        'notifikasi_dari'              => Session::get('session_nama'),
                        'notifikasi_untuk'             => Session::get('session_nim'),
                        'notifikasi_kategori'          => "Pengajuan Judul Dari Dosen",
                        'notifikasi_deskripsi'         => $deskripsiNotifikasiPengirim,
                        'notifikasi_tanggal'           => Carbon::now()
                    ),
                    array(//kirim ke mahasiswa ke-2
                        'notifikasi_dari'              => Session::get('session_nama'),
                        'notifikasi_untuk'             => $getMhs2Nim,
                        'notifikasi_kategori'          => "Gabung TIM judul dari Dosen",
                        'notifikasi_deskripsi'         => $deskripsiNotifikasiMahasiswa2,
                        'notifikasi_tanggal'           => Carbon::now()
                    ),
                );
                notifikasi::insert($dataPemberitahuan); 

            }

            
        }   
    }

    public function getJsonJudulArsipMhs()
    {
        $model =judul::Where('judul_status', 'arsip')->get();
            return DataTables::of($model)
            ->addColumn('action', function ($model){
                return view('layout._action-r',[
                'model' => $model,
                'url_show' => route('mahasiswar.judul.arsip.tampil', $model->judul_id),
                'data_show'     => 'Detail '. $model->judul_nama,
                ]);
            })
            ->addColumn('mahasiswa', function ($model){
                $judul = proyek_akhir::where('judul_id', $model->judul_id)->get();
                $arrMhs=array();           

                foreach($judul as $index => $mahasiswa){                            
                    $arrMhs[] = array(($index+1).". ".$mahasiswa->tbl_mahasiswa->mhs_nama);
                }
                return $arrMhs;
            })
            ->addColumn('nama_tim', function ($model){
                foreach($model->tbl_proyek_akhir as $proyek){
                    return $proyek->nama_tim;
                }
            })
            ->addColumn('pembimbing', function ($model){
                    return $model->tbl_dosen->dsn_nama;                
            })
            ->addColumn('reviewer', function ($model){
                foreach($model->tbl_proyek_akhir as $proyek){
                    if($proyek->nama_tim){
                        return $proyek->tbl_dosen->dsn_nama;
                    }else{
                        return "-";
                    }
                }
            })
            ->addColumn('tahun', function ($model){
                return substr($model->judul_waktu, 0, 4);
          
            })
            ->addColumn('kategori_judul', function ($model){
                return $model->tbl_kategori_judul->kategori_nama;
            })
            ->addIndexColumn()
            ->rawColumns(['action'])
            ->make(true);   
    }
    
    public function getPageArsipJudulMhs()
    {
        return view('mahasiswa.judul-arsip');   
    }

    public function getModalDetailJudulArsip($id){
        $model = judul::findOrFail($id);
        return view('mahasiswa._sub-menu.judul.tampil_arsip', compact('model'));
    }
//END MAHASISWA=========================================================================================


//SEMUA=============================================================================================
    //to do
//END SEMUA=========================================================================================
}



