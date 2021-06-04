

<a>Pembimbing</a>&emsp;&emsp;&emsp;<strong>:  {{ $model->tbl_judul->tbl_dosen->dsn_nama }}</strong> <br>
<a>Judul Proyek</a>&emsp;&emsp;&emsp;<strong> : {{ $model->tbl_judul->judul_nama }}</strong>
<br><br>

<table id="datatable" class="table table-hover" style="width:100%">
    <thead class="thead-light">
       
        <tr>
            <th scope="col">No</th>
            <th scope="col">Kategori Monev</th>                        
            <th scope="col">Nilai</th>
            <th scope="col">Tanggal</th>
        </tr>
    </thead>
    <tbody>
         
            @forelse($model->tbl_proyek_akhir as $proyek)
            <tr>    
                @forelse($proyek->tbl_detail_monev as $index => $nilai)
                    <td>{{ $index+1 }}</td>
                    <td>{{ $nilai->tbl_monev->monev_kategori }}</td>
                    @if(!is_null($nilai->monev_nilai))
                        <td><b>{{$nilai->monev_nilai}}</b></td>
                    @else
                        <td><i>kosong</i></td>
                    @endif
                    <td>{{ $nilai->monev_tanggal}}</td>
            </tr>
                @empty
                @endforelse
            @empty
            @endforelse
        
    </tbody>
</table>