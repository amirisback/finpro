     <!-- Sidebar Holder -->
        <nav id="sidebar">
            <div class="sidebar-header">
                <p>Koordinator</p>
            </div>

            <ul class="list-unstyled components">
                <img src="{{URL::asset('/image/koor_img/'. Session::get('session_gambar') )}}" alt="koordinator">
                <!--code php--> 

                <p>Nama :</p>
                <!--  -->
                <p><b>{{Session::get('session_nama')}}</b></p>
                <!--code php-->
                <br>
                <p>NIP</p>
                <p><b>{{Session::get('session_nip')."(".Session::get('session_kode').")"}}</b></p>
                <!--code php-->
                <li class="{{ url()->current()==url('/koordinator')?'active':'' || url()->current()==url('/koordinator/beranda')?'active':''}}">
                <a href="{{ url('/koordinator') }}"><i class="glyphicon glyphicon-home iconpadding"></i>Beranda</a>
                </li>
                <li> <!--  Profile ? -->
                @if(url()->current()==url('/koordinator/profile/foto') || url()->current()==url('/koordinator/profile/biodata') || url()->current()==url('/koordinator/profile/ubah-password'))
                    <a href="#profileSubmenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-user iconpadding"></i>Profile</a>
                    <ul class="collapse in list-unstyled" id="profileSubmenu">  
                @else
                    <a href="#profileSubmenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-user iconpadding"></i>Profile</a>
                    <ul class="collapse list-unstyled" id="profileSubmenu">
                @endif
                        <li class="{{ url()->current()==url('/koordinator/profile/biodata')?'active':'' }}"><a href="{{route('koordinator.profil.biodata')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Edit Profile</a></li>
                        <li class="{{ url()->current()==url('/koordinator/profile/ubah-password')?'active':'' }}"><a href="{{route('koordinator.profil.password')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Edit Password </a></li>
                        <li class="{{ url()->current()==url('/koordinator/profile/foto')?'active':'' }}"><a href="{{route('koordinator.profil.foto')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Edit Foto </a></li>
                    </ul>
                </li>
                <li>
                    @if(url()->current()==url('/koordinator/informasi/lihat') || url()->current()==url('/koordinator/informasi/ubah'))
                        <a href="#artikelSubmenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Informasi</a>
                        <ul class="list-unstyled collapse in active" id="artikelSubmenu">
                    @else
                        <a href="#artikelSubmenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Informasi</a>
                        <ul class="collapse list-unstyled" id="artikelSubmenu">
                    @endif
                            <li class=" {{ url()->current()==url('/koordinator/informasi/lihat')?'active':'' }}"><a href="{{route('koordinator.informasi.lihat')}}"><i class="glyphicon glyphicon-info-sign iconpadding"></i>Lihat Informasi</a></li>
                            <li class=" {{ url()->current()==url('/koordinator/informasi/ubah')?'active':'' }}"><a href="{{route('koordinator.informasi.ubah')}}"><i class="glyphicon glyphicon-edit iconpadding"></i>Tambah Informasi</a></li>
                        </ul>
                </li>
                <li>
                    @if(url()->current()==url('/koordinator/judul-proyek-akhir/aktif') || url()->current()==url('/koordinator/judul-proyek-akhir/arsip'))
                        <a href="#judulSubmenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Judul Proyek Akhir</a>
                        <ul class="list-unstyled collapse in active" id="judulSubmenu">
                    @else
                        <a href="#judulSubmenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-list-alt iconpadding"></i>Judul Proyek Akhir</a>
                        <ul class="collapse list-unstyled" id="judulSubmenu">
                    @endif
                            <li class=" {{ url()->current()==url('/koordinator/judul-proyek-akhir/aktif')?'active':'' }}"><a href="{{ route('koordinator.judul.aktif') }}"><i class="glyphicon glyphicon-info-sign iconpadding"></i>Judul Aktif</a></li>
                            <li class=" {{ url()->current()==url('/koordinator/judul-proyek-akhir/arsip')?'active':'' }}"><a href="{{ route('koordinator.judul.arsip') }}"><i class="glyphicon glyphicon-edit iconpadding"></i>Judul Arsip</a></li>
                        </ul>
                </li>
                <li class="{{ url()->current()==url('/koordinator/monev')?'active':'' }}">
                    <a href="{{route('koordinator.monev')}}"><i class="glyphicon glyphicon-check iconpadding"></i>Monev</a>
                </li>   
                <li class="{{ url()->current()==url('/koordinator/sidang')?'active':'' }}">
                    <a href="{{route('koordinator.sidang')}}"><i class="glyphicon glyphicon-check iconpadding"></i>Sidang</a>
                </li>   


                <li>
                    @if(url()->current()==url('/koordinator/pengguna/pembimbing') || url()->current()==url('/koordinator/pengguna/reviewer') || url()->current()==url('/koordinator/pengguna/penguji') || url()->current()==url('/koordinator/pengguna/mahasiswa'))
                        <a href="#userSubmenu" data-toggle="collapse" aria-expanded="true"><i class="glyphicon glyphicon-user iconpadding"></i>User</a>
                        <ul class="list-unstyled collapse in active" id="userSubmenu">
                    @else
                        <a href="#userSubmenu" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-user iconpadding"></i>User</a>
                        <ul class="collapse list-unstyled" id="userSubmenu">
                    @endif
                        <li class="{{ url()->current()==url('/koordinator/pengguna/pembimbing')?'active':'' }}"><a href="{{route('koordinator.pengguna.pembimbing')}}"><i class="glyphicon glyphicon-user iconpadding"></i>Pembimbing</a></li>
                        <li class="{{ url()->current()==url('/koordinator/pengguna/reviewer')?'active':'' }}"><a href="{{route('koordinator.pengguna.reviewer')}}"><i class="glyphicon glyphicon-user iconpadding"></i>Reviewer</a></li>
                        <li class="{{ url()->current()==url('/koordinator/pengguna/mahasiswa')?'active':'' }}"><a href="{{route('koordinator.pengguna.mahasiswa')}}"><i class="glyphicon glyphicon-user iconpadding"></i>Mahasiswa</a></li>
                        </ul>
                </li>
            </ul>
        </nav>