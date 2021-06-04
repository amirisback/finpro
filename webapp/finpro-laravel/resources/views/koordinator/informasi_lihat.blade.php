@extends('layout.app')
    @section('title')
        Lihat Informasi
    @endsection

    <!-- body -->
    @section('top-bar')
        Lihat Informasi
    @endsection

    <!-- Page Content Holder -->
    @section('content')
        <div id="content">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    @forelse($informasiPagination as $info)
                        <h2>{{$info->informasi_judul}}</h2>
                        <h4>{{$info->informasi_waktu}} <a>{{$info->penerbit}}</a></h4>
                        <p>{{$info->informasi_isi}}</p>
                        <div class="line"></div>
                    @empty
                    <h2>Belum Ada Informasi apapun saat ini</h2>
                    @endforelse
                    {{ $informasiPagination->links() }}
                </div>
            </nav>
        </div>
    @endsection




