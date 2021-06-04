<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\user;
use App\Models\mahasiswa;
use App\Models\proyek_akhir;
use App\Models\dosen;
use App\Models\koordinator_pa;
use Carbon\Carbon;
use Illuminate\Support\Facades\Hash;
use Intervention\Image\ImageManagerStatic as Image;
use Session;
use Excel;
use File;
use DataTables;
class UserController extends Controller
{
//KOORDINATOR=======================================================================================
    public function getPageBiodataKoor(){
        $koordinator = koordinator_pa::where('username', Session::get('session_username'))->first();
        return view('koordinator.profil-edit-bio', compact('koordinator'));   
    }

    public function putUbahBiodataKoor(Request $request, $nip){
        $this->validate($request, [
            'nama_koor' => 'required|min:4|max:64',
            'nip_koor' => 'required|min:5|max:12',  //unique:koordinator_pa,koor_nip
            'kode_koor' => 'required|min:2|max:5',
            'kontak_koor' => 'required|numeric|digits_between:10,14',
        ]);

        if( Session::get('session_username') != $request->username_koor){
            $this->validate($request, [
                'username_koor' => 'required|min:4|max:32|unique:user,username',
            ]);
            user::where('username', Session::get('session_username'))->update(['username' => $request->username_koor]);
            Session::put('session_username', $request->username_koor);
        }

        if(Session::get('session_email') != $request->email_koor){
            $this->validate($request, [
                'email_koor' => 'required|email|min:7|max:32|unique:koordinator_pa,koor_email',
            ]);
            koordinator_pa::where('koor_nip', Session::get('session_nip'))->update(['koor_email' => $request->email_koor]);
            Session::put('session_email', $request->email_koor); 
        }

        if(Session::get('session_nip') != $request->nip_koor){
            $this->validate($request, [
                'nip_koor' => 'required|min:5|max:15|unique:koordinator_pa,koor_nip',
            ]);
            koordinator_pa::where('koor_nip', Session::get('session_nip'))->update(['koor_nip' => $request->nip_koor]);
            Session::put('session_nip', $request->nip_koor);
        }                                     

        $koorBioUpdate = koordinator_pa::where('koor_nip', $request->nip_koor)->first();
        $koorBioUpdate->koor_nama = $request->nama_koor;
        $koorBioUpdate->koor_kode = $request->kode_koor;
        $koorBioUpdate->koor_kontak = $request->kontak_koor;
        $koorBioUpdate->save();
        Session::put('session_nama', $request->nama_koor);
        Session::put('session_kode', $request->kode_koor);  
        Session::put('session_kontak', $request->kontak_koor);                                  

        $request->session()->flash('alert-success', "Biodata Berhasil Diperbaharui!");
        return redirect()->back();
    }

    public function getPageFotoProfileKoor(){
        $koordinator = koordinator_pa::where('username', Session::get('session_username'))->first(); 
        return view('koordinator.profil-edit-foto', compact('koordinator'));   
    }

    public function putUbahFotoKoor(Request $request, $nip){
        $this->validate($request, [
			'upload_gambar' => 'required|file|image|mimes:jpeg,png,jpg|max:2048',
        ]);

        $gambar = koordinator_pa::where('koor_nip',$nip)->first();
        if($gambar->koor_foto != "default-koor.jpg"){
            File::delete(public_path('image/koor_img/'.$gambar->koor_foto));//hapus foto lama
        }  

        if($request->hasFile('upload_gambar')) {

            $image       = $request->file('upload_gambar');
            $filename    = 'img_koor' . '_' . uniqid() . '.' . $image->getClientOriginalExtension();        
            $image_resize = Image::make($image->getRealPath());              
            $image_resize->resize(400, 400);
            $image_resize->save(public_path('image/koor_img/' .$filename));
            koordinator_pa::where('username', Session::get('session_username'))->update(['koor_foto' => $filename]);
            Session::put('session_gambar', $filename);                              
        }
        return redirect()->back()->with(['success' => 'Gambar Telah Di-upload']);
    }
   
    public function getPageUbahPassword(){
        $koordinator = koordinator_pa::where('username', Session::get('session_username'))->first();
        return view('koordinator.profil-edit-pass', compact('koordinator'));   
    }

    public function putUbahPasswordKoor(Request $request, $username){
        $user = user::where('username', Session::get('session_username'))->first();
        if(!Hash::check($request->password_lama,  $user->password)){
            $request->session()->flash('alert-danger', "Input password lama tidak sama");
            return redirect()->back();
        }
        $this->validate($request, [
            'password_lama' => 'required|min:5|max:32',
            'password_baru' => 'required|different:password_lama|min:5|max:32',
            'password_ulangi' => 'required|same:password_baru', 
        ]);
        $user = user::where('username', Session::get('session_username'))->first();
        if(Hash::check($request->password_lama,  $user->password)){ //cek password lama true
            user::Where('username', Session::get('session_username'))->update(['password' => bcrypt($request->password_baru)]);
            $request->session()->flash('alert-success', "Password Berhasil Diubah!");
            return redirect()->back();
        }
    }

//END KOORDINATOR=======================================================================================

//DOSEN=======================================================================================
    public function getPageBiodataDosen(){
        $dosen = dosen::where('username', Session::get('session_username'))->first();
        return view('dosen.profil_edit-bio',compact('dosen'));   
    }
    public function putUbahBiodataDosen(Request $request,$nip){
        $this->validate($request, [
            'bio_dsnNama' => 'required|min:4|max:64',
        ]);

        if(Session::get('session_username') != $request->bio_username){
            $this->validate($request, [
                'bio_username' => 'required|min:4|max:32|unique:user,username',
            ]);
            user::where('username', Session::get('session_username'))->update(['username' => $request->bio_username]);
            Session::put('session_username', $request->bio_username);
        }

        if(Session::get('session_email') != $request->bio_dsnEmail){
            $this->validate($request, [
                'bio_dsnEmail' => 'required|email|min:7|max:32|unique:dosen,dsn_email',
            ]);
            dosen::where('dsn_nip', Session::get('session_nip'))->update(['dsn_email' => $request->bio_dsnEmail]);
            Session::put('session_email', $request->bio_dsnEmail); 
        }

        if(Session::get('session_kontak') != $request->bio_dsnkontak){
            $this->validate($request, [
                'bio_dsnkontak' => 'required|numeric|digits_between:10,14|unique:dosen,dsn_kontak',
            ]);
            dosen::where('dsn_nip', Session::get('session_nip'))->update(['dsn_kontak' => $request->bio_dsnkontak]);
            Session::put('session_kontak', $request->bio_dsnkontak); 
        }
        $dsnBioUpdate = dosen::where('dsn_nip', Session::get('session_nip'))->first();
        $dsnBioUpdate->dsn_nama = $request->bio_dsnNama;
        $dsnBioUpdate->save();
        Session::put('session_nama', $request->bio_dsnNama);

        $request->session()->flash('alert-success', "Biodata Berhasil Diperbaharui!");
        return redirect()->back();
    }

    public function getPageFotoProfileDosen(){
        $dosen = dosen::where('dsn_nip', Session::get('session_nip'))->first();
        return view('dosen.profil_edit-foto',compact('dosen'));   
    }

    public function putUbahFotoDosen(Request $request, $nip){        
        $this->validate($request, [
            'upload_gambar' => 'required|file|image|mimes:jpeg,png,jpg|max:2048',
        ]);
        
        $gambar = dosen::where('dsn_nip', $nip)->first();

        if($gambar->dsn_foto != "default-dosen.jpg"){
            File::delete(public_path('image/dsn_img/'.$gambar->mhs_foto));//hapus foto lama
        }  

        if($request->hasFile('upload_gambar')) {

            $image       = $request->file('upload_gambar');
            $filename    = 'img_dsn' . '_' . uniqid() . '.' . $image->getClientOriginalExtension();        
            $image_resize = Image::make($image->getRealPath());              
            $image_resize->resize(400, 400);
            $image_resize->save(public_path('image/dsn_img/' .$filename));
            dosen::where('dsn_nip', $nip)->update(['dsn_foto' => $filename]);
            Session::put('session_gambar', $filename);                              
        }
        $request->session()->flash('alert-success', "Foto profile berhasil diperbaharui!");
        return redirect()->back();
    }

    public function getPageUbahPasswordDosen(){
        $bioDosenPass = dosen::where('username',Session::get('session_username'))->first();
        return view('dosen.profil_edit-pass', compact('bioDosenPass'));
    }

    public function putUbahPasswordDosen(Request $request, $username){
        $user = user::where('username', Session::get('session_username'))->first();
        if($request->password_lama == null){
            $request->session()->flash('alert-danger', "Input password lama masih kosong");
            return redirect()->back();
        }
        if(!Hash::check($request->password_lama,  $user->password)){
            $request->session()->flash('alert-danger', "Input password lama tidak sama");
            return redirect()->back();
        }
        $this->validate($request, [
            'password_lama' => 'required|min:5|max:32',
            'password_baru' => 'required|different:password_lama|min:5|max:32',
            'password_ulangi' => 'required|same:password_baru', 
        ]);
    
        if(Hash::check($request->password_lama,  $user->password)){ //cek password lama true
            user::Where('username', Session::get('session_username'))->update(['password' => bcrypt($request->password_baru)]);
            $request->session()->flash('alert-success', "Password Berhasil Diubah!");
            return redirect()->back();
        }
    }
//END DOSEN=======================================================================================

//MAHASISWA=======================================================================================
    public function getPageBiodataMahasiswa(){
        $mhsBio= mahasiswa::where('mhs_nim', Session::get('session_nim'))->first();
        $namaTim="";
        foreach($mhsBio->tbl_proyek_akhir as $index => $proyek){
            $namaTim = $proyek->nama_tim;
        }
        
        $proyekAkhir = proyek_akhir::where('nama_tim', $namaTim)->get();
        return view('mahasiswa.profil-edit-bio', compact('mhsBio', 'namaTim', 'proyekAkhir'));   
    }

    public function putUbahBiodataMahasiswa(Request $request, $nim){
        
        $this->validate($request, [
            'bio_mhsNama' => 'required|min:4|max:64',
        ]);

        if( Session::get('session_username') != $request->bio_username){
            $this->validate($request, [
                'bio_username' => 'required|min:4|max:32|unique:user,username',
            ]);
            user::where('username', Session::get('session_username'))->update(['username' => $request->bio_username]);
            Session::put('session_username', $request->bio_username);
        }

        if(Session::get('session_email') != $request->bio_mhsEmail){
            $this->validate($request, [
                'bio_mhsEmail' => 'required|email|min:7|max:32|unique:mahasiswa,mhs_email',
            ]);
            mahasiswa::where('mhs_nim', Session::get('session_nim'))->update(['mhs_email' => $request->bio_mhsEmail]);
            Session::put('session_email', $request->bio_mhsEmail); 
        }

        if(Session::get('session_kontak') != $request->bio_mhskontak){
            $this->validate($request, [
                'bio_mhskontak' => 'required|numeric|digits_between:10,14|unique:mahasiswa,mhs_kontak',
            ]);
            mahasiswa::where('mhs_nim', Session::get('session_nim'))->update(['mhs_kontak' => $request->bio_mhskontak]);
            Session::put('session_kontak', $request->bio_mhskontak); 
        }
        $mhsBioUpdate = mahasiswa::where('mhs_nim', Session::get('session_nim'))->first();
        $mhsBioUpdate->mhs_nama = $request->bio_mhsNama;
        $mhsBioUpdate->save();
        Session::put('session_nama', $request->bio_mhsNamas);

        $request->session()->flash('alert-success', "Biodata Berhasil Diperbaharui!");
        return redirect()->back();
    }

    public function getPageFotoProfileMahasiswa(){
        $mhsBioFoto = mahasiswa::where('mhs_nim', Session::get('session_nim'))->first();
        return view('mahasiswa.profil-edit-foto', compact('mhsBioFoto'));   
    }

    public function putFotoProfileMahasiswa(Request $request, $nim){
        $this->validate($request, [
			'upload_gambar' => 'required|file|image|mimes:jpeg,png,jpg|max:2048',
        ]);

        $gambar = mahasiswa::where('mhs_nim', $nim)->first();
        if($gambar->mhs_foto != "default-mahasiswa.jpg"){
            File::delete(public_path('image/mhs_img/'.$gambar->mhs_foto));//hapus foto lama
        }  

        if($request->hasFile('upload_gambar')) {

            $image       = $request->file('upload_gambar');
            $filename    = 'img_mhs' . '_' . uniqid() . '.' . $image->getClientOriginalExtension();        
            $image_resize = Image::make($image->getRealPath());              
            $image_resize->resize(400, 400);
            $image_resize->save(public_path('image/mhs_img/' .$filename));
            mahasiswa::where('mhs_nim', $nim)->update(['mhs_foto' => $filename]);
            Session::put('session_gambar', $filename);                              
        }
        $request->session()->flash('alert-success', "Foto profile berhasil diperbaharui!");
        return redirect()->back();
    }

    public function getPageUbahPasswordMahasiswa(){
        $mahasiswa = user::where('username', Session::get('session_username'))->first();
        return view('mahasiswa.profil-edit-pass', compact('mahasiswa'));   
    }

    public function putUbahPasswordMahasiswa(Request $request, $username){
        $user = user::where('username', Session::get('session_username'))->first();
        if($request->password_lama == null){
            $request->session()->flash('alert-danger', "Input password lama masih kosong");
            return redirect()->back();
        }
        if(!Hash::check($request->password_lama,  $user->password)){
            $request->session()->flash('alert-danger', "Input password lama tidak sama");
            return redirect()->back();
        }
        $this->validate($request, [
            'password_lama' => 'required|min:5|max:32',
            'password_baru' => 'required|different:password_lama|min:5|max:32',
            'password_ulangi' => 'required|same:password_baru', 
        ]);
       
        if(Hash::check($request->password_lama,  $user->password)){ //cek password lama true
            user::Where('username', Session::get('session_username'))->update(['password' => bcrypt($request->password_baru)]);
            $request->session()->flash('alert-success', "Password Berhasil Diubah!");
            return redirect()->back();
        }
    }
//END MAHASISWA=======================================================================================
}
