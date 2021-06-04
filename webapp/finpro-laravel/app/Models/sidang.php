<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class sidang extends Model
{
    protected $table = "sidang";
    public $timestamps = false;
    protected $primaryKey = "sidang_id";

    protected $fillable = [
        'sidang_id', 
        'sidang_review',
        'sidang_tanggal', 
        'nilai_proposal', 
        'nilai_penguji_1', 
        'nilai_penguji_2', 
        'nilai_pembimbing', 
        'nilai_total', 
        'sidang_status',
        'proyek_akhir_id',
        ];

    public function tbl_proyek_akhir(){
        return $this->belongsTo('App\Models\proyek_akhir', 'proyek_akhir_id');
    }
}
