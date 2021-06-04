@extends('layout.app-mhs') @section('title') Monev @endsection
<!-- Body -->
@section('top-bar') Data Monev @endsection

<!-- Page Content Holder -->
@section('content')
<div id="content">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Form Elements -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    Monev
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-4">
                            <label>Judul Proyek Akhir</label><br>
                            @if(!is_null($mhsBio->tbl_judul))
                                <p>{{$mhsBio->tbl_judul->judul_nama}}</p>
                            @else
                            <p><i>Belum Ada</i></p>  
                            @endif
                            <label>Dosen Pembimbing</label><br>
                            @if(!is_null($mhsBio->tbl_judul))
                                <p>{{$mhsBio->tbl_judul->tbl_dosen->dsn_nama.' ('.$mhsBio->tbl_judul->tbl_dosen->dsn_kode.')'}}</p>
                            @else
                                <p><i>Belum Ada</i></p>  
                            @endif
                            <label>Dosen Reviewer</label><br>
                            @if(!is_null($mhsBio->tbl_judul))
                                @foreach($mhsBio->tbl_proyek_akhir as $proyek)
                                    @if($proyek->dsn_nip)
                                        <p>{{$proyek->tbl_dosen->dsn_nama.' ('. $proyek->tbl_dosen->dsn_kode.')'}}</p>
                                    @else
                                        <p><i>Belum Ada</i></p>  
                                    @endif
                                @endforeach
                            @else
                                <p><i>Belum Ada</i></p>  
                            @endif
                            
                        </div>
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group"> 
                                <label>Nama Kelompok</label><br>
                                    @if($namaTim)
                                        <p>{{$namaTim}}</p>
                                    @else
                                        <p><i>Belum Ada</i></p>  
                                    @endif
                                <label>Tim Kelompok</label><br>
                                    @if($namaTim)
                                        @php $anggotaTim = Array(); @endphp
                                        <p>
                                        @foreach($proyekAkhir as $index => $proyek)
                                            {{ ($index+1).'. '.$proyek->tbl_mahasiswa->mhs_nama.' ('.$proyek->tbl_mahasiswa->mhs_nim.')'}}<br>
                                        @endforeach
                                        </p>
                                    @else
                                    <p><i>Belum Ada</i></p>  
                                    @endif
                            </div>
                        </div>            
                    </div>
            </div>
        </div>
    </nav>
    <h2>Nilai Monev</h2>
    <div class="line-title"></div>
    <br>
    <!-- <div class="panel panel-default"> -->
    <table id="datatable" class="table table-hover" style="width:100%">
        <thead class="thead-light">
            <tr>
                <th scope="col">No</th>
                <th scope="col">Monev Kategori</th>
                <th scope="col">Tanggal</th>
                <th scope="col">Nilai</th>
                <th scope="col">Catatan</th>
            </tr>
        </thead>
        <tbody>
            @forelse($dataMonev as $index => $monev)
                @forelse($monev->tbl_detail_monev as $indeks => $monevNilai)
                    <tr>
                        <td scope="row">{{ $indeks+1 }}</td>
                        <td>{{ $monevNilai->tbl_monev->monev_kategori }}</td>
                        <td>{{ $monevNilai->monev_tanggal }}</td>
                        <td>
                            @if($monevNilai->monev_nilai >= 80 )
                            <a class="btn btn-success">{{ $monevNilai->monev_nilai }}</a>
                            @elseif($monevNilai->monev_nilai < 79 )
                            <a class="btn btn-warning">{{ $monevNilai->monev_nilai }}</a>
                            @endif
                        </td>
                        <td>{{ $monevNilai->monev_ulasan }}</td>
                    </tr>
                @empty
                    <tr>
                        <td colspan="5" class="text-center">Data Monev Kosong</td>
                    </tr>
                @endforelse
            @empty
                <tr>
                    <td colspan="5" class="text-center">Data Monev Kosong</td>
                </tr>
            @endforelse
         
        </tbody>
    </table>
    
</div>
<!-- </div> -->
@endsection