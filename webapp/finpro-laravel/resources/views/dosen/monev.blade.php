@extends('layout.app-dosen')
    @section('title')
        Monev
    @endsection

    <!-- Body -->
    @section('top-bar')
        Monev
    @endsection

    <!-- Page Content Holder -->
    @section('content')
        <div id="content">
            <nav class="navbar navbar-default">

                <div class="container-fluid">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             Tambahkan Nilai Monev Mahasiswa Reviewer
                         </div>
                        <div class="panel-body">
                            <form action="{{ route('dosen.monev.nilai.tampung')}}" method="GET">
                                <div class="col-lg-5">
                                    <div class="col-lg-12">
                                        <div class="form-group">
                                            <label>Monev</label><br>
                                            <select name="selectMonev"  class="form-control">
                                                @forelse($monev as $item)
                                                    <option value= "{{$item->monev_id}}">{{$item->monev_kategori}}</option>
                                                @empty
                                                    <option value= "">Kosong</option>
                                                @endforelse
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-5">
                                    <div class="col-lg-12">
                                        <div class="form-group">
                                            <label>Judul Proyek Akhir</label>
                                            <select name="selectJudul"  class="form-control">
                                            @forelse($judul as $value)
                                                <option value="{{$value->judul_id}}">{{$value->judul_nama}}</option>
                                            @empty
                                                <option value= "">Data Judul Kosong</option>
                                            @endforelse
                                            </select>
                                        </div>                                                
                                    </div>  
                                    <div class="offset-7 col-lg-2">
                                        <input type="submit" value="Masukan Nilai" class="btn btn-primary">                       
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
            <h2>Data Nilai Monev Reviewer</h2>
            <div class="line-title"></div>
            <br>
            <table class="table"  id="datatable">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Kategori</th>
                        <th scope="col">Nama Mahasiswa</th>
                        <th scope="col">Tim</th>
                        <th scope="col">reviewer</th>
                        <th scope="col">Nilai</th>
                        <th scope="col">Nilai Rata</th>
                        <th scope="col">Aksi</th>
                    </tr>
                </thead>
                </tbody>
                </tbody>
            </table>
        </div>                      
    </nav>
</div>
@include('layout._modal')
@endsection

@push('scripts')
<script>
    $('#datatable').DataTable({
        
        processing:true,
        //serverSide:true,
        ajax: "{{ route('dosen.monev.json') }}",
        columns:[
            //{ "defaultContent": "" },
            {data: 'DT_RowIndex', name:'nama_mhs', searchable: false},
            {data: 'kategori_monev', name:'kategori_monev'},
            {data: 'nama_mhs', name:'nama_mhs'},
            {data: 'tim', name:'tim'},
            {data: 'reviewer', name:'reviewer'},
            {data: 'nilai', name:'nilai'},
            {data: 'nilai_rata', name:'nilai_rata'},
            {data: 'action', name: 'action', orderable: false, searchable: false},   
        ]
    });
</script>
@endpush