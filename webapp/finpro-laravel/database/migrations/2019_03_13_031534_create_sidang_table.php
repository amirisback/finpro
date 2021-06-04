<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateSidangTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('sidang', function (Blueprint $table) {
            $table->increments('sidang_id');
            $table->text('sidang_review')->nullable();
            $table->date('sidang_tanggal');
            $table->tinyInteger('nilai_proposal')->nullable();
            $table->tinyInteger('nilai_penguji_1')->nullable();
            $table->tinyInteger('nilai_penguji_2')->nullable();
            $table->tinyInteger('nilai_pembimbing')->nullable();
            $table->double('nilai_total')->nullable();
            $table->string('sidang_status', 15)->nullable();
            $table->integer('proyek_akhir_id')->unsigned();
        });

        Schema::table('sidang', function($table){            
            $table->foreign('proyek_akhir_id')->references('proyek_akhir_id')->on('proyek_akhir')->onUpdate('cascade');            
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('sidang');
    }
}

