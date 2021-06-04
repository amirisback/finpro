<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateKoordinatorPaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('koordinator_pa', function (Blueprint $table) {
            $table->string('koor_nip', 15)->primary();            
            $table->string('koor_nama', 64);
            $table->string('koor_kode', 4);
            $table->string('koor_kontak', 12)->nullable()->unique();
            $table->text('koor_foto');
            $table->string('koor_email', 64)->nullable()->unique();
            $table->string('username', 32);
        });

        Schema::table('koordinator_pa', function($table){
            $table->foreign('username')->references('username')->on('user')->onUpdate('cascade')->onDelete('cascade');            
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('koordinator_pa');
    }
}
