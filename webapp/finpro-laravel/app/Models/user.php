<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class user extends Model
{
    protected $table = "user";
    public $timestamps = false;
    protected $primaryKey = "username";
    protected $keyType = 'string';


    protected $fillable = [
        'username', 
        'password',
        'pengguna', 
        ];

    protected $hidden = [
        'password',
    ];


    public function tbl_koordinator()
    {
        return $this->hasOne('App\Models\koordinator_pa', 'username');
    }

    public function tbl_dosen()
    {
        return $this->hasOne('App\Models\dosen', 'username');
    }

    public function tbl_mahasiswa()
    {
        return $this->hasOne('App\Models\mahasiswa', 'username');
    }

}
