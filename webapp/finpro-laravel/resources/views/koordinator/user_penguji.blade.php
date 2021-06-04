@extends('layout.app')
    @section('title')
        Pengguna Penguji
    @endsection

    <!-- body -->
    @section('top-bar')
        Data Penguji
    @endsection

    <!-- Page Content Holder -->
    @section('content')
        <div id="content">
            <nav class="navbar navbar-default">

                <div class="container-fluid">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Pembagian Penguji
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form">
                                        <div class="form-group">
                                            <label>Dosen Penguji 1</label><br>
                                            <select name="my_html_select_box">
                                                <option>Hariandi Maulid M.Sc </option>
                                                <option>Rizza Indah Mega M S.Kom,M.T</option>
                                                <option>Fat'hah Noor Prawita S.T.,M.T</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Dosen Penguji 2</label><br>
                                            <select name="my_html_select_box">
                                                <option>Hariandi Maulid M.Sc </option>
                                                <option>Rizza Indah Mega M S.Kom,M.T</option>
                                                <option>Fat'hah Noor Prawita S.T.,M.T</option>
                                            </select>
                                        </div>
                                </div>
                                <div class="col-lg-6">
                                    <form role="form">
                                        <div class="form-group">
                                            <label>Pilih Judul</label>
                                            <select name="my_html_select_box">
                                                <option>Pembuatan Aplikasi Android Edukasi Kanak-kanak </option>
                                                <option>Pembuatan Aplikasi Budaya Indonesia Berbasis Android</option>
                                                <option>Pembuatan Aplikasi Sensor IOT untuk pengendara Motor </option>

                                            </select>

                                        </div>
                                    </form>
                                    <button type="submit" class="btn btn-success">Tambahkan</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Form Elements -->



                </div>
            </nav>
            <div>
                <table class="table">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Nama Dosen</th>
                            <th scope="col">Judul Proyek Akhir</th>
                            <th scope="col">Aksi</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Hariandi Maulid M.Sc</td>
                            <td>Pembuatan Aplikasi Android Edukasi Kanak-kanak</td>
                            <td>
                                <button type="submit" class="btn btn-danger btn-xs">hapus</button>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Rizza Indah Mega M S.Kom,M.T</td>
                            <td>Pembuatan Aplikasi Android Edukasi Kanak-kanak</td>
                            <td>
                                <button type="submit" class="btn btn-danger btn-xs">hapus</button>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Fat'hah Noor Prawita S.T.,M.T</td>
                            <td>Pembuatan Aplikasi Sensor IOT untuk pengendara Motor</td>
                            <td>
                                <button type="submit" class="btn btn-danger btn-xs">hapus</button>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">4</th>
                            <td>Rizza Indah Mega M S.Kom,M.T</td>
                            <td>Pembuatan Aplikasi Sensor IOT untuk pengendara Motor</td>
                            <td>
                                <button type="submit" class="btn btn-danger btn-xs">hapus</button>
                            </td>
                        </tr>
                    </tbody>
                </table>



            </div>
        </div>
    @endsection