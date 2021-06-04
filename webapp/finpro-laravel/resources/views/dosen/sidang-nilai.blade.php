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
                                <center><b><a>{{$namaTim}}</a></b> ({{ $countBimbingan}}X Bimbingan)<br>
                                <a>{{ $waktu_sekarang}}</a></center>
                            </div>
                                <div class="panel-body">
                                    <div class="row">
                                    <form action="{{ route('dosen.sidang.nilai.tambah', ['tim' => $namaTim])}}" method="POST">
                                     {{ csrf_field() }}
                                    @include('layout._alert')
                                    @foreach($proyekAkhir as $index => $proyek)
                                    <div class="col-lg-6">
                                        <div class="panel panel-default">
                                            <table class="table">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col">
                                                        Nama : {{ $proyek->tbl_mahasiswa->mhs_nama}}<br>
                                                        Nim : {{ $proyek->tbl_mahasiswa->mhs_nim}}<br>
                                                    </th>
                                                    <th scope="col">
                                                    <img src="{{URL::asset('/image/mhs_img/'. $proyek->tbl_mahasiswa->mhs_foto )}}" width="200" height="200" alt="mahasiswa">
                                                    
                                                    </th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Nilai Proposal</td>
                                                    <td><input type="number" min="0" max="100" name="nilaiProposal[{{$proyek->proyek_akhir_id}}]" value="{{ empty($proyek->tbl_sidang->nilai_proposal) === true ? '' : $proyek->tbl_sidang->nilai_proposal }}" class="form-control" placeholder="kosong"></td>
                                                </tr>
                                                <tr>
                                                    <td>Nilai pembimbing</td>
                                                    <td><input type="number" min="0" max="100" name="nilaiPembimbing[{{$proyek->proyek_akhir_id}}]" value="{{ empty($proyek->tbl_sidang->nilai_pembimbing) === true ? '' : $proyek->tbl_sidang->nilai_pembimbing }}" class="form-control" placeholder="kosong"></td>
                                                </tr>
                                                <tr>
                                                    <td>Nilai Penguji 1</td>
                                                    <td><input type="number" min="0" max="100" name="nilaiPenguji1[{{$proyek->proyek_akhir_id}}]" value="{{ empty($proyek->tbl_sidang->nilai_penguji_1) === true ? '' : $proyek->tbl_sidang->nilai_penguji_1 }}" class="form-control" placeholder="kosong"></td>
                                                </tr>
                                                <tr>
                                                    <td>Nilai Penguji 2</td>
                                                    <td><input type="number" min="0" max="100" name="nilaiPenguji2[{{$proyek->proyek_akhir_id}}]" value="{{ empty($proyek->tbl_sidang->nilai_penguji_2) === true ? '' : $proyek->tbl_sidang->nilai_penguji_2 }}" class="form-control" placeholder="kosong"></td>
                                                </tr>
                                                <tr>
                                                    <td>Nilai Total</td>
                                                    <td><input type="number"  name="nilaiTotal[{{$proyek->proyek_akhir_id}}]" readonly="readonly" value="{{ empty($proyek->tbl_sidang->nilai_total) === true ? '' : $proyek->tbl_sidang->nilai_total }}" class="form-control" placeholder="otomatis terisi"></td>
                                                </tr>
                                                <tr>
                                                    <td>Review Sidang</td>
                                                    <td>
                                                        <textarea class="form-control" maxlength="255" minlength="1" name="reviewSidang[{{$proyek->proyek_akhir_id}}]" rows="4" placeholder="input review sidang" name="form_deskripsiJudul" >{{ empty($proyek->tbl_sidang->sidang_review) === true ? '' : $proyek->tbl_sidang->sidang_review }}</textarea>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Status Sidang</td>
                                                    <td>
                                                        <div class="form-group">
                                                        @if(empty($proyek->tbl_sidang->sidang_status) === true)
                                                            <select name="statusSidang[{{$proyek->proyek_akhir_id}}]"  class="form-control">
                                                                <option value="-" selected="selected">--pilih--</option>
                                                                <option value="lulus" >Lulus</option>
                                                                <option value="ditunda">Ditunda</option>
                                                                <option value="tidak lulus" >Tidak Lulus</option>
                                                            </select>
                                                        @elseif(empty($proyek->tbl_sidang->sidang_status) === false)
                                                            <select name="statusSidang[{{$proyek->proyek_akhir_id}}]"  class="form-control">
                                                                <option value="-" >--pilih--</option>
                                                                <option value="lulus" {{ $proyek->tbl_sidang->sidang_status === 'lulus' ? 'selected' : ''}}>Lulus</option>
                                                                <option value="ditunda" {{ $proyek->tbl_sidang->sidang_status === 'ditunda' ? 'selected' : ''}}>Ditunda</option>
                                                                <option value="tidak lulus" {{ $proyek->tbl_sidang->sidang_status === 'tidak lulus' ? 'selected' : ''}}>Tidak Lulus</option>
                                                            </select>
                                                        @endif
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                @endforeach
                                <div class="col-lg-12">
                                   <tr>
                                        <td>Status Judul</td>
                                        <td>
                                            <select name="statusJudul" class="form-control">
                                                <option value="-" >--pilih--</option>
                                                <option value="Aktif">Aktif</option>
                                                <option value="Arsip">Arsip</option>
                                            </select>
                                        </td>
                                    </tr>
                                
                                   
                                    <br>
                                    <center><input type="submit" value="{{ $cekNilaiSidang === 'insert' ? 'MASUKAN NILAI SIDANG' : 'UBAH NILAI SIDANG' }}" class="btn btn-primary"></center>
                                </div>
                            </form>                                 
                                    </div>
                                </div>
                            
                        </div>
                        <!-- End Form Elements -->
                    </div>
                </nav>
               
            </div>         
        @endsection 
        