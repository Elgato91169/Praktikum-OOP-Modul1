# Sistem Manajemen Perpustakaan Digital

**Mahasiswa:** Syaroful Haidar Sudaryono  
**NIM:** 225443051

Program Kotlin berbasis OOP untuk mengelola buku, jurnal, DVD, anggota, peminjaman, pengembalian, dan denda keterlambatan.

Jalankan `Main.kt` melalui IntelliJ IDEA atau gunakan Kotlin compiler: `kotlinc src/*.kt -include-runtime -d library.jar`, lalu `java -jar library.jar`.

Struktur kelas: `Item` (abstrak) diturunkan oleh `Book`, `Journal`, dan `DVD`; `Library` mengelola koleksi; `Member` menyimpan transaksi; dan `TransactionStatus` adalah sealed class untuk status transaksi.
