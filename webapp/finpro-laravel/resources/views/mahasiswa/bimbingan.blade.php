@extends('layout.app-mhs')
    @section('title')
        Bimbingan
    @endsection

    <!-- Body -->
        @section('top-bar')
            Data Bimbingan
        @endsection

        <!-- Page Content Holder -->
        @section('content')
            <div id="content">
            <nav class="navbar navbar-default">

                <div class="container-fluid">
                        <!-- Form Elements -->
                        <div class="panel panel-default">
                        <div class="panel-heading">
                    Tambahkan Bimbingan
                </div>
                <div class="panel-body">
                    <div class="row">
                            <form action="{{ route('mahasiswa.bimbingan.tambah')}}" method="POST">
                            {{ csrf_field() }}
                            <div class="col-lg-6">
                                <table class="table">
                                    <thead class="thead-light">
                                        <tr>
                                            <th scope="col">No</th>
                                            <th scope="col">Nama</th>
                                            <th scope="col">Kehadiran</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    @foreach($mahasiswa as $index => $mhs)
                                        <tr>
                                            <th scope="row">{{$index+1}}</th>
                                            <td>{{$mhs->tbl_mahasiswa->mhs_nama}} </td>
                                            <td>
                                                <select name="formKehadiran[{{ $mhs->proyek_akhir_id }}]" class="form-control" id="kehadiran" >
                                                    <option value="hadir">Hadir</option>
                                                    <option value="ijin">Sakit</option>
                                                    <option value="sakit">Tidak Hadir</option>
                                                </select>
                                            </td>
                                        </tr>
                                    @endforeach
                                    </tbody>
                                </table>
                            </div>
                            
                            <div class="col-lg-6">
                                <div class="col-lg-5">
                                    <div class="form-group has-feedback {{ $errors->has('form_bimbinganTanggal') ? ' has-error' : '' }}">
                                        <label>Tanggal Bimbingan</label>
                                        <input type="date" name="form_bimbinganTanggal" class="form-control">
                                        @if ($errors->has('form_bimbinganTanggal'))
                                            <div class="invalid-feedback">
                                            <p class="text-danger">{{ $errors->first('form_bimbinganTanggal')}}</p>
                                            </div>
                                        @endif
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group has-feedback {{ $errors->has('form_bimbinganTopik') ? ' has-error' : '' }}">
                                        <label>Topik Bimbingan Bimbingan</label>
                                        <textarea class="form-control" rows="3" name="form_bimbinganTopik" placeholder="Masukan Keterangan Bimbingan" >{{ old('form_bimbinganTopik') }}</textarea>
                                        @if ($errors->has('form_bimbinganTopik'))
                                            <div class="invalid-feedback">
                                            <p class="text-danger">{{ $errors->first('form_bimbinganTopik')}}</p>
                                            </div>
                                        @endif
                                    </div>
                                </div>

                                <div class="offset-7 col-lg-12">
                                <input type="submit" class="btn btn-primary " value="Tambahkan Bimbingan">
                                </div>
                            </div>
                            <div class="col-lg-12">
                                @include('layout._alert')
                            </div>   
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <h2>Bimbingan</h2>
                <div class="line-title"></div>
                 <br>
                 <table id="datatable" class="table table-hover" style="width:100%">
                     <thead class="thead-light">
                     <tr>
                      <th scope="col">No</th>
                        <th scope="col">Bimbingan</th>
                        <th scope="col">Keterangan Bimbingan</th>
                        <th scope="col">Kehadiran</th>
                        <th scope="col">Tanggal</th>
                        <th scope="col">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                    @forelse($dataBimbingan as $index => $bimbingan)
                        <tr>
                            <td>{{ $index+1 }}</td>
                            <td>bimbingan ke-{{ $index+1}}</td>
                            <td>{{ $bimbingan->bimbingan_review}}</td>
                            <td>{{ $bimbingan->bimbingan_kehadiran}}</td>
                            <td>{{ $bimbingan->bimbingan_tanggal}}</td>
                            <td>
                                @if($bimbingan->bimbingan_status === "disetujui")
                                <p  class="btn btn-success btn-xs" >{{ $bimbingan->bimbingan_status}}</p>
                                @else
                                <p  class="btn btn-warning btn-xs" >{{ $bimbingan->bimbingan_status}}</p>
                                @endif
                            
                            </td>
                        </tr>
                    @empty
                        <tr>
                            <td>Kosong</td>
                        </tr>
                    @endforelse
                    </tbody>
                </table>
     
            </div> -->
        @endsection