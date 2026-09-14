/** Manages items, members, and library transactions. */
class Library(/** Name of this library. */ val name: String) {
    private val items = mutableListOf<Item>()
    private val members = mutableListOf<Member>()
    private val transactions = mutableListOf<Transaction>()

    /** Total registered items. */ val totalItems: Int get() = items.size
    /** Number of available items. */ val availableItems: Int get() = items.count { it.isAvailable }
    /** Total registered members. */ val totalMembers: Int get() = members.size
    /** Total borrowing transactions. */ val totalTransactions: Int get() = transactions.size

    /** Adds an item to the collection. */ fun addItem(item: Item) { items.add(item); println("Item ${item.id} ditambahkan.") }
    /** Adds multiple items to the collection. */ fun addItems(vararg newItems: Item) = newItems.forEach(::addItem)
    /** Finds an item by its ID. */ fun findItem(id: String): Item? = items.find { it.id.equals(id, true) }
    /** Searches items by a title or ID keyword. */ fun searchItems(keyword: String): List<Item> = items.filter { it.id.contains(keyword, true) || it.title.contains(keyword, true) }

    /** Registers a member when their ID is unused. */
    fun registerMember(id: String, name: String, email: String, phone: String): Boolean {
        if (findMember(id) != null) { println("ID anggota $id sudah digunakan."); return false }
        members.add(Member(id, name, email, phone)); println("Anggota $name berhasil didaftarkan."); return true
    }
    /** Finds a member by ID. */ fun findMember(id: String): Member? = members.find { it.id.equals(id, true) }

    /** Creates a borrowing transaction for a member and item. */
    fun borrowItem(memberId: String, itemId: String): Transaction? {
        val member = findMember(memberId) ?: run { println("Anggota $memberId tidak ditemukan."); return null }
        val item = findItem(itemId) ?: run { println("Item $itemId tidak ditemukan."); return null }
        return member.borrowItem(item)?.also { transactions.add(it) }
    }
    /** Returns an item on behalf of a member. */
    fun returnItem(memberId: String, itemId: String, daysLate: Int = 0): Double {
        val member = findMember(memberId) ?: run { println("Anggota $memberId tidak ditemukan."); return 0.0 }
        val item = findItem(itemId) ?: run { println("Item $itemId tidak ditemukan."); return 0.0 }
        return member.returnItem(item, daysLate)
    }

    /** Displays detailed information for all items. */
    fun displayAllItems() { println("\n=== SEMUA ITEM ($totalItems, tersedia $availableItems) ==="); items.forEach { it.displayInfo(); println() } }
    /** Displays a concise list of available items. */
    fun displayAvailableItems() { println("\n=== ITEM TERSEDIA ==="); items.filter { it.isAvailable }.forEach { println("${it.id} - ${it.title} (${it.getItemType()})") } }
    /** Displays all member information. */
    fun displayAllMembers() { println("\n=== SEMUA ANGGOTA ==="); members.forEach { it.displayInfo() } }
    /** Displays all library transactions. */
    fun displayAllTransactions() { println("\n=== SEMUA TRANSAKSI ==="); transactions.forEach { it.displayTransaction() } }
    /** Displays a summary report for this library. */
    fun displayReport() {
        val totalFines = members.sumOf { it.totalFines }
        println("\n=== LAPORAN $name ===\nTotal item: $totalItems\nTersedia: $availableItems\nDipinjam: ${totalItems - availableItems}\nTotal anggota: $totalMembers\nTotal transaksi: $totalTransactions\nTotal denda: ${formatRupiah(totalFines)}")
    }
}
