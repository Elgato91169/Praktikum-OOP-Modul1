/**
 * Menghubungkan pelanggan, pengemudi, dan detail perjalanan dalam satu pesanan.
 */
class Order(
    /** ID unik pesanan. */
    val id: String,
    /** Pelanggan yang membuat pesanan. */
    val customer: Customer,
    /** Pengemudi yang menerima pesanan. */
    val driver: Driver,
    /** Lokasi penjemputan pelanggan. */
    val pickupLocation: String,
    /** Lokasi tujuan perjalanan. */
    val destination: String,
    /** Jarak perjalanan dalam kilometer. */
    val distanceKm: Double
) {
    /** Status terkini pesanan. */
    var status: OrderStatus = OrderStatus.Waiting

    /** Total tarif yang dihitung dari jenis kendaraan dan jarak perjalanan. */
    private var _totalFare: Double

    init {
        _totalFare = driver.vehicle.calculateFare(distanceKm)
    }

    /** Mengembalikan total tarif pesanan. */
    fun getTotalFare(): Double = _totalFare

    /** Memulai perjalanan apabila pesanan masih menunggu. */
    fun startTrip(): Boolean {
        if (status !is OrderStatus.Waiting) return false
        status = OrderStatus.OnGoing
        return true
    }

    /** Menyelesaikan perjalanan apabila perjalanan sedang berlangsung. */
    fun completeTrip(): Boolean {
        if (status !is OrderStatus.OnGoing) return false
        status = OrderStatus.Completed
        return true
    }

    /** Membatalkan pesanan selama pesanan belum berada pada status akhir. */
    fun cancelTrip(reason: String): Boolean {
        if (status.isFinal() || reason.isBlank()) return false
        status = OrderStatus.Cancelled(reason)
        return true
    }

    /** Menampilkan semua detail pesanan ke konsol. */
    fun displayOrder() {
        println("ID pesanan    : $id")
        println("Pelanggan     : ${customer.name}")
        println("Pengemudi     : ${driver.name}")
        println("Rute          : $pickupLocation -> $destination")
        println("Jarak         : $distanceKm km")
        println("Total tarif   : ${formatRupiah(_totalFare)}")
        println("Status        : ${status.display()}")
    }
}
