<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateMahasiswaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('mahasiswa', function (Blueprint $table) {
            $table->string('mhs_nim', 15)->primary();
            $table->string('mhs_nama', 64);
            $table->string('angkatan', 4);
            $table->string('mhs_kontak', 12)->nullable()->unique();
            $table->text('mhs_foto');
            $table->string('mhs_email', 64)->nullable()->unique();
            $table->string('status', 10)->nullable();
            $table->integer('judul_id')->unsigned()->nullable();
            $table->string('username', 32);
        });

        Schema::table('mahasiswa', function($table){
            $table->foreign('username')->references('username')->on('user')->onUpdate('cascade')->onDelete('cascade');
            $table->foreign('judul_id')->references('judul_id')->on('judul')->onUpdate('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('mahasiswas');
    }
}
