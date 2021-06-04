  <!-- Sidebar Holder -->
  <nav id="sidebar">
    <div class="sidebar-header">
        <p>Dosen</p>
    </div>
    <ul class="list-unstyled components">
        <img src="{{URL::asset('/image/dsn_img/'. Session::get('session_gambar') )}}" alt="admin">
        <!--code php-->
        <p>Nama :</p>
        <p><b>{{Session::get('session_nama')}} </b></p>
        <!--code php-->
        <br>
        <p>NIP</p>
        <p><b>{{Session::get('session_nip')}}</b></p>
        <!--code php-->
        <li class="{{ url()->current()==url('/dosen/beranda') || url()->current()==url('/dosen')?'active':'' }}">
            <a href="{{route('dosen.beranda')}}"><i class="glyphicon glyphicon-home iconpadding"></i>Beranda</a>
        </li>
        <li>
            @if(url()->current()==url('/dosen/profile/biodata') || url()->current()==url('/dosen/profile/ubah-password') || url()->current()==url('/dosen/profile/foto'))
                <a href="#profilSubmenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-user iconpadding"></i>Profile</a>
                <ul class="list-unstyled collapse in active" id="profilSubmenu">
            @else
                <a href="#profilSubmenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-user iconpadding"></i>Profile</a>
                <ul class="collapse list-unstyled" id="profilSubmenu">
            @endif
                    <li class=" {{ url()->current()==url('/dosen/profile/biodata')?'active':'' }}"><a href="{{route('dosen.profil.biodata')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Edit Profile</a></li>
                    <li class=" {{ url()->current()==url('/dosen/profile/ubah-password')?'active':'' }}"><a href="{{route('dosen.profil.password')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Edit Password</a></li>
                    <li class=" {{ url()->current()==url('/dosen/profile/foto')?'active':'' }}"><a href="{{route('dosen.profil.foto')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Edit Foto</a></li>
                </ul>
        </li>
        <li>
            @if(url()->current()==url('/dosen/informasi/lihat') || url()->current()==url('/dosen/informasi/tambah-ubah'))
                <a href="#artikelSubmenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Informasi</a>
                <ul class="list-unstyled collapse in active" id="artikelSubmenu">
            @else
                <a href="#artikelSubmenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Informasi</a>
                <ul class="collapse list-unstyled" id="artikelSubmenu">
            @endif
                    <li class="{{ url()->current()==url('/dosen/informasi/lihat')?'active':'' }}"><a href="{{route('dosen.informasi.lihat')}}"><i class="glyphicon glyphicon-info-sign iconpadding"></i>Lihat Informasi</a></li>
                    <li class=" {{ url()->current()==url('/dosen/informasi/tambah-ubah')?'active':'' }}"><a href="{{route('dosen.informasi.tambah-ubah')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Tambah Informasi</a></li>
                    
                </ul>
        </li>
        <li>
            @if(url()->current()==url('/dosen/judul-proyek-akhir/aktif') || url()->current()==url('/dosen/judul-proyek-akhir/arsip'))
                <a href="#judulSubmenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Judul Proyek Akhir</a>
                <ul class="list-unstyled collapse in active" id="judulSubmenu">
            @else
                <a href="#judulSubmenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Judul Proyek Akhir</a>
                <ul class="collapse list-unstyled" id="judulSubmenu">
            @endif
                    <li class=" {{ url()->current()==url('/dosen/judul-proyek-akhir/aktif')?'active':'' }}"><a href="{{ route('dosen.judul.aktif') }}"><i class="glyphicon glyphicon-info-sign iconpadding"></i>Judul Aktif</a></li>
                    <li class=" {{ url()->current()==url('/dosen/judul-proyek-akhir/arsip')?'active':'' }}"><a href="{{ route('dosen.judul.arsip') }}"><i class="glyphicon glyphicon-edit iconpadding"></i>Judul Arsip</a></li>
                </ul>
        </li>


        <li class="{{ url()->current()==url('/dosen/bimbingan')?'active':'' }}">
            <a href="{{route('dosen.bimbingan')}}"><i class="glyphicon glyphicon-stats iconpadding"></i>Bimbingan</a>
        </li>
        <li class="{{ url()->current()==url('/dosen/monev')?'active':'' }}">
            <a href="{{route('dosen.monev')}}"><i class="glyphicon glyphicon-check iconpadding"></i>Monev</a>
        </li>
        <li class="{{ url()->current()==url('/dosen/sidang')?'active':'' }}">
            <a href="{{route('dosen.sidang')}}"><i class="glyphicon glyphicon-education iconpadding"></i>Sidang</a>
        </li>
    </ul>
</nav>
