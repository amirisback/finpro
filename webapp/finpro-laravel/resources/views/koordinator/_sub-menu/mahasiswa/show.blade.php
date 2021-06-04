
<table class="table table-striped">
            <tbody>                    
                <tr>                    
                    <td colspan="2"  class="text-center"><strong> <img src="{{URL::asset('/image/mhs_img/'. $model->mhs_foto ) }}" width="200" height="200"></td></strong></td>
                </tr>                    
                <tr>
                    <td>Nama Lengkap</td>
                    <td><strong>{{ $model->mhs_nama }}</strong></td>
                </tr>

                <tr>
                    <td>Nim</td>
                    <td><strong>{{ $model->mhs_nim }}</strong></td>
                </tr>
                <tr>
                    <td>Angkatan</td>
                    <td><strong>{{ $model->angkatan }}</strong></td>
                </tr>
                <tr>
                    @if(!is_null($model->mhs_email))
                    <td>Email</td>
                    <td><strong>{{ $model->mhs_email }}</strong></td>
                    @else
                        <td>Email</td>
                        <td><i class="text-danger">Belum Diisi</i></td>
                    @endif
                </tr>
                <tr>
                    <td>Nama Tim</td>
                        @if(!is_null($model->id_judul))
                        <td><strong>{{ $model->tbl_proyek_akhir->nama_tim}}</strong></td>                    
                        @else
                        <td><i class="text-danger">Belum Diisi</i></td>
                        @endif                    
                </tr>
                <tr>  
                    <td>Kontak</td>
                    @if(!is_null($model->mhs_kontak))
                    <td><strong>{{ $model->mhs_kontak}}</strong></td>                    
                    @else
                    <td><i class="text-danger">Belum Diisi</i></td>
                    @endif
                </tr>
                <tr>                    
                </tr>                              
            </tbody>
        </table>