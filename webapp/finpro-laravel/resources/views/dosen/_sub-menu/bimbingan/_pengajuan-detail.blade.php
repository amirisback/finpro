<table class="table table-striped">
    <tbody>     
    <tr>
    <td>Nama Anggota</td>
    <td>Kehadiran</td>
    </tr>
    
    {{ $temp = ""}}
    @foreach($model as $index => $mhs)
        <tr>
            <td><strong>{{ $mhs->tbl_mahasiswa->mhs_nama }}</strong></td>
            @foreach($mhs->tbl_bimbingan as $kehadiran)
                @php $temp = $kehadiran->bimbingan_kehadiran @endphp
            @endforeach
            <td><strong>{{ $temp }}</strong></td>
        </tr>
    @endforeach
        <td></td>
        <td>
            <strong><a href="{{ route('dosen.bimbingan.pengajuan.tolak', ['tim' => $tim]) }}" class="href btn btn-danger">Tolak Bimbingan</a></strong>
            <strong><a href="{{ route('dosen.bimbingan.pengajuan.setuju', ['tim' => $tim]) }}" class="href btn btn-success">Setujui</a></strong>
        </td>
    </tbody>
</table>