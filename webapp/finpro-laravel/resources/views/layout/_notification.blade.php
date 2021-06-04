<ul class="dropdown-menu">
        <li class="head text-light bg-dark">
            <div class="row">
                <div class="col-lg-12 col-sm-12 col-12">
                    <span>Notifikasi ({{ $jml_notif }})</span>            
                </div>    
            </div>
        </li>
        @forelse($notifikasi as $index => $notif)
            <li class="notification-box">
                <div class="row">
                    <div class="col-lg-3 col-sm-3 col-3 text-center">
                        @if(preg_match('/\btolak\b/', $notif->notifikasi_kategori))
                        <a>tolakkk</a>
                        @elseif(preg_match('/\bterima\b/', $notif->notifikasi_kategori))
                        <a>terimaaa</a>
                        @elseif(preg_match('/\bsetuju\b/', $notif->notifikasi_kategori))
                        <a>setujuuu</a>

                        <!-- MULAIIIII -->

                        <!-- PERMOHONAN JUDUL -->
                        @elseif(preg_match('/\bGabung TIM\b/', $notif->notifikasi_kategori))
                            <i class="fa fa-user-plus fa-4x text-primary"></i>
                        @elseif(preg_match('/\bPengajuan Judul\b/', $notif->notifikasi_kategori) && Session::get('session_pengguna') === 'mahasiswa')
                            <i class="fa fa fa-hourglass fa-4x text-primary"></i>
                        @elseif(preg_match('/\bPengajuan Judul\b/', $notif->notifikasi_kategori) && Session::get('session_pengguna') === 'dosen')
                            <i class="fas fa-calendar-plus fa-4x text-primary"></i>

                        <!-- BIMBINGAN -->
                        @elseif(preg_match('/\bpengajuan Bimbingan\b/', $notif->notifikasi_kategori))
                            <i class="fa fa-bold fa-4x text-primary" ></i>

                        <!-- Nilai Dan Ubah Nilai -->
                        @elseif((preg_match('/\bNilai\b/', $notif->notifikasi_kategori) && preg_match('/\bDitambahkan\b/', $notif->notifikasi_kategori)) || (preg_match('/\bNilai\b/', $notif->notifikasi_kategori) && preg_match('/\bDiinputkan\b/', $notif->notifikasi_kategori)))
                            <i class="glyphicon glyphicon-certificate fa-4x text-primary" ></i>
                        @elseif(preg_match('/\bDiubah\b/', $notif->notifikasi_kategori) || preg_match('/\bPerubahan\b/', $notif->notifikasi_kategori))
                            <i class="fa fa-refresh fa-4x text-primary" ></i>

                        <!-- ACC / Penolakan Semua -->
                        @elseif(preg_match('/\bPenolakan\b/', $notif->notifikasi_kategori))
                            <center><i class="fas fa-calendar-times fa-4x text-danger" ></i></center>
                        @elseif(preg_match('/\bDiterima\b/', $notif->notifikasi_kategori) || preg_match('/\bdiACC\b/', $notif->notifikasi_kategori))
                            <i class="fas fa-calendar-check fa-4x text-success" ></i>      
                        
                        <!-- Reviewer -->
                        @elseif(preg_match('/\bPemetaan\b/', $notif->notifikasi_kategori) && preg_match('/\bReviewer\b/', $notif->notifikasi_kategori))
                            <i class="glyphicon glyphicon-link fa-4x text-primary" ></i>    

                        <!-- Informasi -->
                        @elseif(preg_match('/\bInformasi\b/', $notif->notifikasi_kategori))
                            <i class="glyphicon glyphicon-bullhorn fa-4x text-primary" ></i>                    

                        <!-- PEMETAAN JUDUL -->
                        @elseif(preg_match('/\bPemetaan Judul\b/', $notif->notifikasi_kategori))
                            <i class="glyphicon glyphicon-pushpin fa-4x text-primary" ></i> 
                        <!-- FINISH -->
                        @endif
                    </div>    
                    <div class="col-lg-8 col-sm-8 col-8">
                        <strong class="text-info">{{ $notif->notifikasi_dari }}</strong>
                        <div>
                            {{ $notif->notifikasi_kategori }}
                        </div>
                        <small class="text-warning">{{ $notif->notifikasi_tanggal }}</small>
                    </div>    
                </div>
            </li>
        @empty
            <li class="notification-box">
                <div class="row">
                    <div class="col-lg-12 col-sm-12 col-12">
                        <strong class="text-info"><center>Notifikasi Kosong</center></strong>
                    </div>    
                </div>
            </li>
        @endforelse
      
        <li class="footer bg-dark text-center">
        @if(Session::get('session_pengguna') === 'mahasiswa')
            <a href="{{ route('mahasiswa.notifikasi.lihat', Session::get('session_nim')) }}" class="text-light"><span>Lihat Semua Notifikasi</span></a>
        @elseif(Session::get('session_pengguna') === 'dosen')
            <a href="{{ route('dosen.notifikasi.lihat', Session::get('session_nip')) }}" class="text-light"><span>Lihat Semua Notifikasi</span></a>
        @elseif(Session::get('session_pengguna') === 'koordinator')
            <a href="{{ route('koordinator.notifikasi.lihat', Session::get('session_nip')) }}" class="text-light"><span>Lihat Semua Notifikasi</span></a>
        @endif
        </li>
    </ul>