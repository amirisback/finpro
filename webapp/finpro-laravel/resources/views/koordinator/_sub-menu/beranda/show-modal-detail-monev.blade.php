
<p>Judul : <b>{{ $detail->tbl_judul->judul_nama }}</b></p>
<p>Pembimbing : <b>{{ $detail->tbl_judul->tbl_dosen->dsn_nama }}</b></p>
@forelse($detailMonev as $indeks => $monev)
    <div class="panel panel-pemberitahuan panel-default">
        Nama : <b>{{$monev->tbl_mahasiswa->mhs_nama}}</b><br>
        NIM : <b>{{$monev->tbl_mahasiswa->mhs_nim}}</b>
        <hr>
        <table id="datatable" class="table table-hover" style="width:100%">
            <thead class="thead-light">
                <tr>        
                    <th>Kategori Monev</th>
                    <th>Nilai</th>
                    <th>Tanggal</th>
                    <th>Review</th>
                </tr>
            </thead>                    
                
                @forelse($monev->tbl_detail_monev as $index => $nilaiMonev)
                <tr>
                    <td>{{ $nilaiMonev->tbl_monev->monev_kategori }}</td>
                    <td>{{ $nilaiMonev->monev_nilai }}</td>
                    <td>{{ $nilaiMonev->monev_tanggal }}</td>
                    <td>{{ $nilaiMonev->monev_ulasan }}</td>
                </tr>
                @empty
                    <td>nilai masih kosong</td>
                @endforelse
                <tr>
                </tr>
            <tbody>
            </tbody>
        </table>
    </div>
@empty
@endforelse