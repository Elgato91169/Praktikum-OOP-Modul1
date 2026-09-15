/**
 * Kendaraan jenis motor dengan kapasitas mesin dan informasi helm.
 */
class Motorcycle(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    /** Kapasitas mesin motor dalam satuan cc. */
    val engineCapacity: Int,
    /** Menandakan ketersediaan helm untuk penumpang. */
    val hasHelmet: Boolean
) : Vehicle(plateNumber, brand, model, year) {
    /** Mengembalikan jenis kendaraan sebagai motor. */
    override fun getType(): String = "Motor"

    /** Menghitung tarif motor berdasarkan jarak tempuh. */
    override fun calculateFare(distanceKm: Double): Double = 3000.0 + (distanceKm * 1500.0)

    /** Menampilkan informasi motor beserta properti khususnya. */
    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas    : $engineCapacity cc")
        println("Helm         : ${if (hasHelmet) "Tersedia" else "Tidak tersedia"}")
    }
}
