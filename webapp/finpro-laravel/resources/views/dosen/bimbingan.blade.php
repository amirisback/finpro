@extends('layout.app-dosen')
@section('title')
Bimbingan
@endsection

<!-- Body -->
@section('top-bar')
Data Bimbingan
@endsection

<!-- Page Content Holder -->
@section('content')
<div id="content">
    <nav class="navbar navbar-default">
        <div class="container-fluid"> 
            <div class="panel-body">
                <div class="row">
                @if($cekPengajuan > 0)
                    <h2>Pengajuan Bimbingan</h2>
                    <a href="{{ route('dosen.bimbingan.pengajuan.setujui-semua')}}" class="href btn btn-success btn-update" subtitle="seluruh bimbingan mahasiswa akan disetujui semua" title="Setujui Seluruh Bimbingan">ACC SEMUA BIMBINGAN</a>
                    <!-- <a href="" class="href btn btn-danger">TOLAK SEMUA BIMBINGAN</a> -->
                    <div class="line-title"></div><br>
                    <table id="datatable2" class="table table-hover" style="width:100%">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Nama Tim</th>
                                <th scope="col">Topik Bimbingan</th>
                                <th scope="col">Tanggal Pengajuan</th>
                                <th scope="col">Aksi</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                @endif
                @include('layout._alert')
                    <h2>Bimbingan</h2>
                    <div class="line-title"></div><br>
                    <table id="datatable" class="table table-hover" style="width:100%">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Nama</th>
                                <th scope="col">Nama Tim</th>
                                <th scope="col">Judul</th>
                                <th scope="col">Jumlah Bimbingan</th>
                                <th scope="col">Aksi</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </nav>
</div>
@include('layout._modal')
@endsection
@push('scripts')
<script>
    $('#datatable').DataTable({
        responsive:true,
        processing:true,
        ajax: "{{ route('dosen.bimbingan.json') }}",
        columns:[
            {data: 'DT_RowIndex', name:'mahasiswa', searchable: false},
            {data: 'mahasiswa', name:'mahasiswa'},
            {data: 'nama_tim', name:'nama_tim'},
            {data: 'judul', name:'judul'},
            {data: 'jumlah_bimbingan', name:'jumlah_bimbingan'},
            {data: 'action', name: 'action', orderable: false, searchable: false},   
        ]
    });
</script>

<script>
    $('#datatable2').DataTable({
        responsive:true,
        processing:true,
        ajax: "{{ route('dosen.bimbingan.pengajuan.json') }}",
        columns:[
            {data: 'DT_RowIndex', name:'tgl_bimbingan', searchable: false},
            {data: 'nama_tim', name:'nama_tim'},
            {data: 'bimbingan_review', name:'bimbingan_review'},
            {data: 'tgl_bimbingan', name:'tgl_bimbingan'},
            {data: 'action', name: 'action', orderable: false, searchable: false},   
        ]
    });
</script>
@endpush