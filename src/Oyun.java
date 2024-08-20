import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Ates {
    private int x;
    private int y;


    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Oyun extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private int gecen_sure = 0;
    private int harcanan_sure = 0;
    private int harcanan_ates = 0;

    private BufferedImage image;
    private ArrayList<Ates> atesler = new ArrayList<>();

    private int atesdirY = 1;
    private int topX = 0;
    private int topdirX = 2;
    private int uzayGemisiX = 0;
    private int dirUzayX = 20;

    public Oyun() {
        try {
            image = ImageIO.read(new File("out/production/uzayOyunuProjesi/uzaygemisi.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBackground(Color.BLACK);

        timer = null;
        timer = new Timer(16, this); // Yaklaşık 60 FPS

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gecen_sure += 5;

        g.setColor(Color.red);
        g.fillOval(topX, 0, 20, 20);
        g.drawImage(image, uzayGemisiX, 490+30, image.getWidth() / 10, image.getHeight() / 10, this);
        atesler.removeIf(ates -> ates.getY() < 0);

        g.setColor(Color.BLUE);

        for (Ates ates : atesler) {
            g.fillRect(ates.getX(), ates.getY(), 10, 20);

        }

        if (kontrolEt()) {
            timer.stop();
            String message = "kazandınız...\n" +
                    "harcanan ateş : " + harcanan_ates +
                    "\ngecen süre : " + gecen_sure / 1000.0 + "saniye";
            String s = "harcanan süre :" + harcanan_sure + "?";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);


        }
    }

    private boolean kontrolEt() {
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Ates ates : atesler) {
            ates.setY(ates.getY() - atesdirY);
        }

        topX += topdirX;
        if (topX >= 750) {
            topX = -topdirX;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            uzayGemisiX -= dirUzayX;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            uzayGemisiX += dirUzayX;
        } else if (keyCode == KeyEvent.VK_CONTROL) {
            atesler.add(new Ates(uzayGemisiX + 180, 550));
            harcanan_ates++;

        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Ana metod
    public static void main(String[] args) {
        JFrame frame = new JFrame("Uzay Oyunu");
        Oyun oyunPaneli = new Oyun();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800); // Pencere boyutu
        frame.add(oyunPaneli);
        frame.setVisible(true);

        // Klavye olaylarını dinlemek için ekleyin
        frame.addKeyListener(oyunPaneli);
    }
}
