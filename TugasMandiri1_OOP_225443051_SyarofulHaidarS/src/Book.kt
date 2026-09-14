/** Buku yang tersedia di perpustakaan. */
class Book(
    id: String,
    title: String,
    year: Int,
    /** Penulis buku. */ val author: String,
    /** Jumlah halaman buku. */ val pages: Int,
    /** Genre buku. */ val genre: String
) : Item(id, title, year) {
    /** Mengembalikan denda keterlambatan buku. */
    override fun calculateFinePerDay() = 2000.0

    /** Mengembalikan kategori item ini. */
    override fun getItemType() = "Buku"

    /** Mengembalikan batas waktu peminjaman buku. */
    override fun getMaxBorrowDays() = 14

    /** Menampilkan informasi buku. */
    override fun displayInfo() {
        super.displayInfo()
        println("Penulis: $author | Halaman: $pages | Genre: $genre")
    }
}
