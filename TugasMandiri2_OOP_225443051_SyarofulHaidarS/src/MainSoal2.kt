/**
 * Menjalankan demonstrasi enkapsulasi pada Soal 2.
 */
object MainSoal2 {
    /** Menjalankan contoh pengelolaan status pesanan dan pembayaran. */
    @JvmStatic
    fun main(args: Array<String>) {
        val vehicle = Vehicle("B 1234 ABC", "Toyota", "Avanza", 2020)
        val driver = Driver("D001", "Andi", "08123456789", vehicle)
        val customer = Customer("C001", "Dewi", "08134567890", "dewi@email.com", 50000.0)
        val order = Order("ORD-001", customer, driver, "Kampus A", "Mall B", 15.0)

        println("=== SOAL 2: ENKAPSULASI ===")
        order.displayOrder()
        println("\nPerjalanan dimulai: ${order.startTrip()}")
        println("Perjalanan selesai: ${order.completeTrip()}")
        println("Membatalkan pesanan selesai: ${order.cancelTrip("Tidak jadi")}")

        // Pada tahap Soal 2, status disimpan sebagai properti private bernama _status.
        // order._status = "Selesai" // ERROR: Cannot access private property
        // Pada versi akhir (Soal 4), String tersebut diganti oleh sealed class OrderStatus.
        println("Enkapsulasi: status pesanan hanya berubah melalui fungsi Order.")

        val payment = Payment(order, QRIS("QRIS-CONTOH-12345"))
        println("\nNominal pembayaran: ${formatRupiah(payment.getAmount())}")
        displayPaymentResult(payment.processPayment(payment.getAmount()))
        payment.displayPayment()
    }
}
