package modul08.model;

public class PersegiPanjangModel {
    private double panjang;
    private double lebar;
    private double luas;
    private double keliling; // TUGAS 2 Nambah variabel buat simpan keliling

    public void hitungLuas() {
        this.luas = this.panjang * this.lebar;
    }

    // TUGAS 2 Method logika bisnis untuk menghitung keliling
    public void hitungKeliling() {
        this.keliling = 2 * (this.panjang + this.lebar);
    }

    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }
    
    public double getLuas() {
        return luas;
    }

    // TUGAS 2 Getter untuk mengambil nilai keliling dari controller
    public double getKeliling() {
        return keliling;
    }
}