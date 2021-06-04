
                <table class="table table-striped">
                    <tbody>                                        
                    @forelse($detailPesan as $index => $value)
                        @php 
                            $arrays = $value->deskripsi; 
                            $ar = json_decode($arrays);
                        @endphp
                            
                        <tr>
                            <td>Nama Judul</td>
                            <td><strong>{!! $ar[0] !!}</strong></td>
                        </tr>

                        <tr>
                            <td>Kategori</td>
                            <td><strong>{!! $ar[1] !!}</strong></td>
                        </tr>
                        <tr>
                            <td>Deskripsi</td>
                            <td><strong>{!! $ar[2] !!}</strong></td>
                        </tr>
                    @empty
                        <td colspan='2' class='text-center'>Pemberitahuan masih kosong</td>
                    @endforelse     
                    </tbody>
                </table>



