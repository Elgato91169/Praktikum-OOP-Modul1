/**
 * Status pesanan yang hanya dapat berada pada kondisi-kondisi yang telah ditentukan.
 */
sealed class OrderStatus {
    /** Mengubah status menjadi teks yang mudah dibaca. */
    abstract fun display(): String

    /** Menentukan apakah status tidak dapat diubah kembali. */
    fun isFinal(): Boolean = this is Completed || this is Cancelled

    /** Status ketika pesanan baru dibuat dan menunggu perjalanan dimulai. */
    object Waiting : OrderStatus() {
        /** Mengembalikan teks status menunggu. */
        override fun display(): String = "Menunggu"
    }

    /** Status ketika perjalanan sedang berlangsung. */
    object OnGoing : OrderStatus() {
        /** Mengembalikan teks status berjalan. */
        override fun display(): String = "Berjalan"
    }

    /** Status ketika perjalanan telah selesai. */
    object Completed : OrderStatus() {
        /** Mengembalikan teks status selesai. */
        override fun display(): String = "Selesai"
    }

    /** Status ketika pesanan dibatalkan beserta alasannya. */
    data class Cancelled(
        /** Alasan pembatalan pesanan. */
        val reason: String
    ) : OrderStatus() {
        /** Mengembalikan teks status pembatalan. */
        override fun display(): String = "Dibatalkan: $reason"
    }
}
