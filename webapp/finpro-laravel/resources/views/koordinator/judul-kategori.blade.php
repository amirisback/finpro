@extends('layout.app')
    @section('title')
        Kategori Judul Proyek Akhir
    @endsection
    <!-- body -->
        @section('top-bar')
            Kategori Judul Proyek Akhir
        @endsection

        <!-- Page Content Holder -->
        @section('content')
            <div id="content">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                            <a href="{{ route('koordinator.judul.aktif')}}" title="kembali"><i class="fa fa-reply text-info"></i></a> Tambahkan Kategori Baru
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <form action="{{route('koordinator.judul.kategori.tambah')}}" method="POST">
                                        {{ csrf_field() }}
                                        
                                            <div class="col-lg-5">
                                                <div class="col-lg-9">
                                                    <div class="form-group has-feedback {{ $errors->has('form_kategoriJudul') ? ' has-error' : '' }}">
                                                        <label>Tambah Kategori</label><br>
                                                        <input class="form-control" placeholder="Input Kategori" name="form_kategoriJudul" value="{{ old('form_kategoriJudul')}}">
                                                        @if ($errors->has('form_kategoriJudul'))
                                                            <span class="help-block">
                                                                <p class="text-danger">{{ $errors->first('form_kategoriJudul') }}</p>
                                                            </span>
                                                        @endif
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <input type="submit" class="btn btn-success" value="Tambah Kategori">
                                                </div>
                                                <div class="col-lg-12"> 
                                                   <p class="filexls">*Kategori yang sudah terisi judul tidak dapat dihapus, melainkan hanya dapat di ubah</p>
                                                </div>
                                                
                                                @include('layout._alert')
                                            </div>
                                            <div class="col-lg-7">
                                                <div class="col lg-12">
                                                <table class="table table-hover" style="width:100%">
                                                    <thead class="thead-light">
                                                        <tr>
                                                            <th scope="col">No</th>
                                                            <th scope="col">Kategori</th>
                                                            <th scope="col">Jumlah judul Aktif</th>
                                                            <th scope="col">Status</th>
                                                            <th scope="col">Aksi</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        @forelse($kategoriJudul as $index => $kategori)
                                                            <tr>
                                                                <td>{{ $index +1 }}</td>
                                                                <td>{{ $kategori->kategori_nama}}</td>
                                                                <td>{{ $countJudul->where('kategori_id', $kategori->kategori_id)->count()}} Judul</td>
                                                                <td>
                                                                    @if($kategori->kategori_status == '1')
                                                                        <a class="btn btn-success">Aktif</a>
                                                                    @else
                                                                        <a class="btn btn-danger">Tidak Aktif</a>
                                                                    @endif
                                                                </td>
                                                                <td>
                                                                    @if($countJudul->where('kategori_id', $kategori->kategori_id)->count() === 0)
                                                                        <a href="{{ route('koordinator.judul.kategori.ubah', ['id' => $kategori->kategori_id])}}" class="btn btn-action modal-show edit " title="{{'Ubah kategori '.$kategori->kategori_nama}}"><i class="icon-pencil text-inverse"></i></a>
                                                                        <a href="{{ route('koordinator.judul.kategori.hapus', ['id' => $kategori->kategori_id])}}" class="btn btn-action btn-delete" title="{{'kategori '.$kategori->kategori_nama}}"><i class="icon-trash text-danger"></i></a>
                                                                    @else
                                                                        <a href="{{ route('koordinator.judul.kategori.ubah', ['id' => $kategori->kategori_id])}}" class="btn btn-action modal-show edit " title="{{'Ubah kategori '.$kategori->kategori_nama}}"><i class="icon-pencil text-inverse"></i></a>
                                                                    @endif
                                                                </td>
                                                            </tr>
                                                        @empty
                                                            <td colspan="2" class="text-center">No informasi found</td>
                                                        @endforelse
                                                    </tbody>
                                                </table>
                                                </div>
                                            </div>
                                           
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </nav>
    </div>
    @include('layout._modal')
    @endsection

    
