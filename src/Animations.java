import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animations extends JPanel implements ActionListener {
    private double col;
    private double row;
    private FarmGame farmGame;
    private BufferedImage wateringCan;
    private int currentFrame;
    private boolean watering;


    public Animations(FarmGame farmGame){
        currentFrame = 0;
        this.farmGame = farmGame;
        watering = false;
        Timer timer = new Timer(400, this);
        timer.start();
        try {
            wateringCan = ImageIO.read(new File("src/Sprites/Watering.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        if (watering){
            int wx1 = 87 * currentFrame;
            int wy1 = 0;
            int wx2 = wx1 + 87;
            int wy2 = wy1 + 87;
            int x = (int) row * 54 + 50;
            int y = (int) col * 55 + 400;
            g.drawImage(wateringCan, x, y, x + 87, y + 87, wx1, wy1, wx2, wy2, null);
            if (currentFrame == 3){
                farmGame.showFarm(0);
                watering = false;
            }
        }
    }

    public void watering(double col, double row){
        currentFrame = 0;
        this.col = col;
        this.row = row;
        watering = true;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame = (currentFrame + 1) % 4;
        repaint();
    }

}
