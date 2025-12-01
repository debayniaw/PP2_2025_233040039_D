/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author TUF
 */
public class Tugas extends JFrame {
     
    
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

// Method untuk membuat desain Tab Input
private JPanel createInputPanel() {
    JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Komponen Nama
    panel.add(new JLabel("Nama Siswa:"));
    txtNama = new JTextField();
    panel.add(txtNama);

    // Komponen Mata Pelajaran (ComboBox)
    panel.add(new JLabel("Mata Pelajaran:"));
    String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
                       "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
    cmbMatkul = new JComboBox<>(matkul);
    panel.add(cmbMatkul);

    // Komponen Nilai
    panel.add(new JLabel("Nilai (0-100):"));
    txtNilai = new JTextField();
    panel.add(txtNilai);

    // Tombol Simpan dan Reset
    JButton btnReset = new JButton("Reset Data");
    JButton btnSimpan = new JButton("Simpan Data");
   
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    buttonPanel.add(btnSimpan);
    buttonPanel.add(btnReset);
    
    panel.add(new JLabel("")); // Placeholder di kolom 1 baris 4
    panel.add(buttonPanel);    // ButtonPanel di kolom 2 baris 4
        
    // Event Handling Tombol Simpan
    btnSimpan.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            prosesSimpan();
        }
    });

    btnReset.addActionListener(e -> {
            txtNama.setText("");
            txtNilai.setText("");
            cmbMatkul.setSelectedIndex(0);
            
            JOptionPane.showMessageDialog(this, "Data Berhasil Direset!");

            tabbedPane.setSelectedIndex(0);

        });
    return panel;
}

// Method untuk membuat desain Tab Tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Setup Model Tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        // Membungkus tabel dengan ScrollPane
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Hapus (Tugas No. 2)
        JButton btnHapus = new JButton("Hapus Data");
        
        // Panel untuk menampung tombol Hapus, diletakkan di SOUTH
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(btnHapus);
        panel.add(southPanel, BorderLayout.SOUTH); 
        
        // Event Handling Tombol Hapus (Tugas No. 2)
        btnHapus.addActionListener(e -> {
            int selectedRow = tableData.getSelectedRow();
            
            if (selectedRow >= 0) { // Cek apakah ada baris yang dipilih
                // Hapus baris dari model tabel
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.", 
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus.", 
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        return panel;
    }

// Logika Validasi dan Penyimpanan Data
private void prosesSimpan() {
    // 1. Ambil data dari input
    String nama = txtNama.getText();
    String matkul = (String) cmbMatkul.getSelectedItem();
    String strNilai = txtNilai.getText();

    // 2. VALIDASI INPUT
        // Validasi 1: Cek apakah nama kosong
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!",
                                          "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validasi 1.5 (Tugas No. 3): Cek minimal 3 karakter
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama minimal terdiri dari 3 karakter!",
                                          "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

    // Validasi 2: Cek apakah nilai berupa angka dan dalam range valid
    int nilai;
    try {
        nilai = Integer.parseInt(strNilai);
        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!",
                                          "Error Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!",
                                      "Error Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
// 3. Logika Bisnis (Menentukan Grade)
    String grade;
    int nilaiDiskrit = nilai / 10; // Menghasilkan nilai 0-10

    switch (nilaiDiskrit) {
        case 8:
        case 9:
        case 10:
            grade = "A"; // Nilai 80-100
            break;
        case 7:
            grade = "AB"; // Nilai 70-79
            break;
        case 6:
            grade = "B"; // Nilai 60-69
            break;
        case 5:
            grade = "BC"; // Nilai 50-59
            break;
        case 4:
            grade = "C"; // Nilai 40-49
            break;
        case 3:
            grade = "D"; // Nilai 30-39
            break;
        default: // mencakup 0, 1, dan 2 (Nilai 0-29)
            grade = "E";
            break;
    }
    
    

    // 4. Masukkan ke Tabel (Update Model)
    Object[] dataBaris = {nama, matkul, nilai, grade};
    tableModel.addRow(dataBaris);

    // 5. Reset Form dan Pindah Tab
    txtNama.setText("");
    txtNilai.setText("");
    cmbMatkul.setSelectedIndex(0);

    JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
    
    tabbedPane.setSelectedIndex(1); // Otomatis pindah ke tab tabel
}

public Tugas() {
    // 1. Konfigurasi Frame Utama
    setTitle("Aplikasi Manajemen Nilai Siswa");
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Posisi di tengah layar

    // 2. Inisialisasi Tabbed Pane
    tabbedPane = new JTabbedPane();

    // 3. Membuat Panel untuk Tab 1 (Form Input)
    JPanel panelInput = createInputPanel();
    tabbedPane.addTab("Input Data", panelInput);

    // 4. Membuat Panel untuk Tab 2 (Tabel Data)
    JPanel panelTabel = createTablePanel();
    tabbedPane.addTab("Daftar Nilai", panelTabel);

    // Menambahkan TabbedPane ke Frame
    add(tabbedPane);
}
    
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new Tugas().setVisible(true);
    });
}
}