package modul08.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modul08.model.PersegiPanjangModel;
import modul08.view.PersegiPanjangView;

public class PersegiPanjangController {
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;
        
        // Pasang listener ke view
        this.view.addHitungListener(new HitungListener());
        
        // TUGAS 3 Menghubungkan tombol reset di View dengan logic di Controller
        this.view.addResetListener(new ResetListener());
    }

    // Inner class buat tombol Hitung
    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                double p = view.getPanjang();
                double l = view.getLebar();

                
                model.setPanjang(p);
                model.setLebar(l);
                
                model.hitungLuas();     
                model.hitungKeliling(); // TUGAS 2 Memanggil logika hitung keliling di Model

                
                view.setHasilLuas(model.getLuas());
                view.setHasilKeliling(model.getKeliling()); // [TUGAS 2] Update tampilan keliling

            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Input angka yang bener dong Bang!");
            }
        }
    }
    
    //TUGAS 3 Inner class khusus menangani klik tombol Reset
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearInput();
        }
    }
}