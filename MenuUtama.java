import java.util.Scanner;

public class MenuUtama {
    public static void main(String[] args) {
        menuUtama();
    }

    static void menuUtama(){
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            // Header menu dengan garis pembatas
            System.out.println("=============================================");
            System.out.println("|         === Menu Utama E-Commerce ===     |");
            System.out.println("=============================================");
            System.out.printf("| %-3s | %-35s |\n", "No", "Menu");
            System.out.println("---------------------------------------------");
            System.out.printf("| %-3s | %-35s |\n", "1", "Pencarian dan Sorting Produk");
            System.out.printf("| %-3s | %-35s |\n", "2", "Produk Favorit dan Stok");
            System.out.printf("| %-3s | %-35s |\n", "3", "Riwayat Transaksi");
            System.out.printf("| %-3s | %-35s |\n", "4", "Kategori Produk");
            System.out.printf("| %-3s | %-35s |\n", "5", "Manajemen Akun Pengguna");
            System.out.printf("| %-3s | %-35s |\n", "6", "Sistem Gudang");
            System.out.printf("| %-3s | %-35s |\n", "0", "Keluar");
            System.out.println("=============================================");

            // Input pilihan menu
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();

            // Switch untuk mengeksekusi menu yang dipilih
            switch (pilihan) {
                case 1:
                    PencarianSorting.menu();
                    break;
                case 2:
                    ProdukFavorit.aMenu();
                    break;
                case 3:
                    RiwayatTransaksi.menu();
                    break;
                case 4:
                    KategoriProduk.menu();
                    break;
                case 5:
                    ManajemenAkun.menu();
                    break;
                case 6:
                    GrafGudang.menu();
                    break;
                case 0:
                    System.out.println("\nTerima kasih telah menggunakan layanan kami!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nPilihan tidak valid! Silakan coba lagi.");
                    break;
            }
            System.out.println(""); // Tambahkan baris kosong untuk pemisah

        } while (pilihan != 0);
    }
}
