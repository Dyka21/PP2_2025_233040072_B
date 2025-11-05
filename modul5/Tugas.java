
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * @author wagus
 * Modifikasi Latihan 4 untuk menyelesaikan Tugas
 */
public class Tugas { 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout - Tugas Selesai"); 
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new BorderLayout());

                
                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton buttonSouth = new JButton("Tombol ada di Bawah (SOUTH)"); 
                
                
                JButton buttonWest = new JButton("WEST");
                JButton buttonEast = new JButton("EAST");
                JButton buttonCenter = new JButton("CENTER");
                

                buttonSouth.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!"); 
                });

                
                buttonWest.addActionListener(e -> {
                    label.setText("Tombol WEST diklik!");
                });
                
                
                buttonEast.addActionListener(e -> {
                    label.setText("Tombol EAST diklik!");
                });
                
                
                buttonCenter.addActionListener(e -> {
                    label.setText("Tombol CENTER diklik!");
                });
                // -----------------------------------

                
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH); 

                
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);
                // ---------------------------------------

                frame.setVisible(true);
            }
        });
    }
}