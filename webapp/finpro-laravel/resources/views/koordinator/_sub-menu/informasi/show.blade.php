
<table class="table table-striped">
    <tbody>                    
        <tr>                    
            <td colspan="2"  class="text-center"><strong> <img src="{{URL::asset('/image/dsn_img/'. $model->dsn_foto ) }}" width="200" height="200"></td></strong></td>
        </tr>                    
        <tr>
            <td>Nama Lengkap</td>
            <td><strong>{{ $model->dsn_nama }}</strong></td>
        </tr>

        <tr>
            <td>NIP</td>
            <td><strong>{{ $model->dsn_nip }}</strong></td>
        </tr>
        <tr>
            <td>Kode Dosen</td>
            <td><strong>{{ $model->dsn_kode }}</strong></td>
        </tr>
        <tr>
            <td>Kontak</td>
            @if(!is_null($model->dsn_kontak))
                <td><strong>{{ $model->dsn_kontak }}</strong></td>
            @else
                <td><i class="text-danger">Belum Diisi</i></td>
            @endif
        </tr>
        <tr>
            <td>Email Dosen</td>
                @if(!is_null($model->dsn_email))
                <td><strong>{{ $model->dsn_email}}</strong></td>                    
                @else
                <td><i class="text-danger">Belum Diisi</i></td>
                @endif                    
        </tr>
    </tbody>
</table>