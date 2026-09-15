# Tugas Mandiri 2 OOP

## Identitas Mahasiswa

- Nama: Syaroful Haidar S.
- NIM: 225443051

Program ini adalah Sistem Manajemen Transportasi Online yang dibuat dengan Kotlin. Setiap kelas ditempatkan pada file terpisah di folder `src`.

## Menjalankan Program

Jalankan `Main.kt` untuk skenario integrasi Soal 5. Untuk menjalankan demo tiap tahap, pilih kelas berikut sebagai main class di IntelliJ IDEA:

| Soal | Main class |
|---|---|
| 1 | `MainSoal1` |
| 2 | `MainSoal2` |
| 3 | `MainSoal3` |
| 4 | `MainSoal4` |
| 5 | `MainKt` |

## Ringkasan Kelas

- `Vehicle`, `Car`, `Motorcycle`, dan `Truck` mengelola data serta tarif kendaraan.
- `Driver` dan `Customer` menyimpan data pengguna sistem.
- `Order` menghubungkan pelanggan, pengemudi, rute, tarif, dan status pesanan.
- `Payment`, `PaymentMethod`, serta `PaymentResult` menangani proses pembayaran.
- `TransportSystem` menjadi pengelola data kendaraan, pengemudi, pelanggan, pesanan, dan pembayaran.

## Struktur Proyek

```text
src/
├── Main.kt                 # Integrasi dan skenario lengkap Soal 5
├── MainSoal1.kt            # Demo konsep dasar
├── MainSoal2.kt            # Demo enkapsulasi
├── MainSoal3.kt            # Demo pewarisan
├── MainSoal4.kt            # Demo polimorfisme, interface, sealed class
├── Vehicle.kt
├── Car.kt
├── Motorcycle.kt
├── Truck.kt
├── Driver.kt
├── Customer.kt
├── Order.kt
├── OrderStatus.kt
├── Payment.kt
├── PaymentMethod.kt
├── PaymentResult.kt
├── TransportSystem.kt
└── Format.kt
docs/
└── Laporan_Tugas_Mandiri.pdf
```

## Jawaban Analisis

### Soal 1

1. `val` adalah nilai yang tidak dapat diubah setelah objek dibuat, contohnya `Vehicle.plateNumber`. `var` dapat diubah, contohnya `Customer.balance` dan `Vehicle.isAvailable`.
2. Pada Soal 1, semua kendaraan masih memakai rumus tarif yang sama. Karena belum ada variasi jenis kendaraan, `calculateFare()` cukup menjadi metode biasa. Metode tersebut dibuat `open` ketika masuk ke Soal 3 agar mobil, motor, dan truk dapat memakai rumus tarif sendiri.

### Soal 2

1. Contoh enkapsulasi adalah `_amount` dan `_isPaid` pada `Payment`, daftar data private pada `TransportSystem`, serta perubahan status pesanan melalui fungsi `startTrip()`, `completeTrip()`, dan `cancelTrip()`.
2. `private set` membuat nilai tetap bisa dibaca dari luar kelas, tetapi hanya dapat diubah dari dalam kelas. Hal ini berguna untuk data seperti status pembayaran. `private val` tidak dapat dibaca maupun diubah dari luar kelas.
3. Validasi metode pembayaran mencegah nilai atau objek pembayaran yang tidak sesuai diproses oleh sistem. Pada program ini validasi dilakukan ketika objek `CreditCard` dan `QRIS` dibuat.

### Soal 3

1. `Vehicle` dibuat `open` karena kelas Kotlin secara default tidak dapat diwariskan.
2. `super.displayInfo()` memanggil informasi dasar dari `Vehicle`, sehingga subclass tidak perlu menulis ulang nomor polisi, merek, model, tahun, dan ketersediaan kendaraan.
3. Tarif tiap kendaraan berbeda. Karena itu `calculateFare()` dioverride agar mobil, motor, dan truk memiliki rumus tarif sesuai jenisnya.

### Soal 4

1. Interface berisi kontrak perilaku dan dapat diimplementasikan oleh banyak kelas. Abstract class dapat memiliki state dan implementasi dasar. `PaymentMethod` memakai interface karena semua metode pembayaran hanya perlu mengikuti kontrak yang sama.
2. `PaymentResult` dibuat sealed class agar semua hasil pembayaran diketahui dan `when` dapat menangani semua kemungkinan secara lengkap.
3. Operator `as?` melakukan casting aman. Jika objek bukan tipe tujuan, hasilnya `null` dan program tidak mengalami error casting.

### Soal 5

1. `TransportSystem` menyimpan daftar kendaraan, pengemudi, pelanggan, pesanan, dan pembayaran sebagai properti private. Data dikelola melalui fungsi seperti `addVehicle()`, `findDriver()`, dan `createOrder()`.
2. Polimorfisme terjadi di `displayAllVehicles()` karena setiap objek diperlakukan sebagai `Vehicle`, tetapi pemanggilan `displayInfo()` menjalankan versi milik `Car`, `Motorcycle`, atau `Truck`.
3. `OrderStatus` memakai sealed class karena status `Cancelled` membutuhkan data tambahan berupa alasan pembatalan, sedangkan enum kurang fleksibel untuk membawa data berbeda pada setiap status.
