/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pratikumpemograman2.modul9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author ASUS
 */
public class AplikasiFileIO extends JFrame {
     // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText, btnAppendText; 
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;
    
    public AplikasiFileIO() {
        // LATIHAN 1: Setup UI Dasar 
        super("Tutorial File IO & Exception Handling");
        setSize(700, 500);
        
        // --- UPDATE PENTING UNTUK LATIHAN 2 ---
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        
        setLocationRelativeTo(null);
        
        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();
        
        // --- TAMBAHAN KODE: Event saat tombol X ditekan ---
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Simpan otomatis ke last_notes.txt sebelum keluar
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("last_notes.txt"))) {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // Tutup aplikasi
                System.exit(0);
            }
        });
        
        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        
        // Tombol Latihan 1
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        
        // Tombol Latihan 4
        btnAppendText = new JButton("Tambah Text (Append)"); 
        
        // Tombol Latihan 3
        btnSaveBinary = new JButton("Simpan Config (Objek)");
        btnLoadBinary = new JButton("Muat Config (Objek)");
        
        // Menambahkan tombol ke panel
        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText); 
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        
        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event Handling
        btnOpenText.addActionListener(e -> bukaFileText());
        btnSaveText.addActionListener(e -> simpanFileTeks());
        btnAppendText.addActionListener(e -> tambahFileTeks());
        btnSaveBinary.addActionListener(e -> simpanConfigObjek()); 
        btnLoadBinary.addActionListener(e -> muatConfigObjek());   
   
        // LATIHAN 2: Auto-read file saat start
        bacaFileOtomatis();
    }
    
    //LATIHAN 2: Membaca file otomatis saat aplikasi dibuka
    private void bacaFileOtomatis() {
        File autoFile = new File("last_notes.txt");
        
        // Jika file tidak ada, diam saja (return)
        if (!autoFile.exists()) {
            return; 
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(autoFile))) {
            String line;
            textArea.setText(""); 
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            // Silent catch: diam saja jika error
        }
    }
    
    // LATIHAN 4: Fitur Append (Menambah text di bawah)
    private void tambahFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write("\n" + textArea.getText()); 
                JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan (Append)!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan text: " + ex.getMessage());
            }
        }
    }
    
    // LATIHAN 1: Fitur Buka File Text (Membaca)
    private void bukaFileText() {
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                JOptionPane.showMessageDialog(this, "File berhasil dimuat");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } 
        }
    }
    
    // LATIHAN 1: Fitur Simpan File Text 
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }   
        }
    }
    
    // LATIHAN 3: Menyimpan Objek Utuh (Serialization)
    private void simpanConfigObjek() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("config.cfg"))) {
            UserConfig config = new UserConfig();
            config.setFontsize(textArea.getFont().getSize()); 
            config.setUsername("MahasiswaIT"); 
            
            oos.writeObject(config);
            JOptionPane.showMessageDialog(this, "Objek UserConfig disimpan!\nFont Size: " + config.getFontsize());
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
        }
    }
    
    // --- LATIHAN 3: Membaca Objek Utuh (Deserialization)
    private void muatConfigObjek() {
        File configFile = new File("config.cfg");
        if (!configFile.exists()) {
             JOptionPane.showMessageDialog(this, "File config.cfg belum ada. Simpan dulu!");
             return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(configFile))) {
            UserConfig config = (UserConfig) ois.readObject();
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontsize()));
            JOptionPane.showMessageDialog(this, "Konfigurasi dimuat!\nUsername: " + config.getUsername() + "\nFont diubah ke: " + config.getFontsize());
        } catch (IOException | ClassNotFoundException ex) {
             JOptionPane.showMessageDialog(this, "Gagal membaca objek: " + ex.getMessage());
        }
    }
    
    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}
