<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <title>@yield('title')</title>

    
    <link rel="shortcut icon title" href="{{{ asset('image/title.png') }}}">

    <!-- Bootstrap CSS CDN -->
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    
    
    <!-- Our Custom CSS -->
    <link href="{{ asset('css/side.css') }}" rel="stylesheet" type="text/css">
    <link href="{{ asset('css/content.css') }}" rel="stylesheet" type="text/css">

    <!-- Datatables -->
    <link href="{{ asset('assets/vendor/datatables/datatables.min.css') }}" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link href="{{ asset('assets/vendor/font-awesome/css/font-awesome.min.css') }}" rel="stylesheet">

    <!-- icon costums -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    
</head>

<body>
    <div class="wrapper">
        @include('layout._top-bar')

        @include('layout._sidebar-koor')

        @yield('content')
    </div>

  
   
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!-- <script src="{{ asset('assets/vendor/jquery/jquery.min.js') }}"></script>
        <script src="{{ asset('assets/vendor/bootstrap/js/bootstrap.min.js') }}"></script> -->

        <!-- Datatables -->
        <script src="{{ asset('assets/vendor/datatables/datatables.min.js') }}"></script>

        <!-- Sweetalert2 -->
        <script src="{{ asset('assets/vendor/sweetalert2/sweetalert2.all.min.js') }}"></script>

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="{{ asset('assets/js/ie10-viewport-bug-workaround.js') }}"></script>

        <script type="text/javascript" src="{{ asset('js/app.js') }}"></script>

        @stack('scripts')

        @include('layout._footer')
    </body>
</html>
