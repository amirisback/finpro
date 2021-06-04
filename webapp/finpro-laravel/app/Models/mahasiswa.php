<?php

namespace App\Models;
use App\Models\Judul;

use Illuminate\Database\Eloquent\Model;

class mahasiswa extends Model
{
    protected $table = "mahasiswa";
    public $timestamps = false;
    protected $keyType = 'string';
    protected $primaryKey = "mhs_nim";

    protected $fillable = [
        'mhs_nim', 
        'mhs_nama',
        'angkatan',
        'mhs_kontak', 
        'mhs_foto', 
        'mhs_email', 
        'status',
        'judul_id',
        'username',
        ];

    public function tbl_proyek_akhir()
    {
        return $this->hasMany('App\Models\proyek_akhir', 'mhs_nim');
    }

    public function tbl_judul()
    {
        return $this->belongsTo('App\Models\judul', 'judul_id');
    }

    public function tbl_user()
    {
        return $this->hasOne('App\Models\user', 'username');
    }
}
