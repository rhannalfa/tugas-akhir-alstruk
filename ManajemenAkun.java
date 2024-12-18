import java.util.Hashtable;
import java.util.Scanner;

public class ManajemenAkun {
    static Hashtable<String, String> akun = new Hashtable<>();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n================= Manajemen Akun ==================");
            System.out.println("| 1 | Tambah Akun                                 |");
            System.out.println("| 2 | Lihat Akun                                  |");
            System.out.println("| 3 | Hapus Akun                                  |");
            System.out.println("| 4 | Update Password Akun                        |");
            System.out.println("| 5 | Cari Akun                                   |");
            System.out.println("| 0 | Kembali ke Menu Utama                       |");
            System.out.println("==================================================");
            System.out.print("Pilih sub-menu: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Input tidak valid! Masukkan angka.");
                System.out.print("Pilih sub-menu: ");
                scanner.next();
            }
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1 -> tambahAkun();
                case 2 -> lihatAkun();
                case 3 -> hapusAkun();
                case 4 -> updatePassword();
                case 5 -> cariAkun();
                case 0 -> System.out.println("Kembali ke Menu Utama...");
                default -> System.out.println("Pilihan tidak valid! Masukkan angka antara 0-5.");
            }
        } while (pilihan != 0);
    }

    static void tambahAkun() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();

        if (akun.containsKey(username)) {
            System.out.println("Username sudah terdaftar. Silakan gunakan username lain.");
            return;
        }

        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        akun.put(username, password);
        System.out.println("Akun berhasil ditambahkan!");
    }

    static void lihatAkun() {
        if (akun.isEmpty()) {
            System.out.println("Belum ada akun yang terdaftar.");
        } else {
            System.out.println("\n==================== Daftar Akun =====================");
            System.out.println("| No | Username                            |");
            System.out.println("=======================================================");
            int count = 1;
            for (String user : akun.keySet()) {
                System.out.printf("| %-2d | %-35s |\n", count, user);
                count++;
            }
            System.out.println("=======================================================");
        }
    }

    static void hapusAkun() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan username yang ingin dihapus: ");
        String username = scanner.nextLine();

        if (akun.containsKey(username)) {
            System.out.print("Apakah Anda yakin ingin menghapus akun '" + username + "'? (y/n): ");
            String konfirmasi = scanner.nextLine();
            if (konfirmasi.equalsIgnoreCase("y")) {
                akun.remove(username);
                System.out.println("Akun dengan username '" + username + "' berhasil dihapus.");
            } else {
                System.out.println("Penghapusan akun dibatalkan.");
            }
        } else {
            System.out.println("Username tidak ditemukan.");
        }
    }

    static void updatePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();

        if (!akun.containsKey(username)) {
            System.out.println("Username tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan password lama: ");
        String oldPassword = scanner.nextLine();

        if (!akun.get(username).equals(oldPassword)) {
            System.out.println("Password lama tidak sesuai.");
            return;
        }

        System.out.print("Masukkan password baru: ");
        String newPassword = scanner.nextLine();
        akun.put(username, newPassword);
        System.out.println("Password berhasil diperbarui!");
    }

    static void cariAkun() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan username yang ingin dicari: ");
        String username = scanner.nextLine();

        if (akun.containsKey(username)) {
            System.out.println("Akun ditemukan!\nUsername: " + username);
        } else {
            System.out.println("Akun dengan username '" + username + "' tidak ditemukan.");
        }
    }
}
