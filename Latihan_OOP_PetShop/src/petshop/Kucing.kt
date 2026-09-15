// 2. Kucing.kt
package petshop

class Kucing(id: String, nama: String, umur: Int, val warnaBulu: String) : Hewan(id, nama, umur) {
    override fun biayaPerawatanHarian() = 50_000.0
    override fun jenisHewan() = "Kucing"
    override fun displayInfo() {
        super.displayInfo()
        println("     Warna bulu: $warnaBulu")
    }
}
