@extends('layout.app')
    @section('title')
         Informasi
    @endsection

    <!-- body -->
        @section('top-bar')
             Informasi
        @endsection
        
        <!-- Page Content Holder -->
            @section('content')
            <div id="content">
            <nav class="navbar navbar-default">

                <div class="container-fluid">

                <!-- Form Elements -->
                <div class="panel panel-default">
                        <div class="panel-heading">
                            Tambahkan Informasi Baru
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                <form action="{{ route('koordinator.informasi.buat') }}" method="post">
                                {{csrf_field()}}
                                    <div class="form-group has-feedback {{ $errors->has('form_informasiJudul') ? ' has-error' : '' }}">
                                        <label>Judul informasi</label>
                                        <input class="form-control" placeholder="Input Judul" name="form_informasiJudul" value="{{ old('form_informasiJudul')}}">
                                        @if ($errors->has('form_informasiJudul'))
                                            <span class="help-block">
                                                <p class="text-danger">{{ $errors->first('form_informasiJudul') }}</p>
                                            </span>
                                        @endif
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group has-feedback {{ $errors->has('form_informasiDeskripsi') ? ' has-error' : '' }}">
                                        <label>Deskripsi informasi</label>
                                        <textarea class="form-control" rows="6" placeholder="Input Deskripsi" name="form_informasiDeskripsi">{{ old('form_iform_informasiDeskripsinformasiJudul')}}</textarea>
                                        @if ($errors->has('form_informasiDeskripsi'))
                                            <span class="help-block">
                                                <p class="text-danger">{{ $errors->first('form_informasiDeskripsi') }}</p>
                                            </span>
                                        @endif
                                    </div>
                                    <button type="submit" class="btn btn-primary">Tambahkan Informasi</button>
                                </div>
                                </form>
                                @include('layout._alert')
                            </div>
                        </div>
                    </div> 
                    <br>   

                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <form role="form">
                                <h2>Terbaru</h2>
                                    <div class="line-title"></div>
                                    <br>
                                <table class="table">
                                    <thead class="thead-light">
                                        <tr>
                                            <th scope="col">No</th>
                                            <th scope="col">Judul</th>
                                            <th scope="col">Isi Informasi</th>
                                            <th scope="col">Penerbit</th>
                                            <th scope="col">Tanggal</th>
                                            <th scope="col">Aksi</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                    @forelse($informasi as $index => $info)
                                        <tr>
                                            <td>{{ $index +1 }}</td>
                                            <td>{{ $info->informasi_judul }}</td>
                                            <td>{{ $info->informasi_isi }}</td>
                                            <td>{{ $info->penerbit }}</td>
                                            <td>{{ $info->informasi_waktu }}</td>
                                            <td>        
                                                <a href="{{ route('koordinator.informasi.edit', ['id' => $info->informasi_id])}}" class="btn modal-show edit  btn-primary" title="Ubah informasi {{ $info->informasi_judul }}" ><i class="icon-pencil text-inverse"></i></a>
                                                <a href="{{ route('koordinator.informasi.hapus', ['id' => $info->informasi_id])}}" class="btn btn-delete  btn-danger"  title="Hapus informasi {{ $info->informasi_judul }}" ><i class="icon-trash text-inverse"></i></a>
                                            </td>
                                        </tr>
                                    @empty
                                        <td colspan="2" class="text-center">Informasi Kosong</td>
                                    @endforelse
                                    </tbody>
                                </table> 
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
            </div>            
            @include('layout._modal')
            @endsection