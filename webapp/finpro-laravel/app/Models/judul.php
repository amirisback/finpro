<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class judul extends Model
{
    protected $table = "judul";
    public $timestamps = false;
    protected $primaryKey = "judul_id";

    protected $fillable = [
        'judul_id', 
        'judul_nama', 
        'judul_deskripsi', 
        'judul_tahun', 
        'judul_status',
        'judul_waktu',
        'judul_abstrak',
        'judul_dokumen',        
        'dsn_nip',
        'kategori_id',
        ];


        
    public function tbl_mahasiswa(){
        return $this->hasMany('App\Models\mahasiswa', 'judul_id');
    }

    public function tbl_proyek_akhir(){
        return $this->hasMany('App\Models\proyek_akhir', 'judul_id');
    }

    public function tbl_dosen()
    {
        return $this->belongsTo('App\Models\dosen', 'dsn_nip');
    }

    public function tbl_kategori_judul()
    {
        return $this->belongsTo('App\Models\kategori_judul', 'kategori_id');
    }

}
