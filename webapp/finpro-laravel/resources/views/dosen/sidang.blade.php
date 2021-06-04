@extends('layout.app-dosen')
    @section('title')
        Sidang
    @endsection

    <!-- Body -->
        @section('top-bar')
            Sidang
        @endsection


        <!-- Page Content Holder -->
        @section('content')
            <div id="content">
                <nav class="navbar navbar-default">

                <div class="container-fluid">
                        <!-- Form Elements -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Nilai Sidang Tim 
                            </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <form action="{{ route('dosen.sidang.nilai')}}" method="GET">
                                                <div class="form-group has-feedback {{ $errors->has('form_bimbinganJudul') ? ' has-error' : '' }}">
                                                    <label>Judul proyek Akhir</label></label><br>
                                                    <select name="form_bimbinganJudul"  class="form-control">
                                                        <option value="">--pilih--</option>     
                                                        @forelse($selectJudul as $index => $judul)
                                                        <option value="{{$judul->judul_id}}">{{ $judul->judul_nama }}</option>     
                                                        @empty
                                                        <option value="">--kosong--</option>     
                                                        @endforelse
                                                    </select>   
                                                </div>
                                                <div class="">
                                                    <input type="submit" value="Selanjutnya" class="btn btn-primary">                       
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                        </div>
                        <!-- End Form Elements -->
                    </div>
                </nav>

                <!-- Alert -->
                @include('layout._alert')

                <br>
                <h2>Data Nilai Sidang Mahasiswa</h2>
                <div class="line-title"></div>
                <table class="table"  id="datatable">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Nama Mahasiswa</th>
                            <th scope="col">Tim</th>
                            <th scope="col">Pembimbing</th>
                            <th scope="col">Nilai Total</th>
                            <th scope="col">Keterangan</th>
                            <th scope="col">Aksi</th>
                        </tr>
                    </thead>
                    </tbody>
                    </tbody>
                </table>
            </div>  
        @include('layout._modal')       
        @endsection 
        @push('scripts')
    <script>
        $('#datatable').DataTable({
        responsive:true,
        processing:true,
        ajax: "{{ route('dosen.sidang.json') }}",
        columns:[
            {data: 'DT_RowIndex', name:'nama_mahasiswa', searchable: false},
            {data: 'nama_mahasiswa', name:'nama_mahasiswa'},
            {data: 'nama_tim', name:'nama_tim'},
            {data: 'pembimbing', name:'pembimbing'},
            {data: 'nilai_total', name:'nilai_total'},
            {data: 'status_sidang', name:'status_sidang'},
            {data: 'action', name: 'action', orderable: false, searchable: false},   
        ]
    });
    </script>
@endpush