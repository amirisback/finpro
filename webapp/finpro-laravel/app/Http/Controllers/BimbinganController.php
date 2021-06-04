<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\notifikasi;
use App\Models\mahasiswa;
use App\Models\proyek_akhir;
use App\Models\bimbingan;
use Carbon\Carbon;
use Illuminate\Support\Facades\Hash;
use Intervention\Image\ImageManagerStatic as Image;
use Session;
use Excel;
use File;
use DataTables;
class BimbinganController extends Controller
{
//KOORDINATOR=============================================================================================
    public function getJsonBimbinganBerandaKoor(){
        $model = proyek_akhir::whereNotNull('judul_id')->whereHas('tbl_judul', function ($query) {
            $query->where('judul_status', 'digunakan');
        })->distinct()->get(['nama_tim']);
       return DataTables::of($model)
       ->addColumn('action', function ($model){
           return view('layout._action-r',[
           'model' => $model,
           'url_show' => route('koordinator.beranda.bimbingan.detail', $model->nama_tim),
           'data_show' => "Detail Bimbingan ". $model->nama_tim,
           ]);
       })
       ->addColumn('mahasiswa', function ($model){
            $mahasiswa = proyek_akhir::where('nama_tim', $model->nama_tim)->get();
            $arrMhs = Array();

            foreach($mahasiswa as $index => $mhs){
                $arrMhs[] = ($index+1).". ".$mhs->tbl_mahasiswa->mhs_nama.', ';
            }
           return $arrMhs;
       })       
       ->addColumn('jumlah_bimbingan', function ($model){
            $jml_bimbingan = proyek_akhir::where('nama_tim', $model->nama_tim)->first();            
            return '<b>'.count($jml_bimbingan->tbl_bimbingan)." </b> Kali";
       })
       ->addColumn('pembimbing', function ($model){
            $pembimbing = proyek_akhir::where('nama_tim', $model->nama_tim)->first();
            return $pembimbing->tbl_judul->tbl_dosen->dsn_kode;
        })
       ->addIndexColumn()
       ->rawColumns(['action', 'pembimbing', 'jumlah_bimbingan'])
       ->make(true); 
    }

    public function getDetailBimbinganBerandaKoor($tim){
        $detailBimbingan = proyek_akhir::with('tbl_bimbingan')->where('nama_tim', $tim)->get();

        return view('koordinator._sub-menu.beranda.show-modal-detail-bimbingan', compact('detailBimbingan'));
    }
    
//END KOORDINATOR=========================================================================================

//DOSEN=============================================================================================

    public function getJsonBimbinganBerandaDosen(){
        $model = proyek_akhir::whereNotNull('judul_id')->whereHas('tbl_judul', function ($query) {
            $query->where('judul_status', 'digunakan')->where('dsn_nip', Session::get('session_nip'));
        })->distinct()->get(['nama_tim']);
    return DataTables::of($model)
    ->addColumn('action', function ($model){
        return view('layout._action-r',[
        'model' => $model,
        'url_show' => route('dosen.beranda.bimbingan.detail', $model->nama_tim),
        'data_show' => "Detail Bimbingan ". $model->nama_tim,
        ]);
    })
    ->addColumn('mahasiswa', function ($model){
            $mahasiswa = proyek_akhir::where('nama_tim', $model->nama_tim)->get();
            $arrMhs = Array();

            foreach($mahasiswa as $index => $mhs){
                $arrMhs[] = ($index+1).". ".$mhs->tbl_mahasiswa->mhs_nama.', ';
            }
        return $arrMhs;
    })       
    ->addColumn('jumlah_bimbingan', function ($model){
            $jml_bimbingan = proyek_akhir::where('nama_tim', $model->nama_tim)->first();            
            return '<b>'.count($jml_bimbingan->tbl_bimbingan)." </b> Kali";
    })
    ->addColumn('pembimbing', function ($model){
            $pembimbing = proyek_akhir::where('nama_tim', $model->nama_tim)->first();
            return $pembimbing->tbl_judul->tbl_dosen->dsn_kode;
        })
    ->addIndexColumn()
    ->rawColumns(['action', 'pembimbing', 'jumlah_bimbingan'])
    ->make(true); 
    }

    public function getDetailBimbinganBerandaDosen($tim){
        $detailBimbingan = proyek_akhir::with('tbl_bimbingan')->where('nama_tim', $tim)->get();

        return view('koordinator._sub-menu.beranda.show-modal-detail-bimbingan', compact('detailBimbingan'));
    }

    public function getJsonPengajuanBimbinganDosen(){
        $dsnNip = Session::get('session_nip');
        $model = bimbingan::where('bimbingan_status', 'pending')->whereHas('tbl_proyek_akhir', 
                    function ($queryProyekAkhir) use ($dsnNip)
                    {
                        $queryProyekAkhir->whereNotNull('judul_id')->whereHas('tbl_judul', 
                        function($query) use ($dsnNip){
                            $query->where('dsn_nip',  $dsnNip);
                        });
                    })
                ->distinct()->get(['bimbingan_review']);
  
       return DataTables::of($model) 
       ->addColumn('action', function ($model) {
        $dsnNip = Session::get('session_nip');
        $review = $model->bimbingan_review;
        $model =  proyek_akhir::whereNotNull('judul_id')->wherehas('tbl_bimbingan', function($queryBimbingan) use ($dsnNip, $review){
                    $queryBimbingan->where('bimbingan_status',  'pending')->where('bimbingan_review', $review);
                })->whereHas('tbl_judul', function ($queryJudul) use ($dsnNip){
                    $queryJudul->where('dsn_nip', $dsnNip);
                })->first();
        return view('layout._action-acc-bimbingan',[
        'model' => $model,
        'url_show' => route('dosen.bimbingan.pengajuan.detail', $model->proyek_akhir_id),
        'data_show' => "Detail Pengajuan",
        'url_update' => route('dosen.bimbingan.pengajuan.setujui-langsung', $model->nama_tim),
        'data_update' => "Setujui Bimbingan Tim ".$model->nama_tim, 
           ]);
       })
       ->addColumn('nama_tim', function ($model){
            $review = $model->bimbingan_review;
            $proyek = proyek_akhir::whereHas('tbl_bimbingan', function($query) use ($review){
                $query->where('bimbingan_review', $review);
            })->first();
            return $proyek->nama_tim;
        })
       ->addColumn('tgl_bimbingan', function ($model){
            $bimbingan = bimbingan::where('bimbingan_review', $model->bimbingan_review)->first();
            return $bimbingan->bimbingan_tanggal;
        })   
       ->addIndexColumn()
       ->rawColumns(['action'])
       ->make(true); 
    }

    public function getJsonBimbinganDosen(){        
        $model = proyek_akhir::whereNotNull('judul_id')->whereHas('tbl_judul', function ($query) {
                 $query->where('dsn_nip', Session::get('session_nip'))->where('judul_status', 'digunakan');})->get();
            return DataTables::of($model)
            ->addColumn('action', function ($model){
                return view('layout._action-r',[
                'model' => $model,
                'url_show' => route('dosen.bimbingan.detail', ['proyek' => $model->proyek_akhir_id, 'id' => $model->judul_id]),
                'data_show' => "Detail Bimbingan ". $model->tbl_mahasiswa->mhs_nama,
                ]);
            })
            ->addColumn('mahasiswa', function ($model){
                return $model->tbl_mahasiswa->mhs_nama;
            })
            ->addColumn('judul', function ($model){
                return $model->tbl_judul->judul_nama;
            })
            ->addColumn('kategori_judul', function ($model){
                return $model->tbl_judul->tbl_kategori_judul->kategori_nama;
            })
            ->addColumn('bimbingan_review', function ($model){
                foreach($model->tbl_bimbingan as $bimbingan){
                    return $bimbingan->bimbingan_review;
                }
            })
            ->addColumn('bimbingan_judul', function ($model){
                foreach($model->tbl_bimbingan as $bimbingan){
                    return $bimbingan->bimbingan_judul;
                }
            })
            ->addColumn('bimbingan_tgl', function ($model){
                foreach($model->tbl_bimbingan as $bimbingan){
                    return $bimbingan->bimbingan_tanggal;
                }
            })
            ->addColumn('jumlah_bimbingan', function ($model){
                $count=array();
                foreach($model->tbl_bimbingan as $bimbingan){
                    $count[] = $bimbingan->proyek_akhir_id;
                }
                return count($count)." Kali";
            })
            ->addIndexColumn()
            ->rawColumns(['action'])
            ->make(true); 
    }

    public function getModalDetailBimbinganDosen($proyek, $id){
        $model = proyek_akhir::with('tbl_bimbingan')->where('judul_id', $id)->where('proyek_akhir_id', $proyek)->first();
        return view('dosen._sub-menu.bimbingan._modal-detail', compact('model'));
    }

    public function getPageBimbinganDosen(){
        $cekPengajuan = bimbingan::where('bimbingan_status', "pending")->whereHas('tbl_proyek_akhir', function($query){
            $query->whereHas('tbl_judul', function($queryJudul){
                $queryJudul->where('dsn_nip', Session::get('session_nip'));
            });
        })->count();
        return view('dosen.bimbingan', compact('cekPengajuan'));   
    }

    public function getModalPengajuanBimbinganDosen($id){
        $temp = proyek_akhir::where('proyek_akhir_id', $id)->first();
        $tim = $temp->nama_tim;

        $model = proyek_akhir::where('nama_tim', $temp->nama_tim)->whereHas('tbl_bimbingan', function($query){
            $query->whereNotNull('proyek_akhir_id')->where('bimbingan_status', "pending");
        })->orderBy('mhs_nim', 'asc')->get();    
        return view('dosen._sub-menu.bimbingan._pengajuan-detail', compact('model', 'id', 'tim'));
    }

    public function getSetujuiPengajuanBimbinganDosen(Request $request, $tim){
        bimbingan::whereNotNull('proyek_akhir_id')->whereHas('tbl_proyek_akhir', function ($query) use ($tim){
                    $query->where('nama_tim', $tim);
                    })->where('bimbingan_status', 'pending')->update(['bimbingan_status' => 'disetujui']);

        $mahasiswa= proyek_akhir::where('nama_tim', $tim)->get();
        foreach($mahasiswa as $mhs){
            notifikasi::create([
                'notifikasi_dari'          => Session::get('session_nama'), 
                'notifikasi_untuk'         => $mhs->mhs_nim, 
                'notifikasi_kategori'      => "Bimbingan Online telah diACC", 
                'notifikasi_deskripsi'     => 'Bimbingan Online anda Telah di ACC oleh Dosbing',
                'notifikasi_tanggal'        => Carbon::now()
            ]);
        }
        $request->session()->flash('alert-success', 'Bimbingan Telah Di-Acc!');
        return redirect()->back();
    }

    public function getSetujuiPengajuanBimbinganSemuaDosen(){
        bimbingan::where('bimbingan_status', 'pending')->wherehas('tbl_proyek_akhir', function($query){
            $query->whereNotNull('proyek_akhir_id')->whereHas('tbl_judul', function($queryJudul){
                $queryJudul->where('dsn_nip', Session::get('session_nip'));
            });
        })->update(['bimbingan_status' => 'disetujui']);
    }

    public function getTolakPengajuanBimbinganDosen(Request $request, $tim){
        bimbingan::whereNotNull('proyek_akhir_id')->whereHas('tbl_proyek_akhir', function ($query) use ($tim){
                    $query->where('nama_tim', $tim);
                    })->where('bimbingan_status', 'pending')->delete();

        $mahasiswa= proyek_akhir::where('nama_tim', $tim)->get();
        foreach($mahasiswa as $mhs){
            notifikasi::create([
                'notifikasi_dari'          => Session::get('session_nama'), 
                'notifikasi_untuk'         => $mhs->mhs_nim, 
                'notifikasi_kategori'      => "Penolakan Bimbingan Online", 
                'notifikasi_deskripsi'     => "Bimbingan Online anda DITOLAK oleh Dosbing, Coba lagi nanti",
                'notifikasi_tanggal'        => Carbon::now()
            ]);
        }
        $request->session()->flash('alert-danger', 'Bimbingan Ditolak!');
        return redirect()->back();
    }

//END DOSEN=========================================================================================

//MAHASISWA=============================================================================================
    public function getPageBimbinganMhs(){ //error
        $bimbingan = proyek_akhir::where('mhs_nim', Session::get('session_nim'))->whereHas('tbl_judul', function($query){
            $query->where('judul_status', 'digunakan');
        })->first();

        if($bimbingan){
            $mahasiswa = proyek_akhir::where('nama_tim', $bimbingan->nama_tim)->get();
            $countMahasiswa = proyek_akhir::where('nama_tim', $bimbingan->nama_tim)->get()->count();
            $dataBimbingan = bimbingan::where('proyek_akhir_id', $bimbingan->proyek_akhir_id)->orderBy('bimbingan_tanggal', 'asc')->get();
            return view('mahasiswa.bimbingan', compact('mahasiswa', 'countMahasiswa', 'dataBimbingan'));   
        }else{
            //belum dapat judul
            return view('mahasiswa.bimbingan-kosong');   
        }
    }

    public function postTambahBimbinganMhs(Request $request){
        $this->validate($request, [
            'form_bimbinganTanggal'     => 'required',
            'form_bimbinganTopik'       => 'required',
        ]);

        $nimMhs = Session::get('session_nim');
        $bimbingan = bimbingan::where('bimbingan_status', 'pending')->whereHas('tbl_proyek_akhir', function($query) use ($nimMhs){
            $query->where('mhs_nim', $nimMhs);
        })->first();

        if($bimbingan){
            $request->session()->flash('alert-warning', 'tidak dapat menambahkan, harap tunggu bimbingan sebelumnya disetujui terlebih dahulu');
            return redirect()->back();
        }else{
            $arr = request()->input('formKehadiran');

            $proyekAkhir = "";
            foreach ($arr as $idProyek => $kehadiran) {            
                bimbingan::create([
                    'bimbingan_kehadiran'   => $kehadiran,
                    'bimbingan_status'      => "pending",
                    'bimbingan_review'      => $request->form_bimbinganTopik,
                    'bimbingan_tanggal'     => $request->form_bimbinganTanggal,
                    'proyek_akhir_id'       => $idProyek,
                ]);       
                $notifikasi = proyek_akhir::where('proyek_akhir_id', $idProyek)->first();
                notifikasi::create([ //notif untuk mhs yg membuat bimbingan
                    'notifikasi_dari'          => Session::get('session_nama'), 
                    'notifikasi_untuk'         => $notifikasi->mhs_nim, 
                    'notifikasi_kategori'      => "pengajuan Bimbingan Online", 
                    'notifikasi_deskripsi'     => "Bimbingan Online anda pada tanggal". $request->form_bimbinganTanggal ." yang berisikan topik ".$request->form_bimbinganTopik." sedang menunggu ACC dari Dosbing",
                    'notifikasi_tanggal'       => Carbon::now()
                ]); 
                $proyekAkhir = $idProyek;
            }
            $notifikasiUntuk = Proyek_akhir::where('proyek_akhir_id', $proyekAkhir)->first();
            notifikasi::create([ //notif untuk DOSBING
                'notifikasi_dari'          => Session::get('session_nama'), 
                'notifikasi_untuk'         => $notifikasiUntuk->tbl_judul->dsn_nip, 
                'notifikasi_kategori'      => "pengajuan Bimbingan Online", 
                'notifikasi_deskripsi'     => "Pengajuan bimbingan Online pada tanggal". $request->form_bimbinganTanggal ."yang berisikan topik ".$request->form_bimbinganTopik." menunggu ACC",
                'notifikasi_tanggal'       => Carbon::now()
            ]);         
         
            $request->session()->flash('alert-success', 'Bimbingan Baru Berhasil ditambahkan, harap tunggu ACC dari dosen pembimbing ');
            return redirect()->back();
        }
    }
//END MAHASISWA=========================================================================================
}

