/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pratikumpemograman2.Modul5;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */
public class Latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Jendela dengan Label");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // 1. Buat komponen JLabel
                JLabel label = new JLabel("Ini adalah JLabel");
                
                // 2. Tambahkan JLabel ke JFrame
                // Secara default, JFrame menggunakan BorerLayout,
                // dan .add() akan menambahkannya ke bagian tengah (CENTER).
                frame.add(label);
                
                frame.setVisible(true);
            }
        });
    }
}
