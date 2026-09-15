/**
 * Menjalankan demonstrasi pewarisan pada Soal 3.
 */
object MainSoal3 {
    /** Menjalankan contoh subclass kendaraan dan penggunaan super. */
    @JvmStatic
    fun main(args: Array<String>) {
        val car = Car("B 1234 XYZ", "Toyota", "Innova", 2021, "Bensin", 4)
        val motorcycle = Motorcycle("D 5678 ABC", "Honda", "Beat", 2022, 125, true)
        val truck = Truck("E 9012 DEF", "Hino", "Dutro", 2020, 5.0, 2)

        println("=== SOAL 3: PEWARISAN ===")
        println("\nData mobil")
        car.displayInfo()
        println("\nData motor")
        motorcycle.displayInfo()
        println("\nData truk")
        truck.displayInfo()

        println("\n=== TARIF JARAK 20 KM ===")
        listOf<Vehicle>(car, motorcycle, truck).forEach { vehicle ->
            println("${vehicle.getType()}: ${formatRupiah(vehicle.calculateFare(20.0))}")
        }

        val drivers = listOf(
            Driver("D001", "Andi", "08123456789", car),
            Driver("D002", "Budi", "08129876543", motorcycle),
            Driver("D003", "Citra", "08125678901", truck)
        )
        println("\n=== DATA PENGEMUDI ===")
        drivers.forEach {
            it.displayInfo()
            println()
        }
    }
}
