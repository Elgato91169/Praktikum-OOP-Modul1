/**
 * Class yang merepresentasikan pesanan perjalanan.
 *
 * @property id ID order.
 * @property customer pelanggan yang membuat order.
 * @property driver driver yang menerima order.
 * @property pickupLocation lokasi penjemputan.
 * @property destination lokasi tujuan.
 * @property distanceKm jarak perjalanan dalam kilometer.
 */
class Order(
    val id: String,
    val customer: Customer,
    val driver: Driver,
    val pickupLocation: String,
    val destination: String,
    val distanceKm: Double
) {
    /**
     * Status order. Setter dibuat private agar perubahan status hanya melalui method class.
     */
    var status: OrderStatus = OrderStatus.Waiting
        private set

    /**
     * Total tarif perjalanan yang dihitung berdasarkan kendaraan driver.
     */
    val totalFare: Double = driver.vehicle.calculateFare(distanceKm)

    /**
     * Mengubah status order dari Menunggu menjadi Berjalan.
     *
     * @return `true` jika perubahan berhasil.
     */
    fun startTrip(): Boolean {
        if (status is OrderStatus.Waiting) {
            status = OrderStatus.OnGoing
            return true
        }
        return false
    }

    /**
     * Mengubah status order dari Berjalan menjadi Selesai.
     *
     * @return `true` jika perubahan berhasil.
     */
    fun completeTrip(): Boolean {
        if (status is OrderStatus.OnGoing) {
            status = OrderStatus.Completed
            driver.vehicle.isAvailable = true
            return true
        }
        return false
    }

    /**
     * Membatalkan order selama status belum final.
     *
     * @param reason alasan pembatalan.
     * @return `true` jika pembatalan berhasil.
     */
    fun cancelTrip(reason: String): Boolean {
        if (!status.isFinal()) {
            status = OrderStatus.Cancelled(reason)
            driver.vehicle.isAvailable = true
            return true
        }
        return false
    }

    /**
     * Menampilkan detail order.
     */
    fun displayOrder() {
        println("ID pesanan : $id")
        println("Pelanggan : ${customer.name}")
        println("Pengemudi : ${driver.name}")
        println("Rute : $pickupLocation -> $destination")
        println("Jarak : $distanceKm km")
        println("Total tarif : ${formatRupiah(totalFare)}")
        println("Status : ${status.display()}")
    }
}
