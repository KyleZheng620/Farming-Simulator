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
    private BufferedImage farmerIdle;
    private BufferedImage plotLand;
    private BufferedImage spriteSheet;
    private BufferedImage background;
    private BufferedImage barn;
    private BufferedImage shop;
    private FarmLand farmPlots;
    private Farmer player;
    private int direction;
    private boolean moving;
    private int currentFrame;

    public DisplayPanel() {
        moving = false;
        currentFrame = 0;
        farmerX = 900;
        farmerY = 500;
        direction = 0;
        player = new Farmer();
        farmPlots = new FarmLand(player);

        Timer timer = new Timer(150, this);
        timer.start();

        try {
            background = ImageIO.read(new File("src\\background.png"));
            barn = ImageIO.read(new File("src\\Barn.png"));
            farmer = ImageIO.read(new File("src\\farmer.png"));
            farmerIdle = ImageIO.read(new File("src\\farmer_idle.png"));
            plotLand = ImageIO.read(new File("src\\plotLand.png"));
            spriteSheet = ImageIO.read(new File("src\\crop_spritesheet-1.png-2.png"));
            shop = ImageIO.read(new File("src/ShopOutside.jpg"));

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
        g.drawImage(background, 0, 0, null);
        g.drawImage(barn, 30,30,null);
        int spriteWidth = 48;
        int spriteHeight = 48;
        for (int r = 0; r < farmPlots.getPlots().size(); r++){
            for (int c = 0; c < farmPlots.getPlots().get(r).size(); c++){
                g.drawImage(plotLand, 80+(64*c), 450+(64*r), null);
                if (farmPlots.getPlots().get(r).get(c).getCrop().equals("Rice")) {
                    int sx1 = 10 * spriteWidth - farmPlots.getPlots().get(r).get(c).getWaterLevel()*48;
                    int sy1 = 3 * spriteHeight;
                    int dx1 = 90 + (64 * c);
                    int dy1 = 450 + (64 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                } else if (farmPlots.getPlots().get(r).get(c).getCrop().equals("Potatoes")) {
                    int sx1 = 5 * spriteWidth - farmPlots.getPlots().get(r).get(c).getWaterLevel()*48;
                    int sy1 = 7 * spriteHeight;
                    int dx1 = 90 + (64 * c);
                    int dy1 = 450 + (64 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                }else if (farmPlots.getPlots().get(r).get(c).getCrop().equals("Wheat")) {
                    int sx1 = 5 * spriteWidth - farmPlots.getPlots().get(r).get(c).getWaterLevel()*48;
                    int sy1 = 5 * spriteHeight;
                    int dx1 = 90 + (64 * c);
                    int dy1 = 450 + (64 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                }else if (farmPlots.getPlots().get(r).get(c).getCrop().equals("Mandarin")) {
                    int sx1 = 5 * spriteWidth - farmPlots.getPlots().get(r).get(c).getWaterLevel() * 48;
                    int sy1 = 8 * spriteHeight;
                    int dx1 = 85 + (64 * c);
                    int dy1 = 450 + (64 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                }
            }
        }

        int fx1 = direction * 128;
        int fy1 = currentFrame * 128;
        int fx2 = fx1 + 128;
        int fy2 = fy1 + 128;
        if (moving){
            g.drawImage(farmer, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        } else {
            g.drawImage(farmerIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame = (currentFrame + 1) % 4;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        moving = true;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                farmerY -= 10;
                direction = 0;   // Up
                break;
            case KeyEvent.VK_DOWN:
                farmerY += 10;
                direction = 2;   // Down
                break;
            case KeyEvent.VK_LEFT:
                farmerX -= 10;
                direction = 3;   // Left
                break;
            case KeyEvent.VK_RIGHT:
                farmerX += 10;
                direction = 1;   // Right
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        moving = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {
        moving = false;
    }
}