/**
 * Menjalankan demonstrasi konsep dasar OOP pada Soal 1.
 */
object MainSoal1 {
    /** Menjalankan contoh kelas Vehicle, Driver, dan Customer. */
    @JvmStatic
    fun main(args: Array<String>) {
        val vehicle = Vehicle("B 1234 ABC", "Toyota", "Avanza", 2020)
        val driver = Driver("D001", "Andi", "08123456789", vehicle)
        val customer = Customer("C001", "Dewi", "08134567890", "dewi@email.com", 50000.0)

        println("=== SOAL 1: KELAS DASAR ===")
        println("\nData kendaraan")
        vehicle.displayInfo()
        println("\nData pengemudi")
        driver.displayInfo()
        println("\nData pelanggan")
        customer.displayInfo()

        println("\nTarif perjalanan 10 km: ${formatRupiah(vehicle.calculateFare(10.0))}")
        customer.topUp(100000.0)
        println("Saldo setelah top up: ${formatRupiah(customer.balance)}")
        println("Driver dapat menerima pesanan: ${driver.acceptOrder()}")
    }
}
