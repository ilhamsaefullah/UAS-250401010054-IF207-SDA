import java.util.Scanner;
 
public class BinarySearch {
 
    static final int N = 9;
 
    /* Kelas sederhana untuk pasangan nilai dan indeks asli (1-indexed) */
    static class Elemen {
        int nilai;
        int indeksAsli;
 
        Elemen(int nilai, int indeksAsli) {
            this.nilai = nilai;
            this.indeksAsli = indeksAsli;
        }
    }
 
    /* ------------------------------------------------------------------
       Bubble Sort manual, STABIL (hanya menukar jika benar-benar lebih
       besar) sehingga untuk nilai yang sama, urutan indeks asli tetap
       terjaga (misalnya 50 di indeks 6 tetap tercatat sebelum 50 di
       indeks 8).
       ------------------------------------------------------------------ */
    static void bubbleSort(Elemen[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (data[j].nilai > data[j + 1].nilai) {
                    Elemen temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }
 
    /* ------------------------------------------------------------------
       Binary Search manual (iteratif).
       Mengembalikan posisi (indeks pada array yang SUDAH terurut) salah
       satu elemen yang bernilai 'target', atau -1 jika tidak ditemukan.
       ------------------------------------------------------------------ */
    static int binarySearch(Elemen[] data, int target) {
        int kiri = 0, kanan = data.length - 1;
        while (kiri <= kanan) {
            int tengah = (kiri + kanan) / 2;
            System.out.println("   Cek indeks tengah (posisi terurut ke-" + (tengah + 1)
                    + ") -> nilai = " + data[tengah].nilai);
            if (data[tengah].nilai == target) {
                return tengah;
            } else if (data[tengah].nilai < target) {
                kiri = tengah + 1;
            } else {
                kanan = tengah - 1;
            }
        }
        return -1;
    }
 
    /* ------------------------------------------------------------------
       Mencari dan mencetak SELURUH indeks asli untuk nilai yang dicari.
       Karena array sudah terurut, nilai yang sama pasti bersebelahan,
       sehingga cukup melebar ke kiri dan ke kanan dari titik ketemu.
       ------------------------------------------------------------------ */
    static void cariDanCetak(Elemen[] data, int target) {
        System.out.println("Pencarian angka " + target + ":");
        int posisi = binarySearch(data, target);
 
        if (posisi == -1) {
            System.out.println("Hasil  : Angka " + target + " tidak ada dalam array\n");
            return;
        }
 
        int kiri = posisi;
        while (kiri - 1 >= 0 && data[kiri - 1].nilai == target) {
            kiri--;
        }
        int kanan = posisi;
        while (kanan + 1 < data.length && data[kanan + 1].nilai == target) {
            kanan++;
        }
 
        StringBuilder sb = new StringBuilder();
        sb.append("Hasil  : Angka ").append(target).append(" ada di indeks ke ");
        int jumlah = kanan - kiri + 1;
        int urutan = 0;
        for (int i = kiri; i <= kanan; i++) {
            urutan++;
            sb.append(data[i].indeksAsli);
            if (urutan < jumlah - 1) {
                sb.append(", ");
            } else if (urutan == jumlah - 1) {
                sb.append(" dan ");
            }
        }
        System.out.println(sb.toString() + "\n");
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        /* Array awal sesuai soal (belum terurut) */
        int[] arrayAsli = {19, 40, 10, 90, 2, 50, 60, 50, 1};
 
        Elemen[] data = new Elemen[N];
        for (int i = 0; i < N; i++) {
            data[i] = new Elemen(arrayAsli[i], i + 1);
        }
 
        System.out.println("=====================================================");
        System.out.println("ARRAY ASLI (sebelum diurutkan)");
        System.out.println("=====================================================");
        for (int i = 0; i < N; i++) {
            System.out.println("Indeks " + (i + 1) + " : " + arrayAsli[i]);
        }
        System.out.println();
 
        /* Urutkan dulu supaya Binary Search bisa dipakai */
        bubbleSort(data);
 
        System.out.println("=====================================================");
        System.out.println("ARRAY SETELAH DIURUTKAN (nilai -> indeks asli)");
        System.out.println("=====================================================");
        for (int i = 0; i < N; i++) {
            System.out.println("Posisi terurut " + (i + 1) + " : nilai = " + data[i].nilai
                    + "  (indeks asli = " + data[i].indeksAsli + ")");
        }
        System.out.println();
 
        System.out.println("=====================================================");
        System.out.println("PENCARIAN DATA (INPUT DARI PENGGUNA)");
        System.out.println("=====================================================\n");
 
        String lagi = "y";
        while (lagi.equalsIgnoreCase("y")) {
            System.out.print("Masukkan angka yang ingin dicari : ");
            int target = sc.nextInt();
            System.out.println();
 
            cariDanCetak(data, target);
 
            System.out.print("Cari angka lain? (y/n): ");
            lagi = sc.next();
            System.out.println();
        }
 
        System.out.println("Program selesai.");
        sc.close();
    }
}