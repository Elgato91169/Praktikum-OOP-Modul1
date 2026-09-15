/** Kelas dasar untuk semua item yang dapat dipinjam dari perpustakaan. */
abstract class Item(
    /** Pengidentifikasi item yang unik. */ val id: String,
    /** Judul item. */ val title: String,
    /** Tahun terbit atau rilis. */ val year: Int
) {
    /** Menunjukkan apakah item saat ini dapat dipinjam. */
    var isAvailable: Boolean = true
        private set

    /** Menghitung denda untuk satu hari keterlambatan. */
    abstract fun calculateFinePerDay(): Double
    /** Mengembalikan kategori item. */
    abstract fun getItemType(): String
    /** Mengembalikan batas maksimum lama peminjaman dalam hari. */
    abstract fun getMaxBorrowDays(): Int

    /** Meminjam item ini jika tersedia. */
    fun borrow(): Boolean = if (isAvailable) {
        isAvailable = false
        println("Item '$title' berhasil dipinjam.")
        true
    } else {
        println("Item '$title' sedang tidak tersedia.")
        false
    }

    /** Mengembalikan item ini dan menghitung denda keterlambatannya. */
    fun returnItem(daysLate: Int = 0): Double {
        if (isAvailable) { println("Peringatan: item '$title' tidak sedang dipinjam."); return 0.0 }
        isAvailable = true
        val fine = daysLate.coerceAtLeast(0) * calculateFinePerDay()
        println("Item '$title' berhasil dikembalikan.")
        if (fine > 0) println("Total denda: ${formatRupiah(fine)}")
        return fine
    }

    /** Menampilkan informasi umum item. */
    open fun displayInfo() {
        println("ID: $id | Judul: $title | Tahun: $year")
        println("Jenis: ${getItemType()} | Status: ${if (isAvailable) "Tersedia" else "Dipinjam"}")
        println("Denda/hari: ${formatRupiah(calculateFinePerDay())} | Maks. pinjam: ${getMaxBorrowDays()} hari")
    }
}

/** Memformat nominal menjadi rupiah tanpa angka desimal. */
fun formatRupiah(amount: Double): String = "Rp %,d".format(amount.toLong()).replace(',', '.')
