// Data Mahasiswa Interaktif (Input User)

// Class MahasiswaInteraktif dibuat mandiri agar tidak bentrok dengan deklarasi di file lain
// serta memiliki konstruktor dan metode yang mendukung input dinamis secara interaktif
class MahasiswaInteraktif(
    val nim: String,
    val nama: String,
    val jurusan: String,
    var ipk: Double
) {
    // Blok init untuk validasi rentang IPK (0.0 - 4.0)
    init {
        if (ipk > 4.0) ipk = 4.0
        if (ipk < 0.0) ipk = 0.0
    }

    // Method untuk menentukan predikat berdasarkan IPK
    fun predikat(): String {
        return when {
            ipk >= 3.5 -> "Cumlaude"
            ipk >= 3.0 -> "Sangat Memuaskan"
            ipk >= 2.5 -> "Memuaskan"
            else -> "Perlu Perbaikan"
        }
    }

    // Method untuk menampilkan data mahasiswa
    fun tampilkan() {
        println("=================================")
        println("          DATA MAHASISWA          ")
        println("=================================")
        println("NIM      : $nim")
        println("Nama     : $nama")
        println("Jurusan  : $jurusan")
        println("IPK      : ${String.format("%.2f", ipk)}")
        println("Predikat : ${predikat()}")
        println("=================================")
    }
}

fun main() {
    println("   INPUT DATA MAHASISWA    ")

    val daftarMahasiswa = mutableListOf<MahasiswaInteraktif>()

    //  While untuk input beberapa mahasiswa
    var lanjut = true
    var nomor = 1

    while (lanjut) {
        println("\n--- Masukkan Data Mahasiswa ke-$nomor ---")

        print("Masukkan NIM     : ")
        val nim = readln().trim()

        print("Masukkan Nama    : ")
        val nama = readln().trim()

        print("Masukkan Jurusan : ")
        val jurusan = readln().trim()

        var ipk = 0.0
        while (true) {
            print("Masukkan IPK     : ")
            val inputIpk = readln().trim()
            val parsedIpk = inputIpk.toDoubleOrNull()
            if (parsedIpk != null) {
                ipk = parsedIpk
                break
            } else {
                println("Input IPK tidak valid! Masukkan angka desimal (contoh: 3.75).")
            }
        }

        // Instansiasi objek MahasiswaInteraktif
        val mhs = MahasiswaInteraktif(nim, nama, jurusan, ipk)
        daftarMahasiswa.add(mhs)

        print("\nTambah data mahasiswa lagi? (y/n): ")
        val jawaban = readln().trim().lowercase()
        if (jawaban != "y" && jawaban != "ya") {
            lanjut = false
        } else {
            nomor++
        }
    }

    // Menampilkan seluruh data mahasiswa yang telah diinput
    println("\n\n========================================")
    println("    DAFTAR DATA MAHASISWA       ")
    println("========================================")
    for (mhs in daftarMahasiswa) {
        mhs.tampilkan()
        println()
    }
}
