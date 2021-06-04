<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateProyekAkhirTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('proyek_akhir', function (Blueprint $table) {
            $table->increments('proyek_akhir_id');
            $table->double('nilai_total')->nullable();
            $table->string('nama_tim', 15)->nullable();
            $table->integer('judul_id')->unsigned()->nullable();
            $table->string('mhs_nim', 15);
            $table->string('dsn_nip', 15)->nullable();
        });

        Schema::table('proyek_akhir', function($table){            
            $table->foreign('judul_id')->references('judul_id')->on('judul')->onUpdate('cascade')->onDelete('cascade');
            $table->foreign('mhs_nim')->references('mhs_nim')->on('mahasiswa')->onUpdate('cascade');
            $table->foreign('dsn_nip')->references('dsn_nip')->on('dosen')->onUpdate('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('proyek_akhir');
    }
}
