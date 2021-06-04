@extends('layout.app')
    @section('title')
        Pengguna Reviewer
    @endsection

    <!-- body -->
    @section('top-bar')
        Data Reviewer
    @endsection

    
    
    <!-- Page Content Holder -->
    @section('content')
        <div id="content">
            <nav class="navbar navbar-default">

                <div class="container-fluid">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Pembagian Reviewer
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <form action="{{route('koordinator.tambah.reviewer')}}" method="POST">
                                {{ csrf_field() }}
                                    <div class="col-lg-6">
                                        <div class="form-group has-feedback {{ $errors->has('nip_reviewer') ? ' has-error' : '' }}">
                                            <label>Pilih Dosen Reviewer</label><br>
                                            <select class="form-control" name="nip_reviewer">
                                                @forelse($dosen as $reviewer)
                                                    @if(empty($proyekAkhirCount->where('dsn_nip', $reviewer->dsn_nip)))
                                                        {{ $tempDosen = 0}}
                                                    @else
                                                        {{ $tempDosen = $proyekAkhirCount->where('dsn_nip', $reviewer->dsn_nip)->count()}}
                                                    @endif
                                                <option value= "{{$reviewer->dsn_nip.'_&_'.$reviewer->dsn_nama}}">{{($reviewer->batas_reviewer - $tempDosen).' - '. $reviewer->dsn_nama.' ('.$reviewer->dsn_kode.')'}} </option>
                                                @empty
                                                <option value="">
                                                    <p>Data Dosen Kosong</p>
                                                </option>
                                                @endforelse
                                            </select>
                                            @if ($errors->has('nip_reviewer'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('nip_reviewer') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group has-feedback {{ $errors->has('id_judul') ? ' has-error' : '' }}">
                                            <label>Pilih Judul</label><br>
                                            <select class="form-control " name="id_judul">
                                            @forelse($proyek_akhir as $judulmhs)
                                                <option value="{{$judulmhs->judul_id.'_&_'.$judulmhs->tbl_judul->tbl_dosen->dsn_nip}}">
                                                    {{$judulmhs->tbl_judul->judul_nama.' ('.$judulmhs->tbl_judul->tbl_dosen->dsn_kode.')'}}
                                                </option>
                                            @empty
                                                <option value="">
                                                    <p>judul tidak tersedia</p>
                                                </option>
                                            @endforelse
                                            </select>
                                            @if ($errors->has('id_judul'))
                                                <span class="help-block">
                                                    <p class="text-danger">{{ $errors->first('id_judul') }}</p>
                                                </span>
                                            @endif
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                    <button type="submit" class="btn btn-primary">Tambah Reviewer</button>      
                                    </div>
                                </form>
                                @include('layout._alert')
                            </div>
                        </div>
                    </div>
                    <!-- End Form Elements -->



                </div>
            </nav>
            <h2>Reviewer Aktif</h2>
            <div class="line-title"></div>
            <br>
            <div>
            <table id="datatable" class="table table-hover" style="width:100%">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Reviewer</th>
                        <th scope="col">Pembimbing</th>
                        <th scope="col">Judul Proyek Akhir</th>
                        <th scope="col">Tim</th>
                        <th scope="col">Aksi</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            </div>
        </div>
    @include('layout._modal')
    @endsection


@push('scripts')
    <script>
        $('#datatable').DataTable({
            responsive:true,
            processing:true,
            //serverSide:true,
            ajax: "{{ route('koordinator.reviewer.json') }}",
            columns:[
                //{ "defaultContent": "" },
                {data: 'DT_RowIndex', name:'dsn_nama', searchable: false},
                {data: 'reviewer', name:'reviewer'},
                {data: 'pembimbing', name:'pembimbing'},
                {data: 'judul', name:'judul'},  
                {data: 'tim_kelompok', name:'tim_kelompok'},  
                {data: 'action', name: 'action', orderable: false, searchable: false},   
            ]
        });
    </script>
@endpush