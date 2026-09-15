/**
 * Menjalankan demonstrasi lengkap Sistem Manajemen Transportasi Online.
 */
fun main() {
    val system = TransportSystem("Go-Transport 2024")

    val car = Car("B 1234 XYZ", "Toyota", "Innova", 2021, "Bensin", 4)
    val motorcycle = Motorcycle("D 5678 ABC", "Honda", "Beat", 2022, 125, true)
    val truck = Truck("E 9012 DEF", "Hino", "Dutro", 2020, 5.0, 2)

    system.addVehicle(car)
    system.addVehicle(motorcycle)
    system.addVehicle(truck)

    val andi = Driver("D001", "Andi", "08123456789", car)
    val budi = Driver("D002", "Budi", "08129876543", motorcycle)
    val citra = Driver("D003", "Citra", "08125678901", truck)

    system.addDriver(andi)
    system.addDriver(budi)
    system.addDriver(citra)

    val dewi = Customer("C001", "Dewi", "08134567890", "dewi@email.com", 100000.0)
    val eko = Customer("C002", "Eko", "08135678901", "eko@email.com", 50000.0)
    val fani = Customer("C003", "Fani", "08136789012", "fani@email.com", 200000.0)

    system.addCustomer(dewi)
    system.addCustomer(eko)
    system.addCustomer(fani)

    println("=== ${system.name} ===")

    println("\n=== TARIF KENDARAAN UNTUK JARAK 20 KM ===")
    listOf<Vehicle>(car, motorcycle, truck).forEach { vehicle ->
        println("${vehicle.getType()}: ${formatRupiah(vehicle.calculateFare(20.0))}")
    }
    system.displayAllVehicles()
    system.displayAllDrivers()
    system.displayAllCustomers()

    val order1 = system.createOrder("C001", "D001", "Kampus A", "Mall B", 12.0)
    val order2 = system.createOrder("C002", "D002", "Stasiun", "Kantor", 8.0)
    val order3 = system.createOrder("C003", "D003", "Gudang", "Pelabuhan", 25.0)

    if (order1 == null || order2 == null || order3 == null) {
        println("Pesanan gagal dibuat karena data atau kendaraan tidak tersedia.")
        return
    }

    system.displayAllOrders()

    println("=== PEMBAYARAN PESANAN 1 ===")
    val qris = QRIS("QRIS-DEWI-12345")
    val payment1 = system.processPayment(order1.id, qris, order1.getTotalFare())
    displayPaymentResult(payment1)

    println("\n=== PEMBAYARAN PESANAN 2 ===")
    val payment2Failed = system.processPayment(order2.id, Cash(), 10000.0)
    displayPaymentResult(payment2Failed)
    eko.topUp(20000.0)
    println("Saldo Eko setelah top up: ${formatRupiah(eko.balance)}")
    val payment2Success = system.processPayment(order2.id, CreditCard("4111111111111111"), order2.getTotalFare())
    displayPaymentResult(payment2Success)

    println("\nOrder 1 selesai: ${system.completeOrder(order1.id)}")
    println("Order 3 dibatalkan: ${system.cancelOrder(order3.id, "Hujan deras")}")
    system.displayAllOrders()
    system.displayRevenueReport()

    println("\n=== DEMONSTRASI SOAL 4 ===")
    val vehicles: List<Vehicle> = listOf(car, motorcycle, truck)
    vehicles.forEach { vehicle ->
        println("Tarif ${vehicle.getType()} untuk 10 km: ${formatRupiah(vehicle.calculateFare(10.0))}")
    }
    val paymentMethods: List<PaymentMethod> = listOf(
        CreditCard("5555555555554444"),
        QRIS("QRIS-CONTOH-12345"),
        Cash()
    )
    paymentMethods.forEach { method ->
        print("${method.name}: ")
        displayPaymentResult(method.processPayment(100000.0))
    }

    println("\n=== DEMONSTRASI POLIMORFISME ===")
    system.getVehicles().forEach { vehicle ->
        println("${vehicle.getType()} ${vehicle.plateNumber}: ${formatRupiah(vehicle.calculateFare(15.0))}")
        when (vehicle) {
            is Car -> println("Bahan bakar: ${vehicle.fuelType}")
            is Motorcycle -> println("Kapasitas mesin: ${vehicle.engineCapacity} cc")
            is Truck -> println("Kapasitas muatan: ${vehicle.loadCapacity} ton")
        }
    }

    val carDriver = system.findDriver("D001")?.vehicle as? Car
    println("\nHasil casting aman kendaraan D001: ${carDriver?.fuelType ?: "bukan mobil"}")

    println("\n=== DEMONSTRASI SEALED CLASS ===")
    val statuses: List<OrderStatus> = listOf(
        OrderStatus.Waiting,
        OrderStatus.OnGoing,
        OrderStatus.Completed,
        OrderStatus.Cancelled("Perubahan rencana")
    )
    statuses.forEach { status ->
        when (status) {
            OrderStatus.Waiting -> println(status.display())
            OrderStatus.OnGoing -> println(status.display())
            OrderStatus.Completed -> println(status.display())
            is OrderStatus.Cancelled -> println(status.display())
        }
    }
}

/**
 * Menampilkan hasil pembayaran dengan when yang ekshaustif untuk sealed class.
 */
fun displayPaymentResult(result: PaymentResult) {
    when (result) {
        is PaymentResult.Success -> println("Berhasil. ID transaksi: ${result.transactionId}")
        is PaymentResult.Failed -> println("Gagal. ${result.reason} (kode ${result.errorCode})")
        PaymentResult.Pending -> println("Pembayaran masih menunggu.")
    }
}
