import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    private static LinkedList<DataTugas> daftarTugas = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("===== TO-DO LIST PROGRAM =====");
            System.out.println("1. Input Tugas");
            System.out.println("2. Delete Tugas");
            System.out.println("3. Lihat list Tugas");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    InputTugas();
                    break;
                case 2:
                    DeleteTugas();
                    break;
                case 3:
                    LihatlistTugas();
                    break;
                case 4:
                    urutkanDaftarTugas();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void InputTugas() {
        System.out.print("Masukkan Nama Mata Kuliah: ");
        String mataKuliah = scanner.nextLine();
        System.out.print("Masukkan Nama Tugas: ");
        String namaTugas = scanner.nextLine();
        System.out.print("Masukkan Deadline Tugas: ");
        String deadline = scanner.nextLine();

        DataTugas tugasBaru = new DataTugas(mataKuliah, namaTugas, deadline);
        daftarTugas.addFirst(tugasBaru);
        System.out.println("Tugas berhasil ditambahkan.");
    }

    private static void DeleteTugas() {
        if (daftarTugas.isEmpty()) {
            System.out.println("Daftar tugas kosong.");
            return;
        }

        System.out.println("Pilihan penghapusan:");
        System.out.println("1. Hapus Tugas di Awal");
        System.out.println("2. Hapus Tugas di Akhir");
        System.out.println("3. Hapus Tugas berdasarkan Mata Kuliah");
        System.out.println("4. Hapus Tugas berdasarkan Nama Tugas");
        System.out.print("Pilihan Anda: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (pilihan) {
            case 1:
                daftarTugas.removeFirst();
                System.out.println("Tugas di awal daftar berhasil dihapus.");
                break;
            case 2:
                daftarTugas.removeLast();
                System.out.println("Tugas di akhir daftar berhasil dihapus.");
                break;
            case 3:
                hapusTugasBerdasarkanMataKuliah();
                break;
            case 4:
                hapusTugasBerdasarkanNamaTugas();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void hapusTugasBerdasarkanMataKuliah() {
        System.out.print("Masukkan Mata Kuliah yang ingin dihapus: ");
        String mataKuliah = scanner.nextLine();

        ListIterator<DataTugas> iterator = daftarTugas.listIterator();
        while (iterator.hasNext()) {
            DataTugas tugas = iterator.next();
            if (tugas.getMataKuliah().equalsIgnoreCase(mataKuliah)) {
                iterator.remove();
                System.out.println("Tugas berhasil dihapus.");
                return;
            }
        }
        System.out.println("Tidak ada tugas dengan Mata Kuliah tersebut.");
    }

    private static void hapusTugasBerdasarkanNamaTugas() {
        System.out.print("Masukkan Nama Tugas yang ingin dihapus: ");
        String namaTugas = scanner.nextLine();

        ListIterator<DataTugas> iterator = daftarTugas.listIterator();
        while (iterator.hasNext()) {
            DataTugas tugas = iterator.next();
            if (tugas.getNamaTugas().equalsIgnoreCase(namaTugas)) {
                iterator.remove();
                System.out.println("Tugas berhasil dihapus.");
                return;
            }
        }
        System.out.println("Tidak ada tugas dengan Nama Tugas tersebut.");
    }

    private static void LihatlistTugas() {
        if (daftarTugas.isEmpty()) {
            System.out.println("Daftar tugas kosong.");
            return;
        }

        System.out.println("===== DAFTAR TUGAS =====");
        for (DataTugas tugas : daftarTugas) {
            System.out.println("Mata Kuliah: " + tugas.getMataKuliah());
            System.out.println("Nama Tugas: " + tugas.getNamaTugas());
            System.out.println("Deadline: " + tugas.getDeadline());
            System.out.println("------------------------");
        }
    }

    private static void urutkanDaftarTugas() {
        if (daftarTugas.isEmpty()) {
            System.out.println("Daftar tugas kosong.");
            return;
        }

        System.out.println("Urutkan berdasarkan:");
        System.out.println("a. Deadline");
        System.out.println("b. Nama Mata Kuliah");
        System.out.println("c. Nama Tugas");
        System.out.print("Pilihan Anda: ");
        char pilihan = scanner.nextLine().charAt(0);

        switch (pilihan) {
            case 'a':
                daftarTugas.sort((t1, t2) -> t1.getDeadline().compareTo(t2.getDeadline()));
                break;
            case 'b':
                daftarTugas.sort((t1, t2) -> t1.getMataKuliah().compareTo(t2.getMataKuliah()));
                break;
            case 'c':
                daftarTugas.sort((t1, t2) -> t1.getNamaTugas().compareTo(t2.getNamaTugas()));
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        System.out.println("Daftar tugas berhasil diurutkan.");
    }
}
