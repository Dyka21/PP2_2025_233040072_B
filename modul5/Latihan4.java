import java.awt.BorderLayout; // <-- Import untuk BorderLayout
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * @author wagus
 */
public class Latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // 1. Atur Layout Manager ke BorderLayout [cite: 258]
                // Sebenarnya ini tidak perlu
                // Karena BorderLayout adalah Layout Manager default [cite: 258]
                frame.setLayout(new BorderLayout());

                // 2. Buat komponen [cite: 258]
                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton button = new JButton("Tombol ada di Bawah (SOUTH)");

                // 3. Tambahkan Aksi (ActionListener) ke tombol [cite: 258]
                button.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });

                // 4. Tambahkan komponen ke frame DENGAN POSISI [cite: 258]
                // Kita harus tentukan areanya (NORTH, SOUTH, dll)
                frame.add(label, BorderLayout.NORTH); 
                frame.add(button, BorderLayout.SOUTH); 

                // Kita bisa tambahkan komponen lain [cite: 265]
                frame.add(new JButton("WEST"), BorderLayout.WEST); 
                frame.add(new JButton("EAST"), BorderLayout.EAST); 
                frame.add(new JButton("CENTER"), BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}
