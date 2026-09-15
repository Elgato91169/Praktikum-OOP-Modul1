import java.util.Locale

/**
 * Mengubah nominal angka menjadi teks rupiah tanpa angka pecahan.
 */
fun formatRupiah(amount: Double): String = String.format(Locale.forLanguageTag("id-ID"), "Rp%,.0f", amount)
