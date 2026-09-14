import java.time.LocalDate

/** A borrowing transaction connecting a member and an item. */
class Transaction(
    /** Unique transaction identifier. */ val id: String,
    /** Borrowed item. */ val item: Item,
    /** Member who borrows the item. */ val member: Member,
    /** Date on which the item was borrowed. */ val borrowDate: String = LocalDate.now().toString(),
    /** Current transaction state. */ var status: TransactionStatus = TransactionStatus.Borrowed
) {
    /** Returns the item and records its return status. */
    fun returnItem(daysLate: Int): Double {
        if (status.isFinal()) { println("Transaksi $id sudah selesai dan tidak dapat diubah."); return 0.0 }
        val lateDays = daysLate.coerceAtLeast(0)
        val fine = item.returnItem(lateDays)
        status = if (lateDays > 0) TransactionStatus.Overdue(lateDays) else TransactionStatus.Returned
        return fine
    }
    /** Cancels this transaction and makes the item available again. */
    fun cancel() { if (status.isFinal()) println("Transaksi $id sudah selesai dan tidak dapat dibatalkan.") else { status = TransactionStatus.Cancelled; item.returnItem() } }
    /** Displays transaction details. */
    fun displayTransaction() = println("$id | ${item.title} (${item.id}) | ${member.name} | $borrowDate | ${status.display()}")
}
