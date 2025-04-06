import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinLoseScreen extends JPanel {
    private int screen;
    private int boatX;
    private BufferedImage lose1;
    private BufferedImage lose2;
    private BufferedImage win;
    private BufferedImage winBoat;

    public WinLoseScreen(){
        try {
            lose1 = ImageIO.read(new File("src/Sprites/Lose1.png"));
            lose2 = ImageIO.read(new File("src/Sprites/Lose2.png"));
            win = ImageIO.read(new File("src/Sprites/Win.png"));
            winBoat = ImageIO.read(new File("src/Sprites/WinBoat.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void setScreen(int a){
        screen = a;
        Timer timer = new Timer(500, e -> {
            boatX = boatX + 80;
            repaint();
        });
        timer.start();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(screen == 1){
            g.drawImage(lose1,0,0, null);
        } else if (screen == 2){
            g.drawImage(lose2, 0 ,0,null);
        } else if (screen == 3){
            g.drawImage(win, 0,0, null);
            g.drawImage(winBoat, 20 + boatX, 400, null);
        }

    }
}
