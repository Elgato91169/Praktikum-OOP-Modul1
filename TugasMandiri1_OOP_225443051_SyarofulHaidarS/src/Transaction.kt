import java.time.LocalDate

/** Transaksi peminjaman yang menghubungkan anggota dan item. */
class Transaction(
    /** Pengidentifikasi transaksi yang unik. */ val id: String,
    /** Item yang dipinjam. */ val item: Item,
    /** Anggota yang meminjam item. */ val member: Member,
    /** Tanggal saat item dipinjam. */ val borrowDate: String = LocalDate.now().toString(),
    /** Status transaksi saat ini. */ var status: TransactionStatus = TransactionStatus.Borrowed
) {
    /** Mengembalikan item dan mencatat status pengembaliannya. */
    fun returnItem(daysLate: Int): Double {
        if (status.isFinal()) {
            println("Transaksi $id sudah selesai dan tidak dapat diubah.")
            return 0.0
        }
        val lateDays = daysLate.coerceAtLeast(0)
        val fine = item.returnItem(lateDays)
        status = if (lateDays > 0) TransactionStatus.Overdue(lateDays) else TransactionStatus.Returned
        return fine
    }

    /** Membatalkan transaksi ini dan membuat item tersedia kembali. */
    fun cancel() {
        if (status.isFinal()) {
            println("Transaksi $id sudah selesai dan tidak dapat dibatalkan.")
        } else {
            status = TransactionStatus.Cancelled
            item.returnItem()
        }
    }

    /** Menampilkan detail transaksi. */
    fun displayTransaction() = println("$id | ${item.title} (${item.id}) | ${member.name} | $borrowDate | ${status.display()}")
}
