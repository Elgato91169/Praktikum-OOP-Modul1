/**
 * Menjalankan demonstrasi polimorfisme, interface, dan sealed class pada Soal 4.
 */
object MainSoal4 {
    /** Menjalankan contoh polymorphic reference, smart casting, dan pembayaran. */
    @JvmStatic
    fun main(args: Array<String>) {
        val vehicles: List<Vehicle> = listOf(
            Car("B 1234 XYZ", "Toyota", "Innova", 2021, "Bensin", 4),
            Motorcycle("D 5678 ABC", "Honda", "Beat", 2022, 125, true),
            Truck("E 9012 DEF", "Hino", "Dutro", 2020, 5.0, 2)
        )

        println("=== SOAL 4: POLIMORFISME, INTERFACE, DAN SEALED CLASS ===")
        vehicles.forEach { vehicle ->
            println("${vehicle.getType()} - tarif 10 km: ${formatRupiah(vehicle.calculateFare(10.0))}")
            when (vehicle) {
                is Car -> println("Bahan bakar: ${vehicle.fuelType}")
                is Motorcycle -> println("Kapasitas mesin: ${vehicle.engineCapacity} cc")
                is Truck -> println("Kapasitas muatan: ${vehicle.loadCapacity} ton")
            }
        }

        println("\n=== METODE PEMBAYARAN ===")
        val methods: List<PaymentMethod> = listOf(
            CreditCard("5555555555554444"),
            QRIS("QRIS-CONTOH-12345"),
            Cash()
        )
        methods.forEach { method ->
            print("${method.name}: ")
            displayPaymentResult(method.processPayment(100000.0))
        }

        println("\n=== STATUS PESANAN ===")
        val statuses: List<OrderStatus> = listOf(
            OrderStatus.Waiting,
            OrderStatus.OnGoing,
            OrderStatus.Completed,
            OrderStatus.Cancelled("Pelanggan berubah rencana")
        )
        statuses.forEach { status ->
            when (status) {
                OrderStatus.Waiting -> println(status.display())
                OrderStatus.OnGoing -> println(status.display())
                OrderStatus.Completed -> println(status.display())
                is OrderStatus.Cancelled -> println(status.display())
            }
        }

        val car = vehicles[0] as? Car
        println("\nHasil casting aman kendaraan pertama: ${car?.fuelType ?: "bukan mobil"}")
    }
}
