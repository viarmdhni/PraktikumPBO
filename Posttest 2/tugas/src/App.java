import java.util.ArrayList;
import java.util.Scanner;

class Treatment {
    //private
    private String nama;
    //default
    int durasi;
    //public
    public int harga;

    public Treatment(String nama, int durasi, int harga) {
        this.nama = nama;
        this.durasi = durasi;
        this.harga = harga;
    }

    //getter pada getNama
    public String getNama() {
        return nama;
    }
    //getter pada getDurasi
    public int getDurasi() {
        return durasi;
    }
    //getter pada getHarga
    public int getHarga() {
        return harga;
    }

    // Protected setter untuk durasi
    protected void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    // setter pada (setHarga)
    public void setHarga(int harga) {
        this.harga = harga;
    }
}

public class App {
    private static ArrayList<Treatment> dataTreatment = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║   Selamat Datang di DY Clinic Treatment   ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. CRUD Treatment");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1 -> menuCRUD();
                case 2 -> {
                    exit = true;
                    System.out.println("\nTerima kasih telah menggunakan sistem DY Clinic!");
                }
                default -> System.out.println("Menu tidak valid.");
            }
        }
    }

    private static void menuCRUD() {
        boolean exitCRUD = false;
        while (!exitCRUD) {
            System.out.println("\n╔══════════════════╗");
            System.out.println("║  CRUD Treatment  ║");
            System.out.println("╚══════════════════╝");
            System.out.println("1. Tambah Treatment");
            System.out.println("2. Lihat Semua Treatment");
            System.out.println("3. Ubah Treatment");
            System.out.println("4. Hapus Treatment");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            int menuCRUD = scanner.nextInt();
            scanner.nextLine();

            switch (menuCRUD) {
                case 1 -> tambahTreatment();
                case 2 -> tampilkanSemuaTreatment();
                case 3 -> ubahTreatment();
                case 4 -> hapusTreatment();
                case 5 -> exitCRUD = true;
                default -> System.out.println("Menu tidak valid.");
            }
        }
    }

    private static void tambahTreatment() {
        System.out.print("Masukkan nama treatment: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan durasi (menit): ");
        int durasi = scanner.nextInt();
        System.out.print("Masukkan harga: ");
        int harga = scanner.nextInt();

        dataTreatment.add(new Treatment(nama, durasi, harga));
        System.out.println("Treatment berhasil ditambahkan.");
    }

    private static void tampilkanSemuaTreatment() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║       DAFTAR TREATMENT CLINIC      ║");
        System.out.println("╚════════════════════════════════════╝");
        if (dataTreatment.isEmpty()) {
            System.out.println("Tidak ada data.");
            return;
        }
        for (int i = 0; i < dataTreatment.size(); i++) {
            Treatment treatment = dataTreatment.get(i);
            System.out.printf("%d. %s \nDurasi: %d menit \nHarga: Rp%d\n",
                    i + 1, treatment.getNama(), treatment.getDurasi(), treatment.getHarga());
        }
    }

    private static void ubahTreatment() {
        tampilkanSemuaTreatment();
        System.out.print("Masukkan nomor treatment yang ingin diubah: ");
        int nomor = scanner.nextInt();
        scanner.nextLine();

        if (nomor < 1 || nomor > dataTreatment.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        Treatment treatment = dataTreatment.get(nomor - 1);
        // setter pada (setDurasi)
        System.out.print("Masukkan durasi baru (menit): ");
        treatment.setDurasi(scanner.nextInt());
        // setter pada (setHarga)
        System.out.print("Masukkan harga baru: ");
        treatment.setHarga(scanner.nextInt());

        System.out.println("Data treatment berhasil diperbarui.");
    }

    private static void hapusTreatment() {
        tampilkanSemuaTreatment();
        System.out.print("Masukkan nomor treatment yang ingin dihapus: ");
        int nomor = scanner.nextInt();
        scanner.nextLine();

        if (nomor < 1 || nomor > dataTreatment.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        dataTreatment.remove(nomor - 1);
        System.out.println("Treatment berhasil dihapus.");
    }
}