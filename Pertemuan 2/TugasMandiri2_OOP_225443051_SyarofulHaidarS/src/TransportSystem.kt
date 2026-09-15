/**
 * Class utama yang mengelola seluruh data dalam sistem transportasi online.
 *
 * @property name nama sistem transportasi.
 */
class TransportSystem(val name: String) {
    private val vehicles = mutableListOf<Vehicle>()
    private val drivers = mutableListOf<Driver>()
    private val customers = mutableListOf<Customer>()
    private val orders = mutableListOf<Order>()
    private val payments = mutableListOf<Payment>()

    /**
     * Mengembalikan daftar kendaraan dalam bentuk read-only list.
     */
    val vehiclesList: List<Vehicle>
        get() = vehicles.toList()

    /**
     * Menambahkan kendaraan ke sistem.
     *
     * @param vehicle kendaraan yang akan ditambahkan.
     */
    fun addVehicle(vehicle: Vehicle) {
        vehicles.add(vehicle)
    }

    /**
     * Menambahkan driver ke sistem.
     *
     * @param driver driver yang akan ditambahkan.
     */
    fun addDriver(driver: Driver) {
        drivers.add(driver)
    }

    /**
     * Menambahkan customer ke sistem.
     *
     * @param customer customer yang akan ditambahkan.
     */
    fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    /**
     * Mencari kendaraan berdasarkan nomor polisi.
     *
     * @param plate nomor polisi kendaraan.
     * @return kendaraan yang ditemukan atau `null`.
     */
    fun findVehicle(plate: String): Vehicle? {
        return vehicles.find { it.plateNumber.equals(plate, true) }
    }

    /**
     * Mencari driver berdasarkan ID.
     *
     * @param id ID driver.
     * @return driver yang ditemukan atau `null`.
     */
    fun findDriver(id: String): Driver? {
        return drivers.find { it.id.equals(id, true) }
    }

    /**
     * Mencari customer berdasarkan ID.
     *
     * @param id ID customer.
     * @return customer yang ditemukan atau `null`.
     */
    fun findCustomer(id: String): Customer? {
        return customers.find { it.id.equals(id, true) }
    }

    /**
     * Membuat order baru dari customer dan driver yang tersedia.
     *
     * @param customerId ID customer.
     * @param driverId ID driver.
     * @param pickup lokasi penjemputan.
     * @param dest lokasi tujuan.
     * @param distance jarak perjalanan.
     * @return order baru atau `null` jika data tidak ditemukan atau driver tidak tersedia.
     */
    fun createOrder(
        customerId: String,
        driverId: String,
        pickup: String,
        dest: String,
        distance: Double
    ): Order? {
        val customer = findCustomer(customerId) ?: return null
        val driver = findDriver(driverId) ?: return null

        if (!driver.acceptOrder()) {
            println("Driver tidak aktif atau kendaraan tidak tersedia.")
            return null
        }

        driver.vehicle.isAvailable = false
        val order = Order(
            id = "ORD-%03d".format(orders.size + 1),
            customer = customer,
            driver = driver,
            pickupLocation = pickup,
            destination = dest,
            distanceKm = distance
        )
        orders.add(order)
        return order
    }

    /**
     * Memproses pembayaran untuk sebuah order.
     *
     * @param orderId ID order.
     * @param method metode pembayaran.
     * @param paidAmount nominal yang dibayarkan.
     * @return hasil pembayaran.
     */
    fun processPayment(
        orderId: String,
        method: PaymentMethod,
        paidAmount: Double
    ): PaymentResult {
        val order = orders.find { it.id == orderId }
            ?: return PaymentResult.Failed("Order tidak ditemukan", 404)

        val payment = Payment(order, method)
        payments.add(payment)
        return payment.processPayment(paidAmount)
    }

    /**
     * Menyelesaikan sebuah order.
     *
     * @param orderId ID order.
     * @return `true` jika order berhasil diselesaikan.
     */
    fun completeOrder(orderId: String): Boolean {
        val order = orders.find { it.id == orderId } ?: return false
        return order.completeTrip()
    }

    /**
     * Membatalkan sebuah order.
     *
     * @param orderId ID order.
     * @param reason alasan pembatalan.
     * @return `true` jika pembatalan berhasil.
     */
    fun cancelOrder(orderId: String, reason: String): Boolean {
        val order = orders.find { it.id == orderId } ?: return false
        return order.cancelTrip(reason)
    }

    /**
     * Menampilkan semua kendaraan yang terdaftar.
     */
    fun displayAllVehicles() {
        println("=== DAFTAR KENDARAAN ===")
        vehicles.forEach {
            it.displayInfo()
            println()
        }
    }

    /**
     * Menampilkan semua driver yang terdaftar.
     */
    fun displayAllDrivers() {
        println("=== DAFTAR PENGEMUDI ===")
        drivers.forEach {
            it.displayInfo()
            println()
        }
    }

    /**
     * Menampilkan semua customer yang terdaftar.
     */
    fun displayAllCustomers() {
        println("=== DAFTAR PELANGGAN ===")
        customers.forEach {
            it.displayInfo()
            println()
        }
    }

    /**
     * Menampilkan semua order yang terdaftar.
     */
    fun displayAllOrders() {
        println("=== DAFTAR PESANAN ===")
        orders.forEach {
            it.displayOrder()
            println()
        }
    }

    /**
     * Menghitung dan menampilkan total pendapatan dari order yang selesai.
     */
    fun displayRevenueReport() {
        val totalRevenue = orders
            .filter { it.status is OrderStatus.Completed }
            .sumOf { it.totalFare }

        println("=== LAPORAN PENDAPATAN ===")
        println("Pendapatan pesanan selesai: ${formatRupiah(totalRevenue)}")
    }
}
