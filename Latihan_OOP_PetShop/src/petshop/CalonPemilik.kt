// 7. CalonPemilik.kt
package petshop

class CalonPemilik(val id: String, val nama: String, private val email: String, private val telepon: String) {
    private val riwayatAdopsi: MutableList<Adopsi> = mutableListOf()
    val jumlahAdopsi: Int get() = riwayatAdopsi.size
    val adopsiAktif: Int get() = riwayatAdopsi.count { !it.status.isFinal() }

    fun ajukanAdopsi(hewan: Hewan): Adopsi? {
        if (!hewan.tersedia) {
            println("❌ ${hewan.nama} sedang tidak tersedia.")
            return null
        }
        // Pengerjaan Latihan Mandiri: Batas 2 Adopsi Aktif
        if (adopsiAktif >= 2) {
            println("❌ $nama sudah punya 2 adopsi aktif. Selesaikan dulu.")
            return null
        }
        if (!hewan.ajukanAdopsi()) return null

        val adopsi = Adopsi("ADP-${System.currentTimeMillis()}", hewan, this)
        riwayatAdopsi.add(adopsi)
        println("✅ $nama berhasil mengajukan adopsi ${hewan.nama}.")
        return adopsi
    }

    fun displayInfo() {
        println("[$id] $nama | Aktif: $adopsiAktif")
    }
}
