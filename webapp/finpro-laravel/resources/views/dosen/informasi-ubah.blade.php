@extends('layout.app-dosen')
    @section('title')
        Informasi
    @endsection

    <!-- Body -->
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
                                    <form action="{{ route('dosen.buat.informasi') }}" method="post">
                                    {{csrf_field()}}
                                        <div class="col-lg-12">
                                                <div class="form-group has-feedback {{ $errors->has('form_informasiJudul') ? ' has-error' : '' }}">
                                                    <label>Tambahkan Judul</label>
                                                    <input class="form-control" placeholder="Input Judul Informasi" name="form_informasiJudul" value="{{ old('judul_informasi') }}">
                                                    @if ($errors->has('form_informasiJudul'))
                                                        <span class="help-block">
                                                            <p class="text-danger">{{ $errors->first('form_informasiJudul') }}</p>
                                                        </span>
                                                    @endif
                                                </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="form-group has-feedback {{ $errors->has('form_informasiDeskripsi') ? ' has-error' : '' }}">
                                                <label>Deskripsi</label>
                                                <textarea class="form-control" rows="3" placeholder="Input Deskripsi" name="form_informasiDeskripsi" value="{{ old('deskripsi_informasi') }}" style="max-width: 100%; min-width: 100%; max-height: 200px; min-height: 80px;"></textarea>
                                                @if ($errors->has('form_informasiDeskripsi'))
                                                    <span class="help-block">
                                                        <p class="text-danger">{{ $errors->first('form_informasiDeskripsi') }}</p>
                                                    </span>
                                                @endif
                                            </div>
                                            <input type="submit" class="btn btn-primary" value="Tambahkan Informasi">
                                        </div>
                                    </form>
                                    @include('layout._alert')
                                </div>
                            </div>
                        </div>
                        <!-- End Form Elements -->
                        <div class="col-lg-12">
                                    
                            <h2>Informasi saya</h2>
                                <div class="line-title"></div>
                                <br>
                            <table class="table">
                                <thead class="thead-light">
                                    <tr>
                                        <th scope="col">No</th>
                                        <th scope="col">Judul</th>
                                        <th scope="col">Isi Informasi</th>
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
                                        <td>{{ $info->informasi_waktu }}</td>
                                        <td>        
                                            <a href="{{ route('dosen.informasi.tambah-ubah.detail', ['id' => $info->informasi_id])}}" class="modal-show edit btn btn-action" title="Ubah informasi {{ $info->informasi_judul }}" ><i class="icon-pencil text-inverse"></i></a>
                                            <a href="{{ route('dosen.informasi.tambah-ubah.hapus', ['id' => $info->informasi_id])}}" class="btn-delete btn btn-action"  title="Hapus informasi {{ $info->informasi_judul }}" ><i class="icon-trash text-danger"></i></a>
                                        </td>
                                    </tr>
                                @empty
                                    <td colspan="2" class="text-center">Informasi Kosong</td>
                                @endforelse
                                </tbody>
                            </table> 
                        </div>
                    </div>
                </nav>
            </div>
        @include('layout._modal')
        @endsection
