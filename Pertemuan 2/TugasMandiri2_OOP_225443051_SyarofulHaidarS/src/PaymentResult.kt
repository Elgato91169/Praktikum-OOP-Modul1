/**
 * Hasil yang mungkin diperoleh setelah proses pembayaran.
 */
sealed class PaymentResult {
    /** Mengubah hasil pembayaran menjadi teks yang mudah dibaca. */
    abstract fun display(): String

    /** Hasil pembayaran yang berhasil diproses. */
    data class Success(
        /** ID transaksi pembayaran. */
        val transactionId: String,
        /** Waktu transaksi diproses. */
        val timestamp: String
    ) : PaymentResult() {
        /** Mengembalikan teks pembayaran berhasil. */
        override fun display(): String = "Pembayaran berhasil. ID: $transactionId, waktu: $timestamp"
    }

    /** Hasil pembayaran yang gagal diproses. */
    data class Failed(
        /** Keterangan penyebab kegagalan pembayaran. */
        val reason: String,
        /** Kode kesalahan pembayaran. */
        val errorCode: Int
    ) : PaymentResult() {
        /** Mengembalikan teks pembayaran gagal. */
        override fun display(): String = "Pembayaran gagal ($errorCode): $reason"
    }

    /** Hasil ketika pembayaran masih menunggu konfirmasi. */
    data object Pending : PaymentResult() {
        /** Mengembalikan teks pembayaran tertunda. */
        override fun display(): String = "Pembayaran sedang menunggu konfirmasi."
    }
}
