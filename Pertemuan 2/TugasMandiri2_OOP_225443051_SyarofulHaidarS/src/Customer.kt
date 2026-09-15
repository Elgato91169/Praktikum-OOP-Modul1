/**
 * Class yang merepresentasikan pelanggan.
 *
 * @property id ID pelanggan.
 * @property name nama pelanggan.
 * @property phone nomor telepon pelanggan.
 * @property email email pelanggan.
 * @property balance saldo pelanggan.
 */
class Customer(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    var balance: Double = 0.0
) {
    /**
     * Menampilkan data pelanggan.
     */
    fun displayInfo() {
        println("ID pelanggan : $id")
        println("Nama : $name")
        println("Telepon : $phone")
        println("Email : $email")
        println("Saldo : ${formatRupiah(balance)}")
    }

    /**
     * Menambah saldo pelanggan.
     *
     * @param amount jumlah saldo yang ditambahkan.
     */
    fun topUp(amount: Double) {
        require(amount > 0) { "Nominal top up harus lebih dari 0." }
        balance += amount
    }

    /**
     * Mengecek apakah saldo pelanggan mencukupi.
     *
     * @param amount nominal yang akan dibayar.
     * @return `true` jika saldo mencukupi.
     */
    fun canPay(amount: Double): Boolean {
        return balance >= amount
    }
}

/**
 * Mengubah nominal menjadi format rupiah sederhana.
 *
 * @param amount nominal yang akan diformat.
 * @return string nominal dalam format rupiah.
 */
fun formatRupiah(amount: Double): String {
    return "Rp%,d".format(amount.toLong()).replace(',', '.')
}
