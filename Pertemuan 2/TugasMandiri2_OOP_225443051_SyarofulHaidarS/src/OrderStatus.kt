/**
 * Sealed class yang mewakili seluruh kemungkinan status sebuah order.
 */
sealed class OrderStatus {
    /**
     * Menampilkan nama status.
     *
     * @return deskripsi status.
     */
    abstract fun display(): String

    /**
     * Mengecek apakah status sudah merupakan status akhir.
     *
     * @return `true` jika order selesai atau dibatalkan.
     */
    open fun isFinal(): Boolean = this is Completed || this is Cancelled

    /**
     * Status ketika order masih menunggu perjalanan dimulai.
     */
    object Waiting : OrderStatus() {
        override fun display(): String = "Menunggu"
    }

    /**
     * Status ketika perjalanan sedang berlangsung.
     */
    object OnGoing : OrderStatus() {
        override fun display(): String = "Berjalan"
    }

    /**
     * Status ketika perjalanan sudah selesai.
     */
    object Completed : OrderStatus() {
        override fun display(): String = "Selesai"
    }

    /**
     * Status ketika order dibatalkan.
     *
     * @property reason alasan pembatalan.
     */
    data class Cancelled(val reason: String) : OrderStatus() {
        override fun display(): String = "Dibatalkan: $reason"
    }
}
