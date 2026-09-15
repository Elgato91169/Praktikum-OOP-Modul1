/** Anggota perpustakaan yang sudah terdaftar. */
class Member(
    /** Pengidentifikasi anggota yang unik. */ val id: String,
    /** Nama anggota. */ val name: String,
    private val email: String,
    private val phone: String
) {
    private val transactions = mutableListOf<Transaction>()

    /** Jumlah seluruh transaksi yang dilakukan anggota ini. */
    val transactionCount: Int get() = transactions.size

    /** Total denda dari transaksi yang terlambat. */
    val totalFines: Double get() = transactions.sumOf { transaction ->
        (transaction.status as? TransactionStatus.Overdue)?.let { it.daysLate * transaction.item.calculateFinePerDay() } ?: 0.0
    }
    /** Jumlah item yang sedang dipinjam. */
    val activeBorrows: Int get() = transactions.count { it.status is TransactionStatus.Borrowed }

    /** Mengembalikan alamat email anggota. */
    fun getEmail(): String = email

    /** Mengembalikan nomor telepon anggota. */
    fun getPhone(): String = phone

    /** Meminjam item yang tersedia jika anggota memiliki kurang dari tiga pinjaman aktif. */
    fun borrowItem(item: Item): Transaction? {
        if (!item.isAvailable) { println("Item ${item.id} tidak tersedia."); return null }
        if (activeBorrows >= 3) { println("$name sudah mencapai batas 3 peminjaman aktif."); return null }
        if (!item.borrow()) return null
        val transaction = Transaction("TRX-${System.currentTimeMillis()}", item, this)
        transactions.add(transaction)
        println("$name berhasil meminjam '${item.title}'.")
        return transaction
    }

    /** Mengembalikan pinjaman aktif anggota untuk suatu item. */
    fun returnItem(item: Item, daysLate: Int = 0): Double {
        val transaction = transactions.firstOrNull { it.item === item && it.status is TransactionStatus.Borrowed }
        if (transaction == null) { println("Tidak ada transaksi aktif untuk item ${item.id}."); return 0.0 }
        return transaction.returnItem(daysLate)
    }

    /** Mengembalikan salinan transaksi anggota yang tidak dapat diubah. */
    fun getTransactions(): List<Transaction> = transactions.toList()

    /** Menampilkan informasi anggota. */
    fun displayInfo() = println("ID: $id | Nama: $name | Email: $email | Telepon: $phone | Total Pinjam: $transactionCount | Aktif: $activeBorrows | Denda: ${formatRupiah(totalFines)}")
    /** Menampilkan riwayat transaksi anggota. */
    fun displayTransactions() {
        println("Riwayat transaksi $name:")
        if (transactions.isEmpty()) println("Belum ada transaksi.") else transactions.forEach { it.displayTransaction() }
    }
}
