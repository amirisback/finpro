
<table class="table table-striped">
    <tbody>                                     
        <tr>
            <td>Judul</td>
            <td><strong>{{ $model->tbl_judul->judul_nama }}</strong></td>
        </tr>
        <tr>
            <td>Pembimbing</td>
            <td><strong>{{ $model->tbl_judul->tbl_dosen->dsn_nama }}</strong></td>
        </tr>
        <tr>
            <td>Nama Tim</td>
            <td><strong>{{ $model->nama_tim }}</strong></td>
        </tr>
        <tr>
            <td>Anggota</td>
            <td><strong>
            @foreach($mahasiswa as $index => $mhs)
                {{ ($index+1).'. '.$mhs->tbl_mahasiswa->mhs_nama }}<br>
            @endforeach
            </strong></td>
        </tr>
        <tr>
            <td>Reviewer</td>
            @if(!is_null($model->dsn_nip))
                <td><strong>{{ $model->tbl_dosen->dsn_nama}}</strong></td>                
            @else
                <td><i class="text-danger">kosong</i></td>
            @endif   
        </tr>
    </tbody>
</table>