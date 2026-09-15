/**
 * Class kendaraan mobil yang merupakan turunan dari [Vehicle].
 *
 * @property fuelType jenis bahan bakar yang digunakan.
 * @property numberOfDoors jumlah pintu mobil.
 */
class Car(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    val fuelType: String,
    val numberOfDoors: Int
) : Vehicle(plateNumber, brand, model, year) {

    /**
     * Mengembalikan jenis kendaraan sebagai mobil.
     *
     * @return "Mobil".
     */
    override fun getType(): String = "Mobil"

    /**
     * Menghitung tarif khusus kendaraan mobil.
     *
     * @param distanceKm jarak perjalanan dalam kilometer.
     * @return total tarif mobil.
     */
    override fun calculateFare(distanceKm: Double): Double {
        return 8000 + (distanceKm * 2500)
    }

    /**
     * Menampilkan informasi dasar kendaraan dan informasi khusus mobil.
     */
    override fun displayInfo() {
        super.displayInfo()
        println("Bahan bakar : $fuelType")
        println("Jumlah pintu : $numberOfDoors")
    }
}
