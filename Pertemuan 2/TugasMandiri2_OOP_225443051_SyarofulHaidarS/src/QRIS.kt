// ==========================================
// 12. QRIS.kt
// ==========================================
class QRIS(val qrcode: String) : PaymentMethod {
    override val name: String = "QRIS"

    override fun getFee(amount: Double): Double = amount * 0.005

    override fun processPayment(amount: Double): PaymentResult {
        return if (qrcode.length >= 10) {
            PaymentResult.Success("QRIS-${System.currentTimeMillis()}", java.time.LocalDateTime.now().toString())
        } else {
            PaymentResult.Failed("Kode QR tidak valid", 400)
        }
    }
}
