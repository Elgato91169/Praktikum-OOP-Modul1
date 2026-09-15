/**
 * Class dasar yang merepresentasikan kendaraan pada sistem transportasi online.
 *
 * @property plateNumber nomor polisi kendaraan.
 * @property brand merek kendaraan.
 * @property model model kendaraan.
 * @property year tahun kendaraan.
 * @property isAvailable status ketersediaan kendaraan.
 */
open class Vehicle(
    val plateNumber: String,
    val brand: String,
    val model: String,
    val year: Int,
    var isAvailable: Boolean = true
) {
    /**
     * Mengembalikan jenis kendaraan.
     *
     * @return jenis kendaraan.
     */
    open fun getType(): String = "Kendaraan Umum"

    /**
     * Menghitung tarif perjalanan berdasarkan jarak.
     *
     * @param distanceKm jarak perjalanan dalam kilometer.
     * @return total tarif perjalanan.
     */
    open fun calculateFare(distanceKm: Double): Double {
        return 5000 + (distanceKm * 2000)
    }

    /**
     * Menampilkan informasi kendaraan ke console.
     */
    open fun displayInfo() {
        println("Nomor polisi : $plateNumber")
        println("Kendaraan : $brand $model ($year)")
        println("Jenis : ${getType()}")
        println("Tersedia : ${if (isAvailable) "Ya" else "Tidak"}")
    }
}
