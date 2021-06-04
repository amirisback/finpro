<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class jadwal_kegiatan extends Model
{
    protected $table = "jadwal_kegiatan";
    public $timestamps = false;
    protected $primaryKey = "kegiatan_id";


    protected $fillable = [
        'kegiatan_id', 
        'kegiatan',
        'tanggal_mulai', 
        'tanggal_berakhir', 
        'pelaku', 
        'keterangan',
        ];
}
