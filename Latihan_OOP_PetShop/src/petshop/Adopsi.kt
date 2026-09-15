// 6. Adopsi.kt
package petshop
import java.time.LocalDate

class Adopsi(val id: String, val hewan: Hewan, val pemilik: CalonPemilik, val tanggal: String = LocalDate.now().toString()) {
    var status: StatusAdopsi = StatusAdopsi.Tersedia

    fun finalisasi(): Boolean {
        if (status.isFinal()) {
            println("❌ Adopsi #$id sudah final, tidak bisa difinalisasi lagi.")
            return false
        }
        status = StatusAdopsi.SudahDiadopsi(tanggal)
        println("🎉 ${hewan.nama} resmi diadopsi oleh ${pemilik.nama}!")
        return true
    }

    // Pengerjaan Latihan Mandiri: Fitur Batalkan Adopsi
    fun batalkan() {
        if (status.isFinal()) {
            println("❌ Adopsi #$id sudah final, tidak bisa dibatalkan.")
            return
        }
        status = StatusAdopsi.Dibatalkan
        hewan.batalkanAdopsi()
    }

    fun display() {
        println("Adopsi #$id | Hewan: ${hewan.nama} | Pemilik: ${pemilik.nama} | Status: ${status.deskripsi()}")
    }
}
