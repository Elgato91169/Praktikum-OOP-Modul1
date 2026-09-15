/**
 * Pengendali utama untuk mengelola data dan operasi transportasi online.
 */
class TransportSystem(
    /** Nama sistem transportasi. */
    val name: String
) {
    /** Daftar kendaraan yang terdaftar pada sistem. */
    private val vehicles: MutableList<Vehicle> = mutableListOf()

    /** Daftar pengemudi yang terdaftar pada sistem. */
    private val drivers: MutableList<Driver> = mutableListOf()

    /** Daftar pelanggan yang terdaftar pada sistem. */
    private val customers: MutableList<Customer> = mutableListOf()

    /** Daftar pesanan yang dibuat pada sistem. */
    private val orders: MutableList<Order> = mutableListOf()

    /** Daftar pembayaran yang diproses pada sistem. */
    private val payments: MutableList<Payment> = mutableListOf()

    /** Menambahkan kendaraan ke dalam sistem. */
    fun addVehicle(vehicle: Vehicle) {
        vehicles.add(vehicle)
    }

    /** Menambahkan pengemudi ke dalam sistem. */
    fun addDriver(driver: Driver) {
        drivers.add(driver)
    }

    /** Menambahkan pelanggan ke dalam sistem. */
    fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    /** Mencari kendaraan berdasarkan nomor polisi. */
    fun findVehicle(plateNumber: String): Vehicle? = vehicles.find { it.plateNumber == plateNumber }

    /** Mencari pengemudi berdasarkan ID. */
    fun findDriver(id: String): Driver? = drivers.find { it.id == id }

    /** Mencari pelanggan berdasarkan ID. */
    fun findCustomer(id: String): Customer? = customers.find { it.id == id }

    /** Mengembalikan salinan daftar kendaraan agar data internal tetap terlindungi. */
    fun getVehicles(): List<Vehicle> = vehicles.toList()

    /** Membuat pesanan baru apabila pelanggan dan pengemudi tersedia. */
    fun createOrder(customerId: String, driverId: String, pickup: String, dest: String, distance: Double): Order? {
        val customer = findCustomer(customerId) ?: return null
        val driver = findDriver(driverId) ?: return null
        if (distance <= 0 || !driver.acceptOrder()) return null

        val order = Order("ORD-%03d".format(orders.size + 1), customer, driver, pickup, dest, distance)
        orders.add(order)
        driver.vehicle.isAvailable = false
        return order
    }

    /** Memproses pembayaran suatu pesanan dengan metode yang dipilih. */
    fun processPayment(orderId: String, method: PaymentMethod, paidAmount: Double): PaymentResult {
        val order = orders.find { it.id == orderId }
            ?: return PaymentResult.Failed("Pesanan tidak ditemukan.", 404)
        val payment = Payment(order, method)
        val result = payment.processPayment(paidAmount)
        payments.add(payment)
        return result
    }

    /** Menyelesaikan pesanan dan mengembalikan ketersediaan kendaraan. */
    fun completeOrder(orderId: String): Boolean {
        val order = orders.find { it.id == orderId } ?: return false
        if (order.status is OrderStatus.Waiting) order.startTrip()
        val completed = order.completeTrip()
        if (completed) order.driver.vehicle.isAvailable = true
        return completed
    }

    /** Membatalkan pesanan dan mengembalikan ketersediaan kendaraan. */
    fun cancelOrder(orderId: String, reason: String): Boolean {
        val order = orders.find { it.id == orderId } ?: return false
        val cancelled = order.cancelTrip(reason)
        if (cancelled) order.driver.vehicle.isAvailable = true
        return cancelled
    }

    /** Menampilkan seluruh kendaraan yang terdaftar. */
    fun displayAllVehicles() {
        println("\n=== DAFTAR KENDARAAN ===")
        vehicles.forEach {
            it.displayInfo()
            println()
        }
    }

    /** Menampilkan seluruh pengemudi yang terdaftar. */
    fun displayAllDrivers() {
        println("\n=== DAFTAR PENGEMUDI ===")
        drivers.forEach {
            it.displayInfo()
            println()
        }
    }

    /** Menampilkan seluruh pelanggan yang terdaftar. */
    fun displayAllCustomers() {
        println("\n=== DAFTAR PELANGGAN ===")
        customers.forEach {
            it.displayInfo()
            println()
        }
    }

    /** Menampilkan seluruh pesanan yang pernah dibuat. */
    fun displayAllOrders() {
        println("\n=== DAFTAR PESANAN ===")
        orders.forEach {
            it.displayOrder()
            println()
        }
    }

    /** Menghitung dan menampilkan pendapatan dari pesanan yang selesai. */
    fun displayRevenueReport() {
        val totalRevenue = orders.filter { it.status is OrderStatus.Completed }.sumOf { it.getTotalFare() }
        println("\n=== LAPORAN PENDAPATAN ===")
        println("Pendapatan pesanan selesai: ${formatRupiah(totalRevenue)}")
    }
}
