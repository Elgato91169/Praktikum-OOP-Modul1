//  Manajemen Gaji Karyawan

class Karyawan(
    val nama: String,
    var gajiPokok: Double
) {
    // Method untuk menampilkan informasi nama dan nominal gaji
    fun tampilkanGaji() {
        val formatRupiah = String.format("Rp %,d", gajiPokok.toInt())
        println("Nama: $nama, Gaji: $formatRupiah")
    }

    // Method untuk menaikkan gaji
    fun naikGaji(persen: Double) {
        if (persen > 0) {
            val nominalKenaikan = gajiPokok * (persen / 100.0)
            gajiPokok += nominalKenaikan
            val formatKenaikan = String.format("Rp %,d", nominalKenaikan.toInt())
            println(">> Kenaikan gaji sebesar $persen% ($formatKenaikan) berhasil diterapkan.")
        } else {
            println(">> Persentase kenaikan harus bernilai positif!")
        }
    }
}

fun main() {
    println(" Manajemen Gaji Karyawan ")

    // Membuat 1 karyawan dengan gaji awal 5.000.000
    val karyawan1 = Karyawan("Ahmad Fauzi", 5_000_000.0)

    // Gaji Awal
    print("[Gaji Awal]  -> ")
    karyawan1.tampilkanGaji()

    // Kenaikan Gaji
    println("\nMemproses kenaikan gaji 15%...")
    karyawan1.naikGaji(15.0)

    // Gaji Akhir
    print("\n[Gaji Akhir] -> ")
    karyawan1.tampilkanGaji()
}
