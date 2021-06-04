@extends('layout.app')
    @section('title')
        Judul Proyek Akhir
    @endsection
    <!-- body -->
        @section('top-bar')
            Judul Proyek Akhir
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
                                <form action="{{route('koordinator.judul.tambah')}}" method="POST">
                                {{ csrf_field() }}
                                <div class="col-lg-5">
                                    <div class="col-lg-12">
                                        <div class="form-group has-feedback {{ $errors->has('form_pembimbingjudul') ? ' has-error' : '' }}">
                                            <label data-error="wrong" data-success="right" for="Pilihdosen">Pilih Dosen</label><br>
                                            <select name="form_pembimbingjudul" class="form-control" id="form_pembimbingjudul" >
                                                <option value="">--Pilih--</option>
                                            @foreach($dosen as $dsn)
                                                <option value= "{{$dsn->dsn_nip.'_&_'.$dsn->dsn_nama}}">{{ ($dsn->batas_bimbingan - $judul->where('dsn_nip', $dsn->dsn_nip)->count()).' - '.$dsn->dsn_nama.' ('.$dsn->dsn_kode.')'}} </option>
                                            @endforeach
                                            </select>
                                            @if ($errors->has('form_pembimbingjudul'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('form_pembimbingjudul') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>

                                    <div class="col-lg-7">
                                        <div class="form-group has-feedback {{ $errors->has('form_kategoriJudul') ? ' has-error' : '' }}">
                                            <label data-error="wrong" data-success="right" for="kategori">Kategori</label>
                                            <a href="{{ route('koordinator.judul.kategori')}}" class="btn" title="Kategori Judul"><i class="fa fa-cog" aria-hidden="true"></i></a><br>
                                            
                                            <select name="form_kategoriJudul" class="form-control">
                                                <option value="">--pilih--</option>
                                                @foreach($kategori_judul  as $kategori)
                                                <option value="{{$kategori->kategori_id.'_&_'.$kategori->kategori_nama}}">{{$kategori->kategori_nama}}</option>
                                                @endforeach
                                            </select>                                            
                                            @if ($errors->has('form_kategoriJudul'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('form_kategoriJudul') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>                            
                                </div>

                                <div class="col-lg-7">
                                    <div class="col-lg-12">
                                        <div class="form-group has-feedback {{ $errors->has('form_judulProyek') ? ' has-error' : '' }}">
                                            <label>Tambah Judul</label><br>
                                            <input class="form-control" placeholder="Tambahkan Judul" name="form_judulProyek" value="{{ old('form_judulProyek')}}">
                                            @if ($errors->has('form_judulProyek'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('form_judulProyek') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="form-group has-feedback {{ $errors->has('form_deskripsiJudul') ? ' has-error' : '' }}">
                                            <label>Deskripsi Judul</label><br>
                                            <textarea class="form-control" rows="5" placeholder="Input Deskripsi" name="form_deskripsiJudul" >{{ old('form_deskripsiJudul')}}</textarea>
                                            @if ($errors->has('form_deskripsiJudul'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('form_deskripsiJudul') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="col-lg-5">
                                        <input type="submit" class="btn btn-primary" value="Tambah Judul Baru">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    @include('layout._alert')
                </div>
                            <!-- End Form Elements -->
            </div>
                </nav>

                <!--judulberjalan-->
                <h2>Judul Proyek Aktif</h2>
                <div class="line-title"></div>
                <br>
                <table id="datatable" class="table table-hover" style="width:100%">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Nama Tim</th>
                            <th scope="col">Judul Proyek Akhir</th>
                            <th scope="col">Kategori</th>
                            <th scope="col">Dosbing</th>
                            <th scope="col">Reviewer</th>
                            <th scope="col">Status</th>
                            <th scope="col">Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </nav>
    </div>
    @include('layout._modal')
    @endsection

    
@push('scripts')
    <script>
        $('#datatable').DataTable({
            responsive:true,
            processing:true,
            //serverSide:true,
            ajax: "{{ route('koordinator.judul_aktif.json') }}",
            columns:[
                {data: 'DT_RowIndex', name:'mhs_nama', searchable: false},
                {data: 'nama_tim', name:'nama_tim'},
                {data: 'judul_nama', name:'judul_nama'},
                {data: 'kategori_judul', name:'kategori_judul'},
                {data: 'pembimbing', name:'pembimbing'},
                {data: 'reviewer', name:'reviewer'},
                {data: 'status', name:'status'},
                {data: 'action', name: 'action', orderable: false, searchable: false},   
            ]
        });
    </script>
@endpush
