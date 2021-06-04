<?php

namespace App\Http\Controllers\ApiController;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\jadwal_kegiatan;


class ApiControllerKegiatan extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $response = jadwal_kegiatan::all();
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
            'kegiatan' => 'required',
            'tanggal_mulai' => 'required',
            'tanggal_berakhir' => 'required'
        ]);

        $kegiatan = $request->kegiatan;
        $tanggal_mulai = $request->tanggal_mulai;
        $tanggal_berakhir = $request->tanggal_berakhir;
        $pelaku = $request->pelaku;
        $keterangan = $request->keterangan;

        $jadwal_kegiatan = new jadwal_kegiatan();
        $jadwal_kegiatan->kegiatan = $kegiatan;
        $jadwal_kegiatan->tanggal_mulai = $tanggal_mulai;
        $jadwal_kegiatan->tanggal_berakhir = $tanggal_berakhir;
        $jadwal_kegiatan->pelaku = $pelaku;
        $jadwal_kegiatan->keterangan = $keterangan;

        if (!$jadwal_kegiatan->save()) {
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
        $response = jadwal_kegiatan::find($id);
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
        
        $jadwal_kegiatan = jadwal_kegiatan::find($id);

        $kegiatan = $request->kegiatan;
        $tanggal_mulai = $request->tanggal_mulai;
        $tanggal_berakhir = $request->tanggal_berakhir;
        $pelaku = $request->pelaku;
        $keterangan = $request->keterangan;

        $jadwal_kegiatan->kegiatan = $kegiatan;
        $jadwal_kegiatan->tanggal_mulai = $tanggal_mulai;
        $jadwal_kegiatan->tanggal_berakhir = $tanggal_berakhir;
        $jadwal_kegiatan->pelaku = $pelaku;
        $jadwal_kegiatan->keterangan = $keterangan;
      
        if (!$jadwal_kegiatan->save()) {
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
        
        $jadwal_kegiatan = jadwal_kegiatan::find($id);

        if (!$jadwal_kegiatan->delete()) {
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
}
