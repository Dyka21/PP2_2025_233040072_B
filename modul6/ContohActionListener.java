package modul6;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ContohActionListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("contoh action listener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("HALO KLIK TOMBOL DIBAWAH INI");
        JButton button = new JButton("klik saya");

        ActionListener listener = new ActionListener() {
        @Override
    public void actionPerformed(ActionEvent e) {
           label.setText("tombol telah dilik");
    }
          };

          button.addActionListener(listener);

          frame.add(label);
          frame.add(button);
          frame.setVisible(true);
    }
}
