/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pratikumpemograman2.modul8;

import id.ac.unpas.pratikumpemograman2.Modul8.controller.PersegiPanjangController;
import id.ac.unpas.pratikumpemograman2.Modul8.model.PersegiPanjangModel;
import id.ac.unpas.pratikumpemograman2.Modul8.view.PersegiPanjangView;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        PersegiPanjangModel model =  new PersegiPanjangModel();
        
        PersegiPanjangView view = new PersegiPanjangView();
        
        PersegiPanjangController controller =  new PersegiPanjangController(model, view);
        
        view.setVisible(true);
    }
}
