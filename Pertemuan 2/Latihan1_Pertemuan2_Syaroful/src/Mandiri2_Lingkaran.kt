// Perhitungan Lingkaran (Luas & Keliling)

class Lingkaran(var jariJari: Double) {

    // Method menghitung luas lingkaran PI * r^2
    fun hitungLuas(): Double {
        return Math.PI * jariJari * jariJari
    }

    // Method menghitung keliling lingkaran 2 * PI * r
    fun hitungKeliling(): Double {
        return 2 * Math.PI * jariJari
    }

    // Method menampilkan rincian
    fun tampilkanDetail(nomor: Int) {
        val luas = hitungLuas()
        val keliling = hitungKeliling()

        println("----------------------------------------")
        println("Lingkaran $nomor:")
        println("  Jari-jari : $jariJari cm")
        println("  Luas      : ${String.format("%.2f", luas)} cm²")
        println("  Keliling  : ${String.format("%.2f", keliling)} cm")
        println("----------------------------------------")
    }
}

fun main() {

    // Membuat 2 objek Lingkaran dengan jari-jari 7.0 dan 14.0
    val lingkaran1 = Lingkaran(7.0)
    val lingkaran2 = Lingkaran(14.0)

    // Tampilkan hasil perhitungan masing-masing lingkaran
    lingkaran1.tampilkanDetail(1)
    lingkaran2.tampilkanDetail(2)

    // Eksplorasi modifikasi nilai jariJari (karena properti bersifat 'var')
    println("\n[Ubah jari-jari Lingkaran 1 menjadi 10.0]")
    lingkaran1.jariJari = 10.0
    lingkaran1.tampilkanDetail(1)
}
