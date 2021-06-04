@extends('layout.app-mhs')
@section('title')
Daftar Judul PA
@endsection

<!-- Body -->
@section('top-bar')
Daftar Judul Proyek Akhir
@endsection


<!-- Page Content Holder -->
@section('content')
<div id="content">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
        <!-- Judul Mandiri -->
        @if(!$belumPunyaJudul)
            <!-- dibiarkan Kosong -->
        @else
            <div class="panel panel-default">
                <div class="panel-heading">
                    Ajukan Judul Baru Mandiri
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <a href="{{ route('mahasiswa.judul.madiri.form')}}" class="btn modal-show btn-primary" title="Ajukan Judul Mandiri" role="button">(+) Pengajuan Judul Baru</a><br>          
                        </div>
                    </div>
                </div>
            </div>
        @endif
        <!-- Judul Mandiri -->
            <h2>Daftar Judul dari Dosen</h2>
            <div class="line-title"></div>
            <br>
            <table id="datatable" class="table table-hover" style="width:100%">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Judul</th>
                        <th scope="col">kategori</th>
                        <th scope="col">Pembimbing</th>
                        <th scope="col">Jumlah Pesaing</th>
                        <th scope="col">Status</th>
                        <th scope="col">Aksi</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            
        </div>
    </nav>

</div>
@include('layout._modal')

@push('scripts')
    <script>
        $('#datatable').DataTable({
            responsive:true,
            processing:true,
            ajax: "{{ route('mahasiswa.judul_aktif.json') }}",
            columns:[
                {data: 'DT_RowIndex', name:'judul_nama', searchable: false},
                {data: 'judul_nama', name:'judul_nama'},
                {data: 'kategori_judul', name:'kategori_judul'},
                {data: 'pembimbing', name:'pembimbing'},
                {data: 'jumlah_kelompok', name:'jumlah_kelompok'},
                {data: 'judul_status', name:'judul_status'},
                 {data: 'action', name: 'action', orderable: true, searchable: false},   
            ]
        });
    </script>
    @endpush

@endsection