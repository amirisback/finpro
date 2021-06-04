<?php

namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Models\bimbingan;
use App\Models\proyek_akhir;
use App\Models\mahasiswa;
use App\Models\judul;

class ApiControllerBimbingan extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */

    public $arrayResponse = [
                            'proyek_akhir.mhs_nim AS mhs_nim',
                            'mahasiswa.mhs_nama AS mhs_nama',
                            'mahasiswa.mhs_foto AS mhs_foto',
                            'judul.judul_nama AS judul_nama',
                            'proyek_akhir.nama_tim AS nama_tim'
                             ];

    public function index()
    {

        $response = DB::table('bimbingan')
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'bimbingan.proyek_akhir_id')
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
        $bimbingan = new bimbingan();
        $bimbingan->bimbingan_review = $request->bimbingan_review;
        $bimbingan->bimbingan_tanggal = $request->bimbingan_tanggal;
        $bimbingan->bimbingan_kehadiran = $request->bimbingan_kehadiran;
        $bimbingan->bimbingan_status = $request->bimbingan_status;
        $bimbingan->proyek_akhir_id = $request->proyek_akhir_id;

        if (!$bimbingan->save()) {
            $response = [
                "msg" => "Sesuatu eror terjadi",
                "success" => false
            ];  
            return response()->json($response, 404);
        } else {
            $response = [
                "msg" => "Berhasil menyimpan data",
                "success" => true
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
        
        $response = DB::table('bimbingan')
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'bimbingan.proyek_akhir_id')
        ->where('bimbingan_id', $id)
        ->get();
        
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
        
        $bimbingan = bimbingan::find($id);
        $bimbingan->bimbingan_review = $request->bimbingan_review;
        $bimbingan->bimbingan_tanggal = $request->bimbingan_tanggal;

        if (!$bimbingan->save()) {
            $response = [
                "msg" => "Sesuatu eror terjadi",
                "success" => false
            ];  
            return response()->json($response, 404);
        } else {
            $response = [
                "msg" => "Berhasil menyimpan data",
                "success" => true
            ];  
            return response()->json($response, 201);
        }

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        
        $bimbingan = bimbingan::find($id);
        
        if (!$bimbingan->delete()) {
            $response = [
                "msg" => "Sesuatu eror terjadi",
                "success" => false
            ];  
            return response()->json($response, 404);
        } else {
            $response = [
                "msg" => "Berhasil menghapus data",
                "success" => true
            ];  
            return response()->json($response, 201);
        }

    }

    public function getBimbinganSearchAllBy($parameter, $query)
    {
        
        $response = DB::table('bimbingan')
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'bimbingan.proyek_akhir_id')
        ->where($parameter, $query)
        ->get();
        
        return response()->json($response, 201);
    }

    public function getBimbinganSearchAllByTwo($parameter1, $query1, $parameter2, $query2)
    {
        
        $parameter = [$parameter1 => $query1, $parameter2 => $query2];
        $response = DB::table('bimbingan')
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'bimbingan.proyek_akhir_id')
        ->where($parameter)
        ->get();
        
        return response()->json($response, 201);
    }




    public function updateStatusBimbingan(Request $request, $id)
    {
        
        $bimbingan = bimbingan::find($id);
        $bimbingan->bimbingan_status = $request->bimbingan_status;
        
        if (!$bimbingan->save()) {
            $response = [
                "msg" => "Sesuatu eror terjadi",
                "success" => false
            ];  
            return response()->json($response, 404);
        } else {
            $response = [
                "msg" => "Berhasil menyimpan data",
                "success" => true
            ];  
            return response()->json($response, 201);
        }

    }

    public function getSiapSidang($jumlah_bimbingan){

        $parameter1 = "bimbingan.proyek_akhir_id";
        $query1 = "proyek_akhir.proyek_akhir_id";

        $parameter2 = "proyek_akhir.mhs_nim";
        $query2 = "mahasiswa.mhs_nim";
        
        $parameter3 = "mahasiswa.judul_id";
        $query3 = "judul.judul_id";

        $parameter4 = "bimbingan_status";
        $query4 = "disetujui";

        $arrayResponse = [
            'proyek_akhir.mhs_nim AS mhs_nim',
            'mahasiswa.mhs_nama AS mhs_nama',
            'mahasiswa.mhs_foto AS mhs_foto',
            'judul.judul_nama AS judul_nama',
            'proyek_akhir.nama_tim AS nama_tim'
             ];

        $parameter = [
            $parameter1 => $query1, 
            $parameter2 => $query2,
            $parameter3 => $query3,
            $parameter4 => $query4
        ];

        // $response = DB::table('bimbingan')
        // ->select(array('proyek_akhir.mhs_nim AS mhs_nim','mahasiswa.mhs_nama AS mhs_nama','mahasiswa.mhs_foto AS mhs_foto','judul.judul_nama AS judul_nama', 'proyek_akhir.nama_tim AS nama_tim', DB::raw('COUNT(*) as jumlah_bimbingan')))
        // ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'bimbingan.proyek_akhir_id')
        // ->join('mahasiswa', 'mahasiswa.mhs_nim', '=', 'proyek_akhir.mhs_nim')
        // ->join('judul', 'judul.judul_id', '=', 'mahasiswa.judul_id')
        // ->where($parameter)
        // ->groupBy("bimbingan.proyek_akhir_id")
        // ->havingRaw("COUNT(*) > 14")
        // ->get();

        $response = DB::select('SELECT proyek_akhir.nama_tim AS nama_tim, proyek_akhir.mhs_nim AS mhs_nim, mahasiswa.mhs_nama AS mhs_nama, mahasiswa.mhs_foto AS mhs_foto, judul.judul_nama AS judul_nama, COUNT(*) FROM bimbingan, proyek_akhir, mahasiswa, judul WHERE bimbingan.proyek_akhir_id = proyek_akhir.proyek_akhir_id and proyek_akhir.mhs_nim = mahasiswa.mhs_nim and mahasiswa.judul_id = judul.judul_id and bimbingan_status = "disetujui" GROUP BY bimbingan.proyek_akhir_id HAVING COUNT(*) >= 14');

        return response()->json($response, 201);
        
    }

}
