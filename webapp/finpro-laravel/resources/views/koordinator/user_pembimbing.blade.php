@extends('layout.app')
    @section('title')
        Pengguna Pembimbing
    @endsection

    <!-- body -->
    @section('top-bar')
        Data Pembimbing
    @endsection

    @section('content')
    <!-- Page Content Holder -->
        <div id="content">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Tambahkan Data Pembimbing
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <form action="{{ route('koordinator.pembimbing.tambah') }}" method="POST">
                                {{ csrf_field() }}
                                    <div class="col-lg-5">
                                        <div class="form-group has-feedback {{ $errors->has('nama_dosen') ? ' has-error' : '' }}">
                                            <label>Nama Dosen </label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-address-book" aria-hidden="true"></i></span>
                                                <input class="form-control" placeholder="Input Nama dosen" name="nama_dosen" value="{{ old('nama_dosen')}}">
                                            </div>
                                            @if ($errors->has('nama_dosen'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('nama_dosen') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="col-lg-5">
                                        <div class="form-group has-feedback {{ $errors->has('nip_dosen') ? ' has-error' : '' }}">
                                            <label>NIP</label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-vcard" aria-hidden="true"></i></span>
                                                <input class="form-control" placeholder="Input Nip Dosen" name="nip_dosen" value="{{ old('nip_dosen')}}">
                                            </div>
                                            @if ($errors->has('nip_dosen'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('nip_dosen') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    
                                    <div class="col-lg-2">
                                        <div class="form-group has-feedback {{ $errors->has('kode_dosen') ? ' has-error' : '' }}">
                                            <label>Kode Dosen</label>
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-info-circle" aria-hidden="true"></i></span>
                                                <input class="form-control" placeholder="Kode Dosen" name="kode_dosen" value="{{ old('kode_dosen')}}">
                                            </div>
                                            @if ($errors->has('kode_dosen'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('kode_dosen') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary">Tambah Dosen</button>
                                        </div>
                                    </div>
                                    <div class="col-lg-11">
                                        <p>*Note : untuk login akun baru, username dan password menggunakan Nip </p>
                                    </div>
                                </form> 
                                @include('layout._alert')
                            </div>
                        </div>
                    </div>
                    <!-- End Form Elements -->

                </div>
            </nav>
            <h2>Pembimbing Aktif</h2>
            <div class="line-title"></div>
            <br>
                <table id="datatable" class="table table-hover" style="width:100%">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">No</th>                        
                        <th scope="col">Nama Dosen</th>
                        <th scope="col">NIP</th>
                        <th scope="col">Kode Dosen</th>
                        <th scope="col">Jml. Judul Bimbingan</th>
                        <th scope="col">Jml. Judul Tersedia</th>
                        <th scope="col">Jml. Max Tim Bimbingan</th>
                        <th scope="col">Jml. Max Tim Review</th>
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
                ajax: "{{ route('koordinator.pembimbing.json') }}",
                columns:[
                    //{ "defaultContent": "" },
                    {data: 'DT_RowIndex', name:'dsn_nama', searchable: false},                    
                    {data: 'dsn_nama', name:'dsn_nama'},
                    {data: 'dsn_nip', name:'dsn_nip'},
                    {data: 'dsn_kode', name:'dsn_kode'},
                    {data: 'jml_judul_bimbingan', name:'jml_judul_bimbingan'},
                    {data: 'jml_judul_tersedia', name:'jml_judul_tersedia'},
                    {data: 'jml_bimbingan', name:'jml_bimbingan'},
                    {data: 'jml_reviewer', name:'jml_reviewer'},
                    {data: 'action', name: 'action', orderable: false, searchable: false},   
                ]
            });
        </script>
    @endpush
    





  
  