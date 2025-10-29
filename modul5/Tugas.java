
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * @author wagus
 * Modifikasi Latihan 4 untuk menyelesaikan Tugas
 */
public class Tugas { // Nama kelas gua ganti biar beda

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout - Tugas Selesai"); // Judul diubah
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new BorderLayout());

                // 2. Buat SEMUA komponen sebagai variabel
                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton buttonSouth = new JButton("Tombol ada di Bawah (SOUTH)"); //  Ganti nama biar jelas
                
                // --- BAGIAN TAMBAHAN UNTUK TUGAS ---
                // Buat tombol WEST, EAST, CENTER sebagai variabel
                JButton buttonWest = new JButton("WEST");
                JButton buttonEast = new JButton("EAST");
                JButton buttonCenter = new JButton("CENTER");
                // -----------------------------------

                // 3. Tambahkan Aksi (ActionListener) ke semua tombol
                
                // Aksi untuk tombol SOUTH (dari Latihan 4)
                buttonSouth.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!"); 
                });

                // --- BAGIAN TAMBAHAN UNTUK TUGAS --- 
                // Tambahkan aksi untuk tombol WEST
                buttonWest.addActionListener(e -> {
                    label.setText("Tombol WEST diklik!");
                });
                
                // Tambahkan aksi untuk tombol EAST
                buttonEast.addActionListener(e -> {
                    label.setText("Tombol EAST diklik!");
                });
                
                // Tambahkan aksi untuk tombol CENTER
                buttonCenter.addActionListener(e -> {
                    label.setText("Tombol CENTER diklik!");
                });
                // -----------------------------------

                // 4. Tambahkan komponen ke frame DENGAN POSISI
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH); //  Pakai variabel baru

                // --- BAGIAN INI DIUBAH DARI LATIHAN 4 --- 
                // Gunakan variabel, bukan 'new JButton' lagi
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);
                // ---------------------------------------

                frame.setVisible(true);
            }
        });
    }
}