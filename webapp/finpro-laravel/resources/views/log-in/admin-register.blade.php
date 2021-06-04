<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Register</div>
                <div class="card-body">

                <!-- form -->
                    <form class="form-horizontal" method="post" action="{{ route('koordinator.register.submit') }}">
                    {{ csrf_field() }}
                        <div class="form-group">
                            <label for="name" class="cols-sm-2 control-label">Nama Anda</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                            aria-hidden="true"></i></span>
                                    <input type="text" class="form-control {{ $errors->has('name') ? 'is-invalid' : '' }}" name="name" id="name" placeholder="Enter your Name" value="{{ old('name') }}"/>
                                        @if ($errors->has('name'))
                                            <div class="invalid-feedback">
                                                {{ $errors->first('name')}}
                                            </div>
                                        @endif
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="cols-sm-2 control-label">NIP Dosen Koordinator</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope fa"
                                            aria-hidden="true"></i></span>
                                    <input type="text" class="form-control {{ $errors->has('nip') ? 'is-invalid' : '' }}" name="nip" id="email" placeholder="Enter your NIP" value="{{ old('nip') }}" />
                                        @if ($errors->has('nip'))
                                            <div class="invalid-feedback">
                                                {{ $errors->first('nip')}}
                                            </div>
                                        @endif
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="username" class="cols-sm-2 control-label">Username</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control {{ $errors->has('username') ? 'is-invalid' : '' }}" name="username" id="username" placeholder="Enter your Username" value="{{ old('username') }}" />
                                        @if ($errors->has('username'))
                                            <div class="invalid-feedback">
                                                {{ $errors->first('username')}}
                                            </div>
                                        @endif
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="cols-sm-2 control-label">Password</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg"
                                            aria-hidden="true"></i></span>
                                    <input type="password" class="form-control {{ $errors->has('password') ? 'is-invalid' : '' }}" name="password" id="password" placeholder="Enter your Password"  />
                                        @if ($errors->has('password'))
                                            <div class="invalid-feedback">
                                                {{ $errors->first('password')}}
                                            </div>
                                        @endif
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg"
                                            aria-hidden="true"></i></span>
                                    <input type="password" class="form-control {{ $errors->has('password_confirmation') ? 'is-invalid' : '' }}" name="password_confirmation" id="confirm" placeholder="Confirm your Password" />
                                        @if ($errors->has('password_confirmation'))
                                            <div class="invalid-feedback">
                                                {{ $errors->first('password_confirmation')}}
                                            </div>
                                        @endif
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="cols-sm-2 control-label">Your Email</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope fa"
                                            aria-hidden="true"></i></span>
                                    <input type="text" class="form-control {{ $errors->has('email') ? 'is-invalid' : '' }}" name="email" id="email" placeholder="Enter your Email" value="{{ old('email') }}"/>
                                        @if ($errors->has('email'))
                                            <div class="invalid-feedback">
                                                {{ $errors->first('email')}}
                                            </div>
                                        @endif
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="noTelpon" class="cols-sm-2 control-label">No Telpon</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope fa"
                                            aria-hidden="true"></i></span>
                                    <input type="text" class="form-control {{ $errors->has('noTelp') ? 'is-invalid' : '' }}" name="noTelp" id="notelp" placeholder="Enter your Phone Number" value="{{ old('noTelp') }}" />
                                        @if ($errors->has('noTelp'))
                                            <div class="invalid-feedback">
                                                {{ $errors->first('noTelp')}}
                                            </div>
                                        @endif
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">                                
                            <button type="submit"
                                class="btn btn-primary btn-lg btn-block login-button">Register</button>
                        </div>
                        <div class="login-register">
                            <a href="index.php">Login</a>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>