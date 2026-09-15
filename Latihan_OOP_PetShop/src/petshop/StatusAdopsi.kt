// 5. StatusAdopsi.kt
package petshop

sealed class StatusAdopsi {
    abstract fun deskripsi(): String
    open fun isFinal(): Boolean = false

    object Tersedia : StatusAdopsi() { override fun deskripsi() = "🟢 Tersedia" }
    data class Diajukan(val namaCalon: String) : StatusAdopsi() { override fun deskripsi() = "🟡 Diajukan oleh $namaCalon" }
    data class SudahDiadopsi(val tanggal: String) : StatusAdopsi() {
        override fun deskripsi() = "✅ Diadopsi pada $tanggal"
        override fun isFinal() = true
    }
    object Dibatalkan : StatusAdopsi() {
        override fun deskripsi() = "❌ Dibatalkan"
        override fun isFinal() = true
    }
}
