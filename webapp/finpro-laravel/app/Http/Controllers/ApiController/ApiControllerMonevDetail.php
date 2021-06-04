<?php

namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\detail_monev;
use Illuminate\Support\Facades\DB;

class ApiControllerMonevDetail extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

        $response = DB::table('detail_monev')
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'detail_monev.proyek_akhir_id')
        ->join('monev', 'monev.monev_id', '=', 'detail_monev.monev_id')
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
        
        $detail_monev = new detail_monev();
        $detail_monev->monev_nilai          = $request->monev_nilai;
        $detail_monev->monev_tanggal        = $request->monev_tanggal;
        $detail_monev->monev_ulasan         = $request->monev_ulasan;
        $detail_monev->monev_id             = $request->monev_id;
        $detail_monev->proyek_akhir_id      = $request->proyek_akhir_id;

        if (!$detail_monev->save()) {
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
        
        $response = DB::table('detail_monev')
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'detail_monev.proyek_akhir_id')
        ->join('monev', 'monev.monev_id', '=', 'detail_monev.monev_id')
        ->where('monev_detail_id', $id)
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
        
        $detail_monev = detail_monev::find($id);
        $detail_monev->monev_nilai          = $request->monev_nilai;
        $detail_monev->monev_tanggal        = $request->monev_tanggal;
        $detail_monev->monev_ulasan         = $request->monev_ulasan;

        if (!$detail_monev->save()) {
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
        $monev = detail_monev::find($id);
        
        if (!$monev->delete()) {
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

    public function searchDetailMonevBy($parameter, $query) {

        $response = DB::table('detail_monev')
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'detail_monev.proyek_akhir_id')
        ->join('monev', 'monev.monev_id', '=', 'detail_monev.monev_id')
        ->where($parameter, $query)
        ->get();

        return response()->json($response, 201);
    
    }

    public function searchDetailMonevBy2($parameter1, $query1, $parameter2, $query2) {

        $parameter = [$parameter1 => $query1, $parameter2 => $query2];

        $response = DB::table('detail_monev')
        ->join('proyek_akhir', 'proyek_akhir.proyek_akhir_id', '=', 'detail_monev.proyek_akhir_id')
        ->join('monev', 'monev.monev_id', '=', 'detail_monev.monev_id')
        ->where($parameter)
        ->get();

        return response()->json($response, 201);
    
    }


}
