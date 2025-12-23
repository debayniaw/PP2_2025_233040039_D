package id.ac.unpas.Tugasmodul10.view;

import id.ac.unpas.Tugasmodul10.model.Mahasiswa;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

public class MahasiswaView extends JFrame {

    private final JTextField txtNama;
    private final JTextField txtNIM;
    private final JTextField txtJurusan;
    private final JTextField txtCari;

    private final JButton btnSimpan;
    private final JButton btnEdit;
    private final JButton btnHapus;
    private final JButton btnClear;
    private final JButton btnCari;

    private final JTable tableMahasiswa;
    private final DefaultTableModel model;

    public MahasiswaView() {
        setTitle("Aplikasi CRUD Mahasiswa JDBC (MVC)");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        txtCari = new JTextField(10);
        btnCari = new JButton("Cari");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);
        panelTombol.add(new JLabel("Nama:"));
        panelTombol.add(txtCari);
        panelTombol.add(btnCari);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.NORTH);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);
    }

    public String getNamaInput() {
        return txtNama.getText();
    }

    public String getNimInput() {
        return txtNIM.getText();
    }

    public String getJurusanInput() {
        return txtJurusan.getText();
    }

    public String getKeywordInput() {
        return txtCari.getText();
    }

    public void setFormFields(String nama, String nim, String jurusan) {
        txtNama.setText(nama);
        txtNIM.setText(nim);
        txtJurusan.setText(jurusan);
    }

    public void clearForm() {
        txtNama.setText(null);
        txtNIM.setText(null);
        txtJurusan.setText(null);
    }

    public void setTableData(List<Mahasiswa> data) {
        model.setRowCount(0);
        int no = 1;
        for (Mahasiswa m : data) {
            model.addRow(new Object[] {
                    no++,
                    m.getNama(),
                    m.getNim(),
                    m.getJurusan()
            });
        }
    }

    public int getSelectedRowIndex() {
        return tableMahasiswa.getSelectedRow();
    }

    public Object getTableValueAt(int row, int col) {
        return model.getValueAt(row, col);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void onSimpan(ActionListener listener) {
        btnSimpan.addActionListener(listener);
    }

    public void onEdit(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void onHapus(ActionListener listener) {
        btnHapus.addActionListener(listener);
    }

    public void onClear(ActionListener listener) {
        btnClear.addActionListener(listener);
    }

    public void onCari(ActionListener listener) {
        btnCari.addActionListener(listener);
    }

    public void onTableClick(MouseListener listener) {
        tableMahasiswa.addMouseListener(listener);
    }
}
