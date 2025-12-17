package id.ac.unpas.modul10.controller; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import id.ac.unpas.modul10.KoneksiDB;
import id.ac.unpas.modul10.model.Mahasiswa;

public class MahasiswaController {

    public List<Mahasiswa> loadMahasiswa() {
        List<Mahasiswa> list = new ArrayList<>();
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
            while (res.next()) {
                Mahasiswa m = new Mahasiswa();
                m.setNama(res.getString("nama"));
                m.setNim(res.getString("nim"));
                m.setJurusan(res.getString("jurusan"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String insertMahasiswa(Mahasiswa m) {
        try {
            Connection conn = KoneksiDB.configDB();
            PreparedStatement cek = conn.prepareStatement("SELECT * FROM mahasiswa WHERE nim = ?");
            cek.setString(1, m.getNim());
            if (cek.executeQuery().next()) return "Gagal: NIM sudah ada!";

            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, m.getNama());
            pst.setString(2, m.getNim());
            pst.setString(3, m.getJurusan());
            pst.execute();
            return "Berhasil disimpan";
        } catch (Exception e) {
            return "Gagal simpan: " + e.getMessage();
        }
    }

    public String updateMahasiswa(Mahasiswa m) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "UPDATE mahasiswa SET nama=?, jurusan=? WHERE nim=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, m.getNama());
            pst.setString(2, m.getJurusan());
            pst.setString(3, m.getNim()); 
            if (pst.executeUpdate() > 0) {
                return "Berhasil diupdate";
            } else {
                return "NIM tidak ditemukan";
            }
        } catch (Exception e) {
            return "Gagal update: " + e.getMessage();
        }
    }

    public String deleteMahasiswa(String nim) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "DELETE FROM mahasiswa WHERE nim=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            if (pst.executeUpdate() > 0) {
                return "Berhasil dihapus";
            } else {
                return "Gagal, NIM tidak ada";
            }
        } catch (Exception e) {
            return "Gagal hapus: " + e.getMessage();
        }
    }
}