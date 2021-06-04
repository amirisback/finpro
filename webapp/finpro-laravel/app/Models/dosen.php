<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class dosen extends Model
{
    protected $table = "dosen";
    public $timestamps = false;
    protected $keyType = 'string';
    protected $primaryKey = "dsn_nip";


    protected $fillable = [
        'dsn_nip', 
        'dsn_nama',
        'dsn_kode', 
        'dsn_kontak', 
        'dsn_foto',
        'batas_bimbingan',
        'batas_reviewer',
        'dsn_email',
        'username',
        ];

    public function tbl_judul(){
        return $this->hasMany('App\Models\judul', 'dsn_nip');
    }

    public function tbl_proyek_akhir(){
        return $this->hasMany('App\Models\proyek_akhir', 'dsn_nip');
    }

    public function tbl_user()
    {
        return $this->hasOne('App\Models\user', 'username');
    }
}

