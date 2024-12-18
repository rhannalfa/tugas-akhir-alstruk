import java.util.LinkedList;
import java.util.Scanner;

public class RiwayatTransaksi {
    static LinkedList<String> riwayat = new LinkedList<>();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n================= Menu Utama ================");
            System.out.println("| 1 | Kelola Riwayat Transaksi              |");
            System.out.println("| 0 | Kembali ke Menu Utama                 |");
            System.out.println("=============================================");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1 :
                    menuRiwayat();
                    break;
                case 0 :
                    System.out.println("Kembali ke Menu Utama...");
                    return;
                default :
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        } while (pilihan != 0);

        scanner.close();
    }

    public static void menuRiwayat() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=============== Riwayat Transaksi ===============");
            System.out.println("| 1 | Tambah Transaksi                          |");
            System.out.println("| 2 | Lihat Riwayat Transaksi                   |");
            System.out.println("| 3 | Hapus Transaksi                           |");
            System.out.println("| 4 | Cari Transaksi                            |");
            System.out.println("| 5 | Jumlah Transaksi                          |");
            System.out.println("| 0 | Kembali ke Sub Utama                      |");
            System.out.println("=================================================");
            System.out.print("Pilih sub-menu: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1 -> tambahTransaksi();
                case 2 -> lihatRiwayat();
                case 3 -> hapusTransaksi();
                case 4 -> cariTransaksi();
                case 5 -> jumlahTransaksi();
                case 0 -> System.out.println("Kembali ke Sub Utama...");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }

    static void tambahTransaksi() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nMasukkan deskripsi transaksi: ");
        String transaksi = scanner.nextLine();
        riwayat.add(transaksi);
        System.out.println("Transaksi telah ditambahkan.");
    }

    static void lihatRiwayat() {
        if (riwayat.isEmpty()) {
            System.out.println("\nTidak ada transaksi yang tercatat.");
        } else {
            System.out.println("\n=============== Riwayat Transaksi ==============");
            System.out.println("| No | Deskripsi Transaksi                     |");
            System.out.println("|----|-----------------------------------------|");
            for (int i = 0; i < riwayat.size(); i++) {
                System.out.printf("| %-2d | %-42s |\n", (i + 1), riwayat.get(i));
            }
            System.out.println("================================================");
        }
    }

    static void hapusTransaksi() {
        Scanner scanner = new Scanner(System.in);
        if (riwayat.isEmpty()) {
            System.out.println("\nTidak ada transaksi untuk dihapus.");
        } else {
            lihatRiwayat();
            System.out.print("\nMasukkan nomor transaksi yang ingin dihapus: ");
            int index = scanner.nextInt();
            if (index > 0 && index <= riwayat.size()) {
                String removed = riwayat.remove(index - 1);
                System.out.println("Transaksi '" + removed + "' telah dihapus.");
            } else {
                System.out.println("Nomor transaksi tidak valid.");
            }
        }
    }

    static void cariTransaksi() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nMasukkan kata kunci pencarian: ");
        String keyword = scanner.nextLine();
        boolean found = false;

        System.out.println("\nHasil Pencarian:");
        for (int i = 0; i < riwayat.size(); i++) {
            if (riwayat.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("| %-2d | %-42s |\n", (i + 1), riwayat.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("Tidak ada transaksi yang cocok dengan kata kunci '" + keyword + "'.");
        }
    }

    static void jumlahTransaksi() {
        System.out.println("\nJumlah total transaksi: " + riwayat.size());
    }
}
