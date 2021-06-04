<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class detail_monev extends Model
{
    protected $table = "detail_monev";
    public $timestamps = false;
    protected $primaryKey = "monev_detail_id";


    protected $fillable = [
        'monev_detail_id', 
        'monev_nilai', 
        'monev_tanggal',
        'monev_ulasan', 
        'monev_id',
        'proyek_akhir_id',
        ];

        public function tbl_monev()
        {
            return $this->belongsTo('App\Models\monev', 'monev_id');
        }
    
        public function tbl_proyek_akhir()
        {
            return $this->belongsTo('App\Models\proyek_akhir', 'proyek_akhir_id');
        }
}
