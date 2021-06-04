<?php

namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\mahasiswa;
use App\Models\user;
use Illuminate\Support\Facades\DB;

class ApiControllerMahasiswa extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $response = DB::table('mahasiswa')
        ->leftjoin('judul', 'judul.judul_id', '=', 'mahasiswa.judul_id')
        ->get();
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
        
        $status_user        = "mahasiswa";
        $status_mahasiswa   = "aktif";
        $foto_default       = "default-mahasiswa.jpg";

        $this->validate($request, [
            'mhs_nim' => 'required',
            'mhs_nama' => 'required'
        ]);

        $exist = user::find($request->mhs_nim);
        if($exist){
            $response = "NIM mahasiswa sudah ada";
            return response()->json($response, 404);
        }else {

            $user = new user();
            $mahasiswa = new mahasiswa();

            $user->username = $request->mhs_nim;
            $user->password = bcrypt($request->mhs_nim);
            $user->pengguna = $status_user;

            $angkatan = '20'. substr($request->mhs_nim, 4,2);

            $mahasiswa->mhs_nim     = $request->mhs_nim;
            $mahasiswa->mhs_nama    = $request->mhs_nama;
            $mahasiswa->angkatan    = $angkatan;
            $mahasiswa->mhs_foto    = $foto_default;
            $mahasiswa->status      = $status_mahasiswa;
            $mahasiswa->username    = $request->mhs_nim;

            if (!$user->save()) {
                $response = "Sesuatu eror terjadi"; 
                $showUser = 'Gagal menyimpan user';
                return response()->json($response, 404); 
            } else {
                $showUser = "berhasil menambahkan data user mahasiswa";
            }

            if (!$mahasiswa->save()) {
                $response = "Sesuatu eror terjadi";
                $showMahasiswa = "Gagal menyimpan dosen";  
                return response()->json($response, 404); 
            } else {
                $showMahasiswa = "berhasil menambahkan data mahasiswa";
            }

            $response = [
                'user'      => $showUser,
                'mahasiswa' => $showMahasiswa
            ];

            return response()->json($response, 201);;

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

        $response = mahasiswa::find($id)
        ->leftjoin('judul', 'judul.judul_id', '=', 'mahasiswa.judul_id')
        ->where('mhs_nim', $id)
        ->first();
        return response()->json($response, 200);

        // $response = mahasiswa::find($id);
        // return response()->json($response, 200);

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
        $mahasiswa = mahasiswa::find($id);

        $nim = $request->mhs_nim;

        if (!empty($nim)){
            $user->username         = $request->mhs_nim;
            $mahasiswa->mhs_nim     = $request->mhs_nim;

            if (!$user->save()) {
                $response = "Sesuatu eror terjadi"; 
                $showUser = 'Gagal merubah user';
                return response()->json($response, 404); 
            } else {
                $showUser = "berhasil merubah data user mahasiswa";
            }

        } else {
            $showUser = "tidak merubah user mahasiswa";
        }


        $mahasiswa->mhs_nama    = $request->mhs_nama;
        $mahasiswa->angkatan    = $request->mhs_angkatan;
        $mahasiswa->mhs_kontak  = $request->mhs_kontak;
        $mahasiswa->mhs_email   = $request->mhs_email;

        if (!$mahasiswa->save()) {
            $response = "Sesuatu eror terjadi";
            $showMahasiswa = "Gagal merubah dosen";  
            return response()->json($response, 404); 
        } else {
            $showMahasiswa = "berhasil merubah data mahasiswa";
        }

        $response = [
            'user'      => $showUser,
            'mahasiswa' => $showMahasiswa
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
        $mahasiswa = mahasiswa::find($id);
        
        if (!$user->delete()) {
            $response = "Sesuatu eror terjadi"; 
            $showUser = 'Gagal menyimpan user';
            return response()->json($response, 404); 
        } else {
            $showUser = "berhasil menghapus data user mahasiswa";
        }

        if (!$mahasiswa->delete()) {
            $response = "Sesuatu eror terjadi";
            $showMahasiswa = "Gagal menyimpan dosen";  
            return response()->json($response, 404); 
        } else {
            $showMahasiswa = "berhasil menghapus data mahasiswa";
        }

        $response = [
            'user'      => $showUser,
            'mahasiswa' => $showMahasiswa
        ];

        return response()->json($response, 201);;

    }

    public function updateJudulMahasiswa(Request $request, $id) {

        $mahasiswa = mahasiswa::find($id);
        
        $requestJudulId = $request->judul_id;

        if($requestJudulId==0) {
            $mahasiswa->judul_id = null;
        } else {
            $mahasiswa->judul_id = $requestJudulId;
        }

        if (!$mahasiswa->save()) {
            $response = "Sesuatu eror terjadi";
            $showMahasiswa = "Gagal merubah dosen";  
            return response()->json($response, 404); 
        } else {
            $showMahasiswa = "berhasil merubah data mahasiswa";
        }

        $response = [
            'mahasiswa' => $showMahasiswa
        ];

        return response()->json($response, 201);

    }

}
