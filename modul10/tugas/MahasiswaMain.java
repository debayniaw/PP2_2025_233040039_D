package id.ac.unpas.Tugasmodul10;

import id.ac.unpas.Tugasmodul10.controller.MahasiswaController;
import id.ac.unpas.Tugasmodul10.model.MahasiswaRepository;
import id.ac.unpas.Tugasmodul10.view.MahasiswaView;

import javax.swing.SwingUtilities;

public class MahasiswaMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaView view = new MahasiswaView();
            MahasiswaRepository repository = new MahasiswaRepository();
            new MahasiswaController(view, repository);
            view.setVisible(true);
        });
    }
}
