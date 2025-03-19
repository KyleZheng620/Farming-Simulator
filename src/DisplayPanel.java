import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class DisplayPanel extends JPanel implements KeyListener, MouseListener, ActionListener {
    private int farmerX;
    private int farmerY;
    private BufferedImage farmer;
    private BufferedImage plotLand;
    private BufferedImage spriteSheet;
    private FarmLand farmPlots;

    public DisplayPanel() {
        farmerX = 0;
        farmerY = 0;
        farmPlots = new FarmLand();

        // UPDATE timer to be 10ms, which will now trigger 100 times per second
        Timer timer = new Timer(10, this);
        timer.start();

        try {
            farmer = ImageIO.read(new File("src\\farmer.png"));
            plotLand = ImageIO.read(new File("src\\plotLand.png"));
            spriteSheet = ImageIO.read(new File("src\\crop_spritesheet-1.png-2.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int spriteWidth = 48;
        int spriteHeight = 48;
        for (int r = 0; r < farmPlots.getPlots().size(); r++){
            for (int c = 0; c < farmPlots.getPlots().get(r).size(); c++){
                g.drawImage(plotLand, 100+(64*c), 300+(64*r), null);
                if (farmPlots.getPlots().get(r).get(c).getCrop().equals("Rice")) {
                    int sx1 = 10 * spriteWidth - farmPlots.getPlots().get(r).get(c).getWaterLevel()*48;
                    int sy1 = 3 * spriteHeight;
                    int dx1 = 100 + (50 * c);
                    int dy1 = 300 + (50 * r) - 15;
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                } else if (farmPlots.getPlots().get(r).get(c).getCrop().equals("Potatoes")) {
                    int sx1 = 5 * spriteWidth - farmPlots.getPlots().get(r).get(c).getWaterLevel()*48;
                    int sy1 = 7 * spriteHeight;
                    int dx1 = 100 + (50 * c);
                    int dy1 = 300 + (50 * r) - 15;
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                }else if (farmPlots.getPlots().get(r).get(c).getCrop().equals("Wheat")) {
                    int sx1 = 5 * spriteWidth - farmPlots.getPlots().get(r).get(c).getWaterLevel()*48;
                    int sy1 = 5 * spriteHeight;
                    int dx1 = 100 + (50 * c);
                    int dy1 = 300 + (50 * r) - 15;
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                } else if (farmPlots.getPlots().get(r).get(c).getCrop().equals("Mandarin")){
                    int sx1 = 5 * spriteWidth - farmPlots.getPlots().get(r).get(c).getWaterLevel()*48;
                    int sy1 = 8 * spriteHeight;
                    int dx1 = 100 + (50 * c);
                    int dy1 = 300 + (50 * r) - 15;
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                }
            }
        }
        g.drawImage(farmer, farmerX, farmerY, null);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == 38) {  // up key
            farmerY -= 5;
        } else if (key == 40) { // down key
            farmerY += 5;
        } else if (key == 37) { // left key
            farmerX -= 5;
        } else if (key == 39) {  // right key
            farmerX += 5;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}