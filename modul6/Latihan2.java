package modul6;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Latihan2 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel labelCelcius = new JLabel("Celcius:");
        JTextField fieldCelcius = new JTextField();
        JLabel labelFahrenheit = new JLabel("Fahrenheit:");
        JLabel labelHasil = new JLabel("...");
        JButton buttonKonversi = new JButton("Konversi");

        buttonKonversi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = fieldCelcius.getText();
                    double celcius = Double.parseDouble(input);
                    double fahrenheit = (celcius * 9.0 / 5.0) + 32;
                    labelHasil.setText(String.format("%.2f", fahrenheit));
                } catch (NumberFormatException ex) {
                    labelHasil.setText("Input Angka!");
                }
            }
        });

        frame.add(labelCelcius);
        frame.add(fieldCelcius);
        frame.add(labelFahrenheit);
        frame.add(labelHasil);
        frame.add(buttonKonversi);

        frame.setVisible(true);
    }
}
