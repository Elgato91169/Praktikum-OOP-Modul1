/** A registered library member. */
class Member(
    /** Unique member identifier. */ val id: String,
    /** Member name. */ val name: String,
    private val email: String,
    private val phone: String
) {
    private val transactions = mutableListOf<Transaction>()

    /** Total transactions made by this member. */ val transactionCount: Int get() = transactions.size
    /** Total fines from overdue transactions. */
    val totalFines: Double get() = transactions.sumOf { transaction ->
        (transaction.status as? TransactionStatus.Overdue)?.let { it.daysLate * transaction.item.calculateFinePerDay() } ?: 0.0
    }
    /** Number of items currently borrowed. */ val activeBorrows: Int get() = transactions.count { it.status is TransactionStatus.Borrowed }

    /** Returns the member email address. */ fun getEmail(): String = email
    /** Returns the member phone number. */ fun getPhone(): String = phone

    /** Borrows an available item if the member has fewer than three active loans. */
    fun borrowItem(item: Item): Transaction? {
        if (!item.isAvailable) { println("Item ${item.id} tidak tersedia."); return null }
        if (activeBorrows >= 3) { println("$name sudah mencapai batas 3 peminjaman aktif."); return null }
        if (!item.borrow()) return null
        val transaction = Transaction("TRX-${System.currentTimeMillis()}", item, this)
        transactions.add(transaction)
        println("$name berhasil meminjam '${item.title}'.")
        return transaction
    }

    /** Returns the member's active loan for an item. */
    fun returnItem(item: Item, daysLate: Int = 0): Double {
        val transaction = transactions.firstOrNull { it.item === item && it.status is TransactionStatus.Borrowed }
        if (transaction == null) { println("Tidak ada transaksi aktif untuk item ${item.id}."); return 0.0 }
        return transaction.returnItem(daysLate)
    }

    /** Returns an immutable snapshot of member transactions. */ fun getTransactions(): List<Transaction> = transactions.toList()
    /** Displays member information. */
    fun displayInfo() = println("ID: $id | Nama: $name | Email: $email | Telepon: $phone | Total Pinjam: $transactionCount | Aktif: $activeBorrows | Denda: ${formatRupiah(totalFines)}")
    /** Displays the member transaction history. */
    fun displayTransactions() {
        println("Riwayat transaksi $name:")
        if (transactions.isEmpty()) println("Belum ada transaksi.") else transactions.forEach { it.displayTransaction() }
    }
}
