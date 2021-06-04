<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateDetailMonevTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('detail_monev', function (Blueprint $table) {

            $table->increments('monev_detail_id');        
            $table->tinyInteger('monev_nilai')->nullable();
            $table->date('monev_tanggal');
            $table->text('monev_ulasan')->nullable();
            $table->integer('monev_id')->unsigned();            
            $table->integer('proyek_akhir_id')->unsigned();                   
        });

        Schema::table('detail_monev', function($table){            
            $table->foreign('proyek_akhir_id')->references('proyek_akhir_id')->on('proyek_akhir')->onUpdate('cascade');            
            $table->foreign('monev_id')->references('monev_id')->on('monev')->onUpdate('cascade');
        });


    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('detail_monev');
    }
}
