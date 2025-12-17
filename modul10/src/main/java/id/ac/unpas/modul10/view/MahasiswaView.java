package id.ac.unpas.modul10.view; 

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import id.ac.unpas.modul10.controller.MahasiswaController;
import id.ac.unpas.modul10.model.Mahasiswa;

public class MahasiswaView extends JFrame {
    private JTextField txtNama, txtNIM, txtJurusan;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear;
    private JTable tableMahasiswa;
    private DefaultTableModel model;
    
    
    private MahasiswaController controller = new MahasiswaController();

    public MahasiswaView() {
        setTitle("Tugas Akhir MVC - Mahasiswa");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelForm.add(new JLabel("Nama:")); txtNama = new JTextField(); panelForm.add(txtNama);
        panelForm.add(new JLabel("NIM:")); txtNIM = new JTextField(); panelForm.add(txtNIM);
        panelForm.add(new JLabel("Jurusan:")); txtJurusan = new JTextField(); panelForm.add(txtJurusan);

        
        JPanel panelTombol = new JPanel();
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        panelTombol.add(btnSimpan); panelTombol.add(btnEdit); panelTombol.add(btnHapus); panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        
        model = new DefaultTableModel(new String[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        tableMahasiswa = new JTable(model);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);

        
        loadData(); 

        
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());

                
            }
        });

        
        btnSimpan.addActionListener(e -> {
            Mahasiswa m = new Mahasiswa(txtNama.getText(), txtNIM.getText(), txtJurusan.getText());
            String status = controller.insertMahasiswa(m);
            JOptionPane.showMessageDialog(this, status);
            if(status.contains("Berhasil")) { loadData(); clearForm(); }
        });

        // Tombol Edit
        btnEdit.addActionListener(e -> {
            Mahasiswa m = new Mahasiswa(txtNama.getText(), txtNIM.getText(), txtJurusan.getText());
            String status = controller.updateMahasiswa(m); 
            JOptionPane.showMessageDialog(this, status);
            if(status.contains("Berhasil")) { loadData(); clearForm(); }
        });

        // Tombol Hapus
        btnHapus.addActionListener(e -> {
            String status = controller.deleteMahasiswa(txtNIM.getText());
            JOptionPane.showMessageDialog(this, status);
            if(status.contains("Berhasil")) { loadData(); clearForm(); }
        });

        btnClear.addActionListener(e -> clearForm());
    }

   
    private void loadData() {
        model.setRowCount(0);
        List<Mahasiswa> list = controller.loadMahasiswa();
        int no = 1;
        for (Mahasiswa m : list) {
            model.addRow(new Object[]{no++, m.getNama(), m.getNim(), m.getJurusan()});
        }
    }

    private void clearForm() {
        txtNama.setText(""); txtNIM.setText(""); txtJurusan.setText("");
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MahasiswaView().setVisible(true));
    }
}