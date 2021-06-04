
{!! Form::model($model, [
    'route' => 'mahasiswa.judul.mandiri.ajukan',
    'method' => 'POST'
]) !!}  
    @include('layout._alert')
    <div class="form-group">
        <label for="" class="control-label">Judul</label>
        {!! Form::text('form_judulProyek', null, ['class' => 'form-control', 'id' => 'form_judulProyek', 'placeholder' => 'input judul proyek akhir']) !!}
    </div>
    
    <div class="form-group">
        <label for="" class="control-label">Kategori </label>
        {!! Form::select('form_kategoriJudul', $selectKategoriJudul, null, ['class'=>'form-control']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Deskripsi</label>
        {!! Form::textarea('form_deskripsiJudul', null, ['class' => 'form-control', 'id' => 'form_deskripsiJudul',  'placeholder' => 'input deskripsi dari judul proyek akhir dengan spesifik mungkin']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Pembimbing <p>*hanya menampilkan data dosen yang tersedia</p></label>
        {!! Form::select('form_pilihPembimbing', $selectPembimbing, null, ['class'=>'form-control']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">Nama Tim / Kelompok</label>
        {!! Form::text('form_namaTim', null, ['class' => 'form-control', 'id' => 'form_namaTim', 'placeholder' => 'input nama tim']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">NIM Anggota 1</label>
        {!! Form::text('form_namaAnggota1', Session::get('session_nim').' ('.Session::get('session_nama').')', ['class' => 'form-control', 'id' => 'form_namaAnggota1', 'placeholder' => 'kosong', 'disabled' => 'disabled']) !!}
    </div>

    <div class="form-group">
        <label for="" class="control-label">NIM Anggota 2</label>
        {!! Form::text('form_namaAnggota2', null, ['class' => 'form-control', 'readonly' => $cekJmlMahasiswa <= 1 ? 'true' : false ,'id' => 'form_namaAnggota2', 'placeholder' => $cekJmlMahasiswa <= 1 ? 'Mahasiswa tanpa Judul Sudah TIDAK ADA / TERISI SEMUA!!!!' : 'kosong (kosongkan apabila hanya 1 orang/tim)', 'id' => 'form_namaAnggota2']) !!}
        <div id="list_mahasiswa2"></div>
    </div>
{!! Form::close() !!}

<script>
    $(document).ready(function(){
    $('#form_namaAnggota2').keyup(function(){ 
            var query = $(this).val();
            if(query != '')
            {
            var _token = $('input[name="_token"]').val();
            $.ajax({
            url:"{{ route('mahasiswa.autocomplete.nim') }}",
            method:"POST",
            data:{query:query, _token:_token},
            success:function(data){
            $('#list_mahasiswa2').fadeIn();  
            $('#list_mahasiswa2').html(data);
            }
            });
            }
        });

        $(document).on('click', 'li', function(){  
            $('#form_namaAnggota2').val($(this).text());  
            $('#list_mahasiswa2').fadeOut();  
        });  
    });
    </script>
