package id.ac.unpas.Tugasmodul10.model;

import com.mysql.cj.jdbc.Driver;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    private static Connection mysqlconfig;

    public static Connection configDB() {
        try {
            if (mysqlconfig != null && !mysqlconfig.isClosed()) {
                return mysqlconfig;
            }

            String url = "jdbc:mysql://localhost:3306/kampus_db";
            String user = "root";
            String pass = "";

            DriverManager.registerDriver(new Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + e.getMessage());
        }

        return mysqlconfig;
    }
}
