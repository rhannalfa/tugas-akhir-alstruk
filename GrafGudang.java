import java.util.LinkedList;
import java.util.Scanner;

public class GrafGudang {
    static class Graph {
        private int vertices;
        private LinkedList<Integer>[] adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        // Menambahkan edge antara dua gudang (arah)
        void addEdge(int src, int dest) {
            adjList[src].add(dest);
        }

        // DFS (Depth First Search) untuk traversal
        void dfsUtil(int vertex, boolean[] visited) {
            visited[vertex] = true;
            System.out.print(vertex + " ");  // Menampilkan hasil DFS

            for (int neighbor : adjList[vertex]) {
                if (!visited[neighbor]) {
                    dfsUtil(neighbor, visited);
                }
            }
        }

        // Fungsi untuk menjalankan DFS dari vertex awal
        void dfs(int startVertex) {
            boolean[] visited = new boolean[vertices];
            System.out.print("Hasil DFS mulai dari Gudang " + startVertex + ": ");
            dfsUtil(startVertex, visited);
            System.out.println();
        }

        // BFS (Breadth First Search) untuk traversal
        void bfs(int startVertex) {
            boolean[] visited = new boolean[vertices];
            LinkedList<Integer> queue = new LinkedList<>();

            visited[startVertex] = true;
            queue.add(startVertex);

            System.out.print("Hasil BFS mulai dari Gudang " + startVertex + ": ");

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                System.out.print(vertex + " ");  // Menampilkan hasil BFS

                for (int neighbor : adjList[vertex]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            System.out.println();
        }

        // Menampilkan graf dengan tampilan lebih sederhana (hanya menggunakan |)
        void printGraph() {
            System.out.println("\n | ===================== Graf Gudang ========================= |");
            System.out.println(" | Gudang | Hubungan Gudang                                    |");
            System.out.println(" |--------|------------------------------------------------------|");
            for (int i = 0; i < vertices; i++) {
                System.out.print(" | Gudang " + i + " | ");
                if (adjList[i].isEmpty()) {
                    System.out.print("Tidak ada hubungan ");
                } else {
                    for (int neighbor : adjList[i]) {
                        System.out.print("Gudang " + neighbor + " ");
                    }
                }
                System.out.println(" |");
            }
            System.out.println(" ==============================================================");
        }
    }

    // Fungsi untuk menampilkan menu dengan tampilan yang lebih sederhana
    public static void displayMenu() {
        System.out.println("\n |================= Menu Gudang ===============|");
        System.out.println(" |No  | Menu                                   |");
        System.out.println(" |---------------------------------------------|");
        System.out.println(" | 1  | Tampilkan graf                         |");
        System.out.println(" | 2  | Tambah hubungan antar gudang           |");
        System.out.println(" | 3  | DFS (Depth First Search)               |");
        System.out.println(" | 4  | BFS (Breadth First Search)             |");
        System.out.println(" | 5  | Kembali ke Menu Utama                  |");
        System.out.println(" ===============================================");
    }

    // Program utama untuk menjalankan menu dan memilih menu
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int vertices = 6;  // Misalnya ada 6 gudang
        Graph graf = new Graph(vertices);

        // Contoh hubungan antar gudang (default)
        graf.addEdge(0, 1);
        graf.addEdge(0, 2);
        graf.addEdge(1, 3);
        graf.addEdge(1, 4);
        graf.addEdge(2, 4);
        graf.addEdge(3, 5);
        graf.addEdge(4, 5);

        while (true) {
            displayMenu();  // Menampilkan menu utama
            System.out.print("Pilih menu: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    graf.printGraph();  // Menampilkan graf
                    break;
                case 2:
                    System.out.print("Masukkan gudang asal dan tujuan untuk hubungan: ");
                    int src = sc.nextInt();
                    int dest = sc.nextInt();
                    graf.addEdge(src, dest);  // Menambahkan hubungan antar gudang
                    System.out.println("Hubungan antara Gudang " + src + " dan Gudang " + dest + " telah ditambahkan.");
                    break;
                case 3:
                    System.out.print("Masukkan gudang awal untuk DFS: ");
                    int dfsStart = sc.nextInt();
                    graf.dfs(dfsStart);  // Menampilkan hasil DFS
                    break;
                case 4:
                    System.out.print("Masukkan gudang awal untuk BFS: ");
                    int bfsStart = sc.nextInt();
                    graf.bfs(bfsStart);  // Menampilkan hasil BFS
                    break;
                case 5:
                    System.out.println("Kembali ke Menu Utama...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        }
    }
}
