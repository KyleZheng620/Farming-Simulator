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


    public DisplayPanel() {
        farmerX = 0;
        farmerY = 0;

        // UPDATE timer to be 10ms, which will now trigger 100 times per second
        Timer timer = new Timer(10, this);
        timer.start();

        try {
            farmer = ImageIO.read(new File("src\\farmer.png"));
            plotLand = ImageIO.read(new File("src\\plotLand.png"));

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
        for (int r = 0; r < farmPlot.size(); r++){
            for (int c = 0; c < plots.get(r).size(); c++){
                if (plots.get(r).get(c).getCrop().equals("soil")){
                    g.drawImage(plotLand, 100+(50*c), 300+(50*r), null);
                }
            }
        }
        g.drawImage(farmer, farmerX, farmerY, null);
        System.out.println(farmerX);
        System.out.println(farmerY);
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