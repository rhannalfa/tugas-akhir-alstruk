import java.util.ArrayList;
import java.util.Scanner;

public class ProdukFavorit {
    static ArrayList<String> favorit = new ArrayList<>();
    static ArrayList<Integer> stok = new ArrayList<>();
    static ArrayList<String> produk = new ArrayList<>();

    static void aMenu() {
        // Inisialisasi data awal
        produk.add("Laptop");
        produk.add("Smartphone");
        produk.add("Tablet");
        stok.add(10);
        stok.add(5);
        stok.add(20);

        while (true) {
            ProdukFavorit.menu();
        }
    }

    public static void menu() {
        int pilihan;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n =============== Sistem Manajemen Produk ==============");
            System.out.println(" | No | Menu                                          |");
            System.out.println(" |----------------------------------------------------|");
            System.out.println(" | 1  | Tambah Produk Favorit                         |");
            System.out.println(" | 2  | Lihat Stok Produk                             |");
            System.out.println(" | 3  | Tambah Produk Baru                            |");
            System.out.println(" | 4  | Perbarui Stok Produk                          |");
            System.out.println(" | 5  | Hapus Produk Favorit                          |");
            System.out.println(" | 6  | Lihat Daftar Produk Favorit                   |");
            System.out.println(" | 7  | Kembali ke Menu Utama                         |");
            System.out.println(" ======================================================");
            System.out.print("Pilih sub-menu: ");
            
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Input tidak valid!");
                scanner.nextLine(); // Clear invalid input
                return;
            }

            switch (pilihan) {
                case 1 -> tambahFavorit();
                case 2 -> lihatStok();
                case 3 -> tambahProdukBaru();
                case 4 -> perbaruiStok();
                case 5 -> hapusFavorit();
                case 6 -> lihatFavorit();
                case 7 -> {
                    System.out.println("Kembali ke Menu Utama...");
                    MenuUtama.menuUtama();
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 7);
    }

    static void tambahFavorit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n | ========================= Tambah Produk Favorit ===================== |");
        
        // Tampilkan daftar produk yang tersedia
        if (produk.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia.");
            return;
        }

        System.out.println(" | Produk yang tersedia:                                                 |");
        for (int i = 0; i < produk.size(); i++) {
            System.out.println(" | " + (i + 1) + ". " + produk.get(i) + "                              |");
        }

        System.out.print("Pilih nomor produk untuk ditambahkan ke favorit: ");
        try {
            int pilihanProduk = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (pilihanProduk >= 0 && pilihanProduk < produk.size()) {
                String namaProduk = produk.get(pilihanProduk);
                
                // Cek apakah produk sudah ada di favorit
                if (favorit.contains(namaProduk)) {
                    System.out.println("Produk sudah ada di daftar favorit!");
                } else {
                    favorit.add(namaProduk);
                    System.out.println("Produk " + namaProduk + " telah ditambahkan ke daftar favorit.");
                }
            } else {
                System.out.println("Pilihan produk tidak valid!");
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid!");
            scanner.nextLine(); // Clear invalid input
        }
    }

    static void lihatStok() {
        System.out.println("\n | =========================== Stok Produk ============================ |");
        if (produk.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia.");
            return;
        }
        

        System.out.println(" | Produk       | Stok        |");
        System.out.println(" |--------------|-------------|");
        for (int i = 0; i < produk.size(); i++) {
            System.out.printf(" | %-12s | %-11d |%n", produk.get(i), stok.get(i));
        }
        System.out.println(" | ===================================================================== |");
    }

    static void tambahProdukBaru() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n | ========================= Tambah Produk Baru ======================= |");
        
        System.out.print("Masukkan nama produk: ");
        String namaProduk = scanner.nextLine();

        // Cek apakah produk sudah ada
        if (produk.contains(namaProduk)) {
            System.out.println("Produk sudah ada!");
            return;
        }

        System.out.print("Masukkan stok awal: ");
        try {
            int stokAwal = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (stokAwal >= 0) {
                produk.add(namaProduk);
                stok.add(stokAwal);
                System.out.println("Produk berhasil ditambahkan!");
            } else {
                System.out.println("Stok tidak boleh negatif!");
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid!");
            scanner.nextLine(); // Clear invalid input
        }
    }

    static void perbaruiStok() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n | ======================= Perbarui Stok Produk ======================= |");
        
        lihatStok(); // Tampilkan stok saat ini

        System.out.print("Pilih nomor produk yang ingin diperbarui stoknya: ");
        try {
            int pilihanProduk = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (pilihanProduk >= 0 && pilihanProduk < produk.size()) {
                System.out.print("Masukkan jumlah stok baru: ");
                int stokBaru = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (stokBaru >= 0) {
                    stok.set(pilihanProduk, stokBaru);
                    System.out.println("Stok " + produk.get(pilihanProduk) + " berhasil diperbarui!");
                } else {
                    System.out.println("Stok tidak boleh negatif!");
                }
            } else {
                System.out.println("Pilihan produk tidak valid!");
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid!");
            scanner.nextLine(); // Clear invalid input
        }
    }

    static void hapusFavorit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n | ======================== Hapus Produk Favorit ===================== |");
        
        lihatFavorit(); // Tampilkan daftar favorit

        if (favorit.isEmpty()) {
            System.out.println("Tidak ada produk favorit!");
            return;
        }

        System.out.print("Pilih nomor produk yang ingin dihapus dari favorit: ");
        try {
            int pilihanProduk = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (pilihanProduk >= 0 && pilihanProduk < favorit.size()) {
                String namaProduk = favorit.get(pilihanProduk);
                favorit.remove(pilihanProduk);
                System.out.println("Produk " + namaProduk + " telah dihapus dari daftar favorit.");
            } else {
                System.out.println("Pilihan produk tidak valid!");
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid!");
            scanner.nextLine(); // Clear invalid input
        }
    }

    static void lihatFavorit() {
        System.out.println("\n | ===================== Daftar Produk Favorit ======================= |");
        if (favorit.isEmpty()) {
            System.out.println("Tidak ada produk favorit.");
            return;
        }

        System.out.println(" | No | Produk Favorit                                                |");
        System.out.println(" |----|---------------------------------------------------------------|");
        for (int i = 0; i < favorit.size(); i++) {
            System.out.printf(" | %-2d | %-60s |%n", (i + 1), favorit.get(i));
        }
        System.out.println(" | ==================================================================== |");
    }
}
