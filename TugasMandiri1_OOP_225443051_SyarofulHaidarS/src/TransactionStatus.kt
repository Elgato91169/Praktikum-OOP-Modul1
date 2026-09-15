/** Merepresentasikan semua kemungkinan status dalam siklus transaksi. */
sealed class TransactionStatus {
    /** Menghasilkan label status yang mudah dibaca. */
    abstract fun display(): String

    /** Bernilai benar jika status ini tidak dapat diubah lagi. */
    fun isFinal(): Boolean = this is Returned || this is Cancelled

    /** Item sedang dipinjam. */
    object Borrowed : TransactionStatus() {
        override fun display() = "Dipinjam"
    }

    /** Item telah dikembalikan tepat waktu. */
    object Returned : TransactionStatus() {
        override fun display() = "Dikembalikan"
    }

    /** Item dikembalikan terlambat. */
    data class Overdue(
        /** Jumlah hari keterlambatan. */ val daysLate: Int
    ) : TransactionStatus() {
        override fun display() = "Terlambat ($daysLate hari)"
    }

    /** Transaksi dibatalkan. */
    object Cancelled : TransactionStatus() {
        override fun display() = "Dibatalkan"
    }
}
