<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class bimbingan extends Model
{
    protected $table = "bimbingan";
    public $timestamps = false;
    protected $primaryKey = "bimbingan_id";


    protected $fillable = [
        'bimbingan_id', 
        'bimbingan_review',
        'bimbingan_kehadiran', 
        'bimbingan_tanggal', 
        'bimbingan_status', 
        'proyek_akhir_id',
        ];

    public function tbl_proyek_akhir(){
        return $this->belongsTo('App\Models\proyek_akhir', 'proyek_akhir_id');
    }
}
