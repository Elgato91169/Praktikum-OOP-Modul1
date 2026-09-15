/**
 * Menangani pembayaran untuk sebuah pesanan dengan metode yang dipilih.
 */
class Payment(
    /** Pesanan yang dibayarkan. */
    val order: Order,
    /** Metode pembayaran yang digunakan. */
    var method: PaymentMethod
) {
    /** Nominal tagihan pesanan sebelum biaya metode pembayaran. */
    private var _amount: Double = order.getTotalFare()

    /**
     * Menandakan apakah pembayaran telah berhasil.
     * Nilainya dapat dibaca dari luar kelas, tetapi hanya kelas ini yang dapat mengubahnya.
     */
    var _isPaid: Boolean = false
        private set

    /** Hasil terakhir dari proses pembayaran. */
    private var result: PaymentResult = PaymentResult.Pending

    /** Mengembalikan nominal tagihan pesanan. */
    fun getAmount(): Double = _amount

    /** Memeriksa apakah pembayaran telah berhasil dilakukan. */
    fun isPaid(): Boolean = _isPaid

    /** Memproses pembayaran apabila nominal yang dibayarkan mencukupi. */
    fun processPayment(paidAmount: Double): PaymentResult {
        if (_isPaid) {
            result = PaymentResult.Failed("Pesanan ini sudah dibayar.", 409)
            return result
        }

        if (paidAmount < _amount) {
            result = PaymentResult.Failed("Nominal pembayaran kurang dari tagihan ${formatRupiah(_amount)}.", 400)
            return result
        }

        result = method.processPayment(_amount)
        _isPaid = result is PaymentResult.Success
        return result
    }

    /** Menampilkan detail pembayaran ke konsol. */
    fun displayPayment() {
        println("ID pesanan    : ${order.id}")
        println("Metode        : ${method.name}")
        println("Tagihan       : ${formatRupiah(_amount)}")
        println("Biaya metode  : ${formatRupiah(method.getFee(_amount))}")
        println("Status bayar  : ${if (_isPaid) "Lunas" else "Belum lunas"}")
        println(result.display())
    }
}
