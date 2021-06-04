{!! Form::model($model, [
    'route' => ['koordinator.monev.kategori.ubah', $model->monev_id],
    'method' => 'put'

]) !!}
    <div class="form-group">
        <label for="" class="control-label">Kategori Judul </label>
        {!! Form::text('form_kategoriMonev', $model->monev_kategori, ['class' => 'form-control', 'id' => 'form_kategoriMonev']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Jumlah Bimbingan  </label>
        {!! Form::number('form_BimbinganMonev', $model->jumlah_bimbingan, ['class' => 'form-control', 'id' => 'form_BimbinganMonev']) !!}
    </div>

{!! Form::close() !!}
