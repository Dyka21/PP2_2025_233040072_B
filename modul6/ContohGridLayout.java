package modul6;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ContohGridLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("contoh gridlayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        frame.setLayout(new GridLayout(3, 2, 5, 5));

        frame.add(new JLabel("Label 1"));
        frame.add(new JTextField());
        frame.add(new JLabel("label 2"));
        frame.add(new JPasswordField());
        frame.add(new JButton("login"));
        frame.add(new JButton("batal"));

        frame.setVisible(true);
    }
}
