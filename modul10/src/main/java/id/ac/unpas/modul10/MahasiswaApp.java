package id.ac.unpas.modul10; // Pastikan package sesuai folder lu

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

public class MahasiswaApp extends JFrame {
    
    // --- Komponen GUI ---
    private JTextField txtNama, txtNIM, txtJurusan, txtCari;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    private JTable tableMahasiswa;
    private DefaultTableModel model;

    public MahasiswaApp() {
        // [LATIHAN 1] Setup Frame Dasar
        setTitle("Aplikasi Mahasiswa (Latihan 1-4)");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Panel Form Input ---
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

        // --- Panel Tombol CRUD ---
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        
        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        // --- [LATIHAN 3] Panel Pencarian ---
        JPanel panelCari = new JPanel(new FlowLayout());
        txtCari = new JTextField(20);
        btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari Nama:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        // Gabungin Panel Form, Tombol, dan Cari di atas
        JPanel panelAtas = new JPanel(new BorderLayout());
        JPanel panelInput = new JPanel(new BorderLayout());
        panelInput.add(panelForm, BorderLayout.CENTER);
        panelInput.add(panelTombol, BorderLayout.SOUTH);
        
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelCari, BorderLayout.SOUTH);
        
        add(panelAtas, BorderLayout.NORTH);

        // --- Tabel Data ---
        model = new DefaultTableModel(new String[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        tableMahasiswa = new JTable(model);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);

        // --- EVENT LISTENER (Aksi Tombol & Mouse) ---
        
        // Klik Tabel (biar data masuk ke form buat diedit)
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
            }
        });

        // Aksi Tombol-Tombol
        btnSimpan.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> kosongkanForm());
        
        // [LATIHAN 3] Tombol Cari
        btnCari.addActionListener(e -> loadData(txtCari.getText()));

        // Load data awal pas aplikasi dibuka
        loadData("");
    }

    // --- LOGIKA DATABASE ---

    // 1. READ Data (Gabungan Latihan 1 & 3)
    private void loadData(String keyword) {
        model.setRowCount(0);
        String sql = "SELECT * FROM mahasiswa";
        
        // [LATIHAN 3] Logika Pencarian
        if (!keyword.isEmpty()) {
            sql += " WHERE nama LIKE ?";
        }

        try {
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            if (!keyword.isEmpty()) {
                pst.setString(1, "%" + keyword + "%");
            }
            
            ResultSet res = pst.executeQuery();
            int no = 1;
            while (res.next()) {
                model.addRow(new Object[]{
                    no++, 
                    res.getString("nama"), 
                    res.getString("nim"), 
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Load: " + e.getMessage());
        }
    }

    // 2. CREATE Data (Gabungan Latihan 1, 2, & 4)
    private void tambahData() {
        // [LATIHAN 2] Validasi Input Kosong
        if (txtNama.getText().trim().isEmpty() || txtNIM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan NIM tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // [LATIHAN 4] Cek NIM Duplikat
        if (isNimExist(txtNIM.getText())) {
            JOptionPane.showMessageDialog(this, "NIM Sudah Terdaftar!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtNIM.getText());
            pst.setString(3, txtJurusan.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(this, "Berhasil Simpan");
            loadData("");
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }

    // [LATIHAN 4] Helper Cek NIM
    private boolean isNimExist(String nim) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                return res.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. UPDATE Data (Latihan 1)
    private void ubahData() {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtJurusan.getText());
            pst.setString(3, txtNIM.getText()); // Kunci Update berdasarkan NIM
            
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(this, "Berhasil Edit");
                loadData("");
                kosongkanForm();
            } else {
                JOptionPane.showMessageDialog(this, "NIM tidak ditemukan!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }

    // 4. DELETE Data (Latihan 1)
    private void hapusData() {
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = KoneksiDB.configDB();
                String sql = "DELETE FROM mahasiswa WHERE nim = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, txtNIM.getText());
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Berhasil Hapus");
                loadData("");
                kosongkanForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
            }
        }
    }

    private void kosongkanForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
        txtCari.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}