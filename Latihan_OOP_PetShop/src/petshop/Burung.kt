// 4. Burung.kt (Pengerjaan Latihan Mandiri: Subclass Baru)
package petshop

class Burung(id: String, nama: String, umur: Int, val bisaTerbang: Boolean) : Hewan(id, nama, umur) {
    override fun biayaPerawatanHarian() = 30_000.0
    override fun jenisHewan() = "Burung"
    override fun displayInfo() {
        super.displayInfo()
        val tb = if (bisaTerbang) "bisa" else "tidak bisa"
        println("     $tb terbang")
    }
}
