// Sistem Peminjaman Buku Sederhana

class Buku(
    val judul: String,
    val pengarang: String,
    var tahunTerbit: Int
) {
    // Method menampilkan informasi lengkap buku
    fun infoBuku() {
        println("----------------------------------------")
        println("Judul Buku   : $judul")
        println("Pengarang    : $pengarang")
        println("Tahun Terbit : $tahunTerbit")
        println("----------------------------------------")
    }
}

fun main() {

    println(" Sistem Peminjaman Buku Sederhana ")

    val bukuFiksi = Buku("Laskar Pelangi", "Andrea Hirata", 2005)
    val bukuNonFiksi = Buku("Filosofi Teras", "Henry Manampiring", 2018)
    val komik = Buku("Naruto", "Masashi Kishimoto", 1999)

    println("\n[Buku Fiksi]")
    bukuFiksi.infoBuku()

    println("\n[Buku Nonfiksi]")
    bukuNonFiksi.infoBuku()

    println("\n[Komik]")
    komik.infoBuku()
}
