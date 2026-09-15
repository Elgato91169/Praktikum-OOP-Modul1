// 1. Hewan.kt
package petshop

abstract class Hewan(val id: String, val nama: String, val umur: Int) {
    var tersedia: Boolean = true
        private set

    abstract fun biayaPerawatanHarian(): Double
    abstract fun jenisHewan(): String

    fun ajukanAdopsi(): Boolean {
        return if (tersedia) {
            tersedia = false
            println("✅ $nama berhasil diajukan untuk diadopsi.")
            true
        } else {
            println("❌ $nama tidak tersedia.")
            false
        }
    }

    // Pengerjaan Latihan Mandiri: Fitur Batalkan Adopsi
    fun batalkanAdopsi() {
        if (!tersedia) {
            tersedia = true
            println("↩️  Adopsi $nama dibatalkan, hewan kembali tersedia.")
        }
    }

    open fun displayInfo() {
        val status = if (tersedia) "Tersedia" else "Tidak tersedia"
        println("[$id] $nama | ${jenisHewan()} | $umur th | $status")
        println("     Biaya/hari: Rp ${biayaPerawatanHarian()}")
    }
}
