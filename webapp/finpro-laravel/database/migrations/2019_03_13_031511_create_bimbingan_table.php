<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateBimbinganTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('bimbingan', function (Blueprint $table) {
            $table->increments('bimbingan_id');
            $table->text('bimbingan_review')->nullable();
            $table->string('bimbingan_kehadiran', 5);
            $table->date('bimbingan_tanggal');
            $table->string('bimbingan_status', 12);
            $table->integer('proyek_akhir_id')->unsigned();            
        });

        Schema::table('bimbingan', function($table){            
            $table->foreign('proyek_akhir_id')->references('proyek_akhir_id')->on('proyek_akhir');            
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('bimbingan');
    }
}
