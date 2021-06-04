@extends('layout.app-dosen')
    @section('title')
        Notifikasi
    @endsection

    <!-- Body -->
        @section('top-bar')
            Notifikasi
        @endsection

       
       <!-- Page Content Holder -->
       @section('content')
            <div id="content">
                <nav class="navbar navbar-default">
                    <table class="table table-hover table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Dari</th>
                                <th scope="col">Kategori Informasi</th>
                                <th scope="col">Detail</th>
                                <th scope="col">Tanggal</th>
                            </tr>
                        </thead>
                        <tbody>
                            @forelse($pemberitahuan as $index => $values)
                                <tr>
                                    <td>{{ $values->notifikasi_dari }}</td>
                                    <td>{{ $values->notifikasi_kategori }}</td>
                                    <td>{{ $values->notifikasi_deskripsi}}</td>
                                    <td>{{ $values->notifikasi_tanggal }}</td>
                                </tr>
                            @empty
                                <td colspan="2" class="text-center">Pemberitahuan masih kosong</td>
                            @endforelse           
                        </tbody>
                    </table>
                    <center>{{ $pemberitahuan->links()}}</center>
                </nav>
            </div>
        @endsection


