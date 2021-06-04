<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateDosenTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('dosen', function (Blueprint $table) {
            $table->string('dsn_nip', 15)->primary();
            $table->string('dsn_nama', 64);
            $table->string('dsn_kode', 4);
            $table->string('dsn_kontak', 12)->nullable()->unique();
            $table->text('dsn_foto');
            $table->tinyInteger('dsn_batas')->default(5);
            $table->string('dsn_email', 64)->nullable()->unique();
            $table->string('username', 32);
        });

        Schema::table('dosen', function($table){
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
        Schema::dropIfExists('dosen');
    }
}
