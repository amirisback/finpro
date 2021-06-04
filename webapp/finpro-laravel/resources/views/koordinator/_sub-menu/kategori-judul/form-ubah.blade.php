{!! Form::model($model, [
    'route' => ['koordinator.judul.kategori.ubah', $model->kategori_id],
    'method' => 'put'

]) !!}
    <div class="form-group">
        <label for="" class="control-label">Ubah Kategori Judul </label>
        {!! Form::text('form_kategoriJudul', $model->kategori_nama, ['class' => 'form-control', 'id' => 'form_kategoriJudul']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Status</label>
        {!! Form::select('form_kategoriStatus',['1' => 'Aktif', '0' => 'Tidak Aktif'], $model->kategori_status, ['class'=>'form-control']) !!}        
    </div>

{!! Form::close() !!}
