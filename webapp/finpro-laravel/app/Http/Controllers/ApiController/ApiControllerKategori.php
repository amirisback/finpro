<?php


namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\kategori_judul;

class ApiControllerKategori extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $response = kategori_judul::all();
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
            'kategori_nama' => 'required'
        ]);

        $kategori_nama = $request->kategori_nama;

        $kategori_judul = new kategori_judul();
        $kategori_judul->kategori_nama = $kategori_nama;
        
        if (!$kategori_judul->save()) {
            $response = [
                "msg" => "Sesuatu eror terjadi",
                "success" => false
            ];   
            return response()->json($response, 404);
        } else {
            $response = [
                "msg" => "Berhasil menambah data",
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
        
        $response = kategori_judul::find($id);
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
            'kategori_nama'        => 'required'
        ]);

        $kategori_judul = kategori_judul::find($id);
        $kategori_judul->kategori_nama = $request->kategori_nama;

        if (!$kategori_judul->save()) {
            $response = [
                "msg" => "Sesuatu eror terjadi",
                "judul" => "Tidak ada"
            ];  
            return response()->json($response, 404);
        } else {
            $response = [
                "msg" => "berhasil merubah judul",
                "judul" => $kategori_judul['kategori_nama']
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
        
        $kategori_judul = kategori_judul::find($id);
        
        if (!$kategori_judul->delete()) {

            $response = [
                "msg" => "Sesuatu eror terjadi",
                "kategori_judul" => "Tidak ada"
            ];

            return response()->json($response, 404);

        } else {

            $response = [
                "msg" => "berhasil menghapus judul",
                "kategori_judul" => $kategori_judul['kategori_nama']
            ];

            return response()->json($response, 201);
        }

    }
}
