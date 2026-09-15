// 9. Main.kt
package petshop

fun main() {
    val shop = PetShop("MeowWoof Pet Shop")

    println("\n=== 1. TAMBAH HEWAN ===")
    shop.tambahHewan(
        Kucing("H001", "Milo", 2, "Oren"),
        Anjing("H003", "Rex", 4, "German Shepherd"),
        Burung("H005", "Chirpy", 1, true) // Implementasi Latihan Mandiri
    )

    println("\n=== 2. DAFTAR PEMILIK ===")
    shop.daftarPemilik("P001", "Syaroful Haidar Sudaryono", "syaroful@mail.com", "081111")
    shop.displaySemuaHewan()

    println("\n=== 4. ADOPSI ===")
    shop.ajukanAdopsi("P001", "H001")

    // Simulasi penolakan batas adopsi (Latihan Mandiri)
    shop.ajukanAdopsi("P001", "H003")
    shop.ajukanAdopsi("P001", "H005") // Akan ditolak karena sudah 2

    shop.displayHewanTersedia()
    shop.displayLaporan()
}
