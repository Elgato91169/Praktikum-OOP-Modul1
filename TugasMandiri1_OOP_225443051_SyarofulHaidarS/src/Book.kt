/** A library book. */
class Book(id: String, title: String, year: Int, /** Book author. */ val author: String, /** Number of pages. */ val pages: Int, /** Book genre. */ val genre: String) : Item(id, title, year) {
    /** Returns the book late fine. */ override fun calculateFinePerDay() = 2000.0
    /** Returns this item's category. */ override fun getItemType() = "Buku"
    /** Returns the book borrowing limit. */ override fun getMaxBorrowDays() = 14
    /** Displays book information. */ override fun displayInfo() { super.displayInfo(); println("Penulis: $author | Halaman: $pages | Genre: $genre") }
}
