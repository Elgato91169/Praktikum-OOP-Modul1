/** DVD yang tersedia di perpustakaan. */
class DVD(
    id: String,
    title: String,
    year: Int,
    /** Sutradara film. */ val director: String,
    /** Durasi film dalam menit. */ val duration: Int,
    /** Genre DVD. */ val genre: String
) : Item(id, title, year) {
    /** Mengembalikan denda keterlambatan DVD. */
    override fun calculateFinePerDay() = 5000.0

    /** Mengembalikan kategori item ini. */
    override fun getItemType() = "DVD"

    /** Mengembalikan batas waktu peminjaman DVD. */
    override fun getMaxBorrowDays() = 3

    /** Menampilkan informasi DVD. */
    override fun displayInfo() {
        super.displayInfo()
        println("Sutradara: $director | Durasi: $duration menit | Genre: $genre")
    }
}
