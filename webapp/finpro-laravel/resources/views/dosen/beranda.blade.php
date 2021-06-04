@extends('layout.app-dosen')
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
                    <div class="col-lg-4 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-group fa-5x" ></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">{{$jmlMahasiswa}}</div>
                                        <div>Mahasiswa Bimbingan</div>
                                    </div>
                                </div>
                            </div>
                            <a href="{{ route('dosen.bimbingan')}}">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                    <i class="fa fa-book fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">{{$jmlJudul}}</div>
                                        <div>Judul Proyek Akhir</div>
                                    </div>
                                </div>
                            </div>
                            <a href="{{ route('dosen.judul.aktif')}}">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-edit fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">{{$jmlReviewer}}</div>
                                        <div>Judul Reviewer</div>
                                    </div>
                                </div>
                            </div>
                            <a href="{{ route('dosen.monev')}}">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>


                <!--- awal tabel ---->
                <h2>Bimbingan</h2>
                <div class="line-title"></div>
                <br>    
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
                <!---Monev--->  
                <br>
                <h2>Monev</h2>
                <div class="line-title"></div>
                <br>    
                <table id="datatable2" class="table table-hover" style="width:100%">
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
            </div>
        @include('layout._modal')
        @endsection
        @push('scripts')
            <script>
                $('#datatable').DataTable({
                    responsive:true,
                    processing:true,
                    //serverSide:true,
                    ajax: "{{ route('dosen.beranda.bimbingan.json') }}",
                    columns:[
                        //{ "defaultContent": "" },
                        {data: 'DT_RowIndex', name:'mahasiswa', searchable: false},
                        {data: 'mahasiswa', name:'mahasiswa'},
                        {data: 'nama_tim', name:'nama_tim'},
                        {data: 'pembimbing', name:'pembimbing'},
                        {data: 'jumlah_bimbingan', name:'jumlah_bimbingan'},
                        {data: 'action', name: 'action', orderable: false, searchable: false},   
                        
                    ]
                });

                $('#datatable2').DataTable({
                    responsive:true,
                    processing:true,
                    //serverSide:true,
                    ajax: "{{ route('dosen.beranda.monev.json') }}",
                    columns:[
                        //{ "defaultContent": "" },
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
        
  



