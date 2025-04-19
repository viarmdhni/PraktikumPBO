import java.util.ArrayList;
import java.util.Scanner;

abstract class Treatment {
    //private
    private String nama;
    //default
    int durasi;
    //public
    public int harga;
    int diskon = 0;
    //final atribut variabel
    final int pajak = 10;

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
    public void setNama(String nama) {
        this.nama = nama;
    }
    // Protected setter untuk durasi
    protected void setDurasi(int durasi) {
        this.durasi = durasi;
    }
    // setter pada (setHarga)
    public void setHarga(int harga) {
        this.harga = harga;
    }
    // Overloading method
    public void setHarga(int harga, int diskon) {
        this.harga = harga;
        this.diskon = diskon;
    }

    // Memodifikasi method ini untuk menggunakan variabel pajak
    int getHargaSetelahDiskon() {
        int hargaSetelahDiskon = harga - (harga * diskon / 100);
        // Menambahkan pajak ke harga setelah diskon
        return hargaSetelahDiskon + (hargaSetelahDiskon * pajak / 100);
    }

    public abstract void info();
}

class TreatmentDiskon extends Treatment {
    TreatmentDiskon(String nama, int durasi, int harga, int diskon) {
        super(nama, durasi, harga);
        this.diskon = diskon;
    }

    // Overriding method
    @Override
    int getHargaSetelahDiskon() {
        int hargaSetelahDiskon = harga - (harga * diskon / 100);
        // Menggunakan pajak dari kelas parent
        return hargaSetelahDiskon + (hargaSetelahDiskon * pajak / 100);
    }

    @Override
    public void info() {
        System.out.println("Treatment dengan Diskon di DY Clinic.");
    }
}

final class App {
    static ArrayList<Treatment> dataTreatment = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

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
            int menu = input.nextInt();
            input.nextLine();

            switch (menu) {
                case 1 -> menuCRUD();
                case 2 -> {
                    exit = true;
                    ucapanTerimaKasih();
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
            System.out.println("1. Lihat Treatment");
            System.out.println("2. Tambah Treatment");
            System.out.println("3. Tambah Diskon ke Treatment");
            System.out.println("4. Ubah Treatment");
            System.out.println("5. Hapus Treatment");
            System.out.println("6. Kembali ke Menu Utama");
            System.out.print("Pilih: ");
            int pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
                case 1 -> tampilkan();
                case 2 -> tambahTreatment();
                case 3 -> tambahDiskon();
                case 4 -> ubah();
                case 5 -> hapus();
                case 6 -> exitCRUD = true;
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void tampilkan() {
        if (dataTreatment.isEmpty()) {
            System.out.println("Belum ada treatment.");
            return;
        }
        System.out.println("\n╔═════════════════════════════════════╗");
        System.out.println("║      DAFTAR TREATMENT DY CLINIC     ║");
        System.out.println("╚═════════════════════════════════════╝");
        for (int i = 0; i < dataTreatment.size(); i++) {
            Treatment t = dataTreatment.get(i);
            int hargaSebelumPajak = t.getHarga() - (t.getHarga() * t.diskon / 100);
            int nilaiPajak = hargaSebelumPajak * t.pajak / 100;
            System.out.printf("%d. %s \n Durasi: %d mnt \n Harga: Rp%d \n Diskon: %d%% \n Harga Setelah Diskon: Rp%d \n Pajak (%d%%): Rp%d \n Total: Rp%d\n\n",
                i + 1, t.getNama(), t.getDurasi(), t.getHarga(), t.diskon,
                hargaSebelumPajak, t.pajak, nilaiPajak, t.getHargaSetelahDiskon());
        }
    }

    static void tambahTreatment() {
        System.out.print("Masukkan nama treatment: ");
        String nama = input.nextLine();
        System.out.print("Masukkan durasi (menit): ");
        int durasi = input.nextInt();
        System.out.print("Masukkan harga: ");
        int harga = input.nextInt();

        dataTreatment.add(new Treatment(nama, durasi, harga) {
            @Override
            public void info() {
                System.out.println("Treatment DY Clinic.");
            }
        });

        System.out.println("Treatment berhasil ditambahkan.");
    }

    static void tambahDiskon() {
        tampilkan();
        System.out.print("Pilih nomor treatment: ");
        int i = input.nextInt();
        if (i < 1 || i > dataTreatment.size()) {
            System.out.println("Nomor salah.");
            return;
        }
        System.out.print("Masukkan diskon (%): ");
        int diskon = input.nextInt();
        Treatment lama = dataTreatment.get(i - 1);
        TreatmentDiskon baru = new TreatmentDiskon(lama.getNama(), lama.getDurasi(), lama.getHarga(), diskon);
        dataTreatment.set(i - 1, baru);
        System.out.println("Diskon ditambahkan.");
    }

    static void ubah() {
        tampilkan();
        System.out.print("Masukkan nomor treatment yang ingin diubah: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > dataTreatment.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        Treatment treatment = dataTreatment.get(nomor - 1);
        // setter pada (setDurasi)
        System.out.print("Masukkan durasi baru (menit): ");
        treatment.setDurasi(input.nextInt());
        // setter pada (setHarga)
        System.out.print("Masukkan harga baru: ");
        treatment.setHarga(input.nextInt());

        System.out.println("Data treatment berhasil diperbarui.");
    }

    static void hapus() {
        tampilkan();
        System.out.print("Masukkan nomor treatment yang ingin dihapus: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > dataTreatment.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        dataTreatment.remove(nomor - 1);
        System.out.println("Treatment berhasil dihapus.");
    }

    public static final void ucapanTerimaKasih() {
        System.out.println("\nTerima kasih telah menggunakan sistem DY Clinic!");
    }
}