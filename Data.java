/* ======================================================================
   PROGRAM PENGURUTAN DATA MAHASISWA (NAMA & ALAMAT)
   Menggunakan BUBBLE SORT dan SELECTION SORT
   Dibuat MANUAL tanpa fungsi pustaka pengurutan Java (tidak memakai
   Arrays.sort, Collections.sort, ataupun String.compareTo).
   Perbandingan string dibuat sendiri karakter per karakter.
   ====================================================================== */

public class SortingManual {

    /* Kelas sederhana untuk menyimpan satu baris data: Nama dan Alamat */
    static class Mahasiswa {
        String nama;
        String alamat;

        Mahasiswa(String nama, String alamat) {
            this.nama = nama;
            this.alamat = alamat;
        }
    }

    /* ------------------------------------------------------------------
       Method bandingkanTeks
       Membandingkan dua String huruf per huruf secara manual
       (pengganti String.compareTo()).
       Mengembalikan:
         - Nilai negatif jika a < b (a lebih dulu secara alfabet)
         - Nilai 0        jika a == b
         - Nilai positif  jika a > b
       ------------------------------------------------------------------ */
    static int bandingkanTeks(String a, String b) {
        int i = 0;
        while (i < a.length() && i < b.length()) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);
            if (ca != cb) {
                return ca - cb;
            }
            i++;
        }
        return a.length() - b.length();
    }

    /* ------------------------------------------------------------------
       Method salinData
       Menyalin isi array data sumber ke array data tujuan yang baru,
       supaya data asli tidak berubah saat diurutkan dua kali dengan
       algoritma yang berbeda.
       ------------------------------------------------------------------ */
    static Mahasiswa[] salinData(Mahasiswa[] sumber) {
        Mahasiswa[] tujuan = new Mahasiswa[sumber.length];
        for (int i = 0; i < sumber.length; i++) {
            tujuan[i] = new Mahasiswa(sumber[i].nama, sumber[i].alamat);
        }
        return tujuan;
    }

    /* ------------------------------------------------------------------
       Method tukar
       Menukar isi dua elemen array Mahasiswa.
       ------------------------------------------------------------------ */
    static void tukar(Mahasiswa[] data, int i, int j) {
        Mahasiswa temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /* ------------------------------------------------------------------
       BUBBLE SORT
       Membandingkan pasangan elemen bersebelahan, tukar jika urutan
       salah, ulangi sampai seluruh data terurut naik (A-Z) berdasarkan
       nama.
       ------------------------------------------------------------------ */
    static void bubbleSort(Mahasiswa[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (bandingkanTeks(data[j].nama, data[j + 1].nama) > 0) {
                    tukar(data, j, j + 1);
                }
            }
        }
    }

    /* ------------------------------------------------------------------
       SELECTION SORT
       Tiap putaran mencari elemen dengan nama terkecil (alfabet) dari
       bagian data yang belum terurut, lalu ditukar ke posisi paling
       depan dari bagian tersebut.
       ------------------------------------------------------------------ */
    static void selectionSort(Mahasiswa[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            int idxTerkecil = i;
            for (int j = i + 1; j < n; j++) {
                if (bandingkanTeks(data[j].nama, data[idxTerkecil].nama) < 0) {
                    idxTerkecil = j;
                }
            }
            if (idxTerkecil != i) {
                tukar(data, i, idxTerkecil);
            }
        }
    }

    /* ------------------------------------------------------------------
       Method cetakTabel
       Menampilkan data dalam bentuk tabel Nama - Alamat.
       ------------------------------------------------------------------ */
    static void cetakTabel(Mahasiswa[] data) {
        System.out.println("+----------------------+----------------------+");
        System.out.printf("| %-20s | %-20s |%n", "Nama", "Alamat");
        System.out.println("+----------------------+----------------------+");
        for (Mahasiswa m : data) {
            System.out.printf("| %-20s | %-20s |%n", m.nama, m.alamat);
        }
        System.out.println("+----------------------+----------------------+\n");
    }

    public static void main(String[] args) {
        /* Data awal sesuai soal */
        Mahasiswa[] dataAsli = {
            new Mahasiswa("Fahmi", "Jakarta"),
            new Mahasiswa("Romi", "Solo"),
            new Mahasiswa("Andri", "Jakarta"),
            new Mahasiswa("Fadillah", "Banyuwangi"),
            new Mahasiswa("Ruli", "Bandung"),
            new Mahasiswa("Rudi", "Bali"),
            new Mahasiswa("Dendi", "Purwokerto"),
            new Mahasiswa("Zaki", "Madiun")
        };

        /* Salin data asli ke dua array kerja agar masing-masing algoritma
           mengurutkan data yang sama tanpa saling mempengaruhi */
        Mahasiswa[] dataBubble = salinData(dataAsli);
        Mahasiswa[] dataSelection = salinData(dataAsli);

        System.out.println("=====================================================");
        System.out.println("           DATA AWAL SEBELUM DIURUTKAN");
        System.out.println("=====================================================");
        cetakTabel(dataAsli);

        /* ---------- Proses Bubble Sort ---------- */
        bubbleSort(dataBubble);
        System.out.println("=====================================================");
        System.out.println("        HASIL SETELAH BUBBLE SORT (A-Z)");
        System.out.println("=====================================================");
        cetakTabel(dataBubble);

        /* ---------- Proses Selection Sort ---------- */
        selectionSort(dataSelection);
        System.out.println("=====================================================");
        System.out.println("       HASIL SETELAH SELECTION SORT (A-Z)");
        System.out.println("=====================================================");
        cetakTabel(dataSelection);
    }
}