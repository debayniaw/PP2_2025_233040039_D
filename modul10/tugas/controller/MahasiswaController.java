package id.ac.unpas.Tugasmodul10.controller;

import id.ac.unpas.Tugasmodul10.model.Mahasiswa;
import id.ac.unpas.Tugasmodul10.model.MahasiswaRepository;
import id.ac.unpas.Tugasmodul10.view.MahasiswaView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MahasiswaController {

    private final MahasiswaView view;
    private final MahasiswaRepository repository;

    public MahasiswaController(MahasiswaView view, MahasiswaRepository repository) {
        this.view = view;
        this.repository = repository;

        this.view.onSimpan(e -> tambahData());
        this.view.onEdit(e -> ubahData());
        this.view.onHapus(e -> hapusData());
        this.view.onClear(e -> this.view.clearForm());
        this.view.onCari(e -> cariData());

        this.view.onTableClick(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = MahasiswaController.this.view.getSelectedRowIndex();
                if (row < 0) {
                    return;
                }

                String nama = MahasiswaController.this.view.getTableValueAt(row, 1).toString();
                String nim = MahasiswaController.this.view.getTableValueAt(row, 2).toString();
                String jurusan = MahasiswaController.this.view.getTableValueAt(row, 3).toString();
                MahasiswaController.this.view.setFormFields(nama, nim, jurusan);
            }
        });

        loadData();
    }

    private void loadData() {
        try {
            view.setTableData(repository.findAll());
        } catch (Exception e) {
            view.showMessage("Gagal Load Data: " + e.getMessage());
        }
    }

    private void tambahData() {
        if (view.getNamaInput().isEmpty() || view.getNimInput().isEmpty()) {
            view.showMessage("Data tidak boleh kosong!");
            return;
        }

        try {
            if (repository.existsByNim(view.getNimInput())) {
                view.showMessage("NIM sudah terdaftar!");
                return;
            }

            Mahasiswa mahasiswa = new Mahasiswa(view.getNamaInput(), view.getNimInput(), view.getJurusanInput());
            repository.insert(mahasiswa);
            view.showMessage("Data Berhasil Disimpan");
            loadData();
            view.clearForm();
        } catch (Exception e) {
            view.showMessage("Gagal Simpan: " + e.getMessage());
        }
    }

    private void ubahData() {
        if (view.getNamaInput().isEmpty() || view.getNimInput().isEmpty()) {
            view.showMessage("Data tidak boleh kosong!");
            return;
        }

        try {
            Mahasiswa mahasiswa = new Mahasiswa(view.getNamaInput(), view.getNimInput(), view.getJurusanInput());
            repository.updateByNim(mahasiswa);
            view.showMessage("Data Berhasil Diubah");
            loadData();
            view.clearForm();
        } catch (Exception e) {
            view.showMessage("Gagal Edit: " + e.getMessage());
        }
    }

    private void hapusData() {
        if (view.getNimInput().isEmpty()) {
            view.showMessage("NIM tidak boleh kosong!");
            return;
        }

        try {
            repository.deleteByNim(view.getNimInput());
            view.showMessage("Data Berhasil Dihapus");
            loadData();
            view.clearForm();
        } catch (Exception e) {
            view.showMessage("Gagal Hapus: " + e.getMessage());
        }
    }

    private void cariData() {
        try {
            String keyword = view.getKeywordInput();
            if (keyword == null || keyword.isBlank()) {
                loadData();
                return;
            }

            view.setTableData(repository.searchByNama(keyword));
        } catch (Exception e) {
            view.showMessage("Gagal Cari Data: " + e.getMessage());
        }
    }
}
