@extends('layout.app-mhs')
@section('title')
Judul Proyek Akhir Tim 
@endsection

<!-- Body -->
@section('top-bar')
Judul Proyek Akhir Tim
@endsection


<!-- Page Content Holder -->
@section('content')
<div id="content">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
        <!-- Tim -->
        <div class="panel panel-default">
            <div class="panel-heading">
                Informasi Tim Proyek Akhir
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
                            <!--  -->
                            <!--  -->
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

                            <label>Status Judul</label><br>
                                @if(!is_null($mhsBio->tbl_judul))
                                    @if($mhsBio->tbl_judul->judul_status === "tersedia" || $mhsBio->tbl_judul->judul_status == "pending")
                                        <p class="text-warning"><b>Menunggu Persetujuan...</b></p>
                                    @else
                                        <p class="text-success"><b>Disetujui</b></p>
                                    @endif
                                @else
                                    <p ><b>Belum Mengajukan</b></p>
                                @endif
                        </div>
                    </div>            
                </div>
            </div>
        </div>
        <!-- Akhir Tim -->      

        <!-- Tim -->
        <div class="panel panel-default">
            <div class="panel-heading">
                Informasi Nilai Anda
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-10">
                        @include('layout._alert')
                        <form action="{{ route('mahasiswa.judul.ubah')}}" method="post" enctype="multipart/form-data">
                        {{csrf_field()}}
                            <table class="table table-bordered  table-hover ">
                            <tr>
                                <td class="table-header"><b>Kategori</b></td>
                                <td class="table-header"><b><center>Nilai</center></b></td>
                            </tr>
                            <tr>
                                <td>Jumlah Bimbingan</td>
                                <td><center>{{ $proyekAkhirNilai->tbl_bimbingan->count() }} kali</center></td>
                            </tr>
                            <tr>
                                <td>Nilai Monev (Rata-rata)</td>
                                <td><center>{!! empty($proyekAkhirNilai->tbl_detail_monev) === true ? '<i>kosong</i> ('.$proyekAkhirNilai->tbl_detail_monev->count().' dari '.$monev->count().' Monev)' : '<b>'.($proyekAkhirNilai->tbl_detail_monev->sum('monev_nilai') / $monev->count()).'</b> ('.$proyekAkhirNilai->tbl_detail_monev->count().' dari '.$monev->count().' Monev)' !!}</center></td>
                            </tr>
                            <tr>
                                <td>Nilai Sidang Total</td>
                                <td><center>{!! empty($proyekAkhirNilai->tbl_sidang->nilai_total) === true ? '<i>kosong</i>' : $proyekAkhirNilai->tbl_sidang->nilai_total !!}</center></td>
                            </tr>
                            <tr>
                                <td>Status Sidang</td>
                                <td><center>
                                    @if(empty($proyekAkhirNilai->tbl_sidang->sidang_status) || is_null($proyekAkhirNilai->tbl_sidang->sidang_status))
                                        <a class="form-control btn-warning">BELUM LULUS</a></center></td>
                                    @elseif($proyekAkhirNilai->tbl_sidang->sidang_status === '-' || $proyekAkhirNilai->tbl_sidang->sidang_status === 'tidak lulus')
                                        <a class="form-control btn-warning">BELUM LULUS</a></center></td>
                                    @elseif($proyekAkhirNilai->tbl_sidang->sidang_status === 'lulus bersyarat')
                                        <a class="form-control btn-success">LULUS BERSYARAT</a></center></td>
                                    @else
                                        <a class="form-control btn-success">LULUS</a></center></td>
                                    @endif
                            </tr>
                            <tr>
                                <td>Berkas Dokumen Proyek Akhir <p>upload perwakilan tim</p></td>
                                <td>
                                    @if(empty($proyekAkhirNilai->tbl_judul->judul_dokumen))
                                        <i>belum ada berkas...</i>
                                    @else
                                        download berkas dokumen <a href="{{ asset('file/dokumen_pa/'.$proyekAkhirNilai->tbl_judul->judul_dokumen) }}" class="btn btn-default">disini...</a>
                                        
                                    @endif
                                    <div class="form-group has-feedback {{ $errors->has('fileDokumen') ? ' has-error' : '' }}">
                                    <input type="file" class="form-control" name="fileDokumen" id="fileDokumen"><p>*.pdf dokumen Max 20MB</p>
                                        @if ($errors->has('fileDokumen'))
                                            <span class="help-block">
                                                <p class="text-danger">{{ $errors->first('fileDokumen') }}</p>
                                            </span>
                                        @endif
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Berkas Abstrak Proyek Akhir <p>upload perwakilan tim</p></td>
                                <td>
                                    @if(empty($proyekAkhirNilai->tbl_judul->judul_abstrak))
                                        <i>belum ada berkas...</i>
                                    @else
                                        download berkas abstrak <a href="{{ asset('file/dokumen_pa/'.$proyekAkhirNilai->tbl_judul->judul_abstrak) }}" class="btn btn-default">disini...</a>
                                        
                                    @endif
                                    <div class="form-group has-feedback {{ $errors->has('fileAbstrak') ? ' has-error' : '' }}">
                                        <input type="file" class="form-control" name="fileAbstrak" ><p>*.pdf dokumen Max 5MB</p>
                                        @if ($errors->has('fileAbstrak'))
                                            <span class="help-block">
                                                <p class="text-danger">{{ $errors->first('fileAbstrak') }}</p>
                                            </span>
                                        @endif
                                    </div>
                                </td>
                            </tr>
                            </table>
                            <input type="submit" class="btn btn-primary" value="Simpan perubahan" name="upload">
                        </form>
                    </div>     
                    <div class="col-lg-2">
                        <div class="panel panel-pemberitahuan panel-default">
                            <p><b>Info :</b><br>
                            Nilai masih dapat berubah sewaktu-waktu, terkecuali status sudah dinyatakan RESMI LULUS</p>
                        </div>
                    </div>   
                </div>
            </div>
        </div>
        <!-- Akhir Tim -->     
        </div>
    </nav>

</div>
@include('layout._modal')

@push('scripts')
    <script>

    </script>
@endpush

@endsection