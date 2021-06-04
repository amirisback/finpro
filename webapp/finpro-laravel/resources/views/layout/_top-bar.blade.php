 <!-- Top-Bar -->
 <div class="top-bar">
    <div class="navbar-header"> 
        <button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
            <i class="glyphicon glyphicon-align-left"></i>
            <span>Toggle Side</span>
        </button>
        <h2>@yield('top-bar')</h2>
        
        @if(Session::get('session_pengguna') == "mahasiswa")
            <button  type="button" class="btn btn-logout navbar-btn" onclick="location.href='/mahasiswa/logout';">Logout</button>
            <a class="btn btn-read1 btn-info btn-notification navbar-btn" href="{{ route('mahasiswa.notifikasi.baca') }}" id="dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fa fa-bell fa-lg  text-default"></i> {{ $jml_notif_baca }}
            </a>
        @elseif(Session::get('session_pengguna') == "dosen")
            <button  type="button" class="btn btn-logout navbar-btn" onclick="location.href='/dosen/logout';" >Logout</button>
            <a class="btn btn-read1 btn-info btn-notification navbar-btn" href="{{ route('dosen.notifikasi.baca') }}" id="dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fa fa-bell fa-lg  text-default"></i> {{ $jml_notif_baca }}
            </a>
        @elseif(Session::get('session_pengguna' ) == "koordinator")
            <button  type="button" class="btn btn-logout navbar-btn" onclick="location.href='/koordinator/logout';" " >Logout</button>
            <a class="btn btn-read1 btn-info btn-notification navbar-btn" href="{{ route('koordinator.notifikasi.baca') }}" id="dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fa fa-bell fa-lg  text-default"></i> {{ $jml_notif_baca }}
            </a>
        @endif
        
        
        @include('layout._notification')
    </div>
</div>
<!-- END Top-Bar -->
