<table class="table table-striped">
    <thead>     
    <tr>
        <th>No</th>
        <th>Bimbingan</th>
        <th>Topik</th>
        <th>Kehadiran</th>
        <th>Tanggal</th>
    </tr>
    </thead>
    <tbody>     
        @forelse($model->tbl_bimbingan as $index => $bimbingan)
            <tr>
                <th>{{ $index+1 }}</td>
                <td>Bimbingan Ke-{{ $index+1 }}</td>
                <td>{{ $bimbingan->bimbingan_review }}</td>
                <td>{{ $bimbingan->bimbingan_kehadiran }}</td>
                <td>{{ $bimbingan->bimbingan_tanggal}}</td>
            </tr>
        @empty
            <th>kosong</th>
        @endforelse
    </tbody>
</table>