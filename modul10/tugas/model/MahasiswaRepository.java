package id.ac.unpas.Tugasmodul10.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaRepository {

    public List<Mahasiswa> findAll() throws SQLException {
        List<Mahasiswa> result = new ArrayList<>();
        Connection conn = KoneksiDB.configDB();
        if (conn == null) {
            return result;
        }

        try (Statement stm = conn.createStatement();
                ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa")) {
            while (res.next()) {
                result.add(new Mahasiswa(
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")));
            }
        }

        return result;
    }

    public boolean existsByNim(String nim) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        if (conn == null) {
            return false;
        }

        String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nim);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public void insert(Mahasiswa mahasiswa) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        if (conn == null) {
            throw new SQLException("Koneksi database null");
        }

        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, mahasiswa.getNama());
            pst.setString(2, mahasiswa.getNim());
            pst.setString(3, mahasiswa.getJurusan());
            pst.executeUpdate();
        }
    }

    public int updateByNim(Mahasiswa mahasiswa) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        if (conn == null) {
            throw new SQLException("Koneksi database null");
        }

        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, mahasiswa.getNama());
            pst.setString(2, mahasiswa.getJurusan());
            pst.setString(3, mahasiswa.getNim());
            return pst.executeUpdate();
        }
    }

    public int deleteByNim(String nim) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        if (conn == null) {
            throw new SQLException("Koneksi database null");
        }

        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nim);
            return pst.executeUpdate();
        }
    }

    public List<Mahasiswa> searchByNama(String keyword) throws SQLException {
        List<Mahasiswa> result = new ArrayList<>();
        Connection conn = KoneksiDB.configDB();
        if (conn == null) {
            return result;
        }

        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + keyword + "%");
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    result.add(new Mahasiswa(
                            res.getString("nama"),
                            res.getString("nim"),
                            res.getString("jurusan")));
                }
            }
        }

        return result;
    }
}
