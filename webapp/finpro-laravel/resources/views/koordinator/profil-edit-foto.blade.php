@extends('layout.app')
    @section('title')
        Edit Foto 
    @endsection

    <!-- Body -->
        @section('top-bar')
            Edit Foto profile
        @endsection

        <!-- Page Content Holder -->
        @section('content')
            <div id="content">
                <nav class="navbar navbar-default">

                <div class="container-fluid">
                        <!-- Form Elements -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Edit Foto
                            </div>

                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-12 text-center">
                                    <form action="{{ route('koordinator.foto.ubah', ['nip' => $koordinator->koor_nip])}}" method="POST" enctype="multipart/form-data">
                                        {{csrf_field()}}
                                        {{method_field('PUT')}}
                                        <div class="form-group">
                                        <img src="{{URL::asset('/image/koor_img/'. Session::get('session_gambar') )}}" alt="koordinator" width="200" height="200">
                                        <br><br>
                                        <div class=" input-group-lg mx-auto">
                                            <input type="file" class="form-control {{ $errors->has('upload_gambar') ? 'is-invalid' : '' }}" name="upload_gambar" id="upload_gambar" style="margin:auto;">                                    
                                            @if ($errors->has('upload_gambar'))
                                                <div class="invalid-feedback">
                                                    <p class="text-danger">{{ $errors->first('upload_gambar')}}</p>
                                                </div>
                                            @endif
                                        </div>

                                        <p>Resolusi foto 150dpi-300dpi
                                        dengan latar
                                        belakang merah
                                        file.jpg, Max 2MB</p>
                                        <button type="submit" class="btn btn-primary">Upload File</button>
                                        </div>
                                    </form>
                                    @include('layout._alert')
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <!-- End Form Elements -->

                    </div>
                </nav>

            </div>
        @endsection
