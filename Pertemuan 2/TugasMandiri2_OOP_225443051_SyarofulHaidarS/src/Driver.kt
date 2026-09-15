/**
 * Merepresentasikan pengemudi yang terdaftar pada sistem.
 */
class Driver(
    /** ID unik pengemudi. */
    val id: String,
    /** Nama pengemudi. */
    val name: String,
    /** Nomor telepon pengemudi. */
    val phone: String,
    /** Kendaraan yang digunakan oleh pengemudi. */
    val vehicle: Vehicle,
    /** Menandakan bahwa pengemudi aktif menerima pesanan. */
    var isActive: Boolean = true
) {
    /** Menampilkan data pengemudi dan kendaraan yang digunakannya. */
    fun displayInfo() {
        println("ID driver    : $id")
        println("Nama driver  : $name")
        println("Telepon      : $phone")
        println("Status aktif : ${if (isActive) "Ya" else "Tidak"}")
        vehicle.displayInfo()
    }

    /** Memeriksa apakah pengemudi dapat menerima pesanan baru. */
    fun acceptOrder(): Boolean = isActive && vehicle.isAvailable
}
