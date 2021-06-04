<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<title>Lupa Password</title>
	<link rel="stylesheet"  href="/css/login.css" >
	<link rel="shortcut icon title" href="{{{ asset('image/title.png') }}}">
</head>

<body>
	<div class="loginbox">
		<img src="image/logo.png" class="avatar">
		<form method="post" action="{{ route('user.login.submit') }}">
		<input type="hidden" name="_token" value="{{ csrf_token() }}">
			<h1>Finpro </h1>
			<p>Email</p>
			<input type="text" name="email" placeholder="Masukan Email">			
			<input type="submit" name="LOGIN" value="Reset Password ke Email">
		</form>
	</div>
	</div>
</body>
</html>