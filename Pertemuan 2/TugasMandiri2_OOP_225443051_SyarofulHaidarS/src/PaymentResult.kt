/**
 * Sealed class yang mewakili hasil proses pembayaran.
 */
sealed class PaymentResult {
    /**
     * Menampilkan hasil pembayaran.
     *
     * @return deskripsi hasil pembayaran.
     */
    abstract fun display(): String

    /**
     * Hasil ketika pembayaran berhasil.
     *
     * @property transactionId ID transaksi.
     * @property timestamp waktu transaksi.
     */
    data class Success(
        val transactionId: String,
        val timestamp: String
    ) : PaymentResult() {
        override fun display(): String {
            return "Berhasil. ID transaksi: $transactionId"
        }
    }

    /**
     * Hasil ketika pembayaran gagal.
     *
     * @property reason alasan pembayaran gagal.
     * @property errorCode kode error.
     */
    data class Failed(
        val reason: String,
        val errorCode: Int
    ) : PaymentResult() {
        override fun display(): String {
            return "Gagal. $reason (kode $errorCode)"
        }
    }

    /**
     * Hasil ketika pembayaran masih tertunda.
     */
    object Pending : PaymentResult() {
        override fun display(): String = "Tertunda"
    }
}
