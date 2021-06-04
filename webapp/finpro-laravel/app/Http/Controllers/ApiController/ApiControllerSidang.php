<?php


namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\sidang;
use Illuminate\Support\Facades\DB;

class ApiControllerSidang extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */

    public $arrayResponse = [
        
                            'sidang.sidang_id',
                            'sidang.sidang_review',
                            'sidang.sidang_tanggal',
                            'sidang.nilai_proposal',
                            'sidang.nilai_penguji_1',
                            'sidang.nilai_penguji_2',
                            'sidang.nilai_pembimbing',
                            'sidang.nilai_total',
                            'sidang.sidang_status',
                            'sidang.proyek_akhir_id',
                            'proyek_akhir.nilai_total',
                            'proyek_akhir.nama_tim',
                            'proyek_akhir.mhs_nim',
                            'proyek_akhir.judul_id'
                        
                        ];


    public function index()
    {

        $response = DB::table('sidang')
        ->select($this->arrayResponse)
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'sidang.proyek_akhir_id')
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
        
        $sidang = new sidang();
        $sidang->sidang_review         = $request->sidang_review;
        $sidang->sidang_tanggal        = $request->sidang_tanggal;
        $sidang->nilai_proposal        = $request->nilai_proposal;
        $sidang->nilai_penguji_1       = $request->nilai_penguji_1;
        $sidang->nilai_penguji_2       = $request->nilai_penguji_2;
        $sidang->nilai_pembimbing      = $request->nilai_pembimbing;
        $sidang->nilai_total           = $request->nilai_total;
        $sidang->sidang_status         = $request->sidang_status;
        $sidang->proyek_akhir_id       = $request->proyek_akhir_id;

        if (!$sidang->save()) {
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
        $response = sidang::find($id);
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
        
        $sidang = sidang::find($id);
        $sidang->sidang_review         = $request->sidang_review;
        $sidang->sidang_tanggal        = $request->sidang_tanggal;
        $sidang->nilai_proposal        = $request->nilai_proposal;
        $sidang->nilai_penguji_1       = $request->nilai_penguji_1;
        $sidang->nilai_penguji_2       = $request->nilai_penguji_2;
        $sidang->nilai_pembimbing      = $request->nilai_pembimbing;
        $sidang->nilai_total           = $request->nilai_total;
        $sidang->sidang_status         = $request->sidang_status;

        if (!$sidang->save()) {
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
        $sidang = sidang::find($id);
        
        if (!$sidang->delete()) {
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

    public function searchAllSidangBy($parameter, $query){
        
        $response = DB::table('sidang')
        ->select($this->arrayResponse)
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'sidang.proyek_akhir_id')
        ->where($parameter, $query)
        ->get();

        return response()->json($response, 201);
    }


    public function searchAllSidangByTwo($parameter1, $query1, $parameter2, $query2){
        $parameter = [$parameter1 => $query1, $parameter2 => $query2];
        $response = DB::table('sidang')
        ->select($this->arrayResponse)
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'sidang.proyek_akhir_id')
        ->where($parameter)
        ->get();
        return response()->json($response, 201);
    }

}
