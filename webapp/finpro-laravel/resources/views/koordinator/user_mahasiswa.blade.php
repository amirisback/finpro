@extends('layout.app')
    @section('title')
        Pengguna Mahasiswa
    @endsection

    <!-- body -->
    @section('top-bar')
        Data Mahasiswa
    @endsection

    <!-- Page Content Holder -->
    @section('content')
        <div id="content">
            <nav class="navbar navbar-default">

                <div class="container-fluid">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Tambahkan Data Mahasiswa
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            <form action="{{route('koordinator.mahasiswa.tambah')}}" method="POST">
                            {{ csrf_field() }}
                                <div class="col-lg-5">
                                    <div class="form-group has-feedback {{ $errors->has('input_nama_mhs') ? ' has-error' : '' }}">
                                        <label>Nama Mahasiswa</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                            <input class="form-control" placeholder="Nama Mahasiswa" name="input_nama_mhs"  value="{{ old('input_nama_mhs') }}">
                                        </div>
                                        @if ($errors->has('input_nama_mhs'))
                                            <span class="help-block">
                                                <p class="text-danger">{{ $errors->first('input_nama_mhs') }}</p>
                                            </span>
                                        @endif
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                    <div class="form-group has-feedback {{ $errors->has('input_nim') ? ' has-error' : '' }}">
                                        <label>NIM</label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-vcard" aria-hidden="true"></i></span>
                                            <input class="form-control" placeholder="Nim Mahasiswa" name="input_nim" value="{{ old('input_nim')}}">
                                        </div>
                                        @if ($errors->has('input_nim'))
                                            <span class="help-block">
                                                <p class="text-danger">{{ $errors->first('input_nim') }}</p>
                                            </span>
                                        @endif
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                    <br>
                                    <p class="filexls">*Mahasiswa Baru secara default login dengan NIM untuk username dan password</p>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <button type="submit" class="btn btn-primary ">Tambah Mahasiswa</button>
                                    <a class="btn btn-success " data-toggle="modal" data-target="#import-mahasiswa">Import file excel</a>                                       
                                </div>
                            
                            </form>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        @include('layout._alert')
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Form Elements -->
                </div>
            </nav>
            <h2>Mahasiswa Aktif</h2>
            <div class="line-title"></div>
            <br>
            <table id="datatable" class="table table-hover" style="width:100%">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Nama Mahasiswa</th>
                        <th scope="col">Nim</th>
                        <th scope="col">Angkatan</th>
                        <th scope="col">Judul PA</th>
                        <th scope="col">Pembimbing</th>
                        <th scope="col">Status</th>
                        <th scope="col">Aksi</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div> 
        @include('koordinator._sub-menu.mahasiswa._modal_upload_excel')
        @include('layout._modal')

    @endsection

    @push('scripts')
        <script>
            $('#datatable').DataTable({
                responsive:true,
                processing:true,
                //serverSide:true,
                ajax: "{{ route('koordinator.mahasiswa.json') }}",
                columns:[
                    //{ "defaultContent": "" },
                    {data: 'DT_RowIndex', name:'mhs_nama', searchable: false},
                    {data: 'mhs_nama', name:'mhs_nama'},
                    {data: 'mhs_nim', name:'mhs_nim'},
                    {data: 'angkatan', name:'angkatan'},
                    {data: 'judul', name:'judul'},
                    {data: 'pembimbing', name:'pembimbing'},
                    {data: 'status', name:'status'},
                    {data: 'action', name: 'action', orderable: false, searchable: false},   
                ]
            });
        </script>
    @endpush
