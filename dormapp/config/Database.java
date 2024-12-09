package dormapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String dbName;
    private final String userName;
    private final String password;
    private final String host;
    private final String port;
    private Connection connection;

    // Konstruktor untuk menginisialisasi parameter koneksi
    public Database(final String dbName, final String userName, final String password, final String host, final String port) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    // Mendapatkan koneksi
    public Connection getConnection() {
        return connection;
    }

    // Metode untuk mengatur koneksi ke database
    public void setup() {
        String mysqlConnUrlTemplate = "jdbc:mysql://%s:%s/%s";
        try {
            // Memastikan driver JDBC dimuat
            Class.forName("com.mysql.cj.jdbc.Driver"); // Gunakan "com.mysql.cj.jdbc.Driver" untuk versi terbaru
            connection = DriverManager.getConnection(String.format(mysqlConnUrlTemplate, host, port, dbName), userName, password);
            System.out.println("Database connected!");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}