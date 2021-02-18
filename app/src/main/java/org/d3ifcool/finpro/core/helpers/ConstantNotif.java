package org.d3ifcool.finpro.core.helpers;

public class ConstantNotif {

    public ConstantNotif() {
    }

    public static final class ConstantaNotif {

        public static final String UNTUK_DOSEN = "dosen"; // untuk dosen
        public static final String UNTUK_MAHASISWA = "mahasiswa"; // untuk mahasiswa
        public static final String UNTUK_KOOR = "koor"; // untuk koor
        public static final String UNTUK_SEMUA = "semua"; // untuk semua

        public static final String NOTIF_KATEGORI_INFORMASI(String nama){
            return nama + " Mengirim Informasi Baru";
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

        public static final String NOTIF_UNDANG_SATU_TIM(String nama, String Judul, String kategori){
            return nama + " Mengundang anda untuk 1 tim dengan judul dari dosen dengan nama judul " + Judul +" dan kategori " + kategori;
        }

        public static final String NOTIF_JUDUL_DIAJUKAN(){
            return "judul sedang di ajukan, harap tunggu acc dari dosen terkait";
        }

        public static final String NOTIF_PENGAJUAN_JUDUL_DOSEN(String nama1, String nama2, String namatim, String namajudul, String kategori){
            return "pengajuan judul dari dosen oleh " +nama1+" " + nama2 + " dengan nama tim " + namatim +
                    " nama judul " + namajudul + " dan kategori " +kategori;
        }

        public static final String NOTIF_JUDUL_MANDIRI_MAHASISWA(){
            return "judul mandiri sedang di ajukan , harap tunggu approve dari dosen terkait";
        }

        public static final String NOTIF_JUDUL_MANDIRI_DOSEN(String mhs1, String mhs2, String namatim, String namajudul, String kategori){
            return "Pengajuan judul mandiri oleh " + mhs1 + " , " +mhs2+ " dengan nama tim "+ namatim + " dan nama judul " +namajudul
                    + " serta kategori " + kategori;
        }

        public static final String NOTIF_JUDUL_TOLAK(String judul){
            return "Judul " + judul + " oleh tim yang anda ajukan di tolak/diberikan ke Tim lain";
        }

        public static final String NOTIF_JUDUL_TERIMA(String nama_dosen){ //di excel nya session nama dosen
            return "Judul yang diajukan telah di setujui dengan dosen pembimbing " + nama_dosen ;
        }

        public static final String NOTIF_INPUT_BIMBINGAN(String tanggal, String namatopik){
            return "Bimbingan online anda pada tanggal " + tanggal +" yang berisikan topik " + namatopik + "sedang menunggu ACC dari dosen pembimbing";
        }

        public static final String NOTIF_BIMBINGAN_TOLAK(String tanggal){
            return "Bimbingan online pada tanggal "+tanggal+" ditolak oleh dosen pembimbing";
        }

        public static final String NOTIF_BIMBINGAN_SETUJU(String tanggal){
            return "Bimbingan online pada tanggal "+tanggal+" disetujui oleh dosen pembimbing";
        }

        public static final String NOTIF_TAMBAH_MONEV(String kategorimonev){
            return "Nilai " + kategorimonev + " telah di tambahkan oleh dosen Reviewer";
        }

        public static final String NOTIF_UBAH_MONEV(String kategorimonev){
            return "Nilai " + kategorimonev + " telah di ubah oleh dosen Reviewer";
        }

        public static final String NOTIF_TAMBAH_SIDANG(){
            return "Nilai Sidang Anda telah di tambahkan ";
        }

        public static final String NOTIF_UBAH_SIDANG(){
            return "Nilai Sidang Anda telah di tambahkan ";
        }

        public static final String NOTIF_PEMETAAN_REVIEWER_MHS(String namadosen){
            return "Dosen " + namadosen + " telah menjadi Reviewer tim anda";
        }

        public static final String NOTIF_PEMETAAN_REVIEWER_DOSEN(String namatim, String judul){
            return "Anda telah menjadi Reviewer untuk Tim " + namatim + " Dengan judul "+judul;
        }

        public static final String NOTIF_UBAH_REVIEWER_MHS(String namadosen){
            return "Reviewer Tim anda telah di ubah menjadi " + namadosen ;
        }

        public static final String NOTIF_UBAH_REVIEWER_DOSEN(String namatim, String judul){
            return "anda telah menjadi reviewer pengganti untuk tim "+namatim+ " dengan judul "+judul;
        }


    }
}
