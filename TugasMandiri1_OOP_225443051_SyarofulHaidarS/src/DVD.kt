/** A library DVD. */
class DVD(id: String, title: String, year: Int, /** Film director. */ val director: String, /** Duration in minutes. */ val duration: Int, /** DVD genre. */ val genre: String) : Item(id, title, year) {
    /** Returns the DVD late fine. */ override fun calculateFinePerDay() = 5000.0
    /** Returns this item's category. */ override fun getItemType() = "DVD"
    /** Returns the DVD borrowing limit. */ override fun getMaxBorrowDays() = 3
    /** Displays DVD information. */ override fun displayInfo() { super.displayInfo(); println("Sutradara: $director | Durasi: $duration menit | Genre: $genre") }
}
