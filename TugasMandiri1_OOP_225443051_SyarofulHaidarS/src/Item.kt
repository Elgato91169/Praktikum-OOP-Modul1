/** Base abstraction for every item that can be borrowed from the library. */
abstract class Item(
    /** Unique identifier of the item. */ val id: String,
    /** Item title. */ val title: String,
    /** Publication or release year. */ val year: Int
) {
    /** Whether the item can currently be borrowed. */
    var isAvailable: Boolean = true
        private set

    /** Calculates the fine charged for one late day. */
    abstract fun calculateFinePerDay(): Double
    /** Returns the item category. */
    abstract fun getItemType(): String
    /** Returns the maximum permitted borrowing duration in days. */
    abstract fun getMaxBorrowDays(): Int

    /** Borrows this item when it is available. */
    fun borrow(): Boolean = if (isAvailable) {
        isAvailable = false
        println("Item '$title' berhasil dipinjam.")
        true
    } else {
        println("Item '$title' sedang tidak tersedia.")
        false
    }

    /** Returns this item and calculates its late fine. */
    fun returnItem(daysLate: Int = 0): Double {
        if (isAvailable) { println("Peringatan: item '$title' tidak sedang dipinjam."); return 0.0 }
        isAvailable = true
        val fine = daysLate.coerceAtLeast(0) * calculateFinePerDay()
        println("Item '$title' berhasil dikembalikan.")
        if (fine > 0) println("Total denda: ${formatRupiah(fine)}")
        return fine
    }

    /** Displays common item information. */
    open fun displayInfo() {
        println("ID: $id | Judul: $title | Tahun: $year")
        println("Jenis: ${getItemType()} | Status: ${if (isAvailable) "Tersedia" else "Dipinjam"}")
        println("Denda/hari: ${formatRupiah(calculateFinePerDay())} | Maks. pinjam: ${getMaxBorrowDays()} hari")
    }
}

/** Formats an amount as Indonesian rupiah without decimals. */
fun formatRupiah(amount: Double): String = "Rp %,d".format(amount.toLong()).replace(',', '.')
