<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<title>login</title>
	<link rel="stylesheet"  href="/css/login.css" >
	<link rel="shortcut icon title" href="{{{ asset('image/title.png') }}}">
</head>

<body>
	<div class="loginbox">
		<img src="image/logo.png" class="avatar">
		<form method="post" action="{{ route('user.login.submit') }}">
		<input type="hidden" name="_token" value="{{ csrf_token() }}">
			<h1>Finpro </h1>
			<p>Username</p>
			<input type="text" name="username" placeholder="Enter Username">
			<p>Password</p>
			<input type="password" name="password" placeholder="Enter Password">
			<input type="submit" name="LOGIN" value="Login">
			<a href="{{ route('user.lupa.password')}}"> Forget password ?</a><br>
			<p>
				<a href="Login.php"></a>
			</p>
		</form>
	</div>
	</div>
</body>

</html>