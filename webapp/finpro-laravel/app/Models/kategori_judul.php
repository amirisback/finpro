<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class kategori_judul extends Model
{
    protected $table = "kategori_judul";
    public $timestamps = false;
    protected $primaryKey = "kategori_id";

    protected $fillable = [
        'kategori_id',
        'kategori_nama',
        'kategori_status',
        ];

    public function tbl_judul(){
        return $this->hasMany('App\Models\judul', 'judul_id');
    }
}
