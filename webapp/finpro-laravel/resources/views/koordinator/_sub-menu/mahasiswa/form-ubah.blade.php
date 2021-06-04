{!! Form::model($model, [
    'route' => ['koordinator.mahasiswa.ubah', $model->mhs_nim],
    'method' => 'put'

]) !!}
    
    <div class="form-group">
        <label for="" class="control-label">Nama Lengkap</label>
        {!! Form::text('mhs_nama', null, ['class' => 'form-control', 'id' => 'mhs_nama']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Nim </label>
        {!! Form::text('mhs_nim', null, ['class' => 'form-control', 'id' => 'mhs_nim', 'disabled' => 'disabled']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Angkatan</label>
        {!! Form::text('angkatan', null, ['class' => 'form-control', 'id' => 'angkatan']) !!}
    </div>

{!! Form::close() !!}
