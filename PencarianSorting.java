import java.util.*;

import java.util.*;

class Produk {
    String nama;
    String kategori;
    int harga;
    double rating;
    boolean isUMKM;
    int diskon; // dalam persen

    public Produk(String nama, String kategori, int harga, double rating, boolean isUMKM, int diskon) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.rating = rating;
        this.isUMKM = isUMKM;
        this.diskon = diskon;
    }

    @Override
    public String toString() {
        int hargaDiskon = harga - (harga * diskon / 100);
        return nama + " (" + kategori + ") - Rp" + hargaDiskon + " | Rating: " + rating + 
               (isUMKM ? " | UMKM" : "") + (diskon > 0 ? " | Diskon: " + diskon + "%" : "");
    }
}

public class PencarianSorting {
    static List<Produk> produkList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        inisialisasiProduk();
        int pilihan;
        do {
            System.out.println("\n+==========================================+");
            System.out.println("|         MENU PENCARIAN & SORTING         |");
            System.out.println("+==========================================+");
            System.out.println("| 1. Menampilkan Semua Kategori            |");
            System.out.println("| 2. Menampilkan Produk UMKM               |");
            System.out.println("| 3. Menampilkan Produk Berdasarkan Harga  |");
            System.out.println("| 4. Sorting Berdasarkan Kategori Harga    |");
            System.out.println("| 5. Kembali ke Menu Utama                 |");
            System.out.println("+==========================================+");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tampilkanKategori();
                    break;
                case 2:
                    tampilkanProdukUMKM();
                    break;
                case 3:
                    sortingHargaDenganKategori();
                    break;
                case 4:
                    sortingKategoriHarga();
                    break;
                case 5:
                    System.out.println("Kembali ke menu utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    // 1. Menampilkan semua kategori
    public static void tampilkanKategori() {
        Set<String> kategoriSet = new HashSet<>();
        for (Produk p : produkList) {
            kategoriSet.add(p.kategori);
        }
        System.out.println("\n+--------------------+");
        System.out.println("|   KATEGORI PRODUK  |");
        System.out.println("+--------------------+");
        int index = 1;
        for (String kategori : kategoriSet) {
            System.out.printf("| %d. %-15s |\n", index++, kategori);
        }
        System.out.println("+--------------------+");
    }

    // 2. Menampilkan produk UMKM
    public static void tampilkanProdukUMKM() {
        System.out.println("\n+================== PRODUK UMKM ==================+");
        boolean found = false;
        for (Produk p : produkList) {
            if (p.isUMKM) {
                displayProdukRow(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("|          Tidak ada produk UMKM tersedia         |");
        }
        System.out.println("+================================================+");
    }

    // 3. Sorting berdasarkan harga lalu memilih kategori
    public static void sortingHargaDenganKategori() {
        System.out.println("\nPilih urutan harga:");
        System.out.println("1. Termurah ke Termahal");
        System.out.println("2. Termahal ke Termurah");
        System.out.print("Pilihan: ");
        int urutan = scanner.nextInt();
        boolean ascending = urutan == 1;

        sortProdukByHarga(ascending);
        tampilkanKategori();
        System.out.print("Pilih kategori (masukkan nama): ");
        scanner.nextLine();
        String kategoriDipilih = scanner.nextLine();

        System.out.println("\n+================ DAFTAR PRODUK =================+");
        boolean found = false;
        for (Produk p : produkList) {
            if (p.kategori.equalsIgnoreCase(kategoriDipilih)) {
                displayProdukRow(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("|       Tidak ada produk pada kategori ini       |");
        }
        System.out.println("+===============================================+");
    }

    // 4. Sorting berdasarkan kategori harga ascending/descending
    public static void sortingKategoriHarga() {
        System.out.println("\nPilih urutan harga:");
        System.out.println("1. Termurah ke Termahal");
        System.out.println("2. Termahal ke Termurah");
        System.out.print("Pilihan: ");
        int urutan = scanner.nextInt();
        boolean ascending = urutan == 1;

        sortProdukByHarga(ascending);
        System.out.println("\n+================ DAFTAR PRODUK =================+");
        for (Produk p : produkList) {
            displayProdukRow(p);
        }
        System.out.println("+===============================================+");
    }

    // Method menampilkan satu baris produk
    public static void displayProdukRow(Produk p) {
        int hargaDiskon = p.harga - (p.harga * p.diskon / 100);
        System.out.printf("| %-20s | %-15s | Rp%-9d | %-4.1f |\n", p.nama, p.kategori, hargaDiskon, p.rating);
    }

    // Sorting produk berdasarkan harga
    public static void sortProdukByHarga(boolean ascending) {
        produkList.sort((p1, p2) -> ascending ? p1.harga - p2.harga : p2.harga - p1.harga);
    }

    // Inisialisasi produk
    public static void inisialisasiProduk() {
        produkList.add(new Produk("Laptop", "Elektronik", 15000000, 4.5, false, 10));
        produkList.add(new Produk("Smartphone", "Elektronik", 7000000, 4.8, true, 5));
        produkList.add(new Produk("Tablet", "Elektronik", 5000000, 4.2, false, 0));
        produkList.add(new Produk("Kamera", "Elektronik", 9000000, 4.6, true, 15));

        produkList.add(new Produk("Lipstik", "Kosmetik", 150000, 4.3, true, 20));
        produkList.add(new Produk("Foundation", "Kosmetik", 250000, 4.6, false, 0));
        produkList.add(new Produk("Maskara", "Kosmetik", 180000, 4.4, true, 5));
        produkList.add(new Produk("Blush On", "Kosmetik", 200000, 4.5, false, 10));
        produkList.add(new Produk("Eyeshadow", "Kosmetik", 300000, 4.7, true, 25));

        produkList.add(new Produk("T-Shirt", "Fashion", 120000, 4.1, true, 15));
        produkList.add(new Produk("Jeans", "Fashion", 300000, 4.4, false, 10));
        produkList.add(new Produk("Jacket", "Fashion", 500000, 4.6, true, 5));
        produkList.add(new Produk("Dress", "Fashion", 400000, 4.3, false, 20));
        produkList.add(new Produk("Sneakers", "Fashion", 750000, 4.8, true, 25));

        produkList.add(new Produk("Rak Buku", "Kebutuhan Rumah", 450000, 4.2, false, 10));
        produkList.add(new Produk("Meja Lipat", "Kebutuhan Rumah", 300000, 4.5, true, 15));
        produkList.add(new Produk("Kursi Santai", "Kebutuhan Rumah", 600000, 4.7, true, 5));
        produkList.add(new Produk("Lampu Hias", "Kebutuhan Rumah", 200000, 4.4, false, 20));
        produkList.add(new Produk("Karpet", "Kebutuhan Rumah", 350000, 4.3, true, 0));

        produkList.add(new Produk("Popok Bayi", "Kebutuhan Anak", 150000, 4.6, false, 10));
        produkList.add(new Produk("Mainan Edukasi", "Kebutuhan Anak", 250000, 4.8, true, 20));
        produkList.add(new Produk("Baju Bayi", "Kebutuhan Anak", 100000, 4.3, true, 15));
        produkList.add(new Produk("Box Bayi", "Kebutuhan Anak", 900000, 4.5, false, 5));
        produkList.add(new Produk("Stroller", "Kebutuhan Anak", 1500000, 4.7, true, 0));
    }
}
