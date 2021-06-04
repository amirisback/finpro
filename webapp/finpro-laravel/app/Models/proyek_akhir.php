<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class proyek_akhir extends Model
{
    protected $table = "proyek_akhir";
    public $timestamps = false;
    protected $primaryKey = "proyek_akhir_id";

    protected $fillable = [
        'proyek_akhir_id', 
        'nilai_total',
        'nama_tim', 
        'judul_id', 
        'mhs_nim',
        'dsn_nip',
        ];

    public function tbl_sidang(){
        return $this->hasOne('App\Models\sidang', 'proyek_akhir_id');
    }

    public function tbl_detail_monev(){
        return $this->hasMany('App\Models\detail_monev', 'proyek_akhir_id');
    }

    public function tbl_bimbingan(){
        return $this->hasMany('App\Models\bimbingan', 'proyek_akhir_id');
    }

    public function tbl_judul()
    {
        return $this->belongsTo('App\Models\judul', 'judul_id');
    }

    public function tbl_mahasiswa()
    {
        return $this->belongsTo('App\Models\mahasiswa', 'mhs_nim');
    }

    public function tbl_dosen()
    {
        return $this->belongsTo('App\Models\dosen', 'dsn_nip');
    }
}
