<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class notifikasi extends Model
{
    protected $table = "notifikasi";
    public $timestamps = false;
    protected $primaryKey = "notifikasi_id";

    
    protected $fillable = [
        'notifikasi_id', 
        'notifikasi_tanggal',
        'notifikasi_kategori', 
        'notifikasi_deskripsi', 
        'notifikasi_dari',
        'notifikasi_untuk',
        'notifikasi_baca',
        ];
  
}
