
<p>Judul : <b>{{ $detailSidang->tbl_judul->judul_nama }}</b></p>
<p>Kategori : <b>{{ $detailSidang->tbl_judul->tbl_kategori_judul->kategori_nama }}</b></p>

    <hr>
    <table class="table table-striped">
    <tbody>                                     
        <tr>
            <td>Nilai Proposal</td>
            <td><strong>{{ empty( $detailSidang->tbl_sidang->nilai_proposal) == true ? '0' : $detailSidang->tbl_sidang->nilai_proposal }}</td>            
        </tr>
        <tr>
            <td>Nilai Penguji 1</td>
            <td><strong>{{ empty($detailSidang->tbl_sidang->nilai_penguji_1) === true ? '0' : $detailSidang->tbl_sidang->nilai_penguji_1 }}</td>
        </tr>
        <tr>
            <td>Nilai Penguji 2</td>
            <td><strong>{{ empty($detailSidang->tbl_sidang->nilai_penguji_2) === true ? '0' : $detailSidang->tbl_sidang->nilai_penguji_2 }}</td>
        </tr>
        <tr>
            <td>Nilai Pembimbing</td>
            <td><strong>{{ empty($detailSidang->tbl_sidang->nilai_pembimbing) === true ? '0' : $detailSidang->tbl_sidang->nilai_pembimbing }}</td>
        </tr>
        <tr>
            <td>Total</td>
            <td><strong>{{ empty($detailSidang->tbl_sidang->nilai_total) === true ? '0' : $detailSidang->tbl_sidang->nilai_total }}</td>
        </tr>
        <tr>
            <td>Keterangan</td>
            <td><strong>{{ empty($detailSidang->tbl_sidang->sidang_status) === true ? '0' : $detailSidang->tbl_sidang->sidang_status }}</td>
        </tr>
    </tbody>
</table>
<p>Review  <b></b></p>
<a>{{ empty($detailSidang->tbl_sidang->sidang_review) === true ? '-' : $detailSidang->tbl_sidang->sidang_review }}</a>
