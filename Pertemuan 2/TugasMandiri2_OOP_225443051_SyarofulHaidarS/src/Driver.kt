/**
 * Class yang merepresentasikan pengemudi beserta kendaraan yang digunakan.
 *
 * @property id ID pengemudi.
 * @property name nama pengemudi.
 * @property phone nomor telepon pengemudi.
 * @property vehicle kendaraan yang digunakan pengemudi.
 * @property isActive status keaktifan pengemudi.
 */
class Driver(
    val id: String,
    val name: String,
    val phone: String,
    val vehicle: Vehicle,
    var isActive: Boolean = true
) {
    /**
     * Menampilkan data pengemudi dan kendaraan.
     */
    fun displayInfo() {
        println("ID driver : $id")
        println("Nama driver : $name")
        println("Telepon : $phone")
        println("Status aktif : ${if (isActive) "Ya" else "Tidak"}")
        vehicle.displayInfo()
    }

    /**
     * Mengecek apakah pengemudi dapat menerima order.
     *
     * @return `true` jika driver aktif dan kendaraan tersedia.
     */
    fun acceptOrder(): Boolean {
        return isActive && vehicle.isAvailable
    }
}
