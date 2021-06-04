{!! Form::model($model, [
    'route' => ['dosen.judul-berjalan.ubah', $model->judul_id],
    'method' => 'put'

]) !!}
    
    <div class="form-group">
        <label for="" class="control-label">Judul Proyek Akhir</label>
        {!! Form::text('form_judulProyek', $model->judul_nama, ['class' => 'form-control', 'id' => 'mhs_nama']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Kategori </label>
        {!! Form::select('form_judulKategori', $selectKategoriJudul, $model->kategori_id, ['class'=>'form-control']) !!}        
    </div>

    <div class="form-group">
        <label for="" class="control-label">Deskripsi Judul</label>
        {!! Form::textarea('form_judulDeskripsi', $model->judul_deskripsi, ['class' => 'form-control', 'id' => 'mhs_angkatan']) !!}
    </div>


    <div class="form-group">
        <label for="" class="control-label">Status</label>
        {!! Form::text('status', $model->judul_status, ['class' => 'form-control', 'id' => 'mhs_kontak', 'readonly' => 'readonly']) !!}
    </div>

{!! Form::close() !!}
