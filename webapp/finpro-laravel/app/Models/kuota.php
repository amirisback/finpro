<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class kuota extends Model
{
    protected $table = "kuota";
    public $timestamps = false;
    protected $primaryKey = "kuota_id";


    protected $fillable = [
        'kuota_id', 
        'kuota_variable',
        'kuota_value',
        ];
        
}
