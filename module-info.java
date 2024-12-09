module dormapp {
    // Menyatakan ketergantungan pada modul lain jika ada
    requires java.sql; // Jika Anda menggunakan JDBC untuk koneksi database
    requires java.base; // Secara default, semua modul memerlukan java.base

    // Menyatakan paket yang diekspos oleh modul ini
    exports dormapp.entities; // Ekspor paket entitas
    exports dormapp.config; // Ekspor paket konfigurasi
    exports dormapp.repositories; // Ekspor paket repositori
    exports dormapp.service; // Ekspor paket layanan
    exports dormapp.views; // Ekspor paket tampilan
}