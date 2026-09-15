// 8. PetShop.kt
package petshop

class PetShop(val nama: String) {
    private val hewanList: MutableList<Hewan> = mutableListOf()
    private val pemilikList: MutableList<CalonPemilik> = mutableListOf()
    private val adopsiList: MutableList<Adopsi> = mutableListOf()

    val totalHewan: Int get() = hewanList.size
    val hewanTersedia: Int get() = hewanList.count { it.tersedia }
    val totalPemilik: Int get() = pemilikList.size
    val totalAdopsi: Int get() = adopsiList.size

    fun tambahHewan(vararg list: Hewan) {
        list.forEach {
            hewanList.add(it)
            println("✅ ${it.nama} ditambahkan ke $nama.")
        }
    }

    fun daftarPemilik(id: String, nama: String, email: String, telp: String): Boolean {
        if (pemilikList.any { it.id == id }) return false
        pemilikList.add(CalonPemilik(id, nama, email, telp))
        println("✅ Pemilik $nama berhasil didaftarkan.")
        return true
    }

    fun ajukanAdopsi(pemilikId: String, hewanId: String): Adopsi? {
        val p = pemilikList.find { it.id == pemilikId } ?: return null
        val h = hewanList.find { it.id == hewanId } ?: return null

        val adopsi = p.ajukanAdopsi(h) ?: return null
        adopsiList.add(adopsi)
        return adopsi
    }

    fun displaySemuaHewan() {
        println("\n=== DAFTAR HEWAN ===")
        hewanList.forEach { it.displayInfo() }
    }

    fun displayHewanTersedia() {
        println("\n=== HEWAN TERSEDIA ===")
        val tersedia = hewanList.filter { it.tersedia }
        if (tersedia.isEmpty()) println("(tidak ada)")
        else tersedia.forEach { println("[$it.id] ${it.nama} (${it.jenisHewan()})") }
    }

    fun displayLaporan() {
        println("\n=== LAPORAN PET SHOP ===")
        println("Total hewan   : $totalHewan")
        println("Hewan tersedia: $hewanTersedia")
        println("Total adopsi  : $totalAdopsi")
    }
}
