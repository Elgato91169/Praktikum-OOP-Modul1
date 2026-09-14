/** Demonstrates the Digital Library Management System. */
fun main() {
    val library = Library("Perpustakaan Kampus")
    val book1 = Book("B001", "Pemrograman Kotlin", 2023, "Budi Santoso", 350, "Programming")
    val book2 = Book("B002", "Dasar-Dasar OOP", 2022, "Siti Rahayu", 280, "Education")
    val journal1 = Journal("J001", "Jurnal Teknologi Informasi", 2023, "ITB", 15, 2)
    val journal2 = Journal("J002", "Jurnal Pendidikan", 2022, "UGM", 10, 1)
    val dvd1 = DVD("D001", "Inception", 2010, "Christopher Nolan", 148, "Sci-Fi")
    val dvd2 = DVD("D002", "The Matrix", 1999, "Wachowski", 136, "Action")

    println("=== INISIALISASI ${library.name} ===")
    library.addItems(book1, book2, journal1, journal2, dvd1, dvd2)
    library.registerMember("M001", "Ahmad Fauzi", "ahmad@email.com", "08123456789")
    library.registerMember("M002", "Dewi Lestari", "dewi@email.com", "08129876543")
    library.registerMember("M003", "Rizky Pratama", "rizky@email.com", "08125678901")
    library.displayAllItems()

    println("=== PEMINJAMAN ===")
    library.borrowItem("M001", "B001"); library.borrowItem("M001", "D001")
    library.borrowItem("M002", "J001"); library.borrowItem("M003", "B002")
    library.displayAvailableItems()
    library.findMember("M001")?.displayTransactions(); library.findMember("M002")?.displayTransactions()

    println("\n=== PENGEMBALIAN ===")
    library.returnItem("M001", "B001"); library.returnItem("M002", "J001", 3)
    library.findMember("M001")?.displayTransactions(); library.findMember("M002")?.displayTransactions()

    println("\n=== POLIMORFISME ===")
    val polymorphicItems: List<Item> = listOf(book1, journal2, dvd2)
    polymorphicItems.forEach { println("${it.getItemType()} - Denda/hari: ${formatRupiah(it.calculateFinePerDay())}") }

    println("\n=== SEALED CLASS ===")
    val statuses: List<TransactionStatus> = listOf(TransactionStatus.Borrowed, TransactionStatus.Returned, TransactionStatus.Overdue(5), TransactionStatus.Cancelled)
    statuses.forEach { status -> when (status) {
        TransactionStatus.Borrowed -> println(status.display())
        TransactionStatus.Returned -> println(status.display())
        is TransactionStatus.Overdue -> println(status.display())
        TransactionStatus.Cancelled -> println(status.display())
    } }

    println("\n=== SMART CASTING ===")
    val selectedItem: Item = book1
    when (selectedItem) { is Book -> println("Item ${selectedItem.id} adalah Buku"); is Journal -> println("Item ${selectedItem.id} adalah Jurnal"); is DVD -> println("Item ${selectedItem.id} adalah DVD") }
    println("Casting B001 ke DVD: ${selectedItem as? DVD ?: "null (bukan DVD)"}")
    println("\n=== ENKAPSULASI ===\n'isAvailable' tidak dapat diubah langsung karena private set. Email anggota juga private dan diakses melalui getEmail().")
    library.displayReport()
}
