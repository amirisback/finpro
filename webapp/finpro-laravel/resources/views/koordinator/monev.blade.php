@extends('layout.app')
    @section('title')
        Pengguna Mahasiswa
    @endsection

    <!-- body -->
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
                    Kelola Kategori Monev
                </div>
                <form action="{{ route('koordinator.monev.kategori.tambah')}}" method="POST">
                    {{ csrf_field() }}
                    <div class="panel-body">
                        <div class="col-lg-6">
                            <div class="col-lg-7">
                                <div class="form-group has-feedback {{ $errors->has('form_monevKategori') ? ' has-error' : '' }}">
                                    <label>Kategori Monev</label><br>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-th-list" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" placeholder="Input Kategori Monev" name="form_monevKategori" value="{{ old('form_monevKategori')}}">
                                    </div>
                                    @if ($errors->has('form_monevKategori'))
                                        <span class="help-block">
                                            <p class="text-danger">{{ $errors->first('form_monevKategori') }}</p>
                                        </span>
                                    @endif
                                </div>
                            </div>   
                            <div class="col-lg-5">
                                <div class="form-group has-feedback {{ $errors->has('form_monevBimbingan') ? ' has-error' : '' }}">
                                    <div class="col-lg-12">
                                        <label>Jml. Bimbingan</label><br>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-sort-numeric-asc" aria-hidden="true"></i></span>
                                            <input type="number" min="1" max="16" class="form-control" placeholder="0 kali" name="form_monevBimbingan" value="{{ old('form_monevBimbingan')}}">
                                        </div>
                                        @if ($errors->has('form_monevBimbingan'))
                                            <span class="help-block">
                                                <p class="text-danger">{{ $errors->first('form_monevBimbingan') }}</p>
                                            </span>
                                        @endif
                                    </div>
                                </div>
                        </div>   
                        <div class="col-lg-12">
                            <input type="submit" class="btn btn-primary" value="Tambah Kategori Monev">          
                        </div>  
                        @include('layout._alert')    
                        <div class="col-lg-12">
                            <br>
                            <div class="panel panel-pemberitahuan panel-default">
                                <a><b>Info :</b><br>
                                    Kategori Monev dapat dihapus apabila data nilai monev tersebut masih kosong</a>
                            </div>
                       </div>
                    </div>
                </form>

              
                <div class="col-lg-6">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Kategori Monev</th>
                                <th scope="col">Minimal Bimbingan</th>
                                <th scope="col">Status</th>
                                <th scope="col">Aksi</th>                                
                            </tr>
                        </thead>
                        </tbody>
                            @forelse($monevKategori as $index => $monev)
                            <tr>
                                <td>{{$index+1}}</td>
                                <td>{{$monev->monev_kategori}}</td>
                                <td>{{$monev->jumlah_bimbingan}} Kali</td>
                                <td>
                                @if($monev->monev_status == '1')
                                <a class="btn btn-success">Aktif</a>
                                @else
                                <a class="btn btn-danger">Tidak Aktif</a>
                                @endif
                                </td>
                                <td>                            
                                @if(empty($monevDetail->where('monev_id', $monev->monev_id)->first()))
                                    <a href="{{ route('koordinator.monev.kategori.detail', ['id' => $monev->monev_id])}}" class="btn-action btn modal-show edit " title="{{'Ubah '.$monev->monev_kategori}}"><i class="icon-pencil text-inverse"></i></a>                                    
                                    <a href="{{ route('koordinator.monev.kategori.hapus', ['id' => $monev->monev_id])}}" class="btn-action btn btn-delete" title="{{$monev->monev_kategori}}"><i class="icon-trash text-danger"></i></a>
                                @else
                                    <a href="{{ route('koordinator.monev.kategori.detail', ['id' => $monev->monev_id])}}" class="btn-action btn modal-show edit " title="{{'Ubah '.$monev->monev_kategori}}"><i class="icon-pencil text-inverse"></i></a>
                                @endif
                                
                                </td>
                                <td>
                                </td>
                            </tr>
                            @empty
                                <td colspan="2" class="text-center">Kategori Monev Masih Kosong</td>
                            @endforelse
                        </tbody>
                    </table>
                </div>
            </div>
        </nav>
        <br>
        <h2>Data Nilai Monev Mahasiswa</h2>
        <div class="line-title"></div>
        <table class="table"  id="datatable">
            <thead class="thead-light">
                <tr>
                    <th scope="col">No</th>
                    <th scope="col">Nama Mahasiswa</th>
                    <th scope="col">Tim</th>
                    <th scope="col">Nilai Rata-rata</th>
                    <th scope="col">reviewer</th>
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
            //serverSide:true,
            ajax: "{{ route('koordinator.monev.json') }}",
            columns:[
                //{ "defaultContent": "" },
                {data: 'DT_RowIndex', name:'nama_mahasiswa', searchable: false},
                {data: 'nama_mahasiswa', name:'nama_mahasiswa'},
                {data: 'nama_tim', name:'nama_tim'},
                {data: 'nilai', name:'nilai'},  
                {data: 'reviewer', name:'reviewer'},
                {data: 'action', name: 'action', orderable: false, searchable: false},   
            ]
        });
    </script>
@endpush
