{!! Form::open([
    'route' => 'koordinator.mahasiswa.import',
    'method' => 'post'

]) !!}
    <div class="form-group">
        <label for="" class="control-label">Upload File Excel </label>
        {!! Form::file('file', null, ['class' => 'form-control', 'id' => 'file']) !!}
        <p class="filexls">*file harus berekstensi .xls</p>
    </div>
{!! Form::close() !!}
