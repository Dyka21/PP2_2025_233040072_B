package modul09;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class AplikasifileIO extends JFrame {

    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JButton btnAppendText; // Latihan 4
    private JButton btnSaveObj, btnLoadObj; // Latihan 3
    private JFileChooser fileChooser;

    public AplikasifileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4)); 

        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnSaveBinary = new JButton("Simpan Config");
        btnLoadBinary = new JButton("Muat Config");
        
        // Tombol Tambahan
        btnAppendText = new JButton("Append ");
        btnSaveObj = new JButton("Simpan Obj ");
        btnLoadObj = new JButton("Muat Obj ");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        buttonPanel.add(btnAppendText);
        buttonPanel.add(btnSaveObj);
        buttonPanel.add(btnLoadObj);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        //  Event Handling 

        // Latihan 1 Text & Binary
        btnOpenText.addActionListener(e -> bukaFileTeks());
        btnSaveText.addActionListener(e -> simpanFileTeks());
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        btnLoadBinary.addActionListener(e -> muatConfigBinary());

        // Latihan 3 Object Stream
        btnSaveObj.addActionListener(e -> simpanUserConfig());
        btnLoadObj.addActionListener(e -> muatUserConfig());

        // Latihan 4 Append
        btnAppendText.addActionListener(e -> tambahFileTeks());

        // Latihan 2 Auto Load saat aplikasi jalan
        bukaLastNotes(); 
    }

    //  Latihan 1 Membaca File Teks Konvensional 
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; 
            try {
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); 
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            } finally {
                try {
                    if (reader != null) reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //  Latihan 1 Menulis File Teks (Try-with-resources) 
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    //  Latihan 1 Simpan Config Binary 
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);
            JOptionPane.showMessageDialog(this, "Font size disimpan: " + fontSize);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    //  Latihan 1: Muat Config Binary 
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            int fontSize = dis.readInt();
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font size dimuat: " + fontSize);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error/File belum ada: " + ex.getMessage());
        }
    }

    //  Latihan 2: Auto Read last_notes.txt 
    private void bukaLastNotes() {
        File file = new File("last_notes.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                textArea.setText("");
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException ex) {
                // Ignore error
            }
        }
    }

    //  Latihan 3 Simpan Objek 
    private void simpanUserConfig() {
        UserConfig config = new UserConfig();
        config.setUsername("MahasiswaUNPAS");
        config.setFontsize(textArea.getFont().getSize());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.cfg"))) {
            oos.writeObject(config);
            JOptionPane.showMessageDialog(this, "Objek disimpan!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //  Latihan 3 Muat Objek 
    private void muatUserConfig() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.cfg"))) {
            UserConfig config = (UserConfig) ois.readObject();
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontsize()));
            JOptionPane.showMessageDialog(this, "Objek dimuat! User: " + config.getUsername());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error muat objek: " + ex.getMessage());
        }
    }

    // Latihan 4 Append Tambah Teks 
    private void tambahFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Parameter true = mode append
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Teks ditambahkan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplikasifileIO().setVisible(true));
    }
}