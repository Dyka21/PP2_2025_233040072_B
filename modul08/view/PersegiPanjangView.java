package modul08.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilLuas = new JLabel("-");
    private JLabel lblHasilKeliling = new JLabel("-"); // [TUGAS 2] Label untuk output keliling
    private JButton btnHitung = new JButton("Hitung luas");
    private JButton btnReset = new JButton("Reset");   // [TUGAS 3] Tombol Reset

    public PersegiPanjangView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 300);
        this.setTitle("MVC Kalkulator");
        this.setLayout(new GridLayout(6, 2, 10, 10)); 

        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);
        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);
        
        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasilLuas);
        
        // TUGAS 2 Menambahkan komponen output keliling ke layar
        this.add(new JLabel("Hasil Keliling:"));
        this.add(lblHasilKeliling);
        
        this.add(btnHitung);
        this.add(btnReset); // TUGAS 3 Menambahkan tombol reset ke layar
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

    // TUGAS 2 Method untuk menampilkan angka keliling ke label
    public void setHasilKeliling(double hasil) {
        lblHasilKeliling.setText(String.valueOf(hasil));
    }

    // TUGAS 3 Method untuk menghapus/mereset semua inputan dan output
    public void clearInput() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilLuas.setText("-");
        lblHasilKeliling.setText("-");
        txtPanjang.requestFocus(); 
    }

    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }
    
    // TUGAS 3 Method pendaftaran listener khusus tombol Reset
    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}