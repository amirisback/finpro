{!! Form::model($model, [
    'route' => ['koordinator.pembimbing.ubah', $model->dsn_nip],
    'method' => 'PUT'

]) !!}
    
    <div class="form-group">
        <label for="" class="control-label">Nama Lengkap</label>
        {!! Form::text('dsn_nama', null, ['class' => 'form-control', 'id' => 'dsn_nama']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">NIP </label>
        {!! Form::text('dsn_nip', null, ['class' => 'form-control', 'id' => 'dsn_nip', 'readonly' => 'readonly']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Kode Dosen</label>
        {!! Form::text('dsn_kode', null, ['class' => 'form-control', 'id' => 'dsn_kode']) !!}
    </div>



{!! Form::close() !!}
