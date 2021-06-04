<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateNotifikasiTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('notifikasi', function (Blueprint $table) {
            $table->increments('notifikasi_id');
            $table->string('notifikasi_dari', 64);
            $table->string('notifikasi_untuk', 64);
            $table->text('notifikasi_kategori');
            $table->text('notifikasi_deskripsi');
            $table->boolean('notifikasi_baca')->default(0);
            $table->timestamp('notifikasi_tanggal');
        });
    }



    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('notifikasi');
    }
}
