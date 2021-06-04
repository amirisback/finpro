@extends('layout.app')
    @section('title')
        Edit Password
    @endsection
    <!-- Body -->
        @section('top-bar')
            Edit Password
        @endsection

        <!-- Page Content Holder -->
        @section('content')
            <div id="content">
                <nav class="navbar navbar-default">

                <div class="container-fluid">
                        <!-- Form Elements -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Edit password
                            </div>
                            
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <form action="{{ route('koordinator.password.ubah', ['username' => $koordinator->username])}}" method="POST">
                                            {{csrf_field()}}
                                            {{method_field('PUT')}}
                                            <div class="form-group has-feedback {{ $errors->has('password_lama') ? ' has-error' : '' }}">
                                                <Label> Password Lama</label>
                                                <input  type="password" class="form-control" name="password_lama" placeholder="Ketik Password Lama">
                                                @if ($errors->has('password_lama'))
                                                    <span class="help-block">
                                                        <p class="text-danger">{{ $errors->first('password_lama') }}</p>
                                                    </span>
                                                @endif
                                            </div>

                                            <div class="form-group has-feedback {{ $errors->has('password_baru') ? ' has-error' : '' }}">
                                                <Label> Password Baru </Label><br>
                                                <div class="form-group has-feedback">
                                                    <input id="password-field" type="password" class="form-control" name="password_baru" placeholder="Ketik Password Baru">
                                                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password" ></span>
                                                </div>
                                                @if ($errors->has('password_baru'))
                                                <span class="help-block">
                                                        <p class="text-danger">{{ $errors->first('password_baru') }}</p>
                                                    </span>
                                                @endif
                                            </div>
                                            <div class="form-group has-feedback {{ $errors->has('password_ulangi') ? ' has-error' : '' }}">
                                                <Label> Ulangi password Baru</label> <br>
                                                <input type="password" class="form-control" name="password_ulangi" placeholder="Ulangi Password Baru">
                                                @if ($errors->has('password_ulangi'))
                                                    <span class="help-block">
                                                        <p class="text-danger">{{ $errors->first('password_ulangi') }}</p>
                                                    </span>
                                                @endif
                                            </div>
                                            <button type="submit" class="btn btn-primary">Perbaharui Password</button>
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

        @push('scripts')
            <script>
           $(".toggle-password").click(function() {
                $(this).toggleClass("fa-eye fa-eye-slash");
                var input = $($(this).attr("toggle"));
                if (input.attr("type") == "password") {
                    input.attr("type", "text");
                } else {
                    input.attr("type", "password");
                }
            });
        </script>
        @endpush
