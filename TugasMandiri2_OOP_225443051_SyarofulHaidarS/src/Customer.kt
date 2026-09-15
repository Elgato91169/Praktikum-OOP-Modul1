/**
 * Merepresentasikan pelanggan yang menggunakan layanan transportasi.
 */
class Customer(
    /** ID unik pelanggan. */
    val id: String,
    /** Nama pelanggan. */
    val name: String,
    /** Nomor telepon pelanggan. */
    val phone: String,
    /** Alamat email pelanggan. */
    val email: String,
    /** Saldo yang dimiliki pelanggan. */
    var balance: Double = 0.0
) {
    /** Menampilkan data pelanggan ke konsol. */
    fun displayInfo() {
        println("ID pelanggan : $id")
        println("Nama         : $name")
        println("Telepon      : $phone")
        println("Email        : $email")
        println("Saldo        : ${formatRupiah(balance)}")
    }

    /** Menambahkan saldo pelanggan apabila nominalnya positif. */
    fun topUp(amount: Double) {
        require(amount > 0) { "Nominal top up harus lebih dari nol." }
        balance += amount
    }

    /** Memeriksa apakah saldo pelanggan mencukupi untuk nominal tertentu. */
    fun canPay(amount: Double): Boolean = balance >= amount
}
