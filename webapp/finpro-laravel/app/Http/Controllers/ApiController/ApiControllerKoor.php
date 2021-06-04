<?php

namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use App\Models\koordinator_pa;
use App\Models\user;
use Illuminate\Support\Facades\DB;

use Illuminate\Http\Request;

class ApiControllerKoor extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $response = koordinator_pa::all();
        return response()->json($response, 201);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        
        $status_user = "koor";
        $foto_default = "default-dosen.jpg";

        $exist=user::find($request->koor_nip);
        if($exist){
            $response = "NIP koor sudah ada";
            return response()->json($response, 404);
        } else {
            $this->validate($request, [
                'username'  => 'required',
                'koor_nip'  => 'required',  
                'koor_nama' => 'required', 
                'koor_kode' => 'required'
            ]);

            $user = new user();
            $koor = new koordinator_pa();

            $user->username = $request->username;
            $user->password = bcrypt($request->username);
            $user->pengguna = $status_user;

            $koor->koor_nip    = $request->koor_nip;
            $koor->koor_nama   = $request->koor_nama;
            $koor->koor_kode   = $request->koor_kode;
            $koor->koor_kontak = $request->koor_kontak;
            $koor->koor_foto   = $foto_default;
            $koor->koor_email  = $request->koor_email;
            $koor->username    = $request->username;


            if (!$user->save()) {
                $response = "Sesuatu eror terjadi"; 
                $showUser = 'Gagal menyimpan user';
                return response()->json($response, 404); 

            } else {
                $showUser = "Berhasil menyimpan data user koor";
            }

            if (!$koor->save()) {
                $response = "Sesuatu eror terjadi";
                $showKoor = "Gagal menyimpan koor";  
                return response()->json($response, 404); 
            } else {
                $showKoor = "Berhasil menyimpan data koor";
            }

            $response = [
                'user'      => $showUser,
                'koor'     => $showKoor
            ];
            return response()->json($response, 201);

        }

    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $response = koordinator_pa::all()->where('username', $id)->first();
        return response()->json($response, 200);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        
        $user = user::find($id);
        $koor = koordinator_pa::all()->where('username', $id)->first();

        $usernames = $request->username;

        if (!empty($usernames)) {
            $user->username = $usernames;
            $koor->username = $usernames;
            
            if (!$user->save()) {
                $response = "Sesuatu eror terjadi"; 
                $showUser = 'Gagal merubah user';
                return response()->json($response, 404); 
            } else {
                $showUser = "Berhasil merubah data user koor";
            }
        
        } else {
            $showUser = "tidak merubah user koor";
        }

        $koor->koor_nip    = $request->koor_nip;
        $koor->koor_nama   = $request->koor_nama;
        $koor->koor_kode   = $request->koor_kode;
        $koor->koor_kontak = $request->koor_kontak;
        $koor->koor_email  = $request->koor_email;

        if (!$koor->save()) {
            $response = "Sesuatu eror terjadi";
            $showkoor = "Gagal merubah koor";  
            return response()->json($response, 404); 
        } else {
            $showkoor = "Berhasil merubah data koor";
        }

        $response = [
            'user'      => $showUser,
            'koor'     => $showkoor
        ];
        return response()->json($response, 201);;
        
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        
        $user = user::find($id);
        $koor = koordinator_pa::find($id);
        
        if (!$user->delete()) {
            $response = "Sesuatu eror terjadi"; 
            $showUser = 'Gagal menyimpan user';
            return response()->json($response, 404); 
        } else {
            $showUser = "berhasil menghapus data user koor";
        }

        if (!$koor->delete()) {
            $response = "Sesuatu eror terjadi";
            $showKoor = "Gagal menyimpan koor";  
            return response()->json($response, 404); 
        } else {
            $showKoor = "berhasil menghapus data koor";
        }

        $response = [
            'user'      => $showUser,
            'koor'     => $showKoor
        ];

        return response()->json($response, 201);;

    }
}
