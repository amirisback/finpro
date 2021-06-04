<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class monev extends Model
{
    protected $table = "monev";
    public $timestamps = false;
    protected $primaryKey = "monev_id";

    protected $fillable = [
        'monev_id',
        'monev_kategori',
        'jumlah_bimbingan',
        'monev_status'        
        ];

    public function tbl_detail_monev(){
        return $this->belongsTo('App\Models\detail_monev', 'monev_id');
    }
}
