<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class informasi extends Model
{
    protected $table = "informasi";
    public $timestamps = false;
    protected $primaryKey = "informasi_id";


    protected $fillable = [
        'informasi_id', 
        'informasi_judul',
        'informasi_isi', 
        'penerbit', 
        'informasi_waktu',        
        ];    
}
