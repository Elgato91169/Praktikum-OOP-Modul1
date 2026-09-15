/**
 * Kelas dasar yang merepresentasikan kendaraan dalam sistem transportasi.
 */
open class Vehicle(
    /** Nomor polisi kendaraan. */
    val plateNumber: String,
    /** Merek kendaraan. */
    val brand: String,
    /** Model kendaraan. */
    val model: String,
    /** Tahun produksi kendaraan. */
    val year: Int,
    /** Menandakan apakah kendaraan sedang dapat menerima pesanan. */
    var isAvailable: Boolean = true
) {
    /** Mengembalikan jenis umum kendaraan. */
    open fun getType(): String = "Kendaraan Umum"

    /** Menghitung tarif kendaraan berdasarkan jarak tempuh dalam kilometer. */
    open fun calculateFare(distanceKm: Double): Double = 5000.0 + (distanceKm * 2000.0)

    /** Menampilkan informasi dasar kendaraan ke konsol. */
    open fun displayInfo() {
        println("Nomor polisi : $plateNumber")
        println("Kendaraan    : $brand $model ($year)")
        println("Jenis        : ${getType()}")
        println("Tersedia     : ${if (isAvailable) "Ya" else "Tidak"}")
    }
}
