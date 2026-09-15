/**
 * Class kendaraan truk yang merupakan turunan dari [Vehicle].
 *
 * @property loadCapacity kapasitas muatan dalam ton.
 * @property numberOfAxles jumlah sumbu roda.
 */
class Truck(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    val loadCapacity: Double,
    val numberOfAxles: Int
) : Vehicle(plateNumber, brand, model, year) {

    /**
     * Mengembalikan jenis kendaraan sebagai truk.
     *
     * @return "Truk".
     */
    override fun getType(): String = "Truk"

    /**
     * Menghitung tarif khusus kendaraan truk.
     *
     * @param distanceKm jarak perjalanan dalam kilometer.
     * @return total tarif truk.
     */
    override fun calculateFare(distanceKm: Double): Double {
        return 10000 + (distanceKm * 3500)
    }

    /**
     * Menampilkan informasi dasar kendaraan dan informasi khusus truk.
     */
    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas : $loadCapacity ton")
        println("Jumlah sumbu : $numberOfAxles")
    }
}
