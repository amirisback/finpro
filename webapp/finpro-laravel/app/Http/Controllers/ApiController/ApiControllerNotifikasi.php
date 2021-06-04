<?php

namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use App\Models\notifikasi;

class ApiControllerNotifikasi extends Controller
{

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $response = notifikasi::all();
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
        
        $pemberitahuan = new notifikasi();
        $pemberitahuan->notifikasi_kategori    = $request->notifikasi_kategori;
        $pemberitahuan->notifikasi_deskripsi   = $request->notifikasi_deskripsi;
        $pemberitahuan->notifikasi_dari        = $request->notifikasi_dari;
        $pemberitahuan->notifikasi_untuk       = $request->notifikasi_untuk;
        $pemberitahuan->notifikasi_baca        = 0;
      
        if (!$pemberitahuan->save()) {
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
        
        $response = notifikasi::find($id);
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

        $pemberitahuan = notifikasi::find($id);
        $pemberitahuan->notifikasi_baca = $request->notifikasi_baca;
      
        if (!$pemberitahuan->save()) {
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
        
        $notifikasi = notifikasi::find($id);

        if (!$notifikasi->delete()) {
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

    public function searchNotifikasiBy($parameter, $query){

        $parameter = [$parameter => $query];
        
        $response = DB::table('notifikasi')
        ->select("*")
        ->where($parameter)
        ->get();

        return response()->json($response, 201);

    }

    public function searchNotifikasiBy2($parameter1, $query1, $parameter2, $query2){

        $parameter = [$parameter1 => $query1, $parameter2 => $query2];
        
        $response = DB::table('notifikasi')
        ->select("*")
        ->where($parameter)
        ->get();

        return response()->json($response, 201);

    }

    public function sortNotifikasi($query1, $query2){

        $parameter = "notifikasi_untuk";
        $semua = "semua";

        $response = DB::table('notifikasi')
        ->select("*")
        ->where($parameter, '=', $semua)
        ->orwhere($parameter, '=', $query1)
        ->orwhere($parameter, '=', $query2)
        ->orderBy('notifikasi_id', 'desc')
        ->get();

        return response()->json($response, 201);

    }

    
}
