@extends('layout.app-mhs')
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
                <nav class="navbar navbar-default">
                    <div class="container-fluid">    
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-12">
                                        @forelse($informasi as $info)
                                            <h3>{{$info->informasi_judul}}</h3>
                                            <p><i>{!! $info->informasi_waktu.', <a>'.$info->penerbit .'</a>'!!}</i></p>
                                            <hr>
                                            <h5>{{$info->informasi_isi}}</h5>
                                            <br>              
                                        @empty
                                            <h3>Belum Ada Informasi apapun saat ini</h3>
                                        @endforelse
                                        <center>{{ $informasi->links()}}</center>
                                    </div>
                                </div>
                            </div>
                    </div>
                </nav>
            </div>
        @endsection