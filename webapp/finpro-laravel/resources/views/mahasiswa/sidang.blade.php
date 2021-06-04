@extends('layout.app-mhs') @section('title') Sidang @endsection

<!-- Body -->
@section('top-bar') Sidang @endsection



<!-- Page Content Holder -->
@section('content')
<div id="content">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Nilai Sidang
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <td>Tanggal Input</td>
                                            <td>
                                            <center><b><a class=" btn-default">{{ empty($nilaiSidang->sidang_tanggal) === true ? '0' : $nilaiSidang->sidang_tanggal}}</a></b></center>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Jumlah Bimbingan</td>
                                            <td>
                                                <center><b><a class=" btn-default">{{ empty($nilaiSidang->tbl_proyek_akhir->tbl_bimbingan) === true ? '0' : $nilaiSidang->tbl_proyek_akhir->tbl_bimbingan->count()}} kali</a></b></center>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Nilai prolosal</td>
                                            <td>
                                                <center><b><a class=" btn-default">{{ empty($nilaiSidang->nilai_proposal) === true ? '0' : $nilaiSidang->nilai_proposal}}</a></b></center>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Nilai Pembimbing</td>
                                            <td>
                                            <center><b><a class=" btn-default">{{ empty($nilaiSidang->nilai_pembimbing) === true ? '0' : $nilaiSidang->nilai_pembimbing}}</a></b></center>
                                            </td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <!-- dibiarkan kosong -->
                                    </tbody>
                                </table>
                            </div>
                            <a><strong>Catatan Sidang</strong></a><br>
                            <a>{{ empty($nilaiSidang->sidang_review) === true ? 'belum ada' : $nilaiSidang->sidang_review}}</a>
                        </div>
                        <div class="col-lg-6">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <td>Nilai Penguji 1</td>
                                            <td>
                                            <center><b><a class=" btn-default">{{ empty($nilaiSidang->nilai_penguji_1) === true ? '0' : $nilaiSidang->nilai_penguji_1}}</a></b></center>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Nilai Penguji 2</td>
                                            <td>
                                            <center><b><a class=" btn-default">{{ empty($nilaiSidang->nilai_penguji_2) === true ? '0' : $nilaiSidang->nilai_penguji_2}}</a></b></center>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Nilai Total</td>
                                            <td>
                                            <center><b><a class=" btn-default">{{ empty($nilaiSidang->nilai_total) === true ? '0' : $nilaiSidang->nilai_total}}</a></b></center>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Status Sidang</td>
                                            <td>
                                            <center><b><a class=" btn-default">{{ empty($nilaiSidang->sidang_status) === true ? 'Belum Lulus' : $nilaiSidang->sidang_status}}</a></b></center>
                                            </td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <!-- dibiarkan kosong -->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</div>
@endsection