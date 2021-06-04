<?php

namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\proyek_akhir;
use Illuminate\Support\Facades\DB;

class ApiControllerProyekAkhir extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
 
    public $arrayResponse = ['proyek_akhir.proyek_akhir_id',
                            'proyek_akhir.nilai_total',
                            'proyek_akhir.nama_tim',
                            'proyek_akhir.mhs_nim',
                            'mahasiswa.mhs_nama',
                            'mahasiswa.angkatan AS mhs_angkatan',
                            'mahasiswa.mhs_kontak',
                            'mahasiswa.mhs_foto',
                            'mahasiswa.mhs_email',
                            'mahasiswa.status AS mhs_status',
                            'mahasiswa.judul_id AS mhs_judul_id',
                            'mahasiswa.username AS mhs_username',
                            'proyek_akhir.judul_id',
                            'judul.judul_nama',
                            'judul.judul_deskripsi',
                            'judul.judul_status',
                            'judul.judul_waktu',
                            'judul.kategori_id',
                            'judul.dsn_nip AS pembimbing_dsn_nip',
                            'proyek_akhir.dsn_nip AS reviewer_dsn_nip',
                            'dosen.dsn_nama AS reviewer_dsn_nama',
                            'dosen.dsn_kode AS reviewer_dsn_kode',
                            'dosen.dsn_foto AS reviewer_dsn_foto',
                            'dosen.dsn_email AS reviewer_dsn_email'
                        ];

    public $arrayResponseDistinct = [
                                    'proyek_akhir.judul_id',
                                    'judul.judul_nama',
                                    'judul.judul_deskripsi',
                                    'judul.judul_status',
                                    'judul.judul_waktu',
                                    'judul.kategori_id',
                                    'proyek_akhir.nama_tim',
                                    'judul.dsn_nip AS pembimbing_dsn_nip',
                                    'proyek_akhir.dsn_nip AS reviewer_dsn_nip',
                                    'dosen.dsn_nama AS reviewer_dsn_nama',
                                    'dosen.dsn_kode AS reviewer_dsn_kode',
                                    'dosen.dsn_foto AS reviewer_dsn_foto',
                                    'dosen.dsn_email AS reviewer_dsn_email'
                                ];
 
    public function index() 
    {

        $response = DB::table('proyek_akhir')
        ->select($this->arrayResponse)
        ->join('mahasiswa', 'mahasiswa.mhs_nim', '=', 'proyek_akhir.mhs_nim')
        ->join('judul', 'judul.judul_id', '=', 'proyek_akhir.judul_id')
        ->leftjoin('dosen', 'dosen.dsn_nip', '=', 'proyek_akhir.dsn_nip')
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
        
        $proyek_akhir = new proyek_akhir();
        $proyek_akhir->judul_id = $request->judul_id;
        $proyek_akhir->nama_tim = $request->nama_tim;
        $proyek_akhir->mhs_nim = $request->mhs_nim;

        if (!$proyek_akhir->save()) {
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

        $response = DB::table('proyek_akhir')
        ->select($this->arrayResponse)
        ->join('mahasiswa', 'mahasiswa.mhs_nim', '=', 'proyek_akhir.mhs_nim')
        ->join('judul', 'judul.judul_id', '=', 'proyek_akhir.judul_id')
        ->leftjoin('dosen', 'dosen.dsn_nip', '=', 'proyek_akhir.dsn_nip')
        ->where('proyek_akhir_id', $id)
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
        
        $proyek_akhir = proyek_akhir::find($id);
        $proyek_akhir->nilai_total = $request->nilai_total;
        $proyek_akhir->nama_tim = $request->nama_tim;
        $proyek_akhir->id_judul = $request->id_judul;
        $proyek_akhir->mhs_nim = $request->mhs_nim;
        $proyek_akhir->dsn_nip = $request->dsn_nip;

        if (!$proyek_akhir->save()) {
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
        
        $proyek_akhir = proyek_akhir::find($id);
        
        if (!$proyek_akhir->delete()) {
            $response = [ "message" => "Sesuatu eror terjadi" ];  
            return response()->json($response, 404);
        } else {
            $response = [ "message" => "Sukses menghapus data" ];
            return response()->json($response, 201);
        }

    }

    public function updateDsnNip(Request $request, $id)
    {
        
        $proyek_akhir = proyek_akhir::find($id);
        $proyek_akhir->dsn_nip = $request->dsn_nip;

        if (!$proyek_akhir->save()) {
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

    public function updateNilaiTotal(Request $request, $id)
    {
        
        $proyek_akhir = proyek_akhir::find($id);
        $proyek_akhir->nilai_total = $request->nilai_total;

        if (!$proyek_akhir->save()) {
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

    public function searchAllProyekAkhirBy($parameter, $query) {

        $response = DB::table('proyek_akhir')
        ->select($this->arrayResponse)
        ->join('mahasiswa', 'mahasiswa.mhs_nim', '=', 'proyek_akhir.mhs_nim')
        ->join('judul', 'judul.judul_id', '=', 'proyek_akhir.judul_id')
        ->leftjoin('dosen', 'dosen.dsn_nip', '=', 'proyek_akhir.dsn_nip')
        ->where($parameter, $query)
        ->get();

        return response()->json($response, 201);
    
    }

    public function searchAllProyekAkhirBy2($parameter1, $query1, $parameter2, $query2) {

        $parameter = [$parameter1 => $query1, $parameter2 => $query2];
        
        $response = DB::table('proyek_akhir')
        ->select($this->arrayResponse)
        ->join('mahasiswa', 'mahasiswa.mhs_nim', '=', 'proyek_akhir.mhs_nim')
        ->join('judul', 'judul.judul_id', '=', 'proyek_akhir.judul_id')
        ->leftjoin('dosen', 'dosen.dsn_nip', '=', 'proyek_akhir.dsn_nip')
        ->where($parameter)
        ->distinct()
        ->get();

        return response()->json($response, 201);
    
    }

    public function searchDistinctProyekAkhirBy($parameter, $query) {

        $response = DB::table('proyek_akhir')
        ->select($this->arrayResponseDistinct)
        ->join('judul', 'judul.judul_id', '=', 'proyek_akhir.judul_id')
        ->leftjoin('dosen', 'dosen.dsn_nip', '=', 'proyek_akhir.dsn_nip')
        ->where($parameter, $query)
        ->distinct()
        ->get();

        return response()->json($response, 201);
    
    }

    public function searchDistinctProyekAkhirBy2($parameter1, $query1, $parameter2, $query2) {

        $parameter = [$parameter1 => $query1, $parameter2 => $query2];
        
        $response = DB::table('proyek_akhir')
        ->select($this->arrayResponseDistinct)
        ->join('judul', 'judul.judul_id', '=', 'proyek_akhir.judul_id')
        ->leftjoin('dosen', 'dosen.dsn_nip', '=', 'proyek_akhir.dsn_nip')
        ->where($parameter)
        ->distinct()
        ->get();

        return response()->json($response, 201);
    
    }

    public function getProyekAkhirDistinct(){
        $response = DB::table('proyek_akhir')
        
        ->select(DB::raw('COUNT(DISTINCT proyek_akhir.nama_tim) as jumlah_nama_tim'))
        // ->select($this->arrayResponseDistinct)
        ->join('judul', 'judul.judul_id', '=', 'proyek_akhir.judul_id')
        ->leftJoin('dosen', 'dosen.dsn_nip', '=', 'proyek_akhir.dsn_nip')
        ->distinct()
        ->get();

        return response()->json($response, 201);
    }


    public function searchDistinctProyekAkhirTimBy2($parameter1, $query1, $parameter2, $query2) {

        $parameter = [$parameter1 => $query1, $parameter2 => $query2];
        
        $responseProyekAkhir = DB::table('proyek_akhir')
        ->select($this->arrayResponseDistinct)
        ->join('judul', 'judul.judul_id', '=', 'proyek_akhir.judul_id')
        ->leftjoin('dosen', 'dosen.dsn_nip', '=', 'proyek_akhir.dsn_nip')
        ->where($parameter)
        ->distinct()
        ->get();


        $responseCountJumlahKelompok = DB::table('proyek_akhir')
        ->select(DB::raw('COUNT(DISTINCT proyek_akhir.nama_tim) as jumlah_tim'))
        ->join('judul', 'judul.judul_id', '=', 'proyek_akhir.judul_id')
        ->where($parameter)
        ->get();


    
        $response = [
            'proyek_akhir' => $responseProyekAkhir,
            'jumlah_tim' => $responseCountJumlahKelompok
        ];

        return response()->json($response, 201);
    
    }

}
