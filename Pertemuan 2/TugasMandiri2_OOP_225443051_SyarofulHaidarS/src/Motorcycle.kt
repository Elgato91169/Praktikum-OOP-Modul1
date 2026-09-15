/**
 * Class kendaraan motor yang merupakan turunan dari [Vehicle].
 *
 * @property engineCapacity kapasitas mesin dalam cc.
 * @property hasHelmet menunjukkan ketersediaan helm.
 */
class Motorcycle(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    val engineCapacity: Int,
    val hasHelmet: Boolean
) : Vehicle(plateNumber, brand, model, year) {

    /**
     * Mengembalikan jenis kendaraan sebagai motor.
     *
     * @return "Motor".
     */
    override fun getType(): String = "Motor"

    /**
     * Menghitung tarif khusus kendaraan motor.
     *
     * @param distanceKm jarak perjalanan dalam kilometer.
     * @return total tarif motor.
     */
    override fun calculateFare(distanceKm: Double): Double {
        return 3000 + (distanceKm * 1500)
    }

    /**
     * Menampilkan informasi dasar kendaraan dan informasi khusus motor.
     */
    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas : $engineCapacity cc")
        println("Helm : ${if (hasHelmet) "Tersedia" else "Tidak ada"}")
    }
}
