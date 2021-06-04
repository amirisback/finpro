@extends('layout.app')
    @section('title')
    Beranda
    @endsection

        <!-- Body -->
        @section('top-bar')
            Beranda
        @endsection

        <!-- Page Content Holder -->
        @section('content')
            <div id="content">
                <!-- Body Kotak Deskripsi -->
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-group fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">{{ $jmlMahasiswa }}</div>
                                        <div>Mahasiswa Aktif</div>
                                    </div>
                                </div>
                            </div>
                            <a href="{{ route('koordinator.pengguna.mahasiswa')}}">
                                <div class="panel-footer">
                                    <span class="pull-left">Lihat Detail</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                    <i class="fa fa-address-book fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">{{ $jmlDosen }}</div>
                                        <div>Dosen</div>
                                    </div>
                                </div>
                            </div>
                            <a href="{{ route('koordinator.pengguna.pembimbing')}}">
                                <div class="panel-footer">
                                    <span class="pull-left">Lihat Detail</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-book fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">{{ $jmlJudul }}</div>
                                        <div>Judul Tersedia</div>
                                    </div>
                                </div>
                            </div>
                            <a href="{{ route('koordinator.judul.aktif')}}">
                                <div class="panel-footer">
                                    <span class="pull-left">Lihat Detail</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon-file fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">{{$jmlMahasiswaJudulKosong}}</div>
                                        <div>Mhs tanpa Judul</div>
                                    </div>
                                </div>
                            </div>
                            <a class="btn-show" href="{{ route('koordinator.mahasiswa.tanpa-judul')}}"  title="Daftar Mahasiswa Belum Punya Judul">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <!-- Body Kotak Deskripsi -->

                <!-- Bimbingan -->
                <h2>Bimbingan</h2>
                <div class="line-title"></div><br>
                <table id="datatable" class="table table-hover" style="width:100%">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Nama</th>
                            <th scope="col">Nama Tim</th>
                            <th scope="col">Pembimbing</th>
                            <th scope="col">Jumlah Bimbingan</th>
                            <th scope="col">Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <br>

                <!-- Akhir Bimbingan -->

                <!-- Monev -->
                <br>
                <h2>Nilai Monev Bimbingan</h2>
                <div class="line-title"></div>
                <table id="datatable-monev" class="table table-hover" style="width:100%">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Nama</th>
                            <th scope="col">Nama Tim</th>
                            <th scope="col">Reviewer</th>
                            <th scope="col">Jumlah Monev</th>
                            <th scope="col">Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <!-- Akhir Monev -->
            </div>
            @include('layout._modal')
        @endsection
        @push('scripts')
<script>
    $('#datatable').DataTable({
        responsive:true,
        processing:true,
        ajax: "{{ route('koordinator.beranda.bimbingan.json') }}",
        columns:[
            {data: 'DT_RowIndex', name:'mahasiswa', searchable: false},
            {data: 'mahasiswa', name:'mahasiswa'},
            {data: 'nama_tim', name:'nama_tim'},
            {data: 'pembimbing', name:'pembimbing'},
            {data: 'jumlah_bimbingan', name:'jumlah_bimbingan'},
            {data: 'action', name: 'action', orderable: false, searchable: false},   
        ]
    });

    $('#datatable-monev').DataTable({
        responsive:true,
        processing:true,
        ajax: "{{ route('koordinator.beranda.monev.json') }}",
        columns:[
            {data: 'DT_RowIndex', name:'mhs_nim', searchable: false},
            {data: 'nama_mahasiswa', name:'nama_mahasiswa'},
            {data: 'nama_tim', name:'nama_tim'},
            {data: 'reviewer', name:'reviewer'},
            {data: 'jumlah_monev', name:'jumlah_monev'},
            {data: 'action', name: 'action', orderable: false, searchable: false},   
        ]
    });
</script>

@endpush


