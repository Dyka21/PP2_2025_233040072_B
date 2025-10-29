
import java.awt.FlowLayout; // <-- Import untuk FlowLayout
import javax.swing.JButton; // <-- Import untuk JButton
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Label dan Tombol");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // 1. Atur Layout Manager ke FlowLayout 
                // Biar komponen tersusun kiri-ke-kanan 
                frame.setLayout(new FlowLayout());

              
                JLabel label = new JLabel("AMBA"); 
                JButton button = new JButton("Klik Saya!"); 

                
                button.addActionListener(e -> {
                    
                    label.setText("Tombol berhasil diklik!"); 
                });

                frame.add(label);
                frame.add(button); 

                frame.setVisible(true); // [cite: 205]
            }
        });
    }
}