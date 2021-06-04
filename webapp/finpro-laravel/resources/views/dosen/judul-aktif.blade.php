@extends('layout.app-dosen')
    @section('title')
        Judul Proyek Akhir
    @endsection

    <!-- Body -->
    @section('top-bar')
        Data Judul Proyek Akhir
    @endsection

        <!-- Page Content Holder -->
        @section('content')
            <div id="content">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Tambahkan Judul Baru
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <form action="{{ route('dosen.judul.tambah')}}" method="post">
                                        <div class="col-lg-12">
                                        {{csrf_field()}}
                                            <div class="col-lg-8">
                                                <div class="form-group has-feedback {{ $errors->has('form_judulProyek') ? ' has-error' : '' }}">
                                                    <label>Tambah Judul</label><br>
                                                    <input class="form-control" {{ $limitBimbingan == '0' ? 'readonly="readonly"' : 'bukan 0'}} placeholder="Tambahkan Judul" name="form_judulProyek" value="{{old('form_judulProyek')}}">
                                                    @if ($errors->has('form_judulProyek'))
                                                        <span class="help-block">
                                                            <p class="text-danger">{{ $errors->first('form_judulProyek') }}</p>
                                                        </span>
                                                    @endif
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group has-feedback {{ $errors->has('form_kategoriJudul') ? ' has-error' : '' }}">
                                                    <label data-error="wrong" data-success="right" for="kategori">Kategori</label>
                                                    <select {{ $limitBimbingan == '0' ? 'readonly="readonly"' : 'bukan 0'}} name="form_kategoriJudul" class="form-control">
                                                        <option value="">--pilih--</option>
                                                        @foreach($kategori_judul  as $kategori)
                                                        <option value="{{$kategori->kategori_id}}">{{$kategori->kategori_nama}}</option>
                                                        @endforeach
                                                    </select>
                                                    @if ($errors->has('form_kategoriJudul'))
                                                        <span class="help-block">
                                                            <p class="text-danger">{{ $errors->first('form_kategoriJudul') }}</p>
                                                        </span>
                                                    @endif
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="form-group has-feedback {{ $errors->has('form_deskripsiJudul') ? ' has-error' : '' }}">
                                                    <label>Deskripsi Judul</label>
                                                    <textarea class="form-control" rows="3" {{ $limitBimbingan == '0' ? 'readonly="readonly"' : 'bukan 0'}} placeholder="Input Deskripsi" name="form_deskripsiJudul">{{old('form_judulProyek')}}</textarea>
                                                    @if ($errors->has('form_deskripsiJudul'))
                                                        <span class="help-block">
                                                            <p class="text-danger">{{ $errors->first('form_deskripsiJudul') }}</p>
                                                        </span>
                                                    @endif
                                                </div>
                                                <input type="submit" class="btn btn-primary" value="Tambahkan">
                                            </div>                                            
                                        </div>
                                    </form>
                                    @include('layout._alert')
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
                @if($judulDosenAcc->count() > 0)                
                    <h2>Pengajuan</h2>
                    <div class="line-title"></div>
                    <br>
                    <div class="lin-title"></div>
                    <table id="datatable" class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Tim</th>
                                <th scope="col">Perihal</th>
                                <th scope="col">Judul</th>
                                <th scope="col">kategori</th>
                                <th scope="col">Pesaing</th>
                                <th scope="col">Aksi</th>
                            </tr>
                        </thead>
                        <tbody>                             
                        </tbody>
                    </table>
                @endif

            <h2>Berjalan</h2>
            <div class="line-title"></div>
            <br>
            <div class="lin-title"></div>
                <table id="datatables" class="table">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Tim</th>
                            <th scope="col">Mahasiswa</th>
                            <th scope="col">Judul</th>
                            <th scope="col">Kategori</th>
                            <th scope="col">Reviewer</th>
                            <th scope="col">Status</th>
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
                ajax: "{{ route('dosen.judul.pengajuan.json') }}",
                columns:[
                    {data: 'DT_RowIndex', name:'nama_tim', searchable: false},
                    {data: 'nama_tim', name:'nama_tim'},
                    {data: 'perihal', name:'perihal'},
                    {data: 'judul', name:'judul'},
                    {data: 'kategori_judul', name:'kategori_judul'},
                    {data: 'jumlah_kelompok', name:'jumlah_kelompok'},
                    {data: 'action', name: 'action', orderable: false, searchable: false},   
                ]
            });

            $('#datatables').DataTable({
                responsive:true,
                processing:true,
                ajax: "{{ route('dosen.judul.berjalan.json') }}",
                columns:[
                    {data: 'DT_RowIndex', name:'nama_tim', searchable: false},
                    {data: 'nama_tim', name:'nama_tim'},
                    {data: 'mahasiswa', name:'mahasiswa'},
                    {data: 'judul_nama', name:'judul_nama'},
                    {data: 'kategori_judul', name:'kategori_judul'},
                    {data: 'reviewer', name:'reviewer'},
                    {data: 'status', name:'status'},
                    {data: 'action', name: 'action', orderable: false, searchable: false},  
                ]
            });
        </script>
        
    @endpush
