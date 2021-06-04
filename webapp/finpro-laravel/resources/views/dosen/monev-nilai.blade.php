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
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        <a href="{{ route('dosen.monev')}}" class="href"><i class="fa fa-reply text-info"></i> Tambahkan Nilai <b>{{$namaMonev->monev_kategori}}</b></a>
                         </div>
                        <div class="panel-body">
                            <form action="{{ route('dosen.monev.nilai.tambah', ['monev'=> $idMonev,  'tim' => $namaTim])}}" method="POST">
                                {{ csrf_field() }}
                                <div class="col-lg-12">
                                    @include('layout._alert')
                                    <table class="table table-hover" style="width:100%">
                                        <thead class="thead-light">
                                        <tr>
                                        <th scope="col">No</th>
                                            <th scope="col">nama</th>
                                            <th scope="col">Tanggal</th>
                                            <th scope="col">Catatan Monev</th>
                                            <th scope="col">Nilai</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            @forelse($dataMonev as $index => $monev)
                                            <tr>
                                                <td>{{ $index+1 }}</td>
                                                <td name="form_monevMahasiswa[{{$monev->proyek_akhir_id}}]">{{ $monev->tbl_proyek_akhir->tbl_mahasiswa->mhs_nama }}</td>
                                                <td name="form_monevWaktu">{{$waktu_sekarang}}</td>
                                                <td>
                                                    <div class="form-group has-feedback {{ $errors->has('form_monevCatatan['.$monev->proyek_akhir_id.']') ? ' has-error' : '' }}">  
                                                        <textarea name="form_monevCatatan[{{$monev->proyek_akhir_id}}]"  maxlength="255" rows="3" placeholder="input catatan monev" class="form-control">{{ empty($monev->monev_ulasan) === true ? '' : $monev->monev_ulasan}}</textarea>
                                                        @if ($errors->has('form_monevCatatan[$monev->proyek_akhir_id]'))
                                                            <span class="help-block">
                                                                <p class="text-danger">{{ $errors->first('form_monevCatatan[$monev->proyek_akhir_id]') }}</p>
                                                            </span>
                                                        @endif
                                                    </div>
                                                </td>
                                                <td><input type="number" min="0" max="100" value="{{ empty($monev->monev_nilai) === true ? '0' : $monev->monev_nilai}}" name="form_monevNilai[{{$monev->proyek_akhir_id}}]" placeholder="0" class="form"></td>
                                            </tr>
                                            @empty
                                                @foreach($dataTim as $index => $monev)
                                                <tr>
                                                    <td>{{ $index+1 }}</td>
                                                    <td name="form_monevMahasiswa[{{$monev->proyek_akhir_id}}]">{{ $monev->tbl_mahasiswa->mhs_nama }}</td>
                                                    <td name="form_monevWaktu">{{$waktu_sekarang}}</td>
                                                    <td>
                                                        <div class="form-group has-feedback {{ $errors->has('form_monevCatatan['.$monev->proyek_akhir_id.']') ? ' has-error' : '' }}">
                                                            <textarea name="form_monevCatatan[{{$monev->proyek_akhir_id}}]"  maxlength="255" rows="3" placeholder="input catatan monev" class="form-control"></textarea>
                                                            @if ($errors->has('form_monevCatatan[$monev->proyek_akhir_id]'))
                                                                <span class="help-block">
                                                                    <p class="text-danger">{{ $errors->first('form_monevCatatan[$monev->proyek_akhir_id]') }}</p>
                                                                </span>
                                                            @endif
                                                        </div>
                                                    </td>
                                                    <td><input type="number" min="0" max="100" value="" name="form_monevNilai[{{$monev->proyek_akhir_id}}]" placeholder="0" class="form"></td>
                                                </tr>
                                                @endforeach
                                            @endforelse
                                        </tbody>
                                    </table>        
                                </div>
                                <div class="offset-8 col-lg-2">
                                    <input type="submit" value="{{ $dataMonev->count() < 1 ? 'Masukan Nilai' : 'Ubah Nilai'}}" class="btn btn-primary">                       
                                </div>
                            </form>
                        </div>
                    </nav>
                <h3>Nilai Monev <b>Tim {{ $namaTim }}</b></h3>
                <div class="line-title"></div>
                 <br>
                 <table id="datatable"  class="table table-hover" style="width:100%">
                     <thead class="thead-light">
                     <tr>
                      <th scope="col">No</th>
                        <th scope="col">Monev</th>
                        <th scope="col">Catatan Monev</th>
                        <th scope="col">Nilai</th>
                        <th scope="col">Tanggal</th>
                        <th scope="col">ubah</th>
                        </tr>
                    </thead>
                    <tbody>
                        @forelse($dataTable as $index => $item)
                        <tr>
                            <td>{{ $index+1 }}</td>
                            <td>{{ $item->tbl_monev->monev_kategori }}</td>
                            <td>{{ $item->monev_ulasan }}</td>
                            <td>{{ $item->monev_nilai }}</td>
                            <td>{{ $item->monev_tanggal }}</td>
                            <td><a href="{{ route('dosen.monev.nilai', ['monev' => $item->monev_id, 'judul' => $item->tbl_proyek_akhir->judul_id])}}" class="btn btn-info">Ubah</a></td>
                            
                        </tr>
                        @empty

                        @endforelse
                    </tbody>
                </table>
        </div>
    @include('layout._modal')
    @endsection
