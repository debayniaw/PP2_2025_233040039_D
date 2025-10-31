/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pratikumpemograman2.Modul5;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */
public class Latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               JFrame frame = new JFrame("Contoh BorderLayout");
               frame.setSize(600, 500);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
               // 1. Atur Layout Manager ke BorderLayout
               // Sebenarnya ini tidak perlu
               // Karena BorderLayout adalah Layout Manager default
               frame.setLayout(new BorderLayout ());
               
               // 2. Buat komponen
               JLabel label = new JLabel("Label ada di Atas (NORTH)");
               JButton button = new JButton("Tombol ada di Bawah (SOUTH)");
               
               // 3. Tambahkan Aksi (ActionListener) ke tombol
               button.addActionListener(e -> {
                   label.setText("Tombol di SOUTH diklik!");
               });
               
               // 4. Tambahkan komponen ke frame DENGAN POSISI
               frame.add(label, BorderLayout.NORTH);
               frame.add(button, BorderLayout.SOUTH);
               // Kita bisa tambahkan komponen lain
               frame.add(new JButton("WEST"), BorderLayout.WEST);
               frame.add(new JButton("EAST"), BorderLayout.EAST);
               frame.add(new JButton("CENTER"), BorderLayout.CENTER);
               
               frame.setVisible(true);
           } 
        });
    }
}
