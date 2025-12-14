/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pratikumpemograman2.modul8.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */
public class PersegiPanjangView extends JFrame  {
    // Komponen UI
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilLuas = new JLabel("-");
    private JLabel lblHasilKeliling = new JLabel("-"); // Tambahan Label Keliling
    private JButton btnHitung = new JButton("Hitung");
    private JButton btnReset = new JButton("Reset"); // Tambahan Tombol Reset
    
    public PersegiPanjangView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        // Ubah grid menjadi 5 baris, 2 kolom
        this.setLayout(new GridLayout(5, 2, 10, 10)); 
        this.setTitle("MVC Kalkulator");
        
        // Baris 1
        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);
        
        // Baris 2
        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);
        
        // Baris 3
        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasilLuas);
        
        // Baris 4 (Tambahan)
        this.add(new JLabel("Hasil Keliling:"));
        this.add(lblHasilKeliling);
        
        // Baris 5 (Tombol)
        this.add(btnHitung);
        this.add(btnReset);
    }
    
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }
    
    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }
    
    public void setHasilLuas(double hasil) {
        lblHasilLuas.setText(String.valueOf(hasil));
    }
    
    // Tambahan setter untuk label keliling
    public void setHasilKeliling(double hasil) {
        lblHasilKeliling.setText(String.valueOf(hasil));
    }
    
    // Tambahan method untuk membersihkan inputan
    public void clearInput() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilLuas.setText("-");
        lblHasilKeliling.setText("-");
        txtPanjang.requestFocus(); // Fokuskan kursor kembali ke kolom panjang
    }
    
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }
    
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }
    
    // Tambahan listener untuk tombol reset
    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}
