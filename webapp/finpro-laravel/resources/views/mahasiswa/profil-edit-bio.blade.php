@extends('layout.app-mhs')
    @section('title')
        Edit Biodata
    @endsection

    <!-- Body -->
        @section('top-bar')
            Edit Biodata
        @endsection


        <!-- Page Content Holder -->
        @section('content')
            <div id="content">
                <nav class="navbar navbar-default">

                <div class="container-fluid">
                        <!-- Form Elements -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Informasi Profile
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
                        <!-- End Form Elements -->
                    </div>
                </nav>

                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-4">
                            <form action="{{ route('mahasiswa.profil.biodata.ubah', ['nim' => $mhsBio->mhs_nim])}}" method="POST">
                                {{csrf_field()}}
                                {{method_field('PUT')}}
                                <div class="form-group has-feedback {{ $errors->has('bio_username') ? ' has-error' : '' }}">
                                    <Label> Username </label>
                                    <input class="form-control" type="text" name="bio_username" value="{{$mhsBio->tbl_user->username}}" placeholder="Isi nama lengkap">
                                    @if ($errors->has('bio_username'))
                                        <span class="help-block">
                                            <p class="text-danger">{{ $errors->first('bio_username') }}</p>
                                        </span>
                                    @endif
                                </div>
                                <div class="form-group has-feedback {{ $errors->has('bio_mhsNama') ? ' has-error' : '' }}">
                                    <Label> Nama </label>
                                    <input class="form-control" type="text" name="bio_mhsNama" value="{{$mhsBio->mhs_nama}}" placeholder="Isi nama lengkap">
                                    @if ($errors->has('bio_mhsNama'))
                                        <span class="help-block">
                                            <p class="text-danger">{{ $errors->first('bio_mhsNama') }}</p>
                                        </span>
                                    @endif
                                </div>
                                <div class="form-group ">
                                    <Label> Nim </Label>
                                    <input class="form-control" type="text" name="bio_mhsNim" value="{{$mhsBio->mhs_nim}}" readonly="readonly">
                                </div>
                                <div class="form-group">
                                    <Label> Angkatan </Label>
                                    <input class="form-control" type="text" name="bio_mhsAngkatan" value="{{$mhsBio->angkatan}}" readonly="readonly" >
                                </div>
                                <div class="form-group has-feedback {{ $errors->has('bio_mhsEmail') ? ' has-error' : '' }}">
                                    <Label> Email </Label>
                                    <input class="form-control" type="text" name="bio_mhsEmail" value="{{$mhsBio->mhs_email}}" placeholder="Email kosong">
                                    @if ($errors->has('bio_mhsEmail'))
                                        <span class="help-block">
                                            <p class="text-danger">{{ $errors->first('bio_mhsEmail') }}</p>
                                        </span>
                                    @endif
                                </div>
                                <div class="form-group has-feedback {{ $errors->has('bio_mhskontak') ? ' has-error' : '' }}">
                                    <Label> Kontak </Label>
                                    <input class="form-control" type="text" name="bio_mhskontak" value="{{$mhsBio->mhs_kontak}}" placeholder="Kontak kosong">
                                    @if ($errors->has('bio_mhskontak'))
                                        <span class="help-block">
                                            <p class="text-danger">{{ $errors->first('bio_mhskontak') }}</p>
                                        </span>
                                    @endif
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-md btn-primary">Perbaharui</button>
                                </div>
                            </form>
                            @include('layout._alert')
                        </div>
                </div>        
        @endsection      
