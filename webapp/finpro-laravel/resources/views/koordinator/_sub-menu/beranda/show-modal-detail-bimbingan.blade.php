
@forelse($detailBimbingan as $index => $bimbingan)
    <div class="panel panel-pemberitahuan panel-default">
        Nama : <b>{{$bimbingan->tbl_mahasiswa->mhs_nama}}</b><br>
        NIM : <b>{{$bimbingan->tbl_mahasiswa->mhs_nim}}</b>
        <hr>
        <table id="datatable" class="table table-hover" style="width:100%">
            <thead class="thead-light">
                <tr>                                              
                    <th>Bimbingan</th>
                    <th>Topik</th>
                    <th>Keterangan</th>
                </tr>
            </thead>
            @forelse($bimbingan->tbl_bimbingan as $indek => $bimbing)
                <tr>
                    <td>{{ "Bimbingan ke-".($indek+1) }}</td>
                    <td>{{ $bimbing->bimbingan_review }}</td>
                    <td>{{ $bimbing->bimbingan_kehadiran }}</td>
                </tr>

            @empty
                <tr>
                <td>Bimbingan Masih Kosong</td>
                </tr>
            @endforelse            
            <tbody>
            </tbody>
        </table>
    </div>

@empty
@endforelse
