@extends('layout.app-dosen')
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
                        Edit Foto Biodata
                    </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-4">
                        @include('layout._alert')
                            <form action="{{route('dosen.biodata.ubah',['nip' => $dosen->dsn_nip])}}" method="POST">
                            {{csrf_field()}}
                             {{method_field('PUT')}}
                                <div class="form-group">
                                    <div class="form-group has-feedback {{ $errors->has('bio_username') ? ' has-error' : '' }}">
                                        <Label>Username</label><br>
                                        <div class="input-group">
                                            <input class="form-control" type="text" name="bio_username" value="{{$dosen->username}}"><br>
                                            @if ($errors->has('bio_username'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('bio_username') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="form-group has-feedback {{ $errors->has('bio_dsnNama') ? ' has-error' : '' }}">
                                        <label>Nama</label><br>
                                        <div class="input-group">
                                            <input class="form-control" type="text" name="bio_dsnNama" value="{{$dosen->dsn_nama}}"><br>
                                            @if ($errors->has('bio_dsnNama'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('bio_dsnNama') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="form-group has-feedback">
                                        <Label>NIP</Label><br>
                                        <div class="input-group">
                                            <input class="form-control" type="text" name="bio_dsnNip" value="{{$dosen->dsn_nip}}" readonly="readonly"><br>    
                                        </div>
                                    </div>
                                    <div class="form-group has-feedback {{ $errors->has('bio_dsnKode') ? ' has-error' : '' }}">
                                        <Label>Kode Dosen</Label><br>
                                        <div class="input-group">
                                            <input class="form-control" type="text" name="bio_dsnKode" value="{{$dosen->dsn_kode}}" readonly="readonly"><br>
                                            @if ($errors->has('bio_dsnKode'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('bio_dsnKode') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="form-group has-feedback {{ $errors->has('bio_dsnEmail') ? ' has-error' : '' }}">
                                        <Label>Email</Label><br>
                                        <div class="input-group">
                                            <input class="form-control" type="text" name="bio_dsnEmail" value="{{$dosen->dsn_email}}" placeholder="kosong"><br>
                                            @if ($errors->has('bio_dsnEmail'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('bio_dsnEmail') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="form-group has-feedback {{ $errors->has('bio_dsnkontak') ? ' has-error' : '' }}">
                                        <Label>No HandPhone</Label><br>
                                        <div class="input-group">
                                            <input class="form-control" type="text" name="bio_dsnkontak" id="kontak" value="{{$dosen->dsn_kontak}}" placeholder="kosong"><br>
                                            @if ($errors->has('bio_dsnkontak'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('bio_dsnkontak') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-md btn-primary">Perbaharui</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                </nav>
            </div>
        @endsection