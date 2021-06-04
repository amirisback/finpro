<?php

namespace App\Http\Controllers\Auth_User;

use App\Http\Controllers\Controller;
use Illuminate\Foundation\Auth\AuthenticatesUsers;
use Illuminate\Support\Facades\Hash;
use Illuminate\Http\Request;
use Session;
use App\Models\user;
use App\Models\mahasiswa;
use App\Models\dosen;
use App\Models\koordinator_pa;

class LoginController extends Controller
{
    /*
    |--------------------------------------------------------------------------
    | Login Controller
    |--------------------------------------------------------------------------
    |
    | This controller handles authenticating users for the application and
    | redirecting them to your home screen. The controller uses a trait
    | to conveniently provide its functionality to your applications.
    |
    */

    use AuthenticatesUsers;

    /**
     * Where to redirect users after login.
     *
     * @var string
     */
    //protected $redirectTo = '/home';

    /**
     * Create a new controller instance.
     *
     * @return void
     */
    // public function __construct()
    // {
    //     $this->middleware('guest')->except('logout');
    // }

   
    
    public function index(){
        if(!Session::get('login')){
            return redirect('login')->with('alert','Kamu harus login dulu');
        }        
    }

    public function postLogInUser(Request $request){
        $this->validate($request, [
            'username' => 'required',
            'password' => 'required'
          ]);    

          $username = $request->username;
          $password = $request->password;


        $data = user::where('username', $username)->first();
        $mhs =  mahasiswa::where('mhs_nim', $username)->first();
        $dosen =  dosen::where('dsn_nip', $username)->first();
        $koor =  koordinator_pa::where('username', $username)->first();

        if($data){ //cek username
            if(Hash::check($password,$data->password)){ //cek password
                if($mhs){ //kalau sebagai mahasiswa
                    Session::put('session_nim', $username);
                    Session::put('session_nama', $mhs->mhs_nama);
                    Session::put('session_kontak', $mhs->mhs_kontak);
                    Session::put('session_gambar', $mhs->mhs_foto);                    
                    Session::put('session_email', $mhs->mhs_email);
                    Session::put('session_status', $mhs->status);
                    Session::put('session_idJudul', $mhs->id_judul); 
                    Session::put('session_username', $mhs->username);
                    Session::put('session_pengguna', "mahasiswa");  //login ==mahasiswa
                    Session::put('login',TRUE);  //login ==mahasiswa
                    return redirect()->intended(route('mahasiswa.beranda')); 
                }else if($dosen){  //kalau sebagai Dosen
                    Session::put('session_nip', $username);
                    Session::put('session_nama', $dosen->dsn_nama);
                    Session::put('session_kode', $dosen->dsn_kode);
                    Session::put('session_kontak', $dosen->dsn_kontak);
                    Session::put('session_gambar', $dosen->dsn_foto);                              
                    Session::put('session_email', $dosen->dsn_email);  
                    Session::put('session_username', $dosen->username);
                    Session::put('session_pengguna', "dosen");  //login ==dosen
                    Session::put('login',TRUE);  //login ==dosen    
                    return redirect()->intended(route('dosen.beranda')); 
                }else if($koor){  //kalau sebagai admin
                    Session::put('session_nip', $koor->koor_nip);
                    Session::put('session_nama', $koor->koor_nama);
                    Session::put('session_kode', $koor->koor_kode);  
                    Session::put('session_kontak', $koor->koor_kontak);              
                    Session::put('session_gambar', $koor->koor_foto);                              
                    Session::put('session_email', $koor->koor_email); 
                    Session::put('session_username', $koor->username);
                    Session::put('session_pengguna', "koordinator");  //login ==koordinator
                    Session::put('login',TRUE);  //login ==koordinator    
                    return redirect()->intended(route('koordinator.beranda')); 
                }
            }
            else{
                return redirect()->back()->withInput($request->only('username'))->with('alert','Password atau Email, Salah !');
            }
        }
        else{
            return redirect()->back()->withInput($request->only('username'))->with('alert','Password atau Email, Salah !');
        }
    }

    public function login(){
        if(!Session::get('login')){
            return view('log-in.index');  //login User
        }else{
            return redirect()->back();
        }
    }

    public function logout(){
        Session::flush();
        return redirect('/');
    }

}
