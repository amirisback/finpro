@extends('layout.app-mhs')
    @section('title')
        Arsip Judul
    @endsection

    <!-- Body -->
        @section('top-bar')
            Data Arsip Judul Proyek Akhir
        @endsection
       
        <!-- Page Content Holder -->
        @section('content')
        <div id="content">
            <nav class="navbar navbar-default">       
            <div class="container-fluid">
            <h2>Arsip Judul Proyek Akhir</h2>
                <div class="line-title"></div>
                <br>
                <table id="datatable" class="table table-hover" style="width:100%">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Nama Tim</th>
                            <th scope="col">Judul Proyek Akhir</th>
                            <th scope="col">Kategori</th>
                            <th scope="col">Dosbing</th>
                            <th scope="col">Reviewer</th>                            
                            <th scope="col">Tahun</th>     
                            <th scope="col">Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            </nav>
        </div>          
        @endsection
        @include('layout._modal')
        
@push('scripts')
    <script>
        $('#datatable').DataTable({
            responsive:true,
            processing:true,
            //serverSide:true,
            ajax: "{{ route('mahasiswa.judul_arsip.json') }}",
            columns:[
                {data: 'DT_RowIndex', name:'mhs_nama', searchable: false},
                {data: 'nama_tim', name:'nama_tim'},
                {data: 'judul_nama', name:'judul_nama'},
                {data: 'kategori_judul', name:'kategori_judul'},
                {data: 'pembimbing', name:'pembimbing'},
                {data: 'reviewer', name:'reviewer'},
                {data: 'tahun', name:'tahun'},
                {data: 'action', name: 'action', orderable: false, searchable: false},   
            ]
        });
    </script>
@endpush
