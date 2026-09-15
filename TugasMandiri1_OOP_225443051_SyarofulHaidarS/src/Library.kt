/** Mengelola item, anggota, dan transaksi perpustakaan. */
class Library(
    /** Nama perpustakaan ini. */ val name: String
) {
    private val items = mutableListOf<Item>()
    private val members = mutableListOf<Member>()
    private val transactions = mutableListOf<Transaction>()

    /** Jumlah seluruh item yang terdaftar. */
    val totalItems: Int get() = items.size

    /** Jumlah item yang tersedia. */
    val availableItems: Int get() = items.count { it.isAvailable }

    /** Jumlah seluruh anggota yang terdaftar. */
    val totalMembers: Int get() = members.size

    /** Jumlah seluruh transaksi peminjaman. */
    val totalTransactions: Int get() = transactions.size

    /** Menambahkan item ke koleksi. */
    fun addItem(item: Item) {
        items.add(item)
        println("Item ${item.id} ditambahkan.")
    }

    /** Menambahkan beberapa item ke koleksi. */
    fun addItems(vararg newItems: Item) = newItems.forEach(::addItem)

    /** Mencari item berdasarkan ID. */
    fun findItem(id: String): Item? = items.find { it.id.equals(id, true) }

    /** Mencari item berdasarkan kata kunci judul atau ID. */
    fun searchItems(keyword: String): List<Item> = items.filter {
        it.id.contains(keyword, true) || it.title.contains(keyword, true)
    }

    /** Mendaftarkan anggota jika ID-nya belum digunakan. */
    fun registerMember(id: String, name: String, email: String, phone: String): Boolean {
        if (findMember(id) != null) {
            println("ID anggota $id sudah digunakan.")
            return false
        }
        members.add(Member(id, name, email, phone))
        println("Anggota $name berhasil didaftarkan.")
        return true
    }

    /** Mencari anggota berdasarkan ID. */
    fun findMember(id: String): Member? = members.find { it.id.equals(id, true) }

    /** Membuat transaksi peminjaman untuk anggota dan item. */
    fun borrowItem(memberId: String, itemId: String): Transaction? {
        val member = findMember(memberId) ?: run { println("Anggota $memberId tidak ditemukan."); return null }
        val item = findItem(itemId) ?: run { println("Item $itemId tidak ditemukan."); return null }
        return member.borrowItem(item)?.also { transactions.add(it) }
    }
    /** Mengembalikan item atas nama anggota. */
    fun returnItem(memberId: String, itemId: String, daysLate: Int = 0): Double {
        val member = findMember(memberId) ?: run { println("Anggota $memberId tidak ditemukan."); return 0.0 }
        val item = findItem(itemId) ?: run { println("Item $itemId tidak ditemukan."); return 0.0 }
        return member.returnItem(item, daysLate)
    }

    /** Menampilkan informasi lengkap semua item. */
    fun displayAllItems() {
        println("\n=== SEMUA ITEM ($totalItems, tersedia $availableItems) ===")
        items.forEach {
            it.displayInfo()
            println()
        }
    }

    /** Menampilkan daftar singkat item yang tersedia. */
    fun displayAvailableItems() {
        println("\n=== ITEM TERSEDIA ===")
        items.filter { it.isAvailable }.forEach {
            println("${it.id} - ${it.title} (${it.getItemType()})")
        }
    }

    /** Menampilkan informasi semua anggota. */
    fun displayAllMembers() {
        println("\n=== SEMUA ANGGOTA ===")
        members.forEach { it.displayInfo() }
    }

    /** Menampilkan semua transaksi perpustakaan. */
    fun displayAllTransactions() {
        println("\n=== SEMUA TRANSAKSI ===")
        transactions.forEach { it.displayTransaction() }
    }

    /** Menampilkan laporan ringkasan perpustakaan ini. */
    fun displayReport() {
        val totalFines = members.sumOf { it.totalFines }
        println("\n=== LAPORAN $name ===\nTotal item: $totalItems\nTersedia: $availableItems\nDipinjam: ${totalItems - availableItems}\nTotal anggota: $totalMembers\nTotal transaksi: $totalTransactions\nTotal denda: ${formatRupiah(totalFines)}")
    }
}
