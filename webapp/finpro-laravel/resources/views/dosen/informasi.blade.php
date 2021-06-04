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
                        
                        
                        @forelse($informasi as $info)
                            <h3>{{$info->informasi_judul}}</h3>
                            <p><i>{!! $info->informasi_waktu.', <a>'.$info->penerbit .'</a>'!!}</i></p>
                            <h5>{{$info->informasi_isi}}</h5>
                            <br>              
                        @empty
                            <h3>Belum Ada Informasi apapun saat ini</h3>
                        @endforelse
                        <center>{{ $informasi->links()}}</center>
                    </div>
                </nav>
            </div>
        @endsection
