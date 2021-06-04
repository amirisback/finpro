<!-- Sidebar Holder -->
<nav id="sidebar">
    <div class="sidebar-header">
        <p>Mahasiswa</p>
    </div>
    <ul class="list-unstyled components">
        <img src="{{URL::asset('/image/mhs_img/'. Session::get('session_gambar') )}}" alt="mahasiswa">
        <!--code php-->
        <p>Nama :</p>
        <p><b>{{Session::get('session_nama')}} </b></p>
        <!--code php-->
        <br>
        <p>NIM :</p>
        <p><b>{{Session::get('session_nim')}}</b></p>
        <!--code php-->
        <li class="{{ url()->current()==url('/mahasiswa/beranda') || url()->current()==url('/mahasiswa')?'active':'' }}">
            <a href="{{route('mahasiswa.beranda')}}"><i class="glyphicon glyphicon-home iconpadding"></i>Beranda</a>
        </li>
        <li> <!--  Profile ? -->
        @if(url()->current()==url('/mahasiswa/profile/biodata') || url()->current()==url('/mahasiswa/profile/foto') || url()->current()==url('/mahasiswa/profile/ubah-password'))
            <a href="#profileSubmenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-user iconpadding"></i>Profile</a>
            <ul class="collapse in list-unstyled" id="profileSubmenu">  
        @else
            <a href="#profileSubmenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-user iconpadding"></i>Profile</a>
            <ul class="collapse list-unstyled" id="profileSubmenu">
        @endif
                <li class="{{ url()->current()==url('/mahasiswa/profile/biodata')?'active':'' }}"><a href="{{route('mahasiswa.profil.biodata')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Edit Profile</a></li>
                <li class="{{ url()->current()==url('/mahasiswa/profile/ubah-password')?'active':'' }}"><a href="{{route('mahasiswa.profil.password')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Edit Password </a></li>
                <li class="{{ url()->current()==url('/mahasiswa/profile/foto')?'active':'' }}"><a href="{{route('mahasiswa.profil.foto')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Edit Foto </a></li>
            </ul>
        </li>        
        <li> <!--  Judul PA ? -->
        @if(url()->current()==url('/mahasiswa/judul/lihat') || url()->current()==url('/mahasiswa/judul/arsip') || url()->current()==url('/mahasiswa/judul/tim'))
            <a href="#judulSubmenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-list iconpadding"></i>Judul Proyek Akhir</a>
            <ul class="list-unstyled collapse in active" id="judulSubmenu">
        @else
            <a href="#judulSubmenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-list iconpadding"></i>Judul Proyek Akhir</a>
            <ul class="collapse list-unstyled" id="judulSubmenu">
        @endif
            @if(!empty($jdl_status))
                <li class="{{ url()->current()==url('/mahasiswa/judul/tim')?'active':'' }}"><a href="{{route('mahasiswa.judul.tim')}}"><i class="glyphicon glyphicon-ok-sign iconpadding"></i>Judul Tim</a></li>
            @endif
                <li class="{{ url()->current()==url('/mahasiswa/judul/lihat')?'active':'' }}"><a href="{{route('mahasiswa.judul.lihat')}}"><i class="glyphicon glyphicon-list iconpadding"></i>Daftar Judul</a></li>
                <li class="{{ url()->current()==url('/mahasiswa/judul/arsip')?'active':'' }}"><a href="{{route('mahasiswa.judul.arsip')}}"><i class="glyphicon glyphicon-file iconpadding"></i>Arsip Judul </a></li>
            </ul>
        </li>
      
        <li class="{{ url()->current()==url('/mahasiswa/bimbingan')?'active':'' }}">
            <a href="{{route('mahasiswa.bimbingan')}}"><i class="glyphicon glyphicon-stats iconpadding"></i>Bimbingan</a>
        </li>
        <li>
            @if(url()->current()==url('/mahasiswa/nilai/monev') || url()->current()==url('/mahasiswa/nilai/sidang'))
                <a href="#nilaiSubMenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Nilai</a>
                <ul class="list-unstyled collapse in active" id="nilaiSubMenu">
            @else
                <a href="#nilaiSubMenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Nilai</a>
                <ul class="collapse list-unstyled" id="nilaiSubMenu">
            @endif
                    <li class="{{ url()->current()==url('/mahasiswa/nilai/monev')?'active':'' }}"><a href="{{route('mahasiswa.nilai.monev')}}"><i class="glyphicon glyphicon-check iconpadding"></i>Monev</a></li>
                    <li class="{{ url()->current()==url('/mahasiswa/nilai/sidang')?'active':'' }}"><a href="{{route('mahasiswa.nilai.sidang')}}"><i class="glyphicon glyphicon-education iconpadding"></i>Sidang</a></li>
                </ul>
        </li>    
    </ul>
</nav>
