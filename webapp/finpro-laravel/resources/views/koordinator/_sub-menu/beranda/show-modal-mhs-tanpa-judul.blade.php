<table id="datatable" class="table table-hover" style="width:100%">
    <thead class="thead-light">
        <tr>
            
            <th scope="col">Nama Mahasiswa</th>                        
            <th scope="col">Kontak</th>
        </tr>
    </thead>
    <tbody>
        @forelse($model as $index => $mhs)
            <tr>
                
                <td>{{$mhs->mhs_nama}}</td>
                @if(!is_null($mhs->mhs_kontak))
                    <td><b>{{$mhs->mhs_kontak}}</b></td>
                @else
                    <td><i>kosong</i></td>
                @endif
            </tr>
        @empty
        @endforelse
    </tbody>
</table>