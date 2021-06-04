<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateJudulTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('judul', function (Blueprint $table) {
            $table->increments('judul_id');
            $table->text('judul_nama');
            $table->text('judul_deskripsi');
            $table->string('judul_status', 10);
            $table->timestamp('judul_waktu');   
            $table->text('judul_abstrak')->nullable();
            $table->text('judul_dokumen')->nullable();
            $table->text('dsn_foto');
            $table->string('dsn_nip', 15);
            $table->integer('kategori_id')->unsigned()->nullable(); 
        });

        Schema::table('judul', function($table){
            $table->foreign('kategori_id')->references('kategori_id')->on('kategori_judul')->onUpdate('cascade');
            $table->foreign('dsn_nip', 15)->references('dsn_nip')->on('dosen')->onUpdate('cascade');            
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('judul');
    }
}
