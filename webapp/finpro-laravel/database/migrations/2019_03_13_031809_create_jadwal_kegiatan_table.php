<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateJadwalKegiatanTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('jadwal_kegiatan', function (Blueprint $table) {
            $table->increments('kegiatan_id');
            $table->text('kegiatan');
            $table->timestamp('tanggal_mulai');
            $table->timestamp('tanggal_berakhir');
            $table->string('pelaku', 25);
            $table->text('keterangan');
        });
    }



    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('jadwal_kegiatan');
    }
}
