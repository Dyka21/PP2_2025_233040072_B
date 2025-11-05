package modul6;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ContohFlowLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("contoh flowlayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.add(new JButton("Tombol 1"));
        panel.add(new JButton("Tombol 2"));
        panel.add(new JButton("Tombol Tiga"));
        panel.add(new JButton("Tombol Empat Panjang"));
        panel.add(new JButton("Tombol 5"));

        frame.add(panel);

        frame.setVisible(true);
    }
}
