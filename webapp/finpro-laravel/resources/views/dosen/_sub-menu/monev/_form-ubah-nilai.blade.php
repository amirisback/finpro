{!! Form::model($model, [
    'route' => ['koordinator.reviewer.ubah', $model->nama_tim],
    'method' => 'PUT'

]) !!}
<!--  -->

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
            <td>
            <div class="form-group">
                <strong>
                    {!! Form::select('form_pilihPembimbing', $selectReviewer, $model->dsn_nip, ['class'=>'form-control']) !!}
                </strong>
            </div>
            </td>
        </tr>
    </tbody>
</table>
<p>apabila dosen reviewer <b>Diupdate</b>, maka identitas dosen reviewer yang memberi <b>nilai</b> sebelumnya akan di ganti dengan reviewer yang baru</p>
{!! Form::close() !!}
