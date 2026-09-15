/**
 * Class yang mengelola pembayaran sebuah order.
 *
 * @property order order yang akan dibayar.
 * @property method metode pembayaran yang digunakan.
 */
class Payment(
    val order: Order,
    val method: PaymentMethod
) {
    /**
     * Nominal dasar tagihan dari order.
     */
    val amount: Double = order.totalFare

    /**
     * Status pembayaran. Setter dibuat private agar tidak dapat diubah langsung dari luar.
     */
    var isPaid: Boolean = false
        private set

    /**
     * Memproses pembayaran beserta biaya layanan metode yang dipilih.
     *
     * @param paidAmount nominal yang dibayarkan.
     * @return hasil proses pembayaran.
     */
    fun processPayment(paidAmount: Double): PaymentResult {
        val totalDue = amount + method.getFee(amount)

        if (!isPaid && paidAmount >= totalDue) {
            val result = method.processPayment(amount)
            if (result is PaymentResult.Success) {
                isPaid = true
            }
            return result
        }

        return PaymentResult.Failed(
            "Nominal pembayaran kurang dari tagihan ${formatRupiah(totalDue)}",
            400
        )
    }

    /**
     * Menampilkan detail pembayaran.
     */
    fun displayPayment() {
        val totalDue = amount + method.getFee(amount)
        println("Tagihan dasar : ${formatRupiah(amount)}")
        println("Biaya layanan : ${formatRupiah(method.getFee(amount))}")
        println("Total tagihan : ${formatRupiah(totalDue)}")
        println("Metode : ${method.name}")
        println("Status Lunas : ${if (isPaid) "Sudah" else "Belum"}")
    }
}
