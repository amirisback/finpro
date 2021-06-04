<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\User;
class koordinator_pa extends Model
{
    protected $table = "koordinator_pa";
    public $timestamps = false;
    protected $primaryKey = "koor_nip";

    protected $fillable = [
        'koor_nip', 
        'koor_nama',
        'koor_kode',
        'koor_kontak', 
        'koor_foto', 
        'koor_email',
        'username',
        ];

    public function tbl_user()
    {
        return $this->belongsTo('App\Models\user', 'username');
    }
    
}

