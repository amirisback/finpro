
<table class="table table-striped">
            <tbody>                                      
                <tr>
                    <td>Judul Proyek</td>
                    <td><strong>{{ $model->judul_nama }}</strong></td>
                </tr>

                <tr>
                    <td>Kategori</td>
                    <td><strong>{{ $model->tbl_kategori_judul->kategori_nama }}</strong></td>
                </tr>
                <tr>
                    <td>Deskripsi Judul</td>
                    <td><strong>{{ $model->judul_deskripsi }}</strong></td>
                </tr>
                <tr>
                    <td>Dosen Pembimbing</td>
                    <td><strong>{{ $model->tbl_dosen->dsn_nama }}</strong></td>                    
                </tr>
                @if($model->judul_status  === "tersedia" || $model->judul_status  === "pending")
                    <tr>
                        <td>Reviewer</td>
                        <td><strong> - </strong></td>                    
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td><i class="text-success">TERSEDIA</i></td>
                    </tr>
                @else
                    <tr>
                        <td>Reviewer</td>
                        @if($proyekAkhir->tbl_dosen)
                            <td><strong>{{ $proyekAkhir->tbl_dosen->dsn_nama }}</strong></td>
                        @endif
                            <td><strong>-</strong></td>
                    </tr>
                    <tr>
                        <td>Nama Tim</td>
                        @php $namaTim @endphp
                        @foreach($model->tbl_proyek_akhir as $proyek)

                        @if($model->judul_id === $proyek->judul_id )    
                            @if(!is_null($proyek->nama_tim))
                                @php 
                                $namaTim = '<td><strong>'. $proyek->nama_tim.'</strong></td>';
                                @endphp
                            @else
                                @php 
                                $namaTim = '<td><i class="text-danger">kosong</i></td>';
                                @endphp
                            @endif

                        @else
                        @endif

                        @endforeach
                        @php echo $namaTim; @endphp
                    </tr>
                    <tr>
                        <td>Anggota</td>   
                        <td><strong>                 
                        @foreach($model->tbl_proyek_akhir as $proyek)
                            @if(!is_null($proyek->mhs_nim))
                                {{  $proyek->mhs_nim.' ('.$proyek->tbl_mahasiswa->mhs_nama.')'}} <br>
                            @else
                                <i class="text-danger">kosong</i>
                            @endif
                        @endforeach
                        </strong></td>
                    </tr>   
                @endif                           
            </tbody>
        </table>