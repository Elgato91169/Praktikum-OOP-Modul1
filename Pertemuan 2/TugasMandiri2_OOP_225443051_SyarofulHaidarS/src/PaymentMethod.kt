import java.time.LocalDateTime

/**
 * Interface yang menjadi standar untuk metode pembayaran.
 */
interface PaymentMethod {
    /**
     * Nama metode pembayaran.
     */
    val name: String

    /**
     * Memproses pembayaran.
     *
     * @param amount nominal pembayaran.
     * @return hasil proses pembayaran.
     */
    fun processPayment(amount: Double): PaymentResult

    /**
     * Menghitung biaya layanan dari metode pembayaran.
     *
     * @param amount nominal pembayaran.
     * @return biaya layanan.
     */
    fun getFee(amount: Double): Double = 0.0
}

/**
 * Metode pembayaran menggunakan kartu kredit.
 *
 * @property cardNumber nomor kartu kredit yang digunakan.
 */
class CreditCard(val cardNumber: String) : PaymentMethod {
    init {
        require(cardNumber.length >= 16 && cardNumber.all { it.isDigit() }) {
            "Nomor kartu kredit harus terdiri dari minimal 16 digit."
        }
    }

    /**
     * Nama metode pembayaran.
     */
    override val name: String = "Kartu Kredit"

    /**
     * Menghitung biaya layanan kartu kredit sebesar 2 persen.
     *
     * @param amount nominal pembayaran.
     * @return biaya layanan.
     */
    override fun getFee(amount: Double): Double = amount * 0.02

    /**
     * Memproses pembayaran kartu kredit.
     *
     * @param amount nominal pembayaran.
     * @return hasil transaksi.
     */
    override fun processPayment(amount: Double): PaymentResult {
        return PaymentResult.Success(
            "CC-${System.currentTimeMillis()}",
            LocalDateTime.now().toString()
        )
    }
}

/**
 * Metode pembayaran menggunakan QRIS.
 *
 * @property qrcode kode QRIS yang digunakan.
 */
class QRIS(val qrcode: String) : PaymentMethod {
    init {
        require(qrcode.length >= 10) {
            "Kode QRIS harus memiliki minimal 10 karakter."
        }
    }

    /**
     * Nama metode pembayaran.
     */
    override val name: String = "QRIS"

    /**
     * Menghitung biaya layanan QRIS sebesar 0,5 persen.
     *
     * @param amount nominal pembayaran.
     * @return biaya layanan.
     */
    override fun getFee(amount: Double): Double = amount * 0.005

    /**
     * Memproses pembayaran QRIS.
     *
     * @param amount nominal pembayaran.
     * @return hasil transaksi.
     */
    override fun processPayment(amount: Double): PaymentResult {
        return PaymentResult.Success(
            "QRIS-${System.currentTimeMillis()}",
            LocalDateTime.now().toString()
        )
    }
}

/**
 * Metode pembayaran menggunakan uang tunai.
 */
class Cash : PaymentMethod {
    /**
     * Nama metode pembayaran.
     */
    override val name: String = "Tunai"

    /**
     * Mengembalikan biaya layanan tunai.
     *
     * @param amount nominal pembayaran.
     * @return 0 karena tunai tidak memiliki biaya layanan.
     */
    override fun getFee(amount: Double): Double = 0.0

    /**
     * Memproses pembayaran tunai.
     *
     * @param amount nominal pembayaran.
     * @return hasil transaksi.
     */
    override fun processPayment(amount: Double): PaymentResult {
        return PaymentResult.Success(
            "CASH-${System.currentTimeMillis()}",
            LocalDateTime.now().toString()
        )
    }
}
