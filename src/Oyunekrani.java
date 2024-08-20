import javax.swing.*;
import java.awt.*;

public class Oyunekrani extends JFrame {
    public Oyunekrani(String title) throws HeadlessException {
        super(title);
    }

    public static <string> void main (string[] args) {
    Oyunekrani ekran = new Oyunekrani("Uzay Oyunu");
    ekran.setResizable(false);
    ekran.setFocusable(false);
    ekran.setSize(1000,800);
    ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Oyun oyun =new Oyun();
    oyun.requestFocus();
    oyun.addKeyListener(oyun);
    oyun.setFocusable(true);
    oyun.setFocusTraversalKeysEnabled(false);
    ekran.add(oyun);
    ekran.setVisible(true);






    }
}