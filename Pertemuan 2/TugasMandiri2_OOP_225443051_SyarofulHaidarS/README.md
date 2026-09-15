# Tugas Mandiri 2 PBO
## Sistem Manajemen Transportasi Online

Repository ini berisi implementasi Tugas Mandiri 2 mata kuliah **Pemrograman Berorientasi Objek (PBO)** dengan studi kasus **Sistem Manajemen Transportasi Online**.

Program dibuat menggunakan **Kotlin** dan menerapkan konsep dasar hingga lanjutan dalam pemrograman berorientasi objek, yaitu class, object, encapsulation, inheritance, polymorphism, interface, sealed class, dan integrasi beberapa class dalam sebuah sistem.

## Identitas

**Nama:** Syaroful Haidar Sudaryono  
**NIM:** 225443051  
**Kelas:** 2AEC2  
**Program Studi:** D4 Teknologi Rekayasa Informatika Industri  
**Jurusan:** Teknik Otomasi Manufaktur dan Mekatronika  
**Politeknik Manufaktur Bandung**

## Deskripsi Program

Program mensimulasikan sistem transportasi online yang mengelola:

- Kendaraan seperti mobil, motor, dan truk.
- Pengemudi yang memiliki kendaraan.
- Pelanggan yang melakukan pemesanan.
- Pesanan perjalanan beserta statusnya.
- Pembayaran menggunakan QRIS, kartu kredit, dan tunai.
- Perubahan status pesanan dari menunggu, berjalan, selesai, atau dibatalkan.
- Laporan pendapatan dari pesanan yang telah selesai.

Studi kasus ini dikembangkan secara bertahap dari Soal 1 sampai Soal 5.

## Konsep OOP yang Digunakan

### 1. Class dan Object

Class digunakan untuk merepresentasikan objek pada sistem, seperti:

- `Vehicle`
- `Driver`
- `Customer`
- `Order`
- `Payment`

Object dibuat berdasarkan class tersebut untuk menjalankan simulasi sistem transportasi online.

### 2. Encapsulation

Encapsulation digunakan untuk membatasi akses langsung terhadap data tertentu.

Contoh implementasinya terdapat pada:

- `OrderStatus` yang menggunakan `private set` pada status order.
- `Payment` yang menggunakan `private set` pada `isPaid`.
- Beberapa data internal `TransportSystem` disimpan menggunakan `private`.

### 3. Inheritance

Class `Car`, `Motorcycle`, dan `Truck` merupakan turunan dari class `Vehicle`.

```text
Vehicle
├── Car
├── Motorcycle
└── Truck
```

Inheritance memungkinkan subclass menggunakan property dan method dari superclass serta menambahkan perilaku masing-masing.

### 4. Polymorphism

Polymorphism diterapkan ketika objek `Car`, `Motorcycle`, dan `Truck` disimpan dalam tipe `Vehicle`.

Method `calculateFare()` memiliki implementasi berbeda pada setiap kendaraan sesuai jenis kendaraannya.

### 5. Interface

Interface `PaymentMethod` digunakan sebagai standar untuk metode pembayaran.

Implementasinya terdiri dari:

- `CreditCard`
- `QRIS`
- `Cash`

Setiap metode pembayaran memiliki fungsi `processPayment()` dan dapat mempunyai biaya layanan yang berbeda.

### 6. Sealed Class

Sealed class digunakan untuk merepresentasikan kondisi yang telah ditentukan.

`PaymentResult` memiliki kondisi:

- `Success`
- `Failed`
- `Pending`

Sedangkan `OrderStatus` memiliki kondisi:

- `Waiting`
- `OnGoing`
- `Completed`
- `Cancelled`

### 7. Smart Casting

Smart casting digunakan untuk mengecek tipe objek secara aman menggunakan:

```kotlin
when (v) {
    is Car -> ...
    is Motorcycle -> ...
    is Truck -> ...
}
```

Selain itu digunakan juga safe cast:

```kotlin
val safeCast = driver1.vehicle as? Car
```

## Struktur Project

```text
Tugas_Mandiri_OOP_NIM_Nama/
├── src/
│   ├── Main.kt
│   ├── Vehicle.kt
│   ├── Car.kt
│   ├── Motorcycle.kt
│   ├── Truck.kt
│   ├── Driver.kt
│   ├── Customer.kt
│   ├── Order.kt
│   ├── OrderStatus.kt
│   ├── Payment.kt
│   ├── PaymentMethod.kt
│   ├── PaymentResult.kt
│   └── TransportSystem.kt
├── docs/
│   └── Laporan_Tugas_Mandiri.pdf
└── README.md
```

## Penjelasan File

### `../../Downloads/Vehicle.kt`

Merupakan class dasar untuk kendaraan.

Class ini menyimpan informasi:

- nomor polisi
- merek
- model
- tahun
- ketersediaan kendaraan

Class ini juga menyediakan method untuk menghitung tarif dan menampilkan informasi kendaraan.

### `../../Downloads/Car.kt`

Merupakan subclass dari `Vehicle` untuk kendaraan mobil.

Memiliki property tambahan:

- `fuelType`
- `numberOfDoors`

Tarif mobil:

```text
8000 + (jarak × 2500)
```

### `../../Downloads/Motorcycle.kt`

Merupakan subclass dari `Vehicle` untuk kendaraan motor.

Memiliki property:

- `engineCapacity`
- `hasHelmet`

Tarif motor:

```text
3000 + (jarak × 1500)
```

### `../../Downloads/Truck.kt`

Merupakan subclass dari `Vehicle` untuk kendaraan truk.

Memiliki property:

- `loadCapacity`
- `numberOfAxles`

Tarif truk:

```text
10000 + (jarak × 3500)
```

### `../../Downloads/Driver.kt`

Menyimpan data pengemudi dan kendaraan yang digunakan.

Method utama:

```text
displayInfo()
acceptOrder()
```

### `../../Downloads/Customer.kt`

Menyimpan data pelanggan dan saldo.

Method utama:

```text
displayInfo()
topUp()
canPay()
```

### `../../Downloads/Order.kt`

Menyimpan informasi pesanan seperti:

- ID order
- pelanggan
- driver
- lokasi penjemputan
- tujuan
- jarak
- total tarif
- status order

Method yang digunakan:

```text
startTrip()
completeTrip()
cancelTrip()
displayOrder()
```

### `../../Downloads/OrderStatus.kt`

Merupakan sealed class untuk status pesanan.

Status yang digunakan:

```text
Waiting
OnGoing
Completed
Cancelled
```

### `../../Downloads/PaymentMethod.kt`

Merupakan interface untuk metode pembayaran.

Implementasinya:

```text
CreditCard
QRIS
Cash
```

### `../../Downloads/PaymentResult.kt`

Merupakan sealed class untuk hasil pembayaran.

Status pembayaran:

```text
Success
Failed
Pending
```

### `../../Downloads/Payment.kt`

Mengelola transaksi pembayaran berdasarkan order dan metode pembayaran yang digunakan.

### `../../Downloads/TransportSystem.kt`

Merupakan class utama yang mengelola keseluruhan data sistem:

- kendaraan
- driver
- customer
- order
- payment

Class ini juga menangani pembuatan order, pembayaran, penyelesaian order, pembatalan order, dan laporan pendapatan.

### `../../Downloads/Main.kt`

Merupakan program utama untuk menjalankan simulasi sistem.

Pada `main()` dilakukan:

1. Inisialisasi sistem.
2. Menambahkan kendaraan.
3. Menambahkan driver.
4. Menambahkan customer.
5. Membuat beberapa order.
6. Menampilkan data.
7. Melakukan pembayaran.
8. Menyelesaikan order.
9. Membatalkan order.
10. Menampilkan laporan pendapatan.
11. Mendemonstrasikan polymorphism.
12. Mendemonstrasikan smart casting.
13. Mendemonstrasikan sealed class.

## Data yang Digunakan

### Kendaraan

```text
Mobil
Nomor Polisi : B 1234 XYZ
Merek        : Toyota
Model        : Innova
Tahun        : 2021
Bahan Bakar  : Bensin
Jumlah Pintu : 4
```

```text
Motor
Nomor Polisi : D 5678 ABC
Merek        : Honda
Model        : Beat
Tahun        : 2022
Kapasitas    : 125 cc
Helm         : Tersedia
```

```text
Truk
Nomor Polisi : E 9012 DEF
Merek        : Hino
Model        : Dutro
Tahun        : 2020
Kapasitas    : 5.0 ton
Jumlah Sumbu : 2
```

### Driver

```text
D001 - Andi
D002 - Budi
D003 - Citra
```

### Customer

```text
C001 - Dewi
C002 - Eko
C003 - Fani
```

## Skenario Program

Program menggunakan studi kasus transportasi online dengan beberapa perjalanan.

Order yang dibuat:

```text
ORD-001
Dewi menggunakan driver Andi
Kampus A -> Mall B
Jarak 12 km
```

```text
ORD-002
Eko menggunakan driver Budi
Stasiun -> Kantor
Jarak 8 km
```

```text
ORD-003
Fani menggunakan driver Citra
Gudang -> Pelabuhan
Jarak 25 km
```

Setelah order dibuat, program melakukan proses pembayaran, menyelesaikan Order 1, membatalkan Order 3, dan menampilkan laporan pendapatan.

## Cara Menjalankan Program

Project dapat dibuka menggunakan **IntelliJ IDEA** dengan dukungan Kotlin.

Langkah menjalankan:

1. Buka project di IntelliJ IDEA.
2. Pastikan seluruh file `.kt` berada dalam source folder project.
3. Pastikan file `../../Downloads/Main.kt` memiliki fungsi:

```kotlin
fun main() {
    // program utama
}
```

4. Jalankan `../../Downloads/Main.kt` menggunakan tombol **Run**.

## Teknologi

```text
Bahasa Pemrograman : Kotlin
IDE                 : IntelliJ IDEA
Library             : Kotlin Standard Library
Paradigma           : Object-Oriented Programming
```

Project tidak menggunakan external library.

## Tujuan Pembelajaran

Project ini dibuat untuk memahami penerapan konsep OOP secara bertahap, mulai dari class dan object hingga integrasi sistem.

Materi yang didemonstrasikan:

```text
Class
Object
Property
Method
Encapsulation
Inheritance
Polymorphism
Interface
Sealed Class
Smart Casting
```

## Dokumentasi

Dokumentasi hasil eksekusi dan penjelasan program tersedia pada:

```text
docs/Laporan_Tugas_Mandiri.pdf
```

## Catatan

Source code pada repository merupakan bagian dari tugas praktikum dan digunakan untuk menunjukkan penerapan konsep OOP pada studi kasus Sistem Manajemen Transportasi Online.

Untuk pengumpulan akhir, pastikan seluruh ketentuan tugas telah dipenuhi, terutama dokumentasi **KDoc**, demonstrasi masing-masing soal, screenshot output, dan pertanyaan analisis pada laporan.