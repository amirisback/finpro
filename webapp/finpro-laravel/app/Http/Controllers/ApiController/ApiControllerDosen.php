<?php

namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\dosen;
use App\Models\user;
use Illuminate\Support\Facades\DB;

class ApiControllerDosen extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

        $response = dosen::all();
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
    
        $status_user = "dosen";
        $foto_default = "default-dosen.jpg";

        $exist=user::find($request->dsn_nip);
        if($exist){
            $response = "NIP dosen sudah ada";
            return response()->json($response, 404);
        } else {
            $this->validate($request, [
                'dsn_nip' => 'required',  
                'dsn_nama' => 'required', 
                'dsn_kode' => 'required',
            ]);

            $user = new user();
            $dosen = new dosen();

            $user->username = $request->dsn_nip;
            $user->password = bcrypt($request->dsn_nip);
            $user->pengguna = $status_user;

            $dosen->dsn_nip     = $request->dsn_nip;
            $dosen->dsn_nama    = $request->dsn_nama;
            $dosen->dsn_kode    = $request->dsn_kode;
            $dosen->dsn_foto    = $foto_default;
            $dosen->username    = $request->dsn_nip;
            
            if (!$user->save()) {
                $response = "Sesuatu eror terjadi"; 
                $showUser = 'Gagal menyimpan user';
                return response()->json($response, 404); 
            } else {
                $showUser = "Berhasil menyimpan data user dosen";
            }

            if (!$dosen->save()) {
                $response = "Sesuatu eror terjadi";
                $showDosen = "Gagal menyimpan dosen";  
                return response()->json($response, 404); 
            } else {
                $showDosen = "Berhasil menyimpan data dosen";
            }

            $response = [
                'user'      => $showUser,
                'dosen'     => $showDosen
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

        // $response = DB::table('dosen')
        // ->join('user', 'user.username', '=', 'user.username')
        // ->where('dsn_nip', $id)
        // ->get();
        
        $response = dosen::find($id);
        return response()->json($response, 201);

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
        $dosen = dosen::find($id);

        $nip = $request->dsn_nip;

        if (!empty($nip)) {
            $user->username = $nip;
            $dosen->dsn_nip = $nip;
            
            if (!$user->save()) {
                $response = "Sesuatu eror terjadi"; 
                $showUser = 'Gagal merubah user';
                return response()->json($response, 404); 
            } else {
                $showUser = "Berhasil merubah data user dosen";
            }
        
        } else {
            $showUser = "tidak merubah user dosen";
        }

        $dosen->dsn_nama    = $request->dsn_nama;
        $dosen->dsn_kode    = $request->dsn_kode;
        $dosen->dsn_kontak  = $request->dsn_kontak;
        $dosen->dsn_email   = $request->dsn_email;
        $dosen->batas_bimbingan = $request->batas_bimbingan;
        $dosen->batas_reviewer = $request->batas_reviewer;


        if (!$dosen->save()) {
            $response = "Sesuatu eror terjadi";
            $showDosen = "Gagal merubah dosen";  
            return response()->json($response, 404); 
        } else {
            $showDosen = "Berhasil merubah data dosen";
        }

        $response = [
            'user'      => $showUser,
            'dosen'     => $showDosen
        ];
        return response()->json($response, 201);

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
        $dosen = dosen::find($id);
        
        if (!$user->delete()) {
            $response = "Sesuatu eror terjadi"; 
            $showUser = 'Gagal menyimpan user';
            return response()->json($response, 404); 
        } else {
            $showUser = "berhasil menghapus data user dosen";
        }

        if (!$dosen->delete()) {
            $response = "Sesuatu eror terjadi";
            $showDosen = "Gagal menyimpan dosen";  
            return response()->json($response, 404); 
        } else {
            $showDosen = "berhasil menghapus data dosen";
        }

        $response = [
            'user'      => $showUser,
            'dosen'     => $showDosen
        ];

        return response()->json($response, 201);;


    }


    public function updatePure(Request $request, $id)
    {
        
        $user = user::find($id);
        $dosen = dosen::find($id);

        $nip = $request->dsn_nip;

        if (!empty($nip)) {
            $user->username = $nip;
            $dosen->dsn_nip = $nip;
            
            if (!$user->save()) {
                $response = "Sesuatu eror terjadi"; 
                $showUser = 'Gagal merubah user';
                return response()->json($response, 404); 
            } else {
                $showUser = "Berhasil merubah data user dosen";
            }
        
        } else {
            $showUser = "tidak merubah user dosen";
        }

        $dosen->dsn_nama    = $request->dsn_nama;
        $dosen->dsn_kode    = $request->dsn_kode;
        $dosen->dsn_kontak  = $request->dsn_kontak;
        $dosen->dsn_email   = $request->dsn_email;


        if (!$dosen->save()) {
            $response = "Sesuatu eror terjadi";
            $showDosen = "Gagal merubah dosen";  
            return response()->json($response, 404); 
        } else {
            $showDosen = "Berhasil merubah data dosen";
        }

        $response = [
            'user'      => $showUser,
            'dosen'     => $showDosen
        ];
        return response()->json($response, 201);

    }

}
