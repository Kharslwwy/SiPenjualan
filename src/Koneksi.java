import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3307/db_jual"; 
    private static final String USER = "root";  
    private static final String PASS = "";     

    public Connection openKoneksi() {
        Connection con = null;
        try {
            // Gunakan driver versi 5.x
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Koneksi Berhasil");
        } catch (SQLException se) {
            System.out.println("Koneksi gagal: " + se.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
        }
        return con;
    }
}
