// Persegi Panjang (Interaksi Properti)

class PersegiPanjang(
    var panjang: Double,
    var lebar: Double
) {
    // Method untuk menghitung luas persegi panjang (panjang * lebar)
    fun hitungLuas(): Double {
        return panjang * lebar
    }

    // Method untuk mengubah ukuran panjang dan lebar objek
    fun ubahUkuran(panjangBaru: Double, lebarBaru: Double) {
        panjang = panjangBaru
        lebar = lebarBaru
    }

    // Method isSquare
    fun isSquare(): Boolean {
        return panjang == lebar
    }
}

fun main() {
    // 1. Buat objek dengan panjang 10.0 dan lebar 5.0
    val pp = PersegiPanjang(10.0, 5.0)

    // 2. Tampilkan luas awal
    println("Luas awal: ${pp.hitungLuas()}")

    // 3. Panggil method ubahUkuran(20.0, 10.0)
    pp.ubahUkuran(20.0, 10.0)

    // 4. Tampilkan luas baru
    println("Luas baru: ${pp.hitungLuas()}")

    // 5. Method isSquare()
    println("Apakah persegi panjang berbentuk persegi? ${pp.isSquare()}")
}
