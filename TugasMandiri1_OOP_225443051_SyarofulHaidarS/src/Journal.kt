/** Jurnal yang tersedia di perpustakaan. */
class Journal(
    id: String,
    title: String,
    year: Int,
    /** Penerbit jurnal. */ val publisher: String,
    /** Volume jurnal. */ val volume: Int,
    /** Nomor edisi jurnal. */ val issueNumber: Int
) : Item(id, title, year) {
    /** Mengembalikan denda keterlambatan jurnal. */
    override fun calculateFinePerDay() = 3000.0

    /** Mengembalikan kategori item ini. */
    override fun getItemType() = "Jurnal"

    /** Mengembalikan batas waktu peminjaman jurnal. */
    override fun getMaxBorrowDays() = 7

    /** Menampilkan informasi jurnal. */
    override fun displayInfo() {
        super.displayInfo()
        println("Penerbit: $publisher | Volume: $volume | Edisi: $issueNumber")
    }
}
