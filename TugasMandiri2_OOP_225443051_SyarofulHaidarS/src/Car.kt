/**
 * Kendaraan jenis mobil dengan informasi bahan bakar dan jumlah pintu.
 */
class Car(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    /** Jenis bahan bakar yang digunakan mobil. */
    val fuelType: String,
    /** Jumlah pintu pada mobil. */
    val numberOfDoors: Int
) : Vehicle(plateNumber, brand, model, year) {
    /** Mengembalikan jenis kendaraan sebagai mobil. */
    override fun getType(): String = "Mobil"

    /** Menghitung tarif mobil berdasarkan jarak tempuh. */
    override fun calculateFare(distanceKm: Double): Double = 8000.0 + (distanceKm * 2500.0)

    /** Menampilkan informasi mobil beserta properti khususnya. */
    override fun displayInfo() {
        super.displayInfo()
        println("Bahan bakar  : $fuelType")
        println("Jumlah pintu : $numberOfDoors")
    }
}
