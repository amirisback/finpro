package org.d3ifcool.base.helpers;

public class ConstantNotif {

    public ConstantNotif() {
    }

    public static final class ConstantaNotif {

        public static final String UNTUK_DOSEN = "dosen"; // untuk dosen
        public static final String UNTUK_MAHASISWA = "mahasiswa"; // untuk mahasiswa
        public static final String UNTUK_KOOR = "koor"; // untuk koor
        public static final String UNTUK_SEMUA = "semua"; // untuk semua

        public static final String NOTIF_KATEGORI_INFORMASI = "informasi"; // judul yang di buat dosen
        public static final String NOTIF_DESC_INFORMASI(String nama){
             return nama + " mengirim informasi";
        } // judul yang di buat dosen

        public static final String NOTIF_KATEGORI_JUDUL_TAMBAH = "judul_tambah"; // judul yang di buat dosen
        public static final String NOTIF_DESC_JUDUL_TAMBAH = "membuat judul baru"; // judul yang di buat dosen

        public static final String NOTIF_KATEGORI_JUDUL_ACC = "judul_acc"; // judul yang di buat dosen
        public static final String NOTIF_DESC_JUDUL_ACC = "Menyetujui  judul"; // judul yang di buat dosen

        public static final String NOTIF_KATEGORI_JUDUL_DITOLAK = "judul_ditolak"; // judul yang di buat dosen
        public static final String NOTIF_DESC_JUDUL_DITOLAK = "Menolak judul "; // judul yang di buat dosen

        public static final String NOTIF_KATEGORI_BIMBINGAN_ACC = "bimbingan_acc"; // judul yang di buat dosen
        public static final String NOTIF_DESC_BIMBINGAN_ACC = "Menyetujui Bimbingan"; // judul yang di buat dosen

        public static final String NOTIF_KATEGORI_MONEV_INPUT = "monev_input"; // judul yang di buat dosen
        public static final String NOTIF_DESC_MONEV_INPUT = "Telah memasukan nilai monev"; // judul yang di buat dosen

    }
}
