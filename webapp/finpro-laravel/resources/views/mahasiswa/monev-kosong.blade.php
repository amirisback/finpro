@extends('layout.app-mhs') @section('title') Monev @endsection
<!-- Body -->
@section('top-bar') Data Monev @endsection

<!-- Page Content Holder -->
@section('content')
<div id="content">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Form Elements -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    Monev
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-4">

                            <label>Judul Proyek Akhir</label><br>
                            <p>Sistem Manajemen Proyek Akhir</p>
                            <label>Dosen Pembimbing</label><br>
                            <p>HMU</p>
                            <label>Dosen Reviewer</label><br>
                            <p>IZM</p>

                        </div>
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-6">

                            <div class="form-group">
                                <label>Nama Kelompok</label><br>
                                <p>BEKAA TIM</p>
                                <label>Tim Kelompok</label><br>
                                <p>1. Rivkal Sukma Sanjaya <br> 2. Bryan Rafsanzani
                                </p>


                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </nav>
    <h2>Nilai Monev</h2>
    <div class="line-title"></div>
    <br>
    <!-- <div class="panel panel-default"> -->
    <table class="table">
        <thead class="thead-light">
            <tr>
                <th scope="col">No</th>
                <th scope="col">Tanggal</th>
                <th scope="col">Monev</th>
                <th scope="col">Catatan</th>
                <th scope="col">Nilai</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">1</th>
                <td>6 oktober 2018 </td>
                <td>Pra-Monev 1</td>
                <td>Sudah baik dan lengkap</td>
                <td>78</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>6 November 2018 </td>
                <td>Pra-Monev 2</td>
                <td>Suumber artikel masih belum valid </td>
                <td>Belum Diinput</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>11 Desember 2018 </td>
                <td>Pra-Monev 3</td>
                <td>Suumber artikel masih belum valid </td>
                <td>50</td>
                <tr>
        </tbody>
    </table>
</div>
<!-- </div> -->
@endsection