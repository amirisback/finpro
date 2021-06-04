@extends('layout.app')
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
                    <form action="{{ route('koordinator.biodata.ubah', ['nip' => $koordinator->koor_nip])}}" method="POST">
                        {{csrf_field()}}
                        {{method_field('PUT')}}
                        <div class="form-group">
                            <Label> Username </label><br>
                            <div class="input-group">
                                <input class="form-control {{ $errors->has('username_koor') ? 'is-invalid' : '' }}" type="text" name="username_koor" value="{{$koordinator->username}}"><br>
                                @if ($errors->has('username_koor'))
                                    <div class="invalid-feedback">
                                    <p class="text-danger">{{ $errors->first('username_koor')}}</p>
                                    </div>
                                @endif
                            </div>

                            <Label> Nama </label><br>
                            <div class="input-group">
                                <input class="form-control {{ $errors->has('nama_koor') ? 'is-invalid' : '' }}" type="text" name="nama_koor" value="{{$koordinator->koor_nama}}"><br>
                                @if ($errors->has('nama_koor'))
                                    <div class="invalid-feedback">
                                        <p class="text-danger">{{ $errors->first('nama_koor')}}</p>
                                    </div>
                                @endif
                            </div>
                            <Label> NIP </Label><br>
                            <div class="input-group">
                                <input class="form-control {{ $errors->has('nip_koor') ? 'is-invalid' : '' }}" type="text" name="nip_koor" value="{{$koordinator->koor_nip}}"><br>
                                @if ($errors->has('nip_koor'))
                                    <div class="invalid-feedback">
                                        <p class="text-danger">{{ $errors->first('nip_koor')}}</p>
                                    </div>
                                @endif
                            </div>
                            <Label> Kode Dosen Koordinator </Label><br>
                            <div class="input-group">
                                <input class="form-control {{ $errors->has('kode_koor') ? 'is-invalid' : '' }}" type="text" name="kode_koor" value="{{$koordinator->koor_kode}}"><br>
                                @if ($errors->has('kode_koor'))
                                    <div class="invalid-feedback">
                                        <p class="text-danger">{{ $errors->first('kode_koor')}}</p>
                                    </div>
                                @endif
                            </div>
                            <Label> Email </Label><br>
                            <div class="input-group">
                                <input class="form-control {{ $errors->has('email_koor') ? 'is-invalid' : '' }}" type="text" name="email_koor" value="{{$koordinator->koor_email}}" placeholder="kosong"><br>
                                @if ($errors->has('email_koor'))
                                    <div class="invalid-feedback">
                                        <p class="text-danger">{{ $errors->first('email_koor')}}</p>
                                    </div>
                                @endif
                            </div>
                            <Label> No HandPhone </Label><br>
                            <div class="input-group">
                            <input class="form-control {{ $errors->has('kontak_koor') ? 'is-invalid' : '' }}" type="text" name="kontak_koor" value="{{$koordinator->koor_kontak}}" placeholder="kosong"><br>
                            @if ($errors->has('kontak_koor'))
                                <div class="invalid-feedback">
                                    <p class="text-danger">{{ $errors->first('kontak_koor')}}</p>
                                </div>
                            @endif
                        </div>
                        <br>
                        <div class="form-group">
                            <button type="submit" class="btn btn-md btn-primary">Perbaharui</button>
                        </div>
                    </form>
                    @include('layout._alert')
                </div>
            </div>
        </div>
    </nav>
    
</div>
@endsection