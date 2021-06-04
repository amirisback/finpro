@extends('layout.app-mhs')
    @section('title')
        Daftar Judul PA
    @endsection

    <!-- Body -->
        @section('top-bar')
            Daftar Judul Proyek Akhir
        @endsection


        <!-- Page Content Holder -->
        @section('content')
            <div id="content">
                <h2>Detail Pengajuan Judul Tersedia</h2>
                <div class="lin-title">
            
                    <table class="table table-striped">
                        <tbody>                                        
                        @forelse($detailJudul as $index => $value)
                            <form action="{{ route('mahasiswa.judul.ajukan.tersedia', ['id' => $getIdJudul])}}" method="POST">
                            {{ csrf_field() }}
                                <input name="_method" type="hidden" value="PUT">  
                            <tr>
                                <td>Judul</td>
                                <td>
                                    <div class="md-form mb-5">
                                        <input type="text" id="" class="form-control validate" placeholder="input Nama Team" name="nama_judul" value="{{$value->judul}}">
                                    </div>
                                </td>                                
                            </tr>

                            <tr>
                                <td>Kategori</td>
                                <td>
                                    <div class="md-form mb-5">
                                        <input type="text" id="" class="form-control validate" placeholder="input Nama Team" name="nama_kategori" value="{{$value->kategori}}">
                                    </div>
                                </td>     
                            </tr>
                            <tr>
                                <td>Deskripsi</td>
                                <td>
                                    <div class="md-form mb-5">
                                        <input type="text" id="" class="form-control validate" placeholder="input Nama Team" name="nama_deskripsi" value="{{ $value->deskripsi }}">
                                    </div>
                                </td>                                
                            </tr>
                            <tr>
                                <td>Pembimbing</td>
                                <td>
                                    <div class="md-form mb-5">
                                        <input type="text" name="nama_pembimbing" id="" class="form-control validate" placeholder="input Nama Mahasiswa-2" value="{{$value->nip_dosen}}">
                                    </div>
                                </td>
                                
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td><strong>{{$value->status}}</strong></td>
                            </tr>
                            <tr>
                                <td>Nama Tim / Kelompok</td>
                                <td>
                                    <div class="md-form mb-5">
                                        <input type="text" name="" id="" class="form-control validate" placeholder="input Nama Team" name="nama_tim">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Mahasiswa 1</td>
                                <td>
                                    <div class="md-form mb-5">
                                        <input type="text" id="" class="form-control validate" placeholder="input Nama Team" name="nama_mhs1" value="{{Session::get('session_nim')}}">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Mahasiswa 2</td>
                                <td>
                                    <div class="md-form mb-5">
                                        <input type="text" name="nama_mhs2" id="" class="form-control validate" placeholder="input Nama Mahasiswa-2">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Jumlah Kelompok pengajuan judul sama</td>
                                <td><strong>5 (statis)</strong></td>
                            </tr>
                            <a href="{{ route('mahasiswa.judul.lihat')}}" class="btn btn-info">Kembali</a>                        
                    <input type="submit" name="submit" value="Ajukan"  class="btn btn-success">
                    
                </form>
                        @empty
                            <td colspan='2' class='text-center'>kosong</td>
                        @endforelse     
                        </tbody>
                    </table>
                    
                </div>
            </div>
            
        @endsection


