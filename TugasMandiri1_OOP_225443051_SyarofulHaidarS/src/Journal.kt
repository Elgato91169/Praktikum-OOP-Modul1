/** A library journal. */
class Journal(id: String, title: String, year: Int, /** Journal publisher. */ val publisher: String, /** Journal volume. */ val volume: Int, /** Journal issue number. */ val issueNumber: Int) : Item(id, title, year) {
    /** Returns the journal late fine. */ override fun calculateFinePerDay() = 3000.0
    /** Returns this item's category. */ override fun getItemType() = "Jurnal"
    /** Returns the journal borrowing limit. */ override fun getMaxBorrowDays() = 7
    /** Displays journal information. */ override fun displayInfo() { super.displayInfo(); println("Penerbit: $publisher | Volume: $volume | Edisi: $issueNumber") }
}
