/** Represents every possible lifecycle state of a transaction. */
sealed class TransactionStatus {
    /** Produces a human-readable status label. */ abstract fun display(): String
    /** True when this state can no longer change. */ fun isFinal(): Boolean = this is Returned || this is Cancelled
    /** Item is currently borrowed. */ object Borrowed : TransactionStatus() { override fun display() = "Dipinjam" }
    /** Item has been returned on time. */ object Returned : TransactionStatus() { override fun display() = "Dikembalikan" }
    /** Item was returned late. */ data class Overdue(/** Number of late days. */ val daysLate: Int) : TransactionStatus() { override fun display() = "Terlambat ($daysLate hari)" }
    /** Transaction was cancelled. */ object Cancelled : TransactionStatus() { override fun display() = "Dibatalkan" }
}
