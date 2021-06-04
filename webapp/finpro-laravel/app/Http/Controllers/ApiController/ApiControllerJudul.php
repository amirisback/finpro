<?php

namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\judul;
use App\Models\proyek_akhir;
use Illuminate\Support\Facades\DB;

class ApiControllerJudul extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $response = DB::table('judul')
        ->join('kategori_judul', 'kategori_judul.kategori_id', '=', 'judul.kategori_id')
        ->join('dosen', 'dosen.dsn_nip', '=', 'judul.dsn_nip')
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

        $this->validate($request, [
            'judul_nama'        => 'required',
            'judul_deskripsi'   => 'required',
            'kategori_id'       => 'required',
            'dsn_nip'           => 'required'
        ]);

        $judul = new judul();

        $judul->judul_nama      = $request->judul_nama;
        $judul->judul_deskripsi = $request->judul_deskripsi;
        $judul->kategori_id     = $request->kategori_id;
        $judul->dsn_nip         = $request->dsn_nip;
        $judul->judul_status    = $request->judul_status;

        if (!$judul->save()) {
            $response = [
                "msg" => "Sesuatu eror terjadi",
                "judul" => "Tidak ada"
            ];  
            return response()->json($response, 404);
        } else {
            $response = [
                "msg" => "berhasil menambahkan judul",
                "judul" => $judul['judul_nama']
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

        $response = DB::table('judul')
        ->join('kategori_judul', 'kategori_judul.kategori_id', '=', 'judul.kategori_id')
        ->join('dosen', 'dosen.dsn_nip', '=', 'judul.dsn_nip')
        ->where('judul_id', $id)
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
        
        $this->validate($request, [
            'judul_nama'        => 'required',
            'judul_deskripsi'   => 'required',
            'kategori_id'       => 'required',
        ]);

        $judul = judul::find($id);

        $judul->judul_nama      = $request->judul_nama;
        $judul->judul_deskripsi = $request->judul_deskripsi;
        $judul->kategori_id     = $request->kategori_id;

        if (!$judul->save()) {
            $response = [
                "msg" => "Sesuatu eror terjadi",
                "judul" => "Tidak ada"
            ];  
            return response()->json($response, 404);
        } else {
            $response = [
                "msg" => "berhasil merubah judul",
                "judul" => $judul['judul_nama']
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

        $judul = judul::find($id);
        
        if (!$judul->delete()) {

            $response = [
                "msg" => "Sesuatu eror terjadi",
                "judul" => "Tidak ada"
            ];

            return response()->json($response, 404);
        } else {

            $response = [
                "msg" => "berhasil menghapus judul",
                "judul" => $judul['judul_nama']
            ];

            return response()->json($response, 201);
        }

    }

    public function searchJudulBy($parameter, $query) {

        $response = DB::table('judul')
        ->join('dosen', 'dosen.dsn_nip', '=', 'judul.dsn_nip')
        ->join('kategori_judul', 'kategori_judul.kategori_id', '=', 'judul.kategori_id')
        ->where($parameter, $query)
        ->get();

        return response()->json($response, 201);
    
    }

    public function searchJudulBy2($parameter1, $query1, $parameter2, $query2) {

        $parameter = [$parameter1 => $query1, $parameter2 => $query2];

        $response = DB::table('judul')
        ->join('dosen', 'dosen.dsn_nip', '=', 'judul.dsn_nip')
        ->join('kategori_judul', 'kategori_judul.kategori_id', '=', 'judul.kategori_id')
        ->where($parameter)
        ->get();

        return response()->json($response, 201);
    
    }

    public function searchJudulShowMhsBy($parameter, $query) {

        $response = DB::table('judul')
        ->join('dosen', 'dosen.dsn_nip', '=', 'judul.dsn_nip')
        ->join('kategori_judul', 'kategori_judul.kategori_id', '=', 'judul.kategori_id')
        ->whereIn('judul_status', ['tersedia', 'digunakan'])
        ->where($parameter, $query)
        ->get();

        return response()->json($response, 201);
    
    }

    public function updateStatusJudul(Request $request, $id){

        $judul = judul::find($id);
        $judul->judul_status = $request->judul_status;

        if (!$judul->save()) {
            $response = [
                "msg" => "Sesuatu eror terjadi",
                "judul" => "Tidak ada"
            ];  
            return response()->json($response, 404);
        } else {
            $response = [
                "msg" => "berhasil merubah judul",
                "status" => $judul['status']
            ];
            return response()->json($response, 201);
        }

    }

    public function searchJudulTeamByTwo($parameter1, $query1, $parameter2, $query2) {

        $parameter = [$parameter1 => $query1, $parameter2 => $query2];

        $responseJudul = DB::table('judul')
        ->join('dosen', 'dosen.dsn_nip', '=', 'judul.dsn_nip')
        ->join('kategori_judul', 'kategori_judul.kategori_id', '=', 'judul.kategori_id')
        ->where($parameter)
        ->get();

        foreach($responseJudul as $row) {
            $judul = $row->judul_id;
            $varJumlah = "jumlah_tim_judul_id_" . $judul;
            $responseSumTeam = DB::table('proyek_akhir')
            ->select(DB::raw('COUNT(DISTINCT proyek_akhir.nama_tim) as '. $varJumlah))
            ->where('proyek_akhir.judul_id', '=', $judul)
            ->get();
        }

        $response = [
            'Judul' => $responseJudul, 
            'ProyekAkhir' => $responseSumTeam
        ];

        return response()->json($response, 201);
    
    }


}
