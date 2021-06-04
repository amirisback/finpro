<table class="table table-striped">
    <tbody> 
    @php $tim=""; @endphp
    @php $id=""; @endphp
    @foreach($judul as $proyekAkhir)                                  
        <tr>
            <td>Judul Proyek</td>
            <td><strong>{{ $proyekAkhir->judul_nama }}</strong></td>
            @php $id = $proyekAkhir->judul_id; @endphp
        </tr>
        <tr>
            <td>Kategori</td>
            <td><strong>{{ $proyekAkhir->tbl_kategori_judul->kategori_nama }}</strong></td>
        </tr>
        <tr>
            <td>Deskripsi Judul</td>
            <td><strong>{{ $proyekAkhir->judul_deskripsi}}</strong></td>
        </tr>
    @endforeach
        <tr>
            <td>Nama Tim</td>
            @foreach($modelDistinct as $proyek)
                @php $tim = $proyek->nama_tim @endphp
            @endforeach
            <td><strong>{{ $tim }}</strong></td>
        </tr>
        <tr>
            <td>Anggota Tim</td>
            <td><strong>
            @foreach($model as $index => $proyek)
            {!! ($index+1).'. '.$proyek->tbl_mahasiswa->mhs_nama.'<br>' !!}
            @endforeach
            </strong></td>
        </tr>
        
        <tr>
            <td>Aksi
            </td>
            <td>
            <a class="btn btn-success" href="{{route('dosen.judul.setuju', ['id'=>$id,'tim'=>$tim])}}" role="button">Setujui Pengajuan Judul</a>
            <a class="btn btn-danger" href="{{route('dosen.judul.tolak', ['id' => $id, 'tim' => $tim])}}" role="button">Tolak Pengajuan Judul</a></td>
        </tr>
    </tbody>
</table>