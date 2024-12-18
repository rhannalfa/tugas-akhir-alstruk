import java.util.*;

public class KategoriProduk {
    static class Node {
        String data;
        List<Node> children; // List untuk menyimpan anak-anak dari node ini

        public Node(String data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    static LinkedList<Node> indukKategori = new LinkedList<>(); // Menyimpan beberapa root kategori

    public static void menu() {
        // Inisialisasi awal dengan induk dan bawah induk
        Node elektronik = new Node("Elektronik");
        elektronik.children.add(new Node("Laptop"));
        elektronik.children.add(new Node("Smartphone"));
        elektronik.children.add(new Node("Tablet"));
        elektronik.children.add(new Node("Kamera"));

        Node kosmetik = new Node("Kosmetik");
        kosmetik.children.add(new Node("Lipstik"));
        kosmetik.children.add(new Node("Foundation"));
        kosmetik.children.add(new Node("Maskara"));
        kosmetik.children.add(new Node("Blush On"));
        kosmetik.children.add(new Node("Eyeshadow"));

        Node fashion = new Node("Fashion");
        fashion.children.add(new Node("T-Shirt"));
        fashion.children.add(new Node("Jeans"));
        fashion.children.add(new Node("Jacket"));
        fashion.children.add(new Node("Dress"));
        fashion.children.add(new Node("Sneakers"));

        Node kebutuhanRumah = new Node("Kebutuhan Rumah");
        kebutuhanRumah.children.add(new Node("Rak Buku"));
        kebutuhanRumah.children.add(new Node("Meja Lipat"));
        kebutuhanRumah.children.add(new Node("Kursi Santai"));
        kebutuhanRumah.children.add(new Node("Lampu Hias"));
        kebutuhanRumah.children.add(new Node("Karpet"));

        Node kebutuhanAnak = new Node("Kebutuhan Anak");
        kebutuhanAnak.children.add(new Node("Popok Bayi"));
        kebutuhanAnak.children.add(new Node("Mainan Edukas"));
        kebutuhanAnak.children.add(new Node("Baju Bayi"));
        kebutuhanAnak.children.add(new Node("Box Bayi"));
        kebutuhanAnak.children.add(new Node("Stroller"));

        indukKategori.add(elektronik);
        indukKategori.add(kosmetik);
        indukKategori.add(fashion);
        indukKategori.add(kebutuhanRumah);
        indukKategori.add(kebutuhanAnak);

        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n================ Menu Kategori Produk ================");
            System.out.println("| 1 | Lihat Semua Kategori                           |");
            System.out.println("| 2 | Tambah Induk Kategori Baru                     |");
            System.out.println("| 3 | Tambah Kategori di Bawah Induk                 |");
            System.out.println("| 4 | Cari Kategori                                  |");
            System.out.println("| 5 | Hapus Kategori                                 |");
            System.out.println("| 0 | Kembali ke Menu Utama                          |");
            System.out.println("======================================================");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> lihatSemuaKategori();
                case 2 -> tambahIndukKategori();
                case 3 -> tambahKategoriAnak();
                case 4 -> cariKategori();
                case 5 -> hapusKategori();
                case 0 -> System.out.println("Kembali ke menu utama...");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    static void tambahIndukKategori() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama induk kategori baru: ");
        String nama = scanner.nextLine();

        indukKategori.add(new Node(nama));
        System.out.println("Induk kategori \"" + nama + "\" berhasil ditambahkan.");
    }

    static void tambahKategoriAnak() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama induk kategori: ");
        String induk = scanner.nextLine();
        System.out.print("Masukkan nama kategori baru: ");
        String anak = scanner.nextLine();

        Node parentNode = cariIndukKategori(induk);
        if (parentNode != null) {
            parentNode.children.add(new Node(anak));
            System.out.println("Kategori \"" + anak + "\" berhasil ditambahkan di bawah \"" + induk + "\".");
        } else {
            System.out.println("Induk kategori \"" + induk + "\" tidak ditemukan.");
        }
    }

    static void cariKategori() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama kategori yang dicari: ");
        String nama = scanner.nextLine();

        boolean ditemukan = false;
        for (Node root : indukKategori) {
            if (cariNode(root, nama) != null) {
                System.out.println("Kategori \"" + nama + "\" ditemukan.");
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Kategori \"" + nama + "\" tidak ditemukan.");
        }
    }

    static void hapusKategori() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama kategori yang ingin dihapus: ");
        String nama = scanner.nextLine();

        // Cek apakah kategori yang ingin dihapus adalah induk kategori
        Iterator<Node> iterator = indukKategori.iterator();
        while (iterator.hasNext()) {
            Node root = iterator.next();
            if (root.data.equalsIgnoreCase(nama)) {
                iterator.remove(); // Hapus induk kategori dari list
                System.out.println("Induk kategori \"" + nama + "\" berhasil dihapus.");
                return;
            }
        }

        // Jika bukan induk, cari di bawah induk
        for (Node root : indukKategori) {
            if (hapusNode(root, nama)) {
                System.out.println("Kategori \"" + nama + "\" berhasil dihapus.");
                return;
            }
        }
        System.out.println("Kategori \"" + nama + "\" tidak ditemukan.");
    }

    static void lihatSemuaKategori() {
        System.out.println("\n==================== Semua Kategori Produk =====================");
        for (Node root : indukKategori) {
            System.out.println("| Induk: " + root.data);
            if (root.children.isEmpty()) {
                System.out.println("|  (Belum ada kategori di bawah induk ini)                   |");
            } else {
                printKategori(root, 1);
            }
        }
        System.out.println("===============================================================");
    }

    static void printKategori(Node node, int level) {
        for (Node child : node.children) {
            System.out.println("|  ".repeat(level) + "- " + child.data);
            printKategori(child, level + 1);
        }
    }

    static Node cariIndukKategori(String nama) {
        for (Node root : indukKategori) {
            if (root.data.equalsIgnoreCase(nama)) {
                return root;
            }
        }
        return null;
    }

    static Node cariNode(Node node, String nama) {
        if (node == null) return null;
        if (node.data.equalsIgnoreCase(nama)) return node;

        for (Node child : node.children) {
            Node result = cariNode(child, nama);
            if (result != null) return result;
        }
        return null;
    }

    static boolean hapusNode(Node parent, String nama) {
        Iterator<Node> iterator = parent.children.iterator();
        while (iterator.hasNext()) {
            Node child = iterator.next();
            if (child.data.equalsIgnoreCase(nama)) {
                iterator.remove();
                return true;
            } else if (hapusNode(child, nama)) {
                return true;
            }
        }
        return false;
    }
}
