// 3. Anjing.kt
package petshop

class Anjing(id: String, nama: String, umur: Int, val ras: String) : Hewan(id, nama, umur) {
    override fun biayaPerawatanHarian() = 80_000.0
    override fun jenisHewan() = "Anjing"
    override fun displayInfo() {
        super.displayInfo()
        println("     Ras: $ras")
    }
}
