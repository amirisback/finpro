<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\user;
use App\Models\informasi;
use App\Models\mahasiswa;
use Illuminate\Support\Facades\Hash;
use Carbon\Carbon;
use Intervention\Image\ImageManagerStatic as Image;
use View;
use File;
use Session;
use DataTables;
use DB;
use Excel;

class MahasiswaController extends Controller
{
    
    public function getBeranda(){
        $informasi = informasi::orderBy('informasi_waktu', 'asc')->paginate(4);
        return view('mahasiswa.beranda', compact('informasi'));   
        
    }

//KOORDINATOR=========================================================================================
    public function getpageUserMahasiswaKoor(){
        $mahasiswa = mahasiswa::All();
        return view('koordinator.user_mahasiswa', compact('mahasiswa'));   
    }

    public function getModalImportDataMhsKoor(){
        return view('koordinator._sub-menu.mahasiswa._upload-excel');
    }

    public function postImportDataMhsKoor(Request $request)
    {
        $this->validate($request, [
            'file' => 'required|mimes:xls,xlsx'
        ]);
        
        if($request->hasFile('file')){
            $path  = $request->file('file')->getRealPath();
            $data = Excel::load($path, function($reader){})->get();
                //create or update
                if(!empty($data) && $data->count() ) {
                    foreach ($data as $key => $value) {
                    
                        $user = user::where('username', $value->nim)->first();
                        if($user){
                            continue;
                        }else{
                            $data=array(
                                "username"=>$value->nim,
                                "password"=> bcrypt($value->nim), 
                                "pengguna"=>"mahasiswa" 
                            );
                            user::updateorCreate($data);
                            $data2=array(
                                "mhs_nim"       => $value->nim,                            
                                "mhs_nama"      => $value->nama,
                                "angkatan"      => $value->angkatan,
                                "mhs_foto"      => "default-mahasiswa.jpg",
                                "status"        => "aktif",
                                "username"      => $value->nim
                            );
                            mahasiswa::updateorCreate($data2);
                        }
                    }
                }
        }else{
            $request->session()->flash('alert-danger', 'file belum di masukan!');
            return redirect()->back(); 
        }
        $request->session()->flash('alert-success', 'Data Mahasiswa berhasil ditambahkan!');
        return redirect()->back();        
    }   

    public function postTambahMahasiswaKoor(Request $request)
    {
        $this->validate($request, [
            'input_nama_mhs' => 'required|min:4|max:64',
            'input_nim' => 'required|min:5|max:12|unique:mahasiswa,mhs_nim', 
        ]);

        $angkatan = '20'. substr($request->input_nim, 4,2);

        $exist=user::find($request->input_nim);
        if($exist){
            $request->session()->flash('alert-danger', 'Mahasiswa dengan NIM '.$request->input_nim. ' sudah ada!');
            return redirect()->back();
        }else {
            user::create([
                'username' => $request->input_nim,
                'password' => bcrypt($request->input_nim),
                'pengguna' => "mahasiswa",
            ]);
            
            mahasiswa::create([
                'mhs_nim'       => $request->input_nim,
                'mhs_nama'      => $request->input_nama_mhs,
                'angkatan'      => $angkatan,
                'mhs_foto'      => "default-mahasiswa.jpg",
                'status'        => "aktif",
                'username'      => $request->input_nim,
            ]);
            $request->session()->flash('alert-success', 'Mahasiswa '.$request->input_nama_mhs. ' berhasil ditambahkan!');
            return redirect()->back();
        }
    }

    public function getJsonMahasiswaKoor(){
        $model = mahasiswa::All();
        return DataTables::of($model)
        ->addColumn('action', function ($model){
            $mhs = mahasiswa::where('mhs_nim', $model->mhs_nim)->whereNotNull('judul_id')->first();
            if($mhs){
                return view('layout._action-ru',[
                    'model' => $model,
                    'url_show' => route('koordinator.mahasiswa.tampil', $model->mhs_nim),
                    'data_show' => "Detail Mahasiswa ". $model->mhs_nama,
                    'url_edit' => route('koordinator.mahasiswa.edit', $model->mhs_nim),
                    'data_edit' => "Edit ".$model->mhs_nama,
                ]);
                
            }else{
                return view('layout._action-rud',[
                    'model' => $model,
                    'url_show' => route('koordinator.mahasiswa.tampil', $model->mhs_nim),
                    'data_show' => "Detail Mahasiswa ". $model->mhs_nama,
                    'url_edit' => route('koordinator.mahasiswa.edit', $model->mhs_nim),
                    'data_edit' => "Edit ".$model->mhs_nama,
                    'url_delete' => route('koordinator.mahasiswa.hapus', $model->mhs_nim),
                    'data_delete' => "Hapus Mahasiswa ".$model->mhs_nama
                ]);
            }
        })
        ->addColumn('judul', function ($model){
            if(!is_null($model->judul_id))
                return $model->tbl_judul->judul_nama;                
            else{
                return '-';
            }                
        })
        ->addColumn('pembimbing', function ($model){
            if(!is_null($model->juduk_id))
            {
                return $model->tbl_judul->nip_dosen;                
            }else{
                return '-';
            }                
        })
        ->addIndexColumn()
        ->rawColumns(['action'])
        ->make(true);   
    }

    public function getModalDetailMahasiswaKoor($nim){
        $model = mahasiswa::findOrFail($nim);
        return view('koordinator._sub-menu.mahasiswa.show', ['model'=>$model]);
    }

    public function getModalUbahMahasiswaKoor($nim){
        $model = mahasiswa::findOrFail($nim);
        return view('koordinator._sub-menu.mahasiswa.form-ubah', compact('model'));
    } 

    public function putUbahMahasiswaKoor(Request $request, $nim){
        $this->validate($request, [
            'mhs_nama' => 'required|string|min:5|max:255',
            'angkatan' => 'required|string|min:2|max:4',
        ]);
        $model = mahasiswa::findOrFail($nim);
        $model->update($request->all());
    } 

    public function postHapusMahasiswaKoor($nim){
            $model = mahasiswa::findOrFail($nim);
            $model->delete();
            $model2 = user::findOrFail($nim);
            $model2->delete();
    }

//END KOORDINATOR=========================================================================================


//DOSEN===================================================================================================
//END KOORDINATOR=========================================================================================
      
//MAHASISWA===============================================================================================
//END MAHASISWA===========================================================================================

    function PostAutoCompleteMahasiswa(Request $request)
    {
        if($request->get('query'))
        {
            $query = $request->get('query');
            $getNim = Session::get('session_nim');
            $data = mahasiswa::where('mhs_nama', 'LIKE',  "%{$query}%")->where('mhs_nim', '!=', $getNim)->where('judul_id', null)->get();
            $output2 = '<ul class="dropdown-menu" style="display:block; position:relative"; z-index:3;>';
            foreach($data as $row)
            {
                $output2 .= '
                <li><a href="#">'.$row->mhs_nim.' ('.$row->mhs_nama.')</a></li>
                ';
            }
            $output2 .= '</ul>';
            echo $output2;
        }
    }
    
}