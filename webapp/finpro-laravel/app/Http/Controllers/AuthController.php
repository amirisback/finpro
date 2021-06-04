<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Controllers\Controller;
//use App\User;
//baru

class AuthController extends Controller
{

    public function debbuging(){

        // $lastId = judul_pa::orderBy('id_judul', 'desc')->first();
        // $temp = $lastId->id_judul + 1;
        // ($temp);

        // $id_grup = "JDL".uniqid();
        // dd($id_grup);
        // $lastUser = Judul_pa::orderBy('id_judul', 'desc')->first();
        // $temp = $lastUser->id + 1;

        // dd($temp);


        //set form to array data
        // $collection = collect([]);
        // $collection->prepend(99);
        // $collection->prepend(991);
        // $collection->prepend(992);
        // dd($collection->all());
    }

    // public function getOneMany()
    // {
    //     $title = 'One To Many and Many To One Relationships ONPHPID.';
    //     $content = 'Saat ini kita belajar relasi One To Many and Many To One.';
    //     $judulss = \App\Models\Judul::all();
    
    //     return view('one_to_many', compact('judulss', 'title', 'content'));
    // }
    
    // public function getmanyOne()
    // {
    //     // $title = 'One To Many and Many To One Relationships ONPHPID.';
    //     // $content = 'Saat ini kita belajar relasi One To Many and Many To One.';
    //     $mahasiswass = \App\Models\Mahasiswa::all();
    
    //     return view('test', compact('mahasiswass'));
    // }


    public function register()
    {
        return view('log-in.admin-register');
    }























    // public function __construct()
    // {
    //     //$this->middleware('guest:admin');
    // }


    // public function postLoginAdmin(Request $request)
    // {
    //     $this->validate($request, [
    //         'username' => 'required',
    //         'password' => 'required'
    //       ]);      
    
    //     if(Auth::guard('admin')->attempt([ 'username' => $request->username , 'password' => $request->password])){
    //         return redirect()->intended('admin/judul');
    //     }else{
    //         return redirect()->back()->withInput($request->only('username'));
    //     }
    //     //return redirect()->route('admin.beranda');
    //     //return redirect()->route('admin.beranda');
    // }

    // public function getLogoutAdmin()
    // {

    //     //belum beres
    //     if (Auth::guard('admin')->check()) {
    //         Auth::guard('admin')->logout();
    //       } elseif (Auth::guard('user')->check()) {
    //         Auth::guard('user')->logout();
    //       }        
    //     return redirect()->route('admin.logout');
    // }

    public function postRegisterAdmin(Request $request)  //register Admin baru
    {
        $this->validate($request, [
            'name' => 'required|min:10',
            'nip' => 'required|min:12',
            'username' => 'required|min:12',
            'password' => 'required|min:6|confirmed',
            'email' => 'required|email',
            'noTelp' => 'required|min:12',
        ]);

    //     Admin::create([
    //         'nama_admin' =>$request->name,
    //         'nip_admin' =>$request->nip,
    //         'username' =>$request->username,
    //         'password' =>bcrypt($request->password),
    //         'email' =>$request->email,
    //         'no_telpon' =>$request->noTelp,
    //     ]);
    //     return redirect()->back();
    }



    public function getRegister(){
        return view('log-in.admin-register');
    }

    // public function index()
    // {
    //     return view('log-in.index');
    // }


  
}