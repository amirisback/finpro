{!! Form::model($model, [
    'route' => ['dosen.informasi.tambah-ubah.ubah', $model->informasi_id],
    'method' => 'PUT'
]) !!}
    <div class="form-group">
        <label for="" class="control-label">Judul Informasi</label>
        {!! Form::text('informasi_judul', null, ['class' => 'form-control', 'id' => 'informasi_judul']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Detail Informasi</label>
        {!! Form::textarea('informasi_isi', null, ['class' => 'form-control', 'id' => 'informasi_isi']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Waktu Publish</label>
        {!! Form::text('informasi_waktu', null, ['class' => 'form-control', 'id' => 'informasi_waktu', 'disabled' => 'disabled']) !!}
    </div>


{!! Form::close() !!}
