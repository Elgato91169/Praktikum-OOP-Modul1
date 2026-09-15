import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Kontrak metode pembayaran yang dapat digunakan pada sistem.
 */
interface PaymentMethod {
    /** Nama metode pembayaran. */
    val name: String

    /** Memproses pembayaran sesuai metode yang dipilih. */
    fun processPayment(amount: Double): PaymentResult

    /** Menghitung biaya tambahan dari penggunaan metode pembayaran. */
    fun getFee(amount: Double): Double = 0.0
}

/**
 * Metode pembayaran menggunakan kartu kredit.
 */
class CreditCard(
    /** Nomor kartu kredit yang digunakan. */
    val cardNumber: String
) : PaymentMethod {
    init {
        require(cardNumber.filter(Char::isDigit).length >= 16) { "Nomor kartu kredit minimal 16 digit." }
    }

    /** Nama metode pembayaran kartu kredit. */
    override val name: String = "Kartu Kredit"

    /** Memproses pembayaran kartu kredit. */
    override fun processPayment(amount: Double): PaymentResult = createSuccessResult("CC")

    /** Menghitung biaya layanan kartu kredit sebesar dua persen. */
    override fun getFee(amount: Double): Double = amount * 0.02
}

/**
 * Metode pembayaran menggunakan kode QRIS.
 */
class QRIS(
    /** Kode QR yang digunakan untuk pembayaran. */
    val qrCode: String
) : PaymentMethod {
    init {
        require(qrCode.length >= 10) { "Kode QRIS minimal 10 karakter." }
    }

    /** Nama metode pembayaran QRIS. */
    override val name: String = "QRIS"

    /** Memproses pembayaran QRIS. */
    override fun processPayment(amount: Double): PaymentResult = createSuccessResult("QRIS")

    /** Menghitung biaya layanan QRIS sebesar nol koma lima persen. */
    override fun getFee(amount: Double): Double = amount * 0.005
}

/**
 * Metode pembayaran secara tunai.
 */
class Cash : PaymentMethod {
    /** Nama metode pembayaran tunai. */
    override val name: String = "Tunai"

    /** Memproses pembayaran tunai. */
    override fun processPayment(amount: Double): PaymentResult = createSuccessResult("CASH")

    /** Mengembalikan biaya layanan tunai yang tidak memiliki biaya tambahan. */
    override fun getFee(amount: Double): Double = 0.0
}

/**
 * Membuat hasil sukses dengan ID transaksi dan waktu proses saat ini.
 */
fun createSuccessResult(prefix: String): PaymentResult.Success {
    val time = LocalDateTime.now()
    val timestamp = time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
    return PaymentResult.Success("$prefix-${System.nanoTime()}", timestamp)
}
