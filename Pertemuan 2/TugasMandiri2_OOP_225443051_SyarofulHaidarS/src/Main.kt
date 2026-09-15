/**
 * Fungsi utama untuk menjalankan seluruh demonstrasi tugas mandiri PBO.
 *
 * Demonstrasi mencakup class dan object, encapsulation, inheritance,
 * polymorphism, interface, sealed class, smart casting, dan integrasi sistem.
 */
fun main() {
    val system = TransportSystem("Go-Transport 2024")

    val car = Car("B 1234 XYZ", "Toyota", "Innova", 2021, "Bensin", 4)
    val motorcycle = Motorcycle("D 5678 ABC", "Honda", "Beat", 2022, 125, true)
    val truck = Truck("E 9012 DEF", "Hino", "Dutro", 2020, 5.0, 2)

    system.addVehicle(car)
    system.addVehicle(motorcycle)
    system.addVehicle(truck)

    val driver1 = Driver("D001", "Andi", "08123456789", car)
    val driver2 = Driver("D002", "Budi", "08129876543", motorcycle)
    val driver3 = Driver("D003", "Citra", "08125678901", truck)

    system.addDriver(driver1)
    system.addDriver(driver2)
    system.addDriver(driver3)

    val customer1 = Customer("C001", "Dewi", "08134567890", "dewi@email.com", 100000.0)
    val customer2 = Customer("C002", "Eko", "08135678901", "eko@email.com", 50000.0)
    val customer3 = Customer("C003", "Fani", "08136789012", "fani@email.com", 200000.0)

    system.addCustomer(customer1)
    system.addCustomer(customer2)
    system.addCustomer(customer3)

    println("=== ${system.name} ===\n")

    println("=== TARIF KENDARAAN UNTUK JARAK 20 KM ===")
    println("Mobil: ${formatRupiah(car.calculateFare(20.0))}")
    println("Motor: ${formatRupiah(motorcycle.calculateFare(20.0))}")
    println("Truk: ${formatRupiah(truck.calculateFare(20.0))}\n")

    system.displayAllVehicles()
    system.displayAllDrivers()
    system.displayAllCustomers()

    val order1 = system.createOrder(
        "C001",
        "D001",
        "Kampus A",
        "Mall B",
        12.0
    ) ?: return

    val order2 = system.createOrder(
        "C002",
        "D002",
        "Stasiun",
        "Kantor",
        8.0
    ) ?: return

    val order3 = system.createOrder(
        "C003",
        "D003",
        "Gudang",
        "Pelabuhan",
        25.0
    ) ?: return

    println("=== DATA AWAL ORDER ===")
    system.displayAllOrders()

    println("=== PEMBAYARAN ORDER 1 ===")
    val qris = QRIS("12345678901234")
    val qrisTotal = order1.totalFare + qris.getFee(order1.totalFare)
    val pay1 = system.processPayment(order1.id, qris, qrisTotal)
    println(pay1.display())
    println()

    println("=== PEMBAYARAN ORDER 2 ===")
    val cash = Cash()
    val pay2 = system.processPayment(order2.id, cash, 10000.0)
    println("Pembayaran tunai kurang: ${pay2.display()}")

    customer2.topUp(20000.0)
    println("Saldo Eko setelah top up: ${formatRupiah(customer2.balance)}")

    val creditCard = CreditCard("1234567890123456")
    val creditCardTotal = order2.totalFare + creditCard.getFee(order2.totalFare)
    val pay3 = system.processPayment(order2.id, creditCard, creditCardTotal)
    println("Pembayaran kartu kredit: ${pay3.display()}")
    println()

    println("=== PENYELESAIAN ORDER 1 ===")
    println("Mulai Order 1 : ${order1.startTrip()}")
    println("Selesai Order 1 : ${system.completeOrder(order1.id)}")
    println()

    println("=== PEMBATALAN ORDER 3 ===")
    println("Order 3 dibatalkan: ${system.cancelOrder(order3.id, "Hujan deras")}")
    println()

    println("=== STATUS AKHIR ORDER ===")
    system.displayAllOrders()

    system.displayRevenueReport()

    println("\n=== DEMONSTRASI SOAL 4 ===")
    val methods: List<PaymentMethod> = listOf(
        CreditCard("1234567890123456"),
        QRIS("12345678901234"),
        Cash()
    )

    methods.forEach { method ->
        val result = method.processPayment(50000.0)

        when (result) {
            is PaymentResult.Success -> println("${method.name}: ${result.display()}")
            is PaymentResult.Failed -> println("${method.name}: ${result.display()}")
            PaymentResult.Pending -> println("${method.name}: ${result.display()}")
        }
    }

    println("\n=== DEMONSTRASI POLIMORFISME ===")
    val vehicleList: List<Vehicle> = listOf(car, motorcycle, truck)

    vehicleList.forEach { vehicle ->
        println(
            "${vehicle.getType()} ${vehicle.plateNumber}: " +
                formatRupiah(vehicle.calculateFare(15.0))
        )

        when (vehicle) {
            is Car -> println("Bahan bakar : ${vehicle.fuelType}")
            is Motorcycle -> println("Kapasitas mesin : ${vehicle.engineCapacity} cc")
            is Truck -> println("Kapasitas muatan : ${vehicle.loadCapacity} ton")
        }
    }

    val safeCast = driver1.vehicle as? Car
    println(
        "\nHasil casting aman kendaraan D001: " +
            (safeCast?.fuelType ?: "Bukan Mobil")
    )

    println("\n=== DEMONSTRASI SEALED CLASS ===")
    val statuses: List<OrderStatus> = listOf(
        OrderStatus.Waiting,
        OrderStatus.OnGoing,
        OrderStatus.Completed,
        OrderStatus.Cancelled("Perubahan rencana")
    )

    statuses.forEach { status ->
        when (status) {
            is OrderStatus.Waiting -> println(status.display())
            is OrderStatus.OnGoing -> println(status.display())
            is OrderStatus.Completed -> println(status.display())
            is OrderStatus.Cancelled -> println(status.display())
        }
    }

    println("\n=== DEMONSTRASI ENKAPSULASI ===")
    println("Status order hanya dapat diubah melalui method startTrip(), completeTrip(), atau cancelTrip().")
    println("Akses langsung terhadap setter status dibatasi karena menggunakan private set.")
}
