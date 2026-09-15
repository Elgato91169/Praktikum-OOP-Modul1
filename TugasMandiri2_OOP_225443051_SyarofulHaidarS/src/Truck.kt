/**
 * Kendaraan jenis truk untuk mengangkut barang.
 */
class Truck(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    /** Kapasitas muatan maksimal dalam ton. */
    val loadCapacity: Double,
    /** Jumlah sumbu roda pada truk. */
    val numberOfAxles: Int
) : Vehicle(plateNumber, brand, model, year) {
    /** Mengembalikan jenis kendaraan sebagai truk. */
    override fun getType(): String = "Truk"

    /** Menghitung tarif truk berdasarkan jarak tempuh. */
    override fun calculateFare(distanceKm: Double): Double = 10000.0 + (distanceKm * 3500.0)

    /** Menampilkan informasi truk beserta properti khususnya. */
    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas    : $loadCapacity ton")
        println("Jumlah sumbu : $numberOfAxles")
    }
}
