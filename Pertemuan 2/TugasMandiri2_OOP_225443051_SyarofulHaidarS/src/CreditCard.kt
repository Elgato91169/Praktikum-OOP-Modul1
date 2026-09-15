// ==========================================
// 11. CreditCard.kt
// ==========================================
class CreditCard(val cardNumber: String) : PaymentMethod {
    override val name: String = "Kartu Kredit"

    override fun getFee(amount: Double): Double = amount * 0.02

    override fun processPayment(amount: Double): PaymentResult {
        return if (cardNumber.length >= 16) {
            PaymentResult.Success("CC-${System.currentTimeMillis()}", java.time.LocalDateTime.now().toString())
        } else {
            PaymentResult.Failed("Nomor kartu tidak valid", 400)
        }
    }
}
