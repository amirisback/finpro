@extends('layout.app')
    @section('title')
        Pengguna Mahasiswa
    @endsection

    <!-- body -->
    @section('top-bar')
        Sidang
    @endsection


<!-- Page Content Holder -->
@section('content')
<div id="content">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <br>
            <h2>Data Nilai Sidang Mahasiswa</h2>
            <div class="line-title"></div>
            <table class="table"  id="datatable">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Nama Mahasiswa</th>
                        <th scope="col">Tim</th>
                        <th scope="col">Nilai Total</th>
                        <th scope="col">Keterangan</th>
                        <th scope="col">Pembimbing</th>
                        <th scope="col">Reviewer</th>
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
        responsive:true,
        processing:true,
        ajax: "{{ route('koordinator.sidang.json') }}",
        columns:[
            {data: 'DT_RowIndex', name:'nama_mahasiswa', searchable: false},
            {data: 'nama_mahasiswa', name:'nama_mahasiswa'},
            {data: 'nama_tim', name:'nama_tim'},
            {data: 'nilai_total', name:'nilai_total'},
            {data: 'status_sidang', name:'status_sidang'},
            {data: 'pembimbing', name:'pembimbing'},
            {data: 'reviewer', name:'reviewer'},
            {data: 'action', name: 'action', orderable: false, searchable: false},   
        ]
    });
    </script>
@endpush