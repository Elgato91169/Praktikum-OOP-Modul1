// ==========================================
// 13. Cash.kt
// ==========================================
class Cash : PaymentMethod {
    override val name: String = "Tunai"

    override fun processPayment(amount: Double): PaymentResult {
        return PaymentResult.Success("CASH-${System.currentTimeMillis()}", java.time.LocalDateTime.now().toString())
    }
}
